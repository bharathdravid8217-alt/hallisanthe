package com.hallisanthe.ui.customer;

import com.hallisanthe.data.repository.ProductRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@QualifierMetadata
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
public final class ProductDetailActivity_MembersInjector implements MembersInjector<ProductDetailActivity> {
  private final Provider<ProductRepository> repositoryProvider;

  public ProductDetailActivity_MembersInjector(Provider<ProductRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public static MembersInjector<ProductDetailActivity> create(
      Provider<ProductRepository> repositoryProvider) {
    return new ProductDetailActivity_MembersInjector(repositoryProvider);
  }

  @Override
  public void injectMembers(ProductDetailActivity instance) {
    injectRepository(instance, repositoryProvider.get());
  }

  @InjectedFieldSignature("com.hallisanthe.ui.customer.ProductDetailActivity.repository")
  public static void injectRepository(ProductDetailActivity instance,
      ProductRepository repository) {
    instance.repository = repository;
  }
}
