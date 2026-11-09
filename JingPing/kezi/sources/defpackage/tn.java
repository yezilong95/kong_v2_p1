package defpackage;

import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class tn extends rp0 {
    public final sn e;

    public tn(TextView textView) {
        this.e = new sn(textView);
    }

    @Override // defpackage.rp0
    public final boolean G() {
        return this.e.g;
    }

    @Override // defpackage.rp0
    public final void T(boolean z) {
        if (hn.j != null) {
            this.e.T(z);
        }
    }

    @Override // defpackage.rp0
    public final void U(boolean z) {
        boolean z2 = hn.j != null;
        sn snVar = this.e;
        if (z2) {
            snVar.U(z);
        } else {
            snVar.g = z;
        }
    }

    @Override // defpackage.rp0
    public final TransformationMethod b0(TransformationMethod transformationMethod) {
        return !(hn.j != null) ? transformationMethod : this.e.b0(transformationMethod);
    }

    @Override // defpackage.rp0
    public final InputFilter[] z(InputFilter[] inputFilterArr) {
        return !(hn.j != null) ? inputFilterArr : this.e.z(inputFilterArr);
    }
}
