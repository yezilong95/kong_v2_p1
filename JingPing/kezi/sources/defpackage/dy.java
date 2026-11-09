package defpackage;

import android.os.Build;
import android.os.Bundle;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class dy extends InputConnectionWrapper {
    public final /* synthetic */ h9 a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public dy(InputConnection inputConnection, h9 h9Var) {
        super(inputConnection, false);
        this.a = h9Var;
    }

    @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
    public final boolean commitContent(InputContentInfo inputContentInfo, int i, Bundle bundle) {
        o70 o70Var = null;
        if (inputContentInfo != null && Build.VERSION.SDK_INT >= 25) {
            o70Var = new o70(24, new fy(inputContentInfo));
        }
        if (this.a.d(o70Var, i, bundle)) {
            return true;
        }
        return super.commitContent(inputContentInfo, i, bundle);
    }
}
