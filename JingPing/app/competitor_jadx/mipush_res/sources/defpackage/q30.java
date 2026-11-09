package defpackage;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.widget.ImageView;

/* JADX INFO: loaded from: classes.dex */
public final class q30 {
    public final float[] a = new float[20];
    public final ColorMatrix b = new ColorMatrix();
    public final ColorMatrix c = new ColorMatrix();
    public float d = 1.0f;
    public float e = 1.0f;
    public float f = 1.0f;
    public float g = 1.0f;

    public final void a(ImageView imageView) {
        boolean z;
        float f;
        char c;
        char c2;
        char c3;
        char c4;
        char c5;
        char c6;
        float f2;
        char c7;
        float fLog;
        float fPow;
        char c8;
        float f3;
        float fLog2;
        ColorMatrix colorMatrix = this.b;
        colorMatrix.reset();
        float f4 = this.e;
        float[] fArr = this.a;
        boolean z2 = true;
        if (f4 != 1.0f) {
            float f5 = 1.0f - f4;
            float f6 = 0.2999f * f5;
            float f7 = 0.587f * f5;
            float f8 = f5 * 0.114f;
            fArr[0] = f6 + f4;
            fArr[1] = f7;
            fArr[2] = f8;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = f6;
            fArr[6] = f7 + f4;
            fArr[7] = f8;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = f6;
            fArr[11] = f7;
            fArr[12] = f8 + f4;
            fArr[13] = 0.0f;
            fArr[14] = 0.0f;
            fArr[15] = 0.0f;
            fArr[16] = 0.0f;
            fArr[17] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
            colorMatrix.set(fArr);
            z = true;
        } else {
            z = false;
        }
        float f9 = this.f;
        ColorMatrix colorMatrix2 = this.c;
        if (f9 != 1.0f) {
            colorMatrix2.setScale(f9, f9, f9, 1.0f);
            colorMatrix.postConcat(colorMatrix2);
            z = true;
        }
        float f10 = this.g;
        if (f10 != 1.0f) {
            if (f10 <= 0.0f) {
                f10 = 0.01f;
            }
            float f11 = (5000.0f / f10) / 100.0f;
            f = 1.0f;
            if (f11 > 66.0f) {
                f2 = 66.0f;
                c = 17;
                c2 = 16;
                double d = f11 - 60.0f;
                c3 = 15;
                c4 = 14;
                fPow = ((float) Math.pow(d, -0.13320475816726685d)) * 329.69873f;
                c7 = '\r';
                c6 = '\f';
                fLog = ((float) Math.pow(d, 0.07551484555006027d)) * 288.12216f;
            } else {
                f2 = 66.0f;
                c = 17;
                c2 = 16;
                c3 = 15;
                c4 = 14;
                c7 = '\r';
                c6 = '\f';
                fLog = (((float) Math.log(f11)) * 99.4708f) - 161.11957f;
                fPow = 255.0f;
            }
            if (f11 >= f2) {
                c8 = c7;
                f3 = 305.0448f;
                fLog2 = 255.0f;
            } else if (f11 > 19.0f) {
                c8 = c7;
                f3 = 305.0448f;
                fLog2 = (((float) Math.log(f11 - 10.0f)) * 138.51773f) - 305.0448f;
            } else {
                c8 = c7;
                f3 = 305.0448f;
                fLog2 = 0.0f;
            }
            float fMin = Math.min(255.0f, Math.max(fPow, 0.0f));
            float fMin2 = Math.min(255.0f, Math.max(fLog, 0.0f));
            float fMin3 = Math.min(255.0f, Math.max(fLog2, 0.0f));
            float fLog3 = (((float) Math.log(50.0f)) * 99.4708f) - 161.11957f;
            c5 = c8;
            float fLog4 = (((float) Math.log(40.0f)) * 138.51773f) - f3;
            float fMin4 = Math.min(255.0f, Math.max(255.0f, 0.0f));
            float fMin5 = Math.min(255.0f, Math.max(fLog3, 0.0f));
            float fMin6 = fMin3 / Math.min(255.0f, Math.max(fLog4, 0.0f));
            fArr[0] = fMin / fMin4;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = fMin2 / fMin5;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[c6] = fMin6;
            fArr[c5] = 0.0f;
            fArr[c4] = 0.0f;
            fArr[c3] = 0.0f;
            fArr[c2] = 0.0f;
            fArr[c] = 0.0f;
            fArr[18] = 1.0f;
            fArr[19] = 0.0f;
            colorMatrix2.set(fArr);
            colorMatrix.postConcat(colorMatrix2);
            z = true;
        } else {
            f = 1.0f;
            c = 17;
            c2 = 16;
            c3 = 15;
            c4 = 14;
            c5 = '\r';
            c6 = '\f';
        }
        float f12 = this.d;
        if (f12 != f) {
            fArr[0] = f12;
            fArr[1] = 0.0f;
            fArr[2] = 0.0f;
            fArr[3] = 0.0f;
            fArr[4] = 0.0f;
            fArr[5] = 0.0f;
            fArr[6] = f12;
            fArr[7] = 0.0f;
            fArr[8] = 0.0f;
            fArr[9] = 0.0f;
            fArr[10] = 0.0f;
            fArr[11] = 0.0f;
            fArr[c6] = f12;
            fArr[c5] = 0.0f;
            fArr[c4] = 0.0f;
            fArr[c3] = 0.0f;
            fArr[c2] = 0.0f;
            fArr[c] = 0.0f;
            fArr[18] = f;
            fArr[19] = 0.0f;
            colorMatrix2.set(fArr);
            colorMatrix.postConcat(colorMatrix2);
        } else {
            z2 = z;
        }
        if (z2) {
            imageView.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
        } else {
            imageView.clearColorFilter();
        }
    }
}
