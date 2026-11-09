package androidx.room;

import android.app.ActivityManager;
import android.content.Context;
import defpackage.oy;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class RoomDatabase$JournalMode {
    public static final RoomDatabase$JournalMode AUTOMATIC;
    public static final RoomDatabase$JournalMode TRUNCATE;
    public static final RoomDatabase$JournalMode WRITE_AHEAD_LOGGING;
    public static final /* synthetic */ RoomDatabase$JournalMode[] b;

    static {
        RoomDatabase$JournalMode roomDatabase$JournalMode = new RoomDatabase$JournalMode("AUTOMATIC", 0);
        AUTOMATIC = roomDatabase$JournalMode;
        RoomDatabase$JournalMode roomDatabase$JournalMode2 = new RoomDatabase$JournalMode("TRUNCATE", 1);
        TRUNCATE = roomDatabase$JournalMode2;
        RoomDatabase$JournalMode roomDatabase$JournalMode3 = new RoomDatabase$JournalMode("WRITE_AHEAD_LOGGING", 2);
        WRITE_AHEAD_LOGGING = roomDatabase$JournalMode3;
        b = new RoomDatabase$JournalMode[]{roomDatabase$JournalMode, roomDatabase$JournalMode2, roomDatabase$JournalMode3};
    }

    public static RoomDatabase$JournalMode valueOf(String str) {
        return (RoomDatabase$JournalMode) Enum.valueOf(RoomDatabase$JournalMode.class, str);
    }

    public static RoomDatabase$JournalMode[] values() {
        return (RoomDatabase$JournalMode[]) b.clone();
    }

    public final RoomDatabase$JournalMode resolve$room_runtime_release(Context context) {
        oy.j(context, "context");
        if (this != AUTOMATIC) {
            return this;
        }
        Object systemService = context.getSystemService("activity");
        oy.h(systemService, "null cannot be cast to non-null type android.app.ActivityManager");
        return !((ActivityManager) systemService).isLowRamDevice() ? WRITE_AHEAD_LOGGING : TRUNCATE;
    }
}
