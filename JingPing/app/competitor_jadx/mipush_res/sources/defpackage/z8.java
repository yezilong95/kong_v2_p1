package defpackage;

import android.animation.Animator;
import android.content.ClipData;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.ContentInfo;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.appcompat.widget.b;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.JsonIOException;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.support.JsonUtils;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.Security;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;
import java.util.TreeMap;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

/* JADX INFO: loaded from: classes.dex */
public class z8 implements pe0, vi0, nf, dd0, je, ac, qi0, ji, fc, ys, av, e20, xd0 {
    public static volatile BouncyCastleProvider G;
    public static final er0 H = new er0();
    public static Class I;
    public static boolean J;
    public static Method K;
    public static boolean L;
    public static Method M;
    public static boolean N;
    public final /* synthetic */ int E;
    public final Object F;

    public z8(int i) {
        Cloneable provider;
        this.E = i;
        switch (i) {
            case 3:
                if (Build.VERSION.SDK_INT >= 26) {
                    this.F = new l2(this);
                    return;
                } else {
                    this.F = new k2(this);
                    return;
                }
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                this.F = new ByteArrayOutputStream();
                return;
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                this.F = new HashMap();
                return;
            case 29:
                this.F = new LinkedHashMap();
                return;
            default:
                synchronized (z8.class) {
                    try {
                        provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME);
                        if (!(provider instanceof BouncyCastleProvider)) {
                            if (G == null) {
                                G = new BouncyCastleProvider();
                            }
                            provider = G;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                    break;
                }
                this.F = (BouncyCastleProvider) provider;
                return;
        }
    }

    public static void w() {
        if (J) {
            return;
        }
        try {
            I = Class.forName("android.view.GhostView");
        } catch (ClassNotFoundException e) {
            Log.i("GhostViewApi21", "Failed to retrieve GhostView class", e);
        }
        J = true;
    }

    public boolean C(int i, int i2, Bundle bundle) {
        return false;
    }

    public void D(int i) {
        ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream) this.F;
        byteArrayOutputStream.write((byte) (i >>> 24));
        byteArrayOutputStream.write((byte) (i >>> 16));
        byteArrayOutputStream.write((byte) (i >>> 8));
        byteArrayOutputStream.write((byte) i);
    }

    @Override // defpackage.pe0
    public void a(zd0 zd0Var, boolean z) {
        if (zd0Var instanceof rx0) {
            ((rx0) zd0Var).z.k().c(false);
        }
        pe0 pe0Var = ((b) this.F).I;
        if (pe0Var != null) {
            pe0Var.a(zd0Var, z);
        }
    }

    @Override // defpackage.ji
    public ClipData b() {
        return ((ContentInfo) this.F).getClip();
    }

    @Override // defpackage.av
    public int c() {
        return ((ExtendedFloatingActionButton) this.F).i0;
    }

    @Override // defpackage.fc
    public void d() {
        ((Animator) this.F).end();
    }

    @Override // defpackage.xd0
    public boolean f(zd0 zd0Var, MenuItem menuItem) {
        Object obj = this.F;
        switch (this.E) {
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                int i = NavigationBarView.I;
                NavigationBarView navigationBarView = (NavigationBarView) obj;
                navigationBarView.getClass();
                navigationBarView.getClass();
                break;
            default:
                ((NavigationView) obj).getClass();
                break;
        }
        return false;
    }

    @Override // defpackage.av
    public int g() {
        return ((ExtendedFloatingActionButton) this.F).h0;
    }

    @Override // defpackage.av
    public int getHeight() {
        return ((ExtendedFloatingActionButton) this.F).getMeasuredHeight();
    }

    @Override // defpackage.av
    public int getWidth() {
        ExtendedFloatingActionButton extendedFloatingActionButton = (ExtendedFloatingActionButton) this.F;
        return (extendedFloatingActionButton.getMeasuredWidth() - (extendedFloatingActionButton.getCollapsedPadding() * 2)) + extendedFloatingActionButton.h0 + extendedFloatingActionButton.i0;
    }

    @Override // defpackage.vi0
    public s81 h(View view, s81 s81Var) {
        AppBarLayout appBarLayout = (AppBarLayout) this.F;
        WeakHashMap weakHashMap = f51.a;
        s81 s81Var2 = appBarLayout.getFitsSystemWindows() ? s81Var : null;
        if (!Objects.equals(appBarLayout.K, s81Var2)) {
            appBarLayout.K = s81Var2;
            appBarLayout.setWillNotDraw(!(appBarLayout.c0 != null && appBarLayout.getTopInset() > 0));
            appBarLayout.requestLayout();
        }
        return s81Var;
    }

    @Override // defpackage.ji
    public int i() {
        return ((ContentInfo) this.F).getFlags();
    }

    @Override // defpackage.ji
    public ContentInfo j() {
        return (ContentInfo) this.F;
    }

