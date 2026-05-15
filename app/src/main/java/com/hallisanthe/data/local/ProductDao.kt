package com.hallisanthe.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query("SELECT * FROM products_table")
    fun observeProducts(): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table WHERE artisanId = :artisanId")
    fun observeProductsByArtisan(artisanId: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM products_table WHERE productId = :id LIMIT 1")
    suspend fun getById(id: String): ProductEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(product: ProductEntity)

    @Query("DELETE FROM products_table WHERE productId = :id")
    suspend fun delete(id: String)
}
