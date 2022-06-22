package science.example.week5cat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import science.example.week5cat.data.Cat
import science.example.week5cat.data.FavoriteCat

@Database(entities = [Cat::class, FavoriteCat::class], version = 1, exportSchema = true)
abstract class DataBase: RoomDatabase() {
    abstract fun getDao(): Dao


    companion object {

        @Volatile
        private var INSTANCE: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = buildDatabase(context)
                }
            }
            return INSTANCE!!
        }


        private fun buildDatabase(context: Context): DataBase {
            return Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                BASE_NAME
            )
                .build()
        }


        const val BASE_NAME = "database"
        const val CATS_TABLE = "cats_table"
        const val FAVORITE_TABLE = "favorite_table"
    }
}