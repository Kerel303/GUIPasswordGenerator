package dev.kerel.guipasswordgenerator

import dev.kerel.guipasswordgenerator.PasswordApp;

object AppLauncher {
    @JvmStatic
    fun main(args: Array<String>) {
        javafx.application.Application.launch(PasswordApp::class.java, *args)
    }
}
