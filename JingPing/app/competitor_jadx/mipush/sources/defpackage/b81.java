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
public final class b81 extends WindowInsetsAnimation$Callback {
    public final sh a;
    public List b;
    public ArrayList c;
    public final HashMap d;

    public b81(sh shVar) {
        super(0);
        this.d = new HashMap();
        this.a = shVar;
    }

    public final e81 a(WindowInsetsAnimation windowInsetsAnimation) {
        e81 e81Var = (e81) this.d.get(windowInsetsAnimation);
        if (e81Var == null) {
            e81Var = new e81(0, null, 0L);
            if (Build.VERSION.SDK_INT >= 30) {
                e81Var.a = new c81(windowInsetsAnimation);
            }
            this.d.put(windowInsetsAnimation, e81Var);
        }
        return e81Var;
    }

    public final void onEnd(WindowInsetsAnimation windowInsetsAnimation) {
        sh shVar = this.a;
        a(windowInsetsAnimation);
        ((View) shVar.d).setTranslationY(0.0f);
        this.d.remove(windowInsetsAnimation);
    }

    public final void onPrepare(WindowInsetsAnimation windowInsetsAnimation) {
        sh shVar = this.a;
        a(windowInsetsAnimation);
        View view = (View) shVar.d;
        int[] iArr = (int[]) shVar.e;
        view.getLocationOnScreen(iArr);
        shVar.a = iArr[1];
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
            WindowInsetsAnimation windowInsetsAnimationM = b2.m(list.get(size));
            e81 e81VarA = a(windowInsetsAnimationM);
            e81VarA.a.d(windowInsetsAnimationM.getFraction());
            this.c.add(e81VarA);
        }
        sh shVar = this.a;
        s81 s81VarH = s81.h(null, windowInsets);
        shVar.b(s81VarH, this.b);
        return s81VarH.g();
    }

    public final WindowInsetsAnimation.Bounds onStart(WindowInsetsAnimation windowInsetsAnimation, WindowInsetsAnimation.Bounds bounds) {
        sh shVar = this.a;
        a(windowInsetsAnimation);
        m40 m40VarC = m40.c(bounds.getLowerBound());
        m40 m40VarC2 = m40.c(bounds.getUpperBound());
        View view = (View) shVar.d;
        int[] iArr = (int[]) shVar.e;
        view.getLocationOnScreen(iArr);
        int i = shVar.a - iArr[1];
        shVar.b = i;
        view.setTranslationY(i);
        b2.q();
        return b2.k(m40VarC.d(), m40VarC2.d());
    }
}
