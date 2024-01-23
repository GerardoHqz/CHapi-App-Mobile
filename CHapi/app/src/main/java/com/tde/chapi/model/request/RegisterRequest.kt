package com.tde.chapi.model.request

data class RegisterRequest (
    var username: String,
    var email: String,
    var password: String,
    var phone: String,
    var role: String
)