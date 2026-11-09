package kotlinx.coroutines.channels;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class TickerMode {
    public static final TickerMode FIXED_DELAY;
    public static final TickerMode FIXED_PERIOD;
    public static final /* synthetic */ TickerMode[] b;

    static {
        TickerMode tickerMode = new TickerMode("FIXED_PERIOD", 0);
        FIXED_PERIOD = tickerMode;
        TickerMode tickerMode2 = new TickerMode("FIXED_DELAY", 1);
        FIXED_DELAY = tickerMode2;
        b = new TickerMode[]{tickerMode, tickerMode2};
    }

    public static TickerMode valueOf(String str) {
        return (TickerMode) Enum.valueOf(TickerMode.class, str);
    }

    public static TickerMode[] values() {
        return (TickerMode[]) b.clone();
    }
}
