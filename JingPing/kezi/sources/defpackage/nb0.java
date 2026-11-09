package defpackage;

import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.R$drawable;
import com.google.android.material.R$string;

/* JADX INFO: loaded from: classes.dex */
public final class nb0 extends go {
    public final int e;
    public EditText f;
    public final nd g;

    public nb0(fo foVar, int i) {
        super(foVar);
        this.e = R$drawable.design_password_eye;
        this.g = new nd(3, this);
        if (i != 0) {
            this.e = i;
        }
    }

    @Override // defpackage.go
    public final void b() {
        q();
    }

    @Override // defpackage.go
    public final int c() {
        return R$string.password_toggle_content_description;
    }

    @Override // defpackage.go
    public final int d() {
        return this.e;
    }

    @Override // defpackage.go
    public final View.OnClickListener f() {
        return this.g;
    }

    @Override // defpackage.go
    public final boolean k() {
        return true;
    }

    @Override // defpackage.go
    public final boolean l() {
        EditText editText = this.f;
        return !(editText != null && (editText.getTransformationMethod() instanceof PasswordTransformationMethod));
    }

    @Override // defpackage.go
    public final void m(EditText editText) {
        this.f = editText;
        q();
    }

    @Override // defpackage.go
    public final void r() {
        EditText editText = this.f;
        if (editText != null) {
            if (editText.getInputType() == 16 || editText.getInputType() == 128 || editText.getInputType() == 144 || editText.getInputType() == 224) {
                this.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        }
    }

    @Override // defpackage.go
    public final void s() {
        EditText editText = this.f;
        if (editText != null) {
            editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
