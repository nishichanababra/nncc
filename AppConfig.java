package app.citta.retail365cloud.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class AppConfig {

    //local url
    public static String AUTH_URL="http://192.168.0.110:1004/";

    //live url
//    public static String AUTH_URL = "http://cittaadmin.cittasolutions.com/";

    private static int MY_PERMISSIONS_REQUEST = 123;

    public static Typeface setOpeSansSemiBoldFont(@NonNull Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Semibold.ttf");
    }

    public static Typeface setOpeSansRegularFont(@NonNull Context context) {
        return Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
    }

    public static Typeface setTypeFaceIcon(@NonNull Context context) {
        return Typeface.createFromAsset(context.getAssets(), "Simple-Line-Icons.ttf");
    }

    public static void showCustomToast(@Nullable Context context, @Nullable String toastmsg) {
        if (toastmsg != null && context != null) {
            Toast.makeText(context, toastmsg, Toast.LENGTH_LONG).show();
        }
    }
}
