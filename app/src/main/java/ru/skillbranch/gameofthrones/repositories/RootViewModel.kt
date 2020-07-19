package ru.skillbranch.gameofthrones.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.skillbranch.gameofthrones.data.local.entities.CharacterFull
import ru.skillbranch.gameofthrones.data.local.entities.CharacterItem

class RootViewModel : ViewModel() {
    private val repository = RootRepository

    fun getHouseCharacter(houseName: String): LiveData<List<CharacterItem>> {
        val result = MutableLiveData<List<CharacterItem>>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(repository.getCharactersByHouseName(houseName))
        }
        return result
    }

    fun getFullCharacter(id: String): LiveData<CharacterFull> {
        val result = MutableLiveData<CharacterFull>()
        viewModelScope.launch(Dispatchers.IO) {
            result.postValue(repository.getFullCharacter(id))
        }
        return result
    }
}