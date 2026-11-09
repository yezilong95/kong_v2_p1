package defpackage;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatToggleButton;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.b;
import androidx.appcompat.widget.j;
import androidx.appcompat.widget.l;
import androidx.core.R$id;
import androidx.work.impl.WorkDatabaseVersions;
import io.nats.client.support.NatsConstants;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class c6 extends o5 implements xd0, LayoutInflater.Factory2 {
    public static final vv0 L0 = new vv0();
    public static final int[] M0 = {R.attr.windowBackground};
    public static final boolean N0 = !"robolectric".equals(Build.FINGERPRINT);
    public static final boolean O0 = true;
    public x5 A0;
    public x5 B0;
    public boolean C0;
    public int D0;
    public boolean F0;
    public Rect G0;
    public Rect H0;
    public n7 I0;
    public OnBackInvokedDispatcher J0;
    public OnBackInvokedCallback K0;
    public final Object N;
    public final Context O;
    public Window P;
    public w5 Q;
    public final Object R;
    public x71 S;
    public cy0 T;
    public CharSequence U;
    public rm V;
    public q5 W;
    public r5 X;
    public l3 Y;
    public ActionBarContextView Z;
    public PopupWindow a0;
    public p5 b0;
    public boolean d0;
    public ViewGroup e0;
    public TextView f0;
    public View g0;
    public boolean h0;
    public boolean i0;
    public boolean j0;
    public boolean k0;
    public boolean l0;
    public boolean m0;
    public boolean n0;
    public boolean o0;
    public b6[] p0;
    public b6 q0;
    public boolean r0;
    public boolean s0;
    public boolean t0;
    public boolean u0;
    public Configuration v0;
    public final int w0;
    public int x0;
    public int y0;
    public boolean z0;
    public h61 c0 = null;
    public final p5 E0 = new p5(this, 0);

    public c6(Context context, Window window, j5 j5Var, Object obj) {
        AppCompatActivity appCompatActivity;
        this.w0 = -100;
        this.O = context;
        this.R = j5Var;
        this.N = obj;
        if (obj instanceof Dialog) {
            while (context != null) {
                if (!(context instanceof AppCompatActivity)) {
                    if (!(context instanceof ContextWrapper)) {
                        break;
                    } else {
                        context = ((ContextWrapper) context).getBaseContext();
                    }
                } else {
                    appCompatActivity = (AppCompatActivity) context;
                    break;
                }
            }
            appCompatActivity = null;
            if (appCompatActivity != null) {
                this.w0 = ((c6) appCompatActivity.j()).w0;
            }
        }
        if (this.w0 == -100) {
            vv0 vv0Var = L0;
            Integer num = (Integer) vv0Var.getOrDefault(this.N.getClass().getName(), null);
            if (num != null) {
                this.w0 = num.intValue();
                vv0Var.remove(this.N.getClass().getName());
            }
        }
        if (window != null) {
            o(window);
        }
        f6.d();
    }

    public static ja0 p(Context context) {
        ja0 ja0Var;
        ja0 ja0Var2;
        if (Build.VERSION.SDK_INT >= 33 || (ja0Var = o5.G) == null) {
            return null;
        }
        ja0 ja0VarB = t5.b(context.getApplicationContext().getResources().getConfiguration());
        ka0 ka0Var = ja0Var.a;
        if (ka0Var.a.isEmpty()) {
            ja0Var2 = ja0.b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (i < ja0VarB.a.a.size() + ka0Var.a.size()) {
                Locale locale = i < ka0Var.a.size() ? ka0Var.a.get(i) : ja0VarB.a.a.get(i - ka0Var.a.size());
                if (locale != null) {
                    linkedHashSet.add(locale);
                }
                i++;
            }
            ja0Var2 = new ja0(new ka0(ia0.a((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]))));
        }
        return ja0Var2.a.a.isEmpty() ? ja0VarB : ja0Var2;
    }

    public static Configuration t(Context context, int i, ja0 ja0Var, Configuration configuration, boolean z) {
        int i2 = i != 1 ? i != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        if (ja0Var != null) {
            t5.d(configuration2, ja0Var);
        }
        return configuration2;
    }

    public final void A() {
        w();
        if (this.j0 && this.S == null) {
            Object obj = this.N;
            if (obj instanceof Activity) {
                this.S = new x71((Activity) obj, this.k0);
            } else if (obj instanceof Dialog) {
                this.S = new x71((Dialog) obj);
            }
            x71 x71Var = this.S;
            if (x71Var != null) {
                x71Var.d(this.F0);
            }
        }
    }

    public final void B(int i) {
        this.D0 = (1 << i) | this.D0;
        if (this.C0) {
            return;
        }
        View decorView = this.P.getDecorView();
        p5 p5Var = this.E0;
        WeakHashMap weakHashMap = f51.a;
        decorView.postOnAnimation(p5Var);
        this.C0 = true;
    }

    public final int C(Context context, int i) {
        if (i != -100) {
            if (i != -1) {
                if (i != 0) {
                    if (i != 1 && i != 2) {
                        if (i != 3) {
                            throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                        }
                        if (this.B0 == null) {
                            this.B0 = new x5(this, context);
                        }
                        return this.B0.g();
                    }
                } else if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() != 0) {
                    return y(context).g();
                }
            }
            return i;
        }
        return -1;
    }

    public final boolean D() {
        sm smVar;
        j jVar;
        boolean z = this.r0;
        this.r0 = false;
        b6 b6VarZ = z(0);
        if (!b6VarZ.m) {
            l3 l3Var = this.Y;
            if (l3Var != null) {
                l3Var.a();
                return true;
            }
            A();
            x71 x71Var = this.S;
            if (x71Var == null || (smVar = x71Var.e) == null || (jVar = ((l) smVar).a.s0) == null || jVar.F == null) {
                return false;
            }
            j jVar2 = ((l) smVar).a.s0;
            de0 de0Var = jVar2 == null ? null : jVar2.F;
            if (de0Var != null) {
                de0Var.collapseActionView();
            }
        } else if (!z) {
            s(b6VarZ, true);
            return true;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0175, code lost:
    
        if (r3.K.getCount() > 0) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void E(defpackage.b6 r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 473
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c6.E(b6, android.view.KeyEvent):void");
    }

    public final boolean F(b6 b6Var, int i, KeyEvent keyEvent) {
        zd0 zd0Var;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((b6Var.k || G(b6Var, keyEvent)) && (zd0Var = b6Var.h) != null) {
            return zd0Var.performShortcut(i, keyEvent, 1);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean G(defpackage.b6 r12, android.view.KeyEvent r13) {
        /*
            Method dump skipped, instruction units count: 360
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c6.G(b6, android.view.KeyEvent):boolean");
    }

    public final void H() {
        if (this.d0) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void I() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z = false;
            if (this.J0 != null && (z(0).m || this.Y != null)) {
                z = true;
            }
            if (z && this.K0 == null) {
                this.K0 = v5.b(this.J0, this);
            } else {
                if (z || (onBackInvokedCallback = this.K0) == null) {
                    return;
                }
                v5.c(this.J0, onBackInvokedCallback);
            }
        }
    }

    @Override // defpackage.o5
    public final void a() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.O);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof c6) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // defpackage.o5
    public final void b() {
        if (this.S != null) {
            A();
            this.S.getClass();
            B(0);
        }
    }

    @Override // defpackage.o5
    public final void d() throws IllegalAccessException {
        String strD;
        this.s0 = true;
        n(false, true);
        x();
        Object obj = this.N;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    strD = la0.D(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
                strD = null;
            }
            if (strD != null) {
                x71 x71Var = this.S;
                if (x71Var == null) {
                    this.F0 = true;
                } else {
                    x71Var.d(true);
                }
            }
            synchronized (o5.L) {
                o5.g(this);
                o5.K.add(new WeakReference(this));
            }
        }
        this.v0 = new Configuration(this.O.getResources().getConfiguration());
        this.t0 = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // defpackage.o5
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void e() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.N
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = defpackage.o5.L
            monitor-enter(r0)
            defpackage.o5.g(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r1
        L11:
            boolean r0 = r3.C0
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.P
            android.view.View r0 = r0.getDecorView()
            p5 r1 = r3.E0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.u0 = r0
            int r0 = r3.w0
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.N
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            vv0 r0 = defpackage.c6.L0
            java.lang.Object r1 = r3.N
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.w0
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            vv0 r0 = defpackage.c6.L0
            java.lang.Object r1 = r3.N
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            x5 r0 = r3.A0
            if (r0 == 0) goto L63
            r0.d()
        L63:
            x5 r0 = r3.B0
            if (r0 == 0) goto L6a
            r0.d()
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c6.e():void");
    }

    @Override // defpackage.xd0
    public final boolean f(zd0 zd0Var, MenuItem menuItem) {
        b6 b6Var;
        Window.Callback callback = this.P.getCallback();
        if (callback != null && !this.u0) {
            zd0 zd0VarK = zd0Var.k();
            b6[] b6VarArr = this.p0;
            int length = b6VarArr != null ? b6VarArr.length : 0;
            int i = 0;
            while (true) {
                if (i < length) {
                    b6Var = b6VarArr[i];
                    if (b6Var != null && b6Var.h == zd0VarK) {
                        break;
                    }
                    i++;
                } else {
                    b6Var = null;
                    break;
                }
            }
            if (b6Var != null) {
                return callback.onMenuItemSelected(b6Var.a, menuItem);
            }
        }
        return false;
    }

    @Override // defpackage.o5
    public final boolean h(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i = 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i = 109;
        }
        if (this.n0 && i == 108) {
            return false;
        }
        if (this.j0 && i == 1) {
            this.j0 = false;
        }
        if (i == 1) {
            H();
            this.n0 = true;
            return true;
        }
        if (i == 2) {
            H();
            this.h0 = true;
            return true;
        }
        if (i == 5) {
            H();
            this.i0 = true;
            return true;
        }
        if (i == 10) {
            H();
            this.l0 = true;
            return true;
        }
        if (i == 108) {
            H();
            this.j0 = true;
            return true;
        }
        if (i != 109) {
            return this.P.requestFeature(i);
        }
        H();
        this.k0 = true;
        return true;
    }

    @Override // defpackage.o5
    public final void i(int i) {
        w();
        ViewGroup viewGroup = (ViewGroup) this.e0.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.O).inflate(i, viewGroup);
        this.Q.a(this.P.getCallback());
    }

    @Override // defpackage.o5
    public final void j(View view) {
        w();
        ViewGroup viewGroup = (ViewGroup) this.e0.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.Q.a(this.P.getCallback());
    }

    @Override // defpackage.o5
    public final void k(View view, ViewGroup.LayoutParams layoutParams) {
        w();
        ViewGroup viewGroup = (ViewGroup) this.e0.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.Q.a(this.P.getCallback());
    }

    @Override // defpackage.o5
    public final void l(CharSequence charSequence) {
        this.U = charSequence;
        rm rmVar = this.V;
        if (rmVar != null) {
            rmVar.setWindowTitle(charSequence);
            return;
        }
        x71 x71Var = this.S;
        if (x71Var == null) {
            TextView textView = this.f0;
            if (textView != null) {
                textView.setText(charSequence);
                return;
            }
            return;
        }
        l lVar = (l) x71Var.e;
        if (lVar.g) {
            return;
        }
        lVar.h = charSequence;
        if ((lVar.b & 8) != 0) {
            Toolbar toolbar = lVar.a;
            toolbar.setTitle(charSequence);
            if (lVar.g) {
                f51.p(toolbar.getRootView(), charSequence);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0048, code lost:
    
        if (r6.h() != false) goto L20;
     */
    @Override // defpackage.xd0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m(defpackage.zd0 r6) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c6.m(zd0):void");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean n(boolean r17, boolean r18) throws java.lang.IllegalAccessException {
        /*
            Method dump skipped, instruction units count: 602
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c6.n(boolean, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void o(android.view.Window r4) {
        /*
            r3 = this;
            android.view.Window r0 = r3.P
            java.lang.String r1 = "AppCompat has already installed itself into the Window"
            if (r0 != 0) goto L66
            android.view.Window$Callback r0 = r4.getCallback()
            boolean r2 = r0 instanceof defpackage.w5
            if (r2 != 0) goto L60
            w5 r1 = new w5
            r1.<init>(r3, r0)
            r3.Q = r1
            r4.setCallback(r1)
            int[] r0 = defpackage.c6.M0
            android.content.Context r1 = r3.O
            r2 = 0
            ou r0 = defpackage.ou.O(r1, r2, r0)
            r1 = 0
            android.graphics.drawable.Drawable r1 = r0.B(r1)
            if (r1 == 0) goto L2b
            r4.setBackgroundDrawable(r1)
        L2b:
            r0.S()
            r3.P = r4
            int r4 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r4 < r0) goto L5f
            android.window.OnBackInvokedDispatcher r4 = r3.J0
            if (r4 != 0) goto L5f
            if (r4 == 0) goto L45
            android.window.OnBackInvokedCallback r0 = r3.K0
            if (r0 == 0) goto L45
            defpackage.v5.c(r4, r0)
            r3.K0 = r2
        L45:
            java.lang.Object r4 = r3.N
            boolean r0 = r4 instanceof android.app.Activity
            if (r0 == 0) goto L5a
            android.app.Activity r4 = (android.app.Activity) r4
            android.view.Window r0 = r4.getWindow()
            if (r0 == 0) goto L5a
            android.window.OnBackInvokedDispatcher r4 = defpackage.v5.a(r4)
            r3.J0 = r4
            goto L5c
        L5a:
            r3.J0 = r2
        L5c:
            r3.I()
        L5f:
            return
        L60:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>(r1)
            throw r4
        L66:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException
            r4.<init>(r1)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c6.o(android.view.Window):void");
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        byte b;
        View appCompatRatingBar;
        View view2;
        if (this.I0 == null) {
            int[] iArr = R$styleable.AppCompatTheme;
            Context context2 = this.O;
            String string = context2.obtainStyledAttributes(iArr).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.I0 = new n7();
            } else {
                try {
                    this.I0 = (n7) context2.getClassLoader().loadClass(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.I0 = new n7();
                }
            }
        }
        n7 n7Var = this.I0;
        int i = b41.a;
        n7Var.getClass();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.View, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_theme, 0);
        if (resourceId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        Context tiVar = (resourceId == 0 || ((context instanceof ti) && ((ti) context).a == resourceId)) ? context : new ti(context, resourceId);
        str.getClass();
        View view3 = null;
        switch (str.hashCode()) {
            case -1946472170:
                b = !str.equals("RatingBar") ? (byte) -1 : (byte) 0;
                break;
            case -1455429095:
                b = !str.equals("CheckedTextView") ? (byte) -1 : (byte) 1;
                break;
            case -1346021293:
                b = !str.equals("MultiAutoCompleteTextView") ? (byte) -1 : (byte) 2;
                break;
            case -938935918:
                b = !str.equals("TextView") ? (byte) -1 : (byte) 3;
                break;
            case -937446323:
                b = !str.equals("ImageButton") ? (byte) -1 : (byte) 4;
                break;
            case -658531749:
                b = !str.equals("SeekBar") ? (byte) -1 : (byte) 5;
                break;
            case -339785223:
                b = !str.equals("Spinner") ? (byte) -1 : (byte) 6;
                break;
            case 776382189:
                b = !str.equals("RadioButton") ? (byte) -1 : (byte) 7;
                break;
            case 799298502:
                b = !str.equals("ToggleButton") ? (byte) -1 : (byte) 8;
                break;
            case 1125864064:
                b = !str.equals("ImageView") ? (byte) -1 : (byte) 9;
                break;
            case 1413872058:
                b = !str.equals("AutoCompleteTextView") ? (byte) -1 : (byte) 10;
                break;
            case 1601505219:
                b = !str.equals("CheckBox") ? (byte) -1 : (byte) 11;
                break;
            case 1666676343:
                b = !str.equals("EditText") ? (byte) -1 : (byte) 12;
                break;
            case 2001146706:
                b = !str.equals("Button") ? (byte) -1 : NatsConstants.CR;
                break;
            default:
                b = -1;
                break;
        }
        switch (b) {
            case 0:
                appCompatRatingBar = new AppCompatRatingBar(tiVar, attributeSet);
                break;
            case 1:
                appCompatRatingBar = new AppCompatCheckedTextView(tiVar, attributeSet);
                break;
            case 2:
                appCompatRatingBar = new AppCompatMultiAutoCompleteTextView(tiVar, attributeSet);
                break;
            case 3:
                appCompatRatingBar = n7Var.e(tiVar, attributeSet);
                break;
            case 4:
                appCompatRatingBar = new AppCompatImageButton(tiVar, attributeSet);
                break;
            case 5:
                appCompatRatingBar = new AppCompatSeekBar(tiVar, attributeSet);
                break;
            case 6:
                appCompatRatingBar = new AppCompatSpinner(tiVar, attributeSet);
                break;
            case 7:
                appCompatRatingBar = n7Var.d(tiVar, attributeSet);
                break;
            case 8:
                appCompatRatingBar = new AppCompatToggleButton(tiVar, attributeSet);
                break;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                appCompatRatingBar = new AppCompatImageView(tiVar, attributeSet);
                break;
            case 10:
                appCompatRatingBar = n7Var.a(tiVar, attributeSet);
                break;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                appCompatRatingBar = n7Var.c(tiVar, attributeSet);
                break;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                appCompatRatingBar = new AppCompatEditText(tiVar, attributeSet);
                break;
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                appCompatRatingBar = n7Var.b(tiVar, attributeSet);
                break;
            default:
                appCompatRatingBar = null;
                break;
        }
        if (appCompatRatingBar != null || context == tiVar) {
            view2 = appCompatRatingBar;
        } else {
            Object[] objArr = n7Var.a;
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = tiVar;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i2 = 0;
                    while (true) {
                        String[] strArr = n7.g;
                        if (i2 < 3) {
                            View viewF = n7Var.f(tiVar, str, strArr[i2]);
                            if (viewF != null) {
                                objArr[0] = null;
                                objArr[1] = null;
                                view3 = viewF;
                            } else {
                                i2++;
                            }
                        } else {
                            objArr[0] = null;
                            objArr[1] = null;
                        }
                    }
                } else {
                    View viewF2 = n7Var.f(tiVar, str, null);
                    objArr[0] = null;
                    objArr[1] = null;
                    view3 = viewF2;
                }
            } catch (Exception unused) {
                objArr[0] = null;
                objArr[1] = null;
            } catch (Throwable th2) {
                objArr[0] = null;
                objArr[1] = null;
                throw th2;
            }
            view2 = view3;
        }
        if (view2 != null) {
            Context context3 = view2.getContext();
            if (context3 instanceof ContextWrapper) {
                WeakHashMap weakHashMap = f51.a;
                if (view2.hasOnClickListeners()) {
                    TypedArray typedArrayObtainStyledAttributes2 = context3.obtainStyledAttributes(attributeSet, n7.c);
                    String string2 = typedArrayObtainStyledAttributes2.getString(0);
                    if (string2 != null) {
                        view2.setOnClickListener(new m7(view2, string2));
                    }
                    typedArrayObtainStyledAttributes2.recycle();
                }
            }
            if (Build.VERSION.SDK_INT <= 28) {
                TypedArray typedArrayObtainStyledAttributes3 = tiVar.obtainStyledAttributes(attributeSet, n7.d);
                if (typedArrayObtainStyledAttributes3.hasValue(0)) {
                    boolean z = typedArrayObtainStyledAttributes3.getBoolean(0, false);
                    WeakHashMap weakHashMap2 = f51.a;
                    new p41(R$id.tag_accessibility_heading, Boolean.class, 0, 28, 3).f(view2, Boolean.valueOf(z));
                }
                typedArrayObtainStyledAttributes3.recycle();
                TypedArray typedArrayObtainStyledAttributes4 = tiVar.obtainStyledAttributes(attributeSet, n7.e);
                if (typedArrayObtainStyledAttributes4.hasValue(0)) {
                    f51.p(view2, typedArrayObtainStyledAttributes4.getString(0));
                }
                typedArrayObtainStyledAttributes4.recycle();
                TypedArray typedArrayObtainStyledAttributes5 = tiVar.obtainStyledAttributes(attributeSet, n7.f);
                if (typedArrayObtainStyledAttributes5.hasValue(0)) {
                    boolean z2 = typedArrayObtainStyledAttributes5.getBoolean(0, false);
                    WeakHashMap weakHashMap3 = f51.a;
                    new p41(R$id.tag_screen_reader_focusable, Boolean.class, 0, 28, 0).f(view2, Boolean.valueOf(z2));
                }
                typedArrayObtainStyledAttributes5.recycle();
            }
        }
        return view2;
    }

    public final void q(int i, b6 b6Var, zd0 zd0Var) {
        if (zd0Var == null) {
            if (b6Var == null && i >= 0) {
                b6[] b6VarArr = this.p0;
                if (i < b6VarArr.length) {
                    b6Var = b6VarArr[i];
                }
            }
            if (b6Var != null) {
                zd0Var = b6Var.h;
            }
        }
        if ((b6Var == null || b6Var.m) && !this.u0) {
            w5 w5Var = this.Q;
            Window.Callback callback = this.P.getCallback();
            w5Var.getClass();
            try {
                w5Var.H = true;
                callback.onPanelClosed(i, zd0Var);
            } finally {
                w5Var.H = false;
            }
        }
    }

    public final void r(zd0 zd0Var) {
        b bVar;
        if (this.o0) {
            return;
        }
        this.o0 = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.V;
        actionBarOverlayLayout.k();
        ActionMenuView actionMenuView = ((l) actionBarOverlayLayout.I).a.E;
        if (actionMenuView != null && (bVar = actionMenuView.a0) != null) {
            bVar.d();
            f3 f3Var = bVar.Y;
            if (f3Var != null && f3Var.b()) {
                f3Var.i.dismiss();
            }
        }
        Window.Callback callback = this.P.getCallback();
        if (callback != null && !this.u0) {
            callback.onPanelClosed(108, zd0Var);
        }
        this.o0 = false;
    }

    public final void s(b6 b6Var, boolean z) {
        a6 a6Var;
        rm rmVar;
        b bVar;
        if (z && b6Var.a == 0 && (rmVar = this.V) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) rmVar;
            actionBarOverlayLayout.k();
            ActionMenuView actionMenuView = ((l) actionBarOverlayLayout.I).a.E;
            if (actionMenuView != null && (bVar = actionMenuView.a0) != null && bVar.h()) {
                r(b6Var.h);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.O.getSystemService("window");
        if (windowManager != null && b6Var.m && (a6Var = b6Var.e) != null) {
            windowManager.removeView(a6Var);
            if (z) {
                q(b6Var.a, b6Var, null);
            }
        }
        b6Var.k = false;
        b6Var.l = false;
        b6Var.m = false;
        b6Var.f = null;
        b6Var.n = true;
        if (this.q0 == b6Var) {
            this.q0 = null;
        }
        if (b6Var.a == 0) {
            I();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean u(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.c6.u(android.view.KeyEvent):boolean");
    }

    public final void v(int i) {
        b6 b6VarZ = z(i);
        if (b6VarZ.h != null) {
            Bundle bundle = new Bundle();
            b6VarZ.h.u(bundle);
            if (bundle.size() > 0) {
                b6VarZ.p = bundle;
            }
            b6VarZ.h.y();
            b6VarZ.h.clear();
        }
        b6VarZ.o = true;
        b6VarZ.n = true;
        if ((i == 108 || i == 0) && this.V != null) {
            b6 b6VarZ2 = z(0);
            b6VarZ2.k = false;
            G(b6VarZ2, null);
        }
    }

    public final void w() {
        ViewGroup viewGroup;
        if (this.d0) {
            return;
        }
        int[] iArr = R$styleable.AppCompatTheme;
        Context context = this.O;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            h(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBar, false)) {
            h(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            h(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            h(10);
        }
        this.m0 = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        x();
        this.P.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        if (this.n0) {
            viewGroup = this.l0 ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.m0) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.k0 = false;
            this.j0 = false;
        } else if (this.j0) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new ti(context, typedValue.resourceId) : context).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            rm rmVar = (rm) viewGroup.findViewById(androidx.appcompat.R$id.decor_content_parent);
            this.V = rmVar;
            rmVar.setWindowCallback(this.P.getCallback());
            if (this.k0) {
                ((ActionBarOverlayLayout) this.V).h(109);
            }
            if (this.h0) {
                ((ActionBarOverlayLayout) this.V).h(2);
            }
            if (this.i0) {
                ((ActionBarOverlayLayout) this.V).h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.j0 + ", windowActionBarOverlay: " + this.k0 + ", android:windowIsFloating: " + this.m0 + ", windowActionModeOverlay: " + this.l0 + ", windowNoTitle: " + this.n0 + " }");
        }
        q5 q5Var = new q5(this);
        WeakHashMap weakHashMap = f51.a;
        t41.u(viewGroup, q5Var);
        if (this.V == null) {
            this.f0 = (TextView) viewGroup.findViewById(androidx.appcompat.R$id.title);
        }
        Method method = q61.a;
        try {
            Method method2 = viewGroup.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
            if (!method2.isAccessible()) {
                method2.setAccessible(true);
            }
            method2.invoke(viewGroup, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e);
        } catch (NoSuchMethodException unused) {
            Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        } catch (InvocationTargetException e2) {
            Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", e2);
        }
        ContentFrameLayout contentFrameLayout = (ContentFrameLayout) viewGroup.findViewById(androidx.appcompat.R$id.action_bar_activity_content);
        ViewGroup viewGroup2 = (ViewGroup) this.P.findViewById(R.id.content);
        if (viewGroup2 != null) {
            while (viewGroup2.getChildCount() > 0) {
                View childAt = viewGroup2.getChildAt(0);
                viewGroup2.removeViewAt(0);
                contentFrameLayout.addView(childAt);
            }
            viewGroup2.setId(-1);
            contentFrameLayout.setId(R.id.content);
            if (viewGroup2 instanceof FrameLayout) {
                ((FrameLayout) viewGroup2).setForeground(null);
            }
        }
        this.P.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new r5(this));
        this.e0 = viewGroup;
        Object obj = this.N;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.U;
        if (!TextUtils.isEmpty(title)) {
            rm rmVar2 = this.V;
            if (rmVar2 != null) {
                rmVar2.setWindowTitle(title);
            } else {
                x71 x71Var = this.S;
                if (x71Var != null) {
                    l lVar = (l) x71Var.e;
                    if (!lVar.g) {
                        lVar.h = title;
                        if ((lVar.b & 8) != 0) {
                            Toolbar toolbar = lVar.a;
                            toolbar.setTitle(title);
                            if (lVar.g) {
                                f51.p(toolbar.getRootView(), title);
                            }
                        }
                    }
                } else {
                    TextView textView = this.f0;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.e0.findViewById(R.id.content);
        View decorView = this.P.getDecorView();
        contentFrameLayout2.K.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        WeakHashMap weakHashMap2 = f51.a;
        if (contentFrameLayout2.isLaidOut()) {
            contentFrameLayout2.requestLayout();
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(R$styleable.AppCompatTheme);
        typedArrayObtainStyledAttributes2.getValue(R$styleable.AppCompatTheme_windowMinWidthMajor, contentFrameLayout2.getMinWidthMajor());
        typedArrayObtainStyledAttributes2.getValue(R$styleable.AppCompatTheme_windowMinWidthMinor, contentFrameLayout2.getMinWidthMinor());
        if (typedArrayObtainStyledAttributes2.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMajor)) {
            typedArrayObtainStyledAttributes2.getValue(R$styleable.AppCompatTheme_windowFixedWidthMajor, contentFrameLayout2.getFixedWidthMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(R$styleable.AppCompatTheme_windowFixedWidthMinor)) {
            typedArrayObtainStyledAttributes2.getValue(R$styleable.AppCompatTheme_windowFixedWidthMinor, contentFrameLayout2.getFixedWidthMinor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMajor)) {
            typedArrayObtainStyledAttributes2.getValue(R$styleable.AppCompatTheme_windowFixedHeightMajor, contentFrameLayout2.getFixedHeightMajor());
        }
        if (typedArrayObtainStyledAttributes2.hasValue(R$styleable.AppCompatTheme_windowFixedHeightMinor)) {
            typedArrayObtainStyledAttributes2.getValue(R$styleable.AppCompatTheme_windowFixedHeightMinor, contentFrameLayout2.getFixedHeightMinor());
        }
        typedArrayObtainStyledAttributes2.recycle();
        contentFrameLayout2.requestLayout();
        this.d0 = true;
        b6 b6VarZ = z(0);
        if (this.u0 || b6VarZ.h != null) {
            return;
        }
        B(108);
    }

    public final void x() {
        if (this.P == null) {
            Object obj = this.N;
            if (obj instanceof Activity) {
                o(((Activity) obj).getWindow());
            }
        }
        if (this.P == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final z5 y(Context context) {
        if (this.A0 == null) {
            if (ou.L == null) {
                Context applicationContext = context.getApplicationContext();
                ou.L = new ou(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.A0 = new x5(this, ou.L);
        }
        return this.A0;
    }

    public final b6 z(int i) {
        b6[] b6VarArr = this.p0;
        if (b6VarArr == null || b6VarArr.length <= i) {
            b6[] b6VarArr2 = new b6[i + 1];
            if (b6VarArr != null) {
                System.arraycopy(b6VarArr, 0, b6VarArr2, 0, b6VarArr.length);
            }
            this.p0 = b6VarArr2;
            b6VarArr = b6VarArr2;
        }
        b6 b6Var = b6VarArr[i];
        if (b6Var != null) {
            return b6Var;
        }
        b6 b6Var2 = new b6();
        b6Var2.a = i;
        b6Var2.n = false;
        b6VarArr[i] = b6Var2;
        return b6Var2;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
