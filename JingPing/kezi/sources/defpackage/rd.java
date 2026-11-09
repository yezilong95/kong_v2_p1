package defpackage;

import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R$attr;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;
import com.google.android.material.internal.CheckableImageButton;

/* JADX INFO: loaded from: classes.dex */
public final class rd extends go {
    public final int e;
    public final int f;
    public final TimeInterpolator g;
    public final TimeInterpolator h;
    public EditText i;
    public final nd j;
    public final od k;
    public AnimatorSet l;
    public ValueAnimator m;

    public rd(fo foVar) {
        super(foVar);
        this.j = new nd(0, this);
        this.k = new od(0, this);
        this.e = k9.E(foVar.getContext(), R$attr.motionDurationShort3, 100);
        this.f = k9.E(foVar.getContext(), R$attr.motionDurationShort3, 150);
        this.g = k9.F(foVar.getContext(), R$attr.motionEasingLinearInterpolator, t3.a);
        this.h = k9.F(foVar.getContext(), R$attr.motionEasingEmphasizedInterpolator, t3.d);
    }

    @Override // defpackage.go
    public final void a() {
        if (this.b.q != null) {
            return;
        }
        t(u());
    }

    @Override // defpackage.go
    public final int c() {
        return R$string.clear_text_end_icon_content_description;
    }

    @Override // defpackage.go
    public final int d() {
        return R$drawable.mtrl_ic_cancel;
    }

    @Override // defpackage.go
    public final View.OnFocusChangeListener e() {
        return this.k;
    }

    @Override // defpackage.go
    public final View.OnClickListener f() {
        return this.j;
    }

    @Override // defpackage.go
    public final View.OnFocusChangeListener g() {
        return this.k;
    }

    @Override // defpackage.go
    public final void m(EditText editText) {
        this.i = editText;
        this.a.setEndIconVisible(u());
    }

    @Override // defpackage.go
    public final void p(boolean z) {
        if (this.b.q == null) {
            return;
        }
        t(z);
    }

    @Override // defpackage.go
    public final void r() {
        final int i = 1;
        final int i2 = 0;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(this.h);
        valueAnimatorOfFloat.setDuration(this.f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: pd
            public final /* synthetic */ rd b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i) {
                    case 0:
                        rd rdVar = this.b;
                        rdVar.getClass();
                        rdVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        rd rdVar2 = this.b;
                        rdVar2.getClass();
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = rdVar2.d;
                        checkableImageButton.setScaleX(fFloatValue);
                        checkableImageButton.setScaleY(fFloatValue);
                        break;
                }
            }
        });
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        TimeInterpolator timeInterpolator = this.g;
        valueAnimatorOfFloat2.setInterpolator(timeInterpolator);
        int i3 = this.e;
        valueAnimatorOfFloat2.setDuration(i3);
        valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: pd
            public final /* synthetic */ rd b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i2) {
                    case 0:
                        rd rdVar = this.b;
                        rdVar.getClass();
                        rdVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        rd rdVar2 = this.b;
                        rdVar2.getClass();
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = rdVar2.d;
                        checkableImageButton.setScaleX(fFloatValue);
                        checkableImageButton.setScaleY(fFloatValue);
                        break;
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.l = animatorSet;
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        this.l.addListener(new qd(this, i2));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat3.setInterpolator(timeInterpolator);
        valueAnimatorOfFloat3.setDuration(i3);
        valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: pd
            public final /* synthetic */ rd b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i2) {
                    case 0:
                        rd rdVar = this.b;
                        rdVar.getClass();
                        rdVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        rd rdVar2 = this.b;
                        rdVar2.getClass();
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = rdVar2.d;
                        checkableImageButton.setScaleX(fFloatValue);
                        checkableImageButton.setScaleY(fFloatValue);
                        break;
                }
            }
        });
        this.m = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.addListener(new qd(this, i));
    }

    @Override // defpackage.go
    public final void s() {
        EditText editText = this.i;
        if (editText != null) {
            editText.post(new z1(1, this));
        }
    }

    public final void t(boolean z) {
        boolean z2 = this.b.d() == z;
        if (z && !this.l.isRunning()) {
            this.m.cancel();
            this.l.start();
            if (z2) {
                this.l.end();
                return;
            }
            return;
        }
        if (z) {
            return;
        }
        this.l.cancel();
        this.m.start();
        if (z2) {
            this.m.end();
        }
    }

    public final boolean u() {
        EditText editText = this.i;
        if (editText != null) {
            return (editText.hasFocus() || this.d.hasFocus()) && this.i.getText().length() > 0;
        }
        return false;
    }
}
