package com.roberto.app.models

import java.util.*

/**
 * Created by Roberto Hdez. on 24/06/18.
 */
data class Message(
        val authorId: String = "",
        val message: String = "",
        val profileImageURL: String = "",
        val sentAt: Date = Date())