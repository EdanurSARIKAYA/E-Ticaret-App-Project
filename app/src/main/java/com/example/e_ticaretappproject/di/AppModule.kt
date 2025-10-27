package com.example.e_ticaretappproject.di

import com.example.e_ticaretappproject.data.datasources.ProductsDatasource
import com.example.e_ticaretappproject.data.repository.ProductsRepository
import com.example.e_ticaretappproject.retrofit.ProductsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideProductsRepository(productsDatasource: ProductsDatasource) : ProductsRepository {
        return ProductsRepository(productsDatasource)
    }

    @Provides
    @Singleton
    fun provideProductsDatasource(productsDao: ProductsDao) : ProductsDatasource {
        return ProductsDatasource(productsDao)
    }

    @Provides
    @Singleton
    fun provideProductsDao(retrofit: Retrofit) : ProductsDao {
        return retrofit.create(ProductsDao::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://kasimadalan.pe.hu/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}