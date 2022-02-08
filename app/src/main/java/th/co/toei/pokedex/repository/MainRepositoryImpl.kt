package th.co.toei.pokedex.repository

import retrofit2.Response
import th.co.toei.pokedex.models.PokemonModel
import th.co.toei.pokedex.network.EndpointInterface
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiService: EndpointInterface) :
    MainRepository {
    override suspend fun getPokeList(): Response<PokemonModel> = apiService.getPokemonList()
}
