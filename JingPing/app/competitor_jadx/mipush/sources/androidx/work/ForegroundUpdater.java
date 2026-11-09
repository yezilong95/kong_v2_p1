package androidx.work;

import android.content.Context;
import defpackage.ca0;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public interface ForegroundUpdater {
    ca0 setForegroundAsync(Context context, UUID uuid, ForegroundInfo foregroundInfo);
}
