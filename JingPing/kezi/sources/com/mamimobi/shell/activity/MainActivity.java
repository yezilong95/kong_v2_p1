package com.mamimobi.shell.activity;

import android.content.Intent;
import android.net.VpnService;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Lifecycle$State;
import com.mamimobi.shell.R$id;
import com.mamimobi.shell.R$layout;
import com.mamimobi.shell.activity.MainActivity;
import com.mamimobi.shell.receiver.ResultReceiver;
import com.mamimobi.shell.service.MyVpnService;
import defpackage.a30;
import defpackage.b30;
import defpackage.ba0;
import defpackage.c80;
import defpackage.g80;
import defpackage.h2;
import defpackage.h9;
import defpackage.i2;
import defpackage.ig0;
import defpackage.j2;
import defpackage.ks0;
import defpackage.lg0;
import defpackage.m2;
import defpackage.o70;
import defpackage.sd;
import defpackage.w10;
import defpackage.wg0;
import defpackage.x30;
import defpackage.xi;
import defpackage.z20;
import defpackage.za0;
import defpackage.ze;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    public static final ExecutorService P = Executors.newSingleThreadExecutor();
    public ImageView C;
    public Button D;
    public TextView E;
    public TextView F;
    public ProgressBar G;
    public TextView H;
    public g80 J;
    public j2 K;
    public final Handler B = new Handler(Looper.getMainLooper());
    public boolean I = false;
    public boolean L = false;
    public boolean M = false;
    public boolean N = false;
    public int O = 0;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.Object, y30] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void m() {
        Object obj;
        c80 c80Var = this.J.b;
        ?? r1 = new ba0() { // from class: y30
            @Override // defpackage.ba0
            public final void onChanged(Object obj2) {
                sd sdVar = (sd) obj2;
                MainActivity mainActivity = this.a;
                if (sdVar == null) {
                    int i = mainActivity.O;
                    mainActivity.O = i + 1;
                    if (i < 3) {
                        mainActivity.m();
                        return;
                    } else {
                        mainActivity.finish();
                        return;
                    }
                }
                if (mainActivity.M) {
                    return;
                }
                mainActivity.M = true;
                mainActivity.D.setEnabled(true);
                String str = sdVar.a;
                ResultReceiver.a = str;
                ResultReceiver.b = sdVar.b;
                xi.U(mainActivity, "pkg", str);
                xi.U(mainActivity, "act", sdVar.b);
                xi.U(mainActivity, "code", String.valueOf(sdVar.c));
                String[] strArr = sdVar.h;
                StringBuilder sb = new StringBuilder();
                if (strArr.length > 0) {
                    sb.append((CharSequence) strArr[0]);
                    for (int i2 = 1; i2 < strArr.length; i2++) {
                        sb.append((CharSequence) ",");
                        sb.append((CharSequence) strArr[i2]);
                    }
                }
                xi.U(mainActivity, "pkgs", sb.toString());
                if (za0.y(mainActivity, sdVar.c, sdVar.a)) {
                    za0.z(mainActivity, sdVar.a, sdVar.b);
                    mainActivity.B.postDelayed(new x30(mainActivity, 1), 1000L);
                } else {
                    MainActivity.P.execute(new l6(mainActivity, 7, sdVar));
                }
            }
        };
        c80Var.getClass();
        b30.a("observe");
        w10 w10Var = this.e;
        if (w10Var.c != Lifecycle$State.DESTROYED) {
            z20 z20Var = new z20(c80Var, this, r1);
            lg0 lg0Var = c80Var.b;
            ig0 ig0VarA = lg0Var.a(r1);
            if (ig0VarA != null) {
                obj = ig0VarA.c;
            } else {
                ig0 ig0Var = new ig0(r1, z20Var);
                lg0Var.e++;
                ig0 ig0Var2 = lg0Var.c;
                if (ig0Var2 == null) {
                    lg0Var.b = ig0Var;
                    lg0Var.c = ig0Var;
                } else {
                    ig0Var2.d = ig0Var;
                    ig0Var.e = ig0Var2;
                    lg0Var.c = ig0Var;
                }
                obj = null;
            }
            a30 a30Var = (a30) obj;
            if (a30Var != null && !a30Var.d(this)) {
                throw new IllegalArgumentException("Cannot add the same observer with different lifecycles");
            }
            if (a30Var == null) {
                w10Var.a(z20Var);
            }
        }
        g80 g80Var = this.J;
        if (g80Var.c) {
            return;
        }
        g80Var.c = true;
        g80Var.e(this, 0);
    }

    public final void n() {
        String[] strArr;
        if (this.L) {
            return;
        }
        Object obj = this.J.b.e;
        if (obj == b30.k) {
            obj = null;
        }
        sd sdVar = (sd) obj;
        if (sdVar == null || (strArr = sdVar.h) == null || strArr.length == 0) {
            this.B.postDelayed(new x30(this, 2), 500L);
            return;
        }
        if (this.N) {
            return;
        }
        this.L = true;
        Intent intentPrepare = VpnService.prepare(this);
        if (intentPrepare == null) {
            this.I = true;
            this.L = false;
            o();
            return;
        }
        j2 j2Var = this.K;
        ze zeVar = j2Var.p;
        HashMap map = zeVar.b;
        String str = j2Var.n;
        Integer num = (Integer) map.get(str);
        h2 h2Var = j2Var.o;
        if (num != null) {
            zeVar.d.add(str);
            try {
                zeVar.b(num.intValue(), h2Var, intentPrepare);
                return;
            } catch (Exception e) {
                zeVar.d.remove(str);
                throw e;
            }
        }
        throw new IllegalStateException("Attempting to launch an unregistered ActivityResultLauncher with contract " + h2Var + " and input " + intentPrepare + ". You must ensure the ActivityResultLauncher is registered before calling launch().");
    }

    public final void o() {
        String strQ;
        if (this.N || (strQ = xi.Q(this, "pkgs")) == null) {
            return;
        }
        this.N = true;
        Intent intent = new Intent(this, (Class<?>) MyVpnService.class);
        intent.putExtra("pkgs", strQ.split(","));
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(intent);
        } else {
            startService(intent);
        }
        Toast.makeText(this, "VPN service is started.", 0).show();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public final void onBackPressed() {
        if (this.I) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Please grant VPN permissions first.", 0).show();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        String strQ = xi.Q(this, "pkg");
        String strQ2 = xi.Q(this, "act");
        String strQ3 = xi.Q(this, "code");
        String strQ4 = xi.Q(this, "pkgs");
        if (strQ != null && strQ2 != null && strQ3 != null && strQ4 != null && za0.y(this, Long.parseLong(strQ3), strQ)) {
            za0.z(this, strQ, strQ2);
            this.B.postDelayed(new x30(this, 1), 1000L);
            return;
        }
        setContentView(R$layout.activity_main);
        this.C = (ImageView) findViewById(R$id.app_icon);
        Button button = (Button) findViewById(R$id.btn_update);
        this.D = button;
        button.setEnabled(false);
        this.E = (TextView) findViewById(R$id.tv_version_info);
        this.F = (TextView) findViewById(R$id.tv_file_size);
        this.G = (ProgressBar) findViewById(R$id.progress_bar);
        this.H = (TextView) findViewById(R$id.tv_update_content);
        h2 h2Var = new h2(1);
        h9 h9Var = new h9(3, this);
        String str = "activity_rq#" + this.l.getAndIncrement();
        ze zeVar = this.m;
        zeVar.getClass();
        w10 w10Var = this.e;
        if (w10Var.c.isAtLeast(Lifecycle$State.STARTED)) {
            throw new IllegalStateException("LifecycleOwner " + this + " is attempting to register while current state is " + w10Var.c + ". LifecycleOwners must call register before they are STARTED.");
        }
        zeVar.d(str);
        HashMap map = zeVar.c;
        m2 m2Var = (m2) map.get(str);
        if (m2Var == null) {
            m2Var = new m2(w10Var);
        }
        i2 i2Var = new i2(zeVar, str, h9Var, h2Var);
        m2Var.a.a(i2Var);
        m2Var.b.add(i2Var);
        map.put(str, m2Var);
        this.K = new j2(zeVar, str, h2Var);
        ks0 ks0VarD = d();
        if (this.h == null) {
            this.h = new wg0(getApplication(), this, getIntent() != null ? getIntent().getExtras() : null);
        }
        this.J = (g80) new o70(ks0VarD, this.h, a()).w(g80.class);
        m();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public final void onDestroy() {
        super.onDestroy();
        this.B.removeCallbacksAndMessages(null);
    }
}
