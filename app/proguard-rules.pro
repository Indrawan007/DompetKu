# ── DompetKu ProGuard Rules ───────────────────────

# Keep all entity classes (Room)
-keep class com.dompetku.app.data.local.entity.** { *; }
-keep class com.dompetku.app.data.local.dao.** { *; }

# Keep domain models
-keep class com.dompetku.app.domain.model.** { *; }

# Keep data classes used in queries
-keep class com.dompetku.app.data.local.entity.CategorySummary { *; }
-keep class com.dompetku.app.data.local.entity.MonthlyTrend { *; }
-keep class com.dompetku.app.data.local.entity.DailyTotal { *; }
-keep class com.dompetku.app.data.local.entity.BudgetWithSpent { *; }
-keep class com.dompetku.app.data.local.entity.TransactionWithDetails { *; }

# Room
-keep class * extends androidx.room.RoomDatabase { *; }
-keep @androidx.room.Entity class * { *; }
-keep @androidx.room.Dao class * { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }

# Coroutines
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}

# MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }

# DataStore
-keepclassmembers class * extends com.google.protobuf.GeneratedMessageLite { *; }

# Biometric
-keep class androidx.biometric.** { *; }

# General
-keepattributes Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable
