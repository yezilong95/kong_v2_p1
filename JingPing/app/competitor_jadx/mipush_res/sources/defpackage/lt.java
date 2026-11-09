package defpackage;

import android.text.InputFilter;
import android.text.method.TransformationMethod;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class lt extends qm {
    public final kt E;

    public lt(TextView textView) {
        this.E = new kt(textView);
    }

    @Override // defpackage.qm
    public final InputFilter[] A(InputFilter[] inputFilterArr) {
        return !(zs.j != null) ? inputFilterArr : this.E.A(inputFilterArr);
    }

    @Override // defpackage.qm
    public final TransformationMethod M0(TransformationMethod transformationMethod) {
        return !(zs.j != null) ? transformationMethod : this.E.M0(transformationMethod);
    }

    @Override // defpackage.qm
    public final boolean O() {
        return this.E.G;
    }

    @Override // defpackage.qm
    public final void q0(boolean z) {
        if (zs.j != null) {
            this.E.q0(z);
        }
    }

    @Override // defpackage.qm
    public final void r0(boolean z) {
        boolean z2 = zs.j != null;
        kt ktVar = this.E;
        if (z2) {
            ktVar.r0(z);
        } else {
            ktVar.G = z;
        }
    }
}
