package com.hlink.mvvmroomretorfit.roomtest.db

import androidx.room.Database
import androidx.room.Entity
import androidx.room.RoomDatabase
import com.hlink.mvvmroomretorfit.roomtest.model.Contact
import com.hlink.mvvmroomretorfit.roomtest.model.ContactDAO

@Database(entities = [Contact::class], version = 1)
abstract class MyDatabase : RoomDatabase() {

    abstract fun contactDao(): ContactDAO
}