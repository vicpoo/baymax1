package com.vicpoo.baymax.features.data.remote

import com.vicpoo.baymax.features.data.dto.MedicineDto
import com.vicpoo.baymax.features.data.dto.MedicineRequest
import retrofit2.Response
import retrofit2.http.*

interface MedicineApi {
    @GET("medications")
    suspend fun getAllMedicines(): List<MedicineDto>

    @GET("medications/{id}")
    suspend fun getMedicineById(@Path("id") id: Int): MedicineDto

    @POST("medications")
    suspend fun createMedicine(@Body medicine: MedicineRequest): MedicineDto

    @PUT("medications/{id}")
    suspend fun updateMedicine(@Path("id") id: Int, @Body medicine: MedicineRequest): MedicineDto

    @DELETE("medications/{id}")
    suspend fun deleteMedicine(@Path("id") id: Int): Response<Void>

    @GET("medications/search")
    suspend fun searchMedicines(@Query("name") name: String): List<MedicineDto>
}