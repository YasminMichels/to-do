package br.edu.satc.todolistcompose

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Update

@Entity(tableName = "task_data")
data class TaskData (
    val title: String,
    val description: String,
    val complete: Boolean
)

@Dao
interface TaskDao {
    @Query("SELECT * FROM task_data")
    fun getAll(): List<TaskData>

    @Query("SELECT * FROM task_data WHERE title LIKE :title AND " +
            "description LIKE :description LIMIT 1")
    fun findByName(title: String, description: String): TaskData

    @Update
    fun updateAll(vararg tasks: TaskData)

    @Insert
    fun insertAll(vararg tasks: TaskData)

    @Delete
    fun delete(task: TaskData)
}

@Database(entities = [TaskData::class], version = 1)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
}