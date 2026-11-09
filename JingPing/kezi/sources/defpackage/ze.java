package defpackage;

import android.content.Intent;
import android.content.IntentSender;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import androidx.activity.ComponentActivity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.IntentSenderRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ze {
    public final HashMap a = new HashMap();
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public ArrayList d = new ArrayList();
    public final transient HashMap e = new HashMap();
    public final HashMap f = new HashMap();
    public final Bundle g = new Bundle();
    public final /* synthetic */ ComponentActivity h;

    public ze(ComponentActivity componentActivity) {
        this.h = componentActivity;
    }

    public final boolean a(int i, int i2, Intent intent) {
        String str = (String) this.a.get(Integer.valueOf(i));
        if (str == null) {
            return false;
        }
        l2 l2Var = (l2) this.e.get(str);
        if (l2Var != null) {
            f2 f2Var = l2Var.a;
            if (this.d.contains(str)) {
                f2Var.b(l2Var.b.G(intent, i2));
                this.d.remove(str);
                return true;
            }
        }
        this.f.remove(str);
        this.g.putParcelable(str, new ActivityResult(intent, i2));
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void b(int i, nv0 nv0Var, Intent intent) {
        Bundle bundleExtra;
        int i2;
        ComponentActivity componentActivity = this.h;
        g2 g2VarY = nv0Var.y(componentActivity, intent);
        if (g2VarY != null) {
            new Handler(Looper.getMainLooper()).post(new t5(this, i, g2VarY, 2));
            return;
        }
        Intent intentK = nv0Var.k(componentActivity, intent);
        if (intentK.getExtras() != null && intentK.getExtras().getClassLoader() == null) {
            intentK.setExtrasClassLoader(componentActivity.getClassLoader());
        }
        if (intentK.hasExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE")) {
            bundleExtra = intentK.getBundleExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
            intentK.removeExtra("androidx.activity.result.contract.extra.ACTIVITY_OPTIONS_BUNDLE");
        } else {
            bundleExtra = null;
        }
        Bundle bundle = bundleExtra;
        if (!"androidx.activity.result.contract.action.REQUEST_PERMISSIONS".equals(intentK.getAction())) {
            if (!"androidx.activity.result.contract.action.INTENT_SENDER_REQUEST".equals(intentK.getAction())) {
                componentActivity.startActivityForResult(intentK, i, bundle);
                return;
            }
            IntentSenderRequest intentSenderRequest = (IntentSenderRequest) intentK.getParcelableExtra("androidx.activity.result.contract.extra.INTENT_SENDER_REQUEST");
            try {
                i2 = i;
            } catch (IntentSender.SendIntentException e) {
                e = e;
                i2 = i;
            }
            try {
                componentActivity.startIntentSenderForResult(intentSenderRequest.b, i2, intentSenderRequest.c, intentSenderRequest.d, intentSenderRequest.e, 0, bundle);
                return;
            } catch (IntentSender.SendIntentException e2) {
                e = e2;
                new Handler(Looper.getMainLooper()).post(new t5(this, i2, e, 3));
                return;
            }
        }
        String[] stringArrayExtra = intentK.getStringArrayExtra("androidx.activity.result.contract.extra.PERMISSIONS");
        if (stringArrayExtra == null) {
            stringArrayExtra = new String[0];
        }
        HashSet hashSet = new HashSet();
        for (int i3 = 0; i3 < stringArrayExtra.length; i3++) {
            if (TextUtils.isEmpty(stringArrayExtra[i3])) {
                throw new IllegalArgumentException(hk.k(new StringBuilder("Permission request for permissions "), Arrays.toString(stringArrayExtra), " must not contain null or empty values"));
            }
            if (Build.VERSION.SDK_INT < 33 && TextUtils.equals(stringArrayExtra[i3], "android.permission.POST_NOTIFICATIONS")) {
                hashSet.add(Integer.valueOf(i3));
            }
        }
        int size = hashSet.size();
        String[] strArr = size > 0 ? new String[stringArrayExtra.length - size] : stringArrayExtra;
        if (size > 0) {
            if (size == stringArrayExtra.length) {
                return;
            }
            int i4 = 0;
            for (int i5 = 0; i5 < stringArrayExtra.length; i5++) {
                if (!hashSet.contains(Integer.valueOf(i5))) {
                    strArr[i4] = stringArrayExtra[i5];
                    i4++;
                }
            }
        }
        if (componentActivity instanceof b2) {
        }
        a2.b(componentActivity, stringArrayExtra, i);
    }

    public final k2 c(String str, nv0 nv0Var, f2 f2Var) {
        d(str);
        this.e.put(str, new l2(f2Var, nv0Var));
        HashMap map = this.f;
        if (map.containsKey(str)) {
            Object obj = map.get(str);
            map.remove(str);
            f2Var.b(obj);
        }
        Bundle bundle = this.g;
        ActivityResult activityResult = (ActivityResult) bundle.getParcelable(str);
        if (activityResult != null) {
            bundle.remove(str);
            f2Var.b(nv0Var.G(activityResult.c, activityResult.b));
        }
        return new k2(this, str, nv0Var);
    }

    public final void d(String str) {
        HashMap map = this.b;
        if (((Integer) map.get(str)) != null) {
            return;
        }
        int iNextInt = pc0.Default.nextInt(2147418112);
        while (true) {
            int i = iNextInt + 65536;
            HashMap map2 = this.a;
            if (!map2.containsKey(Integer.valueOf(i))) {
                map2.put(Integer.valueOf(i), str);
                map.put(str, Integer.valueOf(i));
                return;
            }
            iNextInt = pc0.Default.nextInt(2147418112);
        }
    }

    public final void e(String str) {
        Integer num;
        if (!this.d.contains(str) && (num = (Integer) this.b.remove(str)) != null) {
            this.a.remove(num);
        }
        this.e.remove(str);
        HashMap map = this.f;
        if (map.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + map.get(str));
            map.remove(str);
        }
        Bundle bundle = this.g;
        if (bundle.containsKey(str)) {
            Log.w("ActivityResultRegistry", "Dropping pending result for request " + str + ": " + bundle.getParcelable(str));
            bundle.remove(str);
        }
        HashMap map2 = this.c;
        m2 m2Var = (m2) map2.get(str);
        if (m2Var != null) {
            ArrayList arrayList = m2Var.b;
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                m2Var.a.b((s10) it.next());
            }
            arrayList.clear();
            map2.remove(str);
        }
    }
}
