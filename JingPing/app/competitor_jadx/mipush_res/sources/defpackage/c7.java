package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import java.lang.ref.WeakReference;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class c7 {
    public final TextView a;
    public x01 b;
    public x01 c;
    public x01 d;
    public x01 e;
    public x01 f;
    public x01 g;
    public x01 h;
    public final l7 i;
    public int j = 0;
    public int k = -1;
    public Typeface l;
    public boolean m;

    public c7(TextView textView) {
        this.a = textView;
        this.i = new l7(textView);
    }

    public static x01 c(Context context, f6 f6Var, int i) {
        ColorStateList colorStateListF;
        synchronized (f6Var) {
            colorStateListF = f6Var.a.f(context, i);
        }
        if (colorStateListF == null) {
            return null;
        }
        x01 x01Var = new x01();
        x01Var.d = true;
        x01Var.a = colorStateListF;
        return x01Var;
    }

    public static void h(EditorInfo editorInfo, InputConnection inputConnection, TextView textView) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 30 || inputConnection == null) {
            return;
        }
        CharSequence text = textView.getText();
        if (i >= 30) {
            ks.a(editorInfo, text);
            return;
        }
        text.getClass();
        if (i >= 30) {
            ks.a(editorInfo, text);
            return;
        }
        int i2 = editorInfo.initialSelStart;
        int i3 = editorInfo.initialSelEnd;
        int i4 = i2 > i3 ? i3 : i2;
        if (i2 <= i3) {
            i2 = i3;
        }
        int length = text.length();
        if (i4 < 0 || i2 > length) {
            z91.j0(editorInfo, null, 0, 0);
            return;
        }
        int i5 = editorInfo.inputType & 4095;
        if (i5 == 129 || i5 == 225 || i5 == 18) {
            z91.j0(editorInfo, null, 0, 0);
            return;
        }
        if (length <= 2048) {
            z91.j0(editorInfo, text, i4, i2);
            return;
        }
        int i6 = i2 - i4;
        int i7 = i6 > 1024 ? 0 : i6;
        int i8 = 2048 - i7;
        int iMin = Math.min(text.length() - i2, i8 - Math.min(i4, (int) (((double) i8) * 0.8d)));
        int iMin2 = Math.min(i4, i8 - iMin);
        int i9 = i4 - iMin2;
        if (Character.isLowSurrogate(text.charAt(i9))) {
            i9++;
            iMin2--;
        }
        if (Character.isHighSurrogate(text.charAt((i2 + iMin) - 1))) {
            iMin--;
        }
        int i10 = iMin2 + i7;
        z91.j0(editorInfo, i7 != i6 ? TextUtils.concat(text.subSequence(i9, i9 + iMin2), text.subSequence(i2, iMin + i2)) : text.subSequence(i9, i10 + iMin + i9), iMin2, i10);
    }

    public final void a(Drawable drawable, x01 x01Var) {
        if (drawable == null || x01Var == null) {
            return;
        }
        f6.e(drawable, x01Var, this.a.getDrawableState());
    }

    public final void b() {
        x01 x01Var = this.b;
        TextView textView = this.a;
        if (x01Var != null || this.c != null || this.d != null || this.e != null) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            a(compoundDrawables[0], this.b);
            a(compoundDrawables[1], this.c);
            a(compoundDrawables[2], this.d);
            a(compoundDrawables[3], this.e);
        }
        if (this.f == null && this.g == null) {
            return;
        }
        Drawable[] drawableArrA = y6.a(textView);
        a(drawableArrA[0], this.f);
        a(drawableArrA[2], this.g);
    }

    public final ColorStateList d() {
        x01 x01Var = this.h;
        if (x01Var != null) {
            return x01Var.a;
        }
        return null;
    }

    public final PorterDuff.Mode e() {
        x01 x01Var = this.h;
        if (x01Var != null) {
            return x01Var.b;
        }
        return null;
    }

    public final void f(AttributeSet attributeSet, int i) {
        boolean z;
        boolean z2;
        String string;
        String string2;
        float f;
        int i2;
        ColorStateList colorStateList;
        int resourceId;
        int resourceId2;
        TextView textView = this.a;
        Context context = textView.getContext();
        f6 f6VarA = f6.a();
        ou ouVarP = ou.P(context, attributeSet, R$styleable.AppCompatTextHelper, i);
        f51.n(textView, textView.getContext(), R$styleable.AppCompatTextHelper, attributeSet, (TypedArray) ouVarP.G, i);
        int i3 = R$styleable.AppCompatTextHelper_android_textAppearance;
        TypedArray typedArray = (TypedArray) ouVarP.G;
        int resourceId3 = typedArray.getResourceId(i3, -1);
        if (typedArray.hasValue(R$styleable.AppCompatTextHelper_android_drawableLeft)) {
            this.b = c(context, f6VarA, typedArray.getResourceId(R$styleable.AppCompatTextHelper_android_drawableLeft, 0));
        }
        if (typedArray.hasValue(R$styleable.AppCompatTextHelper_android_drawableTop)) {
            this.c = c(context, f6VarA, typedArray.getResourceId(R$styleable.AppCompatTextHelper_android_drawableTop, 0));
        }
        if (typedArray.hasValue(R$styleable.AppCompatTextHelper_android_drawableRight)) {
            this.d = c(context, f6VarA, typedArray.getResourceId(R$styleable.AppCompatTextHelper_android_drawableRight, 0));
        }
        if (typedArray.hasValue(R$styleable.AppCompatTextHelper_android_drawableBottom)) {
            this.e = c(context, f6VarA, typedArray.getResourceId(R$styleable.AppCompatTextHelper_android_drawableBottom, 0));
        }
        int i4 = Build.VERSION.SDK_INT;
        if (typedArray.hasValue(R$styleable.AppCompatTextHelper_android_drawableStart)) {
            this.f = c(context, f6VarA, typedArray.getResourceId(R$styleable.AppCompatTextHelper_android_drawableStart, 0));
        }
        if (typedArray.hasValue(R$styleable.AppCompatTextHelper_android_drawableEnd)) {
            this.g = c(context, f6VarA, typedArray.getResourceId(R$styleable.AppCompatTextHelper_android_drawableEnd, 0));
        }
        ouVarP.S();
        boolean z3 = textView.getTransformationMethod() instanceof PasswordTransformationMethod;
        if (resourceId3 != -1) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(resourceId3, R$styleable.TextAppearance);
            ou ouVar = new ou(context, typedArrayObtainStyledAttributes);
            if (z3 || !typedArrayObtainStyledAttributes.hasValue(R$styleable.TextAppearance_textAllCaps)) {
                z = false;
                z2 = false;
            } else {
                z = typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
                z2 = true;
            }
            n(context, ouVar);
            string2 = typedArrayObtainStyledAttributes.hasValue(R$styleable.TextAppearance_textLocale) ? typedArrayObtainStyledAttributes.getString(R$styleable.TextAppearance_textLocale) : null;
            string = (i4 < 26 || !typedArrayObtainStyledAttributes.hasValue(R$styleable.TextAppearance_fontVariationSettings)) ? null : typedArrayObtainStyledAttributes.getString(R$styleable.TextAppearance_fontVariationSettings);
            ouVar.S();
        } else {
            z = false;
            z2 = false;
            string = null;
            string2 = null;
        }
        TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, R$styleable.TextAppearance, i, 0);
        ou ouVar2 = new ou(context, typedArrayObtainStyledAttributes2);
        if (!z3 && typedArrayObtainStyledAttributes2.hasValue(R$styleable.TextAppearance_textAllCaps)) {
            z = typedArrayObtainStyledAttributes2.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
            z2 = true;
        }
        if (typedArrayObtainStyledAttributes2.hasValue(R$styleable.TextAppearance_textLocale)) {
            string2 = typedArrayObtainStyledAttributes2.getString(R$styleable.TextAppearance_textLocale);
        }
        if (i4 >= 26 && typedArrayObtainStyledAttributes2.hasValue(R$styleable.TextAppearance_fontVariationSettings)) {
            string = typedArrayObtainStyledAttributes2.getString(R$styleable.TextAppearance_fontVariationSettings);
        }
        if (i4 >= 28 && typedArrayObtainStyledAttributes2.hasValue(R$styleable.TextAppearance_android_textSize) && typedArrayObtainStyledAttributes2.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        n(context, ouVar2);
        ouVar2.S();
        if (!z3 && z2) {
            textView.setAllCaps(z);
        }
        Typeface typeface = this.l;
        if (typeface != null) {
            if (this.k == -1) {
                textView.setTypeface(typeface, this.j);
            } else {
                textView.setTypeface(typeface);
            }
        }
        if (string != null) {
            a7.d(textView, string);
        }
        if (string2 != null) {
            z6.b(textView, z6.a(string2));
        }
        int[] iArr = R$styleable.AppCompatTextView;
        l7 l7Var = this.i;
        Context context2 = l7Var.j;
        TypedArray typedArrayObtainStyledAttributes3 = context2.obtainStyledAttributes(attributeSet, iArr, i, 0);
        TextView textView2 = l7Var.i;
        f51.n(textView2, textView2.getContext(), R$styleable.AppCompatTextView, attributeSet, typedArrayObtainStyledAttributes3, i);
        if (typedArrayObtainStyledAttributes3.hasValue(R$styleable.AppCompatTextView_autoSizeTextType)) {
            l7Var.a = typedArrayObtainStyledAttributes3.getInt(R$styleable.AppCompatTextView_autoSizeTextType, 0);
        }
        float dimension = typedArrayObtainStyledAttributes3.hasValue(R$styleable.AppCompatTextView_autoSizeStepGranularity) ? typedArrayObtainStyledAttributes3.getDimension(R$styleable.AppCompatTextView_autoSizeStepGranularity, -1.0f) : -1.0f;
        float dimension2 = typedArrayObtainStyledAttributes3.hasValue(R$styleable.AppCompatTextView_autoSizeMinTextSize) ? typedArrayObtainStyledAttributes3.getDimension(R$styleable.AppCompatTextView_autoSizeMinTextSize, -1.0f) : -1.0f;
        float dimension3 = typedArrayObtainStyledAttributes3.hasValue(R$styleable.AppCompatTextView_autoSizeMaxTextSize) ? typedArrayObtainStyledAttributes3.getDimension(R$styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0f) : -1.0f;
        if (!typedArrayObtainStyledAttributes3.hasValue(R$styleable.AppCompatTextView_autoSizePresetSizes) || (resourceId2 = typedArrayObtainStyledAttributes3.getResourceId(R$styleable.AppCompatTextView_autoSizePresetSizes, 0)) <= 0) {
            f = -1.0f;
        } else {
            TypedArray typedArrayObtainTypedArray = typedArrayObtainStyledAttributes3.getResources().obtainTypedArray(resourceId2);
            int length = typedArrayObtainTypedArray.length();
            int[] iArr2 = new int[length];
            if (length > 0) {
                f = -1.0f;
                for (int i5 = 0; i5 < length; i5++) {
                    iArr2[i5] = typedArrayObtainTypedArray.getDimensionPixelSize(i5, -1);
                }
                l7Var.f = l7.b(iArr2);
                l7Var.i();
            } else {
                f = -1.0f;
            }
            typedArrayObtainTypedArray.recycle();
        }
        typedArrayObtainStyledAttributes3.recycle();
        if (!l7Var.j()) {
            l7Var.a = 0;
        } else if (l7Var.a == 1) {
            if (!l7Var.g) {
                DisplayMetrics displayMetrics = context2.getResources().getDisplayMetrics();
                if (dimension2 == f) {
                    dimension2 = TypedValue.applyDimension(2, 12.0f, displayMetrics);
                }
                if (dimension3 == f) {
                    dimension3 = TypedValue.applyDimension(2, 112.0f, displayMetrics);
                }
                if (dimension == f) {
                    dimension = 1.0f;
                }
                l7Var.k(dimension2, dimension3, dimension);
            }
            l7Var.h();
        }
        if (q61.b && l7Var.a != 0) {
            int[] iArr3 = l7Var.f;
            if (iArr3.length > 0) {
                if (a7.a(textView) != f) {
                    a7.b(textView, Math.round(l7Var.d), Math.round(l7Var.e), Math.round(l7Var.c), 0);
                } else {
                    a7.c(textView, iArr3, 0);
                }
            }
        }
        TypedArray typedArrayObtainStyledAttributes4 = context.obtainStyledAttributes(attributeSet, R$styleable.AppCompatTextView);
        int resourceId4 = typedArrayObtainStyledAttributes4.getResourceId(R$styleable.AppCompatTextView_drawableLeftCompat, -1);
        Drawable drawableB = resourceId4 != -1 ? f6VarA.b(context, resourceId4) : null;
        int resourceId5 = typedArrayObtainStyledAttributes4.getResourceId(R$styleable.AppCompatTextView_drawableTopCompat, -1);
        Drawable drawableB2 = resourceId5 != -1 ? f6VarA.b(context, resourceId5) : null;
        int resourceId6 = typedArrayObtainStyledAttributes4.getResourceId(R$styleable.AppCompatTextView_drawableRightCompat, -1);
        Drawable drawableB3 = resourceId6 != -1 ? f6VarA.b(context, resourceId6) : null;
        int resourceId7 = typedArrayObtainStyledAttributes4.getResourceId(R$styleable.AppCompatTextView_drawableBottomCompat, -1);
        Drawable drawableB4 = resourceId7 != -1 ? f6VarA.b(context, resourceId7) : null;
        int resourceId8 = typedArrayObtainStyledAttributes4.getResourceId(R$styleable.AppCompatTextView_drawableStartCompat, -1);
        Drawable drawableB5 = resourceId8 != -1 ? f6VarA.b(context, resourceId8) : null;
        int resourceId9 = typedArrayObtainStyledAttributes4.getResourceId(R$styleable.AppCompatTextView_drawableEndCompat, -1);
        Drawable drawableB6 = resourceId9 != -1 ? f6VarA.b(context, resourceId9) : null;
        if (drawableB5 != null || drawableB6 != null) {
            Drawable[] drawableArrA = y6.a(textView);
            if (drawableB5 == null) {
                drawableB5 = drawableArrA[0];
            }
            if (drawableB2 == null) {
                drawableB2 = drawableArrA[1];
            }
            if (drawableB6 == null) {
                drawableB6 = drawableArrA[2];
            }
            if (drawableB4 == null) {
                drawableB4 = drawableArrA[3];
            }
            y6.b(textView, drawableB5, drawableB2, drawableB6, drawableB4);
        } else if (drawableB != null || drawableB2 != null || drawableB3 != null || drawableB4 != null) {
            Drawable[] drawableArrA2 = y6.a(textView);
            Drawable drawable = drawableArrA2[0];
            if (drawable == null && drawableArrA2[2] == null) {
                Drawable[] compoundDrawables = textView.getCompoundDrawables();
                if (drawableB == null) {
                    drawableB = compoundDrawables[0];
                }
                if (drawableB2 == null) {
                    drawableB2 = compoundDrawables[1];
                }
                if (drawableB3 == null) {
                    drawableB3 = compoundDrawables[2];
                }
                if (drawableB4 == null) {
                    drawableB4 = compoundDrawables[3];
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(drawableB, drawableB2, drawableB3, drawableB4);
            } else {
                if (drawableB2 == null) {
                    drawableB2 = drawableArrA2[1];
                }
                Drawable drawable2 = drawableArrA2[2];
                if (drawableB4 == null) {
                    drawableB4 = drawableArrA2[3];
                }
                y6.b(textView, drawable, drawableB2, drawable2, drawableB4);
            }
        }
        if (typedArrayObtainStyledAttributes4.hasValue(R$styleable.AppCompatTextView_drawableTint)) {
            int i6 = R$styleable.AppCompatTextView_drawableTint;
            if (!typedArrayObtainStyledAttributes4.hasValue(i6) || (resourceId = typedArrayObtainStyledAttributes4.getResourceId(i6, 0)) == 0 || (colorStateList = t40.Y(context, resourceId)) == null) {
                colorStateList = typedArrayObtainStyledAttributes4.getColorStateList(i6);
            }
            c01.f(textView, colorStateList);
        }
        if (typedArrayObtainStyledAttributes4.hasValue(R$styleable.AppCompatTextView_drawableTintMode)) {
            i2 = -1;
            c01.g(textView, op.c(typedArrayObtainStyledAttributes4.getInt(R$styleable.AppCompatTextView_drawableTintMode, -1), null));
        } else {
            i2 = -1;
        }
        int dimensionPixelSize = typedArrayObtainStyledAttributes4.getDimensionPixelSize(R$styleable.AppCompatTextView_firstBaselineToTopHeight, i2);
        int dimensionPixelSize2 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(R$styleable.AppCompatTextView_lastBaselineToBottomHeight, i2);
        int dimensionPixelSize3 = typedArrayObtainStyledAttributes4.getDimensionPixelSize(R$styleable.AppCompatTextView_lineHeight, i2);
        typedArrayObtainStyledAttributes4.recycle();
        if (dimensionPixelSize != i2) {
            fa0.t0(textView, dimensionPixelSize);
        }
        if (dimensionPixelSize2 != i2) {
            fa0.u0(textView, dimensionPixelSize2);
        }
        if (dimensionPixelSize3 != i2) {
            la0.g(dimensionPixelSize3);
            if (dimensionPixelSize3 != textView.getPaint().getFontMetricsInt(null)) {
                textView.setLineSpacing(dimensionPixelSize3 - r1, 1.0f);
            }
        }
    }

    public final void g(Context context, int i) {
        String string;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.TextAppearance);
        ou ouVar = new ou(context, typedArrayObtainStyledAttributes);
        boolean zHasValue = typedArrayObtainStyledAttributes.hasValue(R$styleable.TextAppearance_textAllCaps);
        TextView textView = this.a;
        if (zHasValue) {
            textView.setAllCaps(typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false));
        }
        int i2 = Build.VERSION.SDK_INT;
        if (typedArrayObtainStyledAttributes.hasValue(R$styleable.TextAppearance_android_textSize) && typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.TextAppearance_android_textSize, -1) == 0) {
            textView.setTextSize(0, 0.0f);
        }
        n(context, ouVar);
        if (i2 >= 26 && typedArrayObtainStyledAttributes.hasValue(R$styleable.TextAppearance_fontVariationSettings) && (string = typedArrayObtainStyledAttributes.getString(R$styleable.TextAppearance_fontVariationSettings)) != null) {
            a7.d(textView, string);
        }
        ouVar.S();
        Typeface typeface = this.l;
        if (typeface != null) {
            textView.setTypeface(typeface, this.j);
        }
    }

    public final void i(int i, int i2, int i3, int i4) {
        l7 l7Var = this.i;
        if (l7Var.j()) {
            DisplayMetrics displayMetrics = l7Var.j.getResources().getDisplayMetrics();
            l7Var.k(TypedValue.applyDimension(i4, i, displayMetrics), TypedValue.applyDimension(i4, i2, displayMetrics), TypedValue.applyDimension(i4, i3, displayMetrics));
            if (l7Var.h()) {
                l7Var.a();
            }
        }
    }

    public final void j(int[] iArr, int i) {
        l7 l7Var = this.i;
        if (l7Var.j()) {
            int length = iArr.length;
            if (length > 0) {
                int[] iArrCopyOf = new int[length];
                if (i == 0) {
                    iArrCopyOf = Arrays.copyOf(iArr, length);
                } else {
                    DisplayMetrics displayMetrics = l7Var.j.getResources().getDisplayMetrics();
                    for (int i2 = 0; i2 < length; i2++) {
                        iArrCopyOf[i2] = Math.round(TypedValue.applyDimension(i, iArr[i2], displayMetrics));
                    }
                }
                l7Var.f = l7.b(iArrCopyOf);
                if (!l7Var.i()) {
                    throw new IllegalArgumentException("None of the preset sizes is valid: " + Arrays.toString(iArr));
                }
            } else {
                l7Var.g = false;
            }
            if (l7Var.h()) {
                l7Var.a();
            }
        }
    }

    public final void k(int i) {
        l7 l7Var = this.i;
        if (l7Var.j()) {
            if (i == 0) {
                l7Var.a = 0;
                l7Var.d = -1.0f;
                l7Var.e = -1.0f;
                l7Var.c = -1.0f;
                l7Var.f = new int[0];
                l7Var.b = false;
                return;
            }
            if (i != 1) {
                throw new IllegalArgumentException(zn.j(i, "Unknown auto-size text type: "));
            }
            DisplayMetrics displayMetrics = l7Var.j.getResources().getDisplayMetrics();
            l7Var.k(TypedValue.applyDimension(2, 12.0f, displayMetrics), TypedValue.applyDimension(2, 112.0f, displayMetrics), 1.0f);
            if (l7Var.h()) {
                l7Var.a();
            }
        }
    }

    public final void l(ColorStateList colorStateList) {
        if (this.h == null) {
            this.h = new x01();
        }
        x01 x01Var = this.h;
        x01Var.a = colorStateList;
        x01Var.d = colorStateList != null;
        this.b = x01Var;
        this.c = x01Var;
        this.d = x01Var;
        this.e = x01Var;
        this.f = x01Var;
        this.g = x01Var;
    }

    public final void m(PorterDuff.Mode mode) {
        if (this.h == null) {
            this.h = new x01();
        }
        x01 x01Var = this.h;
        x01Var.b = mode;
        x01Var.c = mode != null;
        this.b = x01Var;
        this.c = x01Var;
        this.d = x01Var;
        this.e = x01Var;
        this.f = x01Var;
        this.g = x01Var;
    }

    public final void n(Context context, ou ouVar) {
        String string;
        int i = R$styleable.TextAppearance_android_textStyle;
        int i2 = this.j;
        TypedArray typedArray = (TypedArray) ouVar.G;
        this.j = typedArray.getInt(i, i2);
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 28) {
            int i4 = typedArray.getInt(R$styleable.TextAppearance_android_textFontWeight, -1);
            this.k = i4;
            if (i4 != -1) {
                this.j &= 2;
            }
        }
        if (!typedArray.hasValue(R$styleable.TextAppearance_android_fontFamily) && !typedArray.hasValue(R$styleable.TextAppearance_fontFamily)) {
            if (typedArray.hasValue(R$styleable.TextAppearance_android_typeface)) {
                this.m = false;
                int i5 = typedArray.getInt(R$styleable.TextAppearance_android_typeface, 1);
                if (i5 == 1) {
                    this.l = Typeface.SANS_SERIF;
                    return;
                } else if (i5 == 2) {
                    this.l = Typeface.SERIF;
                    return;
                } else {
                    if (i5 != 3) {
                        return;
                    }
                    this.l = Typeface.MONOSPACE;
                    return;
                }
            }
            return;
        }
        this.l = null;
        int i6 = typedArray.hasValue(R$styleable.TextAppearance_fontFamily) ? R$styleable.TextAppearance_fontFamily : R$styleable.TextAppearance_android_fontFamily;
        int i7 = this.k;
        int i8 = this.j;
        if (!context.isRestricted()) {
            try {
                Typeface typefaceC = ouVar.C(i6, this.j, new w6(this, i7, i8, new WeakReference(this.a)));
                if (typefaceC != null) {
                    if (i3 < 28 || this.k == -1) {
                        this.l = typefaceC;
                    } else {
                        this.l = b7.a(Typeface.create(typefaceC, 0), this.k, (this.j & 2) != 0);
                    }
                }
                this.m = this.l == null;
            } catch (Resources.NotFoundException | UnsupportedOperationException unused) {
            }
        }
        if (this.l != null || (string = typedArray.getString(i6)) == null) {
            return;
        }
        if (Build.VERSION.SDK_INT < 28 || this.k == -1) {
            this.l = Typeface.create(string, this.j);
        } else {
            this.l = b7.a(Typeface.create(string, 0), this.k, (this.j & 2) != 0);
        }
    }
}
