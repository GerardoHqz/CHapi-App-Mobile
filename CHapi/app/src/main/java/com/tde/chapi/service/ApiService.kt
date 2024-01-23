package com.tde.chapi.service

import com.tde.chapi.model.request.*
import com.tde.chapi.model.response.*
import retrofit2.Call
import retrofit2.http.*

public interface ApiService {
    //-------SERVICE AUTHENTICATION-------
    @POST("/login")
    fun loginUsser(@Body LoginResquest: LoginRequest): Call<LoginResponse>

    @POST("/register")
    fun register(@Body RegisterRequest: RegisterRequest): Call<RegisterResponse>

    @PUT("/forgot-password")
    fun forgotPassword(@Body ForgotPassRequest: ForgotPassRequest): Call<ForgotPassResponse>

    @POST("/verify-code")
    fun verifyCode(@Body VerifyCodeRequest: VerifyCodeRequest): Call<VerifyCodeResponse>

    @PUT("/update-password/{digitPass}")
    fun updatePassword(@Body UpdatePassRequest: UpdatePassRequest, @Path("digitPass") code: String): Call<UpdatePassResponse>

    //-------SERVICE HOME-------
    @POST("/consultations/today/")
    fun getConsultationsToday(@Header("Authorization") token: String,@Body ConsultationsTodayRequest: ConsultationsTodayRequest): Call<GetConsultResponse>

    @POST("/reminds/today/")
    fun getRemindsToday(@Header("Authorization") token: String,@Body RemindsTodayRequest: RemindsTodayRequest): Call<GetRemindResponse>

    //-------SERVICE REMINDS-------
    @GET("/reminds/")
    fun getReminds(@Header("Authorization") token: String): Call<GetRemindResponse>

    @POST("/reminds/")
    fun createRemind(@Header("Authorization") token: String, @Body CreateRemindRequest: CreateRemindRequest): Call<CreateRemindResponse>

    @DELETE("reminds/{id}")
    fun deleteRemind(@Header("Authorization") token: String, @Path("id") id: String): Call<DeleteRemindResponse>

    @PATCH("/reminds/{id}/toogle")
    fun toogeRemind(@Header("Authorization") token: String, @Path("id") id: String): Call<ToogleRemindResponse>

    //-------SERVICE CONSULTATIONS-------
    @GET("/consultations/{username}")
    fun getConsultations(@Header("Authorization") token: String, @Path("username") user: String): Call<GetConsultResponse>

    @POST("/consultations/")
    fun createConsultation(@Header("Authorization") token: String, @Body CreateConsultRequest: CreateConsultRequest): Call<CreateConsultResponse>

    @DELETE("consultations/{id}")
    fun deleteConsultation(@Header("Authorization") token: String, @Path("id") id: String): Call<DeleteConsultResponse>

    @PATCH("/consultations/{id}/toogle")
    fun toogleConsultation(@Header("Authorization") token: String, @Path("id") id: String): Call<ToogleConsultResponse>

    //-------SERVICE EMERGENCY-------
    @POST("/emergencies/")
    fun createEmergency(@Header("Authorization") token: String, @Body CreateEmergencyRequest: CreateEmergencyRequest): Call<CreateEmergencyResponse>

    @GET("/emergencies/{username}")
    fun getEmergencyphone(@Header("Authorization") token: String, @Path("username") user: String) : Call<GetEmergencyResponse>

}