package defpackage;

import android.animation.TypeEvaluator;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public final class zm0 implements TypeEvaluator {
    public final /* synthetic */ int a = 1;
    public Rect b;

    public /* synthetic */ zm0() {
    }

    @Override // android.animation.TypeEvaluator
    public final Object evaluate(float f, Object obj, Object obj2) {
        switch (this.a) {
            case 0:
                Rect rect = (Rect) obj;
                Rect rect2 = (Rect) obj2;
                int i = rect.left + ((int) ((rect2.left - r0) * f));
                int i2 = rect.top + ((int) ((rect2.top - r1) * f));
                int i3 = rect.right + ((int) ((rect2.right - r2) * f));
                int i4 = rect.bottom + ((int) ((rect2.bottom - r6) * f));
                Rect rect3 = this.b;
                rect3.set(i, i2, i3, i4);
                return rect3;
            default:
                Rect rect4 = (Rect) obj;
                Rect rect5 = (Rect) obj2;
                int i5 = rect4.left + ((int) ((rect5.left - r0) * f));
                int i6 = rect4.top + ((int) ((rect5.top - r1) * f));
                int i7 = rect4.right + ((int) ((rect5.right - r2) * f));
                int i8 = rect4.bottom + ((int) ((rect5.bottom - r6) * f));
                Rect rect6 = this.b;
                if (rect6 == null) {
                    return new Rect(i5, i6, i7, i8);
                }
                rect6.set(i5, i6, i7, i8);
                return rect6;
        }
    }

    public zm0(Rect rect) {
        this.b = rect;
    }
}
