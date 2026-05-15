package com.hallisanthe.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J,\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00102\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015J\u000e\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f\u00a8\u0006\u0018"}, d2 = {"Lcom/hallisanthe/viewmodel/ReviewViewModel;", "Landroidx/lifecycle/ViewModel;", "repository", "Lcom/hallisanthe/data/repository/ReviewRepository;", "(Lcom/hallisanthe/data/repository/ReviewRepository;)V", "_reviews", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/hallisanthe/data/model/Review;", "reviews", "Lkotlinx/coroutines/flow/StateFlow;", "getReviews", "()Lkotlinx/coroutines/flow/StateFlow;", "add", "Lkotlinx/coroutines/Job;", "productId", "", "rating", "", "comment", "onDone", "Lkotlin/Function0;", "", "load", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class ReviewViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.hallisanthe.data.repository.ReviewRepository repository = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<com.hallisanthe.data.model.Review>> _reviews = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hallisanthe.data.model.Review>> reviews = null;
    
    @javax.inject.Inject()
    public ReviewViewModel(@org.jetbrains.annotations.NotNull()
    com.hallisanthe.data.repository.ReviewRepository repository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.hallisanthe.data.model.Review>> getReviews() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job load(@org.jetbrains.annotations.NotNull()
    java.lang.String productId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job add(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, int rating, @org.jetbrains.annotations.NotNull()
    java.lang.String comment, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
}