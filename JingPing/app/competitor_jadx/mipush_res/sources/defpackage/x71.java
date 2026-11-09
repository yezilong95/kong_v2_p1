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
public final class x71 extends w2 implements b3 {
    public static final AccelerateInterpolator y = new AccelerateInterpolator();
    public static final DecelerateInterpolator z = new DecelerateInterpolator();
    public Context a;
    public Context b;
    public ActionBarOverlayLayout c;
    public ActionBarContainer d;
    public sm e;
    public ActionBarContextView f;
    public final View g;
    public boolean h;
    public w71 i;
    public w71 j;
    public i6 k;
    public boolean l;
    public final ArrayList m;
    public int n;
    public boolean o;
    public boolean p;
    public boolean q;
    public boolean r;
    public i61 s;
    public boolean t;
    public boolean u;
    public final v71 v;
    public final v71 w;
    public final cw0 x;

    public x71(Activity activity, boolean z2) {
        new ArrayList();
        this.m = new ArrayList();
        this.n = 0;
        this.o = true;
        this.r = true;
        this.v = new v71(this, 0);
        this.w = new v71(this, 1);
        this.x = new cw0(6, this);
        View decorView = activity.getWindow().getDecorView();
        c(decorView);
        if (z2) {
            return;
        }
        this.g = decorView.findViewById(R.id.content);
    }

