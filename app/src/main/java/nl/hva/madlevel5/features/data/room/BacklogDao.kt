package nl.hva.madlevel5.features.data.room

import androidx.room.*
import nl.hva.madlevel5.features.data.models.Game

@Dao
interface BacklogDao {

    @Query("DELETE FROM gameTable")
    fun clearTable()

    @Delete
    fun delete(game: Game)

    @Query("SELECT * FROM gameTable")
    fun getAll(): List<Game>

    @Insert
    fun insert(game: Game)

    @Update
    fun edit(game: Game)

}