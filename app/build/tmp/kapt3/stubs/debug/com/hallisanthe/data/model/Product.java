package com.hallisanthe.data.model;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b-\b\u0086\b\u0018\u00002\u00020\u0001B\u00b5\u0001\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\u0003\u0012\b\b\u0002\u0010\t\u001a\u00020\u0003\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\r\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u000b\u0012\b\b\u0002\u0010\u0015\u001a\u00020\u0013\u0012\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0017\u00a2\u0006\u0002\u0010\u0018J\t\u0010.\u001a\u00020\u0003H\u00c6\u0003J\t\u0010/\u001a\u00020\u0003H\u00c6\u0003J\t\u00100\u001a\u00020\u0003H\u00c6\u0003J\t\u00101\u001a\u00020\u0003H\u00c6\u0003J\t\u00102\u001a\u00020\u0011H\u00c6\u0003J\t\u00103\u001a\u00020\u0013H\u00c6\u0003J\t\u00104\u001a\u00020\u000bH\u00c6\u0003J\t\u00105\u001a\u00020\u0013H\u00c6\u0003J\u000f\u00106\u001a\b\u0012\u0004\u0012\u00020\u00030\u0017H\u00c6\u0003J\t\u00107\u001a\u00020\u0003H\u00c6\u0003J\t\u00108\u001a\u00020\u0003H\u00c6\u0003J\t\u00109\u001a\u00020\u0003H\u00c6\u0003J\t\u0010:\u001a\u00020\u0003H\u00c6\u0003J\t\u0010;\u001a\u00020\u0003H\u00c6\u0003J\t\u0010<\u001a\u00020\u0003H\u00c6\u0003J\t\u0010=\u001a\u00020\u000bH\u00c6\u0003J\t\u0010>\u001a\u00020\u0003H\u00c6\u0003J\u00b9\u0001\u0010?\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\b\b\u0002\u0010\t\u001a\u00020\u00032\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\u00032\b\b\u0002\u0010\r\u001a\u00020\u00032\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u000b2\b\b\u0002\u0010\u0015\u001a\u00020\u00132\u000e\b\u0002\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0017H\u00c6\u0001J\u0013\u0010@\u001a\u00020\u00112\b\u0010A\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010B\u001a\u00020\u0013H\u00d6\u0001J\t\u0010C\u001a\u00020\u0003H\u00d6\u0001R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0011\u0010\b\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001aR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001aR\u0011\u0010\u0007\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001aR\u0011\u0010\u0014\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010\f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u001aR\u0011\u0010\u000e\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u001aR\u0011\u0010\u000f\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010\u001aR\u0011\u0010\u0010\u001a\u00020\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010$R\u0011\u0010\n\u001a\u00020\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010 R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\u001aR\u0011\u0010\t\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u001aR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00030\u0017\u00a2\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u0011\u0010\u0012\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u0011\u0010\r\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b,\u0010\u001aR\u0011\u0010\u0015\u001a\u00020\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b-\u0010+\u00a8\u0006D"}, d2 = {"Lcom/hallisanthe/data/model/Product;", "", "productId", "", "artisanId", "artisanName", "artisanPhone", "artisanWhatsapp", "artisanLocation", "productName", "price", "", "category", "subCategory", "description", "imageUrl", "isAvailable", "", "stockCount", "", "averageRating", "totalReviews", "searchKeywords", "", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;DLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZIDILjava/util/List;)V", "getArtisanId", "()Ljava/lang/String;", "getArtisanLocation", "getArtisanName", "getArtisanPhone", "getArtisanWhatsapp", "getAverageRating", "()D", "getCategory", "getDescription", "getImageUrl", "()Z", "getPrice", "getProductId", "getProductName", "getSearchKeywords", "()Ljava/util/List;", "getStockCount", "()I", "getSubCategory", "getTotalReviews", "component1", "component10", "component11", "component12", "component13", "component14", "component15", "component16", "component17", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class Product {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String productId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String artisanId = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String artisanName = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String artisanPhone = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String artisanWhatsapp = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String artisanLocation = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String productName = null;
    private final double price = 0.0;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String category = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String subCategory = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String imageUrl = null;
    private final boolean isAvailable = false;
    private final int stockCount = 0;
    private final double averageRating = 0.0;
    private final int totalReviews = 0;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<java.lang.String> searchKeywords = null;
    
    public Product(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanId, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanName, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanWhatsapp, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String productName, double price, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String subCategory, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, boolean isAvailable, int stockCount, double averageRating, int totalReviews, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> searchKeywords) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProductId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArtisanId() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArtisanName() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArtisanPhone() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArtisanWhatsapp() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getArtisanLocation() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getProductName() {
        return null;
    }
    
    public final double getPrice() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSubCategory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getImageUrl() {
        return null;
    }
    
    public final boolean isAvailable() {
        return false;
    }
    
    public final int getStockCount() {
        return 0;
    }
    
    public final double getAverageRating() {
        return 0.0;
    }
    
    public final int getTotalReviews() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> getSearchKeywords() {
        return null;
    }
    
    public Product() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component10() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component11() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component12() {
        return null;
    }
    
    public final boolean component13() {
        return false;
    }
    
    public final int component14() {
        return 0;
    }
    
    public final double component15() {
        return 0.0;
    }
    
    public final int component16() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.String> component17() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component3() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component4() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component6() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component7() {
        return null;
    }
    
    public final double component8() {
        return 0.0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.hallisanthe.data.model.Product copy(@org.jetbrains.annotations.NotNull()
    java.lang.String productId, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanId, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanName, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanPhone, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanWhatsapp, @org.jetbrains.annotations.NotNull()
    java.lang.String artisanLocation, @org.jetbrains.annotations.NotNull()
    java.lang.String productName, double price, @org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String subCategory, @org.jetbrains.annotations.NotNull()
    java.lang.String description, @org.jetbrains.annotations.NotNull()
    java.lang.String imageUrl, boolean isAvailable, int stockCount, double averageRating, int totalReviews, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> searchKeywords) {
        return null;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public java.lang.String toString() {
        return null;
    }
}