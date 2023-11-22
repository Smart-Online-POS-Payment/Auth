package com.sopp.auth.entity

import com.sopp.auth.model.VerifyUserModel
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "verified_user")
data class VerifiedUserEntity(
    @Id
    val userId: String,
    val tc: Long,
    val firstname: String,
    val surname: String,
    val email: String,
    val phoneNumber: String,
    val openAddress: String,
    val city: String,
    val country: String
) {
    constructor(verifyUserModel: VerifyUserModel) : this(verifyUserModel.userId, verifyUserModel.tc, verifyUserModel.firstname, verifyUserModel.surname, verifyUserModel.email, verifyUserModel.phoneNumber, verifyUserModel.openAddress, verifyUserModel.city, verifyUserModel.country)
    constructor() : this("1234", 12L, "", "", "", "", "", "", "")
}
