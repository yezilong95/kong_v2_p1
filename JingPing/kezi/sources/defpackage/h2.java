package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.text.TextUtils;
import android.util.Log;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import androidx.fragment.app.c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class h2 extends nv0 {
    public final /* synthetic */ int t;

    public /* synthetic */ h2(int i) {
        this.t = i;
    }

    @Override // defpackage.nv0
    public final Object G(Intent intent, int i) {
        switch (this.t) {
            case 0:
                ao aoVar = ao.b;
                if (i == -1 && intent != null) {
                    String[] stringArrayExtra = intent.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
                    int[] intArrayExtra = intent.getIntArrayExtra("androidx.activity.result.contract.extra.PERMISSION_GRANT_RESULTS");
                    if (intArrayExtra != null && stringArrayExtra != null) {
                        ArrayList arrayList = new ArrayList(intArrayExtra.length);
                        for (int i2 : intArrayExtra) {
                            arrayList.add(Boolean.valueOf(i2 == 0));
                        }
                        ArrayList arrayListD0 = g7.d0(stringArrayExtra);
                        Iterator it = arrayListD0.iterator();
                        Iterator it2 = arrayList.iterator();
                        ArrayList arrayList2 = new ArrayList(Math.min(ce.Z(arrayListD0), ce.Z(arrayList)));
                        while (it.hasNext() && it2.hasNext()) {
                            arrayList2.add(new Pair(it.next(), it2.next()));
                        }
                        break;
                    }
                }
                break;
        }
        return new ActivityResult(intent, i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.nv0
    public final Intent k(Context context, Intent intent) {
        Bundle bundleExtra;
        switch (this.t) {
            case 0:
                Intent intentPutExtra = new Intent("androidx.activity.result.contract.action.REQUEST_PERMISSIONS").putExtra("androidx.activity.result.contract.extra.PERMISSIONS", (String[]) intent);
                oy.i(intentPutExtra, "Intent(ACTION_REQUEST_PE…EXTRA_PERMISSIONS, input)");
                return intentPutExtra;
            case 1:
                return intent;
            default:
                IntentSenderRequest intentSenderRequest = (IntentSenderRequest) intent;
                Intent intent2 = new Intent("androidx.activity.result.contract.action.INTENT_SENDER_REQUEST");
                Intent intent3 = intentSenderRequest.c;
                if (intent3 != null && (bundleExtra = intent3.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) != null) {
                    intent2.putExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE", bundleExtra);
                    intent3.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
                    if (intent3.getBooleanExtra("androidx.fragment.extra.ACTIVITY_OPTIONS_BUNDLE", false)) {
                        intentSenderRequest = new IntentSenderRequest(intentSenderRequest.b, null, intentSenderRequest.d, intentSenderRequest.e);
                    }
                }
                intent2.putExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST", intentSenderRequest);
                if (c.C(2)) {
                    Log.v("FragmentManager", "CreateIntent created the following intent: " + intent2);
                }
                return intent2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.nv0
    public g2 y(Context context, Intent intent) {
        switch (this.t) {
            case 0:
                String[] strArr = (String[]) intent;
                if (strArr.length == 0) {
                    return new g2(ao.b);
                }
                for (String str : strArr) {
                    if (str == null) {
                        throw new NullPointerException("permission must be non-null");
                    }
                    if (((Build.VERSION.SDK_INT >= 33 || !TextUtils.equals("android.permission.POST_NOTIFICATIONS", str)) ? context.checkPermission(str, Process.myPid(), Process.myUid()) : y90.a(new z90(context).a) ? 0 : -1) != 0) {
                        return null;
                    }
                }
                int iU0 = o40.u0(strArr.length);
                if (iU0 < 16) {
                    iU0 = 16;
                }
                LinkedHashMap linkedHashMap = new LinkedHashMap(iU0);
                for (String str2 : strArr) {
                    Pair pair = new Pair(str2, Boolean.TRUE);
                    linkedHashMap.put(pair.getFirst(), pair.getSecond());
                }
                return new g2(linkedHashMap);
            default:
                return super.y(context, intent);
        }
    }
}
