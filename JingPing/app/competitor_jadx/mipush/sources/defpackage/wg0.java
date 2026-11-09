package defpackage;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.g;
import androidx.recyclerview.widget.n;
import com.google.android.material.R$layout;
import com.google.android.material.internal.NavigationMenuItemView;
import java.util.ArrayList;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class wg0 extends g {
    public final ArrayList c = new ArrayList();
    public de0 d;
    public boolean e;
    public final /* synthetic */ eh0 f;

    public wg0(eh0 eh0Var) {
        this.f = eh0Var;
        g();
    }

    @Override // androidx.recyclerview.widget.g
    public final int a() {
        return this.c.size();
    }

    @Override // androidx.recyclerview.widget.g
    public final long b(int i) {
        return i;
    }

    @Override // androidx.recyclerview.widget.g
    public final int c(int i) {
        yg0 yg0Var = (yg0) this.c.get(i);
        if (yg0Var instanceof zg0) {
            return 2;
        }
        if (yg0Var instanceof xg0) {
            return 3;
        }
        if (yg0Var instanceof ah0) {
            return ((ah0) yg0Var).a.hasSubMenu() ? 1 : 0;
        }
        throw new RuntimeException("Unknown item type.");
    }

    @Override // androidx.recyclerview.widget.g
    public final void d(n nVar, int i) {
        int iC = c(i);
        ArrayList arrayList = this.c;
        eh0 eh0Var = this.f;
        View view = ((dh0) nVar).a;
        if (iC != 0) {
            if (iC != 1) {
                if (iC != 2) {
                    return;
                }
                zg0 zg0Var = (zg0) arrayList.get(i);
                view.setPadding(eh0Var.W, zg0Var.a, eh0Var.X, zg0Var.b);
                return;
            }
            TextView textView = (TextView) view;
            textView.setText(((ah0) arrayList.get(i)).a.e);
            textView.setTextAppearance(eh0Var.K);
            textView.setPadding(eh0Var.Y, textView.getPaddingTop(), eh0Var.Z, textView.getPaddingBottom());
            ColorStateList colorStateList = eh0Var.L;
            if (colorStateList != null) {
                textView.setTextColor(colorStateList);
            }
            f51.o(textView, new vg0(this, i, true));
            return;
        }
        NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
        navigationMenuItemView.setIconTintList(eh0Var.P);
        navigationMenuItemView.setTextAppearance(eh0Var.M);
        ColorStateList colorStateList2 = eh0Var.O;
        if (colorStateList2 != null) {
            navigationMenuItemView.setTextColor(colorStateList2);
        }
        Drawable drawable = eh0Var.Q;
        Drawable drawableNewDrawable = drawable != null ? drawable.getConstantState().newDrawable() : null;
        WeakHashMap weakHashMap = f51.a;
        navigationMenuItemView.setBackground(drawableNewDrawable);
        RippleDrawable rippleDrawable = eh0Var.R;
        if (rippleDrawable != null) {
            navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
        }
        ah0 ah0Var = (ah0) arrayList.get(i);
        navigationMenuItemView.setNeedsEmptyIcon(ah0Var.b);
        int i2 = eh0Var.S;
        int i3 = eh0Var.T;
        navigationMenuItemView.setPadding(i2, i3, i2, i3);
        navigationMenuItemView.setIconPadding(eh0Var.U);
        if (eh0Var.a0) {
            navigationMenuItemView.setIconSize(eh0Var.V);
        }
        navigationMenuItemView.setMaxLines(eh0Var.c0);
        navigationMenuItemView.f0 = eh0Var.N;
        navigationMenuItemView.d(ah0Var.a);
        f51.o(navigationMenuItemView, new vg0(this, i, false));
    }

    @Override // androidx.recyclerview.widget.g
    public final n e(ViewGroup viewGroup, int i) {
        eh0 eh0Var = this.f;
        if (i == 0) {
            LayoutInflater layoutInflater = eh0Var.J;
            y2 y2Var = eh0Var.g0;
            View viewInflate = layoutInflater.inflate(R$layout.design_navigation_item, viewGroup, false);
            ch0 ch0Var = new ch0(viewInflate);
            viewInflate.setOnClickListener(y2Var);
            return ch0Var;
        }
        if (i == 1) {
            return new ug0(eh0Var.J.inflate(R$layout.design_navigation_item_subheader, viewGroup, false));
        }
        if (i == 2) {
            return new ug0(eh0Var.J.inflate(R$layout.design_navigation_item_separator, viewGroup, false));
        }
        if (i != 3) {
            return null;
        }
        return new ug0(eh0Var.F);
    }

    @Override // androidx.recyclerview.widget.g
    public final void f(n nVar) {
        dh0 dh0Var = (dh0) nVar;
        if (dh0Var instanceof ch0) {
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) dh0Var.a;
            FrameLayout frameLayout = navigationMenuItemView.h0;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            navigationMenuItemView.g0.setCompoundDrawables(null, null, null, null);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void g() {
        boolean z;
        if (this.e) {
            return;
        }
        this.e = true;
        ArrayList arrayList = this.c;
        arrayList.clear();
        arrayList.add(new xg0());
        eh0 eh0Var = this.f;
        int size = eh0Var.G.l().size();
        boolean z2 = false;
        int i = -1;
        int i2 = 0;
        boolean z3 = false;
        int size2 = 0;
        while (i2 < size) {
            de0 de0Var = (de0) eh0Var.G.l().get(i2);
            if (de0Var.isChecked()) {
                h(de0Var);
            }
            if (de0Var.isCheckable()) {
                de0Var.f(z2);
            }
            if (de0Var.hasSubMenu()) {
                rx0 rx0Var = de0Var.o;
                if (rx0Var.hasVisibleItems()) {
                    if (i2 != 0) {
                        arrayList.add(new zg0(eh0Var.e0, z2 ? 1 : 0));
                    }
                    arrayList.add(new ah0(de0Var));
                    int size3 = rx0Var.f.size();
                    int i3 = z2 ? 1 : 0;
                    int i4 = i3;
                    while (i3 < size3) {
                        de0 de0Var2 = (de0) rx0Var.getItem(i3);
                        if (de0Var2.isVisible()) {
                            if (i4 == 0 && de0Var2.getIcon() != null) {
                                i4 = 1;
                            }
                            if (de0Var2.isCheckable()) {
                                de0Var2.f(z2);
                            }
                            if (de0Var.isChecked()) {
                                h(de0Var);
                            }
                            arrayList.add(new ah0(de0Var2));
                        }
                        i3++;
                        z2 = false;
                    }
                    if (i4 != 0) {
                        int size4 = arrayList.size();
                        for (int size5 = arrayList.size(); size5 < size4; size5++) {
                            ((ah0) arrayList.get(size5)).b = true;
                        }
                    }
                }
                z = true;
            } else {
                int i5 = de0Var.b;
                if (i5 != i) {
                    size2 = arrayList.size();
                    z3 = de0Var.getIcon() != null;
                    if (i2 != 0) {
                        size2++;
                        int i6 = eh0Var.e0;
                        arrayList.add(new zg0(i6, i6));
                    }
                } else {
                    if (!z3 && de0Var.getIcon() != null) {
                        int size6 = arrayList.size();
                        for (int i7 = size2; i7 < size6; i7++) {
                            ((ah0) arrayList.get(i7)).b = true;
                        }
                        z = true;
                        z3 = true;
                    }
                    ah0 ah0Var = new ah0(de0Var);
                    ah0Var.b = z3;
                    arrayList.add(ah0Var);
                    i = i5;
                }
                z = true;
                ah0 ah0Var2 = new ah0(de0Var);
                ah0Var2.b = z3;
                arrayList.add(ah0Var2);
                i = i5;
            }
            i2++;
            z2 = false;
        }
        this.e = z2 ? 1 : 0;
    }

    public final void h(de0 de0Var) {
        if (this.d == de0Var || !de0Var.isCheckable()) {
            return;
        }
        de0 de0Var2 = this.d;
        if (de0Var2 != null) {
            de0Var2.setChecked(false);
        }
        this.d = de0Var;
        de0Var.setChecked(true);
    }
}
