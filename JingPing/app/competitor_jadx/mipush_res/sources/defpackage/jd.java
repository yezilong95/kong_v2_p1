package defpackage;

import android.view.View;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class jd {
    public final float a;
    public final float b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;
    public final float g;
    public final float h;

    public jd(View view) {
        this.a = view.getTranslationX();
        this.b = view.getTranslationY();
        WeakHashMap weakHashMap = f51.a;
        this.c = t41.l(view);
        this.d = view.getScaleX();
        this.e = view.getScaleY();
        this.f = view.getRotationX();
        this.g = view.getRotationY();
        this.h = view.getRotation();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof jd)) {
            return false;
        }
        jd jdVar = (jd) obj;
        return jdVar.a == this.a && jdVar.b == this.b && jdVar.c == this.c && jdVar.d == this.d && jdVar.e == this.e && jdVar.f == this.f && jdVar.g == this.g && jdVar.h == this.h;
    }

    public final int hashCode() {
        float f = this.a;
        int iFloatToIntBits = (f != 0.0f ? Float.floatToIntBits(f) : 0) * 31;
        float f2 = this.b;
        int iFloatToIntBits2 = (iFloatToIntBits + (f2 != 0.0f ? Float.floatToIntBits(f2) : 0)) * 31;
        float f3 = this.c;
        int iFloatToIntBits3 = (iFloatToIntBits2 + (f3 != 0.0f ? Float.floatToIntBits(f3) : 0)) * 31;
        float f4 = this.d;
        int iFloatToIntBits4 = (iFloatToIntBits3 + (f4 != 0.0f ? Float.floatToIntBits(f4) : 0)) * 31;
        float f5 = this.e;
        int iFloatToIntBits5 = (iFloatToIntBits4 + (f5 != 0.0f ? Float.floatToIntBits(f5) : 0)) * 31;
        float f6 = this.f;
        int iFloatToIntBits6 = (iFloatToIntBits5 + (f6 != 0.0f ? Float.floatToIntBits(f6) : 0)) * 31;
        float f7 = this.g;
        int iFloatToIntBits7 = (iFloatToIntBits6 + (f7 != 0.0f ? Float.floatToIntBits(f7) : 0)) * 31;
        float f8 = this.h;
        return iFloatToIntBits7 + (f8 != 0.0f ? Float.floatToIntBits(f8) : 0);
    }
}
