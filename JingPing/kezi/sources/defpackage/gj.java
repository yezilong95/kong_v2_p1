package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class gj implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ ArrayList c;
    public final /* synthetic */ mj d;

    public /* synthetic */ gj(mj mjVar, ArrayList arrayList, int i) {
        this.b = i;
        this.d = mjVar;
        this.c = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ArrayList arrayList = this.c;
                Iterator it = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    mj mjVar = this.d;
                    if (!zHasNext) {
                        arrayList.clear();
                        mjVar.m.remove(arrayList);
                    } else {
                        lj ljVar = (lj) it.next();
                        n nVar = ljVar.a;
                        mjVar.getClass();
                        View view = nVar.a;
                        int i = ljVar.d - ljVar.b;
                        int i2 = ljVar.e - ljVar.c;
                        if (i != 0) {
                            view.animate().translationX(0.0f);
                        }
                        if (i2 != 0) {
                            view.animate().translationY(0.0f);
                        }
                        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                        mjVar.p.add(nVar);
                        viewPropertyAnimatorAnimate.setDuration(mjVar.e).setListener(new ij(mjVar, nVar, i, view, i2, viewPropertyAnimatorAnimate)).start();
                    }
                    break;
                }
                break;
            case 1:
                ArrayList arrayList2 = this.c;
                Iterator it2 = arrayList2.iterator();
                while (true) {
                    boolean zHasNext2 = it2.hasNext();
                    mj mjVar2 = this.d;
                    if (!zHasNext2) {
                        arrayList2.clear();
                        mjVar2.n.remove(arrayList2);
                        break;
                    } else {
                        kj kjVar = (kj) it2.next();
                        mjVar2.getClass();
                        n nVar2 = kjVar.a;
                        View view2 = nVar2 == null ? null : nVar2.a;
                        n nVar3 = kjVar.b;
                        View view3 = nVar3 != null ? nVar3.a : null;
                        ArrayList arrayList3 = mjVar2.r;
                        long j = mjVar2.f;
                        if (view2 != null) {
                            ViewPropertyAnimator duration = view2.animate().setDuration(j);
                            arrayList3.add(kjVar.a);
                            duration.translationX(kjVar.e - kjVar.c);
                            duration.translationY(kjVar.f - kjVar.d);
                            duration.alpha(0.0f).setListener(new jj(mjVar2, kjVar, duration, view2, 0)).start();
                        }
                        if (view3 != null) {
                            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view3.animate();
                            arrayList3.add(kjVar.b);
                            viewPropertyAnimatorAnimate2.translationX(0.0f).translationY(0.0f).setDuration(j).alpha(1.0f).setListener(new jj(mjVar2, kjVar, viewPropertyAnimatorAnimate2, view3, 1)).start();
                        }
                    }
                }
                break;
            default:
                ArrayList arrayList4 = this.c;
                Iterator it3 = arrayList4.iterator();
                while (true) {
                    boolean zHasNext3 = it3.hasNext();
                    mj mjVar3 = this.d;
                    if (!zHasNext3) {
                        arrayList4.clear();
                        mjVar3.l.remove(arrayList4);
                    } else {
                        n nVar4 = (n) it3.next();
                        mjVar3.getClass();
                        View view4 = nVar4.a;
                        ViewPropertyAnimator viewPropertyAnimatorAnimate3 = view4.animate();
                        mjVar3.o.add(nVar4);
                        viewPropertyAnimatorAnimate3.alpha(1.0f).setDuration(mjVar3.c).setListener(new hj(mjVar3, nVar4, view4, viewPropertyAnimatorAnimate3)).start();
                    }
                    break;
                }
                break;
        }
    }
}
