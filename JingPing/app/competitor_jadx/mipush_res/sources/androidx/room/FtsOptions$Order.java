package androidx.room;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class FtsOptions$Order {
    public static final FtsOptions$Order ASC;
    public static final FtsOptions$Order DESC;
    public static final /* synthetic */ FtsOptions$Order[] E;

    static {
        FtsOptions$Order ftsOptions$Order = new FtsOptions$Order("ASC", 0);
        ASC = ftsOptions$Order;
        FtsOptions$Order ftsOptions$Order2 = new FtsOptions$Order("DESC", 1);
        DESC = ftsOptions$Order2;
        E = new FtsOptions$Order[]{ftsOptions$Order, ftsOptions$Order2};
    }

    public static FtsOptions$Order valueOf(String str) {
        return (FtsOptions$Order) Enum.valueOf(FtsOptions$Order.class, str);
    }

    public static FtsOptions$Order[] values() {
        return (FtsOptions$Order[]) E.clone();
    }
}
