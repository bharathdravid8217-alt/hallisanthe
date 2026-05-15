package com.hallisanthe.data.local;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\t\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0004\u001a\u00020\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0006J\u0014\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\nH\'J\u001c\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u000b0\n2\u0006\u0010\r\u001a\u00020\u0005H\'J\u0016\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\bH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\u001c\u0010\u0011\u001a\u00020\u00032\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u000bH\u00a7@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"Lcom/hallisanthe/data/local/ProductDao;", "", "delete", "", "id", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getById", "Lcom/hallisanthe/data/local/ProductEntity;", "observeProducts", "Lkotlinx/coroutines/flow/Flow;", "", "observeProductsByArtisan", "artisanId", "upsert", "product", "(Lcom/hallisanthe/data/local/ProductEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertAll", "products", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao()
public abstract interface ProductDao {
    
    @androidx.room.Query(value = "SELECT * FROM products_table")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hallisanthe.data.local.ProductEntity>> observeProducts();
    
    @androidx.room.Query(value = "SELECT * FROM products_table WHERE artisanId = :artisanId")
    @org.jetbrains.annotations.NotNull()
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.hallisanthe.data.local.ProductEntity>> observeProductsByArtisan(@org.jetbrains.annotations.NotNull()
    java.lang.String artisanId);
    
    @androidx.room.Query(value = "SELECT * FROM products_table WHERE productId = :id LIMIT 1")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getById(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hallisanthe.data.local.ProductEntity> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsertAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hallisanthe.data.local.ProductEntity> products, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object upsert(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.data.local.ProductEntity product, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM products_table WHERE productId = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object delete(@org.jetbrains.annotations.NotNull()
    java.lang.String id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}