    @Override // defpackage.ys
    public void k(z91 z91Var) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new z7("EmojiCompatInitializer", 1));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new j(this, z91Var, threadPoolExecutor, 2));
    }

    @Override // defpackage.nf
    public void l() {
        ((ka) this.F).H.l();
    }

    @Override // defpackage.xd0
    public void m(zd0 zd0Var) {
        int i = this.E;
    }

    @Override // defpackage.pe0
    public boolean n(zd0 zd0Var) {
        b bVar = (b) this.F;
        if (zd0Var == bVar.G) {
            return false;
        }
        bVar.c0 = ((rx0) zd0Var).A.a;
        pe0 pe0Var = bVar.I;
        if (pe0Var != null) {
            return pe0Var.n(zd0Var);
        }
        return false;
    }

    @Override // defpackage.ji
    public int o() {
        return ((ContentInfo) this.F).getSource();
    }

    @Override // defpackage.av
    public ViewGroup.LayoutParams p() {
        return new ViewGroup.LayoutParams(-2, -2);
    }

    @Override // defpackage.qi0
    public Object q() {
        Type type = (Type) this.F;
        if (!(type instanceof ParameterizedType)) {
            throw new JsonIOException("Invalid EnumSet type: " + type.toString());
        }
        Type type2 = ((ParameterizedType) type).getActualTypeArguments()[0];
        if (type2 instanceof Class) {
            return EnumSet.noneOf((Class) type2);
        }
        throw new JsonIOException("Invalid EnumSet type: " + type.toString());
    }

    @Override // defpackage.ac
    public void r(Typeface typeface) {
        sf sfVar = (sf) this.F;
        if (sfVar.o(typeface)) {
            sfVar.i(false);
        }
    }

    public void s(ye0... ye0VarArr) {
        t40.n(ye0VarArr, "migrations");
        for (ye0 ye0Var : ye0VarArr) {
            int i = ye0Var.startVersion;
            int i2 = ye0Var.endVersion;
            LinkedHashMap linkedHashMap = (LinkedHashMap) this.F;
            Integer numValueOf = Integer.valueOf(i);
            Object treeMap = linkedHashMap.get(numValueOf);
            if (treeMap == null) {
                treeMap = new TreeMap();
                linkedHashMap.put(numValueOf, treeMap);
            }
            TreeMap treeMap2 = (TreeMap) treeMap;
            if (treeMap2.containsKey(Integer.valueOf(i2))) {
                Log.w("ROOM", "Overriding migration " + treeMap2.get(Integer.valueOf(i2)) + " with " + ye0Var);
            }
            treeMap2.put(Integer.valueOf(i2), ye0Var);
        }
    }

    @Override // defpackage.e20
    public void setVisibility(int i) {
        ((View) this.F).setVisibility(i);
    }

    public void t(tt ttVar) {
        try {
            ((ByteArrayOutputStream) this.F).write(ttVar.getEncoded());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public String toString() {
        switch (this.E) {
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                return "ContentInfoCompat{" + ((ContentInfo) this.F) + JsonUtils.CLOSE;
            default:
                return super.toString();
        }
    }

    public void u(byte[] bArr) {
        try {
            ((ByteArrayOutputStream) this.F).write(bArr);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public j2 v(int i) {
        return null;
    }

    public j2 x(int i) {
        return null;
    }

    public o51 y(Class cls) {
        String str;
        df dfVarA = yn0.a(cls);
        Class cls2 = dfVarA.E;
        t40.n(cls2, "jClass");
        String canonicalName = null;
        if (!cls2.isAnonymousClass() && !cls2.isLocalClass()) {
            boolean zIsArray = cls2.isArray();
            HashMap map = df.G;
            if (zIsArray) {
                Class<?> componentType = cls2.getComponentType();
                if (componentType.isPrimitive() && (str = (String) map.get(componentType.getName())) != null) {
                    canonicalName = str.concat("Array");
                }
                if (canonicalName == null) {
                    canonicalName = "kotlin.Array";
                }
            } else {
                canonicalName = (String) map.get(cls2.getName());
                if (canonicalName == null) {
                    canonicalName = cls2.getCanonicalName();
                }
            }
        }
        if (canonicalName != null) {
            return ((tb) this.F).c(dfVarA, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(canonicalName));
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public void z() {
        ((wz) this.F).e0.G();
    }

    public /* synthetic */ z8(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    public z8(r51 r51Var, q51 q51Var) {
        this.E = 1;
        t40.n(r51Var, "store");
        oj ojVar = oj.b;
        t40.n(ojVar, "defaultCreationExtras");
        this.F = new tb(r51Var, q51Var, ojVar);
    }

    public z8(TextView textView) {
        this.E = 18;
        this.F = new lt(textView);
    }

    public z8(Context context) {
        this.E = 17;
        this.F = context.getApplicationContext();
    }

    public z8(ContentInfo contentInfo) {
        this.E = 14;
        contentInfo.getClass();
        this.F = ab.g(contentInfo);
    }

    private final void A(zd0 zd0Var) {
    }

    private final void B(zd0 zd0Var) {
    }

    @Override // defpackage.e20
    public void e(ViewGroup viewGroup, View view) {
    }
}
