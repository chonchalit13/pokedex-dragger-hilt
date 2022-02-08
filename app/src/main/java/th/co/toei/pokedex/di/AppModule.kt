package th.co.toei.pokedex.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import th.co.toei.pokedex.network.ApiService
import th.co.toei.pokedex.network.EndpointInterface
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideApiService(): EndpointInterface =
        ApiService().getEndpointInterface(EndpointInterface::class.java)
}