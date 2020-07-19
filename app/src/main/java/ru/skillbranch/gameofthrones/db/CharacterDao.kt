package ru.skillbranch.gameofthrones.db

import androidx.room.*
import ru.skillbranch.gameofthrones.data.local.entities.Character
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

@Dao
abstract class CharacterDao {
    @Transaction
    open suspend fun insertCharacters(characters: List<Character>) {
        characters.forEach {insertCharacters(it) }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertCharacters(characters: Character): Long

    @Query("DELETE FROM character")
    abstract suspend fun cleanTable()

    @Transaction
    @Query("SELECT * FROM CharacterItem WHERE house = :house_id")
    abstract suspend fun getCharactersByHouseName(house_id: String): List<CharacterItem>

    @Query("SELECT * FROM character WHERE id = :id")
    abstract suspend fun getCharacterById(id: String): Character

    @Transaction
    @Query("SELECT * FROM CharacterFull WHERE id = :id")
    abstract suspend fun getFullCharacterInfo(id: String): CharacterFull

    @Query("SELECT COUNT(id) FROM character WHERE id > 0")
    abstract suspend fun getCount(): Int
}