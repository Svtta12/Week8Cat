package science.example.week5cat.data

import android.content.Context
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import science.example.week5cat.database.DataBase

interface ApiInterface {

    suspend fun getCat(): List<Cat>
    suspend fun getCatDataBase(): List<Cat>
    suspend fun getDataBase()
    suspend fun getCatToFavorite(): List<FavoriteCat>
    suspend fun getFavorite(cat: Cat)
    suspend fun getCreated(): Boolean
    suspend fun deleteAll()
}


class CatService(context: Context) : ApiInterface {

    companion object {
        const val BASE_URL = "https://api.thecatapi.com/v1/images/search?limit=15"
    }

    private val client = HttpClient(CIO)
    private val json = Json { ignoreUnknownKeys = true }
    private var result: MutableList<Cat> = mutableListOf()
    private val database by lazy { DataBase.getDatabase(context).getDao() }

    override suspend fun getCat(): List<Cat> = withContext(Dispatchers.IO) {
        val response: HttpResponse = client.get(BASE_URL)

        result = json.decodeFromString(response.body())
        result
    }

    override suspend fun getCatDataBase(): List<Cat> = withContext(Dispatchers.IO) {
        database.getCats()
    }

    override suspend fun getDataBase() = withContext(Dispatchers.IO) {
        database.insertAll(result)
    }

    override suspend fun getCatToFavorite(): List<FavoriteCat> = withContext(Dispatchers.IO) {
        database.getFavorite()
    }

    override suspend fun getFavorite(cat: Cat) {
        database.addFavorite(FavoriteCat(id = cat.id, url = cat.url))
    }

    override suspend fun getCreated(): Boolean = database.getRowCount() != 0

    override suspend fun deleteAll() = database.deleteAll()


}