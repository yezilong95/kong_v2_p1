package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import defpackage.b70;
import defpackage.bt0;
import defpackage.c1;
import defpackage.g1;
import defpackage.i60;
import defpackage.k1;
import defpackage.kt0;
import defpackage.q1;
import defpackage.rp0;
import defpackage.xr0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {
    public final defpackage.c b;
    public final Context c;
    public ActionMenuView d;
    public b e;
    public int f;
    public bt0 g;
    public boolean h;
    public boolean i;
    public CharSequence j;
    public CharSequence k;
    public View l;
    public View m;
    public View n;
    public LinearLayout o;
    public TextView p;
    public TextView q;
    public final int r;
    public final int s;
    public boolean t;
    public final int u;

    public ActionBarContextView(Context context) {
        this(context, null);
    }

    public static int f(View view, int i, int i2) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, i - view.getMeasuredWidth());
    }

    public static int g(int i, int i2, int i3, View view, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public final void c(q1 q1Var) {
        View view = this.l;
        int i = 0;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.u, (ViewGroup) this, false);
            this.l = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.l);
        }
        View viewFindViewById = this.l.findViewById(R$id.action_mode_close_button);
        this.m = viewFindViewById;
        viewFindViewById.setOnClickListener(new c1(i, q1Var));
        i60 i60VarC = q1Var.c();
        b bVar = this.e;
        if (bVar != null) {
            bVar.f();
            k1 k1Var = bVar.v;
            if (k1Var != null && k1Var.b()) {
                k1Var.i.dismiss();
            }
        }
        b bVar2 = new b(getContext());
        this.e = bVar2;
        bVar2.n = true;
        bVar2.o = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        i60VarC.b(this.e, this.c);
        b bVar3 = this.e;
        b70 b70Var = bVar3.i;
        if (b70Var == null) {
            b70 b70Var2 = (b70) bVar3.e.inflate(bVar3.g, (ViewGroup) this, false);
            bVar3.i = b70Var2;
            b70Var2.c(bVar3.d);
            bVar3.l(true);
        }
        b70 b70Var3 = bVar3.i;
        if (b70Var != b70Var3) {
            ((ActionMenuView) b70Var3).setPresenter(bVar3);
        }
        ActionMenuView actionMenuView = (ActionMenuView) b70Var3;
        this.d = actionMenuView;
        WeakHashMap weakHashMap = xr0.a;
        actionMenuView.setBackground(null);
        addView(this.d, layoutParams);
    }

    public final void d() {
        if (this.o == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.o = linearLayout;
            this.p = (TextView) linearLayout.findViewById(R$id.action_bar_title);
            this.q = (TextView) this.o.findViewById(R$id.action_bar_subtitle);
            int i = this.r;
            if (i != 0) {
                this.p.setTextAppearance(getContext(), i);
            }
            int i2 = this.s;
            if (i2 != 0) {
                this.q.setTextAppearance(getContext(), i2);
            }
        }
        this.p.setText(this.j);
        this.q.setText(this.k);
        boolean zIsEmpty = TextUtils.isEmpty(this.j);
        boolean zIsEmpty2 = TextUtils.isEmpty(this.k);
        this.q.setVisibility(!zIsEmpty2 ? 0 : 8);
        this.o.setVisibility((zIsEmpty && zIsEmpty2) ? 8 : 0);
        if (this.o.getParent() == null) {
            addView(this.o);
        }
    }

    public final void e() {
        removeAllViews();
        this.n = null;
        this.d = null;
        this.e = null;
        View view = this.m;
        if (view != null) {
            view.setOnClickListener(null);
        }
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup.MarginLayoutParams(-1, -2);
    }

    @Override // android.view.ViewGroup
    public final ViewGroup.LayoutParams generateLayoutParams(AttributeSet attributeSet) {
        return new ViewGroup.MarginLayoutParams(getContext(), attributeSet);
    }

    public int getAnimatedVisibility() {
        return this.g != null ? this.b.b : getVisibility();
    }

    public int getContentHeight() {
        return this.f;
    }

    public CharSequence getSubtitle() {
        return this.k;
    }

    public CharSequence getTitle() {
        return this.j;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i) {
        if (i != getVisibility()) {
            bt0 bt0Var = this.g;
            if (bt0Var != null) {
                bt0Var.b();
            }
            super.setVisibility(i);
        }
    }

    public final bt0 i(long j, int i) {
        bt0 bt0Var = this.g;
        if (bt0Var != null) {
            bt0Var.b();
        }
        defpackage.c cVar = this.b;
        if (i != 0) {
            bt0 bt0VarA = xr0.a(this);
            bt0VarA.a(0.0f);
            bt0VarA.c(j);
            ((ActionBarContextView) cVar.c).g = bt0VarA;
            cVar.b = i;
            bt0VarA.d(cVar);
            return bt0VarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        bt0 bt0VarA2 = xr0.a(this);
        bt0VarA2.a(1.0f);
        bt0VarA2.c(j);
        ((ActionBarContextView) cVar.c).g = bt0VarA2;
        cVar.b = i;
        bt0VarA2.d(cVar);
        return bt0VarA2;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        b bVar = this.e;
        if (bVar != null) {
            bVar.r = g1.b(bVar.c).c();
            i60 i60Var = bVar.d;
            if (i60Var != null) {
                i60Var.p(true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.e;
        if (bVar != null) {
            bVar.f();
            k1 k1Var = this.e.v;
            if (k1Var == null || !k1Var.b()) {
                return;
            }
            k1Var.i.dismiss();
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.i = false;
        }
        if (!this.i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.i = true;
            }
        }
        if (actionMasked != 10 && actionMasked != 3) {
            return true;
        }
        this.i = false;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean zA = kt0.a(this);
        int paddingRight = zA ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.l;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.l.getLayoutParams();
            int i5 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i7 = zA ? paddingRight - i5 : paddingRight + i5;
            int iG = g(i7, paddingTop, paddingTop2, this.l, zA) + i7;
            paddingRight = zA ? iG - i6 : iG + i6;
        }
        LinearLayout linearLayout = this.o;
        if (linearLayout != null && this.n == null && linearLayout.getVisibility() != 8) {
            paddingRight += g(paddingRight, paddingTop, paddingTop2, this.o, zA);
        }
        View view2 = this.n;
        if (view2 != null) {
            g(paddingRight, paddingTop, paddingTop2, view2, zA);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.d;
        if (actionMenuView != null) {
            g(paddingLeft, paddingTop, paddingTop2, actionMenuView, !zA);
        }
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        if (View.MeasureSpec.getMode(i) != 1073741824) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_width=\"match_parent\" (or fill_parent)"));
        }
        if (View.MeasureSpec.getMode(i2) == 0) {
            throw new IllegalStateException(getClass().getSimpleName().concat(" can only be used with android:layout_height=\"wrap_content\""));
        }
        int size = View.MeasureSpec.getSize(i);
        int size2 = this.f;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i2);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.l;
        if (view != null) {
            int iF = f(view, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.l.getLayoutParams();
            paddingLeft = iF - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.d;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = f(this.d, paddingLeft, iMakeMeasureSpec);
        }
        LinearLayout linearLayout = this.o;
        if (linearLayout != null && this.n == null) {
            if (this.t) {
                this.o.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.o.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.o.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = f(linearLayout, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view2 = this.n;
        if (view2 != null) {
            ViewGroup.LayoutParams layoutParams = view2.getLayoutParams();
            int i3 = layoutParams.width;
            int i4 = i3 != -2 ? 1073741824 : Integer.MIN_VALUE;
            if (i3 >= 0) {
                paddingLeft = Math.min(i3, paddingLeft);
            }
            int i5 = layoutParams.height;
            int i6 = i5 == -2 ? Integer.MIN_VALUE : 1073741824;
            if (i5 >= 0) {
                iMin = Math.min(i5, iMin);
            }
            this.n.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i4), View.MeasureSpec.makeMeasureSpec(iMin, i6));
        }
        if (this.f > 0) {
            setMeasuredDimension(size, size2);
            return;
        }
        int childCount = getChildCount();
        int i7 = 0;
        for (int i8 = 0; i8 < childCount; i8++) {
            int measuredHeight = getChildAt(i8).getMeasuredHeight() + paddingBottom;
            if (measuredHeight > i7) {
                i7 = measuredHeight;
            }
        }
        setMeasuredDimension(size, i7);
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.h = false;
        }
        if (!this.h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.h = true;
            }
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return true;
        }
        this.h = false;
        return true;
    }

    public void setContentHeight(int i) {
        this.f = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.n;
        if (view2 != null) {
            removeView(view2);
        }
        this.n = view;
        if (view != null && (linearLayout = this.o) != null) {
            removeView(linearLayout);
            this.o = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.k = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.j = charSequence;
        d();
        xr0.q(this, charSequence);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.t) {
            requestLayout();
        }
        this.t = z;
    }

    @Override // android.view.ViewGroup
    public final boolean shouldDelayChildPressedState() {
        return false;
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.actionModeStyle);
    }

    public ActionBarContextView(Context context, AttributeSet attributeSet, int i) {
        Drawable drawable;
        int resourceId;
        super(context, attributeSet, i);
        this.b = new defpackage.c(this);
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.c = new ContextThemeWrapper(context, typedValue.resourceId);
        } else {
            this.c = context;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMode, i, 0);
        int i2 = R$styleable.ActionMode_background;
        if (typedArrayObtainStyledAttributes.hasValue(i2) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i2, 0)) != 0) {
            drawable = rp0.y(context, resourceId);
        } else {
            drawable = typedArrayObtainStyledAttributes.getDrawable(i2);
        }
        WeakHashMap weakHashMap = xr0.a;
        setBackground(drawable);
        this.r = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ActionMode_titleTextStyle, 0);
        this.s = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.f = typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionMode_height, 0);
        this.u = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        typedArrayObtainStyledAttributes.recycle();
    }
}
