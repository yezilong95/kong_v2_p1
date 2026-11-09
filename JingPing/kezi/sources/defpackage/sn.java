package defpackage;

import android.text.InputFilter;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.SparseArray;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public final class sn extends rp0 {
    public final TextView e;
    public final on f;
    public boolean g = true;

    public sn(TextView textView) {
        this.e = textView;
        this.f = new on(textView);
    }

    @Override // defpackage.rp0
    public final boolean G() {
        return this.g;
    }

    @Override // defpackage.rp0
    public final void T(boolean z) {
        if (z) {
            TextView textView = this.e;
            textView.setTransformationMethod(b0(textView.getTransformationMethod()));
        }
    }

    @Override // defpackage.rp0
    public final void U(boolean z) {
        this.g = z;
        TextView textView = this.e;
        textView.setTransformationMethod(b0(textView.getTransformationMethod()));
        textView.setFilters(z(textView.getFilters()));
    }

    @Override // defpackage.rp0
    public final TransformationMethod b0(TransformationMethod transformationMethod) {
        return this.g ? ((transformationMethod instanceof wn) || (transformationMethod instanceof PasswordTransformationMethod)) ? transformationMethod : new wn(transformationMethod) : transformationMethod instanceof wn ? ((wn) transformationMethod).a : transformationMethod;
    }

    @Override // defpackage.rp0
    public final InputFilter[] z(InputFilter[] inputFilterArr) {
        if (!this.g) {
            SparseArray sparseArray = new SparseArray(1);
            for (int i = 0; i < inputFilterArr.length; i++) {
                InputFilter inputFilter = inputFilterArr[i];
                if (inputFilter instanceof on) {
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
            on onVar = this.f;
            if (i4 >= length2) {
                InputFilter[] inputFilterArr3 = new InputFilter[inputFilterArr.length + 1];
                System.arraycopy(inputFilterArr, 0, inputFilterArr3, 0, length2);
                inputFilterArr3[length2] = onVar;
                return inputFilterArr3;
            }
            if (inputFilterArr[i4] == onVar) {
                return inputFilterArr;
            }
            i4++;
        }
    }
}
