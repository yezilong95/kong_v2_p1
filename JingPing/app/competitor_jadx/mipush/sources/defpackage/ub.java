package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import com.google.android.material.R$attr;
import com.google.android.material.R$styleable;
import com.google.android.material.datepicker.l;

/* JADX INFO: loaded from: classes.dex */
public final class ub {
    public final Object a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;
    public final Object g;
    public final Object h;

    public ub() {
        this.a = new int[16];
        this.b = new int[16];
        this.c = new int[16];
        this.d = new int[16];
        this.e = new int[16];
        this.f = new int[16];
        this.g = new int[16];
        this.h = new int[16];
    }

    public ub(Context context) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(s90.W(context, R$attr.materialCalendarStyle, l.class.getCanonicalName()).data, R$styleable.MaterialCalendar);
        this.a = i5.e(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_dayStyle, 0));
        this.g = i5.e(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_dayInvalidStyle, 0));
        this.b = i5.e(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_daySelectedStyle, 0));
        this.c = i5.e(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_dayTodayStyle, 0));
        ColorStateList colorStateListP = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendar_rangeFillColor);
        this.d = i5.e(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_yearStyle, 0));
        this.e = i5.e(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_yearSelectedStyle, 0));
        this.f = i5.e(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendar_yearTodayStyle, 0));
        Paint paint = new Paint();
        this.h = paint;
        paint.setColor(colorStateListP.getDefaultColor());
        typedArrayObtainStyledAttributes.recycle();
    }
}
