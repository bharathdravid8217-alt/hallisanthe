package com.hallisanthe.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile ProductDao _productDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `products_table` (`productId` TEXT NOT NULL, `artisanId` TEXT NOT NULL, `artisanName` TEXT NOT NULL, `artisanPhone` TEXT NOT NULL, `artisanWhatsapp` TEXT NOT NULL, `artisanLocation` TEXT NOT NULL, `productName` TEXT NOT NULL, `price` REAL NOT NULL, `category` TEXT NOT NULL, `subCategory` TEXT NOT NULL, `description` TEXT NOT NULL, `imageUrl` TEXT NOT NULL, `isAvailable` INTEGER NOT NULL, `stockCount` INTEGER NOT NULL, `averageRating` REAL NOT NULL, `totalReviews` INTEGER NOT NULL, PRIMARY KEY(`productId`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'd7d49ee92ea65f0c3c38bb99050d339d')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `products_table`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsProductsTable = new HashMap<String, TableInfo.Column>(16);
        _columnsProductsTable.put("productId", new TableInfo.Column("productId", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("artisanId", new TableInfo.Column("artisanId", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("artisanName", new TableInfo.Column("artisanName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("artisanPhone", new TableInfo.Column("artisanPhone", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("artisanWhatsapp", new TableInfo.Column("artisanWhatsapp", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("artisanLocation", new TableInfo.Column("artisanLocation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("productName", new TableInfo.Column("productName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("price", new TableInfo.Column("price", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("category", new TableInfo.Column("category", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("subCategory", new TableInfo.Column("subCategory", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("description", new TableInfo.Column("description", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("imageUrl", new TableInfo.Column("imageUrl", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("isAvailable", new TableInfo.Column("isAvailable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("stockCount", new TableInfo.Column("stockCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("averageRating", new TableInfo.Column("averageRating", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsProductsTable.put("totalReviews", new TableInfo.Column("totalReviews", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysProductsTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesProductsTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoProductsTable = new TableInfo("products_table", _columnsProductsTable, _foreignKeysProductsTable, _indicesProductsTable);
        final TableInfo _existingProductsTable = TableInfo.read(db, "products_table");
        if (!_infoProductsTable.equals(_existingProductsTable)) {
          return new RoomOpenHelper.ValidationResult(false, "products_table(com.hallisanthe.data.local.ProductEntity).\n"
                  + " Expected:\n" + _infoProductsTable + "\n"
                  + " Found:\n" + _existingProductsTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "d7d49ee92ea65f0c3c38bb99050d339d", "dc796a59905ca9e83a3dee2afdbd0b67");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "products_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `products_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(ProductDao.class, ProductDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public ProductDao productDao() {
    if (_productDao != null) {
      return _productDao;
    } else {
      synchronized(this) {
        if(_productDao == null) {
          _productDao = new ProductDao_Impl(this);
        }
        return _productDao;
      }
    }
  }
}
