package defpackage;

import android.view.View;
import android.view.ViewPropertyAnimator;
import androidx.recyclerview.widget.n;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class zm implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ ArrayList F;
    public final /* synthetic */ fn G;

    public /* synthetic */ zm(fn fnVar, ArrayList arrayList, int i) {
        this.E = i;
        this.G = fnVar;
        this.F = arrayList;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ArrayList arrayList = this.F;
                Iterator it = arrayList.iterator();
                while (true) {
                    boolean zHasNext = it.hasNext();
                    fn fnVar = this.G;
                    if (!zHasNext) {
                        arrayList.clear();
                        fnVar.m.remove(arrayList);
                    } else {
                        en enVar = (en) it.next();
                        n nVar = enVar.a;
                        fnVar.getClass();
                        View view = nVar.a;
                        int i = enVar.d - enVar.b;
                        int i2 = enVar.e - enVar.c;
                        if (i != 0) {
                            view.animate().translationX(0.0f);
                        }
                        if (i2 != 0) {
                            view.animate().translationY(0.0f);
                        }
                        ViewPropertyAnimator viewPropertyAnimatorAnimate = view.animate();
                        fnVar.p.add(nVar);
                        viewPropertyAnimatorAnimate.setDuration(fnVar.e).setListener(new bn(fnVar, nVar, i, view, i2, viewPropertyAnimatorAnimate)).start();
                    }
                    break;
                }
                break;
            case 1:
                ArrayList arrayList2 = this.F;
                Iterator it2 = arrayList2.iterator();
                while (true) {
                    boolean zHasNext2 = it2.hasNext();
                    fn fnVar2 = this.G;
                    if (!zHasNext2) {
                        arrayList2.clear();
                        fnVar2.n.remove(arrayList2);
                        break;
                    } else {
                        dn dnVar = (dn) it2.next();
                        fnVar2.getClass();
                        n nVar2 = dnVar.a;
                        View view2 = nVar2 == null ? null : nVar2.a;
                        n nVar3 = dnVar.b;
                        View view3 = nVar3 != null ? nVar3.a : null;
                        ArrayList arrayList3 = fnVar2.r;
                        long j = fnVar2.f;
                        if (view2 != null) {
                            ViewPropertyAnimator duration = view2.animate().setDuration(j);
                            arrayList3.add(dnVar.a);
                            duration.translationX(dnVar.e - dnVar.c);
                            duration.translationY(dnVar.f - dnVar.d);
                            duration.alpha(0.0f).setListener(new cn(fnVar2, dnVar, duration, view2, 0)).start();
                        }
                        if (view3 != null) {
                            ViewPropertyAnimator viewPropertyAnimatorAnimate2 = view3.animate();
                            arrayList3.add(dnVar.b);
                            viewPropertyAnimatorAnimate2.translationX(0.0f).translationY(0.0f).setDuration(j).alpha(1.0f).setListener(new cn(fnVar2, dnVar, viewPropertyAnimatorAnimate2, view3, 1)).start();
                        }
                    }
                }
                break;
            default:
                ArrayList arrayList4 = this.F;
                Iterator it3 = arrayList4.iterator();
                while (true) {
                    boolean zHasNext3 = it3.hasNext();
                    fn fnVar3 = this.G;
                    if (!zHasNext3) {
                        arrayList4.clear();
                        fnVar3.l.remove(arrayList4);
                    } else {
                        n nVar4 = (n) it3.next();
                        fnVar3.getClass();
                        View view4 = nVar4.a;
                        ViewPropertyAnimator viewPropertyAnimatorAnimate3 = view4.animate();
                        fnVar3.o.add(nVar4);
                        viewPropertyAnimatorAnimate3.alpha(1.0f).setDuration(fnVar3.c).setListener(new an(fnVar3, nVar4, view4, viewPropertyAnimatorAnimate3)).start();
                    }
                    break;
                }
                break;
        }
    }
}
