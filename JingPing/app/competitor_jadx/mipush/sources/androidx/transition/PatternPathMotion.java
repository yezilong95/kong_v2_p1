package androidx.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import defpackage.la0;
import defpackage.o80;
import defpackage.qm;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class PatternPathMotion extends PathMotion {
    public final Path a = new Path();
    public final Matrix b = new Matrix();

    public PatternPathMotion(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.z);
        try {
            String strZ = la0.z(typedArrayObtainStyledAttributes, (XmlPullParser) attributeSet, "patternPathData", 0);
            if (strZ == null) {
                throw new RuntimeException("pathData must be supplied for patternPathMotion");
            }
            a(o80.r(strZ));
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public final void a(Path path) {
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float[] fArr = new float[2];
        pathMeasure.getPosTan(pathMeasure.getLength(), fArr, null);
        float f = fArr[0];
        float f2 = fArr[1];
        pathMeasure.getPosTan(0.0f, fArr, null);
        float f3 = fArr[0];
        float f4 = fArr[1];
        if (f3 == f && f4 == f2) {
            throw new IllegalArgumentException("pattern must not end at the starting point");
        }
        Matrix matrix = this.b;
        matrix.setTranslate(-f3, -f4);
        float f5 = f2 - f4;
        float fSqrt = 1.0f / ((float) Math.sqrt((f5 * f5) + (r2 * r2)));
        matrix.postScale(fSqrt, fSqrt);
        matrix.postRotate((float) Math.toDegrees(-Math.atan2(f5, f - f3)));
        path.transform(matrix, this.a);
    }
}
