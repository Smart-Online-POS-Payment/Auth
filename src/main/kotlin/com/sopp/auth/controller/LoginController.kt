package com.sopp.auth.controller

import com.sopp.auth.model.LoginModel
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/login")
class LoginController {

    @PostMapping
    suspend fun login(loginModel: LoginModel): Boolean {
        return true
    }

    @PostMapping("/merchant")
    suspend fun merchantLogin(loginModel: LoginModel): Boolean {
        return true
    }

}