package kotlinx.coroutines.flow;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class SharingCommand {
    public static final /* synthetic */ SharingCommand[] E;
    public static final /* synthetic */ hu F;
    public static final SharingCommand START;
    public static final SharingCommand STOP;
    public static final SharingCommand STOP_AND_RESET_REPLAY_CACHE;

    static {
        SharingCommand sharingCommand = new SharingCommand("START", 0);
        START = sharingCommand;
        SharingCommand sharingCommand2 = new SharingCommand("STOP", 1);
        STOP = sharingCommand2;
        SharingCommand sharingCommand3 = new SharingCommand("STOP_AND_RESET_REPLAY_CACHE", 2);
        STOP_AND_RESET_REPLAY_CACHE = sharingCommand3;
        SharingCommand[] sharingCommandArr = {sharingCommand, sharingCommand2, sharingCommand3};
        E = sharingCommandArr;
        F = new hu(sharingCommandArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static SharingCommand valueOf(String str) {
        return (SharingCommand) Enum.valueOf(SharingCommand.class, str);
    }

    public static SharingCommand[] values() {
        return (SharingCommand[]) E.clone();
    }
}
