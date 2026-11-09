package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class g40 extends InputConnectionWrapper {
    public final /* synthetic */ fb a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public g40(InputConnection inputConnection, fb fbVar) {
        super(inputConnection, false);
        this.a = fbVar;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        p50 p50Var = null;
        if (inputContentInfo != null && Build.VERSION.SDK_INT >= 25) {
            p50Var = new p50(24, new i40(inputContentInfo));
        }
        if (this.a.c(p50Var, i, bundle)) {
            return true;
        }
        return super.commitContent(inputContentInfo, i, bundle);
    }
}
