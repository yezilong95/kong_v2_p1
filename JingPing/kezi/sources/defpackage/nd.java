package defpackage;

import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import com.google.android.material.datepicker.m;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class nd implements View.OnClickListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ nd(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        switch (this.b) {
            case 0:
                rd rdVar = (rd) this.c;
                EditText editText = rdVar.i;
                if (editText != null) {
                    Editable text = editText.getText();
                    if (text != null) {
                        text.clear();
                    }
                    rdVar.q();
                    break;
                }
                break;
            case 1:
                ((gm) this.c).u();
                break;
            case 2:
                m mVar = (m) this.c;
                mVar.D0.setEnabled(mVar.E().g());
                mVar.B0.toggle();
                mVar.u0 = mVar.u0 == 1 ? 0 : 1;
                mVar.I(mVar.B0);
                mVar.H();
                break;
            default:
                nb0 nb0Var = (nb0) this.c;
                EditText editText2 = nb0Var.f;
                if (editText2 != null) {
                    int selectionEnd = editText2.getSelectionEnd();
                    EditText editText3 = nb0Var.f;
                    if (editText3 == null || !(editText3.getTransformationMethod() instanceof PasswordTransformationMethod)) {
                        nb0Var.f.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    } else {
                        nb0Var.f.setTransformationMethod(null);
                    }
                    if (selectionEnd >= 0) {
                        nb0Var.f.setSelection(selectionEnd);
                    }
                    nb0Var.q();
                    break;
                }
                break;
        }
    }
}
