package defpackage;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class kt extends qm {
    public final TextView E;
    public final ft F;
    public boolean G = true;

    public kt(TextView textView) {
        this.E = textView;
        this.F = new ft(textView);
    }

    @Override // defpackage.qm
    public final InputFilter[] A(InputFilter[] inputFilterArr) {
        if (!this.G) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i = 0; i < inputFilterArr.length; i++) {
                InputFilter inputFilter = inputFilterArr[i];
                if (inputFilter instanceof ft) {
                    sparseArray.put(i, inputFilter);
                }
            }
            if (sparseArray.size() == 0) {
                return inputFilterArr;
            }
            int length = inputFilterArr.length;
            InputFilter[] inputFilterArr2 = new InputFilter[inputFilterArr.length - sparseArray.size()];
            int i2 = 0;
            for (int i3 = 0; i3 < length; i3++) {
                if (sparseArray.indexOfKey(i3) < 0) {
                    inputFilterArr2[i2] = inputFilterArr[i3];
                    i2++;
                }
            }
            return inputFilterArr2;
        }
        int length2 = inputFilterArr.length;
        int i4 = 0;
        while (true) {
            ft ftVar = this.F;
            if (i4 >= length2) {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = ftVar;
                return inputFilterArr3;
            }
            if (inputFilterArr[i4] == ftVar) {
                return inputFilterArr;
            }
            i4++;
        }
    }

    @Override // defpackage.qm
    public final TransformationMethod M0(TransformationMethod transformationMethod) {
        return this.G ? ((transformationMethod instanceof ot) || (transformationMethod instanceof PasswordTransformationMethod)) ? transformationMethod : new ot(transformationMethod) : transformationMethod instanceof ot ? ((ot) transformationMethod).a : transformationMethod;
    }

    @Override // defpackage.qm
    public final boolean O() {
        return this.G;
    }

    @Override // defpackage.qm
    public final void q0(boolean z) {
        if (z) {
            TextView textView = this.E;
            textView.setTransformationMethod(M0(textView.getTransformationMethod()));
        }
    }

    @Override // defpackage.qm
    public final void r0(boolean z) {
        this.G = z;
        TextView textView = this.E;
        textView.setTransformationMethod(M0(textView.getTransformationMethod()));
        textView.setFilters(A(textView.getFilters()));
    }
}
