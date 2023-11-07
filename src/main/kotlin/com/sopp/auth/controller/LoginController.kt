package com.sopp.auth.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestHeader

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = ["http://localhost:3000"])
class LoginController(
) {

    @PostMapping
    suspend fun login(@RequestHeader("Authorization") token: String): Boolean {
        println(token)
        return true
    }

    @PostMapping("/merchant")
    suspend fun merchantLogin(): Boolean {
        return true
    }

}