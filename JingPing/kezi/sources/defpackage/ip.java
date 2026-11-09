package defpackage;

import android.view.View;
import androidx.transition.Transition;
import androidx.transition.TransitionSet;

/* JADX INFO: loaded from: classes.dex */
public final class ip extends hp0 {
    public final /* synthetic */ int a;
    public Object b;

    public /* synthetic */ ip() {
        this.a = 2;
    }

    @Override // defpackage.hp0, defpackage.gp0
    public void b() {
        switch (this.a) {
            case 2:
                TransitionSet transitionSet = (TransitionSet) this.b;
                if (!transitionSet.B) {
                    transitionSet.D();
                    transitionSet.B = true;
                }
                break;
        }
    }

    @Override // defpackage.gp0
    public final void d(Transition transition) {
        switch (this.a) {
            case 0:
                jt0.b((View) this.b, 1.0f);
                jt0.a.getClass();
                transition.u(this);
                break;
            case 1:
                ((Transition) this.b).w();
                transition.u(this);
                break;
            default:
                TransitionSet transitionSet = (TransitionSet) this.b;
                int i = transitionSet.A - 1;
                transitionSet.A = i;
                if (i == 0) {
                    transitionSet.B = false;
                    transitionSet.l();
                }
                transition.u(this);
                break;
        }
    }

    public /* synthetic */ ip(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }
}
