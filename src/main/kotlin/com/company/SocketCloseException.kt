package com.company

import java.io.IOException

class SocketCloseException: IOException {

    constructor(): super()

    constructor(message: String?): super(message)


    constructor(errorMessage: String?, err: Throwable?): super(errorMessage, err)

    constructor(cause: Throwable?): super(cause)

}