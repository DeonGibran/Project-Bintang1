package com.bintangg.finalproject.data

import io.github.jan.supabase.postgrest.from

object CategoryRepository {

    private val supabase get() = SupabaseClientProvider.client
    private const val TABLE = "categories"

    /**
     * Menyimpan data kategori baru ke tabel 'categories' di Supabase.
     * Fungsi menggunakan modifier 'suspend' karena proses I/O jaringan berjalan asinkron.
     */
    suspend fun addCategory(category: Category) {
        supabase.from(TABLE).insert(category)
    }

    /**
     * Mengambil seluruh data kategori yang tersimpan di Supabase.
     */
    suspend fun getCategories(): List<Category> {
        return supabase.from(TABLE)
            .select()
            .decodeList<Category>()
    }
}
