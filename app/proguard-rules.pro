# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /opt/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}
-dontwarn org.jsoup.**
-dontwarn org.json.simple.**
-dontwarn com.tjeannin.apprate.**
-dontwarn de.hdodenhof.**
-dontwarn com.squareup.retrofit2.**
-keep class  org.jsoup.** {
public *;
}
-keep class  org.json.simple.** {
public *;
}
-keep class  com.tjeannin.apprate.** {
public *;
}
-keep class  de.hdodenhof.** {
public *;
}
-keep class  com.squareup.retrofit2.** {
public *;
}
-dontwarn com.ernieyu.feedparser.**
-keep class  com.ernieyu.feedparser.** {
public *;
}
-dontwarn org.libtorrent4j.**
-keep class  org.libtorrent4j.** {
public *;
}
-keep public class com.google.firebase.analytics.FirebaseAnalytics {
    public *;
}
-keep public class com.google.android.gms.measurement.AppMeasurement {
    public *;
}
-dontwarn org.libtorrent4j.**
-keep class  com.takisoft.** {
    public *;
}
-keep class com.devmeca.simpletorrent.ui.settings.sections.** {
    public *;
}