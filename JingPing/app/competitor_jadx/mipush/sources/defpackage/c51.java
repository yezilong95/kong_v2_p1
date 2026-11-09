package defpackage;

import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class c51 implements OnReceiveContentListener {
    public final jj0 a;

    public c51(jj0 jj0Var) {
        this.a = jj0Var;
    }

    public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
        ki kiVar = new ki(new z8(contentInfo));
        ki kiVarA = ((g01) this.a).a(view, kiVar);
        if (kiVarA == null) {
            return null;
        }
        if (kiVarA == kiVar) {
            return contentInfo;
        }
        ContentInfo contentInfoJ = kiVarA.a.j();
        Objects.requireNonNull(contentInfoJ);
        return ab.g(contentInfoJ);
    }
}
