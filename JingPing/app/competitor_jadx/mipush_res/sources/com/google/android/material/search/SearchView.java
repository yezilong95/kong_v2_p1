package com.google.android.material.search;

import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.customview.view.AbsSavedState;
import com.google.android.material.R$attr;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.search.SearchView;
import com.google.android.material.search.c;
import com.google.android.material.search.e;
import com.google.android.material.search.g;
import defpackage.as0;
import defpackage.c11;
import defpackage.f51;
import defpackage.fa0;
import defpackage.fp0;
import defpackage.gv;
import defpackage.hq;
import defpackage.i01;
import defpackage.id0;
import defpackage.ip;
import defpackage.l9;
import defpackage.ma0;
import defpackage.mc0;
import defpackage.mv;
import defpackage.nc0;
import defpackage.ou;
import defpackage.pd0;
import defpackage.qm;
import defpackage.rp;
import defpackage.s81;
import defpackage.t40;
import defpackage.t41;
import defpackage.ts;
import defpackage.vi0;
import defpackage.wp;
import defpackage.wr0;
import defpackage.xr0;
import defpackage.y4;
import defpackage.z91;
import defpackage.zn;
import defpackage.zr0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends FrameLayout implements androidx.coordinatorlayout.widget.a, mc0 {
    public static final int k0 = R$style.Widget_Material3_SearchView;
    public final View E;
    public final ClippableRoundedCornerLayout F;
    public final View G;
    public final View H;
    public final FrameLayout I;
    public final FrameLayout J;
    public final MaterialToolbar K;
    public final Toolbar L;
    public final TextView M;
    public final EditText N;
    public final ImageButton O;
    public final View P;
    public final TouchObserverFrameLayout Q;
    public final boolean R;
    public final g S;
    public final ou T;
    public final boolean U;
    public final ts V;
    public final LinkedHashSet W;
    public SearchBar a0;
    public int b0;
    public boolean c0;
    public boolean d0;
    public boolean e0;
    public final int f0;
    public boolean g0;
    public boolean h0;
    public TransitionState i0;
    public HashMap j0;

    public static class Behavior extends CoordinatorLayout.Behavior<SearchView> {
        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            SearchView searchView = (SearchView) view;
            if (searchView.a0 != null || !(view2 instanceof SearchBar)) {
                return false;
            }
            searchView.setupWithSearchBar((SearchBar) view2);
            return false;
        }

        public Behavior() {
        }
    }

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new b();
        public String G;
        public int H;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.G = parcel.readString();
            this.H = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.G);
            parcel.writeInt(this.H);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class TransitionState {
        public static final /* synthetic */ TransitionState[] E;
        public static final TransitionState HIDDEN;
        public static final TransitionState HIDING;
        public static final TransitionState SHOWING;
        public static final TransitionState SHOWN;

        static {
            TransitionState transitionState = new TransitionState("HIDING", 0);
            HIDING = transitionState;
            TransitionState transitionState2 = new TransitionState("HIDDEN", 1);
            HIDDEN = transitionState2;
            TransitionState transitionState3 = new TransitionState("SHOWING", 2);
            SHOWING = transitionState3;
            TransitionState transitionState4 = new TransitionState("SHOWN", 3);
            SHOWN = transitionState4;
            E = new TransitionState[]{transitionState, transitionState2, transitionState3, transitionState4};
        }

        public static TransitionState valueOf(String str) {
            return (TransitionState) Enum.valueOf(TransitionState.class, str);
        }

        public static TransitionState[] values() {
            return (TransitionState[]) E.clone();
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public static /* synthetic */ void e(SearchView searchView, s81 s81Var) {
        int iD = s81Var.d();
        searchView.setUpStatusBarSpacer(iD);
        if (searchView.h0) {
            return;
        }
        searchView.setStatusBarSpacerEnabledInternal(iD > 0);
    }

    private Window getActivityWindow() {
        Activity activity;
        Context context = getContext();
        while (true) {
            if (!(context instanceof ContextWrapper)) {
                activity = null;
                break;
            }
            if (context instanceof Activity) {
                activity = (Activity) context;
                break;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        if (activity == null) {
            return null;
        }
        return activity.getWindow();
    }

    private float getOverlayElevation() {
        SearchBar searchBar = this.a0;
        return searchBar != null ? searchBar.getCompatElevation() : getResources().getDimension(R$dimen.m3_searchview_elevation);
    }

    private int getStatusBarHeight() {
        int identifier = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    private void setStatusBarSpacerEnabledInternal(boolean z) {
        this.H.setVisibility(z ? 0 : 8);
    }

    private void setUpBackgroundViewElevationOverlay(float f) {
        View view;
        ts tsVar = this.V;
        if (tsVar == null || (view = this.G) == null) {
            return;
        }
        view.setBackgroundColor(tsVar.b(this.f0, f));
    }

    private void setUpHeaderLayout(int i) {
        if (i != -1) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
            FrameLayout frameLayout = this.I;
            frameLayout.addView(layoutInflaterFrom.inflate(i, (ViewGroup) frameLayout, false));
            frameLayout.setVisibility(0);
        }
    }

    private void setUpStatusBarSpacer(int i) {
        View view = this.H;
        if (view.getLayoutParams().height != i) {
            view.getLayoutParams().height = i;
            view.requestLayout();
        }
    }

    @Override // defpackage.mc0
    public final void a() {
        if (h()) {
            return;
        }
        g gVar = this.S;
        id0 id0Var = gVar.m;
        l9 l9Var = id0Var.f;
        id0Var.f = null;
        if (Build.VERSION.SDK_INT < 34 || this.a0 == null || l9Var == null) {
            if (this.i0.equals(TransitionState.HIDDEN) || this.i0.equals(TransitionState.HIDING)) {
                return;
            }
            gVar.j();
            return;
        }
        long totalDuration = gVar.j().getTotalDuration();
        SearchBar searchBar = gVar.o;
        id0 id0Var2 = gVar.m;
        AnimatorSet animatorSetB = id0Var2.b(searchBar);
        animatorSetB.setDuration(totalDuration);
        animatorSetB.start();
        id0Var2.i = 0.0f;
        id0Var2.j = null;
        id0Var2.k = null;
        if (gVar.n != null) {
            gVar.c(false).start();
            gVar.n.resume();
        }
        gVar.n = null;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.R) {
            this.Q.addView(view, i, layoutParams);
        } else {
            super.addView(view, i, layoutParams);
        }
    }

    @Override // defpackage.mc0
    public final void b(l9 l9Var) {
        if (h() || this.a0 == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        g gVar = this.S;
        gVar.getClass();
        float f = l9Var.c;
        if (f <= 0.0f) {
            return;
        }
        SearchBar searchBar = gVar.o;
        float cornerSize = searchBar.getCornerSize();
        id0 id0Var = gVar.m;
        if (id0Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        l9 l9Var2 = id0Var.f;
        id0Var.f = l9Var;
        if (l9Var2 != null) {
            if (searchBar.getVisibility() != 4) {
                searchBar.setVisibility(4);
            }
            boolean z = l9Var.d == 0;
            float interpolation = id0Var.a.getInterpolation(f);
            View view = id0Var.b;
            float width = view.getWidth();
            float height = view.getHeight();
            if (width > 0.0f && height > 0.0f) {
                float fA = y4.a(1.0f, 0.9f, interpolation);
                float f2 = id0Var.g;
                float fA2 = y4.a(0.0f, Math.max(0.0f, ((width - (0.9f * width)) / 2.0f) - f2), interpolation) * (z ? 1 : -1);
                float fMin = Math.min(Math.max(0.0f, ((height - (fA * height)) / 2.0f) - f2), id0Var.h);
                float f3 = l9Var.b - id0Var.i;
                float fA3 = y4.a(0.0f, fMin, Math.abs(f3) / height) * Math.signum(f3);
                view.setScaleX(fA);
                view.setScaleY(fA);
                view.setTranslationX(fA2);
                view.setTranslationY(fA3);
                if (view instanceof ClippableRoundedCornerLayout) {
                    ((ClippableRoundedCornerLayout) view).a(r12.getLeft(), r12.getTop(), r12.getRight(), r12.getBottom(), y4.a(id0Var.c(), cornerSize, interpolation));
                }
            }
        }
        AnimatorSet animatorSet = gVar.n;
        if (animatorSet != null) {
            animatorSet.setCurrentPlayTime((long) (f * animatorSet.getDuration()));
            return;
        }
        SearchView searchView = gVar.a;
        if (searchView.g()) {
            searchView.f();
        }
        if (searchView.c0) {
            AnimatorSet animatorSet2 = new AnimatorSet();
            gVar.b(animatorSet2);
            animatorSet2.setDuration(250L);
            animatorSet2.setInterpolator(fp0.a(false, y4.b));
            gVar.n = animatorSet2;
            animatorSet2.start();
            gVar.n.pause();
        }
    }

    @Override // defpackage.mc0
    public final void c(l9 l9Var) {
        if (h() || this.a0 == null) {
            return;
        }
        g gVar = this.S;
        SearchBar searchBar = gVar.o;
        id0 id0Var = gVar.m;
        id0Var.f = l9Var;
        View view = id0Var.b;
        id0Var.j = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (searchBar != null) {
            id0Var.k = fa0.n(view, searchBar);
        }
        id0Var.i = l9Var.b;
    }

    @Override // defpackage.mc0
    public final void d() {
        if (h() || this.a0 == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        g gVar = this.S;
        SearchBar searchBar = gVar.o;
        id0 id0Var = gVar.m;
        if (id0Var.a() != null) {
            AnimatorSet animatorSetB = id0Var.b(searchBar);
            View view = id0Var.b;
            if (view instanceof ClippableRoundedCornerLayout) {
                ClippableRoundedCornerLayout clippableRoundedCornerLayout = (ClippableRoundedCornerLayout) view;
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(clippableRoundedCornerLayout.getCornerRadius(), id0Var.c());
                valueAnimatorOfFloat.addUpdateListener(new wp(3, clippableRoundedCornerLayout));
                animatorSetB.playTogether(valueAnimatorOfFloat);
            }
            animatorSetB.setDuration(id0Var.e);
            animatorSetB.start();
            id0Var.i = 0.0f;
            id0Var.j = null;
            id0Var.k = null;
        }
        AnimatorSet animatorSet = gVar.n;
        if (animatorSet != null) {
            animatorSet.reverse();
        }
        gVar.n = null;
    }

    public final void f() {
        this.N.post(new xr0(this, 1));
    }

    public final boolean g() {
        return this.b0 == 48;
    }

    public id0 getBackHelper() {
        return this.S.m;
    }

    @Override // androidx.coordinatorlayout.widget.a
    public CoordinatorLayout.Behavior<SearchView> getBehavior() {
        return new Behavior();
    }

    public TransitionState getCurrentTransitionState() {
        return this.i0;
    }

    public int getDefaultNavigationIconResource() {
        return R$drawable.ic_arrow_back_black_24;
    }

    public EditText getEditText() {
        return this.N;
    }

    public CharSequence getHint() {
        return this.N.getHint();
    }

    public TextView getSearchPrefix() {
        return this.M;
    }

    public CharSequence getSearchPrefixText() {
        return this.M.getText();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getSoftInputMode() {
        return this.b0;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public Editable getText() {
        return this.N.getText();
    }

    public Toolbar getToolbar() {
        return this.K;
    }

    public final boolean h() {
        return this.i0.equals(TransitionState.HIDDEN) || this.i0.equals(TransitionState.HIDING);
    }

    public final void i() {
        if (this.e0) {
            this.N.postDelayed(new xr0(this, 0), 100L);
        }
    }

    public final void j(TransitionState transitionState, boolean z) {
        if (this.i0.equals(transitionState)) {
            return;
        }
        if (z) {
            if (transitionState == TransitionState.SHOWN) {
                setModalForAccessibility(true);
            } else if (transitionState == TransitionState.HIDDEN) {
                setModalForAccessibility(false);
            }
        }
        this.i0 = transitionState;
        Iterator it = new LinkedHashSet(this.W).iterator();
        if (it.hasNext()) {
            throw zn.i(it);
        }
        m(transitionState);
    }

    public final void k() {
        if (this.i0.equals(TransitionState.SHOWN)) {
            return;
        }
        TransitionState transitionState = this.i0;
        TransitionState transitionState2 = TransitionState.SHOWING;
        if (transitionState.equals(transitionState2)) {
            return;
        }
        final g gVar = this.S;
        SearchBar searchBar = gVar.o;
        SearchView searchView = gVar.a;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = gVar.c;
        if (searchBar == null) {
            if (searchView.g()) {
                searchView.postDelayed(new xr0(searchView, 3), 150L);
            }
            clippableRoundedCornerLayout.setVisibility(4);
            final int i = 1;
            clippableRoundedCornerLayout.post(new Runnable() { // from class: js0
                @Override // java.lang.Runnable
                public final void run() {
                    switch (i) {
                        case 0:
                            g gVar2 = gVar;
                            AnimatorSet animatorSetD = gVar2.d(true);
                            animatorSetD.addListener(new c(gVar2));
                            animatorSetD.start();
                            break;
                        default:
                            g gVar3 = gVar;
                            gVar3.c.setTranslationY(r1.getHeight());
                            AnimatorSet animatorSetH = gVar3.h(true);
                            animatorSetH.addListener(new e(gVar3));
                            animatorSetH.start();
                            break;
                    }
                }
            });
            return;
        }
        if (searchView.g()) {
            searchView.i();
        }
        searchView.setTransitionState(transitionState2);
        Toolbar toolbar = gVar.g;
        Menu menu = toolbar.getMenu();
        if (menu != null) {
            menu.clear();
        }
        if (gVar.o.getMenuResId() == -1 || !searchView.d0) {
            toolbar.setVisibility(8);
        } else {
            toolbar.m(gVar.o.getMenuResId());
            ActionMenuView actionMenuViewU = t40.U(toolbar);
            if (actionMenuViewU != null) {
                for (int i2 = 0; i2 < actionMenuViewU.getChildCount(); i2++) {
                    View childAt = actionMenuViewU.getChildAt(i2);
                    childAt.setClickable(false);
                    childAt.setFocusable(false);
                    childAt.setFocusableInTouchMode(false);
                }
            }
            toolbar.setVisibility(0);
        }
        CharSequence text = gVar.o.getText();
        EditText editText = gVar.i;
        editText.setText(text);
        editText.setSelection(editText.getText().length());
        clippableRoundedCornerLayout.setVisibility(4);
        final int i3 = 0;
        clippableRoundedCornerLayout.post(new Runnable() { // from class: js0
            @Override // java.lang.Runnable
            public final void run() {
                switch (i3) {
                    case 0:
                        g gVar2 = gVar;
                        AnimatorSet animatorSetD = gVar2.d(true);
                        animatorSetD.addListener(new c(gVar2));
                        animatorSetD.start();
                        break;
                    default:
                        g gVar3 = gVar;
                        gVar3.c.setTranslationY(r1.getHeight());
                        AnimatorSet animatorSetH = gVar3.h(true);
                        animatorSetH.addListener(new e(gVar3));
                        animatorSetH.start();
                        break;
                }
            }
        });
    }

    public final void l(ViewGroup viewGroup, boolean z) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt != this) {
                if (childAt.findViewById(this.F.getId()) != null) {
                    l((ViewGroup) childAt, z);
                } else if (z) {
                    this.j0.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    WeakHashMap weakHashMap = f51.a;
                    childAt.setImportantForAccessibility(4);
                } else {
                    HashMap map = this.j0;
                    if (map != null && map.containsKey(childAt)) {
                        int iIntValue = ((Integer) this.j0.get(childAt)).intValue();
                        WeakHashMap weakHashMap2 = f51.a;
                        childAt.setImportantForAccessibility(iIntValue);
                    }
                }
            }
        }
    }

    public final void m(TransitionState transitionState) {
        nc0 nc0Var;
        if (this.a0 == null || !this.U) {
            return;
        }
        boolean zEquals = transitionState.equals(TransitionState.SHOWN);
        ou ouVar = this.T;
        if (zEquals) {
            nc0 nc0Var2 = (nc0) ouVar.F;
            if (nc0Var2 != null) {
                nc0Var2.b((mc0) ouVar.G, (FrameLayout) ouVar.H, false);
                return;
            }
            return;
        }
        if (!transitionState.equals(TransitionState.HIDDEN) || (nc0Var = (nc0) ouVar.F) == null) {
            return;
        }
        nc0Var.c((FrameLayout) ouVar.H);
    }

    public final void n() {
        ImageButton imageButtonH0 = t40.h0(this.K);
        if (imageButtonH0 == null) {
            return;
        }
        int i = this.F.getVisibility() == 0 ? 1 : 0;
        Drawable drawableV0 = z91.v0(imageButtonH0.getDrawable());
        if (drawableV0 instanceof rp) {
            rp rpVar = (rp) drawableV0;
            float f = i;
            if (rpVar.i != f) {
                rpVar.i = f;
                rpVar.invalidateSelf();
            }
        }
        if (drawableV0 instanceof gv) {
            ((gv) drawableV0).a(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        ma0.p0(this);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        Window activityWindow = getActivityWindow();
        if (activityWindow != null) {
            this.b0 = activityWindow.getAttributes().softInputMode;
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.E);
        setText(savedState.G);
        setVisible(savedState.H == 0);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Editable text = getText();
        savedState.G = text == null ? null : text.toString();
        savedState.H = this.F.getVisibility();
        return savedState;
    }

    public void setAnimatedNavigationIcon(boolean z) {
        this.c0 = z;
    }

    public void setAutoShowKeyboard(boolean z) {
        this.e0 = z;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        setUpBackgroundViewElevationOverlay(f);
    }

    public void setHint(CharSequence charSequence) {
        this.N.setHint(charSequence);
    }

    public void setMenuItemsAnimated(boolean z) {
        this.d0 = z;
    }

    public void setModalForAccessibility(boolean z) {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        if (z) {
            this.j0 = new HashMap(viewGroup.getChildCount());
        }
        l(viewGroup, z);
        if (z) {
            return;
        }
        this.j0 = null;
    }

    public void setOnMenuItemClickListener(c11 c11Var) {
        this.K.setOnMenuItemClickListener(c11Var);
    }

    public void setSearchPrefixText(CharSequence charSequence) {
        TextView textView = this.M;
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public void setStatusBarSpacerEnabled(boolean z) {
        this.h0 = true;
        setStatusBarSpacerEnabledInternal(z);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setText(CharSequence charSequence) {
        this.N.setText(charSequence);
    }

    public void setToolbarTouchscreenBlocksFocus(boolean z) {
        this.K.setTouchscreenBlocksFocus(z);
    }

    public void setTransitionState(TransitionState transitionState) {
        j(transitionState, true);
    }

    public void setUseWindowInsetsController(boolean z) {
        this.g0 = z;
    }

    public void setVisible(boolean z) {
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.F;
        boolean z2 = clippableRoundedCornerLayout.getVisibility() == 0;
        clippableRoundedCornerLayout.setVisibility(z ? 0 : 8);
        n();
        j(z ? TransitionState.SHOWN : TransitionState.HIDDEN, z2 != z);
    }

    public void setupWithSearchBar(SearchBar searchBar) {
        this.a0 = searchBar;
        this.S.o = searchBar;
        if (searchBar != null) {
            searchBar.setOnClickListener(new wr0(this, 1));
            if (Build.VERSION.SDK_INT >= 34) {
                try {
                    searchBar.setHandwritingDelegatorCallback(new xr0(this, 2));
                    this.N.setIsHandwritingDelegate(true);
                } catch (LinkageError unused) {
                }
            }
        }
        MaterialToolbar materialToolbar = this.K;
        if (materialToolbar != null && !(z91.v0(materialToolbar.getNavigationIcon()) instanceof rp)) {
            int defaultNavigationIconResource = getDefaultNavigationIconResource();
            if (this.a0 == null) {
                materialToolbar.setNavigationIcon(defaultNavigationIconResource);
            } else {
                Drawable drawableMutate = qm.z(getContext(), defaultNavigationIconResource).mutate();
                if (materialToolbar.getNavigationIconTint() != null) {
                    ip.g(drawableMutate, materialToolbar.getNavigationIconTint().intValue());
                }
                materialToolbar.setNavigationIcon(new gv(this.a0.getNavigationIcon(), drawableMutate));
                n();
            }
        }
        setUpBackgroundViewElevationOverlay(getOverlayElevation());
        m(getCurrentTransitionState());
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialSearchViewStyle);
    }

    public void setHint(int i) {
        this.N.setHint(i);
    }

    public void setText(int i) {
        this.N.setText(i);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SearchView(Context context, AttributeSet attributeSet, int i) {
        int i2 = k0;
        super(pd0.a(context, attributeSet, i, i2), attributeSet, i);
        this.T = new ou(this);
        this.W = new LinkedHashSet();
        this.b0 = 16;
        this.i0 = TransitionState.HIDDEN;
        Context context2 = getContext();
        TypedArray typedArrayD = i01.d(context2, attributeSet, R$styleable.SearchView, i, i2, new int[0]);
        this.f0 = typedArrayD.getColor(R$styleable.SearchView_backgroundTint, 0);
        int resourceId = typedArrayD.getResourceId(R$styleable.SearchView_headerLayout, -1);
        int resourceId2 = typedArrayD.getResourceId(R$styleable.SearchView_android_textAppearance, -1);
        String string = typedArrayD.getString(R$styleable.SearchView_android_text);
        String string2 = typedArrayD.getString(R$styleable.SearchView_android_hint);
        String string3 = typedArrayD.getString(R$styleable.SearchView_searchPrefixText);
        boolean z = typedArrayD.getBoolean(R$styleable.SearchView_useDrawerArrowDrawable, false);
        this.c0 = typedArrayD.getBoolean(R$styleable.SearchView_animateNavigationIcon, true);
        this.d0 = typedArrayD.getBoolean(R$styleable.SearchView_animateMenuItems, true);
        boolean z2 = typedArrayD.getBoolean(R$styleable.SearchView_hideNavigationIcon, false);
        this.e0 = typedArrayD.getBoolean(R$styleable.SearchView_autoShowKeyboard, true);
        this.U = typedArrayD.getBoolean(R$styleable.SearchView_backHandlingEnabled, true);
        typedArrayD.recycle();
        LayoutInflater.from(context2).inflate(R$layout.mtrl_search_view, this);
        this.R = true;
        this.E = findViewById(R$id.open_search_view_scrim);
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = (ClippableRoundedCornerLayout) findViewById(R$id.open_search_view_root);
        this.F = clippableRoundedCornerLayout;
        this.G = findViewById(R$id.open_search_view_background);
        View viewFindViewById = findViewById(R$id.open_search_view_status_bar_spacer);
        this.H = viewFindViewById;
        this.I = (FrameLayout) findViewById(R$id.open_search_view_header_container);
        this.J = (FrameLayout) findViewById(R$id.open_search_view_toolbar_container);
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R$id.open_search_view_toolbar);
        this.K = materialToolbar;
        this.L = (Toolbar) findViewById(R$id.open_search_view_dummy_toolbar);
        this.M = (TextView) findViewById(R$id.open_search_view_search_prefix);
        EditText editText = (EditText) findViewById(R$id.open_search_view_edit_text);
        this.N = editText;
        ImageButton imageButton = (ImageButton) findViewById(R$id.open_search_view_clear_button);
        this.O = imageButton;
        View viewFindViewById2 = findViewById(R$id.open_search_view_divider);
        this.P = viewFindViewById2;
        TouchObserverFrameLayout touchObserverFrameLayout = (TouchObserverFrameLayout) findViewById(R$id.open_search_view_content_container);
        this.Q = touchObserverFrameLayout;
        this.S = new g(this);
        this.V = new ts(context2);
        clippableRoundedCornerLayout.setOnTouchListener(new mv(1));
        setUpBackgroundViewElevationOverlay(getOverlayElevation());
        setUpHeaderLayout(resourceId);
        setSearchPrefixText(string3);
        if (resourceId2 != -1) {
            editText.setTextAppearance(resourceId2);
        }
        editText.setText(string);
        editText.setHint(string2);
        if (z2) {
            materialToolbar.setNavigationIcon((Drawable) null);
        } else {
            materialToolbar.setNavigationOnClickListener(new wr0(this, 2));
            if (z) {
                rp rpVar = new rp(getContext());
                int iP = fa0.P(this, R$attr.colorOnSurface);
                Paint paint = rpVar.a;
                if (iP != paint.getColor()) {
                    paint.setColor(iP);
                    rpVar.invalidateSelf();
                }
                materialToolbar.setNavigationIcon(rpVar);
            }
        }
        imageButton.setOnClickListener(new wr0(this, 0));
        editText.addTextChangedListener(new as0(this, 1));
        touchObserverFrameLayout.setOnTouchListener(new hq(1, this));
        fa0.B(materialToolbar, new zr0(this));
        final ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById2.getLayoutParams();
        final int i3 = marginLayoutParams.leftMargin;
        final int i4 = marginLayoutParams.rightMargin;
        vi0 vi0Var = new vi0() { // from class: yr0
            @Override // defpackage.vi0
            public final s81 h(View view, s81 s81Var) {
                int i5 = SearchView.k0;
                int iB = s81Var.b() + i3;
                ViewGroup.MarginLayoutParams marginLayoutParams2 = marginLayoutParams;
                marginLayoutParams2.leftMargin = iB;
                marginLayoutParams2.rightMargin = s81Var.c() + i4;
                return s81Var;
            }
        };
        WeakHashMap weakHashMap = f51.a;
        t41.u(viewFindViewById2, vi0Var);
        setUpStatusBarSpacer(getStatusBarHeight());
        t41.u(viewFindViewById, new zr0(this));
    }
}
