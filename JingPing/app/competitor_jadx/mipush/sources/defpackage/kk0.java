package defpackage;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;

/* JADX INFO: loaded from: classes.dex */
public final class kk0 extends yt {
    public final int e;
    public EditText f;
    public final Cif g;

    public kk0(xt xtVar, int i) {
        super(xtVar);
        this.e = R$drawable.design_password_eye;
        this.g = new Cif(3, this);
        if (i != 0) {
            this.e = i;
        }
    }

    @Override // defpackage.yt
    public final void b() {
        q();
    }

    @Override // defpackage.yt
    public final int c() {
        return R$string.password_toggle_content_description;
    }

    @Override // defpackage.yt
    public final int d() {
        return this.e;
    }

    @Override // defpackage.yt
    public final View.OnClickListener f() {
        return this.g;
    }

    @Override // defpackage.yt
    public final boolean k() {
        return true;
    }

    @Override // defpackage.yt
    public final boolean l() {
        EditText editText = this.f;
        return !(editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod));
    }

    @Override // defpackage.yt
    public final void m(EditText editText) {
        this.f = editText;
        q();
    }

    @Override // defpackage.yt
    public final void r() {
        EditText editText = this.f;
        if (editText != null) {
            if (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224) {
                this.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    @Override // defpackage.yt
    public final void s() {
        EditText editText = this.f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
