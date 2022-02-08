package th.co.toei.pokedex.base

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PokemonRepositoryAsync

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PokemonRepositoryNonAsync
