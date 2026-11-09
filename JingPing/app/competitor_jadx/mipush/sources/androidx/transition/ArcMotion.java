package androidx.transition;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import defpackage.la0;
import defpackage.qm;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class ArcMotion extends PathMotion {
    public static final float a = (float) Math.tan(Math.toRadians(35.0d));

    public ArcMotion(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, qm.y);
        XmlPullParser xmlPullParser = (XmlPullParser) attributeSet;
        a(!la0.J(xmlPullParser, "minimumVerticalAngle") ? 0.0f : typedArrayObtainStyledAttributes.getFloat(1, 0.0f));
        a(xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "minimumHorizontalAngle") != null ? typedArrayObtainStyledAttributes.getFloat(0, 0.0f) : 0.0f);
        a(xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", "maximumAngle") != null ? typedArrayObtainStyledAttributes.getFloat(2, 70.0f) : 70.0f);
        typedArrayObtainStyledAttributes.recycle();
    }

    public static float a(float f) {
        if (f < 0.0f || f > 90.0f) {
            throw new IllegalArgumentException("Arc must be between 0 and 90 degrees");
        }
        return (float) Math.tan(Math.toRadians(f / 2.0f));
    }
}
