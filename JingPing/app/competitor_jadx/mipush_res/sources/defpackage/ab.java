package defpackage;

import android.content.ClipData;
import android.view.ContentInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract /* synthetic */ class ab {
    public static /* synthetic */ ContentInfo.Builder e(ClipData clipData, int i) {
        return new ContentInfo.Builder(clipData, i);
    }

    public static /* bridge */ /* synthetic */ ContentInfo g(Object obj) {
        return (ContentInfo) obj;
    }
}
