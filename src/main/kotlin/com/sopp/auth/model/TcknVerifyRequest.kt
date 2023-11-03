package com.sopp.auth.model

import java.time.Year

data class TcknVerifyRequest(
    val tc: Long,
    val firstname: String,
    val surname: String,
    val birthYear: Int
)