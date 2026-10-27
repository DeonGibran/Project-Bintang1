package com.bintangg.finalproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Task(
    val id: Long? = null,
    val nama_task: String,
    val category_id: String? = null,
    val user_id : String?=null,
    val status: String, // format "yyyy-MM-dd"
    val start_date: String? = null, // format "HH:mm"
    val due_date: String? = null, // "Tinggi" | "Sedang" | "Rendah"
    val priority: String? = null,
    val completed_at: String? = null,
    @SerialName("created_at") val createdAt: String? = null,
    @SerialName("updated_at") val updatedAt: String? = null,
)
