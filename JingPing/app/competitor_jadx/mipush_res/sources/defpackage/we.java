package defpackage;

import android.animation.TypeEvaluator;

/* JADX INFO: loaded from: classes.dex */
public final class we implements TypeEvaluator {
    public static final we b = new we();
    public final ze a = new ze();

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f, Object obj, Object obj2) {
        ze zeVar = (ze) obj;
        ze zeVar2 = (ze) obj2;
        float fR = o80.R(zeVar.a, zeVar2.a, f);
        float fR2 = o80.R(zeVar.b, zeVar2.b, f);
        float fR3 = o80.R(zeVar.c, zeVar2.c, f);
        ze zeVar3 = this.a;
        zeVar3.a = fR;
        zeVar3.b = fR2;
        zeVar3.c = fR3;
        return zeVar3;
    }
}
