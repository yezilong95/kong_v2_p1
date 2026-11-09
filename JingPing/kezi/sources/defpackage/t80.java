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
public final class t80 extends g {
    public final ArrayList c = new ArrayList();
    public m60 d;
    public boolean e;
    public final /* synthetic */ b90 f;

    public t80(b90 b90Var) {
        this.f = b90Var;
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
        v80 v80Var = (v80) this.c.get(i);
        if (v80Var instanceof w80) {
            return 2;
        }
        if (v80Var instanceof u80) {
            return 3;
        }
        if (v80Var instanceof x80) {
            return ((x80) v80Var).a.hasSubMenu() ? 1 : 0;
        }
        throw new RuntimeException("Unknown item type.");
    }

    @Override // androidx.recyclerview.widget.g
    public final void d(n nVar, int i) {
        int iC = c(i);
        ArrayList arrayList = this.c;
        b90 b90Var = this.f;
        View view = ((a90) nVar).a;
        if (iC != 0) {
            if (iC != 1) {
                if (iC != 2) {
                    return;
                }
                w80 w80Var = (w80) arrayList.get(i);
                view.setPadding(b90Var.t, w80Var.a, b90Var.u, w80Var.b);
                return;
            }
            TextView textView = (TextView) view;
            textView.setText(((x80) arrayList.get(i)).a.e);
            textView.setTextAppearance(b90Var.h);
            textView.setPadding(b90Var.v, textView.getPaddingTop(), b90Var.w, textView.getPaddingBottom());
            ColorStateList colorStateList = b90Var.i;
            if (colorStateList != null) {
                textView.setTextColor(colorStateList);
            }
            xr0.p(textView, new s80(this, i, true));
            return;
        }
        NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) view;
        navigationMenuItemView.setIconTintList(b90Var.m);
        navigationMenuItemView.setTextAppearance(b90Var.j);
        ColorStateList colorStateList2 = b90Var.l;
        if (colorStateList2 != null) {
            navigationMenuItemView.setTextColor(colorStateList2);
        }
        Drawable drawable = b90Var.n;
        Drawable drawableNewDrawable = drawable != null ? drawable.getConstantState().newDrawable() : null;
        WeakHashMap weakHashMap = xr0.a;
        navigationMenuItemView.setBackground(drawableNewDrawable);
        RippleDrawable rippleDrawable = b90Var.o;
        if (rippleDrawable != null) {
            navigationMenuItemView.setForeground(rippleDrawable.getConstantState().newDrawable());
        }
        x80 x80Var = (x80) arrayList.get(i);
        navigationMenuItemView.setNeedsEmptyIcon(x80Var.b);
        int i2 = b90Var.p;
        int i3 = b90Var.q;
        navigationMenuItemView.setPadding(i2, i3, i2, i3);
        navigationMenuItemView.setIconPadding(b90Var.r);
        if (b90Var.x) {
            navigationMenuItemView.setIconSize(b90Var.s);
        }
        navigationMenuItemView.setMaxLines(b90Var.z);
        navigationMenuItemView.z = b90Var.k;
        navigationMenuItemView.d(x80Var.a);
        xr0.p(navigationMenuItemView, new s80(this, i, false));
    }

    @Override // androidx.recyclerview.widget.g
    public final n e(ViewGroup viewGroup, int i) {
        b90 b90Var = this.f;
        if (i == 0) {
            LayoutInflater layoutInflater = b90Var.g;
            c1 c1Var = b90Var.D;
            View viewInflate = layoutInflater.inflate(R$layout.design_navigation_item, viewGroup, false);
            z80 z80Var = new z80(viewInflate);
            viewInflate.setOnClickListener(c1Var);
            return z80Var;
        }
        if (i == 1) {
            return new r80(b90Var.g.inflate(R$layout.design_navigation_item_subheader, viewGroup, false));
        }
        if (i == 2) {
            return new r80(b90Var.g.inflate(R$layout.design_navigation_item_separator, viewGroup, false));
        }
        if (i != 3) {
            return null;
        }
        return new r80(b90Var.c);
    }

    @Override // androidx.recyclerview.widget.g
    public final void f(n nVar) {
        a90 a90Var = (a90) nVar;
        if (a90Var instanceof z80) {
            NavigationMenuItemView navigationMenuItemView = (NavigationMenuItemView) a90Var.a;
            FrameLayout frameLayout = navigationMenuItemView.B;
            if (frameLayout != null) {
                frameLayout.removeAllViews();
            }
            navigationMenuItemView.A.setCompoundDrawables(null, null, null, null);
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
        arrayList.add(new u80());
        b90 b90Var = this.f;
        int size = b90Var.d.l().size();
        boolean z2 = false;
        int i = -1;
        int i2 = 0;
        boolean z3 = false;
        int size2 = 0;
        while (i2 < size) {
            m60 m60Var = (m60) b90Var.d.l().get(i2);
            if (m60Var.isChecked()) {
                h(m60Var);
            }
            if (m60Var.isCheckable()) {
                m60Var.f(z2);
            }
            if (m60Var.hasSubMenu()) {
                el0 el0Var = m60Var.o;
                if (el0Var.hasVisibleItems()) {
                    if (i2 != 0) {
                        arrayList.add(new w80(b90Var.B, z2 ? 1 : 0));
                    }
                    arrayList.add(new x80(m60Var));
                    int size3 = el0Var.f.size();
                    int i3 = z2 ? 1 : 0;
                    int i4 = i3;
                    while (i3 < size3) {
                        m60 m60Var2 = (m60) el0Var.getItem(i3);
                        if (m60Var2.isVisible()) {
                            if (i4 == 0 && m60Var2.getIcon() != null) {
                                i4 = 1;
                            }
                            if (m60Var2.isCheckable()) {
                                m60Var2.f(z2);
                            }
                            if (m60Var.isChecked()) {
                                h(m60Var);
                            }
                            arrayList.add(new x80(m60Var2));
                        }
                        i3++;
                        z2 = false;
                    }
                    if (i4 != 0) {
                        int size4 = arrayList.size();
                        for (int size5 = arrayList.size(); size5 < size4; size5++) {
                            ((x80) arrayList.get(size5)).b = true;
                        }
                    }
                }
                z = true;
            } else {
                int i5 = m60Var.b;
                if (i5 != i) {
                    size2 = arrayList.size();
                    z3 = m60Var.getIcon() != null;
                    if (i2 != 0) {
                        size2++;
                        int i6 = b90Var.B;
                        arrayList.add(new w80(i6, i6));
                    }
                } else {
                    if (!z3 && m60Var.getIcon() != null) {
                        int size6 = arrayList.size();
                        for (int i7 = size2; i7 < size6; i7++) {
                            ((x80) arrayList.get(i7)).b = true;
                        }
                        z = true;
                        z3 = true;
                    }
                    x80 x80Var = new x80(m60Var);
                    x80Var.b = z3;
                    arrayList.add(x80Var);
                    i = i5;
                }
                z = true;
                x80 x80Var2 = new x80(m60Var);
                x80Var2.b = z3;
                arrayList.add(x80Var2);
                i = i5;
            }
            i2++;
            z2 = false;
        }
        this.e = z2 ? 1 : 0;
    }

    public final void h(m60 m60Var) {
        if (this.d == m60Var || !m60Var.isCheckable()) {
            return;
        }
        m60 m60Var2 = this.d;
        if (m60Var2 != null) {
            m60Var2.setChecked(false);
        }
        this.d = m60Var;
        m60Var.setChecked(true);
    }
}
