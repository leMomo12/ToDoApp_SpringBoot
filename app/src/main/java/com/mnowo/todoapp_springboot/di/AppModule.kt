package com.mnowo.todoapp_springboot.di

import com.mnowo.todoapp_springboot.util.Constants.BASE_URL
import com.mnowo.todoapp_springboot.data.remote.ToDoApi
import com.mnowo.todoapp_springboot.data.repository.ToDoRepositoryImpl
import com.mnowo.todoapp_springboot.domain.repository.ToDoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideToDoApi(retrofit: Retrofit): ToDoApi = retrofit.create()

    @Provides
    @Singleton
    fun provideToDoRepository(api: ToDoApi): ToDoRepository {
        return ToDoRepositoryImpl(toDoApi = api)
    }
}