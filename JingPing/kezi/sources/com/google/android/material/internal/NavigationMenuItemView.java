package com.google.android.material.internal;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.CheckedTextView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.appcompat.R$attr;
import androidx.appcompat.widget.LinearLayoutCompat;
import com.google.android.material.R$dimen;
import com.google.android.material.R$drawable;
import com.google.android.material.R$id;
import com.google.android.material.R$layout;
import defpackage.a70;
import defpackage.cf0;
import defpackage.hl;
import defpackage.j9;
import defpackage.jq0;
import defpackage.m60;
import defpackage.we0;
import defpackage.xr0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements a70 {
    public static final int[] H = {R.attr.state_checked};
    public final CheckedTextView A;
    public FrameLayout B;
    public m60 C;
    public ColorStateList D;
    public boolean E;
    public Drawable F;
    public final j9 G;
    public int w;
    public boolean x;
    public boolean y;
    public boolean z;

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.B == null) {
                this.B = (FrameLayout) ((ViewStub) findViewById(R$id.design_menu_item_action_area_stub)).inflate();
            }
            this.B.removeAllViews();
            this.B.addView(view);
        }
    }

    @Override // defpackage.a70
    public final void d(m60 m60Var) {
        StateListDrawable stateListDrawable;
        this.C = m60Var;
        int i = m60Var.a;
        if (i > 0) {
            setId(i);
        }
        setVisibility(m60Var.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(R$attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(H, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            WeakHashMap weakHashMap = xr0.a;
            setBackground(stateListDrawable);
        }
        setCheckable(m60Var.isCheckable());
        setChecked(m60Var.isChecked());
        setEnabled(m60Var.isEnabled());
        setTitle(m60Var.e);
        setIcon(m60Var.getIcon());
        setActionView(m60Var.getActionView());
        setContentDescription(m60Var.q);
        jq0.d0(this, m60Var.r);
        m60 m60Var2 = this.C;
        CharSequence charSequence = m60Var2.e;
        CheckedTextView checkedTextView = this.A;
        if (charSequence == null && m60Var2.getIcon() == null && this.C.getActionView() != null) {
            checkedTextView.setVisibility(8);
            FrameLayout frameLayout = this.B;
            if (frameLayout != null) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).width = -1;
                this.B.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        checkedTextView.setVisibility(0);
        FrameLayout frameLayout2 = this.B;
        if (frameLayout2 != null) {
            LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) layoutParams2).width = -2;
            this.B.setLayoutParams(layoutParams2);
        }
    }

    @Override // defpackage.a70
    public m60 getItemData() {
        return this.C;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        m60 m60Var = this.C;
        if (m60Var != null && m60Var.isCheckable() && this.C.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, H);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.y != z) {
            this.y = z;
            this.G.h(this.A, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        CheckedTextView checkedTextView = this.A;
        checkedTextView.setChecked(z);
        checkedTextView.setTypeface(checkedTextView.getTypeface(), (z && this.z) ? 1 : 0);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, getPaddingTop(), i, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.E) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                hl.h(drawable, this.D);
            }
            int i = this.w;
            drawable.setBounds(0, 0, i, i);
        } else if (this.x) {
            if (this.F == null) {
                Resources resources = getResources();
                int i2 = R$drawable.navigation_empty_icon;
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal threadLocal = cf0.a;
                Drawable drawableA = we0.a(resources, i2, theme);
                this.F = drawableA;
                if (drawableA != null) {
                    int i3 = this.w;
                    drawableA.setBounds(0, 0, i3, i3);
                }
            }
            drawable = this.F;
        }
        this.A.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.A.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.w = i;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.D = colorStateList;
        this.E = colorStateList != null;
        m60 m60Var = this.C;
        if (m60Var != null) {
            setIcon(m60Var.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.A.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.x = z;
    }

    public void setTextAppearance(int i) {
        this.A.setTextAppearance(i);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.A.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.A.setText(charSequence);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.z = true;
        j9 j9Var = new j9(4, this);
        this.G = j9Var;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R$layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R$dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R$id.design_menu_item_text);
        this.A = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        xr0.p(checkedTextView, j9Var);
    }
}
