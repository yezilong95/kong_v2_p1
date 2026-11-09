package kotlinx.coroutines.channels;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class TickerMode {
    public static final /* synthetic */ TickerMode[] E;
    public static final /* synthetic */ hu F;
    public static final TickerMode FIXED_DELAY;
    public static final TickerMode FIXED_PERIOD;

    static {
        TickerMode tickerMode = new TickerMode("FIXED_PERIOD", 0);
        FIXED_PERIOD = tickerMode;
        TickerMode tickerMode2 = new TickerMode("FIXED_DELAY", 1);
        FIXED_DELAY = tickerMode2;
        TickerMode[] tickerModeArr = {tickerMode, tickerMode2};
        E = tickerModeArr;
        F = new hu(tickerModeArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static TickerMode valueOf(String str) {
        return (TickerMode) Enum.valueOf(TickerMode.class, str);
    }

    public static TickerMode[] values() {
        return (TickerMode[]) E.clone();
    }
}
