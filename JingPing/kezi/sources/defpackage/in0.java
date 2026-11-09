package defpackage;

import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;

/* JADX INFO: loaded from: classes.dex */
public final class in0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ TextInputLayout c;

    public /* synthetic */ in0(TextInputLayout textInputLayout, int i) {
        this.b = i;
        this.c = textInputLayout;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                CheckableImageButton checkableImageButton = this.c.d.h;
                checkableImageButton.performClick();
                checkableImageButton.jumpDrawablesToCurrentState();
                break;
            default:
                this.c.e.requestLayout();
                break;
        }
    }
}
