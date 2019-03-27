package nl.hva.madlevel5.features.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import nl.hva.madlevel5.features.data.models.Game

@Database(entities = [Game::class], version = 1)
abstract class BacklogDatabase : RoomDatabase() {

    abstract fun backlogDao(): BacklogDao

}