package defpackage;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import java.util.Locale;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class tg implements sg, ug {
    public final /* synthetic */ int b = 0;
    public ClipData c;
    public int d;
    public int e;
    public Uri f;
    public Bundle g;

    public /* synthetic */ tg() {
    }

    @Override // defpackage.ug
    public ClipData b() {
        return this.c;
    }

    @Override // defpackage.sg
    public vg d() {
        return new vg(new tg(this));
    }

    @Override // defpackage.ug
    public int f() {
        return this.e;
    }

    @Override // defpackage.ug
    public ContentInfo g() {
        return null;
    }

    @Override // defpackage.sg
    public void j(Bundle bundle) {
        this.g = bundle;
    }

    @Override // defpackage.sg
    public void l(Uri uri) {
        this.f = uri;
    }

    @Override // defpackage.ug
    public int n() {
        return this.d;
    }

    @Override // defpackage.sg
    public void r(int i) {
        this.e = i;
    }

    public String toString() {
        String str;
        switch (this.b) {
            case 1:
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(this.c.getDescription());
                sb.append(", source=");
                int i = this.d;
                sb.append(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
                sb.append(", flags=");
                int i2 = this.e;
                sb.append((i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2));
                String str2 = ExtensionRequestData.EMPTY_VALUE;
                Uri uri = this.f;
                if (uri == null) {
                    str = ExtensionRequestData.EMPTY_VALUE;
                } else {
                    str = ", hasLinkUri(" + uri.toString().length() + ")";
                }
                sb.append(str);
                if (this.g != null) {
                    str2 = ", hasExtras";
                }
                return hk.k(sb, str2, "}");
            default:
                return super.toString();
        }
    }

    public tg(tg tgVar) {
        ClipData clipData = tgVar.c;
        clipData.getClass();
        this.c = clipData;
        int i = tgVar.d;
        if (i < 0) {
            Locale locale = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too low)");
        }
        if (i > 5) {
            Locale locale2 = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too high)");
        }
        this.d = i;
        int i2 = tgVar.e;
        if ((i2 & 1) == i2) {
            this.e = i2;
            this.f = tgVar.f;
            this.g = tgVar.g;
        } else {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i2) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
        }
    }
}
