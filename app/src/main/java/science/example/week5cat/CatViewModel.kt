package science.example.week5cat

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import science.example.week5cat.data.Cat
import science.example.week5cat.data.CatService
import science.example.week5cat.data.FavoriteCat

@SuppressLint("StaticFieldLeak")
class CatViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private var currentCat = Cat()
    private val repository = CatService(context)
    private var id: Int = -1
    private var end = 0



    suspend fun getCat(): Cat {
        id++
        if (end == 0) {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                onCreate()
            }
            end = 1
        }
        if (id == 14) {
            withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                repository.deleteAll()
                end = 0
                onCreate()
                end = 1
                id = 0
            }
        }
        withContext(viewModelScope.coroutineContext) {
            currentCat = repository.getCat()[id]
        }

        return currentCat
    }

    fun Like() {
        CoroutineScope(Dispatchers.IO).launch {
            repository.getFavorite(currentCat)
        }
    }

    suspend fun getFavoriteCats(): List<FavoriteCat> {
        return repository.getCatToFavorite()
    }

    private suspend fun onCreate() {
        repository.getCat()
        repository.getDataBase()
    }
}