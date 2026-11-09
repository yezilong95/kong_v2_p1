package defpackage;

import android.R;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.window.OnBackInvokedDispatcher;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Lifecycle$Event;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class x2 extends Dialog implements DialogInterface, f4, u10, vg0 {
    public w10 b;
    public final e5 c;
    public final qa0 d;
    public x4 e;
    public final y4 f;
    public final v2 g;

    /* JADX WARN: Type inference failed for: r1v4, types: [y4] */
    public x2(ContextThemeWrapper contextThemeWrapper, int i) {
        int i2;
        int iG = g(contextThemeWrapper, i);
        if (iG == 0) {
            TypedValue typedValue = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue, true);
            i2 = typedValue.resourceId;
        } else {
            i2 = iG;
        }
        super(contextThemeWrapper, i2);
        this.c = new e5(new ug0(this, new we(this, 2)));
        this.d = new qa0(new z1(4, this));
        this.f = new x00() { // from class: y4
            @Override // defpackage.x00
            public final boolean c(KeyEvent keyEvent) {
                return this.b.i(keyEvent);
            }
        };
        k4 k4VarC = c();
        if (iG == 0) {
            TypedValue typedValue2 = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue2, true);
            iG = typedValue2.resourceId;
        }
        ((x4) k4VarC).U = iG;
        k4VarC.d();
        this.g = new v2(getContext(), this, getWindow());
    }

    public static void a(x2 x2Var) {
        oy.j(x2Var, "this$0");
        super.onBackPressed();
    }

    public static int g(Context context, int i) {
        if (((i >>> 24) & 255) >= 1) {
            return i;
        }
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R$attr.alertDialogTheme, typedValue, true);
        return typedValue.resourceId;
    }

    @Override // android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        x4 x4Var = (x4) c();
        x4Var.w();
        ((ViewGroup) x4Var.B.findViewById(R.id.content)).addView(view, layoutParams);
        x4Var.n.a(x4Var.m.getCallback());
    }

    @Override // defpackage.vg0
    public final e5 b() {
        return (e5) this.c.d;
    }

    public final k4 c() {
        if (this.e == null) {
            m6 m6Var = k4.b;
            this.e = new x4(getContext(), getWindow(), this, this);
        }
        return this.e;
    }

    public final void d(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            oy.i(onBackInvokedDispatcher, "onBackInvokedDispatcher");
            qa0 qa0Var = this.d;
            qa0Var.getClass();
            qa0Var.e = onBackInvokedDispatcher;
            qa0Var.b(qa0Var.g);
        }
        this.c.J(bundle);
        w10 w10Var = this.b;
        if (w10Var == null) {
            w10Var = new w10(this);
            this.b = w10Var;
        }
        w10Var.e(Lifecycle$Event.ON_CREATE);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
        c().e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return xi.p(this.f, getWindow().getDecorView(), this, keyEvent);
    }

    public final void e(Bundle bundle) {
        c().a();
        d(bundle);
        c().d();
    }

    public final void f() {
        w10 w10Var = this.b;
        if (w10Var == null) {
            w10Var = new w10(this);
            this.b = w10Var;
        }
        w10Var.e(Lifecycle$Event.ON_DESTROY);
        this.b = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public final View findViewById(int i) {
        x4 x4Var = (x4) c();
        x4Var.w();
        return x4Var.m.findViewById(i);
    }

    @Override // defpackage.u10
    public final p10 getLifecycle() {
        w10 w10Var = this.b;
        if (w10Var != null) {
            return w10Var;
        }
        w10 w10Var2 = new w10(this);
        this.b = w10Var2;
        return w10Var2;
    }

    public final void h(CharSequence charSequence) {
        super.setTitle(charSequence);
        c().l(charSequence);
    }

    public final boolean i(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // android.app.Dialog
    public final void invalidateOptionsMenu() {
        c().b();
    }

    @Override // android.app.Dialog
    public final void onBackPressed() {
        this.d.a();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i;
        ListAdapter listAdapter;
        View viewFindViewById;
        e(bundle);
        v2 v2Var = this.g;
        v2Var.b.setContentView(v2Var.q);
        int i2 = R$id.parentPanel;
        Window window = v2Var.c;
        View viewFindViewById2 = window.findViewById(i2);
        View viewFindViewById3 = viewFindViewById2.findViewById(R$id.topPanel);
        View viewFindViewById4 = viewFindViewById2.findViewById(R$id.contentPanel);
        View viewFindViewById5 = viewFindViewById2.findViewById(R$id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(R$id.customPanel);
        window.setFlags(131072, 131072);
        viewGroup.setVisibility(8);
        View viewFindViewById6 = viewGroup.findViewById(R$id.topPanel);
        View viewFindViewById7 = viewGroup.findViewById(R$id.contentPanel);
        View viewFindViewById8 = viewGroup.findViewById(R$id.buttonPanel);
        ViewGroup viewGroupA = v2.a(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupA2 = v2.a(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupA3 = v2.a(viewFindViewById8, viewFindViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(R$id.scrollView);
        v2Var.i = nestedScrollView;
        nestedScrollView.setFocusable(false);
        v2Var.i.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupA2.findViewById(R.id.message);
        v2Var.m = textView;
        if (textView != null) {
            textView.setVisibility(8);
            v2Var.i.removeView(v2Var.m);
            if (v2Var.e != null) {
                ViewGroup viewGroup2 = (ViewGroup) v2Var.i.getParent();
                int iIndexOfChild = viewGroup2.indexOfChild(v2Var.i);
                viewGroup2.removeViewAt(iIndexOfChild);
                viewGroup2.addView(v2Var.e, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
            } else {
                viewGroupA2.setVisibility(8);
            }
        }
        Button button = (Button) viewGroupA3.findViewById(R.id.button1);
        v2Var.f = button;
        c1 c1Var = v2Var.w;
        button.setOnClickListener(c1Var);
        if (TextUtils.isEmpty(null)) {
            v2Var.f.setVisibility(8);
            i = 0;
        } else {
            v2Var.f.setText((CharSequence) null);
            v2Var.f.setVisibility(0);
            i = 1;
        }
        Button button2 = (Button) viewGroupA3.findViewById(R.id.button2);
        v2Var.g = button2;
        button2.setOnClickListener(c1Var);
        if (TextUtils.isEmpty(null)) {
            v2Var.g.setVisibility(8);
        } else {
            v2Var.g.setText((CharSequence) null);
            v2Var.g.setVisibility(0);
            i |= 2;
        }
        Button button3 = (Button) viewGroupA3.findViewById(R.id.button3);
        v2Var.h = button3;
        button3.setOnClickListener(c1Var);
        if (TextUtils.isEmpty(null)) {
            v2Var.h.setVisibility(8);
        } else {
            v2Var.h.setText((CharSequence) null);
            v2Var.h.setVisibility(0);
            i |= 4;
        }
        TypedValue typedValue = new TypedValue();
        v2Var.a.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i == 1) {
                Button button4 = v2Var.f;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i == 2) {
                Button button5 = v2Var.g;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i == 4) {
                Button button6 = v2Var.h;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i == 0) {
            viewGroupA3.setVisibility(8);
        }
        if (v2Var.n != null) {
            viewGroupA.addView(v2Var.n, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(R$id.title_template).setVisibility(8);
        } else {
            v2Var.k = (ImageView) window.findViewById(R.id.icon);
            if (TextUtils.isEmpty(v2Var.d) || !v2Var.u) {
                window.findViewById(R$id.title_template).setVisibility(8);
                v2Var.k.setVisibility(8);
                viewGroupA.setVisibility(8);
            } else {
                TextView textView2 = (TextView) window.findViewById(R$id.alertTitle);
                v2Var.l = textView2;
                textView2.setText(v2Var.d);
                Drawable drawable = v2Var.j;
                if (drawable != null) {
                    v2Var.k.setImageDrawable(drawable);
                } else {
                    v2Var.l.setPadding(v2Var.k.getPaddingLeft(), v2Var.k.getPaddingTop(), v2Var.k.getPaddingRight(), v2Var.k.getPaddingBottom());
                    v2Var.k.setVisibility(8);
                }
            }
        }
        boolean z = viewGroup.getVisibility() != 8;
        int i3 = (viewGroupA == null || viewGroupA.getVisibility() == 8) ? 0 : 1;
        boolean z2 = viewGroupA3.getVisibility() != 8;
        if (!z2 && (viewFindViewById = viewGroupA2.findViewById(R$id.textSpacerNoButtons)) != null) {
            viewFindViewById.setVisibility(0);
        }
        if (i3 != 0) {
            NestedScrollView nestedScrollView2 = v2Var.i;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View viewFindViewById9 = v2Var.e != null ? viewGroupA.findViewById(R$id.titleDividerNoCustom) : null;
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
        } else {
            View viewFindViewById10 = viewGroupA2.findViewById(R$id.textSpacerNoTitle);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        }
        AlertController$RecycleListView alertController$RecycleListView = v2Var.e;
        if (alertController$RecycleListView != null && (!z2 || i3 == 0)) {
            alertController$RecycleListView.setPadding(alertController$RecycleListView.getPaddingLeft(), i3 != 0 ? alertController$RecycleListView.getPaddingTop() : alertController$RecycleListView.b, alertController$RecycleListView.getPaddingRight(), z2 ? alertController$RecycleListView.getPaddingBottom() : alertController$RecycleListView.c);
        }
        if (!z) {
            View view = v2Var.e;
            if (view == null) {
                view = v2Var.i;
            }
            if (view != null) {
                int i4 = (z2 ? 2 : 0) | i3;
                View viewFindViewById11 = window.findViewById(R$id.scrollIndicatorUp);
                View viewFindViewById12 = window.findViewById(R$id.scrollIndicatorDown);
                WeakHashMap weakHashMap = xr0.a;
                mr0.d(view, i4, 3);
                if (viewFindViewById11 != null) {
                    viewGroupA2.removeView(viewFindViewById11);
                }
                if (viewFindViewById12 != null) {
                    viewGroupA2.removeView(viewFindViewById12);
                }
            }
        }
        AlertController$RecycleListView alertController$RecycleListView2 = v2Var.e;
        if (alertController$RecycleListView2 == null || (listAdapter = v2Var.o) == null) {
            return;
        }
        alertController$RecycleListView2.setAdapter(listAdapter);
        int i5 = v2Var.p;
        if (i5 > -1) {
            alertController$RecycleListView2.setItemChecked(i5, true);
            alertController$RecycleListView2.setSelection(i5);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.g.i;
        if (nestedScrollView == null || !nestedScrollView.g(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.g.i;
        if (nestedScrollView == null || !nestedScrollView.g(keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        oy.i(bundleOnSaveInstanceState, "super.onSaveInstanceState()");
        this.c.K(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        w10 w10Var = this.b;
        if (w10Var == null) {
            w10Var = new w10(this);
            this.b = w10Var;
        }
        w10Var.e(Lifecycle$Event.ON_RESUME);
    }

    @Override // android.app.Dialog
    public final void onStop() {
        f();
        x4 x4Var = (x4) c();
        x4Var.A();
        bu0 bu0Var = x4Var.p;
        if (bu0Var != null) {
            bu0Var.u = false;
            ct0 ct0Var = bu0Var.t;
            if (ct0Var != null) {
                ct0Var.a();
            }
        }
    }

    @Override // android.app.Dialog
    public final void setContentView(int i) {
        c().h(i);
    }

    @Override // android.app.Dialog
    public final void setTitle(int i) {
        super.setTitle(i);
        c().l(getContext().getString(i));
    }

    @Override // android.app.Dialog
    public final void setContentView(View view) {
        c().j(view);
    }

    @Override // android.app.Dialog
    public final void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c().k(view, layoutParams);
    }

    @Override // android.app.Dialog
    public final void setTitle(CharSequence charSequence) {
        h(charSequence);
        v2 v2Var = this.g;
        v2Var.d = charSequence;
        TextView textView = v2Var.l;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
