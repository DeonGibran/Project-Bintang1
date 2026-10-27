package com.bintangg.finalproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val id: Long? = null,             // Bernilai null saat insert (dibuat otomatis oleh DB)
    val name: String,                 // Nama kategori (wajib)
    val description: String? = null,  // Deskripsi kategori (opsional)
    @SerialName("created_at")
    val createdAt: String? = null     // Timestamp pembuatan dari server
)