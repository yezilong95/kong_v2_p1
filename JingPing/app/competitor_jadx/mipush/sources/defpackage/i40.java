package defpackage;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class i40 implements j40 {
    public final InputContentInfo E;

    public i40(Object obj) {
        this.E = (InputContentInfo) obj;
    }

    @Override // defpackage.j40
    public final ClipDescription a() {
        return this.E.getDescription();
    }

    @Override // defpackage.j40
    public final Object b() {
        return this.E;
    }

    @Override // defpackage.j40
    public final Uri e() {
        return this.E.getContentUri();
    }

    @Override // defpackage.j40
    public final void f() {
        this.E.requestPermission();
    }

    @Override // defpackage.j40
    public final Uri h() {
        return this.E.getLinkUri();
    }

    public i40(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.E = new InputContentInfo(uri, clipDescription, uri2);
    }
}
