package com.github.blarc.ai.commits.intellij.plugin

import com.aallam.openai.api.BetaOpenAI
import com.aallam.openai.api.chat.*
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.github.blarc.ai.commits.intellij.plugin.notifications.Notification
import com.github.blarc.ai.commits.intellij.plugin.notifications.sendNotification
import com.github.blarc.ai.commits.intellij.plugin.settings.AppSettings
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.Service

import com.knuddels.jtokkit.Encodings
import com.knuddels.jtokkit.api.EncodingType

@Service
class OpenAIService {

    companion object {
        const val model = "gpt-4o-mini"
        val instance: OpenAIService
            get() = ApplicationManager.getApplication().getService(OpenAIService::class.java)
    }

    @OptIn(BetaOpenAI::class)
    suspend fun generateCommitMessage(diff: String, hint: String?, completions: Int): String {
        val openAiToken = AppSettings.instance.getOpenAIToken() ?: throw Exception("OpenAI Token is not set")

        val openAI = OpenAI(openAiToken)

        val prompt = AppSettings.instance.getPrompt(diff, hint)

        if (isPromptTooLarge(prompt)) {
            sendNotification(Notification.promptTooLarge())
            return "Prompt is too large"
        }

        sendNotification(Notification.usedPrompt(prompt))

        val chatCompletionRequest = ChatCompletionRequest(
            ModelId(model),
            listOf(
                ChatMessage(
                    role = ChatRole.User,
                    content = prompt
                )
            ),
            temperature = 0.7,
            topP = 1.0,
            frequencyPenalty = 0.0,
            presencePenalty = 0.0,
            maxTokens = 2048,
            n = completions
        )

        val completion: ChatCompletion = openAI.chatCompletion(chatCompletionRequest)
        return completion.choices[0].message!!.content
    }
    @Throws(Exception::class)
    suspend fun verifyToken(token: String){
        OpenAI(token).models()
    }

    private fun isPromptTooLarge(prompt: String): Boolean {
        val registry = Encodings.newDefaultEncodingRegistry()
        val encoding = registry.getEncoding(EncodingType.CL100K_BASE)
        return encoding.countTokens(prompt) > 4000
    }
}
