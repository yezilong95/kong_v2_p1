package defpackage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.graphics.Matrix;
import android.widget.ImageView;
import androidx.transition.R$id;
import androidx.transition.Transition;

/* JADX INFO: loaded from: classes.dex */
public final class fd extends AnimatorListenerAdapter implements s11 {
    public final ImageView a;
    public final Matrix b;
    public final Matrix c;
    public boolean d = true;

    public fd(ImageView imageView, Matrix matrix, Matrix matrix2) {
        this.a = imageView;
        this.b = matrix;
        this.c = matrix2;
    }

    @Override // defpackage.s11
    public final void b() {
        if (this.d) {
            int i = R$id.transition_image_transform;
            ImageView imageView = this.a;
            imageView.setTag(i, this.b);
            j31.g(imageView, this.c);
        }
    }

    @Override // defpackage.s11
    public final void e() {
        int i = R$id.transition_image_transform;
        ImageView imageView = this.a;
        Matrix matrix = (Matrix) imageView.getTag(i);
        if (matrix != null) {
            j31.g(imageView, matrix);
            imageView.setTag(R$id.transition_image_transform, null);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator, boolean z) {
        this.d = z;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationPause(Animator animator) {
        Matrix matrix = (Matrix) ((ObjectAnimator) animator).getAnimatedValue();
        int i = R$id.transition_image_transform;
        ImageView imageView = this.a;
        imageView.setTag(i, matrix);
        j31.g(imageView, this.c);
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
    public final void onAnimationResume(Animator animator) {
        int i = R$id.transition_image_transform;
        ImageView imageView = this.a;
        Matrix matrix = (Matrix) imageView.getTag(i);
        if (matrix != null) {
            j31.g(imageView, matrix);
            imageView.setTag(R$id.transition_image_transform, null);
        }
    }

    @Override // android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator, boolean z) {
        this.d = false;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationEnd(Animator animator) {
        this.d = false;
    }

    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
    public final void onAnimationStart(Animator animator) {
        this.d = false;
    }

    @Override // defpackage.s11
    public final void c(Transition transition) {
    }

    @Override // defpackage.s11
    public final void d(Transition transition) {
    }

    @Override // defpackage.s11
    public final void g(Transition transition) {
    }
}
