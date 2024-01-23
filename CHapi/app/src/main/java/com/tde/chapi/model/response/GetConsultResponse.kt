package com.tde.chapi.model.response

data class GetConsultResponse (val consultations : List<Consultation>)

class Consultation{
    val _id : String
    val hospital: String
    val direction: String
    val medical: String
    val date : String
    val hour : String
    val activation: Boolean
    val username: String

    constructor(_id: String, hospital: String, direction: String, medical: String, date: String, hour: String, activation: Boolean, username: String) {
        this._id = _id
        this.hospital = hospital
        this.direction = direction
        this.medical = medical
        this.date = date
        this.hour = hour
        this.activation = activation
        this.username = username
    }
}
