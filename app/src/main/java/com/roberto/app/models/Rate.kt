package com.roberto.app.models

import java.util.*

/**
 * Created by Roberto Hdez. on 25/06/18.
 */
data class Rate(
        val userId: String = "",
        val text: String = "",
        val rate: Float = 0f,
        val createdAt: Date = Date(),
        val profileImageURL: String = ""
)