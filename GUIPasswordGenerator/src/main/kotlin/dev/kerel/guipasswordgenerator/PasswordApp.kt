package dev.kerel.guipasswordgenerator;

import javafx.application.Application
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.layout.VBox
import javafx.stage.Stage

class PasswordApp : Application() {
    override fun start(stage: Stage) {
        // 1. Elementy interfejsu
        val resultField = TextField().apply {
            isEditable = false
            promptText = "Your password will be shown here"
            style = "-fx-font-size: 16px; -fx-alignment: center;"
        }

        val lengthLabel = Label("Length: 12")
        val slider = Slider(8.0, 32.0, 12.0).apply {
            blockIncrement = 1.0
            isSnapToTicks = true
            majorTickUnit = 4.0
        }

        val specialCharsCb = CheckBox("Add special characters (!@#$)")
        val generateBtn = Button("Generate password").apply {
            style = "-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;"
            maxWidth = Double.MAX_VALUE
        }

        // 2. Logika suwaka (aktualizacja tekstu na żywo)
        slider.valueProperty().addListener { _, _, newValue ->
            lengthLabel.text = "Length: ${newValue.toInt()}"
        }

        // 3. Logika przycisku
        generateBtn.setOnAction {
            val length = slider.value.toInt()
            val useSpecial = specialCharsCb.isSelected
            resultField.text = generatePassword(length, useSpecial)
        }

        // 4. Układ (Layout)
        val root = VBox(15.0).apply {
            padding = Insets(20.0)
            alignment = Pos.CENTER
            children.addAll(
                Label("PASSWORD GENERATOR").apply { style = "-fx-font-weight: bold; -fx-font-size: 18px;" },
                resultField,
                lengthLabel,
                slider,
                specialCharsCb,
                generateBtn
            )
        }

        stage.scene = Scene(root, 350.0, 400.0)
        stage.title = "Password Generator"
        stage.show()
    }

    private fun generatePassword(length: Int, special: Boolean): String {
        val chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        val symbols = "!@#$%^&*()-_=+"
        val combined = if (special) chars + symbols else chars
        return (1..length)
            .map { combined.random() }
            .joinToString("")
    }
}
