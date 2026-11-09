package defpackage;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;
import android.widget.TextView;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.AppCompatCheckedTextView;

/* JADX INFO: loaded from: classes.dex */
public final class g4 {
    public ColorStateList a = null;
    public PorterDuff.Mode b = null;
    public boolean c = false;
    public boolean d = false;
    public boolean e;
    public final TextView f;

    public /* synthetic */ g4(TextView textView) {
        this.f = textView;
    }

    public void a() {
        CompoundButton compoundButton = (CompoundButton) this.f;
        Drawable drawableA = ff.a(compoundButton);
        if (drawableA != null) {
            if (this.c || this.d) {
                Drawable drawableMutate = drawableA.mutate();
                if (this.c) {
                    hl.h(drawableMutate, this.a);
                }
                if (this.d) {
                    hl.i(drawableMutate, this.b);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(compoundButton.getDrawableState());
                }
                compoundButton.setButtonDrawable(drawableMutate);
            }
        }
    }

    public void b() {
        AppCompatCheckedTextView appCompatCheckedTextView = (AppCompatCheckedTextView) this.f;
        Drawable checkMarkDrawable = appCompatCheckedTextView.getCheckMarkDrawable();
        if (checkMarkDrawable != null) {
            if (this.c || this.d) {
                Drawable drawableMutate = checkMarkDrawable.mutate();
                if (this.c) {
                    hl.h(drawableMutate, this.a);
                }
                if (this.d) {
                    hl.i(drawableMutate, this.b);
                }
                if (drawableMutate.isStateful()) {
                    drawableMutate.setState(appCompatCheckedTextView.getDrawableState());
                }
                appCompatCheckedTextView.setCheckMarkDrawable(drawableMutate);
            }
        }
    }

    public void c(AttributeSet attributeSet, int i) {
        int resourceId;
        int resourceId2;
        CompoundButton compoundButton = (CompoundButton) this.f;
        po poVarN = po.N(compoundButton.getContext(), attributeSet, R$styleable.CompoundButton, i);
        TypedArray typedArray = (TypedArray) poVarN.d;
        xr0.o(compoundButton, compoundButton.getContext(), R$styleable.CompoundButton, attributeSet, (TypedArray) poVarN.d, i);
        try {
            if (typedArray.hasValue(R$styleable.CompoundButton_buttonCompat) && (resourceId2 = typedArray.getResourceId(R$styleable.CompoundButton_buttonCompat, 0)) != 0) {
                try {
                    compoundButton.setButtonDrawable(rp0.y(compoundButton.getContext(), resourceId2));
                } catch (Resources.NotFoundException unused) {
                    if (typedArray.hasValue(R$styleable.CompoundButton_android_button)) {
                        compoundButton.setButtonDrawable(rp0.y(compoundButton.getContext(), resourceId));
                    }
                }
            } else if (typedArray.hasValue(R$styleable.CompoundButton_android_button) && (resourceId = typedArray.getResourceId(R$styleable.CompoundButton_android_button, 0)) != 0) {
                compoundButton.setButtonDrawable(rp0.y(compoundButton.getContext(), resourceId));
            }
            if (typedArray.hasValue(R$styleable.CompoundButton_buttonTint)) {
                ef.c(compoundButton, poVarN.w(R$styleable.CompoundButton_buttonTint));
            }
            if (typedArray.hasValue(R$styleable.CompoundButton_buttonTintMode)) {
                ef.d(compoundButton, ll.c(typedArray.getInt(R$styleable.CompoundButton_buttonTintMode, -1), null));
            }
            poVarN.P();
        } catch (Throwable th) {
            poVarN.P();
            throw th;
        }
    }
}
