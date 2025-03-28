package com.rsstudio.zshoe.di

import com.rsstudio.zshoe.data.repositories.ProductDetailRepository
import com.rsstudio.zshoe.domain.GetProductDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped


@Module
@InstallIn(ActivityRetainedComponent::class)
class UseCaseModule {

    @Provides
    @ActivityRetainedScoped
    fun provideGetProductDetailUseCase(productDetailRepository: ProductDetailRepository) =
        GetProductDetailUseCase(productDetailRepository)
}