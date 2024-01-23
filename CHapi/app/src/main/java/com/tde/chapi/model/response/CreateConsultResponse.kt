package com.tde.chapi.model.response

data class CreateConsultResponse (
    val hospital : String,
    val direction : String,
    val medical : String,
    val date : String,
    val hour : String,
    val activation : Boolean,
    val username : String,
    val _id : String
)
