package com.hallisanthe.data.repository;

@javax.inject.Singleton()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0011\b\u0007\u0012\b\b\u0001\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0010\u0010\f\u001a\u0004\u0018\u00010\rH\u0086@\u00a2\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0016\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0086@\u00a2\u0006\u0002\u0010\u0014J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\rJ\u0006\u0010\u0017\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u00020\u00068BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n \u000b*\u0004\u0018\u00010\n0\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0018"}, d2 = {"Lcom/hallisanthe/data/repository/AuthRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "firebaseReady", "", "getFirebaseReady", "()Z", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "currentUserType", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "logout", "", "saveProfile", "user", "Lcom/hallisanthe/data/model/User;", "(Lcom/hallisanthe/data/model/User;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveSelectedRole", "role", "selectedRole", "app_debug"})
public final class AuthRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    private final android.content.SharedPreferences prefs = null;
    
    @javax.inject.Inject()
    public AuthRepository(@dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final boolean getFirebaseReady() {
        return false;
    }
    
    public final void saveSelectedRole(@org.jetbrains.annotations.NotNull()
    java.lang.String role) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String selectedRole() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object currentUserType(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveProfile(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.data.model.User user, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    public final void logout() {
    }
}