package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import com.google.android.material.datepicker.l;

/* JADX INFO: loaded from: classes.dex */
public final class ea {
    public final e4 a;
    public final e4 b;
    public final e4 c;
    public final e4 d;
    public final e4 e;
    public final e4 f;
    public final e4 g;
    public final Paint h;

    public ea(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(nv0.L(context, R$attr.materialCalendarStyle, l.class.getCanonicalName()).data, R$styleable.MaterialCalendar);
        this.a = e4.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_dayStyle, 0));
        this.g = e4.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_dayInvalidStyle, 0));
        this.b = e4.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_daySelectedStyle, 0));
        this.c = e4.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateListS = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendar_rangeFillColor);
        this.d = e4.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_yearStyle, 0));
        this.e = e4.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_yearSelectedStyle, 0));
        this.f = e4.b(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListS.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
