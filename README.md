# Halli-Santhe Digital — README

> Converted from the provided SOP PDF document.

---

```text
STANDARD OPERATING PROCEDURE (SOP) 
HALLI-SANTHE DIGITAL — Android Application 
Version 1.0 | Complete Development Guide for AI-Assisted Coding 
 
═══════════════════════════════════════  
SECTION 0: DOCUMENT PURPOSE & HOW TO USE 
═══════════════════════════════════════  
This SOP is a complete, self-contained speciﬁcation document for building the Halli-
Santhe Digital  Android application. It is written to be handed directly to an AI coding 
assistant (Cursor AI, GitHub Copilot, Claude, ChatGPT, etc.) so that the entire project 
can be generated with minimal back-and-forth. 
This document contains:  
 Project overview and mission 
 Complete technical stack 
 Every screen with UI prompt, layout description, and component list 
 Screen navigation and connection map 
 Firebase data models and schema 
 Backend logic for every feature 
 Kotlin code architecture guidance 
 Step-by-step build order 
Language:  Kotlin only 
Platform:  Android (API 26+) 
Architecture:  MVVM (ViewModel + LiveData) 
 
═══════════════════════════════════════  
SECTION 1: PROJECT OVERVIEW 
═══════════════════════════════════════  
1.1 App Name 
Halli-Santhe Digital  
Tagline: "Your Village Market, Now Digital"  

1.2 Problem Statement 
Local artisans in rural Karnataka sell only at weekly physical markets (Santhes) with 
zero digital presence. Urban consumers cannot discover authentic handmade products 
without physically visiting. This app bridges that gap by creating a digital twin of the 
traditional village market. 
1.3 Mission 
Connect rural artisans/sellers directly with urban buyers through a hyper-local digital 
marketplace that preserves cultural identity and empowers artisan income. 
1.4 User Types 
There are exactly TWO types of users:  
User Type  Also Called  What They Can Do  
Artisan/Seller  Vendor Upload products, manage listings, view reviews received  
Customer/Buyer  Consumer  Browse products, view details, contact seller via WhatsApp, give ratings/reviews
 
═══════════════════════════════════════  
SECTION 2: COMPLETE TECHNICAL STACK 
═══════════════════════════════════════  
text 
LANGUAGE:           Kotlin (100% — no Java) 
MIN SDK:            API 26 (Android 8.0) 
TARGET SDK:         API 34 (Android 14) 
ARCHITECTURE:       MVVM (Model-View-ViewModel) 
                    ViewModel + LiveData + StateFlow 
 
UI FRAMEWORK:       Material Design 3 
LAYOUT:             XML (ConstraintLayout, LinearLayout, RecyclerView) 
 
NAVIGATION:         Jetpack Navigation Component 
                    Single Activity + Multiple Fragments (where applicable) 

                    Plus speciﬁc Activities for complex screens 
 
FIREBASE SUITE: 
  - Firebase Authentication (Phone OTP) 
  - Firebase Firestore (primary database) 
  - Firebase Storage (product images) 
  - Firebase Crashlytics (error tracking) 
 
LOCAL DATABASE:     Room Database (o Ưline cache)  
 
IMAGE LOADING:      Glide 4.x 
IMAGE COMPRESSION:  Compressor (Kotlin library by zetbaitsu) 
 
DEPENDENCY INJECTION: Hilt (Dagger Hilt) 
 
COROUTINES:         Kotlin Coroutines + Flow 
 
WHATSAPP INTENT:    Android Intent (ACTION_VIEW with WhatsApp URL scheme) 
 
SEARCH:             Firestore real-time query + local Room query fallback 
 
GENAI (Optional):   Google Gemini API (for auto product descriptions) 
 
RATINGS:            Firestore sub-collection per product 
 
BUILD SYSTEM:       Gradle (Kotlin DSL) 
2.1 Gradle Dependencies (build.gradle.kts — app level) 
Kotlin 

// Core Android 
implementation("androidx.core:core-ktx:1.12.0") 
implementation("androidx.appcompat:appcompat:1.6.1") 
implementation("com.google.android.material:material:1.11.0") 
implementation("androidx.constraintlayout:constraintlayout:2.1.4") 
 
// Lifecycle & ViewModel 
implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0") 
implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0") 
implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0") 
 
// Navigation 
implementation("androidx.navigation:navigation-fragment-ktx:2.7.6") 
implementation("androidx.navigation:navigation-ui-ktx:2.7.6") 
 
// Firebase 
implementation(platform("com.google.ﬁrebase:ﬁrebase-bom:32.7.0")) 
implementation("com.google.ﬁrebase:ﬁrebase-auth-ktx") 
implementation("com.google.ﬁrebase:ﬁrebase-ﬁrestore-ktx") 
implementation("com.google.ﬁrebase:ﬁrebase-storage-ktx") 
implementation("com.google.ﬁrebase:ﬁrebase-crashlytics-ktx") 
 
// Room Database 
implementation("androidx.room:room-runtime:2.6.1") 
implementation("androidx.room:room-ktx:2.6.1") 
kapt("androidx.room:room-compiler:2.6.1") 
 
// Hilt DI 

implementation("com.google.dagger:hilt-android:2.50") 
kapt("com.google.dagger:hilt-compiler:2.50") 
 
// Coroutines 
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3") 
implementation("org.jetbrains.kotlinx:kotlinx-coroutines-play-services:1.7.3") 
 
// Glide 
implementation("com.github.bumptech.glide:glide:4.16.0") 
kapt("com.github.bumptech.glide:compiler:4.16.0") 
 
// Image Compressor 
implementation("id.zelory:compressor:3.0.1") 
 
// Gemini AI (Optional) 
implementation("com.google.ai.client.generativeai:generativeai:0.2.2") 
 
// Activity Result API 
implementation("androidx.activity:activity-ktx:1.8.2") 
implementation("androidx.fragment:fragment-ktx:1.6.2") 
 
═══════════════════════════════════════  
SECTION 3: FIREBASE DATA SCHEMA 
═══════════════════════════════════════  
3.1 Firestore Collections Structure 
text 
ﬁrestore/ 
│ 

├── users/ 
│   └── {userId}/ 
│       ├── uid: String 
│       ├── phoneNumber: String 
│       ├── userType: String ("artisan" | "customer") 
│       ├── name: String 
│       ├── proﬁleImageUrl: String 
│       ├── location: String 
│       ├── bio: String (artisan only) 
│       ├── whatsappNumber: String (artisan only) 
│       ├── createdAt: Timestamp 
│       └── updatedAt: Timestamp 
│ 
├── products/ 
│   └── {productId}/ 
│       ├── productId: String 
│       ├── artisanId: String (reference to users/{userId}) 
│       ├── artisanName: String 
│       ├── artisanPhone: String 
│       ├── artisanWhatsapp: String 
│       ├── artisanLocation: String 
│       ├── productName: String 
│       ├── price: Double 
│       ├── category: String 
│       ├── subCategory: String 
│       ├── description: String 
│       ├── imageUrl: String 

│       ├── isAvailable: Boolean 
│       ├── stockCount: Int 
│       ├── averageRating: Double 
│       ├── totalReviews: Int 
│       ├── searchKeywords: List<String> (for search) 
│       ├── createdAt: Timestamp 
│       └── updatedAt: Timestamp 
│ 
├── reviews/ 
│   └── {reviewId}/ 
│       ├── reviewId: String 
│       ├── productId: String 
│       ├── customerId: String 
│       ├── customerName: String 
│       ├── rating: Int (1-5) 
│       ├── comment: String 
│       └── createdAt: Timestamp 
│ 
└── enquiries/ 
    └── {enquiryId}/ 
        ├── enquiryId: String 
        ├── productId: String 
        ├── customerId: String 
        ├── artisanId: String 
        ├── message: String 
        └── createdAt: Timestamp 
3.2 Room Local Database Schema (O Ưline Cache)  

Kotlin 
// Tables: 
// - products_table (cached product list) 
// - users_table (cached user proﬁle) 
 
═══════════════════════════════════════  
SECTION 4: PROJECT FOLDER STRUCTURE 
═══════════════════════════════════════  
text 
com.halliSanthe/ 
│ 
├── HalliSantheApplication.kt          (Hilt Application class) 
│ 
├── data/ 
│   ├── model/ 
│   │   ├── User.kt 
│   │   ├── Product.kt 
│   │   └── Review.kt 
│   ├── repository/ 
│   │   ├── AuthRepository.kt 
│   │   ├── ProductRepository.kt 
│   │   └── ReviewRepository.kt 
│   ├── remote/ 
│   │   └── FirestoreService.kt 
│   └── local/ 
│       ├── AppDatabase.kt 
│       ├── ProductDao.kt 

│       └── ProductEntity.kt 
│ 
├── ui/ 
│   ├── splash/ 
│   │   └── SplashActivity.kt 
│   ├── auth/ 
│   │   ├── RoleSelectionActivity.kt 
│   │   ├── PhoneAuthActivity.kt 
│   │   ├── OtpVeriﬁcationActivity.kt 
│   │   └── ProﬁleSetupActivity.kt 
│   ├── customer/ 
│   │   ├── CustomerDashboardActivity.kt 
│   │   ├── fragment/ 
│   │   │   ├── HomeFragment.kt 
│   │   │   ├── CategoryFragment.kt 
│   │   │   ├── SearchFragment.kt 
│   │   │   └── ProﬁleFragment.kt 
│   │   ├── ProductDetailActivity.kt 
│   │   └── ReviewActivity.kt 
│   ├── artisan/ 
│   │   ├── ArtisanDashboardActivity.kt 
│   │   ├── fragment/ 
│   │   │   ├── MyProductsFragment.kt 
│   │   │   ├── UploadProductFragment.kt 
│   │   │   └── ReviewsReceivedFragment.kt 
│   │   └── EditProductActivity.kt 
│   └── common/ 

│       ├── adapter/ 
│       │   ├── ProductAdapter.kt 
│       │   ├── CategoryAdapter.kt 
│       │   └── ReviewAdapter.kt 
│       └── EmptyStateView.kt 
│ 
├── viewmodel/ 
│   ├── AuthViewModel.kt 
│   ├── ProductViewModel.kt 
│   └── ReviewViewModel.kt 
│ 
├── utils/ 
│   ├── Constants.kt 
│   ├── Extensions.kt 
│   ├── ImageCompressor.kt 
│   └── WhatsAppHelper.kt 
│ 
└── di/ 
    └── AppModule.kt 
 
═══════════════════════════════════════  
SECTION 5: COMPLETE SCREEN SPECIFICATIONS 
═══════════════════════════════════════  
 
SCREEN 1: SPLASH SCREEN 
File: SplashActivity.kt 
Layout: activity_splash.xml 
UI Prompt: 

text 
Create a full- screen splash screen with a deep sa Ưron/orange gradient background  
(#FF6B35 to #FF8C00).  
 
Center-place: 
1. A large circular logo/icon with a traditional Indian market illustration  
   (or a basket with crafts icon) — size 120dp x 120dp 
2. App name "Halli-Santhe" in bold Kannada-inspired font, white color,  
   text size 32sp, below the logo 
3. Tagline "Your Village Market, Now Digital" in white, italic, 14sp,  
   below the app name with 8dp margin 
4. A small loading animation (circular progress or animated dots)  
   at the bottom 40dp from bottom edge 
 
Animation: Logo fades in with a slight scale animation (0.8 to 1.0)  
over 800ms. Then tagline fades in. After 2 seconds total, navigate  
to next screen. 
Logic: 
text 
1. Show splash for 2 seconds 
2. Check Firebase Auth current user: 
   - If user is logged in → fetch user type from Firestore 
     - If userType == "artisan" → go to ArtisanDashboardActivity 
     - If userType == "customer" → go to CustomerDashboardActivity 
   - If no user logged in → go to RoleSelectionActivity 
3. Use Handler(Looper.getMainLooper()).postDelayed({}, 2000) 
Navigation OUT: 
 → RoleSelectionActivity (if not logged in) 

 → ArtisanDashboardActivity (if artisan already logged in) 
 → CustomerDashboardActivity (if customer already logged in) 
 
SCREEN 2: ROLE SELECTION SCREEN 
File: RoleSelectionActivity.kt 
Layout: activity_role_selection.xml 
UI Prompt: 
text 
Create a vibrant screen with a warm cream/o Ư-white background (#FFF8F0). 
 
TOP SECTION (35% of screen height): 
- Large header image or illustration showing an Indian market/Santhe scene 
- OR a colorful banner with traditional Indian patterns (rangoli-inspired border) 
 
MIDDLE SECTION — Title: 
- Text: "Welcome to Halli-Santhe" — bold, dark brown (#3E1F00), 24sp 
- Sub-text: "Are you a Seller or a Buyer?" — medium, brown, 16sp 
- Small decorative divider line in orange (#FF6B35) 
 
BOTTOM SECTION — Two large cards side by side (or stacked): 
 
CARD 1 — ARTISAN/SELLER: 
- Background: Gradient orange-to-dark-orange (#FF6B35 → #E85D04) 
- Large icon: 逄逅逆퍺逎透퍽퍾퍿逈选逊逋逌逍逐쳑펀펁쳒퍻퍼逖펂逗逘쳕쳖這通逛逜逞 or a craft/weaving icon, 64dp, white 
- Title: "I'm a Seller" — white, bold, 20sp 
- Subtitle: "Artisan / Vendor" — white, 13sp 
- Corner radius: 16dp 
- Elevation: 8dp 

- Width: match parent with 16dp horizontal padding 
 
CARD 2 — CUSTOMER/BUYER: 
- Background: Gradient green-to-dark-green (#2D9A4E → #1B6B35) 
- Large icon: 뭖뭗뭘뭙뭚 or a shopping bag icon, 64dp, white 
- Title: "I'm a Buyer" — white, bold, 20sp 
- Subtitle: "Customer / Consumer" — white, 13sp 
- Corner radius: 16dp 
- Elevation: 8dp 
- Width: match parent with 16dp horizontal padding 
 
BOTTOM: 
- Small text: "Already have an account? Sign In" — clickable, orange color 
Logic: 
text 
1. User taps "I'm a Seller" → store role="artisan" in SharedPreferences →  
   go to PhoneAuthActivity 
2. User taps "I'm a Buyer" → store role="customer" in SharedPreferences →  
   go to PhoneAuthActivity 
3. Pass selected role as Intent extra: intent.putExtra("USER_ROLE", "artisan/customer") 
Navigation OUT: 
 → PhoneAuthActivity (with USER_ROLE extra = "artisan" or "customer") 
 
SCREEN 3: PHONE NUMBER AUTHENTICATION SCREEN 
File: PhoneAuthActivity.kt 
Layout: activity_phone_auth.xml 
UI Prompt: 
text 

Create a clean authentication screen with warm white background (#FFF8F0). 
 
TOP: 
- Back arrow button (top-left) 
- Decorative illustration of a mobile phone with Indian patterns,  
  centered, 100dp height 
   
MIDDLE: 
- Title: "Enter Your Phone Number" — bold, dark brown, 22sp 
- Subtitle: "We'll send you a veriﬁcation code" — regular, gray, 14sp 
- 24dp top margin 
 
PHONE NUMBER INPUT: 
- Country code preﬁx: "+91" in a rounded box (non-editable),  
  orange border, orange text 
- Phone number EditText: 10-digit number,  
  hint "10-digit mobile number",  
  keyboard type: number 
  Material Design OutlinedTextField style 
  orange accent color (#FF6B35) 
  rounded corners 
 
BUTTON: 
- Large button "Send OTP" — full width,  
  background: sa Ưron orange (#FF6B35),  
  white text, bold, 16sp 
  corner radius: 12dp 
  elevation: 4dp 

  disabled state: gray (#BDBDBD) when number < 10 digits 
 
PRIVACY NOTE: 
- Small text below button: "By continuing, you agree to our Terms of Service" 
  gray, 11sp, centered 
Logic: 
text 
1. Validate: phone number must be exactly 10 digits 
2. Append "+91" preﬁx to make "+91XXXXXXXXXX" 
3. Use Firebase Phone Authentication: 
   PhoneAuthProvider.verifyPhoneNumber( 
     phoneNumber = "+91$phoneNumber", 
     timeout = 60L, 
     unit = TimeUnit.SECONDS, 
     activity = this, 
     callbacks = phoneAuthCallbacks 
   ) 
4. On veriﬁcation sent → navigate to OtpVeriﬁcationActivity 
   Pass: phone number, veriﬁcationId, USER_ROLE 
5. Show progress indicator while sending OTP 
6. Handle errors: invalid number, too many requests, network error 
Navigation OUT: 
 → OtpVeriﬁcationActivity (with veriﬁcationId, phoneNumber, USER_ROLE) 
 
SCREEN 4: OTP VERIFICATION SCREEN 
File: OtpVeriﬁcationActivity.kt 
Layout: activity_otp_veriﬁcation.xml 
UI Prompt: 

text 
Create an OTP entry screen with warm white background (#FFF8F0). 
 
TOP: 
- Back button 
- Icon: Shield/lock with checkmark, 80dp, orange color 
 
CONTENT: 
- Title: "Verify Your Number" — bold, dark brown, 22sp 
- Subtitle: "Enter the 6-digit OTP sent to" — gray, 14sp 
- Phone number display: "+91 XXXXXXXXXX" — bold, orange, 16sp 
- "Change Number" — small clickable text, underlined, gray 
 
OTP INPUT BOXES: 
- 6 individual square boxes in a horizontal row 
- Each box: 48dp x 56dp, rounded corners 8dp 
- Orange border when focused (#FF6B35) 
- Gray border when empty (#BDBDBD) 
- Center-aligned digit, bold, 24sp, dark color 
- Auto-advance to next box when digit entered 
- Auto-delete to previous box on backspace 
 
TIMER: 
- "Resend OTP in 00:45" — gray, 14sp, centered 
- When timer ends: "Resend OTP" becomes clickable, orange color 
 
BUTTON: 
- "Verify & Continue" — full width, orange background (#FF6B35),  

  white text, disabled until all 6 boxes ﬁlled 
  corner radius: 12dp 
 
LOADING STATE: 
- Show circular progress over button when verifying 
Logic: 
text 
1. Auto-read SMS if possible (SMS Retriever API) 
2. On "Verify & Continue": 
   val credential = PhoneAuthProvider.getCredential(veriﬁcationId, otpCode) 
   FirebaseAuth.getInstance().signInWithCredential(credential) 
3. On success: 
   - Check if user document exists in Firestore users/{uid} 
   - If NEW user → go to ProﬁleSetupActivity (with USER_ROLE) 
   - If EXISTING user → fetch userType from Firestore 
     - userType == "artisan" → go to ArtisanDashboardActivity 
     - userType == "customer" → go to CustomerDashboardActivity 
4. On failure: show error Snackbar "Invalid OTP. Please try again." 
5. Resend OTP: re-trigger verifyPhoneNumber after countdown 
Navigation OUT: 
 → ProﬁleSetupActivity (new user) 
 → ArtisanDashboardActivity (returning artisan) 
 → CustomerDashboardActivity (returning customer) 
 
SCREEN 5: PROFILE SETUP SCREEN 
File: ProﬁleSetupActivity.kt 
Layout: activity_proﬁle_setup.xml 
UI Prompt: 

text 
Create a proﬁle setup screen with warm cream background (#FFF8F0). 
 
TOP: 
- Progress indicator: Step 1 of 1 (or a simple header) 
- Title: "Complete Your Proﬁle" — bold, dark brown, 22sp 
- Subtitle text changes based on role: 
  - Artisan: "Tell buyers about yourself and your craft" 
  - Customer: "Let us personalize your experience" 
 
PROFILE PICTURE: 
- Circular image view, 100dp diameter, centered 
- Default: orange circle with person icon 
- Tap to change: camera icon overlay at bottom-right of circle 
- Optional for customer, encouraged for artisan 
 
FORM FIELDS (Material OutlinedTextField, orange accent): 
 
FOR BOTH ROLES: 
1. Full Name — text input, required 
2. Location/Village — text input, hint "e.g., Channapatna, Karnataka" 
 
FOR ARTISAN ONLY (show extra ﬁelds): 
3. Craft Type — dropdown/spinner:  
   [Pottery, Textiles, Woodwork, Jewelry, Painting, Bamboo Crafts,  
    Leather Work, Other] 
4. WhatsApp Number — number input, 10 digits 
   (pre-ﬁll with phone number, editable) 

5. Bio/About — multi-line text, hint "Tell buyers about your craft story...", 
   max 200 characters, character counter shown 
 
FOR CUSTOMER ONLY: 
3. Preferred Categories — multi-select chips 
   (show category list, customer can pick favorites) 
 
SAVE BUTTON: 
- "Save & Continue" — full width, orange, white text, 12dp corner radius 
- Validates required ﬁelds before proceeding 
Logic: 
text 
1. Get USER_ROLE from intent 
2. Build User object with all ﬁeld values 
3. Save to Firestore: users/{uid}/{all ﬁelds} 
4. If artisan uploaded proﬁle photo: 
   - Compress image using Compressor library 
   - Upload to Firebase Storage: proﬁle_images/{uid}.jpg 
   - Get download URL and save in user document 
5. On success: 
   - If artisan → go to ArtisanDashboardActivity 
   - If customer → go to CustomerDashboardActivity 
6. Show loading dialog while saving 
Navigation OUT: 
 → ArtisanDashboardActivity (if artisan) 
 → CustomerDashboardActivity (if customer) 
 
SCREEN 6: ARTISAN DASHBOARD 

File: ArtisanDashboardActivity.kt 
Layout: activity_artisan_dashboard.xml 
UI Prompt: 
text 
Create a vibrant artisan dashboard with a traditional Indian market feel. 
 
COLOR SCHEME:  
- Primary: Sa Ưron Orange (#FF6B35)  
- Secondary: Deep Turmeric Yellow (#FFB300) 
- Background: Warm cream (#FFF8F0) 
- Card background: White (#FFFFFF) 
 
TOP APP BAR: 
- Background: orange gradient (#FF6B35 → #E85D04) 
- App name: "Halli-Santhe" — white, bold, left-aligned 
- Greeting: "Namaste, {ArtisanName}! 뜱뜲뜳뜴뜵뜶띃뜷뜸뜹뜺뜻뜼뜽뜾뜿띀띁띂띄" — white, 14sp, below title 
- Proﬁle avatar (circular, 40dp) — right side, tap to go to proﬁle 
- Logout icon — right side 
 
BOTTOM NAVIGATION BAR (3 tabs): 
Tab 1: "My Products" — icon: grid/store icon 
Tab 2: "Upload" — icon: plus/upload icon (highlighted, special) 
Tab 3: "Reviews" — icon: star icon 
 
The Upload tab should have a special orange FAB-style appearance to make it  
stand out. Or use a FloatingActionButton above the bottom nav. 
 
CONTENT AREA: 

Shows fragment based on selected tab. 
 
ARTISAN STATS BANNER (top of home tab): 
- Horizontal scrollable row of 3 stat cards: 
  Card 1: "Total Products" — count, orange icon 
  Card 2: "Avg Rating" — star rating, yellow icon   
  Card 3: "Total Reviews" — count, green icon 
- Each card: white background, orange border, rounded 12dp, shadow 
Fragments inside Artisan Dashboard: 
Fragment A: MY PRODUCTS FRAGMENT 
File: MyProductsFragment.kt 
Layout: fragment_my_products.xml 
text 
UI Prompt: 
- Title bar: "My Listings" — bold, dark brown, 20sp 
- RecyclerView with LinearLayoutManager (single column for artisan) 
- Each product card shows: 
  * Product image (left, 80dp x 80dp, rounded 8dp) 
  * Product name — bold, 16sp 
  * Price — orange, bold, 15sp (₹ symbol) 
  * Category — chip/tag style, green 
  * Available/Unavailable toggle switch 
  * Edit button (pencil icon) — orange 
  * Delete button (trash icon) — red 
  * Swipe to delete functionality 
- EMPTY STATE: 
  * Illustration: empty box or craft illustration 
  * Text: "No products yet! Start by uploading your ﬁrst craft 虞號虠虡虢" 

  * Button: "Upload Now" — orange 
Logic: 
text 
1. Fetch products from Firestore where artisanId == currentUser.uid 
   (real-time listener with addSnapshotListener) 
2. Display in RecyclerView using ProductAdapter (artisan view mode) 
3. Toggle availability: update isAvailable ﬁeld in Firestore 
4. Tap Edit: go to EditProductActivity with product data 
5. Delete: show conﬁrmation dialog → delete from Firestore + Storage 
6. Show empty state if product list is empty 
Fragment B: UPLOAD PRODUCT FRAGMENT 
File: UploadProductFragment.kt 
Layout: fragment_upload_product.xml 
text 
UI Prompt: 
Full form to add a new product. ScrollView containing: 
 
HEADER: 
- Title "Add New Product 뭃뭄뭅뭆" — bold, orange, 22sp 
 
IMAGE UPLOAD SECTION: 
- Large dashed-border rectangle (match width, 200dp height) 
- Background: light orange (#FFF3E0) 
- Center: camera icon (64dp, orange) + text "Tap to add product photo" 
- After selection: show selected image preview, full size 
- Tap to change: ﬂoating camera button overlay 
- Required ﬁeld indicator (red asterisk) 
 

FORM FIELDS (all Material OutlinedTextField with orange accent): 
 
1. Product Name*  
   - Hint: "e.g., Channapatna Wooden Toy" 
   - Max 60 characters 
 
2. Price (₹)* 
   - Number input with decimal 
   - Preﬁx: "₹" symbol 
   - Hint: "Enter selling price" 
 
3. Category* (Dropdown/Spinner) 
   Options: 
   - Home Decor 
   - Fashion & Accessories 
   - Eco-Friendly Products 
   - Kitchen & Utility Items 
   - Handmade Gifts & Toys 
   - Natural & Wellness Products 
 
4. Sub-Category (Dropdown, updates based on Category): 
   - Home Decor → [Terracotta items, Bamboo crafts, Wooden decorations,  
                    Folk paintings] 
   - Fashion → [Handwoven clothes, Embroidered bags, Handmade jewelry,  
                 Leather products] 
   - Eco-Friendly → [Jute bags, Coconut shell items, Palm leaf products,  
                      Reusable cloth bags] 
   - Kitchen → [Wooden kitchen tools, Ceramic cups and plates,  

                 Brass/copper utensils, Storage baskets] 
   - Gifts & Toys → [Wooden toys, Clay idols, Handmade cards,  
                      Festival decorations] 
   - Wellness → [Handmade soaps, Candles, Herbal products, Incense sticks] 
 
5. Stock Count 
   - Number input 
   - Hint: "Available quantity" 
 
6. Description (multi-line, 4 lines visible) 
   - Hint: "Describe your product — material, size, how it's made..." 
   - Max 300 characters 
   - "Auto-Generate " button next to label (calls Gemini AI) 
 
7. WhatsApp Contact Number 
   - Pre-ﬁlled from artisan proﬁle 
   - Editable, 10 digits 
 
SUBMIT BUTTON: 
- "Upload Product 띙띚띞띟띛띜띝" — full width, orange gradient, white bold text 
- 16dp corner radius, 6dp elevation 
- Shows upload progress bar when tapped 
 
UPLOAD PROGRESS: 
- LinearProgressIndicator (orange) appears below button 
- Text: "Compressing image... / Uploading... / Almost done!" 
Logic: 
text 

1. Validate all required ﬁelds (name, price, category, image) 
2. Image selection: 
   - Pick from gallery OR camera 
   - Show preview immediately 
3. On submit: 
   a. Compress image using Compressor library (max 500KB) 
   b. Upload image to Firebase Storage: products/{productId}/image.jpg 
   c. Get download URL 
   d. Generate search keywords list from product name + category 
   e. Call Gemini API (optional) for description if user tapped Auto-Generate 
   f. Create Product object and save to Firestore products collection 
   g. Update artisan's product count in their user document 
4. On success: show success Snackbar, clear form, switch to My Products tab 
5. Handle errors: show retry option 
Fragment C: REVIEWS RECEIVED FRAGMENT 
File: ReviewsReceivedFragment.kt 
Layout: fragment_reviews_received.xml 
text 
UI Prompt: 
- Title: "Customer Reviews " — bold, dark brown, 20sp 
 
RATING SUMMARY CARD: 
- White card, orange border, 16dp corner radius 
- Large centered number: "4.5" — bold, 48sp, orange 
- Row of 5 stars (ﬁlled based on average rating) 
- Text: "Based on 23 reviews" — gray, 14sp 
 
REVIEWS LIST (RecyclerView, LinearLayoutManager): 

Each review card: 
- Circular customer avatar (initials or default) — 48dp 
- Customer name — bold, 15sp 
- Date — gray, 12sp, right-aligned 
- Star rating (1-5 stars displayed as icons) 
- Review comment — gray, 14sp, italic 
- Divider between cards 
 
EMPTY STATE: 
- Star illustration with text "No reviews yet.  
  Your ﬁrst sale is just around the corner! 芖芗芘芙芚芛" 
 
FILTER CHIPS (horizontal scroll at top): 
- All | 5 Star | 4 Star | 3 Star | 2 Star | 1 Star 
Logic: 
text 
1. Fetch reviews from Firestore where product's artisanId == currentUser.uid 
   (join: get all productIds of this artisan, then fetch reviews) 
2. Calculate average rating from all fetched reviews 
3. Display in RecyclerView using ReviewAdapter 
4. Implement ﬁlter chip click → ﬁlter list by star rating 
5. Empty state when no reviews 
 
SCREEN 7: CUSTOMER DASHBOARD 
File: CustomerDashboardActivity.kt 
Layout: activity_customer_dashboard.xml 
UI Prompt: 
text 

Create a colorful, vibrant marketplace dashboard inspired by Indian bazaar aesthetics. 
 
COLOR SCHEME: 
- Primary: Deep Sa Ưron (#FF6B35)  
- Secondary: Marigold Yellow (#FFB300) 
- Accent: Forest Green (#2D9A4E) 
- Background: Warm cream (#FFF8F0) 
- Card backgrounds: White 
 
TOP APP BAR: 
- Orange gradient background 
- Left: App logo (small) + "Halli-Santhe" text, white, bold 
- Center: Search bar (outlined, white background, rounded 24dp) 
  Hint: "Search products, crafts..."  
  Search icon inside bar 
- Right: Proﬁle avatar (circular, 40dp) 
 
BOTTOM NAVIGATION BAR (4 tabs): 
Tab 1: 諝諞諣諟諠諡諢 "Home" — all products grid view 
Tab 2: 굸굹 "Categories" — category browse 
Tab 3: 껳껱껲 "Search" — dedicated search screen 
Tab 4: 躽躾躿軀 "Proﬁle" — customer proﬁle 
Fragment D: HOME FRAGMENT (Customer) 
File: HomeFragment.kt 
Layout: fragment_customer_home.xml 
text 
UI Prompt for Customer Home: 
 

GREETING BANNER: 
- Orange gradient card (full width, 100dp height, 12dp corner radius) 
- Text: "Namaste, {CustomerName}! 뜱뜲뜳뜴뜵뜶띃뜷뜸뜹뜺뜻뜼뜽뜾뜿띀띁띂띄" 
- Subtitle: "Discover authentic Indian crafts" 
- Small decorative Indian pattern on right side 
 
CATEGORY QUICK SCROLL (horizontal RecyclerView): 
Title: "Shop by Category" — bold, dark brown, 18sp, with "See All" link (right) 
Horizontal scrollable row of category chips/cards: 
Each chip: 
- Colored background (each category has unique color) 
- Category icon (emoji or drawable) 
- Category name text below icon 
- Size: 80dp x 90dp, rounded 12dp 
- Slight shadow/elevation 
 
Categories with suggested colors: 
諝諞諣諟諠諡諢 Home Decor — Orange (#FF6B35) 
躉躊 Fashion — Purple (#9C27B0) 
茦茧茨茩茪茫 Eco-Friendly — Green (#4CAF50) 
蒨蒩 Kitchen — Teal (#009688) 
蔍蔎蔏蔐 Gifts & Toys — Pink (#E91E63) 
茇茈 Wellness — Lavender (#673AB7) 
 
FEATURED PRODUCTS SECTION: 
Title: "Featured Crafts " — bold, dark brown, 18sp 
RecyclerView with GridLayoutManager (2 columns): 

Each product card: 
- White background, 12dp corner radius, 4dp elevation 
- Product image (top, full width of card, 160dp height, rounded top) 
- Availability badge: "In Stock" (green) or "Out of Stock" (red chip) 
- Product name — bold, 14sp, dark brown, max 2 lines 
- Category chip — small, rounded, category color 
- Price — "₹ 250" — bold, 16sp, orange color 
- Artisan name — "by {ArtisanName}" — gray, 12sp, with location pin icon 
- Star rating — small stars + count "(12)" 
- Add to Wishlist heart icon (top-right of image, subtle) 
 
LOADING STATE: 
- Skeleton loading cards (shimmer e Ưect) while fetching data  
 
EMPTY STATE: 
- Illustration of empty market stall 
- Text: "The market is setting up!  
  No products yet. Check back soon 謽譄謾謿譀譁譅譂譃" 
Logic: 
text 
1. Fetch all products from Firestore (real-time listener) 
2. Display in RecyclerView with GridLayoutManager(2) 
3. Sort by createdAt descending (newest ﬁrst) 
4. Tap on product card → open ProductDetailActivity 
5. Room DB: cache fetched products for o Ưline access  
6. Swipe to refresh: pull-to-refresh functionality 
7. Category horizontal list: tap → navigate to CategoryFragment  
   with selected category ﬁlter 

Fragment E: CATEGORIES FRAGMENT (Customer) 
File: CategoryFragment.kt 
Layout: fragment_category.xml 
text 
UI Prompt: 
- Background: warm cream 
- Title: "Browse by Category" — bold, dark brown, 20sp 
 
CATEGORY GRID (RecyclerView, GridLayoutManager 2 columns): 
Each category card (large, 160dp height): 
- Full card gradient background (unique per category) 
- Large emoji/icon centered (48dp) 
- Category name — white, bold, 18sp, at bottom 
- Product count — white, 13sp, below name "45 products" 
- Corner radius: 16dp 
- Slight shadow 
 
6 Category Cards: 
1. 諝諞諣諟諠諡諢 Home Decor — Orange gradient 
2. 躉躊 Fashion & Accessories — Purple gradient   
3. 茦茧茨茩茪茫 Eco-Friendly — Green gradient 
4. 蒨蒩 Kitchen & Utility — Teal gradient 
5. 蔍蔎蔏蔐 Gifts & Toys — Pink gradient 
6. 茇茈 Natural & Wellness — Lavender gradient 
 
When category card is tapped: 
Show products of that category in a new list below OR navigate to  
ﬁltered product list screen showing sub-categories as chips at top. 

 
SUB-CATEGORY CHIPS (when category selected): 
Horizontal scrollable chips showing sub-categories 
Orange selected chip, gray unselected 
Products below ﬁlter based on selected sub-category 
Logic: 
text 
1. Fetch count of products per category from Firestore (or cache from Room) 
2. Tap category card → navigate to ﬁltered product screen  
   OR show ﬁltered RecyclerView below 
3. Sub-category chips ﬁlter the RecyclerView list in real-time 
4. Show empty state per category if no products 
Fragment F: SEARCH FRAGMENT (Customer) 
File: SearchFragment.kt 
Layout: fragment_search.xml 
text 
UI Prompt: 
- SearchView at top — full width, orange accent, hint "Search by product name..." 
- Recent Searches section: horizontal chips of past searches (store in SharedPrefs) 
- Clear button to clear all recent searches 
 
SEARCH RESULTS: 
- RecyclerView (LinearLayoutManager, single column for search results) 
- Each result card:  
  * Thumbnail (left, 72dp x 72dp) 
  * Product name (bold) with search term highlighted in orange 
  * Price + Category 
  * Artisan name + location 

- Result count: "Showing 5 results for 'clay'" 
 
NO RESULTS STATE: 
- Illustration of magnifying glass with question mark 
- Text: "No products found for '{searchQuery}'" 
- Suggestion: "Try di Ưerent keywords or browse by category"  
- "Browse Categories" button — orange 
Logic: 
text 
1. Real-time search as user types (debounce 300ms) 
2. Query Firestore:  
   products where searchKeywords array-contains query.lowercase() 
3. Fallback: also search productName.startsWith(query) 
4. Display results in RecyclerView 
5. Tap result → ProductDetailActivity 
6. Store recent searches in SharedPreferences 
7. Minimum 2 characters before triggering search 
Fragment G: PROFILE FRAGMENT (Customer) 
File: ProﬁleFragment.kt 
Layout: fragment_customer_proﬁle.xml 
text 
UI Prompt: 
- Top: Orange gradient header (120dp height) 
- Circular proﬁle picture: 90dp, centered, overlapping header and content 
- Name: bold, 20sp, dark brown, centered 
- Phone number: gray, 14sp, centered 
 
INFO CARDS (white, rounded, shadow): 

- Card 1: Account Info (name, phone, location) 
- Card 2: "My Activity" — reviews given count, products viewed 
- Edit Proﬁle button — outlined, orange 
 
BOTTOM OPTIONS LIST: 
- 궰궱궲궳궴궷궵궶 My Reviews Given 
- 꼈꼉꼊 Notiﬁcations (placeholder) 
-  Help & Support (placeholder) 
- 룇룈룉룊룋 Logout — red text, at bottom 
 
SCREEN 8: PRODUCT DETAIL SCREEN 
File: ProductDetailActivity.kt 
Layout: activity_product_detail.xml 
UI Prompt: 
text 
Create a rich, detailed product screen that feels like an artisan's showcase. 
 
TOP — PRODUCT IMAGE: 
- Full-width image (280dp height, no corner radius at top) 
- Back button (top-left, white circular background with shadow) 
- Wishlist heart button (top-right, white circular background) 
- Image loads with Glide, placeholder: orange-tinted skeleton 
 
PRODUCT INFO SECTION (white card, rounded top corners 24dp,  
overlapping the image by 20dp, elevation 8dp): 
 
ROW 1: Product Name + Availability 
- Product name: bold, 22sp, dark brown 

- Availability badge: green "In Stock" or red "Out of Stock" chip (right side) 
 
ROW 2: Price 
- "₹ 850" — bold, 28sp, orange (#FF6B35) 
- Optional: per unit text "per piece" — gray, 12sp 
 
ROW 3: Category Tags 
- Horizontal chip group: 
  Category chip: orange background, white text, 12sp 
  Sub-category chip: outlined orange, 12sp 
 
DIVIDER LINE (light orange, 1dp) 
 
DESCRIPTION SECTION: 
- Header: "About this Product 귲귳귴귵귶귷" — bold, 16sp, dark brown 
- Description text: gray, 14sp, line height 1.5 
- "Read more / Read less" toggle if text > 4 lines 
 
DIVIDER 
 
ARTISAN INFO CARD: 
- White card, orange left border (4dp), 12dp corner radius, shadow 
- Header: "Crafted By 逄逅逆퍺逎透퍽퍾퍿逈选逊逋逌逍逐쳑펀펁쳒퍻퍼逖펂逗逘쳕쳖這通逛逜逞" — bold, 15sp, dark brown 
- Artisan circular avatar (56dp) — left side 
- Right side: 
  * Artisan name: bold, 16sp 
  * Location with pin emoji: " 궼궾궽 Channapatna, Karnataka" — gray, 13sp 
  * Craft type/specialty — orange text, 13sp 

 
DIVIDER 
 
RATINGS SUMMARY: 
- Header: "Customer Ratings " — bold, 15sp 
- Large rating number: "4.5" — bold, 36sp, orange 
- 5 stars displayed (ﬁlled/half-ﬁlled based on rating) 
- "23 reviews" — gray, 13sp 
- Rating bars (5 ★ to 1★ horizontal bars showing distribution) 
 
REVIEWS SECTION: 
- "Reviews" header with "See All" link 
- Show top 3 reviews (RecyclerView nested or just 3 static review cards) 
- Each review: avatar, name, date, stars, comment 
- "Write a Review" button — outlined orange 
 
BOTTOM STICKY BAR: 
- Full width, white background, 8dp top shadow 
- Two buttons: 
  LEFT: "긒긓 Call Artisan" — outlined orange button (50% width) 
  RIGHT: " 괕괖 Chat on WhatsApp" — ﬁlled green button (#25D366) (50% width) 
   
  OR if artisan's stock = 0: 
  Single "Out of Stock — Contact Seller" button (gray, full width) 
Logic: 
text 
1. Receive productId from Intent 
2. Fetch product from Firestore products/{productId} 

3. Fetch artisan details from Firestore users/{product.artisanId} 
4. Load image with Glide (with placeholder and error image) 
5. Load top 3 reviews from Firestore reviews where productId == this product 
6. Calculate and display average rating 
 
WHATSAPP BUTTON: 
val whatsappNumber = "91${artisan.whatsappNumber}" 
val message = "Hi! I'm interested in your product: ${product.productName}  
               (₹${product.price}). Is it available?" 
val intent = Intent(Intent.ACTION_VIEW).apply { 
    data = Uri.parse("https://wa.me/$whatsappNumber?text=${Uri.encode(message)}") 
} 
startActivity(intent) 
 
CALL BUTTON: 
val intent = Intent(Intent.ACTION_DIAL).apply { 
    data = Uri.parse("tel:+91${artisan.phoneNumber}") 
} 
startActivity(intent) 
 
WRITE REVIEW: Navigate to ReviewActivity with productId 
Navigation IN: 
 From any product card click 
Navigation OUT: 
 Back → previous screen 
 → ReviewActivity (when "Write a Review" tapped) 
 
SCREEN 9: WRITE REVIEW SCREEN 

File: ReviewActivity.kt 
Layout: activity_review.xml 
UI Prompt: 
text 
Create a clean, encouraging review screen. 
 
TOP: 
- Back button 
- Title: "Write a Review " — bold, dark brown, 22sp 
 
PRODUCT REFERENCE CARD: 
- Small product image (left, 64dp, rounded) 
- Product name (bold, 15sp) 
- Artisan name (gray, 13sp) 
All in a light orange card, 12dp corner radius 
 
RATING SECTION: 
- Question: "How would you rate this product?" — bold, 16sp 
- 5 large star icons (48dp each), horizontally centered 
- Stars light up orange when tapped (tap to select 1-5) 
- Selected rating text: "Excellent! / Good / Average / Poor / Terrible" 
  changes dynamically below stars (matches star colors) 
 
REVIEW TEXT: 
- Material OutlinedTextField (multiline, 5 lines) 
- Hint: "Share your experience... What did you love?  
         How was the quality? Would you recommend it?" 
- Character counter: "0 / 250" 

- Orange accent 
 
TAGS (optional quick-add chips): 
- "Great Quality" | "Fast Response" | "Authentic Craft" |  
  "Value for Money" | "Beautiful Design" 
- Tapping chip adds to review text or highlights 
 
SUBMIT BUTTON: 
- "Submit Review 芖芗芘芙芚芛" — full width, orange gradient, white bold 
- Disabled until: rating selected + text > 10 characters 
 
AFTER SUBMIT: 
- Show success animation (star burst or checkmark lottie animation) 
- Success card: "Thank you for your review! 뜱뜲뜳뜴뜵뜶띃뜷뜸뜹뜺뜻뜼뜽뜾뜿띀띁띂띄" 
- Auto-navigate back to product detail after 2 seconds 
Logic: 
text 
1. Validate: rating > 0 AND comment.length > 10 
2. Check if customer already reviewed this product: 
   Query Firestore reviews where productId == X AND customerId == currentUser.uid 
   If exists: show "You have already reviewed this product" → allow edit 
3. Create Review object and save to Firestore reviews collection 
4. Update product's averageRating and totalReviews count in Firestore: 
   - Fetch all reviews for product → recalculate average 
   - Update products/{productId} averageRating ﬁeld 
5. Show success state → navigate back 
 
SCREEN 10: EDIT PRODUCT SCREEN (Artisan Only) 

File: EditProductActivity.kt 
Layout: activity_edit_product.xml 
UI Prompt: 
text 
Same layout as Upload Product Fragment but: 
- Title: "Edit Product " 
- All ﬁelds pre-ﬁlled with existing product data 
- Image shows current product image with "Change Photo" overlay button 
- "Save Changes" button instead of "Upload Product" 
- Additional "Delete This Product" button at very bottom in red color 
  with trash icon 
Logic: 
text 
1. Receive Product object from Intent 
2. Pre-ﬁll all form ﬁelds with product data 
3. Load existing image with Glide 
4. On "Save Changes": 
   - If image changed: compress + re-upload to Firebase Storage 
   - Update product document in Firestore with changed ﬁelds 
   - Update updatedAt timestamp 
5. On "Delete": 
   - Show conﬁrmation AlertDialog: "Are you sure you want to delete  
     this product? This cannot be undone." 
   - On conﬁrm:  
     * Delete image from Firebase Storage 
     * Delete product from Firestore 
     * Navigate back to My Products 
6. Show loading state during operations 

 
═══════════════════════════════════════  
SECTION 6: SCREEN NAVIGATION & CONNECTION MAP 
═══════════════════════════════════════  
text 
COMPLETE NAVIGATION FLOW: 
 
[App Launch] 
     ↓ 
[SplashActivity] ─────────────────────────────────────────┐ 
     │                                                     │ 
     │ (not logged in)           (logged in, artisan)      │ (logged in, customer) 
     ↓                                      ↓              ↓ 
[RoleSelectionActivity]    [ArtisanDashboardActivity]  [CustomerDashboardActivity] 
     │ 
     │ (selected role) 
     ↓ 
[PhoneAuthActivity] 
     │ 
     │ (OTP sent) 
     ↓ 
[OtpVeriﬁcationActivity] 
     │ 
     ├─── (new user) ──→ [ProﬁleSetupActivity] ─── ┬──→ [ArtisanDashboardActivity] 
     │                                             └──→ [CustomerDashboardActivity] 
     │ 
     ├─── (existing artisan) ──→ [ArtisanDashboardActivity] 
     └─── (existing customer) ──→ [CustomerDashboardActivity] 

 
 
ARTISAN DASHBOARD INTERNAL NAVIGATION: 
[ArtisanDashboardActivity] 
     ├── Bottom Nav Tab 1 ──→ [MyProductsFragment] 
     │                              │ 
     │                              ├── Edit button ──→ [EditProductActivity] 
     │                              │                        │ 
     │                              │                        └── Save/Delete → back 
     │                              └── Delete button → conﬁrm dialog → delete 
     │ 
     ├── Bottom Nav Tab 2 ──→ [UploadProductFragment] 
     │                              │ 
     │                              └── Upload success → switch to Tab 1 
     │ 
     └── Bottom Nav Tab 3 ──→ [ReviewsReceivedFragment] 
 
 
CUSTOMER DASHBOARD INTERNAL NAVIGATION: 
[CustomerDashboardActivity] 
     ├── Bottom Nav Tab 1 ──→ [HomeFragment] 
     │                              │ 
     │                              ├── Product card tap ──→ [ProductDetailActivity] 
     │                              │                              │ 
     │                              │                              ├── WhatsApp btn → WhatsApp App (external) 
     │                              │                              ├── Call btn → Phone dialer (external) 
     │                              │                              └── Write Review → [ReviewActivity] 
     │                              │                                                     │ 

     │                              │                                                     └── Submit → back to ProductDetail 
     │                              └── Category chip ──→ [CategoryFragment] (ﬁltered) 
     │ 
     ├── Bottom Nav Tab 2 ──→ [CategoryFragment] 
     │                              │ 
     │                              └── Category card tap → ﬁltered products → product card → 
[ProductDetailActivity] 
     │ 
     ├── Bottom Nav Tab 3 ──→ [SearchFragment] 
     │                              │ 
     │                              └── Search result tap → [ProductDetailActivity] 
     │ 
     └── Bottom Nav Tab 4 ──→ [ProﬁleFragment] 
                                    │ 
                                    └── Logout → [RoleSelectionActivity] (clear back stack) 
 
═══════════════════════════════════════  
SECTION 7: DATA MODELS (KOTLIN) 
═══════════════════════════════════════  
Kotlin 
// User.kt 
data class User( 
    val uid: String = "", 
    val phoneNumber: String = "", 
    val userType: String = "", // "artisan" or "customer" 
    val name: String = "", 
    val proﬁleImageUrl: String = "", 
    val location: String = "", 

    val bio: String = "", 
    val whatsappNumber: String = "", 
    val craftType: String = "", 
    val createdAt: com.google.ﬁrebase.Timestamp? = null, 
    val updatedAt: com.google.ﬁrebase.Timestamp? = null 
) 
 
// Product.kt 
data class Product( 
    val productId: String = "", 
    val artisanId: String = "", 
    val artisanName: String = "", 
    val artisanPhone: String = "", 
    val artisanWhatsapp: String = "", 
    val artisanLocation: String = "", 
    val productName: String = "", 
    val price: Double = 0.0, 
    val category: String = "", 
    val subCategory: String = "", 
    val description: String = "", 
    val imageUrl: String = "", 
    val isAvailable: Boolean = true, 
    val stockCount: Int = 0, 
    val averageRating: Double = 0.0, 
    val totalReviews: Int = 0, 
    val searchKeywords: List<String> = emptyList(), 
    val createdAt: com.google.ﬁrebase.Timestamp? = null, 
    val updatedAt: com.google.ﬁrebase.Timestamp? = null 

) 
 
// Review.kt 
data class Review( 
    val reviewId: String = "", 
    val productId: String = "", 
    val customerId: String = "", 
    val customerName: String = "", 
    val rating: Int = 0, 
    val comment: String = "", 
    val createdAt: com.google.ﬁrebase.Timestamp? = null 
) 
 
═══════════════════════════════════════  
SECTION 8: CATEGORY AND SUB-CATEGORY MASTER DATA 
═══════════════════════════════════════  
Kotlin 
// Constants.kt 
object Categories { 
    val CATEGORY_MAP = mapOf( 
        "Home Decor" to listOf( 
            "Terracotta items", 
            "Bamboo crafts", 
            "Wooden decorations", 
            "Folk paintings" 
        ), 
        "Fashion & Accessories" to listOf( 
            "Handwoven clothes", 

            "Embroidered bags", 
            "Handmade jewelry", 
            "Leather products" 
        ), 
        "Eco-Friendly Products" to listOf( 
            "Jute bags", 
            "Coconut shell items", 
            "Palm leaf products", 
            "Reusable cloth bags" 
        ), 
        "Kitchen & Utility Items" to listOf( 
            "Wooden kitchen tools", 
            "Ceramic cups and plates", 
            "Brass/copper utensils", 
            "Storage baskets" 
        ), 
        "Handmade Gifts & Toys" to listOf( 
            "Wooden toys", 
            "Clay idols", 
            "Handmade cards", 
            "Festival decorations" 
        ), 
        "Natural & Wellness Products" to listOf( 
            "Handmade soaps", 
            "Candles", 
            "Herbal products", 
            "Incense sticks" 
        ) 

    ) 
     
    val CATEGORY_COLORS = mapOf( 
        "Home Decor" to "#FF6B35", 
        "Fashion & Accessories" to "#9C27B0", 
        "Eco-Friendly Products" to "#4CAF50", 
        "Kitchen & Utility Items" to "#009688", 
        "Handmade Gifts & Toys" to "#E91E63", 
        "Natural & Wellness Products" to "#673AB7" 
    ) 
     
    val CATEGORY_EMOJIS = mapOf( 
        "Home Decor" to " 諝諞諣諟諠諡諢", 
        "Fashion & Accessories" to " 躉躊", 
        "Eco-Friendly Products" to " 茦茧茨茩茪茫", 
        "Kitchen & Utility Items" to " 蒨蒩", 
        "Handmade Gifts & Toys" to " 蔍蔎蔏蔐", 
        "Natural & Wellness Products" to " 茇茈" 
    ) 
} 
 
═══════════════════════════════════════  
SECTION 9: KEY UTILITY IMPLEMENTATIONS 
═══════════════════════════════════════  
9.1 WhatsApp Helper 
Kotlin 
// WhatsAppHelper.kt 

object WhatsAppHelper { 
    fun openWhatsApp(context: Context, whatsappNumber: String, productName: String, 
price: Double) { 
        val cleanNumber = "91${whatsappNumber.replace(" ", "").replace("+91", "")}" 
        val message = "Namaste! 뜱뜲뜳뜴뜵뜶띃뜷뜸뜹뜺뜻뜼뜽뜾뜿띀띁띂띄 I found your product *$productName* (₹$price) " + 
                      "on Halli-Santhe Digital. Is it available? Can we discuss?" 
        val url = "https://wa.me/$cleanNumber?text=${Uri.encode(message)}" 
         
        try { 
            val intent = Intent(Intent.ACTION_VIEW).apply { 
                data = Uri.parse(url) 
                setPackage("com.whatsapp") // Direct WhatsApp 
            } 
            context.startActivity(intent) 
        } catch (e: ActivityNotFoundException) { 
            // WhatsApp not installed, open browser 
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url)) 
            context.startActivity(browserIntent) 
        } 
    } 
} 
9.2 Image Compression 
Kotlin 
// ImageCompressor.kt 
suspend fun compressImage(context: Context, imageFile: File): File { 
    return Compressor.compress(context, imageFile) { 
        resolution(1080, 1080) 
        quality(75) 

        format(Bitmap.CompressFormat.JPEG) 
        size(500_000) // Max 500KB 
    } 
} 
9.3 Search Keywords Generator 
Kotlin 
// Generate search keywords for Firestore 
fun generateSearchKeywords(productName: String, category: String, subCategory: 
String): List<String> { 
    val keywords = mutableListOf<String>() 
    val words = productName.lowercase().split(" ") +  
                category.lowercase().split(" ") +  
                subCategory.lowercase().split(" ") 
     
    words.forEach { word -> 
        for (i in 1..word.length) { 
            keywords.add(word.substring(0, i)) 
        } 
    } 
    return keywords.distinct() 
} 
9.4 Empty State View 
Kotlin 
// Show empty state 
fun showEmptyState(recyclerView: RecyclerView, emptyView: View, isEmpty: Boolean) { 
    if (isEmpty) { 
        recyclerView.visibility = View.GONE 
        emptyView.visibility = View.VISIBLE 

    } else { 
        recyclerView.visibility = View.VISIBLE 
        emptyView.visibility = View.GONE 
    } 
} 
 
═══════════════════════════════════════  
SECTION 10: COLOR THEME & DESIGN SYSTEM 
═══════════════════════════════════════  
XML 
<!-- colors.xml — Traditional Indian Market Palette --> 
<resources> 
    <!-- Primary Colors --> 
    <color name="primary_orange">#FF6B35</color> 
    <color name="primary_orange_dark">#E85D04</color> 
    <color name="primary_orange_light">#FF8C55</color> 
     
    <!-- Secondary --> 
    <color name="secondary_yellow">#FFB300</color> 
    <color name="secondary_yellow_dark">#FF8F00</color> 
     
    <!-- Accent Colors --> 
    <color name="accent_green">#2D9A4E</color> 
    <color name="accent_teal">#009688</color> 
    <color name="accent_purple">#9C27B0</color> 
    <color name="accent_pink">#E91E63</color> 
     
    <!-- WhatsApp --> 

    <color name="whatsapp_green">#25D366</color> 
     
    <!-- Backgrounds --> 
    <color name="background_cream">#FFF8F0</color> 
    <color name="background_white">#FFFFFF</color> 
    <color name="background_light_orange">#FFF3E0</color> 
     
    <!-- Text Colors --> 
    <color name="text_dark_brown">#3E1F00</color> 
    <color name="text_gray">#757575</color> 
    <color name="text_light_gray">#BDBDBD</color> 
    <color name="text_white">#FFFFFF</color> 
     
    <!-- Status Colors --> 
    <color name="status_success">#4CAF50</color> 
    <color name="status_error">#F44336</color> 
    <color name="status_warning">#FF9800</color> 
     
    <!-- Star Rating --> 
    <color name="star_ﬁlled">#FFB300</color> 
    <color name="star_empty">#E0E0E0</color> 
</resources> 
XML 
<!-- themes.xml --> 
<style name="Theme.HalliSanthe" 
parent="Theme.MaterialComponents.DayNight.NoActionBar"> 
    <item name="colorPrimary">@color/primary_orange</item> 
    <item name="colorPrimaryVariant">@color/primary_orange_dark</item> 

    <item name="colorSecondary">@color/secondary_yellow</item> 
    <item name="colorOnPrimary">@color/text_white</item> 
    <item name="android:colorBackground">@color/background_cream</item> 
    <item name="android:fontFamily">@font/poppins</item> 
</style> 
 
═══════════════════════════════════════  
SECTION 11: FIREBASE SECURITY RULES 
═══════════════════════════════════════  
JavaScript 
// Firestore Security Rules 
rules_version = '2'; 
service cloud.ﬁrestore { 
  match /databases/{database}/documents { 
     
    // Users: read own, write own 
    match /users/{userId} { 
      allow read: if request.auth != null; 
      allow write: if request.auth != null && request.auth.uid == userId; 
    } 
     
    // Products: anyone authenticated can read,  
    // only artisan can write their own products 
    match /products/{productId} { 
      allow read: if request.auth != null; 
      allow create: if request.auth != null; 
      allow update, delete: if request.auth != null &&  
                               resource.data.artisanId == request.auth.uid; 

    } 
     
    // Reviews: authenticated users can read all,  
    // customers can create, update own reviews 
    match /reviews/{reviewId} { 
      allow read: if request.auth != null; 
      allow create: if request.auth != null; 
      allow update, delete: if request.auth != null &&  
                               resource.data.customerId == request.auth.uid; 
    } 
  } 
} 
 
═══════════════════════════════════════  
SECTION 12: MVVM ARCHITECTURE GUIDE 
═══════════════════════════════════════  
text 
UI Layer (Activities/Fragments) 
        ↕ (observes LiveData / StateFlow) 
ViewModel Layer (AuthViewModel, ProductViewModel, ReviewViewModel) 
        ↕ (calls repository functions) 
Repository Layer (AuthRepository, ProductRepository, ReviewRepository) 
        ↕ (reads/writes) 
Data Sources: 
  - Firebase Firestore (remote) 
  - Firebase Storage (images) 
  - Room Database (local cache) 
  - Firebase Auth (authentication) 

ViewModel Example Structure: 
Kotlin 
// ProductViewModel.kt 
@HiltViewModel 
class ProductViewModel @Inject constructor( 
    private val productRepository: ProductRepository 
) : ViewModel() { 
 
    private val _products = MutableLiveData<List<Product>>() 
    val products: LiveData<List<Product>> = _products 
     
    private val _uploadState = MutableStateFlow<UploadState>(UploadState.Idle) 
    val uploadState: StateFlow<UploadState> = _uploadState 
 
    fun fetchAllProducts() { 
        viewModelScope.launch { 
            productRepository.getAllProducts().collect { productList -> 
                _products.value = productList 
            } 
        } 
    } 
     
    fun uploadProduct(product: Product, imageFile: File, context: Context) { 
        viewModelScope.launch { 
            _uploadState.value = UploadState.Compressing 
            // compress → upload → save to Firestore 
        } 
    } 

} 
 
sealed class UploadState { 
    object Idle : UploadState() 
    object Compressing : UploadState() 
    object Uploading : UploadState() 
    object Success : UploadState() 
    data class Error(val message: String) : UploadState() 
} 
 
═══════════════════════════════════════  
SECTION 13: BUILD ORDER FOR AI ASSISTANT 
═══════════════════════════════════════  
Follow this exact sequence when building the app: 
text 
PHASE 1: PROJECT SETUP 
Step 1:  Create Android project (Kotlin, Empty Activity, API 26) 
Step 2:  Add all Gradle dependencies from Section 2.1 
Step 3:  Create Firebase project, add google-services.json 
Step 4:  Enable Firebase Auth (Phone), Firestore, Storage 
Step 5:  Setup Hilt in Application class 
Step 6:  Create all data model classes (User, Product, Review) 
Step 7:  Setup color theme, fonts (Poppins), and base theme 
 
PHASE 2: AUTHENTICATION FLOW 
Step 8:  Build SplashActivity with auto-login check 
Step 9:  Build RoleSelectionActivity with two-card UI 
Step 10: Build PhoneAuthActivity with Firebase phone auth 

Step 11: Build OtpVeriﬁcationActivity with 6-box OTP UI 
Step 12: Build ProﬁleSetupActivity with role-based form 
Step 13: Create AuthRepository and AuthViewModel 
Step 14: Test complete auth ﬂow end-to-end 
 
PHASE 3: DATA LAYER 
Step 15: Setup Room Database with ProductEntity and ProductDao 
Step 16: Create FirestoreService with all CRUD operations 
Step 17: Create ProductRepository (Firestore + Room) 
Step 18: Create ReviewRepository 
Step 19: Create all ViewModels 
 
PHASE 4: ARTISAN FLOW 
Step 20: Build ArtisanDashboardActivity with bottom nav 
Step 21: Build MyProductsFragment with RecyclerView 
Step 22: Build UploadProductFragment with full form 
Step 23: Build ReviewsReceivedFragment 
Step 24: Build EditProductActivity 
Step 25: Implement image compression and upload 
Step 26: Test artisan complete ﬂow 
 
PHASE 5: CUSTOMER FLOW 
Step 27: Build CustomerDashboardActivity with bottom nav 
Step 28: Build HomeFragment with category scroll + product grid 
Step 29: Build CategoryFragment with category cards 
Step 30: Build SearchFragment with real-time search 
Step 31: Build ProﬁleFragment 
Step 32: Build ProductDetailActivity (most important screen) 

Step 33: Implement WhatsApp intent 
Step 34: Build ReviewActivity 
Step 35: Test customer complete ﬂow 
 
PHASE 6: POLISH & EDGE CASES 
Step 36: Add empty state views to all list screens 
Step 37: Add loading skeletons/progress indicators 
Step 38: Add error handling and retry options 
Step 39: Add pull-to-refresh on home screen 
Step 40: Verify all navigation ﬂows and back stack 
Step 41: Apply Firebase Security Rules 
Step 42: Final UI polish — ensure colorful, vibrant Indian aesthetic 
Step 43: Test on low-end device (mid-range simulation) 
 
═══════════════════════════════════════  
SECTION 14: ACCEPTANCE CRITERIA CHECKLIST 
═══════════════════════════════════════  
Before submitting, verify ALL of the following: 
text 
AUTH & ONBOARDING: 
☐ App asks for role selection (Artisan / Customer) on ﬁrst open 
☐ Phone number + OTP authentication works correctly 
☐ Returning users are auto-logged in and directed to correct dashboard 
☐ Proﬁle data is saved and retrieved from Firestore correctly 
 
ARTISAN FEATURES: 
☐ Artisan can upload product with: name, price, category, sub-category,  
  description, stock count, 1 photo 

☐ Image is compressed before upload (< 500KB) 
☐ Artisan can view all their product listings 
☐ Artisan can edit any product listing 
☐ Artisan can delete any product listing 
☐ Artisan can see all reviews received on their products 
☐ Artisan dashboard shows stats (product count, avg rating) 
 
CUSTOMER FEATURES: 
☐ Customer can browse all products in grid view (2 columns) 
☐ Products are organized by category with quick-ﬁlter chips 
☐ Category browse shows 6 main categories with product counts 
☐ Search works by product name with debounce (min 2 chars) 
☐ Tapping any product opens ProductDetailActivity 
☐ Product detail shows: image, name, price, category, description,  
  artisan info, location, ratings, reviews 
☐ WhatsApp button opens WhatsApp with pre-ﬁlled message to artisan 
☐ Call button opens phone dialer with artisan number 
☐ Customer can write a review (rating + comment) 
☐ Reviews are displayed on product detail screen 
 
DATA & PERFORMANCE: 
☐ All user data persists across app restarts (Firebase + Room) 
☐ Empty states shown gracefully on all list screens 
☐ App handles no internet connection gracefully (shows cached data) 
☐ App does not crash on network loss (shows retry option) 
☐ App launches in < 3 seconds on mid-range device 
☐ Product grid loads < 2 seconds for 20 items 
 

UI/UX: 
☐ UI uses at least 3 vibrant Indian market-inspired colors 
☐ All cards have proper corner radius and shadows 
☐ RecyclerView uses GridLayoutManager (2 columns) for product grid 
☐ RecyclerView uses LinearLayoutManager for lists 
☐ Bottom navigation works correctly on both dashboards 
☐ All buttons have clear labels and appropriate colors 
☐ Loading indicators shown during all async operations 
 
═══════════════════════════════════════  
SECTION 15: MANIFEST PERMISSIONS 
═══════════════════════════════════════  
XML 
<!-- AndroidManifest.xml permissions --> 
<uses-permission android:name="android.permission.INTERNET" /> 
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"  
    android:maxSdkVersion="32" /> 
<uses-permission android:name="android.permission.READ_MEDIA_IMAGES" /> 
<uses-permission android:name="android.permission.CAMERA" /> 
<uses-permission android:name="android.permission.CALL_PHONE" /> 
<uses-permission android:name="android.permission.RECEIVE_SMS" /> 
<uses-permission android:name="android.permission.READ_SMS" /> 
 
═══════════════════════════════════════  
SECTION 16: MANIFEST ACTIVITY DECLARATIONS 
═══════════════════════════════════════  
XML 
<!-- AndroidManifest.xml activities --> 

<application 
    android:name=".HalliSantheApplication" 
    android:label="Halli-Santhe" 
    android:theme="@style/Theme.HalliSanthe"> 
 
    <activity android:name=".ui.splash.SplashActivity" 
        android:exported="true"> 
        <intent-ﬁlter> 
            <action android:name="android.intent.action.MAIN" /> 
            <category android:name="android.intent.category.LAUNCHER" /> 
        </intent-ﬁlter> 
    </activity> 
 
    <activity android:name=".ui.auth.RoleSelectionActivity" /> 
    <activity android:name=".ui.auth.PhoneAuthActivity" /> 
    <activity android:name=".ui.auth.OtpVeriﬁcationActivity" /> 
    <activity android:name=".ui.auth.ProﬁleSetupActivity" /> 
     
    <activity android:name=".ui.artisan.ArtisanDashboardActivity" /> 
    <activity android:name=".ui.artisan.EditProductActivity" /> 
     
    <activity android:name=".ui.customer.CustomerDashboardActivity" /> 
    <activity android:name=".ui.customer.ProductDetailActivity" /> 
    <activity android:name=".ui.customer.ReviewActivity" /> 
 
</application> 
 
═══════════════════════════════════════  

SECTION 17: PROJECT SUMMARY CARD 
═══════════════════════════════════════  
text 
╔════════════════════════════════════════════════════
══════╗  
║           HALLI-SANTHE DIGITAL — QUICK REFERENCE         ║ 
╠════════════════════════════════════════════════════
══════╣  
║ App Name:     Halli-Santhe Digital                       ║ 
║ Platform:     Android (API 26+)                          ║ 
║ Language:     Kotlin (100%)                              ║ 
║ Architecture: MVVM                                       ║ 
╠════════════════════════════════════════════════════
══════╣  
║ USER TYPES:   Artisan/Seller | Customer/Buyer            ║ 
╠════════════════════════════════════════════════════
══════╣  
║ TOTAL SCREENS:                                           ║ 
║  1. Splash Screen                                        ║ 
║  2. Role Selection (Artisan vs Customer)                 ║ 
║  3. Phone Number Entry                                   ║ 
║  4. OTP Veriﬁcation                                     ║ 
║  5. Proﬁle Setup                                        ║ 
║  6. Artisan Dashboard                                    ║ 
║     6a. My Products Fragment                             ║ 
║     6b. Upload Product Fragment                          ║ 
║     6c. Reviews Received Fragment                        ║ 
║  7. Customer Dashboard                                   ║ 
║     7a. Home Fragment (product grid)                     ║ 
║     7b. Categories Fragment                              ║ 

║     7c. Search Fragment                                  ║ 
║     7d. Proﬁle Fragment                                 ║ 
║  8. Product Detail Screen                                ║ 
║  9. Write Review Screen                                  ║ 
║  10. Edit Product Screen                                 ║ 
╠════════════════════════════════════════════════════
══════╣  
║ FIREBASE:     Auth (Phone OTP) + Firestore + Storage     ║ 
║ LOCAL DB:     Room Database (o Ưline cache)              ║ 
║ IMAGES:       Glide + Compressor library                 ║ 
║ WHATSAPP:     Android Intent (wa.me URL scheme)          ║ 
╠════════════════════════════════════════════════════
══════╣  
║ KEY CATEGORIES: Home Decor | Fashion | Eco-Friendly |    ║ 
║  Kitchen | Gifts & Toys | Natural & Wellness             ║ 
╠════════════════════════════════════════════════════
══════╣  
║ UI THEME:     Vibrant Indian Market Aesthetic            ║ 
║ Primary:      Sa Ưron Orange (#FF6B35)                   ║ 
║ Secondary:    Marigold Yellow (#FFB300)                  ║ 
║ Accent:       Forest Green (#2D9A4E)                     ║ 
║ Background:   Warm Cream (#FFF8F0)                       ║ 
╚════════════════════════════════════════════════════
══════╝  
 
```
