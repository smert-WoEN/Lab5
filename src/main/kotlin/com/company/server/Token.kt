package com.company.server

import org.apache.commons.codec.digest.DigestUtils

data class Token(val login: String, private val password: String, private val timeLong: Long): java.io.Serializable {
    val token = DigestUtils.sha512Hex(login+password+timeLong)
}