package defpackage;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$bool;
import androidx.appcompat.R$id;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionBarContainer;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.ActionBarOverlayLayout;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.l;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class bu0 extends a1 implements f1 {
    public Context a;
    public Context b;
    public ActionBarOverlayLayout c;
    public ActionBarContainer d;
    public zi e;
    public ActionBarContextView f;
    public final View g;
    public boolean h;
    public au0 i;
    public au0 j;
    public e5 k;
    public boolean l;
    public final ArrayList m;
    public int n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public boolean s;
    public ct0 t;
    public boolean u;
    public boolean v;
    public final zt0 w;
    public final zt0 x;
    public final d90 y;
    public static final AccelerateInterpolator z = new AccelerateInterpolator();
    public static final DecelerateInterpolator A = new DecelerateInterpolator();

    public bu0(Activity activity, boolean z2) {
        new ArrayList();
        this.m = new ArrayList();
        this.n = 0;
        this.o = true;
        this.s = true;
        this.w = new zt0(this, 0);
        this.x = new zt0(this, 1);
        this.y = new d90(this);
        View decorView = activity.getWindow().getDecorView();
        c(decorView);
        if (z2) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    public final void a(boolean z2) {
        bt0 bt0VarI;
        bt0 bt0VarI2;
        if (z2) {
            if (!this.r) {
                this.r = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                f(false);
            }
        } else if (this.r) {
            this.r = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            f(false);
        }
        ActionBarContainer actionBarContainer = this.d;
        WeakHashMap weakHashMap = xr0.a;
        if (!actionBarContainer.isLaidOut()) {
            if (z2) {
                ((l) this.e).a.setVisibility(4);
                this.f.setVisibility(0);
                return;
            } else {
                ((l) this.e).a.setVisibility(0);
                this.f.setVisibility(8);
                return;
            }
        }
        if (z2) {
            l lVar = (l) this.e;
            bt0VarI = xr0.a(lVar.a);
            bt0VarI.a(0.0f);
            bt0VarI.c(100L);
            bt0VarI.d(new to0(lVar, 4));
            bt0VarI2 = this.f.i(200L, 0);
        } else {
            l lVar2 = (l) this.e;
            bt0 bt0VarA = xr0.a(lVar2.a);
            bt0VarA.a(1.0f);
            bt0VarA.c(200L);
            bt0VarA.d(new to0(lVar2, 0));
            bt0VarI = this.f.i(100L, 8);
            bt0VarI2 = bt0VarA;
        }
        ct0 ct0Var = new ct0();
        ArrayList arrayList = ct0Var.a;
        arrayList.add(bt0VarI);
        View view = (View) bt0VarI.a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = (View) bt0VarI2.a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(bt0VarI2);
        ct0Var.b();
    }

    @Override // defpackage.a1
    public void addOnMenuVisibilityListener(z0 z0Var) {
        this.m.add(z0Var);
    }

    public final Context b() {
        if (this.b == null) {
            TypedValue typedValue = new TypedValue();
            this.a.getTheme().resolveAttribute(R$attr.actionBarWidgetTheme, typedValue, true);
            int i = typedValue.resourceId;
            if (i != 0) {
                this.b = new ContextThemeWrapper(this.a, i);
            } else {
                this.b = this.a;
            }
        }
        return this.b;
    }

    public final void c(View view) {
        zi wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(R$id.action_bar);
        if (callbackFindViewById instanceof zi) {
            wrapper = (zi) callbackFindViewById;
        } else {
            if (!(callbackFindViewById instanceof Toolbar)) {
                throw new IllegalStateException("Can't make a decor toolbar out of ".concat(callbackFindViewById != null ? callbackFindViewById.getClass().getSimpleName() : "null"));
            }
            wrapper = ((Toolbar) callbackFindViewById).getWrapper();
        }
        this.e = wrapper;
        this.f = (ActionBarContextView) view.findViewById(R$id.action_context_bar);
        ActionBarContainer actionBarContainer = (ActionBarContainer) view.findViewById(R$id.action_bar_container);
        this.d = actionBarContainer;
        zi ziVar = this.e;
        if (ziVar == null || this.f == null || actionBarContainer == null) {
            throw new IllegalStateException(bu0.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        Context context = ((l) ziVar).a.getContext();
        this.a = context;
        if ((((l) this.e).b & 4) != 0) {
            this.h = true;
        }
        g1 g1VarB = g1.b(context);
        int i = g1VarB.a.getApplicationInfo().targetSdkVersion;
        this.e.getClass();
        e(g1VarB.a.getResources().getBoolean(R$bool.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.a.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (!actionBarOverlayLayout2.i) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.v = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.d;
            WeakHashMap weakHashMap = xr0.a;
            lr0.s(actionBarContainer2, dimensionPixelSize);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void d(boolean z2) {
        if (this.h) {
            return;
        }
        int i = z2 ? 4 : 0;
        l lVar = (l) this.e;
        int i2 = lVar.b;
        this.h = true;
        lVar.a((i & 4) | (i2 & (-5)));
    }

    public final void e(boolean z2) {
        if (z2) {
            this.d.setTabContainer(null);
            ((l) this.e).getClass();
        } else {
            ((l) this.e).getClass();
            this.d.setTabContainer(null);
        }
        this.e.getClass();
        ((l) this.e).a.setCollapsible(false);
        this.c.setHasNonEmbeddedTabs(false);
    }

    public final void f(boolean z2) {
        boolean z3 = this.r || !(this.p || this.q);
        View view = this.g;
        d90 d90Var = this.y;
        if (!z3) {
            if (this.s) {
                this.s = false;
                ct0 ct0Var = this.t;
                if (ct0Var != null) {
                    ct0Var.a();
                }
                int i = this.n;
                zt0 zt0Var = this.w;
                if (i != 0 || (!this.u && !z2)) {
                    zt0Var.a();
                    return;
                }
                this.d.setAlpha(1.0f);
                this.d.setTransitioning(true);
                ct0 ct0Var2 = new ct0();
                float f = -this.d.getHeight();
                if (z2) {
                    this.d.getLocationInWindow(new int[]{0, 0});
                    f -= r12[1];
                }
                bt0 bt0VarA = xr0.a(this.d);
                bt0VarA.e(f);
                View view2 = (View) bt0VarA.a.get();
                if (view2 != null) {
                    view2.animate().setUpdateListener(d90Var != null ? new tl(d90Var, view2) : null);
                }
                boolean z4 = ct0Var2.e;
                ArrayList arrayList = ct0Var2.a;
                if (!z4) {
                    arrayList.add(bt0VarA);
                }
                if (this.o && view != null) {
                    bt0 bt0VarA2 = xr0.a(view);
                    bt0VarA2.e(f);
                    if (!ct0Var2.e) {
                        arrayList.add(bt0VarA2);
                    }
                }
                AccelerateInterpolator accelerateInterpolator = z;
                boolean z5 = ct0Var2.e;
                if (!z5) {
                    ct0Var2.c = accelerateInterpolator;
                }
                if (!z5) {
                    ct0Var2.b = 250L;
                }
                if (!z5) {
                    ct0Var2.d = zt0Var;
                }
                this.t = ct0Var2;
                ct0Var2.b();
                return;
            }
            return;
        }
        if (this.s) {
            return;
        }
        this.s = true;
        ct0 ct0Var3 = this.t;
        if (ct0Var3 != null) {
            ct0Var3.a();
        }
        this.d.setVisibility(0);
        int i2 = this.n;
        zt0 zt0Var2 = this.x;
        if (i2 == 0 && (this.u || z2)) {
            this.d.setTranslationY(0.0f);
            float f2 = -this.d.getHeight();
            if (z2) {
                this.d.getLocationInWindow(new int[]{0, 0});
                f2 -= r12[1];
            }
            this.d.setTranslationY(f2);
            ct0 ct0Var4 = new ct0();
            bt0 bt0VarA3 = xr0.a(this.d);
            bt0VarA3.e(0.0f);
            View view3 = (View) bt0VarA3.a.get();
            if (view3 != null) {
                view3.animate().setUpdateListener(d90Var != null ? new tl(d90Var, view3) : null);
            }
            boolean z6 = ct0Var4.e;
            ArrayList arrayList2 = ct0Var4.a;
            if (!z6) {
                arrayList2.add(bt0VarA3);
            }
            if (this.o && view != null) {
                view.setTranslationY(f2);
                bt0 bt0VarA4 = xr0.a(view);
                bt0VarA4.e(0.0f);
                if (!ct0Var4.e) {
                    arrayList2.add(bt0VarA4);
                }
            }
            DecelerateInterpolator decelerateInterpolator = A;
            boolean z7 = ct0Var4.e;
            if (!z7) {
                ct0Var4.c = decelerateInterpolator;
            }
            if (!z7) {
                ct0Var4.b = 250L;
            }
            if (!z7) {
                ct0Var4.d = zt0Var2;
            }
            this.t = ct0Var4;
            ct0Var4.b();
        } else {
            this.d.setAlpha(1.0f);
            this.d.setTranslationY(0.0f);
            if (this.o && view != null) {
                view.setTranslationY(0.0f);
            }
            zt0Var2.a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.c;
        if (actionBarOverlayLayout != null) {
            WeakHashMap weakHashMap = xr0.a;
            jr0.c(actionBarOverlayLayout);
        }
    }

    @Override // defpackage.a1
    public void removeOnMenuVisibilityListener(z0 z0Var) {
        this.m.remove(z0Var);
    }

    public bu0(Dialog dialog) {
        new ArrayList();
        this.m = new ArrayList();
        this.n = 0;
        this.o = true;
        this.s = true;
        this.w = new zt0(this, 0);
        this.x = new zt0(this, 1);
        this.y = new d90(this);
        c(dialog.getWindow().getDecorView());
    }
}
