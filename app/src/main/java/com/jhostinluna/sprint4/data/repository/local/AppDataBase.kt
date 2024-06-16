package com.jhostinluna.sprint4.data.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.jhostinluna.sprint4.data.repository.local.entities.PersonEntity

@Database(entities = [PersonEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDataBase: RoomDatabase() {
    abstract fun personDAO(): PersonDAO
}