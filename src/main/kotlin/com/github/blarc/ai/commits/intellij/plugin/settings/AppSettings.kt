package com.github.blarc.ai.commits.intellij.plugin.settings

import com.github.blarc.ai.commits.intellij.plugin.notifications.Notification
import com.github.blarc.ai.commits.intellij.plugin.notifications.sendNotification
import com.intellij.credentialStore.CredentialAttributes
import com.intellij.credentialStore.Credentials
import com.intellij.ide.passwordSafe.PasswordSafe
import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.util.xmlb.XmlSerializerUtil

@State(
    name = AppSettings.SERVICE_NAME,
    storages = [Storage("CommitGPT.xml")]
)
class AppSettings : PersistentStateComponent<AppSettings> {

    private val openAITokenTitle = "OpenAIToken"
    private val openAIPromptTitle = "OpenAIPrompt"

    private var hits = 0

    private var defaultPrompt = "Write a git commit message. " +
            "Use the conventional commit convention and follow best practices to maintain clear and concise commit messages. " +
            "The format have to be 'type(scope): short' and should not exceed 74 characters.\n" +
            "Generate a meaningful commit message for the given changes in '''{diffs}'''. " +
            "Please describe the changes briefly and why they were made in the present tense.\n" +
            "If available, use the hints provided by the user in '''{hint}''' to help you write the commit message.\n" +
            "Remember, do not preface the commit with anything and add a short description of why the commit was done after the commit message."

    var requestSupport = true
    var lastVersion: String? = null

    companion object {
        const val SERVICE_NAME = "com.github.blarc.ai.commits.intellij.plugin.settings.AppSettings"
        val instance: AppSettings
            get() = ApplicationManager.getApplication().getService(AppSettings::class.java)
    }

    fun getPrompt(): String {
        return PropertiesComponent.getInstance().getValue(openAIPromptTitle, defaultPrompt)
    }

    fun savePrompt(it: String) {
        if (!it.contains("{diffs}")) return
        PropertiesComponent.getInstance().setValue(openAIPromptTitle, it)
    }

    fun saveOpenAIToken(token: String) {
        try {
            PasswordSafe.instance.setPassword(getCredentialAttributes(openAITokenTitle), token)
        } catch (e: Exception) {
            sendNotification(Notification.unableToSaveToken())
        }
    }

    fun getOpenAIToken(): String? {
        val credentialAttributes = getCredentialAttributes(openAITokenTitle)
        val credentials: Credentials = PasswordSafe.instance.get(credentialAttributes) ?: return null
        return credentials.getPasswordAsString()
    }

    private fun getCredentialAttributes(title: String): CredentialAttributes {
        return CredentialAttributes(
            title,
            null,
            this.javaClass,
            false
        )
    }

    override fun getState() = this

    override fun loadState(state: AppSettings) {
        XmlSerializerUtil.copyBean(state, this)
    }

    fun recordHit() {
        hits++
        return
        /* if (requestSupport && (hits == 50 || hits % 100 == 0)) {
            sendNotification(Notification.star())
        } */
    }
}