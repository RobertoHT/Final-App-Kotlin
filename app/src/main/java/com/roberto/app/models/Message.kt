package com.roberto.app.models

import java.util.*

/**
 * Created by Roberto Hdez. on 24/06/18.
 */
data class Message(
        val authorId: String = "",
        val message: String = "",
        val profileImageUrl: String = "",
        val sentAt: Date = Date())