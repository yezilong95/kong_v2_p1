package defpackage;

import android.view.ContentInfo;
import android.view.OnReceiveContentListener;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ur0 implements OnReceiveContentListener {
    public final ua0 a;

    public ur0(ua0 ua0Var) {
        this.a = ua0Var;
    }

    public final ContentInfo onReceiveContent(View view, ContentInfo contentInfo) {
        vg vgVar = new vg(new o70(contentInfo));
        vg vgVarA = ((sn0) this.a).a(view, vgVar);
        if (vgVarA == null) {
            return null;
        }
        if (vgVarA == vgVar) {
            return contentInfo;
        }
        ContentInfo contentInfoG = vgVarA.a.g();
        Objects.requireNonNull(contentInfoG);
        return c9.g(contentInfoG);
    }
}
