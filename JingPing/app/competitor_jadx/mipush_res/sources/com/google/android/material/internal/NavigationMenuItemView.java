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
import defpackage.bp0;
import defpackage.de0;
import defpackage.f51;
import defpackage.hb;
import defpackage.ip;
import defpackage.la0;
import defpackage.re0;
import defpackage.vo0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuItemView extends ForegroundLinearLayout implements re0 {
    public static final int[] n0 = {R.attr.state_checked};
    public int c0;
    public boolean d0;
    public boolean e0;
    public boolean f0;
    public final CheckedTextView g0;
    public FrameLayout h0;
    public de0 i0;
    public ColorStateList j0;
    public boolean k0;
    public Drawable l0;
    public final hb m0;

    public NavigationMenuItemView(Context context) {
        this(context, null);
    }

    private void setActionView(View view) {
        if (view != null) {
            if (this.h0 == null) {
                this.h0 = (FrameLayout) ((ViewStub) findViewById(R$id.design_menu_item_action_area_stub)).inflate();
            }
            if (view.getParent() != null) {
                ((ViewGroup) view.getParent()).removeView(view);
            }
            this.h0.removeAllViews();
            this.h0.addView(view);
        }
    }

    @Override // defpackage.re0
    public final void d(de0 de0Var) {
        StateListDrawable stateListDrawable;
        this.i0 = de0Var;
        int i = de0Var.a;
        if (i > 0) {
            setId(i);
        }
        setVisibility(de0Var.isVisible() ? 0 : 8);
        if (getBackground() == null) {
            TypedValue typedValue = new TypedValue();
            if (getContext().getTheme().resolveAttribute(R$attr.colorControlHighlight, typedValue, true)) {
                stateListDrawable = new StateListDrawable();
                stateListDrawable.addState(n0, new ColorDrawable(typedValue.data));
                stateListDrawable.addState(ViewGroup.EMPTY_STATE_SET, new ColorDrawable(0));
            } else {
                stateListDrawable = null;
            }
            WeakHashMap weakHashMap = f51.a;
            setBackground(stateListDrawable);
        }
        setCheckable(de0Var.isCheckable());
        setChecked(de0Var.isChecked());
        setEnabled(de0Var.isEnabled());
        setTitle(de0Var.e);
        setIcon(de0Var.getIcon());
        setActionView(de0Var.getActionView());
        setContentDescription(de0Var.q);
        la0.e0(this, de0Var.r);
        de0 de0Var2 = this.i0;
        CharSequence charSequence = de0Var2.e;
        CheckedTextView checkedTextView = this.g0;
        if (charSequence == null && de0Var2.getIcon() == null && this.i0.getActionView() != null) {
            checkedTextView.setVisibility(8);
            FrameLayout frameLayout = this.h0;
            if (frameLayout != null) {
                LinearLayoutCompat.LayoutParams layoutParams = (LinearLayoutCompat.LayoutParams) frameLayout.getLayoutParams();
                ((LinearLayout.LayoutParams) layoutParams).width = -1;
                this.h0.setLayoutParams(layoutParams);
                return;
            }
            return;
        }
        checkedTextView.setVisibility(0);
        FrameLayout frameLayout2 = this.h0;
        if (frameLayout2 != null) {
            LinearLayoutCompat.LayoutParams layoutParams2 = (LinearLayoutCompat.LayoutParams) frameLayout2.getLayoutParams();
            ((LinearLayout.LayoutParams) layoutParams2).width = -2;
            this.h0.setLayoutParams(layoutParams2);
        }
    }

    @Override // defpackage.re0
    public de0 getItemData() {
        return this.i0;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final int[] onCreateDrawableState(int i) {
        int[] iArrOnCreateDrawableState = super.onCreateDrawableState(i + 1);
        de0 de0Var = this.i0;
        if (de0Var != null && de0Var.isCheckable() && this.i0.isChecked()) {
            View.mergeDrawableStates(iArrOnCreateDrawableState, n0);
        }
        return iArrOnCreateDrawableState;
    }

    public void setCheckable(boolean z) {
        refreshDrawableState();
        if (this.e0 != z) {
            this.e0 = z;
            this.m0.h(this.g0, 2048);
        }
    }

    public void setChecked(boolean z) {
        refreshDrawableState();
        CheckedTextView checkedTextView = this.g0;
        checkedTextView.setChecked(z);
        checkedTextView.setTypeface(checkedTextView.getTypeface(), (z && this.f0) ? 1 : 0);
    }

    public void setHorizontalPadding(int i) {
        setPadding(i, getPaddingTop(), i, getPaddingBottom());
    }

    public void setIcon(Drawable drawable) {
        if (drawable != null) {
            if (this.k0) {
                Drawable.ConstantState constantState = drawable.getConstantState();
                if (constantState != null) {
                    drawable = constantState.newDrawable();
                }
                drawable = drawable.mutate();
                ip.h(drawable, this.j0);
            }
            int i = this.c0;
            drawable.setBounds(0, 0, i, i);
        } else if (this.d0) {
            if (this.l0 == null) {
                Resources resources = getResources();
                int i2 = R$drawable.navigation_empty_icon;
                Resources.Theme theme = getContext().getTheme();
                ThreadLocal threadLocal = bp0.a;
                Drawable drawableA = vo0.a(resources, i2, theme);
                this.l0 = drawableA;
                if (drawableA != null) {
                    int i3 = this.c0;
                    drawableA.setBounds(0, 0, i3, i3);
                }
            }
            drawable = this.l0;
        }
        this.g0.setCompoundDrawablesRelative(drawable, null, null, null);
    }

    public void setIconPadding(int i) {
        this.g0.setCompoundDrawablePadding(i);
    }

    public void setIconSize(int i) {
        this.c0 = i;
    }

    public void setIconTintList(ColorStateList colorStateList) {
        this.j0 = colorStateList;
        this.k0 = colorStateList != null;
        de0 de0Var = this.i0;
        if (de0Var != null) {
            setIcon(de0Var.getIcon());
        }
    }

    public void setMaxLines(int i) {
        this.g0.setMaxLines(i);
    }

    public void setNeedsEmptyIcon(boolean z) {
        this.d0 = z;
    }

    public void setTextAppearance(int i) {
        this.g0.setTextAppearance(i);
    }

    public void setTextColor(ColorStateList colorStateList) {
        this.g0.setTextColor(colorStateList);
    }

    public void setTitle(CharSequence charSequence) {
        this.g0.setText(charSequence);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public NavigationMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f0 = true;
        hb hbVar = new hb(4, this);
        this.m0 = hbVar;
        setOrientation(0);
        LayoutInflater.from(context).inflate(R$layout.design_navigation_menu_item, (ViewGroup) this, true);
        setIconSize(context.getResources().getDimensionPixelSize(R$dimen.design_navigation_icon_size));
        CheckedTextView checkedTextView = (CheckedTextView) findViewById(R$id.design_menu_item_text);
        this.g0 = checkedTextView;
        checkedTextView.setDuplicateParentStateEnabled(true);
        f51.o(checkedTextView, hbVar);
    }
}
