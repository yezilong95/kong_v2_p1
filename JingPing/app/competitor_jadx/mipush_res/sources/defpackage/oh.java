package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintAttribute$AttributeType;
import androidx.constraintlayout.widget.R$styleable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class oh {
    public String a;
    public ConstraintAttribute$AttributeType b;
    public int c;
    public float d;
    public String e;
    public boolean f;
    public int g;

    public oh(oh ohVar, Object obj) {
        this.a = ohVar.a;
        this.b = ohVar.b;
        h(obj);
    }

    public static int a(int i) {
        int i2 = (i & (~(i >> 31))) - 255;
        return (i2 & (i2 >> 31)) + 255;
    }

    public static void e(Context context, XmlResourceParser xmlResourceParser, HashMap map) {
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType;
        Object string;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.CustomAttribute);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        String string2 = null;
        Object objValueOf = null;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType2 = null;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == R$styleable.CustomAttribute_attributeName) {
                string2 = typedArrayObtainStyledAttributes.getString(index);
                if (string2 != null && string2.length() > 0) {
                    string2 = Character.toUpperCase(string2.charAt(0)) + string2.substring(1);
                }
            } else if (index == R$styleable.CustomAttribute_customBoolean) {
                objValueOf = Boolean.valueOf(typedArrayObtainStyledAttributes.getBoolean(index, false));
                constraintAttribute$AttributeType2 = ConstraintAttribute$AttributeType.BOOLEAN_TYPE;
            } else {
                if (index == R$styleable.CustomAttribute_customColorValue) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.COLOR_TYPE;
                    string = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(index, 0));
                } else if (index == R$styleable.CustomAttribute_customColorDrawableValue) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.COLOR_DRAWABLE_TYPE;
                    string = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(index, 0));
                } else if (index == R$styleable.CustomAttribute_customPixelDimension) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.DIMENSION_TYPE;
                    string = Float.valueOf(TypedValue.applyDimension(1, typedArrayObtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics()));
                } else if (index == R$styleable.CustomAttribute_customDimension) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.DIMENSION_TYPE;
                    string = Float.valueOf(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == R$styleable.CustomAttribute_customFloatValue) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.FLOAT_TYPE;
                    string = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(index, Float.NaN));
                } else if (index == R$styleable.CustomAttribute_customIntegerValue) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.INT_TYPE;
                    string = Integer.valueOf(typedArrayObtainStyledAttributes.getInteger(index, -1));
                } else if (index == R$styleable.CustomAttribute_customStringValue) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.STRING_TYPE;
                    string = typedArrayObtainStyledAttributes.getString(index);
                }
                Object obj = string;
                constraintAttribute$AttributeType2 = constraintAttribute$AttributeType;
                objValueOf = obj;
            }
        }
        if (string2 != null && objValueOf != null) {
            oh ohVar = new oh();
            ohVar.a = string2;
            ohVar.b = constraintAttribute$AttributeType2;
            ohVar.h(objValueOf);
            map.put(string2, ohVar);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static void f(View view, HashMap map) {
        Class<?> cls = view.getClass();
        for (String str : map.keySet()) {
            oh ohVar = (oh) map.get(str);
            String strV = zn.v("set", str);
            try {
                int i = nh.a[ohVar.b.ordinal()];
                Class cls2 = Integer.TYPE;
                Class cls3 = Float.TYPE;
                switch (i) {
                    case 1:
                        cls.getMethod(strV, cls2).invoke(view, Integer.valueOf(ohVar.g));
                        break;
                    case 2:
                        Method method = cls.getMethod(strV, Drawable.class);
                        ColorDrawable colorDrawable = new ColorDrawable();
                        colorDrawable.setColor(ohVar.g);
                        method.invoke(view, colorDrawable);
                        break;
                    case 3:
                        cls.getMethod(strV, cls2).invoke(view, Integer.valueOf(ohVar.c));
                        break;
                    case 4:
                        cls.getMethod(strV, cls3).invoke(view, Float.valueOf(ohVar.d));
                        break;
                    case 5:
                        cls.getMethod(strV, CharSequence.class).invoke(view, ohVar.e);
                        break;
                    case 6:
                        cls.getMethod(strV, Boolean.TYPE).invoke(view, Boolean.valueOf(ohVar.f));
                        break;
                    case 7:
                        cls.getMethod(strV, cls3).invoke(view, Float.valueOf(ohVar.d));
                        break;
                }
            } catch (IllegalAccessException e) {
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                Log.e("TransitionLayout", e2.getMessage());
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                Log.e("TransitionLayout", cls.getName() + " must have a method " + strV);
            } catch (InvocationTargetException e3) {
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    public final float b() {
        switch (nh.a[this.b.ordinal()]) {
            case 1:
            case 2:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 3:
                return this.c;
            case 4:
                return this.d;
            case 5:
                throw new RuntimeException("Cannot interpolate String");
            case 6:
                return this.f ? 0.0f : 1.0f;
            case 7:
                return this.d;
            default:
                return Float.NaN;
        }
    }

    public final void c(float[] fArr) {
        switch (nh.a[this.b.ordinal()]) {
            case 1:
            case 2:
                int i = (this.g >> 24) & 255;
                float fPow = (float) Math.pow(((r0 >> 16) & 255) / 255.0f, 2.2d);
                float fPow2 = (float) Math.pow(((r0 >> 8) & 255) / 255.0f, 2.2d);
                float fPow3 = (float) Math.pow((r0 & 255) / 255.0f, 2.2d);
                fArr[0] = fPow;
                fArr[1] = fPow2;
                fArr[2] = fPow3;
                fArr[3] = i / 255.0f;
                return;
            case 3:
                fArr[0] = this.c;
                return;
            case 4:
                fArr[0] = this.d;
                return;
            case 5:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 6:
                fArr[0] = this.f ? 0.0f : 1.0f;
                return;
            case 7:
                fArr[0] = this.d;
                return;
            default:
                return;
        }
    }

    public final int d() {
        int i = nh.a[this.b.ordinal()];
        return (i == 1 || i == 2) ? 4 : 1;
    }

    public final void g(View view, float[] fArr) {
        Class<?> cls = view.getClass();
        StringBuilder sb = new StringBuilder("set");
        String str = this.a;
        sb.append(str);
        String string = sb.toString();
        try {
            int i = nh.a[this.b.ordinal()];
            Class cls2 = Integer.TYPE;
            Class cls3 = Float.TYPE;
            boolean z = true;
            switch (i) {
                case 1:
                    cls.getMethod(string, cls2).invoke(view, Integer.valueOf((a((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f)) << 16) | (a((int) (fArr[3] * 255.0f)) << 24) | (a((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | a((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f))));
                    return;
                case 2:
                    Method method = cls.getMethod(string, Drawable.class);
                    int iA = (a((int) (((float) Math.pow(fArr[0], 0.45454545454545453d)) * 255.0f)) << 16) | (a((int) (fArr[3] * 255.0f)) << 24) | (a((int) (((float) Math.pow(fArr[1], 0.45454545454545453d)) * 255.0f)) << 8) | a((int) (((float) Math.pow(fArr[2], 0.45454545454545453d)) * 255.0f));
                    ColorDrawable colorDrawable = new ColorDrawable();
                    colorDrawable.setColor(iA);
                    method.invoke(view, colorDrawable);
                    return;
                case 3:
                    cls.getMethod(string, cls2).invoke(view, Integer.valueOf((int) fArr[0]));
                    return;
                case 4:
                    cls.getMethod(string, cls3).invoke(view, Float.valueOf(fArr[0]));
                    return;
                case 5:
                    throw new RuntimeException("unable to interpolate strings " + str);
                case 6:
                    Method method2 = cls.getMethod(string, Boolean.TYPE);
                    if (fArr[0] <= 0.5f) {
                        z = false;
                    }
                    method2.invoke(view, Boolean.valueOf(z));
                    return;
                case 7:
                    cls.getMethod(string, cls3).invoke(view, Float.valueOf(fArr[0]));
                    return;
                default:
                    return;
            }
        } catch (IllegalAccessException e) {
            Log.e("TransitionLayout", "cannot access method " + string + "on View \"" + t40.g0(view) + "\"");
            e.printStackTrace();
        } catch (NoSuchMethodException e2) {
            Log.e("TransitionLayout", "no method " + string + "on View \"" + t40.g0(view) + "\"");
            e2.printStackTrace();
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
        }
    }

    public final void h(Object obj) {
        switch (nh.a[this.b.ordinal()]) {
            case 1:
            case 2:
                this.g = ((Integer) obj).intValue();
                break;
            case 3:
                this.c = ((Integer) obj).intValue();
                break;
            case 4:
                this.d = ((Float) obj).floatValue();
                break;
            case 5:
                this.e = (String) obj;
                break;
            case 6:
                this.f = ((Boolean) obj).booleanValue();
                break;
            case 7:
                this.d = ((Float) obj).floatValue();
                break;
        }
    }
}
