package defpackage;

import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.datepicker.m;
import com.mamimobi.AccessService;

/* JADX INFO: renamed from: if, reason: invalid class name */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class Cif implements View.OnClickListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ Cif(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.E) {
            case 0:
                mf mfVar = (mf) this.F;
                EditText editText = mfVar.i;
                if (editText != null) {
                    Editable text = editText.getText();
                    if (text != null) {
                        text.clear();
                    }
                    mfVar.q();
                    break;
                }
                break;
            case 1:
                ((jq) this.F).u();
                break;
            case 2:
                m mVar = (m) this.F;
                mVar.k1.setEnabled(mVar.E().f());
                mVar.i1.toggle();
                mVar.X0 = mVar.X0 == 1 ? 0 : 1;
                mVar.I(mVar.i1);
                mVar.H();
                break;
            case 3:
                kk0 kk0Var = (kk0) this.F;
                EditText editText2 = kk0Var.f;
                if (editText2 != null) {
                    int selectionEnd = editText2.getSelectionEnd();
                    EditText editText3 = kk0Var.f;
                    if (editText3 == null || !(editText3.getTransformationMethod() instanceof PasswordTransformationMethod)) {
                        kk0Var.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        kk0Var.f.setTransformationMethod(null);
                    }
                    if (selectionEnd >= 0) {
                        kk0Var.f.setSelection(selectionEnd);
                    }
                    kk0Var.q();
                    break;
                }
                break;
            default:
                ((AccessService) this.F).B();
                s90.A();
                break;
        }
    }
}
