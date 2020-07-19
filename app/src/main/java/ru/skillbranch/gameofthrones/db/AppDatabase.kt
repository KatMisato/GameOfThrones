package ru.skillbranch.gameofthrones.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.skillbranch.gameofthrones.data.local.entities.Character
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem
import ru.skillbranch.gameofthrones.data.local.entities.House

@Database(
    entities = [Character::class, House::class],
    views = [CharacterFull::class, CharacterItem::class],
    version = 1
)
@TypeConverters(StringListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun houseDao(): HouseDao

    suspend fun cleanDB() {
        characterDao().cleanTable()
        houseDao().cleanTable()
    }

    suspend fun isEmpty(): Boolean {
        return characterDao().getCount() == 0 && houseDao().getCount() == 0
    }
}