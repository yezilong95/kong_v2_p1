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
import defpackage.bn;
import defpackage.eh0;
import defpackage.em;
import defpackage.fh0;
import defpackage.ha0;
import defpackage.hh0;
import defpackage.hk;
import defpackage.hl;
import defpackage.ih0;
import defpackage.jh0;
import defpackage.jp;
import defpackage.k9;
import defpackage.kf0;
import defpackage.lr0;
import defpackage.nv0;
import defpackage.ol;
import defpackage.oy;
import defpackage.po;
import defpackage.r50;
import defpackage.r7;
import defpackage.ro0;
import defpackage.rp0;
import defpackage.t3;
import defpackage.tl;
import defpackage.un0;
import defpackage.w40;
import defpackage.x40;
import defpackage.xr0;
import defpackage.xu0;
import defpackage.y50;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class SearchView extends FrameLayout implements androidx.coordinatorlayout.widget.a, w40 {
    public static final int E = R$style.Widget_Material3_SearchView;
    public boolean A;
    public boolean B;
    public TransitionState C;
    public HashMap D;
    public final View b;
    public final ClippableRoundedCornerLayout c;
    public final View d;
    public final View e;
    public final FrameLayout f;
    public final FrameLayout g;
    public final MaterialToolbar h;
    public final Toolbar i;
    public final TextView j;
    public final EditText k;
    public final ImageButton l;
    public final View m;
    public final TouchObserverFrameLayout n;
    public final boolean o;
    public final g p;
    public final po q;
    public final boolean r;
    public final bn s;
    public final LinkedHashSet t;
    public SearchBar u;
    public int v;
    public boolean w;
    public boolean x;
    public boolean y;
    public final int z;

    public static class Behavior extends CoordinatorLayout.Behavior<SearchView> {
        public Behavior(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        @Override // androidx.coordinatorlayout.widget.CoordinatorLayout.Behavior
        public final boolean h(CoordinatorLayout coordinatorLayout, View view, View view2) {
            SearchView searchView = (SearchView) view;
            if (searchView.u != null || !(view2 instanceof SearchBar)) {
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
        public String d;
        public int e;

        public SavedState(Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.d = parcel.readString();
            this.e = parcel.readInt();
        }

        @Override // androidx.customview.view.AbsSavedState, android.os.Parcelable
        public final void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeString(this.d);
            parcel.writeInt(this.e);
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    public static final class TransitionState {
        public static final TransitionState HIDDEN;
        public static final TransitionState HIDING;
        public static final TransitionState SHOWING;
        public static final TransitionState SHOWN;
        public static final /* synthetic */ TransitionState[] b;

        static {
            TransitionState transitionState = new TransitionState("HIDING", 0);
            HIDING = transitionState;
            TransitionState transitionState2 = new TransitionState("HIDDEN", 1);
            HIDDEN = transitionState2;
            TransitionState transitionState3 = new TransitionState("SHOWING", 2);
            SHOWING = transitionState3;
            TransitionState transitionState4 = new TransitionState("SHOWN", 3);
            SHOWN = transitionState4;
            b = new TransitionState[]{transitionState, transitionState2, transitionState3, transitionState4};
        }

        public static TransitionState valueOf(String str) {
            return (TransitionState) Enum.valueOf(TransitionState.class, str);
        }

        public static TransitionState[] values() {
            return (TransitionState[]) b.clone();
        }
    }

    public SearchView(Context context) {
        this(context, null);
    }

    public static /* synthetic */ void e(SearchView searchView, xu0 xu0Var) {
        int iD = xu0Var.d();
        searchView.setUpStatusBarSpacer(iD);
        if (searchView.B) {
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
        SearchBar searchBar = this.u;
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
        this.e.setVisibility(z ? 0 : 8);
    }

    private void setUpBackgroundViewElevationOverlay(float f) {
        View view;
        bn bnVar = this.s;
        if (bnVar == null || (view = this.d) == null) {
            return;
        }
        view.setBackgroundColor(bnVar.b(this.z, f));
    }

    private void setUpHeaderLayout(int i) {
        if (i != -1) {
            LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
            FrameLayout frameLayout = this.f;
            frameLayout.addView(layoutInflaterFrom.inflate(i, (ViewGroup) frameLayout, false));
            frameLayout.setVisibility(0);
        }
    }

    private void setUpStatusBarSpacer(int i) {
        View view = this.e;
        if (view.getLayoutParams().height != i) {
            view.getLayoutParams().height = i;
            view.requestLayout();
        }
    }

    @Override // defpackage.w40
    public final void a(r7 r7Var) {
        if (h() || this.u == null) {
            return;
        }
        g gVar = this.p;
        SearchBar searchBar = gVar.o;
        r50 r50Var = gVar.m;
        r50Var.f = r7Var;
        View view = r50Var.b;
        r50Var.j = new Rect(view.getLeft(), view.getTop(), view.getRight(), view.getBottom());
        if (searchBar != null) {
            r50Var.k = rp0.h(view, searchBar);
        }
        r50Var.i = r7Var.b;
    }

    @Override // android.view.ViewGroup
    public final void addView(View view, int i, ViewGroup.LayoutParams layoutParams) {
        if (this.o) {
            this.n.addView(view, i, layoutParams);
        } else {
            super.addView(view, i, layoutParams);
        }
    }

    @Override // defpackage.w40
    public final void b() {
        if (h()) {
            return;
        }
        g gVar = this.p;
        r50 r50Var = gVar.m;
        r7 r7Var = r50Var.f;
        r50Var.f = null;
        if (Build.VERSION.SDK_INT < 34 || this.u == null || r7Var == null) {
            if (this.C.equals(TransitionState.HIDDEN) || this.C.equals(TransitionState.HIDING)) {
                return;
            }
            gVar.j();
            return;
        }
        long totalDuration = gVar.j().getTotalDuration();
        SearchBar searchBar = gVar.o;
        r50 r50Var2 = gVar.m;
        AnimatorSet animatorSetB = r50Var2.b(searchBar);
        animatorSetB.setDuration(totalDuration);
        animatorSetB.start();
        r50Var2.i = 0.0f;
        r50Var2.j = null;
        r50Var2.k = null;
        if (gVar.n != null) {
            gVar.c(false).start();
            gVar.n.resume();
        }
        gVar.n = null;
    }

    @Override // defpackage.w40
    public final void c(r7 r7Var) {
        if (h() || this.u == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        g gVar = this.p;
        gVar.getClass();
        float f = r7Var.c;
        if (f <= 0.0f) {
            return;
        }
        SearchBar searchBar = gVar.o;
        float cornerSize = searchBar.getCornerSize();
        r50 r50Var = gVar.m;
        if (r50Var.f == null) {
            Log.w("MaterialBackHelper", "Must call startBackProgress() before updateBackProgress()");
        }
        r7 r7Var2 = r50Var.f;
        r50Var.f = r7Var;
        if (r7Var2 != null) {
            if (searchBar.getVisibility() != 4) {
                searchBar.setVisibility(4);
            }
            boolean z = r7Var.d == 0;
            View view = r50Var.b;
            float width = view.getWidth();
            float height = view.getHeight();
            if (width > 0.0f && height > 0.0f) {
                float fA = t3.a(1.0f, 0.9f, f);
                float f2 = r50Var.g;
                float fA2 = t3.a(0.0f, Math.max(0.0f, ((width - (0.9f * width)) / 2.0f) - f2), f) * (z ? 1 : -1);
                float fMin = Math.min(Math.max(0.0f, ((height - (fA * height)) / 2.0f) - f2), r50Var.h);
                float f3 = r7Var.b - r50Var.i;
                float fA3 = t3.a(0.0f, fMin, Math.abs(f3) / height) * Math.signum(f3);
                view.setScaleX(fA);
                view.setScaleY(fA);
                view.setTranslationX(fA2);
                view.setTranslationY(fA3);
                if (view instanceof ClippableRoundedCornerLayout) {
                    ((ClippableRoundedCornerLayout) view).a(r11.getLeft(), r11.getTop(), r11.getRight(), r11.getBottom(), t3.a(r50Var.c(), cornerSize, f));
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
        if (searchView.w) {
            AnimatorSet animatorSet2 = new AnimatorSet();
            gVar.b(animatorSet2);
            animatorSet2.setDuration(250L);
            animatorSet2.setInterpolator(kf0.a(false, t3.b));
            gVar.n = animatorSet2;
            animatorSet2.start();
            gVar.n.pause();
        }
    }

    @Override // defpackage.w40
    public final void d() {
        if (h() || this.u == null || Build.VERSION.SDK_INT < 34) {
            return;
        }
        g gVar = this.p;
        SearchBar searchBar = gVar.o;
        r50 r50Var = gVar.m;
        if (r50Var.a() != null) {
            AnimatorSet animatorSetB = r50Var.b(searchBar);
            View view = r50Var.b;
            if (view instanceof ClippableRoundedCornerLayout) {
                ClippableRoundedCornerLayout clippableRoundedCornerLayout = (ClippableRoundedCornerLayout) view;
                ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(clippableRoundedCornerLayout.getCornerRadius(), r50Var.c());
                valueAnimatorOfFloat.addUpdateListener(new tl(3, clippableRoundedCornerLayout));
                animatorSetB.playTogether(valueAnimatorOfFloat);
            }
            animatorSetB.setDuration(r50Var.e);
            animatorSetB.start();
            r50Var.i = 0.0f;
            r50Var.j = null;
            r50Var.k = null;
        }
        AnimatorSet animatorSet = gVar.n;
        if (animatorSet != null) {
            animatorSet.reverse();
        }
        gVar.n = null;
    }

    public final void f() {
        this.k.post(new fh0(this, 1));
    }

    public final boolean g() {
        return this.v == 48;
    }

    public r50 getBackHelper() {
        return this.p.m;
    }

    @Override // androidx.coordinatorlayout.widget.a
    public CoordinatorLayout.Behavior<SearchView> getBehavior() {
        return new Behavior();
    }

    public TransitionState getCurrentTransitionState() {
        return this.C;
    }

    public int getDefaultNavigationIconResource() {
        return R$drawable.ic_arrow_back_black_24;
    }

    public EditText getEditText() {
        return this.k;
    }

    public CharSequence getHint() {
        return this.k.getHint();
    }

    public TextView getSearchPrefix() {
        return this.j;
    }

    public CharSequence getSearchPrefixText() {
        return this.j.getText();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getSoftInputMode() {
        return this.v;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public Editable getText() {
        return this.k.getText();
    }

    public Toolbar getToolbar() {
        return this.h;
    }

    public final boolean h() {
        return this.C.equals(TransitionState.HIDDEN) || this.C.equals(TransitionState.HIDING);
    }

    public final void i() {
        if (this.y) {
            this.k.postDelayed(new fh0(this, 0), 100L);
        }
    }

    public final void j(TransitionState transitionState, boolean z) {
        x40 x40Var;
        if (this.C.equals(transitionState)) {
            return;
        }
        if (z) {
            if (transitionState == TransitionState.SHOWN) {
                setModalForAccessibility(true);
            } else if (transitionState == TransitionState.HIDDEN) {
                setModalForAccessibility(false);
            }
        }
        this.C = transitionState;
        Iterator it = new LinkedHashSet(this.t).iterator();
        if (it.hasNext()) {
            throw hk.e(it);
        }
        if (this.u == null || !this.r) {
            return;
        }
        boolean zEquals = transitionState.equals(TransitionState.SHOWN);
        po poVar = this.q;
        if (zEquals) {
            x40 x40Var2 = (x40) poVar.c;
            if (x40Var2 != null) {
                x40Var2.b((w40) poVar.d, (FrameLayout) poVar.e, false);
                return;
            }
            return;
        }
        if (!transitionState.equals(TransitionState.HIDDEN) || (x40Var = (x40) poVar.c) == null) {
            return;
        }
        x40Var.c((FrameLayout) poVar.e);
    }

    public final void k() {
        if (this.C.equals(TransitionState.SHOWN)) {
            return;
        }
        TransitionState transitionState = this.C;
        TransitionState transitionState2 = TransitionState.SHOWING;
        if (transitionState.equals(transitionState2)) {
            return;
        }
        final g gVar = this.p;
        SearchBar searchBar = gVar.o;
        SearchView searchView = gVar.a;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = gVar.c;
        if (searchBar == null) {
            if (searchView.g()) {
                searchView.postDelayed(new fh0(searchView, 3), 150L);
            }
            clippableRoundedCornerLayout.setVisibility(4);
            final int i = 1;
            clippableRoundedCornerLayout.post(new Runnable() { // from class: sh0
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
        if (gVar.o.getMenuResId() == -1 || !searchView.x) {
            toolbar.setVisibility(8);
        } else {
            toolbar.m(gVar.o.getMenuResId());
            ActionMenuView actionMenuViewV = rp0.v(toolbar);
            if (actionMenuViewV != null) {
                for (int i2 = 0; i2 < actionMenuViewV.getChildCount(); i2++) {
                    View childAt = actionMenuViewV.getChildAt(i2);
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
        clippableRoundedCornerLayout.post(new Runnable() { // from class: sh0
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
                if (childAt.findViewById(this.c.getId()) != null) {
                    l((ViewGroup) childAt, z);
                } else if (z) {
                    this.D.put(childAt, Integer.valueOf(childAt.getImportantForAccessibility()));
                    WeakHashMap weakHashMap = xr0.a;
                    childAt.setImportantForAccessibility(4);
                } else {
                    HashMap map = this.D;
                    if (map != null && map.containsKey(childAt)) {
                        int iIntValue = ((Integer) this.D.get(childAt)).intValue();
                        WeakHashMap weakHashMap2 = xr0.a;
                        childAt.setImportantForAccessibility(iIntValue);
                    }
                }
            }
        }
    }

    public final void m() {
        ImageButton imageButtonB = rp0.B(this.h);
        if (imageButtonB == null) {
            return;
        }
        int i = this.c.getVisibility() == 0 ? 1 : 0;
        Drawable drawableQ = nv0.Q(imageButtonB.getDrawable());
        if (drawableQ instanceof ol) {
            ol olVar = (ol) drawableQ;
            float f = i;
            if (olVar.i != f) {
                olVar.i = f;
                olVar.invalidateSelf();
            }
        }
        if (drawableQ instanceof jp) {
            ((jp) drawableQ).a(i);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        oy.P(this);
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        Window activityWindow = getActivityWindow();
        if (activityWindow != null) {
            this.v = activityWindow.getAttributes().softInputMode;
        }
    }

    @Override // android.view.View
    public final void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.b);
        setText(savedState.d);
        setVisible(savedState.e == 0);
    }

    @Override // android.view.View
    public final Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Editable text = getText();
        savedState.d = text == null ? null : text.toString();
        savedState.e = this.c.getVisibility();
        return savedState;
    }

    public void setAnimatedNavigationIcon(boolean z) {
        this.w = z;
    }

    public void setAutoShowKeyboard(boolean z) {
        this.y = z;
    }

    @Override // android.view.View
    public void setElevation(float f) {
        super.setElevation(f);
        setUpBackgroundViewElevationOverlay(f);
    }

    public void setHint(CharSequence charSequence) {
        this.k.setHint(charSequence);
    }

    public void setMenuItemsAnimated(boolean z) {
        this.x = z;
    }

    public void setModalForAccessibility(boolean z) {
        ViewGroup viewGroup = (ViewGroup) getRootView();
        if (z) {
            this.D = new HashMap(viewGroup.getChildCount());
        }
        l(viewGroup, z);
        if (z) {
            return;
        }
        this.D = null;
    }

    public void setOnMenuItemClickListener(ro0 ro0Var) {
        this.h.setOnMenuItemClickListener(ro0Var);
    }

    public void setSearchPrefixText(CharSequence charSequence) {
        TextView textView = this.j;
        textView.setText(charSequence);
        textView.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
    }

    public void setStatusBarSpacerEnabled(boolean z) {
        this.B = true;
        setStatusBarSpacerEnabledInternal(z);
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setText(CharSequence charSequence) {
        this.k.setText(charSequence);
    }

    public void setToolbarTouchscreenBlocksFocus(boolean z) {
        this.h.setTouchscreenBlocksFocus(z);
    }

    public void setTransitionState(TransitionState transitionState) {
        j(transitionState, true);
    }

    public void setUseWindowInsetsController(boolean z) {
        this.A = z;
    }

    public void setVisible(boolean z) {
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.c;
        boolean z2 = clippableRoundedCornerLayout.getVisibility() == 0;
        clippableRoundedCornerLayout.setVisibility(z ? 0 : 8);
        m();
        j(z ? TransitionState.SHOWN : TransitionState.HIDDEN, z2 != z);
    }

    public void setupWithSearchBar(SearchBar searchBar) {
        this.u = searchBar;
        this.p.o = searchBar;
        if (searchBar != null) {
            searchBar.setOnClickListener(new eh0(this, 1));
            if (Build.VERSION.SDK_INT >= 34) {
                try {
                    searchBar.setHandwritingDelegatorCallback(new fh0(this, 2));
                    this.k.setIsHandwritingDelegate(true);
                } catch (LinkageError unused) {
                }
            }
        }
        MaterialToolbar materialToolbar = this.h;
        if (materialToolbar != null && !(nv0.Q(materialToolbar.getNavigationIcon()) instanceof ol)) {
            int defaultNavigationIconResource = getDefaultNavigationIconResource();
            if (this.u == null) {
                materialToolbar.setNavigationIcon(defaultNavigationIconResource);
            } else {
                Drawable drawableMutate = rp0.y(getContext(), defaultNavigationIconResource).mutate();
                if (materialToolbar.getNavigationIconTint() != null) {
                    hl.g(drawableMutate, materialToolbar.getNavigationIconTint().intValue());
                }
                materialToolbar.setNavigationIcon(new jp(this.u.getNavigationIcon(), drawableMutate));
                m();
            }
        }
        setUpBackgroundViewElevationOverlay(getOverlayElevation());
    }

    public SearchView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.materialSearchViewStyle);
    }

    public void setHint(int i) {
        this.k.setHint(i);
    }

    public void setText(int i) {
        this.k.setText(i);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public SearchView(Context context, AttributeSet attributeSet, int i) {
        int i2 = E;
        super(y50.a(context, attributeSet, i, i2), attributeSet, i);
        this.q = new po(this);
        this.t = new LinkedHashSet();
        this.v = 16;
        this.C = TransitionState.HIDDEN;
        Context context2 = getContext();
        TypedArray typedArrayD = un0.d(context2, attributeSet, R$styleable.SearchView, i, i2, new int[0]);
        this.z = typedArrayD.getColor(R$styleable.SearchView_backgroundTint, 0);
        int resourceId = typedArrayD.getResourceId(R$styleable.SearchView_headerLayout, -1);
        int resourceId2 = typedArrayD.getResourceId(R$styleable.SearchView_android_textAppearance, -1);
        String string = typedArrayD.getString(R$styleable.SearchView_android_text);
        String string2 = typedArrayD.getString(R$styleable.SearchView_android_hint);
        String string3 = typedArrayD.getString(R$styleable.SearchView_searchPrefixText);
        boolean z = typedArrayD.getBoolean(R$styleable.SearchView_useDrawerArrowDrawable, false);
        this.w = typedArrayD.getBoolean(R$styleable.SearchView_animateNavigationIcon, true);
        this.x = typedArrayD.getBoolean(R$styleable.SearchView_animateMenuItems, true);
        boolean z2 = typedArrayD.getBoolean(R$styleable.SearchView_hideNavigationIcon, false);
        this.y = typedArrayD.getBoolean(R$styleable.SearchView_autoShowKeyboard, true);
        this.r = typedArrayD.getBoolean(R$styleable.SearchView_backHandlingEnabled, true);
        typedArrayD.recycle();
        LayoutInflater.from(context2).inflate(R$layout.mtrl_search_view, this);
        this.o = true;
        this.b = findViewById(R$id.open_search_view_scrim);
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = (ClippableRoundedCornerLayout) findViewById(R$id.open_search_view_root);
        this.c = clippableRoundedCornerLayout;
        this.d = findViewById(R$id.open_search_view_background);
        View viewFindViewById = findViewById(R$id.open_search_view_status_bar_spacer);
        this.e = viewFindViewById;
        this.f = (FrameLayout) findViewById(R$id.open_search_view_header_container);
        this.g = (FrameLayout) findViewById(R$id.open_search_view_toolbar_container);
        MaterialToolbar materialToolbar = (MaterialToolbar) findViewById(R$id.open_search_view_toolbar);
        this.h = materialToolbar;
        this.i = (Toolbar) findViewById(R$id.open_search_view_dummy_toolbar);
        this.j = (TextView) findViewById(R$id.open_search_view_search_prefix);
        EditText editText = (EditText) findViewById(R$id.open_search_view_edit_text);
        this.k = editText;
        ImageButton imageButton = (ImageButton) findViewById(R$id.open_search_view_clear_button);
        this.l = imageButton;
        View viewFindViewById2 = findViewById(R$id.open_search_view_divider);
        this.m = viewFindViewById2;
        TouchObserverFrameLayout touchObserverFrameLayout = (TouchObserverFrameLayout) findViewById(R$id.open_search_view_content_container);
        this.n = touchObserverFrameLayout;
        this.p = new g(this);
        this.s = new bn(context2);
        clippableRoundedCornerLayout.setOnTouchListener(new ih0());
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
            materialToolbar.setNavigationOnClickListener(new eh0(this, 2));
            if (z) {
                ol olVar = new ol(getContext());
                int iQ = k9.q(this, R$attr.colorOnSurface);
                Paint paint = olVar.a;
                if (iQ != paint.getColor()) {
                    paint.setColor(iQ);
                    olVar.invalidateSelf();
                }
                materialToolbar.setNavigationIcon(olVar);
            }
        }
        imageButton.setOnClickListener(new eh0(this, 0));
        editText.addTextChangedListener(new jh0(this, 1));
        touchObserverFrameLayout.setOnTouchListener(new em(1, this));
        rp0.s(materialToolbar, new hh0(this));
        final ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewFindViewById2.getLayoutParams();
        final int i3 = marginLayoutParams.leftMargin;
        final int i4 = marginLayoutParams.rightMargin;
        ha0 ha0Var = new ha0() { // from class: gh0
            @Override // defpackage.ha0
            public final xu0 e(View view, xu0 xu0Var) {
                int i5 = SearchView.E;
                int iB = xu0Var.b() + i3;
                ViewGroup.MarginLayoutParams marginLayoutParams2 = marginLayoutParams;
                marginLayoutParams2.leftMargin = iB;
                marginLayoutParams2.rightMargin = xu0Var.c() + i4;
                return xu0Var;
            }
        };
        WeakHashMap weakHashMap = xr0.a;
        lr0.u(viewFindViewById2, ha0Var);
        setUpStatusBarSpacer(getStatusBarHeight());
        lr0.u(viewFindViewById, new hh0(this));
    }
}
