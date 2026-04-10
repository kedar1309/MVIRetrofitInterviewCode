package com.example.myapplication.data

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    var url = "https://jsonplaceholder.typicode.com/"

    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url).
        build()
    }

    @Provides
    fun provideAPiservice(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }


}

@Module
@InstallIn(SingletonComponent::class)
abstract class provideUserImple{

    @Binds
    abstract fun prodideImpl(userRepositoryImpl: UserRepositoryImpl): UserRepository
}