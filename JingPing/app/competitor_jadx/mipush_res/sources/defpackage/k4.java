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
import io.nats.client.support.NatsObjectStoreUtil;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class k4 extends Dialog implements DialogInterface, j5, x80, kr0 {
    public z80 E;
    public final i6 F;
    public final ej0 G;
    public c6 H;
    public final d6 I;
    public final i4 J;

    /* JADX WARN: Type inference failed for: r1v4, types: [d6] */
    public k4(ContextThemeWrapper contextThemeWrapper, int i) {
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
        this.F = new i6(new jr0(this, new qg(this, 2)));
        this.G = new ej0(new e(10, this));
        this.I = new m70() { // from class: d6
            @Override // defpackage.m70
            public final boolean b(KeyEvent keyEvent) {
                return this.E.i(keyEvent);
            }
        };
        o5 o5VarC = c();
        if (iG == 0) {
            TypedValue typedValue2 = new TypedValue();
            contextThemeWrapper.getTheme().resolveAttribute(R$attr.dialogTheme, typedValue2, true);
            iG = typedValue2.resourceId;
        }
        ((c6) o5VarC).x0 = iG;
        o5VarC.d();
        this.J = new i4(getContext(), this, getWindow());
    }

    public static void b(k4 k4Var) {
        t40.n(k4Var, "this$0");
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

    @Override // defpackage.kr0
    public final i6 a() {
        return (i6) this.F.G;
    }

    @Override // android.app.Dialog
    public final void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        c6 c6Var = (c6) c();
        c6Var.w();
        ((ViewGroup) c6Var.e0.findViewById(R.id.content)).addView(view, layoutParams);
        c6Var.Q.a(c6Var.P.getCallback());
    }

    public final o5 c() {
        if (this.H == null) {
            q7 q7Var = o5.E;
            this.H = new c6(getContext(), getWindow(), this, this);
        }
        return this.H;
    }

    public final void d(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= 33) {
            OnBackInvokedDispatcher onBackInvokedDispatcher = getOnBackInvokedDispatcher();
            t40.m(onBackInvokedDispatcher, "onBackInvokedDispatcher");
            ej0 ej0Var = this.G;
            ej0Var.getClass();
            ej0Var.e = onBackInvokedDispatcher;
            ej0Var.b(ej0Var.g);
        }
        this.F.I(bundle);
        z80 z80Var = this.E;
        if (z80Var == null) {
            z80Var = new z80(this);
            this.E = z80Var;
        }
        z80Var.e(Lifecycle$Event.ON_CREATE);
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public final void dismiss() {
        super.dismiss();
        c().e();
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return t40.L(this.I, getWindow().getDecorView(), this, keyEvent);
    }

    public final void e(Bundle bundle) {
        c().a();
        d(bundle);
        c().d();
    }

    public final void f() {
        z80 z80Var = this.E;
        if (z80Var == null) {
            z80Var = new z80(this);
            this.E = z80Var;
        }
        z80Var.e(Lifecycle$Event.ON_DESTROY);
        this.E = null;
        super.onStop();
    }

    @Override // android.app.Dialog
    public final View findViewById(int i) {
        c6 c6Var = (c6) c();
        c6Var.w();
        return c6Var.P.findViewById(i);
    }

    @Override // defpackage.x80
    public final s80 getLifecycle() {
        z80 z80Var = this.E;
        if (z80Var != null) {
            return z80Var;
        }
        z80 z80Var2 = new z80(this);
        this.E = z80Var2;
        return z80Var2;
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
        this.G.a();
    }

    @Override // android.app.Dialog
    public final void onCreate(Bundle bundle) {
        int i;
        ListAdapter listAdapter;
        View viewFindViewById;
        e(bundle);
        i4 i4Var = this.J;
        i4Var.b.setContentView(i4Var.q);
        int i2 = R$id.parentPanel;
        Window window = i4Var.c;
        View viewFindViewById2 = window.findViewById(i2);
        View viewFindViewById3 = viewFindViewById2.findViewById(R$id.topPanel);
        View viewFindViewById4 = viewFindViewById2.findViewById(R$id.contentPanel);
        View viewFindViewById5 = viewFindViewById2.findViewById(R$id.buttonPanel);
        ViewGroup viewGroup = (ViewGroup) viewFindViewById2.findViewById(R$id.customPanel);
        window.setFlags(NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE, NatsObjectStoreUtil.DEFAULT_CHUNK_SIZE);
        viewGroup.setVisibility(8);
        View viewFindViewById6 = viewGroup.findViewById(R$id.topPanel);
        View viewFindViewById7 = viewGroup.findViewById(R$id.contentPanel);
        View viewFindViewById8 = viewGroup.findViewById(R$id.buttonPanel);
        ViewGroup viewGroupA = i4.a(viewFindViewById6, viewFindViewById3);
        ViewGroup viewGroupA2 = i4.a(viewFindViewById7, viewFindViewById4);
        ViewGroup viewGroupA3 = i4.a(viewFindViewById8, viewFindViewById5);
        NestedScrollView nestedScrollView = (NestedScrollView) window.findViewById(R$id.scrollView);
        i4Var.i = nestedScrollView;
        nestedScrollView.setFocusable(false);
        i4Var.i.setNestedScrollingEnabled(false);
        TextView textView = (TextView) viewGroupA2.findViewById(R.id.message);
        i4Var.m = textView;
        if (textView != null) {
            textView.setVisibility(8);
            i4Var.i.removeView(i4Var.m);
            if (i4Var.e != null) {
                ViewGroup viewGroup2 = (ViewGroup) i4Var.i.getParent();
                int iIndexOfChild = viewGroup2.indexOfChild(i4Var.i);
                viewGroup2.removeViewAt(iIndexOfChild);
                viewGroup2.addView(i4Var.e, iIndexOfChild, new ViewGroup.LayoutParams(-1, -1));
            } else {
                viewGroupA2.setVisibility(8);
            }
        }
        Button button = (Button) viewGroupA3.findViewById(R.id.button1);
        i4Var.f = button;
        y2 y2Var = i4Var.w;
        button.setOnClickListener(y2Var);
        if (TextUtils.isEmpty(null)) {
            i4Var.f.setVisibility(8);
            i = 0;
        } else {
            i4Var.f.setText((CharSequence) null);
            i4Var.f.setVisibility(0);
            i = 1;
        }
        Button button2 = (Button) viewGroupA3.findViewById(R.id.button2);
        i4Var.g = button2;
        button2.setOnClickListener(y2Var);
        if (TextUtils.isEmpty(null)) {
            i4Var.g.setVisibility(8);
        } else {
            i4Var.g.setText((CharSequence) null);
            i4Var.g.setVisibility(0);
            i |= 2;
        }
        Button button3 = (Button) viewGroupA3.findViewById(R.id.button3);
        i4Var.h = button3;
        button3.setOnClickListener(y2Var);
        if (TextUtils.isEmpty(null)) {
            i4Var.h.setVisibility(8);
        } else {
            i4Var.h.setText((CharSequence) null);
            i4Var.h.setVisibility(0);
            i |= 4;
        }
        TypedValue typedValue = new TypedValue();
        i4Var.a.getTheme().resolveAttribute(R$attr.alertDialogCenterButtons, typedValue, true);
        if (typedValue.data != 0) {
            if (i == 1) {
                Button button4 = i4Var.f;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) button4.getLayoutParams();
                layoutParams.gravity = 1;
                layoutParams.weight = 0.5f;
                button4.setLayoutParams(layoutParams);
            } else if (i == 2) {
                Button button5 = i4Var.g;
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) button5.getLayoutParams();
                layoutParams2.gravity = 1;
                layoutParams2.weight = 0.5f;
                button5.setLayoutParams(layoutParams2);
            } else if (i == 4) {
                Button button6 = i4Var.h;
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) button6.getLayoutParams();
                layoutParams3.gravity = 1;
                layoutParams3.weight = 0.5f;
                button6.setLayoutParams(layoutParams3);
            }
        }
        if (i == 0) {
            viewGroupA3.setVisibility(8);
        }
        if (i4Var.n != null) {
            viewGroupA.addView(i4Var.n, 0, new ViewGroup.LayoutParams(-1, -2));
            window.findViewById(R$id.title_template).setVisibility(8);
        } else {
            i4Var.k = (ImageView) window.findViewById(R.id.icon);
            if (TextUtils.isEmpty(i4Var.d) || !i4Var.u) {
                window.findViewById(R$id.title_template).setVisibility(8);
                i4Var.k.setVisibility(8);
                viewGroupA.setVisibility(8);
            } else {
                TextView textView2 = (TextView) window.findViewById(R$id.alertTitle);
                i4Var.l = textView2;
                textView2.setText(i4Var.d);
                Drawable drawable = i4Var.j;
                if (drawable != null) {
                    i4Var.k.setImageDrawable(drawable);
                } else {
                    i4Var.l.setPadding(i4Var.k.getPaddingLeft(), i4Var.k.getPaddingTop(), i4Var.k.getPaddingRight(), i4Var.k.getPaddingBottom());
                    i4Var.k.setVisibility(8);
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
            NestedScrollView nestedScrollView2 = i4Var.i;
            if (nestedScrollView2 != null) {
                nestedScrollView2.setClipToPadding(true);
            }
            View viewFindViewById9 = i4Var.e != null ? viewGroupA.findViewById(R$id.titleDividerNoCustom) : null;
            if (viewFindViewById9 != null) {
                viewFindViewById9.setVisibility(0);
            }
        } else {
            View viewFindViewById10 = viewGroupA2.findViewById(R$id.textSpacerNoTitle);
            if (viewFindViewById10 != null) {
                viewFindViewById10.setVisibility(0);
            }
        }
        AlertController$RecycleListView alertController$RecycleListView = i4Var.e;
        if (alertController$RecycleListView != null && (!z2 || i3 == 0)) {
            alertController$RecycleListView.setPadding(alertController$RecycleListView.getPaddingLeft(), i3 != 0 ? alertController$RecycleListView.getPaddingTop() : alertController$RecycleListView.E, alertController$RecycleListView.getPaddingRight(), z2 ? alertController$RecycleListView.getPaddingBottom() : alertController$RecycleListView.F);
        }
        if (!z) {
            View view = i4Var.e;
            if (view == null) {
                view = i4Var.i;
            }
            if (view != null) {
                int i4 = (z2 ? 2 : 0) | i3;
                View viewFindViewById11 = window.findViewById(R$id.scrollIndicatorUp);
                View viewFindViewById12 = window.findViewById(R$id.scrollIndicatorDown);
                WeakHashMap weakHashMap = f51.a;
                u41.d(view, i4, 3);
                if (viewFindViewById11 != null) {
                    viewGroupA2.removeView(viewFindViewById11);
                }
                if (viewFindViewById12 != null) {
                    viewGroupA2.removeView(viewFindViewById12);
                }
            }
        }
        AlertController$RecycleListView alertController$RecycleListView2 = i4Var.e;
        if (alertController$RecycleListView2 == null || (listAdapter = i4Var.o) == null) {
            return;
        }
        alertController$RecycleListView2.setAdapter(listAdapter);
        int i5 = i4Var.p;
        if (i5 > -1) {
            alertController$RecycleListView2.setItemChecked(i5, true);
            alertController$RecycleListView2.setSelection(i5);
        }
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.J.i;
        if (nestedScrollView == null || !nestedScrollView.f(keyEvent)) {
            return super.onKeyDown(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog, android.view.KeyEvent.Callback
    public final boolean onKeyUp(int i, KeyEvent keyEvent) {
        NestedScrollView nestedScrollView = this.J.i;
        if (nestedScrollView == null || !nestedScrollView.f(keyEvent)) {
            return super.onKeyUp(i, keyEvent);
        }
        return true;
    }

    @Override // android.app.Dialog
    public final Bundle onSaveInstanceState() {
        Bundle bundleOnSaveInstanceState = super.onSaveInstanceState();
        t40.m(bundleOnSaveInstanceState, "super.onSaveInstanceState()");
        this.F.J(bundleOnSaveInstanceState);
        return bundleOnSaveInstanceState;
    }

    @Override // android.app.Dialog
    public final void onStart() {
        super.onStart();
        z80 z80Var = this.E;
        if (z80Var == null) {
            z80Var = new z80(this);
            this.E = z80Var;
        }
        z80Var.e(Lifecycle$Event.ON_RESUME);
    }

    @Override // android.app.Dialog
    public final void onStop() {
        f();
        c6 c6Var = (c6) c();
        c6Var.A();
        x71 x71Var = c6Var.S;
        if (x71Var != null) {
            x71Var.t = false;
            i61 i61Var = x71Var.s;
            if (i61Var != null) {
                i61Var.a();
            }
        }
    }

    @Override // android.app.Dialog
    public final void setContentView(int i) {
        c().i(i);
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
        i4 i4Var = this.J;
        i4Var.d = charSequence;
        TextView textView = i4Var.l;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
