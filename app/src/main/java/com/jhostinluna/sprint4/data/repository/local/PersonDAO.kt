package com.jhostinluna.sprint4.data.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jhostinluna.sprint4.data.repository.local.entities.PersonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PersonDAO {

    @Query("SELECT * FROM persons")
    fun getAll(): Flow<List<PersonEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg persons: PersonEntity)

    @Delete
    fun delete(person: PersonEntity)
    @Query("SELECT * FROM persons WHERE uid = :id")
    fun getById(id: Int): Flow<PersonEntity>

}