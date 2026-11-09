package androidx.room;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class Index$Order {
    public static final Index$Order ASC;
    public static final Index$Order DESC;
    public static final /* synthetic */ Index$Order[] b;

    static {
        Index$Order index$Order = new Index$Order("ASC", 0);
        ASC = index$Order;
        Index$Order index$Order2 = new Index$Order("DESC", 1);
        DESC = index$Order2;
        b = new Index$Order[]{index$Order, index$Order2};
    }

    public static Index$Order valueOf(String str) {
        return (Index$Order) Enum.valueOf(Index$Order.class, str);
    }

    public static Index$Order[] values() {
        return (Index$Order[]) b.clone();
    }
}
