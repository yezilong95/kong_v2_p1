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
import defpackage.f3;
import defpackage.f51;
import defpackage.h61;
import defpackage.l3;
import defpackage.p50;
import defpackage.q61;
import defpackage.qm;
import defpackage.se0;
import defpackage.u;
import defpackage.y2;
import defpackage.zd0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ActionBarContextView extends ViewGroup {
    public final u E;
    public final Context F;
    public ActionMenuView G;
    public b H;
    public int I;
    public h61 J;
    public boolean K;
    public boolean L;
    public CharSequence M;
    public CharSequence N;
    public View O;
    public View P;
    public View Q;
    public LinearLayout R;
    public TextView S;
    public TextView T;
    public final int U;
    public final int V;
    public boolean W;
    public final int a0;

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

    public final void c(l3 l3Var) {
        View view = this.O;
        int i = 0;
        if (view == null) {
            View viewInflate = LayoutInflater.from(getContext()).inflate(this.a0, (ViewGroup) this, false);
            this.O = viewInflate;
            addView(viewInflate);
        } else if (view.getParent() == null) {
            addView(this.O);
        }
        View viewFindViewById = this.O.findViewById(R$id.action_mode_close_button);
        this.P = viewFindViewById;
        viewFindViewById.setOnClickListener(new y2(i, l3Var));
        zd0 zd0VarC = l3Var.c();
        b bVar = this.H;
        if (bVar != null) {
            bVar.d();
            f3 f3Var = bVar.Y;
            if (f3Var != null && f3Var.b()) {
                f3Var.i.dismiss();
            }
        }
        b bVar2 = new b(getContext());
        this.H = bVar2;
        bVar2.Q = true;
        bVar2.R = true;
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -1);
        zd0VarC.b(this.H, this.F);
        b bVar3 = this.H;
        se0 se0Var = bVar3.L;
        if (se0Var == null) {
            se0 se0Var2 = (se0) bVar3.H.inflate(bVar3.J, (ViewGroup) this, false);
            bVar3.L = se0Var2;
            se0Var2.c(bVar3.G);
            bVar3.j(true);
        }
        se0 se0Var3 = bVar3.L;
        if (se0Var != se0Var3) {
            ((ActionMenuView) se0Var3).setPresenter(bVar3);
        }
        ActionMenuView actionMenuView = (ActionMenuView) se0Var3;
        this.G = actionMenuView;
        WeakHashMap weakHashMap = f51.a;
        actionMenuView.setBackground(null);
        addView(this.G, layoutParams);
    }

    public final void d() {
        if (this.R == null) {
            LayoutInflater.from(getContext()).inflate(R$layout.abc_action_bar_title_item, this);
            LinearLayout linearLayout = (LinearLayout) getChildAt(getChildCount() - 1);
            this.R = linearLayout;
            this.S = (TextView) linearLayout.findViewById(R$id.action_bar_title);
            this.T = (TextView) this.R.findViewById(R$id.action_bar_subtitle);
            int i = this.U;
            if (i != 0) {
                this.S.setTextAppearance(getContext(), i);
            }
            int i2 = this.V;
            if (i2 != 0) {
                this.T.setTextAppearance(getContext(), i2);
            }
        }
        this.S.setText(this.M);
        this.T.setText(this.N);
        boolean zIsEmpty = TextUtils.isEmpty(this.M);
        boolean zIsEmpty2 = TextUtils.isEmpty(this.N);
        this.T.setVisibility(!zIsEmpty2 ? 0 : 8);
        this.R.setVisibility((zIsEmpty && zIsEmpty2) ? 8 : 0);
        if (this.R.getParent() == null) {
            addView(this.R);
        }
    }

    public final void e() {
        removeAllViews();
        this.Q = null;
        this.G = null;
        this.H = null;
        View view = this.P;
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
        return this.J != null ? this.E.b : getVisibility();
    }

    public int getContentHeight() {
        return this.I;
    }

    public CharSequence getSubtitle() {
        return this.N;
    }

    public CharSequence getTitle() {
        return this.M;
    }

    @Override // android.view.View
    /* JADX INFO: renamed from: h, reason: merged with bridge method [inline-methods] */
    public final void setVisibility(int i) {
        if (i != getVisibility()) {
            h61 h61Var = this.J;
            if (h61Var != null) {
                h61Var.b();
            }
            super.setVisibility(i);
        }
    }

    public final h61 i(int i, long j) {
        h61 h61Var = this.J;
        if (h61Var != null) {
            h61Var.b();
        }
        u uVar = this.E;
        if (i != 0) {
            h61 h61VarA = f51.a(this);
            h61VarA.a(0.0f);
            h61VarA.c(j);
            ((ActionBarContextView) uVar.c).J = h61VarA;
            uVar.b = i;
            h61VarA.d(uVar);
            return h61VarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        h61 h61VarA2 = f51.a(this);
        h61VarA2.a(1.0f);
        h61VarA2.c(j);
        ((ActionBarContextView) uVar.c).J = h61VarA2;
        uVar.b = i;
        h61VarA2.d(uVar);
        return h61VarA2;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        b bVar = this.H;
        if (bVar != null) {
            bVar.U = p50.t(bVar.F).z();
            zd0 zd0Var = bVar.G;
            if (zd0Var != null) {
                zd0Var.p(true);
            }
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        b bVar = this.H;
        if (bVar != null) {
            bVar.d();
            f3 f3Var = this.H.Y;
            if (f3Var == null || !f3Var.b()) {
                return;
            }
            f3Var.i.dismiss();
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.L = false;
        }
        if (!this.L) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.L = true;
            }
        }
        if (actionMasked != 10 && actionMasked != 3) {
            return true;
        }
        this.L = false;
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onLayout(boolean z, int i, int i2, int i3, int i4) {
        boolean zA = q61.a(this);
        int paddingRight = zA ? (i3 - i) - getPaddingRight() : getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingTop2 = ((i4 - i2) - getPaddingTop()) - getPaddingBottom();
        View view = this.O;
        if (view != null && view.getVisibility() != 8) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.O.getLayoutParams();
            int i5 = zA ? marginLayoutParams.rightMargin : marginLayoutParams.leftMargin;
            int i6 = zA ? marginLayoutParams.leftMargin : marginLayoutParams.rightMargin;
            int i7 = zA ? paddingRight - i5 : paddingRight + i5;
            int iG = g(i7, paddingTop, paddingTop2, this.O, zA) + i7;
            paddingRight = zA ? iG - i6 : iG + i6;
        }
        LinearLayout linearLayout = this.R;
        if (linearLayout != null && this.Q == null && linearLayout.getVisibility() != 8) {
            paddingRight += g(paddingRight, paddingTop, paddingTop2, this.R, zA);
        }
        View view2 = this.Q;
        if (view2 != null) {
            g(paddingRight, paddingTop, paddingTop2, view2, zA);
        }
        int paddingLeft = zA ? getPaddingLeft() : (i3 - i) - getPaddingRight();
        ActionMenuView actionMenuView = this.G;
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
        int size2 = this.I;
        if (size2 <= 0) {
            size2 = View.MeasureSpec.getSize(i2);
        }
        int paddingBottom = getPaddingBottom() + getPaddingTop();
        int paddingLeft = (size - getPaddingLeft()) - getPaddingRight();
        int iMin = size2 - paddingBottom;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(iMin, Integer.MIN_VALUE);
        View view = this.O;
        if (view != null) {
            int iF = f(view, paddingLeft, iMakeMeasureSpec);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) this.O.getLayoutParams();
            paddingLeft = iF - (marginLayoutParams.leftMargin + marginLayoutParams.rightMargin);
        }
        ActionMenuView actionMenuView = this.G;
        if (actionMenuView != null && actionMenuView.getParent() == this) {
            paddingLeft = f(this.G, paddingLeft, iMakeMeasureSpec);
        }
        LinearLayout linearLayout = this.R;
        if (linearLayout != null && this.Q == null) {
            if (this.W) {
                this.R.measure(View.MeasureSpec.makeMeasureSpec(0, 0), iMakeMeasureSpec);
                int measuredWidth = this.R.getMeasuredWidth();
                boolean z = measuredWidth <= paddingLeft;
                if (z) {
                    paddingLeft -= measuredWidth;
                }
                this.R.setVisibility(z ? 0 : 8);
            } else {
                paddingLeft = f(linearLayout, paddingLeft, iMakeMeasureSpec);
            }
        }
        View view2 = this.Q;
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
            this.Q.measure(View.MeasureSpec.makeMeasureSpec(paddingLeft, i4), View.MeasureSpec.makeMeasureSpec(iMin, i6));
        }
        if (this.I > 0) {
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
            this.K = false;
        }
        if (!this.K) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.K = true;
            }
        }
        if (actionMasked != 1 && actionMasked != 3) {
            return true;
        }
        this.K = false;
        return true;
    }

    public void setContentHeight(int i) {
        this.I = i;
    }

    public void setCustomView(View view) {
        LinearLayout linearLayout;
        View view2 = this.Q;
        if (view2 != null) {
            removeView(view2);
        }
        this.Q = view;
        if (view != null && (linearLayout = this.R) != null) {
            removeView(linearLayout);
            this.R = null;
        }
        if (view != null) {
            addView(view);
        }
        requestLayout();
    }

    public void setSubtitle(CharSequence charSequence) {
        this.N = charSequence;
        d();
    }

    public void setTitle(CharSequence charSequence) {
        this.M = charSequence;
        d();
        f51.p(this, charSequence);
    }

    public void setTitleOptional(boolean z) {
        if (z != this.W) {
            requestLayout();
        }
        this.W = z;
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
        u uVar = new u();
        uVar.c = this;
        uVar.a = false;
        this.E = uVar;
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) && typedValue.resourceId != 0) {
            this.F = new ContextThemeWrapper(context, typedValue.resourceId);
        } else {
            this.F = context;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.ActionMode, i, 0);
        int i2 = R$styleable.ActionMode_background;
        if (typedArrayObtainStyledAttributes.hasValue(i2) && (resourceId = typedArrayObtainStyledAttributes.getResourceId(i2, 0)) != 0) {
            drawable = qm.z(context, resourceId);
        } else {
            drawable = typedArrayObtainStyledAttributes.getDrawable(i2);
        }
        WeakHashMap weakHashMap = f51.a;
        setBackground(drawable);
        this.U = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ActionMode_titleTextStyle, 0);
        this.V = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ActionMode_subtitleTextStyle, 0);
        this.I = typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionMode_height, 0);
        this.a0 = typedArrayObtainStyledAttributes.getResourceId(R$styleable.ActionMode_closeItemLayout, R$layout.abc_action_mode_close_item_material);
        typedArrayObtainStyledAttributes.recycle();
    }
}
