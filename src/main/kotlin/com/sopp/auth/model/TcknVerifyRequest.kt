package com.sopp.auth.model

data class TcknVerifyRequest(
    val tc: Long,
    val firstname: String,
    val surname: String,
    val birthYear: Int
){
    constructor(verifyUserModel: VerifyUserModel) : this(verifyUserModel.tc, verifyUserModel.firstname, verifyUserModel.surname, verifyUserModel.birthYear)
}