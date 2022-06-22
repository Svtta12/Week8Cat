package science.example.week5cat.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import science.example.week5cat.data.Cat
import science.example.week5cat.data.FavoriteCat
import science.example.week5cat.database.DataBase.Companion.CATS_TABLE
import science.example.week5cat.database.DataBase.Companion.FAVORITE_TABLE

@Dao
interface Dao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cats: List<Cat>)

    @Query("SELECT * FROM $CATS_TABLE")
    fun getCats(): List<Cat>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavorite(cat: FavoriteCat)

    @Query("SELECT * FROM $FAVORITE_TABLE")
    fun getFavorite(): List<FavoriteCat>

    @Query("SELECT COUNT(url) FROM $CATS_TABLE")
    fun getRowCount(): Int

    @Query("DELETE FROM $CATS_TABLE")
    fun deleteAll()
}