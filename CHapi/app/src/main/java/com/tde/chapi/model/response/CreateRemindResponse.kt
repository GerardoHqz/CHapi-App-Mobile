package com.tde.chapi.model.response

data class CreateRemindResponse(
    var name: String,
    var many: Number,
    var unit: String,
    var type: String,
    var date: String,
    var hour: String,
    var frecuently: String,
    var activation: Boolean,
    var username: String,
    var _id: String
)
