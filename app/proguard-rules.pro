# Production ProGuard rules for CV Builder AI

# Keep AdMob classes
-keep class com.google.android.gms.ads.** { *; }
-dontwarn com.google.android.gms.ads.**

# Keep PDF generation classes - only essential parts
-keep class com.itextpdf.kernel.** { *; }
-keep class com.itextpdf.layout.** { *; }
-dontwarn com.itextpdf.**
-dontwarn com.fasterxml.jackson.**
-dontwarn java.awt.**
-dontwarn javax.imageio.**
-dontwarn org.slf4j.**

# Keep Android components
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider

# Keep app activities and essential classes
-keep class com.mrteesoft.cvbuilderai.activities.** { *; }
-keep class com.mrteesoft.cvbuilderai.utils.AdManager { *; }