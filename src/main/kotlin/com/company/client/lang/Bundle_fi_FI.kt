package com.company.client.lang

import com.company.collection.ValidatorCoordinates
import com.company.collection.ValidatorDiscipline
import com.company.collection.ValidatorLabWork
import com.company.superclasses.Difficulty
import java.util.ListResourceBundle

class Bundle_fi_FI: ListResourceBundle() {

    override fun getContents(): Array<Array<Any>> = arrayOf(
        arrayOf("auth", "Valtuutus"),
        arrayOf("reg", "Rekisteröinti"),
        arrayOf("login", "Kirjaudu sisään"),
        arrayOf("password", "Salasana"),
        arrayOf("enter", "tulla sisään"),
        arrayOf("askReg", "Eikö sinulla ole tiliä?"),
        arrayOf("password2", "Toista salasana"),
        arrayOf("register", "Rekisteröidy"),
        arrayOf("askLog", "on tili?"),
        arrayOf("errLP", "Salasana tai kirjautumistunnus on virheellinen."),
        arrayOf("okLP", "Olet kirjautunut sisään."),
        arrayOf("RC", "Rekisteröinti valmis"),
        arrayOf("LE", "Kirjautuminen varattu"),
        arrayOf("main", "Pääikkuna"),
        arrayOf("add", "lisää"),
        arrayOf("remove", "Remove"),
        arrayOf("user", "käyttäjä"),
        arrayOf("rOK", "Poistettu onnistuneesti"),
        arrayOf("rNO", "ei voi poistaa"),
        arrayOf("noNumber", "ei numero"),
        arrayOf("askRemove", "enter id"),
        arrayOf("name", "nimi"),
        arrayOf("creationDate", "creation date"),
        arrayOf("minimalPoint", "minim point"),
        arrayOf("maximalPoint","maksimipiste"),
        arrayOf("difficulty","vaikeus"),
        arrayOf("disciplineName", "tieteenalojen nimet"),
        arrayOf("lectureHours","luentojen lukumäärä"),
        arrayOf("practiceHours","practice hours"),
        arrayOf("selfStudyHours","luku ilman tunteja"),
        arrayOf("labsCount","laboratorioiden lukumäärä"),
        arrayOf("owner", "omistaja"),
        arrayOf("inputName", "kirjoita nimi"),
        arrayOf("notName", "nimi on tyhjä"),
        arrayOf("inputX", "enter X-koordinaatti"),
        arrayOf("errX", "x on ulkopuolella [${ValidatorCoordinates.CoordinatesValidatorConfig.minX}, ${ValidatorCoordinates.CoordinatesValidatorConfig.maxX}]"),
        arrayOf("inputY", "kirjoita Y-koordinaatti"),
        arrayOf("errY", "y yli [${ValidatorCoordinates.CoordinatesValidatorConfig.minY}, ${ValidatorCoordinates.CoordinatesValidatorConfig.maxY}]"),
        arrayOf("inputMinimalPoint", "kirjoita minimipiste"),
        arrayOf("errMP", "minimipiste yli [${ValidatorLabWork.LabWorkValidateConfig.minMinimalPoint}, ${ValidatorLabWork.LabWorkValidateConfig.maxMinimalPoint}]"),
        arrayOf("inputMaximalPoint", "input v point"),
        arrayOf("errMAP", "maksimipiste yli [${ValidatorLabWork.LabWorkValidateConfig.minMaximalPoint}, ${ValidatorLabWork.LabWorkValidateConfig.maxMaximalPoint}]"),
        arrayOf("inputDifficulty", "syötteen monimutkaisuus ${Difficulty.values().contentToString()}"),
        arrayOf("errDifficulty", "ei löydy"),
        arrayOf("inputDiscipline", "kirjoita tieteenalan nimi"),
        arrayOf("inputLH", "kirjoita luentotuntien määrä"),
        arrayOf("errLH","alueen ulkopuolella [${ValidatorDiscipline.DisciplineValidatorConfig.minLectureHours}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxLectureHours}"),
        arrayOf("inputPH", "anna harjoitustuntien määrä"),
        arrayOf("errPH", "beyond [${ValidatorDiscipline.DisciplineValidatorConfig.minPracticeHours}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxPracticeHours}"),
        arrayOf("inputSSH", "anna harjoitustuntien määrä"),
        arrayOf("errSSH", "tunnit ulkopuolella [${ValidatorDiscipline.DisciplineValidatorConfig.minSelfStudyHours}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxSelfStudyHours}"),
        arrayOf("inputCount", "laboratorioiden lukumäärä"),
        arrayOf("errIC", "count ulkopuolella [${ValidatorDiscipline.DisciplineValidatorConfig.minLabsCount}, ${ValidatorDiscipline.DisciplineValidatorConfig.maxLabsCount}"),
        arrayOf("addS", "lisätty onnistuneesti")
    )
}
