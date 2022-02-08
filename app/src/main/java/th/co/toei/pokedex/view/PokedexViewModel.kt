package th.co.toei.pokedex.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import th.co.toei.pokedex.base.BaseViewModel
import th.co.toei.pokedex.base.Result
import th.co.toei.pokedex.models.Card
import th.co.toei.pokedex.usecase.GetPokemonListUseCase
import javax.inject.Inject

@HiltViewModel
class PokedexViewModel @Inject constructor(private val getPokemonListUseCase: GetPokemonListUseCase) :
    BaseViewModel() {

    val pokemonListLiveData: MutableLiveData<MutableList<Card>> = MutableLiveData()

    fun getPokemonList() {
        viewModelScope.launch {
            loadingLiveData.postValue(true)
            val result = withContext(Dispatchers.IO) {
                getPokemonListUseCase.execute(Unit)
            }

            when (result) {
                is Result.Success -> {
                    loadingLiveData.postValue(false)
                    pokemonListLiveData.postValue(result.data.cards)
                }
                is Result.Error -> {
                    loadingLiveData.postValue(false)
                    errorMessageLiveData.postValue(result.exception)
                }
            }
        }
    }
}