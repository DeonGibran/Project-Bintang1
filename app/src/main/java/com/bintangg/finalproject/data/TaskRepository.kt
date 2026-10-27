package com.bintangg.finalproject.data

import io.github.jan.supabase.postgrest.from

object TaskRepository {

    private val supabase get() = SupabaseClientProvider.client
    private const val TABLE = "tasks"

    suspend fun getTasks(): List<Task> {
        return supabase.from(TABLE)
            .select()
            .decodeList<Task>()
    }

    suspend fun addTask(task: Task) {
        supabase.from(TABLE).insert(task)
    }

    suspend fun setTaskDone(id: Long, isDone: Boolean) {
        supabase.from(TABLE).update(
            { set("is_done", isDone) }
        ) {
            filter { eq("id", id) }
        }
    }

    suspend fun deleteTask(id: Long) {
        supabase.from(TABLE).delete {
            filter { eq("id", id) }
        }
    }
}
