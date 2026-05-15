package com.hallisanthe.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\fJ\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0010\u0010\u001a\u001a\u00020\u00142\b\b\u0002\u0010\u001b\u001a\u00020\fJ\u0006\u0010\u001c\u001a\u00020\u0014J4\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J<\u0010&\u001a\u00020\u00142\u0006\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\f2\u0006\u0010\"\u001a\u00020\f2\u0006\u0010\'\u001a\u00020\f2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$J$\u0010(\u001a\u00020\u00142\u0006\u0010\u0018\u001a\u00020\u00192\u0014\u0010)\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0004\u0012\u00020%0*R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0011\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0010\u00a8\u0006+"}, d2 = {"Lcom/hallisanthe/viewmodel/ProductViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/hallisanthe/data/repository/ProductRepository;", "context", "Landroid/content/Context;", "(Lcom/hallisanthe/data/repository/ProductRepository;Landroid/content/Context;)V", "_products", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/hallisanthe/data/model/Product;", "_uploadStatus", "", "products", "Lkotlinx/coroutines/flow/StateFlow;", "getProducts", "()Lkotlinx/coroutines/flow/StateFlow;", "uploadStatus", "getUploadStatus", "delete", "Lkotlinx/coroutines/Job;", "id", "getFileFromUri", "Ljava/io/File;", "uri", "Landroid/net/Uri;", "loadArtisanProducts", "artisanId", "loadProducts", "saveDemoProduct", "name", "price", "", "category", "description", "onDone", "Lkotlin/Function0;", "", "saveProduct", "imageUrl", "uploadImage", "onResult", "Lkotlin/Function1;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ProductViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hallisanthe.data.repository.ProductRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.hallisanthe.data.model.Product>> _products = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hallisanthe.data.model.Product>> products = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _uploadStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> uploadStatus = null;
    
    @javax.inject.Inject()
    public ProductViewModel(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.data.repository.ProductRepository repository, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hallisanthe.data.model.Product>> getProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getUploadStatus() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job loadProducts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job loadArtisanProducts(@org.jetbrains.annotations.NotNull()
    java.lang.String artisanId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job uploadImage(@org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onResult) {
        return null;
    }
    
    private final java.io.File getFileFromUri(android.net.Uri uri) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job saveProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String name, double price, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job saveDemoProduct(@org.jetbrains.annotations.NotNull()
    java.lang.String name, double price, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
}