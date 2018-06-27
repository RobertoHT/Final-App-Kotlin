package com.roberto.app.models

import java.util.*

/**
 * Created by Roberto Hdez. on 25/06/18.
 */
data class Rate(
        val text: String,
        val rate: Float,
        val createdAt: Date,
        val profileImageURL: String
)