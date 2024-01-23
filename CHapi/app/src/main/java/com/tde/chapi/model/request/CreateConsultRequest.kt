package com.tde.chapi.model.request

data class CreateConsultRequest (
    val hospital : String,
    val direction : String,
    val medical : String,
    val date : String,
    val hour : String,
    val activation : Boolean,
    val username : String
        )
