package androidx.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.a21;
import defpackage.la0;
import defpackage.o80;
import defpackage.q11;
import defpackage.qm;
import defpackage.s11;
import defpackage.tb;
import defpackage.w11;
import defpackage.z91;
import defpackage.zn;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class TransitionSet extends Transition {
    public ArrayList f0;
    public boolean g0;
    public int h0;
    public boolean i0;
    public int j0;

    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f0 = new ArrayList();
        this.g0 = true;
        this.i0 = false;
        this.j0 = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.x);
        L(la0.y(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.transition.Transition
    public final void B(o80 o80Var) {
        this.j0 |= 8;
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.f0.get(i)).B(o80Var);
        }
    }

    @Override // androidx.transition.Transition
    public final void D(q11 q11Var) {
        super.D(q11Var);
        this.j0 |= 4;
        if (this.f0 != null) {
            for (int i = 0; i < this.f0.size(); i++) {
                ((Transition) this.f0.get(i)).D(q11Var);
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void E(z91 z91Var) {
        this.Z = z91Var;
        this.j0 |= 2;
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.f0.get(i)).E(z91Var);
        }
    }

    @Override // androidx.transition.Transition
    public final void F(long j) {
        this.F = j;
    }

    @Override // androidx.transition.Transition
    public final String H(String str) {
        String strH = super.H(str);
        for (int i = 0; i < this.f0.size(); i++) {
            StringBuilder sbQ = zn.q(strH, "\n");
            sbQ.append(((Transition) this.f0.get(i)).H(str + "  "));
            strH = sbQ.toString();
        }
        return strH;
    }

    public final void I(Transition transition) {
        this.f0.add(transition);
        transition.M = this;
        long j = this.G;
        if (j >= 0) {
            transition.A(j);
        }
        if ((this.j0 & 1) != 0) {
            transition.C(this.H);
        }
        if ((this.j0 & 2) != 0) {
            transition.E(this.Z);
        }
        if ((this.j0 & 4) != 0) {
            transition.D(this.a0);
        }
        if ((this.j0 & 8) != 0) {
            transition.B(null);
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: J, reason: merged with bridge method [inline-methods] */
    public final void A(long j) {
        ArrayList arrayList;
        this.G = j;
        if (j < 0 || (arrayList = this.f0) == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.f0.get(i)).A(j);
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: K, reason: merged with bridge method [inline-methods] */
    public final void C(TimeInterpolator timeInterpolator) {
        this.j0 |= 1;
        ArrayList arrayList = this.f0;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Transition) this.f0.get(i)).C(timeInterpolator);
            }
        }
        this.H = timeInterpolator;
    }

    public final void L(int i) {
        if (i == 0) {
            this.g0 = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException(zn.j(i, "Invalid parameter for TransitionSet ordering: "));
            }
            this.g0 = false;
        }
    }

    @Override // androidx.transition.Transition
    public final void c() {
        super.c();
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.f0.get(i)).c();
        }
    }

    @Override // androidx.transition.Transition
    public final void d(a21 a21Var) {
        if (t(a21Var.b)) {
            for (Transition transition : this.f0) {
                if (transition.t(a21Var.b)) {
                    transition.d(a21Var);
                    a21Var.c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void f(a21 a21Var) {
        super.f(a21Var);
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.f0.get(i)).f(a21Var);
        }
    }

    @Override // androidx.transition.Transition
    public final void g(a21 a21Var) {
        if (t(a21Var.b)) {
            for (Transition transition : this.f0) {
                if (transition.t(a21Var.b)) {
                    transition.g(a21Var);
                    a21Var.c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public final Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.f0 = new ArrayList();
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            Transition transitionClone = ((Transition) this.f0.get(i)).clone();
            transitionSet.f0.add(transitionClone);
            transitionClone.M = transitionSet;
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    public final void l(ViewGroup viewGroup, tb tbVar, tb tbVar2, ArrayList arrayList, ArrayList arrayList2) {
        long j = this.F;
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            Transition transition = (Transition) this.f0.get(i);
            if (j > 0 && (this.g0 || i == 0)) {
                long j2 = transition.F;
                if (j2 > 0) {
                    transition.F(j2 + j);
                } else {
                    transition.F(j);
                }
            }
            transition.l(viewGroup, tbVar, tbVar2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.Transition
    public final void w(ViewGroup viewGroup) {
        super.w(viewGroup);
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.f0.get(i)).w(viewGroup);
        }
    }

    @Override // androidx.transition.Transition
    public final Transition x(s11 s11Var) {
        super.x(s11Var);
        return this;
    }

    @Override // androidx.transition.Transition
    public final void y(View view) {
        super.y(view);
        int size = this.f0.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.f0.get(i)).y(view);
        }
    }

    @Override // androidx.transition.Transition
    public final void z() {
        if (this.f0.isEmpty()) {
            G();
            m();
            return;
        }
        w11 w11Var = new w11();
        w11Var.b = this;
        Iterator it = this.f0.iterator();
        while (it.hasNext()) {
            ((Transition) it.next()).a(w11Var);
        }
        this.h0 = this.f0.size();
        if (this.g0) {
            Iterator it2 = this.f0.iterator();
            while (it2.hasNext()) {
                ((Transition) it2.next()).z();
            }
            return;
        }
        for (int i = 1; i < this.f0.size(); i++) {
            ((Transition) this.f0.get(i - 1)).a(new w11((Transition) this.f0.get(i)));
        }
        Transition transition = (Transition) this.f0.get(0);
        if (transition != null) {
            transition.z();
        }
    }
}
