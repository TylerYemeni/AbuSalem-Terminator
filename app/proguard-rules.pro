-keep class com.abusalem.guardian.** { *; }
-dontwarn com.abusalem.guardian.**
-optimizationpasses 5
-dontoptimize
-dontpreverify
-allowaccessmodification
-keepattributes *Annotation*
-keepclassmembers class * {
    public <init>(...);
}
