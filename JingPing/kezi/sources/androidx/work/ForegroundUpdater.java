package androidx.work;

import android.content.Context;
import defpackage.x20;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public interface ForegroundUpdater {
    x20 setForegroundAsync(Context context, UUID uuid, ForegroundInfo foregroundInfo);
}
