package com.hallisanthe.data.repository;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class ReviewRepository_Factory implements Factory<ReviewRepository> {
  private final Provider<Context> contextProvider;

  public ReviewRepository_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public ReviewRepository get() {
    return newInstance(contextProvider.get());
  }

  public static ReviewRepository_Factory create(Provider<Context> contextProvider) {
    return new ReviewRepository_Factory(contextProvider);
  }

  public static ReviewRepository newInstance(Context context) {
    return new ReviewRepository(context);
  }
}
