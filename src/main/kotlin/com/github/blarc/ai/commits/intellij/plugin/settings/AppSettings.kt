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
import java.util.Locale

@State(
    name = AppSettings.SERVICE_NAME,
    storages = [Storage("AICommit.xml")]
)
class AppSettings : PersistentStateComponent<AppSettings> {

    private val openAITokenTitle = "OpenAIToken"
    private val openAIPromptTitle = "OpenAIPrompt"

    private var defaultPrompt = "As a professional developer working with a Git repository, it's important to maintain clear and concise commit messages that follow the standard format. Please create a commit message for the following changes: {diffs}. Remember to label everything logically and keep it short. Please use the following format <type>(<scope>): <short> \n\n [...]'. And here's a helpful hint from another developer: {hint}. Maybe you can use it to help you write your commit message."

    var requestSupport = true

    companion object {
        const val SERVICE_NAME = "com.github.blarc.ai.commits.intellij.plugin.settings.AppSettings"

        val instance: AppSettings
            get() = ApplicationManager.getApplication().getService(AppSettings::class.java)
    }

    fun saveOpenAIToken(token: String) {
        println("Saving token: $token")
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

    fun getPrompt(): String {
        return PropertiesComponent.getInstance().getValue(openAIPromptTitle, defaultPrompt)
    }

    fun savePrompt(it: String) {
        if (!it.contains("{diffs}")) return
        PropertiesComponent.getInstance().setValue(openAIPromptTitle, it)
    }


}