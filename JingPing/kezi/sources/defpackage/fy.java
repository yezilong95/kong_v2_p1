package defpackage;

import android.content.ClipDescription;
import android.net.Uri;
import android.view.inputmethod.InputContentInfo;

/* JADX INFO: loaded from: classes.dex */
public final class fy implements gy {
    public final InputContentInfo b;

    public fy(Object obj) {
        this.b = (InputContentInfo) obj;
    }

    @Override // defpackage.gy
    public final void a() {
        this.b.requestPermission();
    }

    @Override // defpackage.gy
    public final Uri b() {
        return this.b.getLinkUri();
    }

    @Override // defpackage.gy
    public final ClipDescription d() {
        return this.b.getDescription();
    }

    @Override // defpackage.gy
    public final Object e() {
        return this.b;
    }

    @Override // defpackage.gy
    public final Uri i() {
        return this.b.getContentUri();
    }

    public fy(Uri uri, ClipDescription clipDescription, Uri uri2) {
        this.b = new InputContentInfo(uri, clipDescription, uri2);
    }
}
