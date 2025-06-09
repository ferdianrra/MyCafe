package com.dicoding.mycafe.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

class SendResponse (
    @field:SerializedName("name")
    val name: String
)