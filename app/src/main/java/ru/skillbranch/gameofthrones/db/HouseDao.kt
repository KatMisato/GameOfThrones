package ru.skillbranch.gameofthrones.db

import androidx.room.*
import ru.skillbranch.gameofthrones.data.local.entities.House

@Dao
abstract class HouseDao {
    @Transaction
    open suspend fun insertHouses(houses: List<House>) {
        houses.forEach { insertHouse (it) }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertHouse(house: House): Long

    @Query("DELETE FROM house")
    abstract suspend fun cleanTable()

    @Query("SELECT words FROM house WHERE id = :id")
    abstract suspend fun getHouseWords(id: String): String

    @Query("SELECT COUNT(id) FROM house WHERE id > 0")
    abstract suspend fun getCount(): Int
}
