package androidx.work.impl.constraints.trackers;

import androidx.work.Logger;
import defpackage.oy;

/* JADX INFO: loaded from: classes.dex */
public final class BatteryNotLowTrackerKt {
    public static final float BATTERY_LOW_THRESHOLD = 0.15f;
    private static final String TAG;

    static {
        String strTagWithPrefix = Logger.tagWithPrefix("BatteryNotLowTracker");
        oy.i(strTagWithPrefix, "tagWithPrefix(\"BatteryNotLowTracker\")");
        TAG = strTagWithPrefix;
    }
}
