package th.co.toei.pokedex.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import th.co.toei.pokedex.network.ApiService
import th.co.toei.pokedex.network.EndpointInterface
import th.co.toei.pokedex.repository.MainRepository
import th.co.toei.pokedex.repository.MainRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideMainRepository(mainRepositoryImpl: MainRepositoryImpl): MainRepository

    companion object{
        @Provides
        @Singleton
        fun provideApiService(): EndpointInterface =
            ApiService().getEndpointInterface(EndpointInterface::class.java)
    }
}