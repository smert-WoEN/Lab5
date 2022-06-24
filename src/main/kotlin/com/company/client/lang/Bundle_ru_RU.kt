package com.company.client.lang

import com.company.collection.ValidatorCoordinates
import com.company.collection.ValidatorDiscipline
import com.company.collection.ValidatorLabWork
import com.company.superclasses.Difficulty
import java.util.*

class Bundle_ru_RU: ListResourceBundle() {

    override fun getContents(): Array<Array<Any>> = arrayOf(
        arrayOf("auth", "Авторизация"),
        arrayOf("reg", "Регистрация"),
        arrayOf("login", "Логин"),
        arrayOf("password", "Пароль"),
        arrayOf("enter", "Войти"),
        arrayOf("askReg", "Нет аккаунта?"),
        arrayOf("password2", "Повторите пароль"),
        arrayOf("register", "Зарегестрироваться"),
        arrayOf("askLog", "есть аккаунт?"),
        arrayOf("errLP", "Пароль или логин неверный."),
        arrayOf("okLP", "Вы вошли в систему."),
        arrayOf("RC", "Регистрация заверешена"),
        arrayOf("LE", "Логин занят"),
        arrayOf("main", "Главное окно"),
        arrayOf("add", "Добавить"),
        arrayOf("remove", "Удалить"),
        arrayOf("user", "Пользователь"),
        arrayOf("rOK", "Успешно удалено"),
        arrayOf("rNO", "удалить невозможно"),
        arrayOf("noNumber", "не число"),
        arrayOf("askRemove", "введите id"),
        arrayOf("name", "имя"),
        arrayOf("creationDate", "дата создания"),
        arrayOf("minimalPoint", "минимальная точка"),
        arrayOf("maximalPoint","максимальная точка"),
        arrayOf("difficulty","сложность"),
        arrayOf("disciplineName", "названия дисциплины"),
        arrayOf("lectureHours","кол-во лекций"),
        arrayOf("practiceHours","кол-во часов практ"),
        arrayOf("selfStudyHours","колчисество без часов"),
        arrayOf("labsCount","колличество лаб"),
        arrayOf("owner", "владелец"),
        arrayOf("inputName", "введите имя"),
        arrayOf("notName", "имя пусто"),
        arrayOf("inputX", "введите координату X"),
        arrayOf("errX", "x за границами [${ValidatorCoordinates.CoordinatesValidatorConfig.minX}, ${ValidatorCoordinates.CoordinatesValidatorConfig.maxX}]"),
        arrayOf("inputY", "введите координату Y"),
        arrayOf("errY", "y за границами [${ValidatorCoordinates.CoordinatesValidatorConfig.minY}, ${ValidatorCoordinates.CoordinatesValidatorConfig.maxY}]"),
        arrayOf("inputMinimalPoint", "введите минимальную точку"),
        arrayOf("errMP", "минимальная точка за границами [${ValidatorLabWork.LabWorkValidateConfig.minMinimalPoint}, ${ValidatorLabWork.LabWorkValidateConfig.maxMinimalPoint}]"),
        arrayOf("inputMaximalPoint", "введите v точку"),
        arrayOf("errMAP", "максимальная точка за границами [${ValidatorLabWork.LabWorkValidateConfig.minMaximalPoint}, ${ValidatorLabWork.LabWorkValidateConfig.maxMaximalPoint}]"),
        arrayOf("inputDifficulty", "введите сложность ${Difficulty.values().contentToString()}"),
        arrayOf("errDifficulty", "не найдено"),
        arrayOf("inputDiscipline", "введите имя дисциплины"),
        arrayOf("inputLH", "введите кол-во часов лекций"),
        arrayOf("errLH","за диапозоном [${ValidatorDiscipline.DisciplineValidatorConfig.minLectureHours}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxLectureHours}"),
        arrayOf("inputPH", "введите кол-во практ часов"),
        arrayOf("errPH", "за диапозоном [${ValidatorDiscipline.DisciplineValidatorConfig.minPracticeHours}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxPracticeHours}"),
        arrayOf("inputSSH", "введите кол-во часов обучения"),
        arrayOf("errSSH", "часы за границей [${ValidatorDiscipline.DisciplineValidatorConfig.minSelfStudyHours}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxSelfStudyHours}"),
        arrayOf("inputCount", "кол-во лаб"),
        arrayOf("errIC", "кол-во за границей [${ValidatorDiscipline.DisciplineValidatorConfig.minLabsCount}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxLabsCount}"),
        arrayOf("addS", "успешно добавлено")



        )

}