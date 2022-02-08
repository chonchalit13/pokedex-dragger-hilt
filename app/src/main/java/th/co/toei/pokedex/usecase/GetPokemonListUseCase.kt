package th.co.toei.pokedex.usecase

import th.co.toei.pokedex.base.BaseAsyncUseCase
import th.co.toei.pokedex.base.Result
import th.co.toei.pokedex.models.PokemonModel
import th.co.toei.pokedex.repository.MainRepository
import javax.inject.Inject

class GetPokemonListUseCase @Inject constructor(private val mainRepository: MainRepository) :
    BaseAsyncUseCase<Unit, PokemonModel>() {
    override suspend fun execute(parameter: Unit): Result<PokemonModel> {
        return try {
            val response = mainRepository.getPokeList()
            if (isResponseSuccess(response)) {
                Result.Success(response.body() ?: PokemonModel())
            } else {
                Result.Error(response.message())
            }
        } catch (e: Exception) {
            Result.Error(e.message.toString())
        }
    }
}
