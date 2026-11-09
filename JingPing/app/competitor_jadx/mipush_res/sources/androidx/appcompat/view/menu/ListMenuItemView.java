package androidx.appcompat.view.menu;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$id;
import androidx.appcompat.R$layout;
import androidx.appcompat.R$styleable;
import defpackage.de0;
import defpackage.f51;
import defpackage.ou;
import defpackage.re0;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class ListMenuItemView extends LinearLayout implements re0, AbsListView.SelectionBoundsAdjuster {
    public de0 E;
    public ImageView F;
    public RadioButton G;
    public TextView H;
    public CheckBox I;
    public TextView J;
    public ImageView K;
    public ImageView L;
    public LinearLayout M;
    public final Drawable N;
    public final int O;
    public final Context P;
    public boolean Q;
    public final Drawable R;
    public final boolean S;
    public LayoutInflater T;
    public boolean U;

    public ListMenuItemView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, R$attr.listMenuViewStyle);
    }

    private LayoutInflater getInflater() {
        if (this.T == null) {
            this.T = LayoutInflater.from(getContext());
        }
        return this.T;
    }

    private void setSubMenuArrowVisible(boolean z) {
        ImageView imageView = this.K;
        if (imageView != null) {
            imageView.setVisibility(z ? 0 : 8);
        }
    }

    @Override // android.widget.AbsListView.SelectionBoundsAdjuster
    public final void adjustListItemSelectionBounds(Rect rect) {
        ImageView imageView = this.L;
        if (imageView == null || imageView.getVisibility() != 0) {
            return;
        }
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.L.getLayoutParams();
        rect.top = this.L.getHeight() + layoutParams.topMargin + layoutParams.bottomMargin + rect.top;
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005e  */
    @Override // defpackage.re0
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void d(defpackage.de0 r11) {
        /*
            Method dump skipped, instruction units count: 315
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.appcompat.view.menu.ListMenuItemView.d(de0):void");
    }

    @Override // defpackage.re0
    public de0 getItemData() {
        return this.E;
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        WeakHashMap weakHashMap = f51.a;
        setBackground(this.N);
        TextView textView = (TextView) findViewById(R$id.title);
        this.H = textView;
        int i = this.O;
        if (i != -1) {
            textView.setTextAppearance(this.P, i);
        }
        this.J = (TextView) findViewById(R$id.shortcut);
        ImageView imageView = (ImageView) findViewById(R$id.submenuarrow);
        this.K = imageView;
        if (imageView != null) {
            imageView.setImageDrawable(this.R);
        }
        this.L = (ImageView) findViewById(R$id.group_divider);
        this.M = (LinearLayout) findViewById(R$id.content);
    }

    @Override // android.widget.LinearLayout, android.view.View
    public final void onMeasure(int i, int i2) {
        if (this.F != null && this.Q) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.F.getLayoutParams();
            int i3 = layoutParams.height;
            if (i3 > 0 && layoutParams2.width <= 0) {
                layoutParams2.width = i3;
            }
        }
        super.onMeasure(i, i2);
    }

    public void setCheckable(boolean z) {
        CompoundButton compoundButton;
        View view;
        if (!z && this.G == null && this.I == null) {
            return;
        }
        if ((this.E.x & 4) != 0) {
            if (this.G == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.G = radioButton;
                LinearLayout linearLayout = this.M;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.G;
            view = this.I;
        } else {
            if (this.I == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.I = checkBox;
                LinearLayout linearLayout2 = this.M;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.I;
            view = this.G;
        }
        if (z) {
            compoundButton.setChecked(this.E.isChecked());
            if (compoundButton.getVisibility() != 0) {
                compoundButton.setVisibility(0);
            }
            if (view == null || view.getVisibility() == 8) {
                return;
            }
            view.setVisibility(8);
            return;
        }
        CheckBox checkBox2 = this.I;
        if (checkBox2 != null) {
            checkBox2.setVisibility(8);
        }
        RadioButton radioButton2 = this.G;
        if (radioButton2 != null) {
            radioButton2.setVisibility(8);
        }
    }

    public void setChecked(boolean z) {
        CompoundButton compoundButton;
        if ((this.E.x & 4) != 0) {
            if (this.G == null) {
                RadioButton radioButton = (RadioButton) getInflater().inflate(R$layout.abc_list_menu_item_radio, (ViewGroup) this, false);
                this.G = radioButton;
                LinearLayout linearLayout = this.M;
                if (linearLayout != null) {
                    linearLayout.addView(radioButton, -1);
                } else {
                    addView(radioButton, -1);
                }
            }
            compoundButton = this.G;
        } else {
            if (this.I == null) {
                CheckBox checkBox = (CheckBox) getInflater().inflate(R$layout.abc_list_menu_item_checkbox, (ViewGroup) this, false);
                this.I = checkBox;
                LinearLayout linearLayout2 = this.M;
                if (linearLayout2 != null) {
                    linearLayout2.addView(checkBox, -1);
                } else {
                    addView(checkBox, -1);
                }
            }
            compoundButton = this.I;
        }
        compoundButton.setChecked(z);
    }

    public void setForceShowIcon(boolean z) {
        this.U = z;
        this.Q = z;
    }

    public void setGroupDividerEnabled(boolean z) {
        ImageView imageView = this.L;
        if (imageView != null) {
            imageView.setVisibility((this.S || !z) ? 8 : 0);
        }
    }

    public void setIcon(Drawable drawable) {
        this.E.n.getClass();
        boolean z = this.U;
        if (z || this.Q) {
            ImageView imageView = this.F;
            if (imageView == null && drawable == null && !this.Q) {
                return;
            }
            if (imageView == null) {
                ImageView imageView2 = (ImageView) getInflater().inflate(R$layout.abc_list_menu_item_icon, (ViewGroup) this, false);
                this.F = imageView2;
                LinearLayout linearLayout = this.M;
                if (linearLayout != null) {
                    linearLayout.addView(imageView2, 0);
                } else {
                    addView(imageView2, 0);
                }
            }
            if (drawable == null && !this.Q) {
                this.F.setVisibility(8);
                return;
            }
            ImageView imageView3 = this.F;
            if (!z) {
                drawable = null;
            }
            imageView3.setImageDrawable(drawable);
            if (this.F.getVisibility() != 0) {
                this.F.setVisibility(0);
            }
        }
    }

    public void setTitle(CharSequence charSequence) {
        if (charSequence == null) {
            if (this.H.getVisibility() != 8) {
                this.H.setVisibility(8);
            }
        } else {
            this.H.setText(charSequence);
            if (this.H.getVisibility() != 0) {
                this.H.setVisibility(0);
            }
        }
    }

    public ListMenuItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        ou ouVarP = ou.P(getContext(), attributeSet, R$styleable.MenuView, i);
        this.N = ouVarP.A(R$styleable.MenuView_android_itemBackground);
        int i2 = R$styleable.MenuView_android_itemTextAppearance;
        TypedArray typedArray = (TypedArray) ouVarP.G;
        this.O = typedArray.getResourceId(i2, -1);
        this.Q = typedArray.getBoolean(R$styleable.MenuView_preserveIconSpacing, false);
        this.P = context;
        this.R = ouVarP.A(R$styleable.MenuView_subMenuArrow);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(null, new int[]{R.attr.divider}, R$attr.dropDownListViewStyle, 0);
        this.S = typedArrayObtainStyledAttributes.hasValue(0);
        ouVarP.S();
        typedArrayObtainStyledAttributes.recycle();
    }
}
