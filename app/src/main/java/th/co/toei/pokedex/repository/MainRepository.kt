package th.co.toei.pokedex.repository

import retrofit2.Response
import th.co.toei.pokedex.models.PokemonModel

interface MainRepository {
    suspend fun getPokeList() : Response<PokemonModel>
}
