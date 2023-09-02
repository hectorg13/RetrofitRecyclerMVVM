package com.hectorg13.thesimpsons.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hectorg13.thesimpsons.core.RetrofitClient
import com.hectorg13.thesimpsons.models.Character
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private var _listCharacters = MutableLiveData<List<Character>>()
    val listCharacters: LiveData<List<Character>> get() = _listCharacters

    fun getAllCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getAllCharacters()
            withContext(Dispatchers.Main) {
                _listCharacters.value = response.body()
            }
        }
    }

    fun getCharacter(character: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.webService.getCharacter(character)
            withContext(Dispatchers.Main) {
                _listCharacters.value = response.body()
            }
        }
    }
}