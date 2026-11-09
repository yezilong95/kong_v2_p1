package defpackage;

import android.os.Build;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsAnimation;
import android.view.WindowInsetsAnimation$Callback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class gu0 extends WindowInsetsAnimation$Callback {
    public final gg a;
    public List b;
    public ArrayList c;
    public final HashMap d;

    public gu0(gg ggVar) {
        super(0);
        this.d = new HashMap();
        this.a = ggVar;
    }

    public final ju0 a(WindowInsetsAnimation windowInsetsAnimation) {
        ju0 ju0Var = (ju0) this.d.get(windowInsetsAnimation);
        if (ju0Var == null) {
            ju0Var = new ju0(0, null, 0L);
            if (Build.VERSION.SDK_INT >= 30) {
                ju0Var.a = new hu0(windowInsetsAnimation);
            }
            this.d.put(windowInsetsAnimation, ju0Var);
        }
        return ju0Var;
    }

    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
        gg ggVar = this.a;
        a(windowInsetsAnimation);
        ((View) ggVar.d).setTranslationY(0.0f);
        this.d.remove(windowInsetsAnimation);
    }

    public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
        gg ggVar = this.a;
        a(windowInsetsAnimation);
        View view = (View) ggVar.d;
        int[] iArr = (int[]) ggVar.e;
        view.getLocationOnScreen(iArr);
        ggVar.a = iArr[1];
    }

    public final WindowInsets onProgress(WindowInsets windowInsets, List list) {
        ArrayList arrayList = this.c;
        if (arrayList == null) {
            ArrayList arrayList2 = new ArrayList(list.size());
            this.c = arrayList2;
            this.b = Collections.unmodifiableList(arrayList2);
        } else {
            arrayList.clear();
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            WindowInsetsAnimation windowInsetsAnimationM = g0.m(list.get(size));
            ju0 ju0VarA = a(windowInsetsAnimationM);
            ju0VarA.a.d(windowInsetsAnimationM.getFraction());
            this.c.add(ju0VarA);
        }
        gg ggVar = this.a;
        xu0 xu0VarH = xu0.h(null, windowInsets);
        ggVar.a(xu0VarH, this.b);
        return xu0VarH.g();
    }

    public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
        gg ggVar = this.a;
        a(windowInsetsAnimation);
        jy jyVarC = jy.c(bounds.getLowerBound());
        jy jyVarC2 = jy.c(bounds.getUpperBound());
        View view = (View) ggVar.d;
        int[] iArr = (int[]) ggVar.e;
        view.getLocationOnScreen(iArr);
        int i = ggVar.a - iArr[1];
        ggVar.b = i;
        view.setTranslationY(i);
        g0.q();
        return g0.k(jyVarC.d(), jyVarC2.d());
    }
}
