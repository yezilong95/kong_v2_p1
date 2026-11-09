package defpackage;

import android.animation.TypeEvaluator;

/* JADX INFO: loaded from: classes.dex */
public final class u6 implements TypeEvaluator {
    public static final u6 b = new u6(0);
    public final /* synthetic */ int a;

    public /* synthetic */ u6(int i) {
        this.a = i;
    }

    public static Integer a(float f, Integer num, Integer num2) {
        int iIntValue = num.intValue();
        float f2 = ((iIntValue >> 24) & 255) / 255.0f;
        int iIntValue2 = num2.intValue();
        float f3 = ((iIntValue2 >> 24) & 255) / 255.0f;
        float fPow = (float) Math.pow(((iIntValue >> 16) & 255) / 255.0f, 2.2d);
        float fPow2 = (float) Math.pow(((iIntValue >> 8) & 255) / 255.0f, 2.2d);
        float fPow3 = (float) Math.pow((iIntValue & 255) / 255.0f, 2.2d);
        float fPow4 = (float) Math.pow(((iIntValue2 >> 16) & 255) / 255.0f, 2.2d);
        float fPow5 = (float) Math.pow(((iIntValue2 >> 8) & 255) / 255.0f, 2.2d);
        float fPow6 = (float) Math.pow((iIntValue2 & 255) / 255.0f, 2.2d);
        float fD = hk.d(f3, f2, f, f2);
        float fD2 = hk.d(fPow4, fPow, f, fPow);
        float fD3 = hk.d(fPow5, fPow2, f, fPow2);
        float fD4 = hk.d(fPow6, fPow3, f, fPow3);
        float fPow7 = ((float) Math.pow(fD2, 0.45454545454545453d)) * 255.0f;
        float fPow8 = ((float) Math.pow(fD3, 0.45454545454545453d)) * 255.0f;
        return Integer.valueOf(Math.round(((float) Math.pow(fD4, 0.45454545454545453d)) * 255.0f) | (Math.round(fPow7) << 16) | (Math.round(fD * 255.0f) << 24) | (Math.round(fPow8) << 8));
    }

    @Override // android.animation.TypeEvaluator
    public final /* bridge */ /* synthetic */ Object evaluate(float f, Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                return a(f, (Integer) obj, (Integer) obj2);
            default:
                return null;
        }
    }
}
