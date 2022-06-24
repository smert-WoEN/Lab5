package com.company.client.GUI

import com.company.client.ClientSocket
import com.company.client.commands.Register
import java.awt.BorderLayout
import java.awt.Dimension
import java.util.*
import javax.swing.*


class Login: JFrame("app") {
    val port = 25566
    val ip = "127.0.0.1"
    val clientSocket = ClientSocket(ip, port)
    lateinit var localeLang: Locale
    lateinit var resourceBundle: ResourceBundle
    lateinit var mainTitle: JLabel
    lateinit var loginLabel: JLabel
    lateinit var passwordLabel: JLabel
    lateinit var languageLabel: JLabel
    lateinit var enterButton: JButton
    lateinit var registerButton: JButton
    lateinit var passwordRepeatLabel: JLabel
    lateinit var askRegButton: JButton
    lateinit var regTitle: JLabel
    lateinit var loginButton: JButton
    init {
        clientSocket.initialize()
        val mainPanel = JPanel(BorderLayout())
        val login = JPanel()
        val register = JPanel()
        val layoutRegister = SpringLayout()
        val layout = SpringLayout()
        localeLang = Locale("ru", "RU")
        resourceBundle = ResourceBundle.getBundle("com.company.client.lang.Bundle", localeLang)
        //val frame = JFrame("app")
        mainTitle = JLabel(resourceBundle.getString("auth"))
        loginLabel = JLabel(resourceBundle.getString("login"))
        passwordLabel = JLabel(resourceBundle.getString("password"))
        passwordRepeatLabel = JLabel(resourceBundle.getString("password2"))
        regTitle = JLabel(resourceBundle.getString("reg"))
        languageLabel = JLabel("Language")
        val languageBox = JComboBox<String>()
        languageBox.maximumSize = Dimension(50, 40)
        languageBox.isEditable = false
        languageBox.addItem("Русский")
        languageBox.addItem("Suomalainen")
        languageBox.addItemListener { e ->
            if (e.item.toString() == "Русский") {
                localeLang = Locale("ru", "RU")
            } else if (e.item.toString() == "Suomalainen") {
                localeLang = Locale("fi", "FI")
            }
//            } else if (e.item.toString() == "Français") {
//                locale = Locale("fr", "FR")
//            } else if (e.item.toString() == "español") {
//                locale = Locale("es", "PR")
//            }
            resourceBundle = ResourceBundle.getBundle("com.company.client.lang.Bundle", localeLang)
            changeLang()
        }

        val languagePanel = JPanel()
        languagePanel.add(languageLabel)
        languagePanel.add(languageBox)

//        layoutLanguage.putConstraint(SpringLayout.HORIZONTAL_CENTER, languageBox, 40, SpringLayout.HORIZONTAL_CENTER, languagePanel)
//        layoutLanguage.putConstraint(SpringLayout.VERTICAL_CENTER, languageBox, 0, SpringLayout.VERTICAL_CENTER, languagePanel)
//        layoutLanguage.putConstraint(SpringLayout.HORIZONTAL_CENTER, languageLabel, -100, SpringLayout.HORIZONTAL_CENTER, languagePanel)
//        layoutLanguage.putConstraint(SpringLayout.VERTICAL_CENTER, languageLabel, 0, SpringLayout.VERTICAL_CENTER, languagePanel)

        val loginFiled = JTextField(20)
        val passwordField = JPasswordField(20)
        val passwortRepeatField = JPasswordField(20)

        enterButton = JButton(resourceBundle.getString("enter"))
        enterButton.addActionListener {
            val login1 = loginFiled.text
            val password: String = java.lang.String.valueOf(passwordField.password)
            val command = com.company.client.commands.Login(clientSocket)
            val message = command.execute("$login1 $password")
            JOptionPane.showMessageDialog(this, resourceBundle.getString(message))
            if (message == "okLP") {
                mainPanel.removeAll()
                Main(clientSocket, resourceBundle, this)
            }
            //println(command.execute("$login1 $password"))



        }

        registerButton = JButton(resourceBundle.getString("register"))
        registerButton.addActionListener {
            val login1 = loginFiled.text
            val password: String = java.lang.String.valueOf(passwordField.password)
            val password2: String = java.lang.String.valueOf(passwortRepeatField.password)
            val command = Register(clientSocket)
            val message = command.execute("$login1 $password $password2")
            JOptionPane.showMessageDialog(this, resourceBundle.getString(message))
            if (message == "RC") {
                mainPanel.removeAll()
                login.add(mainTitle)
                login.add(loginLabel)
                login.add(loginFiled)
                login.add(passwordField)
                login.add(passwordLabel)
                login.add(enterButton)
                login.add(askRegButton)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainTitle, 0, SpringLayout.HORIZONTAL_CENTER, login)
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainTitle, -40, SpringLayout.VERTICAL_CENTER, login)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginLabel, -180, SpringLayout.HORIZONTAL_CENTER, login)
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginLabel, 0, SpringLayout.VERTICAL_CENTER, login)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginFiled, 0, SpringLayout.HORIZONTAL_CENTER, login)
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginFiled, 0, SpringLayout.VERTICAL_CENTER, login)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordLabel, -180, SpringLayout.HORIZONTAL_CENTER, login)
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLabel, 40, SpringLayout.VERTICAL_CENTER, login)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, 0, SpringLayout.HORIZONTAL_CENTER, login)
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, 40, SpringLayout.VERTICAL_CENTER, login)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, enterButton, -100, SpringLayout.HORIZONTAL_CENTER, login)
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, enterButton, 80, SpringLayout.VERTICAL_CENTER, login)
                layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, askRegButton, 60, SpringLayout.HORIZONTAL_CENTER, login)
                layout.putConstraint(SpringLayout.VERTICAL_CENTER, askRegButton, 80, SpringLayout.VERTICAL_CENTER, login)
                mainPanel.add(BorderLayout.CENTER, login)
                mainPanel.add(BorderLayout.SOUTH, languagePanel)
                revalidate()
                repaint()
                isVisible = true
            }

        }

        loginButton = JButton(resourceBundle.getString("askLog"))
        loginButton.addActionListener {
            mainPanel.removeAll()
            login.add(mainTitle)
            login.add(loginLabel)
            login.add(loginFiled)
            login.add(passwordField)
            login.add(passwordLabel)
            login.add(enterButton)
            login.add(askRegButton)
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainTitle, 0, SpringLayout.HORIZONTAL_CENTER, login)
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainTitle, -40, SpringLayout.VERTICAL_CENTER, login)
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginLabel, -180, SpringLayout.HORIZONTAL_CENTER, login)
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginLabel, 0, SpringLayout.VERTICAL_CENTER, login)
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginFiled, 0, SpringLayout.HORIZONTAL_CENTER, login)
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginFiled, 0, SpringLayout.VERTICAL_CENTER, login)
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordLabel, -180, SpringLayout.HORIZONTAL_CENTER, login)
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLabel, 40, SpringLayout.VERTICAL_CENTER, login)
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, 0, SpringLayout.HORIZONTAL_CENTER, login)
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, 40, SpringLayout.VERTICAL_CENTER, login)
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, enterButton, -100, SpringLayout.HORIZONTAL_CENTER, login)
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, enterButton, 80, SpringLayout.VERTICAL_CENTER, login)
            layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, askRegButton, 60, SpringLayout.HORIZONTAL_CENTER, login)
            layout.putConstraint(SpringLayout.VERTICAL_CENTER, askRegButton, 80, SpringLayout.VERTICAL_CENTER, login)
            mainPanel.add(BorderLayout.CENTER, login)
            mainPanel.add(BorderLayout.SOUTH, languagePanel)
            revalidate()
            repaint()
            isVisible = true
        }

        askRegButton = JButton(resourceBundle.getString("askReg"))
        askRegButton.addActionListener {
            mainPanel.removeAll()
            register.add(regTitle)
            register.add(loginLabel)
            register.add(loginFiled)
            register.add(passwordField)
            register.add(passwordLabel)
            register.add(registerButton)
            register.add(passwordRepeatLabel)
            register.add(passwortRepeatField)
            register.add(loginButton)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, regTitle, 0, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, regTitle, -40, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginLabel, -180, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, loginLabel, 0, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginFiled, 0, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, loginFiled, 0, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordLabel, -180, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLabel, 40, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, 0, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, 40, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordRepeatLabel, -180, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, passwordRepeatLabel, 80, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwortRepeatField, 0, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, passwortRepeatField, 80, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, registerButton, -100, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, registerButton, 120, SpringLayout.VERTICAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginButton, 60, SpringLayout.HORIZONTAL_CENTER, register)
            layoutRegister.putConstraint(SpringLayout.VERTICAL_CENTER, loginButton, 120, SpringLayout.VERTICAL_CENTER, register)
            mainPanel.add(BorderLayout.SOUTH, languagePanel)
            mainPanel.add(BorderLayout.CENTER, register)
            revalidate()
            repaint()
            isVisible = true
        }
        register.layout = layoutRegister

        login.layout = layout
        login.add(mainTitle)
        login.add(loginLabel)
        login.add(loginFiled)
        login.add(passwordField)
        login.add(passwordLabel)
        login.add(enterButton)
        login.add(askRegButton)
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainTitle, 0, SpringLayout.HORIZONTAL_CENTER, login)
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, mainTitle, -40, SpringLayout.VERTICAL_CENTER, login)
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginLabel, -180, SpringLayout.HORIZONTAL_CENTER, login)
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginLabel, 0, SpringLayout.VERTICAL_CENTER, login)
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, loginFiled, 0, SpringLayout.HORIZONTAL_CENTER, login)
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, loginFiled, 0, SpringLayout.VERTICAL_CENTER, login)
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordLabel, -180, SpringLayout.HORIZONTAL_CENTER, login)
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordLabel, 40, SpringLayout.VERTICAL_CENTER, login)
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, passwordField, 0, SpringLayout.HORIZONTAL_CENTER, login)
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, passwordField, 40, SpringLayout.VERTICAL_CENTER, login)
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, enterButton, -100, SpringLayout.HORIZONTAL_CENTER, login)
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, enterButton, 80, SpringLayout.VERTICAL_CENTER, login)
        layout.putConstraint(SpringLayout.HORIZONTAL_CENTER, askRegButton, 60, SpringLayout.HORIZONTAL_CENTER, login)
        layout.putConstraint(SpringLayout.VERTICAL_CENTER, askRegButton, 80, SpringLayout.VERTICAL_CENTER, login)

        mainPanel.add(BorderLayout.CENTER, login)
        mainPanel.add(BorderLayout.SOUTH, languagePanel)
        add(mainPanel)
        defaultCloseOperation = EXIT_ON_CLOSE
        setSize(960, 720)
        isVisible = true
    }

    private fun changeLang() {
        mainTitle.text = resourceBundle.getString("auth")
        loginLabel.text = (resourceBundle.getString("login"))
        passwordLabel.text = (resourceBundle.getString("password"))
        enterButton.text = resourceBundle.getString("enter")
        askRegButton.text = resourceBundle.getString("askReg")
        passwordRepeatLabel.text = resourceBundle.getString("password2")
        registerButton.text = resourceBundle.getString("register")
        regTitle.text = resourceBundle.getString("reg")
        loginButton.text = resourceBundle.getString("askLog")
    }
}