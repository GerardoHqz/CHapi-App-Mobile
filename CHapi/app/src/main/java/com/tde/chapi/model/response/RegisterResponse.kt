package com.tde.chapi.model.response

data class RegisterResponse (
    var username: String,
    var email: String,
    var phone: String,
    var role: String,
    var code: String,
)