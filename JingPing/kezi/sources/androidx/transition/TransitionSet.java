package androidx.transition;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import defpackage.ba;
import defpackage.ep0;
import defpackage.hk;
import defpackage.ip;
import defpackage.k9;
import defpackage.mp0;
import defpackage.nv0;
import defpackage.za0;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class TransitionSet extends Transition {
    public int A;
    public boolean B;
    public int C;
    public ArrayList y;
    public boolean z;

    @SuppressLint({"RestrictedApi"})
    public TransitionSet(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.y = new ArrayList();
        this.z = true;
        this.B = false;
        this.C = 0;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, za0.m);
        I(za0.q(typedArrayObtainStyledAttributes, (XmlResourceParser) attributeSet, "transitionOrdering", 0, 0));
        typedArrayObtainStyledAttributes.recycle();
    }

    @Override // androidx.transition.Transition
    public final void A(ep0 ep0Var) {
        super.A(ep0Var);
        this.C |= 4;
        if (this.y != null) {
            for (int i = 0; i < this.y.size(); i++) {
                ((Transition) this.y.get(i)).A(ep0Var);
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void B(nv0 nv0Var) {
        this.t = nv0Var;
        this.C |= 2;
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.y.get(i)).B(nv0Var);
        }
    }

    @Override // androidx.transition.Transition
    public final void C(long j) {
        this.c = j;
    }

    @Override // androidx.transition.Transition
    public final String E(String str) {
        String strE = super.E(str);
        for (int i = 0; i < this.y.size(); i++) {
            StringBuilder sb = new StringBuilder();
            sb.append(strE);
            sb.append("\n");
            sb.append(((Transition) this.y.get(i)).E(str + "  "));
            strE = sb.toString();
        }
        return strE;
    }

    public final void F(Transition transition) {
        this.y.add(transition);
        transition.j = this;
        long j = this.d;
        if (j >= 0) {
            transition.x(j);
        }
        if ((this.C & 1) != 0) {
            transition.z(this.e);
        }
        if ((this.C & 2) != 0) {
            transition.B(this.t);
        }
        if ((this.C & 4) != 0) {
            transition.A(this.u);
        }
        if ((this.C & 8) != 0) {
            transition.y(null);
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: G, reason: merged with bridge method [inline-methods] */
    public final void x(long j) {
        ArrayList arrayList;
        this.d = j;
        if (j < 0 || (arrayList = this.y) == null) {
            return;
        }
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.y.get(i)).x(j);
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public final void z(TimeInterpolator timeInterpolator) {
        this.C |= 1;
        ArrayList arrayList = this.y;
        if (arrayList != null) {
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((Transition) this.y.get(i)).z(timeInterpolator);
            }
        }
        this.e = timeInterpolator;
    }

    public final void I(int i) {
        if (i == 0) {
            this.z = true;
        } else {
            if (i != 1) {
                throw new AndroidRuntimeException(hk.g(i, "Invalid parameter for TransitionSet ordering: "));
            }
            this.z = false;
        }
    }

    @Override // androidx.transition.Transition
    public final void c(mp0 mp0Var) {
        if (r(mp0Var.b)) {
            for (Transition transition : this.y) {
                if (transition.r(mp0Var.b)) {
                    transition.c(mp0Var);
                    mp0Var.c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    public final void e(mp0 mp0Var) {
        super.e(mp0Var);
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.y.get(i)).e(mp0Var);
        }
    }

    @Override // androidx.transition.Transition
    public final void f(mp0 mp0Var) {
        if (r(mp0Var.b)) {
            for (Transition transition : this.y) {
                if (transition.r(mp0Var.b)) {
                    transition.f(mp0Var);
                    mp0Var.c.add(transition);
                }
            }
        }
    }

    @Override // androidx.transition.Transition
    /* JADX INFO: renamed from: i */
    public final Transition clone() {
        TransitionSet transitionSet = (TransitionSet) super.clone();
        transitionSet.y = new ArrayList();
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            Transition transitionClone = ((Transition) this.y.get(i)).clone();
            transitionSet.y.add(transitionClone);
            transitionClone.j = transitionSet;
        }
        return transitionSet;
    }

    @Override // androidx.transition.Transition
    public final void k(ViewGroup viewGroup, ba baVar, ba baVar2, ArrayList arrayList, ArrayList arrayList2) {
        long j = this.c;
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            Transition transition = (Transition) this.y.get(i);
            if (j > 0 && (this.z || i == 0)) {
                long j2 = transition.c;
                if (j2 > 0) {
                    transition.C(j2 + j);
                } else {
                    transition.C(j);
                }
            }
            transition.k(viewGroup, baVar, baVar2, arrayList, arrayList2);
        }
    }

    @Override // androidx.transition.Transition
    public final void t(ViewGroup viewGroup) {
        super.t(viewGroup);
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.y.get(i)).t(viewGroup);
        }
    }

    @Override // androidx.transition.Transition
    public final void v(View view) {
        super.v(view);
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.y.get(i)).v(view);
        }
    }

    @Override // androidx.transition.Transition
    public final void w() {
        if (this.y.isEmpty()) {
            D();
            l();
            return;
        }
        ip ipVar = new ip();
        ipVar.b = this;
        Iterator it = this.y.iterator();
        while (it.hasNext()) {
            ((Transition) it.next()).a(ipVar);
        }
        this.A = this.y.size();
        if (this.z) {
            Iterator it2 = this.y.iterator();
            while (it2.hasNext()) {
                ((Transition) it2.next()).w();
            }
            return;
        }
        for (int i = 1; i < this.y.size(); i++) {
            ((Transition) this.y.get(i - 1)).a(new ip(1, (Transition) this.y.get(i)));
        }
        Transition transition = (Transition) this.y.get(0);
        if (transition != null) {
            transition.w();
        }
    }

    @Override // androidx.transition.Transition
    public final void y(k9 k9Var) {
        this.C |= 8;
        int size = this.y.size();
        for (int i = 0; i < size; i++) {
            ((Transition) this.y.get(i)).y(k9Var);
        }
    }
}
