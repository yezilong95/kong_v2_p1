package kotlinx.coroutines.channels;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class BufferOverflow {
    public static final BufferOverflow DROP_LATEST;
    public static final BufferOverflow DROP_OLDEST;
    public static final /* synthetic */ BufferOverflow[] E;
    public static final /* synthetic */ hu F;
    public static final BufferOverflow SUSPEND;

    static {
        BufferOverflow bufferOverflow = new BufferOverflow("SUSPEND", 0);
        SUSPEND = bufferOverflow;
        BufferOverflow bufferOverflow2 = new BufferOverflow("DROP_OLDEST", 1);
        DROP_OLDEST = bufferOverflow2;
        BufferOverflow bufferOverflow3 = new BufferOverflow("DROP_LATEST", 2);
        DROP_LATEST = bufferOverflow3;
        BufferOverflow[] bufferOverflowArr = {bufferOverflow, bufferOverflow2, bufferOverflow3};
        E = bufferOverflowArr;
        F = new hu(bufferOverflowArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static BufferOverflow valueOf(String str) {
        return (BufferOverflow) Enum.valueOf(BufferOverflow.class, str);
    }

    public static BufferOverflow[] values() {
        return (BufferOverflow[]) E.clone();
    }
}
