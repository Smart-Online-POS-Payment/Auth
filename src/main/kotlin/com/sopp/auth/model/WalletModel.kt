package com.sopp.auth.model

import java.math.BigDecimal
import java.util.*

data class WalletModel(
    val id: UUID = UUID.randomUUID(),
    val customerId: String,
    var balance: BigDecimal,
)
