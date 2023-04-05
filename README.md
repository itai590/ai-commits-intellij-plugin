

<div align="center">
    <img src="./src/main/resources/META-INF/pluginIcon.svg" width="200" height="200" alt="logo"/>
</div>
<h1 align="center">CommitGPT</h1>
<p align="center">CommitGPT for IntelliJ based IDEs/Android Studio.</p>

<p align="center">
<a href="https://github.com/Marc-R2/ai-commits-intellij-plugin/actions/workflows/build.yml/badge.svg)](https://github.com/Marc-R2/ai-commits-intellij-plugin/actions/workflows/build.yml"><img alt="Build Status" src="https://img.shields.io/endpoint.svg?url=https%3A%2F%2Factions-badge.atrox.dev%2Fblarc%2Fai-commits-intellij-plugin%2Fbadge%3Fref%3Dmain&style=popout-square" /></a>
<!--
<a href="https://plugins.jetbrains.com/plugin/21335-ai-commits"><img src="https://img.shields.io/jetbrains/plugin/r/stars/21335?style=flat-square"></a>
<a href="https://plugins.jetbrains.com/plugin/21335-ai-commits"><img src="https://img.shields.io/jetbrains/plugin/d/21335-ai-commits.svg?style=flat-square"></a>
<a href="https://plugins.jetbrains.com/plugin/21335-ai-commits"><img src="https://img.shields.io/jetbrains/plugin/v/21335-ai-commits.svg?style=flat-square"></a>
-->
</p>
<br>

- [Description](#description)
- [Features](#features)
- [Hint for the AI](#hint-the-ai)
- [Custom prompt](#custom-prompt)
- [Compatibility](#compatibility)
- [Install](#install)
- [Installation from zip](#installation-from-zip)

[//]: # (- [Demo]&#40;#demo&#41;)

## Description
CommitGPT is a plugin that generates your commit messages with ChatGPT. To get started, 
install the plugin and set OpenAI private token in plugin's settings:
<kbd>Settings</kbd> > <kbd>Tools</kbd> > <kbd>CommitGPT</kbd>

## Features
- Generate commit message from diff using OpenAI ChatGPT API
- Compute diff only from the selected files and lines in the commit dialog
- Choose your own base prompt
- Include a hint in the prompt for the AI to generate a better commit message.

## Hint the AI
You can provide a hint for the AI to generate a better commit message by
writing a sentence in the commit dialog starting with a `!`.

This hint will be included in the prompt for the AI.

*Note:* Your custom prompt have to include `{hint}` at some point in order for the hint to be included.
By default, this is the case.

## Custom prompt
You can choose your own base prompt for the AI to generate the commit message from in the settings.

Your custom prompt have to include `{diffs}`, otherwise the AI will not be able to generate a commit message based on your changes.
The prompt can also include `{hint}` to include a hint in the prompt for the AI to generate a better commit message.

*Note:* The custom prompt will not be saved in the settings if it does not include `{diffs}`.
`{hint}` is optional.

## Compatibility
IntelliJ IDEA, PhpStorm, WebStorm, PyCharm, RubyMine, AppCode, CLion, GoLand, DataGrip, Rider, MPS, Android Studio, DataSpell, Code With Me

## Install

<!--
<a href="https://plugins.jetbrains.com/embeddable/install/21335">
<img src="https://user-images.githubusercontent.com/12044174/123105697-94066100-d46a-11eb-9832-338cdf4e0612.png" width="300"/>
</a>

Or you could install it inside your IDE:

For Windows & Linux: <kbd>File</kbd> > <kbd>Settings</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "CommitGPT"</kbd> > <kbd>Install Plugin</kbd> > <kbd>Restart IntelliJ IDEA</kbd>

For Mac: <kbd>IntelliJ IDEA</kbd> > <kbd>Preferences</kbd> > <kbd>Plugins</kbd> > <kbd>Marketplace</kbd> > <kbd>Search for "CommitGPT"</kbd> > <kbd>Install Plugin</kbd>  > <kbd>Restart IntelliJ IDEA</kbd>
-->

Install from JetBrains Marketplace is currently not available for this fork.
Please use the installation from zip method below.

### Installation from zip
1. Download zip from [releases](https://github.com/Marc-R2/ai-commits-intellij-plugin/releases)
2. Import to IntelliJ: <kbd>Settings</kbd> > <kbd>Plugins</kbd> > <kbd>Cog</kbd> > <kbd>Install plugin from disk...</kbd>
3. Set OpenAI private token in plugin's settings: <kbd>Settings</kbd> > <kbd>Tools</kbd> > <kbd>CommitGPT</kbd>

[//]: # (## Demo)

[//]: # ()
[//]: # (![demo.gif]&#40;./screenshots/plugin2.gif&#41;)

## Support

* Star the repository
* [Buy me a coffee](https://ko-fi.com/marcr2)
<!--
* [Rate the original plugin](https://plugins.jetbrains.com/plugin/21335-ai-commits)
* [Share the plugin](https://plugins.jetbrains.com/plugin/21335-ai-commits)
-->

## Change log

Please see [CHANGELOG](CHANGELOG.md) for more information what has changed recently.
This may not be complete, so please check the [commit history](https://github.com/Marc-R2/ai-commits-intellij-plugin/commits)

## Contributing

We welcome contributions of all kinds.

If you find a bug, have a question or a feature request, please file an issue.

If you'd like to contribute code, fork the repository, make your changes and feel free to submit a pull request.
Please see [CONTRIBUTING](CONTRIBUTING.md) for details.

## Acknowledgements

- Fork from Blarc's [AI Commits](https://github.com/Blarc/ai-commits-intellij-plugin)
- Originally inspired by Nutlope's [AICommits](https://github.com/Nutlope/aicommits).
- [openai-kotlin](https://github.com/aallam/openai-kotlin) for OpenAI API client.

## License

Please see [LICENSE](LICENSE) for details.
