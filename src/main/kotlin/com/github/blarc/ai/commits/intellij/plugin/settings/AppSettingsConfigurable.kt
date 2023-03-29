package com.github.blarc.ai.commits.intellij.plugin.settings

import com.github.blarc.ai.commits.intellij.plugin.AICommitsBundle.message
import com.intellij.ide.starters.shared.withValidation
import com.intellij.openapi.options.BoundConfigurable
import com.intellij.ui.dsl.builder.*
import java.awt.Component
import java.util.*
import javax.swing.DefaultListCellRenderer
import javax.swing.JList

class AppSettingsConfigurable : BoundConfigurable(message("settings.general.group.title")) {
    override fun createPanel() = panel {
        // OpenAI Token
        row {
            passwordField()
                .label(message("settings.openAIToken"))
                .bindText(
                    { AppSettings.instance.getOpenAIToken().orEmpty() },
                    { AppSettings.instance.saveOpenAIToken(it) }
                )
                .align(Align.FILL)
        }
        // Get OpenAI Token
        row {
            comment(message("settings.openAITokenComment"))
        }

        // Prompt to use with OpenAI
        row {
            textField()
                .label(message("settings.prompt"))
                .bindText(
                    { AppSettings.instance.getPrompt() },
                    { AppSettings.instance.savePrompt(it) }
                )
                .align(Align.FILL)
        }


    }
}