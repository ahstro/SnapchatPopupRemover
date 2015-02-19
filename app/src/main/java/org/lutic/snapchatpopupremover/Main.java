package org.lutic.snapchatpopupremover;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;


public class Main implements IXposedHookLoadPackage {

    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.snapchat.android")) {
            return;
        }

        // Effectively disables the getErrorDialog function by returning
        // '0' which is the SUCCESS constant for isGooglePlayServicesAvailable()
        // Probably very bad since the app might think you actually have the Google services.
        XposedHelpers.findAndHookMethod(
                "com.google.android.gms.common.GooglePlayServicesUtil", lpparam.classLoader,
                "isGooglePlayServicesAvailable", "android.content.Context",
                XC_MethodReplacement.returnConstant(0)
        );

    }
}
