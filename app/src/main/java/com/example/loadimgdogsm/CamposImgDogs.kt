package com.example.loadimgdogsm

import com.google.gson.annotations.SerializedName

data class CamposImgDogs(@SerializedName("status")var status: String,
                         @SerializedName("message") var imgsDos: List<String> )
