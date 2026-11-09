package defpackage;

import android.content.ClipData;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContentInfo;
import io.nats.client.support.JsonUtils;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class ii implements hi, ji {
    public final /* synthetic */ int E = 0;
    public ClipData F;
    public int G;
    public int H;
    public Uri I;
    public Bundle J;

    public /* synthetic */ ii() {
    }

    @Override // defpackage.hi
    public ki a() {
        return new ki(new ii(this));
    }

    @Override // defpackage.ji
    public ClipData b() {
        return this.F;
    }

    @Override // defpackage.hi
    public void e(Bundle bundle) {
        this.J = bundle;
    }

    @Override // defpackage.ji
    public int i() {
        return this.H;
    }

    @Override // defpackage.ji
    public ContentInfo j() {
        return null;
    }

    @Override // defpackage.hi
    public void n(Uri uri) {
        this.I = uri;
    }

    @Override // defpackage.ji
    public int o() {
        return this.G;
    }

    @Override // defpackage.hi
    public void s(int i) {
        this.H = i;
    }

    public String toString() {
        String str;
        switch (this.E) {
            case 1:
                StringBuilder sb = new StringBuilder("ContentInfoCompat{clip=");
                sb.append(this.F.getDescription());
                sb.append(", source=");
                int i = this.G;
                sb.append(i != 0 ? i != 1 ? i != 2 ? i != 3 ? i != 4 ? i != 5 ? String.valueOf(i) : "SOURCE_PROCESS_TEXT" : "SOURCE_AUTOFILL" : "SOURCE_DRAG_AND_DROP" : "SOURCE_INPUT_METHOD" : "SOURCE_CLIPBOARD" : "SOURCE_APP");
                sb.append(", flags=");
                int i2 = this.H;
                sb.append((i2 & 1) != 0 ? "FLAG_CONVERT_TO_PLAIN_TEXT" : String.valueOf(i2));
                Uri uri = this.I;
                if (uri == null) {
                    str = "";
                } else {
                    str = ", hasLinkUri(" + uri.toString().length() + ")";
                }
                sb.append(str);
                return zn.o(sb, this.J != null ? ", hasExtras" : "", JsonUtils.CLOSE);
            default:
                return super.toString();
        }
    }

    public ii(ii iiVar) {
        ClipData clipData = iiVar.F;
        clipData.getClass();
        this.F = clipData;
        int i = iiVar.G;
        if (i < 0) {
            Locale locale = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too low)");
        }
        if (i > 5) {
            Locale locale2 = Locale.US;
            throw new IllegalArgumentException("source is out of range of [0, 5] (too high)");
        }
        this.G = i;
        int i2 = iiVar.H;
        if ((i2 & 1) == i2) {
            this.H = i2;
            this.I = iiVar.I;
            this.J = iiVar.J;
        } else {
            throw new IllegalArgumentException("Requested flags 0x" + Integer.toHexString(i2) + ", but only 0x" + Integer.toHexString(1) + " are allowed");
        }
    }
}
