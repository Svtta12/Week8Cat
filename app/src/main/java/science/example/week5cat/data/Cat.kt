package science.example.week5cat.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import science.example.week5cat.database.DataBase

@Entity(tableName = DataBase.CATS_TABLE)
@Parcelize
@Serializable
data class Cat(
    @PrimaryKey val id: String = "",
    val url: String = ""
) : Parcelable

@Entity(tableName = DataBase.FAVORITE_TABLE)
@kotlinx.parcelize.Parcelize
@Serializable
data class FavoriteCat(
    @PrimaryKey
    val id: String = "",
    val url: String = ""
) : Parcelable
