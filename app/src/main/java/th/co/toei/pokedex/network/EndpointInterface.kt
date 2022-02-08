package th.co.toei.pokedex.network

import retrofit2.Response
import retrofit2.http.POST
import th.co.toei.pokedex.models.PokemonModel

interface EndpointInterface {
    @POST("v3/f9916417-f92e-478e-bfbc-c39e43f7c75b")
    suspend fun getPokemonList(): Response<PokemonModel>
}
