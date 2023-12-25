package com.sopp.auth.controller

import com.sopp.auth.entity.VerifiedUserEntity
import com.sopp.auth.model.VerifyUserModel
import com.sopp.auth.service.VerifyService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/verify")
@CrossOrigin(origins = ["http://localhost:3000"])
class VerifyController(
    private val verifyService: VerifyService,
) {
    @PostMapping
    suspend fun verifyCustomer(
        @RequestBody body: VerifyUserModel,
    ): Boolean {
        return verifyService.verifyCustomer(body)
    }

    @GetMapping("/customer/{customerId}/is-verified")
    suspend fun isCustomerVerified(
        @PathVariable customerId: String,
    ): Boolean {
        return verifyService.isCustomerVerified(customerId)
    }

    @GetMapping("/customer/{customerId}")
    suspend fun getCustomerVerificationInfo(
        @PathVariable customerId: String,
    ): VerifiedUserEntity {
        return verifyService.getCustomerVerificationInfo(customerId)
    }
}
