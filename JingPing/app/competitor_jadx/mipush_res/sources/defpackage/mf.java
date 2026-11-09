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
public final class mf extends yt {
    public final int e;
    public final int f;
    public final TimeInterpolator g;
    public final TimeInterpolator h;
    public EditText i;
    public final Cif j;
    public final jf k;
    public AnimatorSet l;
    public ValueAnimator m;

    public mf(xt xtVar) {
        super(xtVar);
        this.j = new Cif(0, this);
        this.k = new jf(0, this);
        this.e = s90.U(xtVar.getContext(), R$attr.motionDurationShort3, 100);
        this.f = s90.U(xtVar.getContext(), R$attr.motionDurationShort3, 150);
        this.g = s90.V(xtVar.getContext(), R$attr.motionEasingLinearInterpolator, y4.a);
        this.h = s90.V(xtVar.getContext(), R$attr.motionEasingEmphasizedInterpolator, y4.d);
    }

    @Override // defpackage.yt
    public final void a() {
        if (this.b.T != null) {
            return;
        }
        t(u());
    }

    @Override // defpackage.yt
    public final int c() {
        return R$string.clear_text_end_icon_content_description;
    }

    @Override // defpackage.yt
    public final int d() {
        return R$drawable.mtrl_ic_cancel;
    }

    @Override // defpackage.yt
    public final View.OnFocusChangeListener e() {
        return this.k;
    }

    @Override // defpackage.yt
    public final View.OnClickListener f() {
        return this.j;
    }

    @Override // defpackage.yt
    public final View.OnFocusChangeListener g() {
        return this.k;
    }

    @Override // defpackage.yt
    public final void m(EditText editText) {
        this.i = editText;
        this.a.setEndIconVisible(u());
    }

    @Override // defpackage.yt
    public final void p(boolean z) {
        if (this.b.T == null) {
            return;
        }
        t(z);
    }

    @Override // defpackage.yt
    public final void r() {
        final int i = 1;
        final int i2 = 0;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.8f, 1.0f);
        valueAnimatorOfFloat.setInterpolator(this.h);
        valueAnimatorOfFloat.setDuration(this.f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: kf
            public final /* synthetic */ mf b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i) {
                    case 0:
                        mf mfVar = this.b;
                        mfVar.getClass();
                        mfVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        mf mfVar2 = this.b;
                        mfVar2.getClass();
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = mfVar2.d;
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
        valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: kf
            public final /* synthetic */ mf b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i2) {
                    case 0:
                        mf mfVar = this.b;
                        mfVar.getClass();
                        mfVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        mf mfVar2 = this.b;
                        mfVar2.getClass();
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = mfVar2.d;
                        checkableImageButton.setScaleX(fFloatValue);
                        checkableImageButton.setScaleY(fFloatValue);
                        break;
                }
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        this.l = animatorSet;
        animatorSet.playTogether(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        this.l.addListener(new lf(this, i2));
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat3.setInterpolator(timeInterpolator);
        valueAnimatorOfFloat3.setDuration(i3);
        valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(this) { // from class: kf
            public final /* synthetic */ mf b;

            {
                this.b = this;
            }

            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                switch (i2) {
                    case 0:
                        mf mfVar = this.b;
                        mfVar.getClass();
                        mfVar.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
                        break;
                    default:
                        mf mfVar2 = this.b;
                        mfVar2.getClass();
                        float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                        CheckableImageButton checkableImageButton = mfVar2.d;
                        checkableImageButton.setScaleX(fFloatValue);
                        checkableImageButton.setScaleY(fFloatValue);
                        break;
                }
            }
        });
        this.m = valueAnimatorOfFloat3;
        valueAnimatorOfFloat3.addListener(new lf(this, i));
    }

    @Override // defpackage.yt
    public final void s() {
        EditText editText = this.i;
        if (editText != null) {
            editText.post(new e(7, this));
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
