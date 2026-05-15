package com.hallisanthe.viewmodel;

import android.content.Context;
import com.hallisanthe.data.repository.ProductRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ProductViewModel_Factory implements Factory<ProductViewModel> {
  private final Provider<ProductRepository> repositoryProvider;

  private final Provider<Context> contextProvider;

  public ProductViewModel_Factory(Provider<ProductRepository> repositoryProvider,
      Provider<Context> contextProvider) {
    this.repositoryProvider = repositoryProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public ProductViewModel get() {
    return newInstance(repositoryProvider.get(), contextProvider.get());
  }

  public static ProductViewModel_Factory create(Provider<ProductRepository> repositoryProvider,
      Provider<Context> contextProvider) {
    return new ProductViewModel_Factory(repositoryProvider, contextProvider);
  }

  public static ProductViewModel newInstance(ProductRepository repository, Context context) {
    return new ProductViewModel(repository, context);
  }
}
