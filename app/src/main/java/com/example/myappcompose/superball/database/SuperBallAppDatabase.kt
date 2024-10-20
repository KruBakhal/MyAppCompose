package com.example.myappcompose.superball.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.practicesession.superballgame.database.SuperBallDao
import com.example.practicesession.superballgame.model.SuperBallConverters
import com.example.practicesession.superballgame.model.SuperBallModel

@Database(entities = [SuperBallModel::class], version = 1)
@TypeConverters(SuperBallConverters::class)
abstract class SuperBallAppDatabase : RoomDatabase() {
    abstract fun getArticleDBDao(): SuperBallDao
}