    public final void a(boolean z2) {
        h61 h61VarI;
        h61 h61VarI2;
        if (z2) {
            if (!this.q) {
                this.q = true;
                ActionBarOverlayLayout actionBarOverlayLayout = this.c;
                if (actionBarOverlayLayout != null) {
                    actionBarOverlayLayout.setShowingForActionMode(true);
                }
                f(false);
            }
        } else if (this.q) {
            this.q = false;
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (actionBarOverlayLayout2 != null) {
                actionBarOverlayLayout2.setShowingForActionMode(false);
            }
            f(false);
        }
        ActionBarContainer actionBarContainer = this.d;
        WeakHashMap weakHashMap = f51.a;
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
            h61VarI = f51.a(lVar.a);
            h61VarI.a(0.0f);
            h61VarI.c(100L);
            h61VarI.d(new e11(lVar, 4));
            h61VarI2 = this.f.i(0, 200L);
        } else {
            l lVar2 = (l) this.e;
            h61 h61VarA = f51.a(lVar2.a);
            h61VarA.a(1.0f);
            h61VarA.c(200L);
            h61VarA.d(new e11(lVar2, 0));
            h61VarI = this.f.i(8, 100L);
            h61VarI2 = h61VarA;
        }
        i61 i61Var = new i61();
        ArrayList arrayList = i61Var.a;
        arrayList.add(h61VarI);
        View view = (View) h61VarI.a.get();
        long duration = view != null ? view.animate().getDuration() : 0L;
        View view2 = (View) h61VarI2.a.get();
        if (view2 != null) {
            view2.animate().setStartDelay(duration);
        }
        arrayList.add(h61VarI2);
        i61Var.b();
    }

    @Override // defpackage.w2
    public void addOnMenuVisibilityListener(v2 v2Var) {
        this.m.add(v2Var);
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
        sm wrapper;
        ActionBarOverlayLayout actionBarOverlayLayout = (ActionBarOverlayLayout) view.findViewById(R$id.decor_content_parent);
        this.c = actionBarOverlayLayout;
        if (actionBarOverlayLayout != null) {
            actionBarOverlayLayout.setActionBarVisibilityCallback(this);
        }
        KeyEvent.Callback callbackFindViewById = view.findViewById(R$id.action_bar);
        if (callbackFindViewById instanceof sm) {
            wrapper = (sm) callbackFindViewById;
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
        sm smVar = this.e;
        if (smVar == null || this.f == null || actionBarContainer == null) {
            throw new IllegalStateException(x71.class.getSimpleName().concat(" can only be used with a compatible window decor layout"));
        }
        Context context = ((l) smVar).a.getContext();
        this.a = context;
        if ((((l) this.e).b & 4) != 0) {
            this.h = true;
        }
        p50 p50VarT = p50.t(context);
        int i = ((Context) p50VarT.F).getApplicationInfo().targetSdkVersion;
        this.e.getClass();
        e(((Context) p50VarT.F).getResources().getBoolean(R$bool.abc_action_bar_embed_tabs));
        TypedArray typedArrayObtainStyledAttributes = this.a.obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        if (typedArrayObtainStyledAttributes.getBoolean(R$styleable.ActionBar_hideOnContentScroll, false)) {
            ActionBarOverlayLayout actionBarOverlayLayout2 = this.c;
            if (!actionBarOverlayLayout2.L) {
                throw new IllegalStateException("Action bar must be in overlay mode (Window.FEATURE_OVERLAY_ACTION_BAR) to enable hide on content scroll");
            }
            this.u = true;
            actionBarOverlayLayout2.setHideOnContentScrollEnabled(true);
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.ActionBar_elevation, 0);
        if (dimensionPixelSize != 0) {
            ActionBarContainer actionBarContainer2 = this.d;
            WeakHashMap weakHashMap = f51.a;
            t41.s(actionBarContainer2, dimensionPixelSize);
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
        l lVar = (l) this.e;
        lVar.getClass();
        lVar.a.setCollapsible(false);
        this.c.setHasNonEmbeddedTabs(false);
    }

    public final void f(boolean z2) {
        boolean z3 = this.q || !this.p;
        View view = this.g;
        cw0 cw0Var = this.x;
        if (!z3) {
            if (this.r) {
                this.r = false;
                i61 i61Var = this.s;
                if (i61Var != null) {
                    i61Var.a();
                }
                int i = this.n;
                v71 v71Var = this.v;
                if (i != 0 || (!this.t && !z2)) {
                    v71Var.a();
                    return;
                }
                this.d.setAlpha(1.0f);
                this.d.setTransitioning(true);
                i61 i61Var2 = new i61();
                float f = -this.d.getHeight();
                if (z2) {
                    this.d.getLocationInWindow(new int[]{0, 0});
                    f -= r12[1];
                }
                h61 h61VarA = f51.a(this.d);
                h61VarA.e(f);
                View view2 = (View) h61VarA.a.get();
                if (view2 != null) {
                    view2.animate().setUpdateListener(cw0Var != null ? new wp(cw0Var, view2) : null);
                }
                boolean z4 = i61Var2.e;
                ArrayList arrayList = i61Var2.a;
                if (!z4) {
                    arrayList.add(h61VarA);
                }
                if (this.o && view != null) {
                    h61 h61VarA2 = f51.a(view);
                    h61VarA2.e(f);
                    if (!i61Var2.e) {
                        arrayList.add(h61VarA2);
                    }
                }
                AccelerateInterpolator accelerateInterpolator = y;
                boolean z5 = i61Var2.e;
                if (!z5) {
                    i61Var2.c = accelerateInterpolator;
                }
                if (!z5) {
                    i61Var2.b = 250L;
                }
                if (!z5) {
                    i61Var2.d = v71Var;
                }
                this.s = i61Var2;
                i61Var2.b();
                return;
            }
            return;
        }
        if (this.r) {
            return;
        }
        this.r = true;
        i61 i61Var3 = this.s;
        if (i61Var3 != null) {
            i61Var3.a();
        }
        this.d.setVisibility(0);
        int i2 = this.n;
        v71 v71Var2 = this.w;
        if (i2 == 0 && (this.t || z2)) {
            this.d.setTranslationY(0.0f);
            float f2 = -this.d.getHeight();
            if (z2) {
                this.d.getLocationInWindow(new int[]{0, 0});
                f2 -= r12[1];
            }
            this.d.setTranslationY(f2);
            i61 i61Var4 = new i61();
            h61 h61VarA3 = f51.a(this.d);
            h61VarA3.e(0.0f);
            View view3 = (View) h61VarA3.a.get();
            if (view3 != null) {
                view3.animate().setUpdateListener(cw0Var != null ? new wp(cw0Var, view3) : null);
            }
            boolean z6 = i61Var4.e;
            ArrayList arrayList2 = i61Var4.a;
            if (!z6) {
                arrayList2.add(h61VarA3);
            }
            if (this.o && view != null) {
                view.setTranslationY(f2);
                h61 h61VarA4 = f51.a(view);
                h61VarA4.e(0.0f);
                if (!i61Var4.e) {
                    arrayList2.add(h61VarA4);
                }
            }
            DecelerateInterpolator decelerateInterpolator = z;
            boolean z7 = i61Var4.e;
            if (!z7) {
                i61Var4.c = decelerateInterpolator;
            }
            if (!z7) {
                i61Var4.b = 250L;
            }
            if (!z7) {
                i61Var4.d = v71Var2;
            }
            this.s = i61Var4;
            i61Var4.b();
        } else {
            this.d.setAlpha(1.0f);
            this.d.setTranslationY(0.0f);
            if (this.o && view != null) {
                view.setTranslationY(0.0f);
            }
            v71Var2.a();
        }
        ActionBarOverlayLayout actionBarOverlayLayout = this.c;
        if (actionBarOverlayLayout != null) {
            WeakHashMap weakHashMap = f51.a;
            r41.c(actionBarOverlayLayout);
        }
    }

    @Override // defpackage.w2
    public void removeOnMenuVisibilityListener(v2 v2Var) {
        this.m.remove(v2Var);
    }

    public x71(Dialog dialog) {
        new ArrayList();
        this.m = new ArrayList();
        this.n = 0;
        this.o = true;
        this.r = true;
        this.v = new v71(this, 0);
        this.w = new v71(this, 1);
        this.x = new cw0(6, this);
        c(dialog.getWindow().getDecorView());
    }
}
