package com.example.loadimgdogsm

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface ImgDogsInterface {
    @GET
    suspend fun getDogCamposImg(@Url url:String): Response<CamposImgDogs>
}