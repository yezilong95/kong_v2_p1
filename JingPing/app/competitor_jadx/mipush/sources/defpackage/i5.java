package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.R$styleable;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.bouncycastle.crypto.prng.SP800SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public final class i5 implements fu {
    public final /* synthetic */ int a;
    public int b;
    public final Object c;
    public final Object d;
    public Object e;
    public Object f;
    public Object g;

    public i5(int i) {
        this.a = 3;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.c = atomicBoolean;
        this.d = new AtomicInteger(0);
        byte[] bArr = new byte[8];
        ma0.S(System.currentTimeMillis(), bArr, 0);
        this.g = bArr;
        sl slVarB = wl.b();
        this.b = (i + 7) / 8;
        tl tlVar = new tl(atomicBoolean, slVarB);
        this.f = tlVar;
        i6 i6Var = new i6(25, new sl(0, this));
        i6Var.G = z91.l(jx0.b("Bouncy Castle Hybrid Entropy Source"));
        this.e = new SP800SecureRandom((tl) ((i5) ((sl) i6Var.F).b).f, new ou(new q20(new fq0()), tlVar.b(), (byte[]) i6Var.G, 13), false);
    }

    public static i5 e(Context context, int i) {
        la0.f("Cannot create a CalendarItemStyle with a styleResId of 0", i != 0);
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(i, R$styleable.MaterialCalendarItem);
        Rect rect = new Rect(typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetLeft, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetTop, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetRight, 0), typedArrayObtainStyledAttributes.getDimensionPixelOffset(R$styleable.MaterialCalendarItem_android_insetBottom, 0));
        ColorStateList colorStateListP = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemFillColor);
        ColorStateList colorStateListP2 = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemTextColor);
        ColorStateList colorStateListP3 = la0.p(context, typedArrayObtainStyledAttributes, R$styleable.MaterialCalendarItem_itemStrokeColor);
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.MaterialCalendarItem_itemStrokeWidth, 0);
        zu0 zu0VarA = zu0.a(context, typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearance, 0), typedArrayObtainStyledAttributes.getResourceId(R$styleable.MaterialCalendarItem_itemShapeAppearanceOverlay, 0)).a();
        typedArrayObtainStyledAttributes.recycle();
        return new i5(colorStateListP, colorStateListP2, colorStateListP3, dimensionPixelSize, zu0VarA, rect);
    }

    @Override // defpackage.fu
    public byte[] b() {
        switch (this.a) {
            case 2:
                byte[] bArr = new byte[this.b];
                if (((AtomicInteger) this.d).getAndIncrement() > 128) {
                    if (!((AtomicBoolean) this.c).getAndSet(false)) {
                        rl rlVar = (rl) this.f;
                        if (!rlVar.f.getAndSet(true)) {
                            cu cuVar = rlVar.a;
                            eu euVar = new eu(rlVar.c, rlVar.b, rlVar.e);
                            synchronized (cuVar.E) {
                                cuVar.E.add(euVar);
                            }
                        }
                    } else {
                        ((AtomicInteger) this.d).set(0);
                        ((SP800SecureRandom) this.e).reseed((byte[]) this.g);
                    }
                    break;
                }
                ((SP800SecureRandom) this.e).nextBytes(bArr);
                return bArr;
            default:
                byte[] bArr2 = new byte[this.b];
                AtomicInteger atomicInteger = (AtomicInteger) this.d;
                int andIncrement = atomicInteger.getAndIncrement();
                SP800SecureRandom sP800SecureRandom = (SP800SecureRandom) this.e;
                if (andIncrement > 1024) {
                    if (((AtomicBoolean) this.c).getAndSet(false)) {
                        atomicInteger.set(0);
                        sP800SecureRandom.reseed((byte[]) this.g);
                    } else {
                        tl tlVar = (tl) this.f;
                        if (!tlVar.e.getAndSet(true)) {
                            Thread thread = new Thread(new eu(tlVar.b, tlVar.a, tlVar.d));
                            thread.setDaemon(true);
                            thread.start();
                        }
                    }
                }
                sP800SecureRandom.nextBytes(bArr2);
                return bArr2;
        }
    }

    @Override // defpackage.fu
    public int c() {
        switch (this.a) {
        }
        return this.b * 8;
    }

    public void d() {
        View view = (View) this.c;
        Drawable background = view.getBackground();
        if (background != null) {
            if (((x01) this.e) != null) {
                if (((x01) this.g) == null) {
                    this.g = new x01();
                }
                x01 x01Var = (x01) this.g;
                x01Var.a = null;
                x01Var.d = false;
                x01Var.b = null;
                x01Var.c = false;
                WeakHashMap weakHashMap = f51.a;
                ColorStateList colorStateListG = t41.g(view);
                if (colorStateListG != null) {
                    x01Var.d = true;
                    x01Var.a = colorStateListG;
                }
                PorterDuff.Mode modeH = t41.h(view);
                if (modeH != null) {
                    x01Var.c = true;
                    x01Var.b = modeH;
                }
                if (x01Var.d || x01Var.c) {
                    f6.e(background, x01Var, view.getDrawableState());
                    return;
                }
            }
            x01 x01Var2 = (x01) this.f;
            if (x01Var2 != null) {
                f6.e(background, x01Var2, view.getDrawableState());
                return;
            }
            x01 x01Var3 = (x01) this.e;
            if (x01Var3 != null) {
                f6.e(background, x01Var3, view.getDrawableState());
            }
        }
    }

    public ColorStateList f() {
        x01 x01Var = (x01) this.f;
        if (x01Var != null) {
            return x01Var.a;
        }
        return null;
    }

    public PorterDuff.Mode g() {
        x01 x01Var = (x01) this.f;
        if (x01Var != null) {
            return x01Var.b;
        }
        return null;
    }

    public void h(AttributeSet attributeSet, int i) {
        ColorStateList colorStateListF;
        View view = (View) this.c;
        ou ouVarP = ou.P(view.getContext(), attributeSet, androidx.appcompat.R$styleable.ViewBackgroundHelper, i);
        TypedArray typedArray = (TypedArray) ouVarP.G;
        View view2 = (View) this.c;
        f51.n(view2, view2.getContext(), androidx.appcompat.R$styleable.ViewBackgroundHelper, attributeSet, (TypedArray) ouVarP.G, i);
        try {
            if (typedArray.hasValue(androidx.appcompat.R$styleable.ViewBackgroundHelper_android_background)) {
                this.b = typedArray.getResourceId(androidx.appcompat.R$styleable.ViewBackgroundHelper_android_background, -1);
                f6 f6Var = (f6) this.d;
                Context context = view.getContext();
                int i2 = this.b;
                synchronized (f6Var) {
                    colorStateListF = f6Var.a.f(context, i2);
                }
                if (colorStateListF != null) {
                    k(colorStateListF);
                }
            }
            if (typedArray.hasValue(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTint)) {
                t41.q(view, ouVarP.y(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTint));
            }
            if (typedArray.hasValue(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTintMode)) {
                t41.r(view, op.c(typedArray.getInt(androidx.appcompat.R$styleable.ViewBackgroundHelper_backgroundTintMode, -1), null));
            }
            ouVarP.S();
        } catch (Throwable th) {
            ouVarP.S();
            throw th;
        }
    }

    public void i() {
        this.b = -1;
        k(null);
        d();
    }

    public void j(int i) {
        ColorStateList colorStateListF;
        this.b = i;
        f6 f6Var = (f6) this.d;
        if (f6Var != null) {
            Context context = ((View) this.c).getContext();
            synchronized (f6Var) {
                colorStateListF = f6Var.a.f(context, i);
            }
        } else {
            colorStateListF = null;
        }
        k(colorStateListF);
        d();
    }

    public void k(ColorStateList colorStateList) {
        if (colorStateList != null) {
            if (((x01) this.e) == null) {
                this.e = new x01();
            }
            x01 x01Var = (x01) this.e;
            x01Var.a = colorStateList;
            x01Var.d = true;
        } else {
            this.e = null;
        }
        d();
    }

    public void l(ColorStateList colorStateList) {
        if (((x01) this.f) == null) {
            this.f = new x01();
        }
        x01 x01Var = (x01) this.f;
        x01Var.a = colorStateList;
        x01Var.d = true;
        d();
    }

    public void m(PorterDuff.Mode mode) {
        if (((x01) this.f) == null) {
            this.f = new x01();
        }
        x01 x01Var = (x01) this.f;
        x01Var.b = mode;
        x01Var.c = true;
        d();
    }

    public void n(TextView textView) {
        ld0 ld0Var = new ld0();
        ld0 ld0Var2 = new ld0();
        zu0 zu0Var = (zu0) this.g;
        ld0Var.setShapeAppearanceModel(zu0Var);
        ld0Var2.setShapeAppearanceModel(zu0Var);
        ld0Var.m((ColorStateList) this.e);
        ld0Var.s(this.b);
        ld0Var.r((ColorStateList) this.f);
        ColorStateList colorStateList = (ColorStateList) this.d;
        textView.setTextColor(colorStateList);
        RippleDrawable rippleDrawable = new RippleDrawable(colorStateList.withAlpha(30), ld0Var, ld0Var2);
        Rect rect = (Rect) this.c;
        InsetDrawable insetDrawable = new InsetDrawable((Drawable) rippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
        WeakHashMap weakHashMap = f51.a;
        textView.setBackground(insetDrawable);
    }

    public i5(cu cuVar, int i) {
        this.a = 2;
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        this.c = atomicBoolean;
        this.d = new AtomicInteger(0);
        byte[] bArr = new byte[8];
        ma0.S(System.currentTimeMillis(), bArr, 0);
        this.g = bArr;
        sl slVarB = wl.b();
        this.b = (i + 7) / 8;
        rl rlVar = new rl(cuVar, atomicBoolean, slVarB);
        this.f = rlVar;
        this.e = new SP800SecureRandom(rlVar, new ou(new q20(new fq0()), rlVar.b(), z91.l(jx0.b("Bouncy Castle Hybrid Entropy Source")), 13), false);
    }

    public i5(View view) {
        this.a = 0;
        this.b = -1;
        this.c = view;
        this.d = f6.a();
    }

    public i5(ColorStateList colorStateList, ColorStateList colorStateList2, ColorStateList colorStateList3, int i, zu0 zu0Var, Rect rect) {
        this.a = 1;
        la0.g(rect.left);
        la0.g(rect.top);
        la0.g(rect.right);
        la0.g(rect.bottom);
        this.c = rect;
        this.d = colorStateList2;
        this.e = colorStateList;
        this.f = colorStateList3;
        this.b = i;
        this.g = zu0Var;
    }
}
