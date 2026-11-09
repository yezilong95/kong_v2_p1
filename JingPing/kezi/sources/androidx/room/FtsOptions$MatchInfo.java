package androidx.room;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class FtsOptions$MatchInfo {
    public static final FtsOptions$MatchInfo FTS3;
    public static final FtsOptions$MatchInfo FTS4;
    public static final /* synthetic */ FtsOptions$MatchInfo[] b;

    static {
        FtsOptions$MatchInfo ftsOptions$MatchInfo = new FtsOptions$MatchInfo("FTS3", 0);
        FTS3 = ftsOptions$MatchInfo;
        FtsOptions$MatchInfo ftsOptions$MatchInfo2 = new FtsOptions$MatchInfo("FTS4", 1);
        FTS4 = ftsOptions$MatchInfo2;
        b = new FtsOptions$MatchInfo[]{ftsOptions$MatchInfo, ftsOptions$MatchInfo2};
    }

    public static FtsOptions$MatchInfo valueOf(String str) {
        return (FtsOptions$MatchInfo) Enum.valueOf(FtsOptions$MatchInfo.class, str);
    }

    public static FtsOptions$MatchInfo[] values() {
        return (FtsOptions$MatchInfo[]) b.clone();
    }
}
