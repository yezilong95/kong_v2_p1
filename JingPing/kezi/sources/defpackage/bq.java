package defpackage;

import android.animation.FloatEvaluator;
import android.animation.TypeEvaluator;

/* JADX INFO: loaded from: classes.dex */
public final class bq implements TypeEvaluator {
    public final /* synthetic */ int a;
    public Object b;

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f, Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                float[] fArr = (float[]) obj;
                float[] fArr2 = (float[]) obj2;
                float[] fArr3 = (float[]) this.b;
                if (fArr3 == null) {
                    fArr3 = new float[fArr.length];
                }
                for (int i = 0; i < fArr3.length; i++) {
                    float f2 = fArr[i];
                    fArr3[i] = hk.d(fArr2[i], f2, f, f2);
                }
                return fArr3;
            default:
                float fFloatValue = ((FloatEvaluator) this.b).evaluate(f, (Number) obj, (Number) obj2).floatValue();
                if (fFloatValue < 0.1f) {
                    fFloatValue = 0.0f;
                }
                return Float.valueOf(fFloatValue);
        }
    }
}
