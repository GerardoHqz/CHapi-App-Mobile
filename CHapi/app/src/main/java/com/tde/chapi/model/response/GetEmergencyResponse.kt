package com.tde.chapi.model.response

data class GetEmergencyResponse(val emergencies : List<Emergency>)

class Emergency{
    val name: String
    val phone: String
    val email: String

    constructor(name : String, phone : String, email : String){
        this.name = name
        this.phone = phone
        this.email = email
    }
}
