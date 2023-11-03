package com.sopp.auth.controller

import com.sopp.auth.model.TcknVerifyRequest
import com.sopp.auth.service.VerifyService
import okhttp3.Response
import org.springframework.web.bind.annotation.*
@RestController
@RequestMapping("/verify")
@CrossOrigin(origins = ["http://localhost:3000"])
class VerifyController(
    private val verifyService: VerifyService,
) {
    @PostMapping
    suspend fun verify(@RequestBody body: TcknVerifyRequest): Boolean? {
        return verifyService.sendSoapRequest(body)
    }
}