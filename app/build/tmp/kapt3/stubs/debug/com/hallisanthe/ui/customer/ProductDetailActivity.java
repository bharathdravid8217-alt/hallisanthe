package com.hallisanthe.ui.customer;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u0007\u001a\u00020\b8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\f\u00a8\u0006\u0011"}, d2 = {"Lcom/hallisanthe/ui/customer/ProductDetailActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/hallisanthe/databinding/ActivityProductDetailBinding;", "productId", "", "repository", "Lcom/hallisanthe/data/repository/ProductRepository;", "getRepository", "()Lcom/hallisanthe/data/repository/ProductRepository;", "setRepository", "(Lcom/hallisanthe/data/repository/ProductRepository;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug"})
public final class ProductDetailActivity extends androidx.appcompat.app.AppCompatActivity {
    @javax.inject.Inject()
    public com.hallisanthe.data.repository.ProductRepository repository;
    private com.hallisanthe.databinding.ActivityProductDetailBinding binding;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String productId = "";
    
    public ProductDetailActivity() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hallisanthe.data.repository.ProductRepository getRepository() {
        return null;
    }
    
    public final void setRepository(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.data.repository.ProductRepository p0) {
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
}