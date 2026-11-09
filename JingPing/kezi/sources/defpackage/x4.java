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
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class x4 extends k4 implements g60, LayoutInflater.Factory2 {
    public static final fj0 i0 = new fj0();
    public static final int[] j0 = {R.attr.windowBackground};
    public static final boolean k0 = !"robolectric".equals(Build.FINGERPRINT);
    public static final boolean l0 = true;
    public boolean A;
    public ViewGroup B;
    public TextView C;
    public View D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public w4[] M;
    public w4 N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public boolean R;
    public Configuration S;
    public final int T;
    public int U;
    public int V;
    public boolean W;
    public s4 X;
    public s4 Y;
    public boolean Z;
    public int a0;
    public boolean c0;
    public Rect d0;
    public Rect e0;
    public j6 f0;
    public OnBackInvokedDispatcher g0;
    public OnBackInvokedCallback h0;
    public final Object k;
    public final Context l;
    public Window m;
    public r4 n;
    public final Object o;
    public bu0 p;
    public ol0 q;
    public CharSequence r;
    public yi s;
    public m4 t;
    public m4 u;
    public q1 v;
    public ActionBarContextView w;
    public PopupWindow x;
    public l4 y;
    public bt0 z = null;
    public final l4 b0 = new l4(this, 0);

    public x4(Context context, Window window, f4 f4Var, Object obj) {
        AppCompatActivity appCompatActivity;
        this.T = -100;
        this.l = context;
        this.o = f4Var;
        this.k = obj;
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
                this.T = ((x4) appCompatActivity.j()).T;
            }
        }
        if (this.T == -100) {
            fj0 fj0Var = i0;
            Integer num = (Integer) fj0Var.getOrDefault(this.k.getClass().getName(), null);
            if (num != null) {
                this.T = num.intValue();
                fj0Var.remove(this.k.getClass().getName());
            }
        }
        if (window != null) {
            o(window);
        }
        a5.d();
    }

    public static f30 p(Context context) {
        f30 f30Var;
        f30 f30Var2;
        if (Build.VERSION.SDK_INT >= 33 || (f30Var = k4.d) == null) {
            return null;
        }
        f30 f30VarB = o4.b(context.getApplicationContext().getResources().getConfiguration());
        g30 g30Var = f30Var.a;
        if (g30Var.a.isEmpty()) {
            f30Var2 = f30.b;
        } else {
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            int i = 0;
            while (i < f30VarB.a.a.size() + g30Var.a.size()) {
                Locale locale = i < g30Var.a.size() ? g30Var.a.get(i) : f30VarB.a.a.get(i - g30Var.a.size());
                if (locale != null) {
                    linkedHashSet.add(locale);
                }
                i++;
            }
            f30Var2 = new f30(new g30(e30.a((Locale[]) linkedHashSet.toArray(new Locale[linkedHashSet.size()]))));
        }
        return f30Var2.a.a.isEmpty() ? f30VarB : f30Var2;
    }

    public static Configuration t(Context context, int i, f30 f30Var, Configuration configuration, boolean z) {
        int i2 = i != 1 ? i != 2 ? z ? 0 : context.getApplicationContext().getResources().getConfiguration().uiMode & 48 : 32 : 16;
        Configuration configuration2 = new Configuration();
        configuration2.fontScale = 0.0f;
        if (configuration != null) {
            configuration2.setTo(configuration);
        }
        configuration2.uiMode = i2 | (configuration2.uiMode & (-49));
        if (f30Var != null) {
            o4.d(configuration2, f30Var);
        }
        return configuration2;
    }

    public final void A() {
        w();
        if (this.G && this.p == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                this.p = new bu0((Activity) obj, this.H);
            } else if (obj instanceof Dialog) {
                this.p = new bu0((Dialog) obj);
            }
            bu0 bu0Var = this.p;
            if (bu0Var != null) {
                bu0Var.d(this.c0);
            }
        }
    }

    public final void B(int i) {
        this.a0 = (1 << i) | this.a0;
        if (this.Z) {
            return;
        }
        View decorView = this.m.getDecorView();
        l4 l4Var = this.b0;
        WeakHashMap weakHashMap = xr0.a;
        decorView.postOnAnimation(l4Var);
        this.Z = true;
    }

    public final int C(Context context, int i) {
        if (i != -100) {
            if (i != -1) {
                if (i != 0) {
                    if (i != 1 && i != 2) {
                        if (i != 3) {
                            throw new IllegalStateException("Unknown value set for night mode. Please use one of the MODE_NIGHT values from AppCompatDelegate.");
                        }
                        if (this.Y == null) {
                            this.Y = new s4(this, context);
                        }
                        return this.Y.f();
                    }
                } else if (((UiModeManager) context.getApplicationContext().getSystemService("uimode")).getNightMode() != 0) {
                    return y(context).f();
                }
            }
            return i;
        }
        return -1;
    }

    public final boolean D() {
        zi ziVar;
        j jVar;
        boolean z = this.O;
        this.O = false;
        w4 w4VarZ = z(0);
        if (!w4VarZ.m) {
            q1 q1Var = this.v;
            if (q1Var != null) {
                q1Var.a();
                return true;
            }
            A();
            bu0 bu0Var = this.p;
            if (bu0Var == null || (ziVar = bu0Var.e) == null || (jVar = ((l) ziVar).a.M) == null || jVar.c == null) {
                return false;
            }
            j jVar2 = ((l) ziVar).a.M;
            m60 m60Var = jVar2 == null ? null : jVar2.c;
            if (m60Var != null) {
                m60Var.collapseActionView();
            }
        } else if (!z) {
            s(w4VarZ, true);
            return true;
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:87:0x0176, code lost:
    
        if (r3.h.getCount() > 0) goto L88;
     */
    /* JADX WARN: Removed duplicated region for block: B:100:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:105:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void E(defpackage.w4 r18, android.view.KeyEvent r19) {
        /*
            Method dump skipped, instruction units count: 474
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x4.E(w4, android.view.KeyEvent):void");
    }

    public final boolean F(w4 w4Var, int i, KeyEvent keyEvent) {
        i60 i60Var;
        if (keyEvent.isSystem()) {
            return false;
        }
        if ((w4Var.k || G(w4Var, keyEvent)) && (i60Var = w4Var.h) != null) {
            return i60Var.performShortcut(i, keyEvent, 1);
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:59:0x00d0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean G(defpackage.w4 r12, android.view.KeyEvent r13) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x4.G(w4, android.view.KeyEvent):boolean");
    }

    public final void H() {
        if (this.A) {
            throw new AndroidRuntimeException("Window feature must be requested before adding content");
        }
    }

    public final void I() {
        OnBackInvokedCallback onBackInvokedCallback;
        if (Build.VERSION.SDK_INT >= 33) {
            boolean z = false;
            if (this.g0 != null && (z(0).m || this.v != null)) {
                z = true;
            }
            if (z && this.h0 == null) {
                this.h0 = q4.b(this.g0, this);
            } else {
                if (z || (onBackInvokedCallback = this.h0) == null) {
                    return;
                }
                q4.c(this.g0, onBackInvokedCallback);
            }
        }
    }

    @Override // defpackage.k4
    public final void a() {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.l);
        if (layoutInflaterFrom.getFactory() == null) {
            layoutInflaterFrom.setFactory2(this);
        } else {
            if (layoutInflaterFrom.getFactory2() instanceof x4) {
                return;
            }
            Log.i("AppCompatDelegate", "The Activity's LayoutInflater already has a Factory installed so we can not install AppCompat's");
        }
    }

    @Override // defpackage.k4
    public final void b() {
        if (this.p != null) {
            A();
            this.p.getClass();
            B(0);
        }
    }

    @Override // defpackage.k4
    public final void d() {
        String strA;
        this.P = true;
        n(false, true);
        x();
        Object obj = this.k;
        if (obj instanceof Activity) {
            try {
                Activity activity = (Activity) obj;
                try {
                    strA = oy.A(activity, activity.getComponentName());
                } catch (PackageManager.NameNotFoundException e) {
                    throw new IllegalArgumentException(e);
                }
            } catch (IllegalArgumentException unused) {
                strA = null;
            }
            if (strA != null) {
                bu0 bu0Var = this.p;
                if (bu0Var == null) {
                    this.c0 = true;
                } else {
                    bu0Var.d(true);
                }
            }
            synchronized (k4.i) {
                k4.f(this);
                k4.h.add(new WeakReference(this));
            }
        }
        this.S = new Configuration(this.l.getResources().getConfiguration());
        this.Q = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x004d  */
    @Override // defpackage.k4
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void e() {
        /*
            r3 = this;
            java.lang.Object r0 = r3.k
            boolean r0 = r0 instanceof android.app.Activity
            if (r0 == 0) goto L11
            java.lang.Object r0 = defpackage.k4.i
            monitor-enter(r0)
            defpackage.k4.f(r3)     // Catch: java.lang.Throwable -> Le
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            goto L11
        Le:
            r1 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Le
            throw r1
        L11:
            boolean r0 = r3.Z
            if (r0 == 0) goto L20
            android.view.Window r0 = r3.m
            android.view.View r0 = r0.getDecorView()
            l4 r1 = r3.b0
            r0.removeCallbacks(r1)
        L20:
            r0 = 1
            r3.R = r0
            int r0 = r3.T
            r1 = -100
            if (r0 == r1) goto L4d
            java.lang.Object r0 = r3.k
            boolean r1 = r0 instanceof android.app.Activity
            if (r1 == 0) goto L4d
            android.app.Activity r0 = (android.app.Activity) r0
            boolean r0 = r0.isChangingConfigurations()
            if (r0 == 0) goto L4d
            fj0 r0 = defpackage.x4.i0
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            int r2 = r3.T
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r0.put(r1, r2)
            goto L5c
        L4d:
            fj0 r0 = defpackage.x4.i0
            java.lang.Object r1 = r3.k
            java.lang.Class r1 = r1.getClass()
            java.lang.String r1 = r1.getName()
            r0.remove(r1)
        L5c:
            s4 r0 = r3.X
            if (r0 == 0) goto L63
            r0.c()
        L63:
            s4 r0 = r3.Y
            if (r0 == 0) goto L6a
            r0.c()
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x4.e():void");
    }

    @Override // defpackage.k4
    public final boolean g(int i) {
        if (i == 8) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR id when requesting this feature.");
            i = 108;
        } else if (i == 9) {
            Log.i("AppCompatDelegate", "You should now use the AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY id when requesting this feature.");
            i = 109;
        }
        if (this.K && i == 108) {
            return false;
        }
        if (this.G && i == 1) {
            this.G = false;
        }
        if (i == 1) {
            H();
            this.K = true;
            return true;
        }
        if (i == 2) {
            H();
            this.E = true;
            return true;
        }
        if (i == 5) {
            H();
            this.F = true;
            return true;
        }
        if (i == 10) {
            H();
            this.I = true;
            return true;
        }
        if (i == 108) {
            H();
            this.G = true;
            return true;
        }
        if (i != 109) {
            return this.m.requestFeature(i);
        }
        H();
        this.H = true;
        return true;
    }

    @Override // defpackage.k4
    public final void h(int i) {
        w();
        ViewGroup viewGroup = (ViewGroup) this.B.findViewById(R.id.content);
        viewGroup.removeAllViews();
        LayoutInflater.from(this.l).inflate(i, viewGroup);
        this.n.a(this.m.getCallback());
    }

    /* JADX WARN: Code restructure failed: missing block: B:19:0x0048, code lost:
    
        if (r6.j() != false) goto L20;
     */
    @Override // defpackage.g60
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(defpackage.i60 r6) {
        /*
            Method dump skipped, instruction units count: 246
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x4.i(i60):void");
    }

    @Override // defpackage.k4
    public final void j(View view) {
        w();
        ViewGroup viewGroup = (ViewGroup) this.B.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view);
        this.n.a(this.m.getCallback());
    }

    @Override // defpackage.k4
    public final void k(View view, ViewGroup.LayoutParams layoutParams) {
        w();
        ViewGroup viewGroup = (ViewGroup) this.B.findViewById(R.id.content);
        viewGroup.removeAllViews();
        viewGroup.addView(view, layoutParams);
        this.n.a(this.m.getCallback());
    }

    @Override // defpackage.k4
    public final void l(CharSequence charSequence) {
        this.r = charSequence;
        yi yiVar = this.s;
        if (yiVar != null) {
            yiVar.setWindowTitle(charSequence);
            return;
        }
        bu0 bu0Var = this.p;
        if (bu0Var == null) {
            TextView textView = this.C;
            if (textView != null) {
                textView.setText(charSequence);
                return;
            }
            return;
        }
        l lVar = (l) bu0Var.e;
        if (lVar.g) {
            return;
        }
        lVar.h = charSequence;
        if ((lVar.b & 8) != 0) {
            Toolbar toolbar = lVar.a;
            toolbar.setTitle(charSequence);
            if (lVar.g) {
                xr0.q(toolbar.getRootView(), charSequence);
            }
        }
    }

    @Override // defpackage.g60
    public final boolean m(i60 i60Var, MenuItem menuItem) {
        w4 w4Var;
        Window.Callback callback = this.m.getCallback();
        if (callback != null && !this.R) {
            i60 i60VarK = i60Var.k();
            w4[] w4VarArr = this.M;
            int length = w4VarArr != null ? w4VarArr.length : 0;
            int i = 0;
            while (true) {
                if (i < length) {
                    w4Var = w4VarArr[i];
                    if (w4Var != null && w4Var.h == i60VarK) {
                        break;
                    }
                    i++;
                } else {
                    w4Var = null;
                    break;
                }
            }
            if (w4Var != null) {
                return callback.onMenuItemSelected(w4Var.a, menuItem);
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00e1  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean n(boolean r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 595
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x4.n(boolean, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(android.view.Window r4) {
        /*
            r3 = this;
            android.view.Window r0 = r3.m
            java.lang.String r1 = "AppCompat has already installed itself into the Window"
            if (r0 != 0) goto L66
            android.view.Window$Callback r0 = r4.getCallback()
            boolean r2 = r0 instanceof defpackage.r4
            if (r2 != 0) goto L60
            r4 r1 = new r4
            r1.<init>(r3, r0)
            r3.n = r1
            r4.setCallback(r1)
            int[] r0 = defpackage.x4.j0
            android.content.Context r1 = r3.l
            r2 = 0
            po r0 = defpackage.po.M(r1, r2, r0)
            r1 = 0
            android.graphics.drawable.Drawable r1 = r0.z(r1)
            if (r1 == 0) goto L2b
            r4.setBackgroundDrawable(r1)
        L2b:
            r0.P()
            r3.m = r4
            int r4 = android.os.Build.VERSION.SDK_INT
            r0 = 33
            if (r4 < r0) goto L5f
            android.window.OnBackInvokedDispatcher r4 = r3.g0
            if (r4 != 0) goto L5f
            if (r4 == 0) goto L45
            android.window.OnBackInvokedCallback r0 = r3.h0
            if (r0 == 0) goto L45
            defpackage.q4.c(r4, r0)
            r3.h0 = r2
        L45:
            java.lang.Object r4 = r3.k
            boolean r0 = r4 instanceof android.app.Activity
            if (r0 == 0) goto L5a
            android.app.Activity r4 = (android.app.Activity) r4
            android.view.Window r0 = r4.getWindow()
            if (r0 == 0) goto L5a
            android.window.OnBackInvokedDispatcher r4 = defpackage.q4.a(r4)
            r3.g0 = r4
            goto L5c
        L5a:
            r3.g0 = r2
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
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x4.o(android.view.Window):void");
    }

    @Override // android.view.LayoutInflater.Factory2
    public final View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
        j6 j6Var;
        Context ehVar;
        View appCompatRatingBar;
        View view2;
        if (this.f0 == null) {
            int[] iArr = R$styleable.AppCompatTheme;
            Context context2 = this.l;
            String string = context2.obtainStyledAttributes(iArr).getString(R$styleable.AppCompatTheme_viewInflaterClass);
            if (string == null) {
                this.f0 = new j6();
            } else {
                try {
                    this.f0 = (j6) context2.getClassLoader().loadClass(string).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Throwable th) {
                    Log.i("AppCompatDelegate", "Failed to instantiate custom view inflater " + string + ". Falling back to default.", th);
                    this.f0 = new j6();
                }
            }
        }
        j6Var = this.f0;
        int i = wq0.a;
        j6Var.getClass();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.View, 0, 0);
        int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.View_theme, 0);
        if (resourceId != 0) {
            Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        ehVar = (resourceId == 0 || ((context instanceof eh) && ((eh) context).a == resourceId)) ? context : new eh(context, resourceId);
        str.getClass();
        View view3 = null;
        switch (str) {
            case "RatingBar":
                appCompatRatingBar = new AppCompatRatingBar(ehVar, attributeSet);
                break;
            case "CheckedTextView":
                appCompatRatingBar = new AppCompatCheckedTextView(ehVar, attributeSet);
                break;
            case "MultiAutoCompleteTextView":
                appCompatRatingBar = new AppCompatMultiAutoCompleteTextView(ehVar, attributeSet);
                break;
            case "TextView":
                appCompatRatingBar = j6Var.e(ehVar, attributeSet);
                break;
            case "ImageButton":
                appCompatRatingBar = new AppCompatImageButton(ehVar, attributeSet);
                break;
            case "SeekBar":
                appCompatRatingBar = new AppCompatSeekBar(ehVar, attributeSet);
                break;
            case "Spinner":
                appCompatRatingBar = new AppCompatSpinner(ehVar, attributeSet);
                break;
            case "RadioButton":
                appCompatRatingBar = j6Var.d(ehVar, attributeSet);
                break;
            case "ToggleButton":
                appCompatRatingBar = new AppCompatToggleButton(ehVar, attributeSet);
                break;
            case "ImageView":
                appCompatRatingBar = new AppCompatImageView(ehVar, attributeSet);
                break;
            case "AutoCompleteTextView":
                appCompatRatingBar = j6Var.a(ehVar, attributeSet);
                break;
            case "CheckBox":
                appCompatRatingBar = j6Var.c(ehVar, attributeSet);
                break;
            case "EditText":
                appCompatRatingBar = new AppCompatEditText(ehVar, attributeSet);
                break;
            case "Button":
                appCompatRatingBar = j6Var.b(ehVar, attributeSet);
                break;
            default:
                appCompatRatingBar = null;
                break;
        }
        if (appCompatRatingBar != null || context == ehVar) {
            view2 = appCompatRatingBar;
        } else {
            Object[] objArr = j6Var.a;
            if (str.equals("view")) {
                str = attributeSet.getAttributeValue(null, "class");
            }
            try {
                objArr[0] = ehVar;
                objArr[1] = attributeSet;
                if (-1 == str.indexOf(46)) {
                    int i2 = 0;
                    while (true) {
                        String[] strArr = j6.g;
                        if (i2 < 3) {
                            View viewF = j6Var.f(ehVar, str, strArr[i2]);
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
                    View viewF2 = j6Var.f(ehVar, str, null);
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
                WeakHashMap weakHashMap = xr0.a;
                if (view2.hasOnClickListeners()) {
                    TypedArray typedArrayObtainStyledAttributes2 = context3.obtainStyledAttributes(attributeSet, j6.c);
                    String string2 = typedArrayObtainStyledAttributes2.getString(0);
                    if (string2 != null) {
                        view2.setOnClickListener(new i6(view2, string2));
                    }
                    typedArrayObtainStyledAttributes2.recycle();
                }
            }
            if (Build.VERSION.SDK_INT <= 28) {
                TypedArray typedArrayObtainStyledAttributes3 = ehVar.obtainStyledAttributes(attributeSet, j6.d);
                if (typedArrayObtainStyledAttributes3.hasValue(0)) {
                    boolean z = typedArrayObtainStyledAttributes3.getBoolean(0, false);
                    WeakHashMap weakHashMap2 = xr0.a;
                    new hr0(R$id.tag_accessibility_heading, Boolean.class, 0, 28, 3).f(view2, Boolean.valueOf(z));
                }
                typedArrayObtainStyledAttributes3.recycle();
                TypedArray typedArrayObtainStyledAttributes4 = ehVar.obtainStyledAttributes(attributeSet, j6.e);
                if (typedArrayObtainStyledAttributes4.hasValue(0)) {
                    xr0.q(view2, typedArrayObtainStyledAttributes4.getString(0));
                }
                typedArrayObtainStyledAttributes4.recycle();
                TypedArray typedArrayObtainStyledAttributes5 = ehVar.obtainStyledAttributes(attributeSet, j6.f);
                if (typedArrayObtainStyledAttributes5.hasValue(0)) {
                    boolean z2 = typedArrayObtainStyledAttributes5.getBoolean(0, false);
                    WeakHashMap weakHashMap3 = xr0.a;
                    new hr0(R$id.tag_screen_reader_focusable, Boolean.class, 0, 28, 0).f(view2, Boolean.valueOf(z2));
                }
                typedArrayObtainStyledAttributes5.recycle();
            }
        }
        return view2;
    }

    public final void q(int i, w4 w4Var, i60 i60Var) {
        if (i60Var == null) {
            if (w4Var == null && i >= 0) {
                w4[] w4VarArr = this.M;
                if (i < w4VarArr.length) {
                    w4Var = w4VarArr[i];
                }
            }
            if (w4Var != null) {
                i60Var = w4Var.h;
            }
        }
        if ((w4Var == null || w4Var.m) && !this.R) {
            r4 r4Var = this.n;
            Window.Callback callback = this.m.getCallback();
            r4Var.getClass();
            try {
                r4Var.e = true;
                callback.onPanelClosed(i, i60Var);
            } finally {
                r4Var.e = false;
            }
        }
    }

    public final void r(i60 i60Var) {
        b bVar;
        if (this.L) {
            return;
        }
        this.L = true;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) this.s;
        actionBarOverlayLayout.k();
        ActionMenuView actionMenuView = ((l) actionBarOverlayLayout.f).a.b;
        if (actionMenuView != null && (bVar = actionMenuView.u) != null) {
            bVar.f();
            k1 k1Var = bVar.v;
            if (k1Var != null && k1Var.b()) {
                k1Var.i.dismiss();
            }
        }
        Window.Callback callback = this.m.getCallback();
        if (callback != null && !this.R) {
            callback.onPanelClosed(108, i60Var);
        }
        this.L = false;
    }

    public final void s(w4 w4Var, boolean z) {
        v4 v4Var;
        yi yiVar;
        b bVar;
        if (z && w4Var.a == 0 && (yiVar = this.s) != null) {
            ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) yiVar;
            actionBarOverlayLayout.k();
            ActionMenuView actionMenuView = ((l) actionBarOverlayLayout.f).a.b;
            if (actionMenuView != null && (bVar = actionMenuView.u) != null && bVar.j()) {
                r(w4Var.h);
                return;
            }
        }
        WindowManager windowManager = (WindowManager) this.l.getSystemService("window");
        if (windowManager != null && w4Var.m && (v4Var = w4Var.e) != null) {
            windowManager.removeView(v4Var);
            if (z) {
                q(w4Var.a, w4Var, null);
            }
        }
        w4Var.k = false;
        w4Var.l = false;
        w4Var.m = false;
        w4Var.f = null;
        w4Var.n = true;
        if (this.N == w4Var) {
            this.N = null;
        }
        if (w4Var.a == 0) {
            I();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0105  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0123  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean u(android.view.KeyEvent r7) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.x4.u(android.view.KeyEvent):boolean");
    }

    public final void v(int i) {
        w4 w4VarZ = z(i);
        if (w4VarZ.h != null) {
            Bundle bundle = new Bundle();
            w4VarZ.h.u(bundle);
            if (bundle.size() > 0) {
                w4VarZ.p = bundle;
            }
            w4VarZ.h.y();
            w4VarZ.h.clear();
        }
        w4VarZ.o = true;
        w4VarZ.n = true;
        if ((i == 108 || i == 0) && this.s != null) {
            w4 w4VarZ2 = z(0);
            w4VarZ2.k = false;
            G(w4VarZ2, null);
        }
    }

    public final void w() {
        ViewGroup viewGroup;
        int i = 1;
        int i2 = 0;
        if (this.A) {
            return;
        }
        int[] iArr = R$styleable.AppCompatTheme;
        Context context = this.l;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        if (!typedArrayObtainStyledAttributes.hasValue(R$styleable.AppCompatTheme_windowActionBar)) {
            typedArrayObtainStyledAttributes.recycle();
            throw new IllegalStateException("You need to use a Theme.AppCompat theme (or descendant) with this activity.");
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowNoTitle, false)) {
            g(1);
        } else if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBar, false)) {
            g(108);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionBarOverlay, false)) {
            g(109);
        }
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_windowActionModeOverlay, false)) {
            g(10);
        }
        this.J = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AppCompatTheme_android_windowIsFloating, false);
        typedArrayObtainStyledAttributes.recycle();
        x();
        this.m.getDecorView();
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        if (this.K) {
            viewGroup = this.I ? (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple_overlay_action_mode, (ViewGroup) null) : (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_screen_simple, (ViewGroup) null);
        } else if (this.J) {
            viewGroup = (ViewGroup) layoutInflaterFrom.inflate(R$layout.abc_dialog_title_material, (ViewGroup) null);
            this.H = false;
            this.G = false;
        } else if (this.G) {
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(R$attr.actionBarTheme, typedValue, true);
            viewGroup = (ViewGroup) LayoutInflater.from(typedValue.resourceId != 0 ? new eh(context, typedValue.resourceId) : context).inflate(R$layout.abc_screen_toolbar, (ViewGroup) null);
            yi yiVar = (yi) viewGroup.findViewById(androidx.appcompat.R$id.decor_content_parent);
            this.s = yiVar;
            yiVar.setWindowCallback(this.m.getCallback());
            if (this.H) {
                ((ActionBarOverlayLayout) this.s).h(109);
            }
            if (this.E) {
                ((ActionBarOverlayLayout) this.s).h(2);
            }
            if (this.F) {
                ((ActionBarOverlayLayout) this.s).h(5);
            }
        } else {
            viewGroup = null;
        }
        if (viewGroup == null) {
            throw new IllegalArgumentException("AppCompat does not support the current theme features: { windowActionBar: " + this.G + ", windowActionBarOverlay: " + this.H + ", android:windowIsFloating: " + this.J + ", windowActionModeOverlay: " + this.I + ", windowNoTitle: " + this.K + " }");
        }
        m4 m4Var = new m4(this, i2);
        WeakHashMap weakHashMap = xr0.a;
        lr0.u(viewGroup, m4Var);
        if (this.s == null) {
            this.C = (TextView) viewGroup.findViewById(androidx.appcompat.R$id.title);
        }
        Method method = kt0.a;
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
        ViewGroup viewGroup2 = (ViewGroup) this.m.findViewById(R.id.content);
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
        this.m.setContentView(viewGroup);
        contentFrameLayout.setAttachListener(new m4(this, i));
        this.B = viewGroup;
        Object obj = this.k;
        CharSequence title = obj instanceof Activity ? ((Activity) obj).getTitle() : this.r;
        if (!TextUtils.isEmpty(title)) {
            yi yiVar2 = this.s;
            if (yiVar2 != null) {
                yiVar2.setWindowTitle(title);
            } else {
                bu0 bu0Var = this.p;
                if (bu0Var != null) {
                    l lVar = (l) bu0Var.e;
                    if (!lVar.g) {
                        lVar.h = title;
                        if ((lVar.b & 8) != 0) {
                            Toolbar toolbar = lVar.a;
                            toolbar.setTitle(title);
                            if (lVar.g) {
                                xr0.q(toolbar.getRootView(), title);
                            }
                        }
                    }
                } else {
                    TextView textView = this.C;
                    if (textView != null) {
                        textView.setText(title);
                    }
                }
            }
        }
        ContentFrameLayout contentFrameLayout2 = (ContentFrameLayout) this.B.findViewById(R.id.content);
        View decorView = this.m.getDecorView();
        contentFrameLayout2.h.set(decorView.getPaddingLeft(), decorView.getPaddingTop(), decorView.getPaddingRight(), decorView.getPaddingBottom());
        WeakHashMap weakHashMap2 = xr0.a;
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
        this.A = true;
        w4 w4VarZ = z(0);
        if (this.R || w4VarZ.h != null) {
            return;
        }
        B(108);
    }

    public final void x() {
        if (this.m == null) {
            Object obj = this.k;
            if (obj instanceof Activity) {
                o(((Activity) obj).getWindow());
            }
        }
        if (this.m == null) {
            throw new IllegalStateException("We have not been given a Window");
        }
    }

    public final u4 y(Context context) {
        if (this.X == null) {
            if (po.i == null) {
                Context applicationContext = context.getApplicationContext();
                po.i = new po(applicationContext, (LocationManager) applicationContext.getSystemService("location"));
            }
            this.X = new s4(this, po.i);
        }
        return this.X;
    }

    public final w4 z(int i) {
        w4[] w4VarArr = this.M;
        if (w4VarArr == null || w4VarArr.length <= i) {
            w4[] w4VarArr2 = new w4[i + 1];
            if (w4VarArr != null) {
                System.arraycopy(w4VarArr, 0, w4VarArr2, 0, w4VarArr.length);
            }
            this.M = w4VarArr2;
            w4VarArr = w4VarArr2;
        }
        w4 w4Var = w4VarArr[i];
        if (w4Var != null) {
            return w4Var;
        }
        w4 w4Var2 = new w4();
        w4Var2.a = i;
        w4Var2.n = false;
        w4VarArr[i] = w4Var2;
        return w4Var2;
    }

    @Override // android.view.LayoutInflater.Factory
    public final View onCreateView(String str, Context context, AttributeSet attributeSet) {
        return onCreateView(null, str, context, attributeSet);
    }
}
