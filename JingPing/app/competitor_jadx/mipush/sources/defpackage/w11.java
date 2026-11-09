package defpackage;

import androidx.transition.Transition;
import androidx.transition.TransitionSet;

/* JADX INFO: loaded from: classes.dex */
public final class w11 extends t11 {
    public final /* synthetic */ int a = 1;
    public Transition b;

    public /* synthetic */ w11() {
    }

    @Override // defpackage.t11, defpackage.s11
    public void c(Transition transition) {
        switch (this.a) {
            case 1:
                TransitionSet transitionSet = (TransitionSet) this.b;
                if (!transitionSet.i0) {
                    transitionSet.G();
                    transitionSet.i0 = true;
                }
                break;
        }
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
        switch (this.a) {
            case 0:
                this.b.z();
                transition.x(this);
                break;
            default:
                TransitionSet transitionSet = (TransitionSet) this.b;
                int i = transitionSet.h0 - 1;
                transitionSet.h0 = i;
                if (i == 0) {
                    transitionSet.i0 = false;
                    transitionSet.m();
                }
                transition.x(this);
                break;
        }
    }

    public w11(Transition transition) {
        this.b = transition;
    }
}
