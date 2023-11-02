package com.sopp.auth.controller

import com.sopp.auth.model.LoginModel
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = ["http://localhost:3000"])
class LoginController {

    @PostMapping
    suspend fun login(@RequestBody loginModel: LoginModel): Boolean {
        return true
    }

    @PostMapping("/merchant")
    suspend fun merchantLogin(loginModel: LoginModel): Boolean {
        return true
    }

}