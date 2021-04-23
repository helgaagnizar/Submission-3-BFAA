package com.example.submission1fundamental.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.submission1fundamental.model.User
import com.example.submission1fundamental.db.FavoriteDb

import com.example.submission1fundamental.repo.FavoriteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private val repo: FavoriteRepository
    val readAllData: LiveData<List<User>>
    val favoriteUser= MutableLiveData<User>()

    init {
        val favoriteDAO = FavoriteDb.getDb(application).favoriteDao()
        repo = FavoriteRepository(favoriteDAO)
        readAllData = repo.readAll
    }

    fun tambahFavorite(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.tambahFavorite(user)
        }
    }

    fun updateFavorite(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateFavorite(user)
        }
    }

    fun hapusFavorite(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.hapusFavorite(user)
        }

    }

    fun hapusSemua() {
        viewModelScope.launch(Dispatchers.IO) {
            repo.hapusSemua()
        }
    }

    fun checkById(username:String){
        viewModelScope.launch (Dispatchers.IO) {
            favoriteUser.postValue(repo.checkById(username))
        }
    }
}
