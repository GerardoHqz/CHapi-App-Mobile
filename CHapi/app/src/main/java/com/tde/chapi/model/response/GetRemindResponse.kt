package com.tde.chapi.model.response

data class GetRemindResponse(val reminds: List<Remind>)

class Remind {
    val _id: String
    val name: String
    val many: Number
    val unit: String
    val type: String
    val date: String
    val hour: String
    val frecuently: String
    var activation: Boolean
    var username: String


    constructor (_id: String, name: String, many: Number, unit: String, type: String, date: String, hour: String, frecuently: String, activation: Boolean, username: String) {
        this._id = _id
        this.name = name
        this.many = many
        this.unit = unit
        this.type = type
        this.date = date
        this.hour = hour
        this.frecuently = frecuently
        this.activation = activation
        this.username = username
    }
}
