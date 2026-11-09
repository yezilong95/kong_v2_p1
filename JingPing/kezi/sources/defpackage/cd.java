package defpackage;

import android.animation.TypeEvaluator;

/* JADX INFO: loaded from: classes.dex */
public final class cd implements TypeEvaluator {
    public static final cd b = new cd();
    public final fd a = new fd();

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f, Object obj, Object obj2) {
        fd fdVar = (fd) obj;
        fd fdVar2 = (fd) obj2;
        float f2 = fdVar.a;
        float f3 = 1.0f - f;
        float f4 = (fdVar2.a * f) + (f2 * f3);
        float f5 = fdVar.b;
        float f6 = (fdVar2.b * f) + (f5 * f3);
        float f7 = fdVar.c;
        float f8 = (f * fdVar2.c) + (f3 * f7);
        fd fdVar3 = this.a;
        fdVar3.a = f4;
        fdVar3.b = f6;
        fdVar3.c = f8;
        return fdVar3;
    }
}
