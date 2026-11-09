package kotlin.io;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class FileWalkDirection {
    public static final FileWalkDirection BOTTOM_UP;
    public static final /* synthetic */ FileWalkDirection[] E;
    public static final /* synthetic */ hu F;
    public static final FileWalkDirection TOP_DOWN;

    static {
        FileWalkDirection fileWalkDirection = new FileWalkDirection("TOP_DOWN", 0);
        TOP_DOWN = fileWalkDirection;
        FileWalkDirection fileWalkDirection2 = new FileWalkDirection("BOTTOM_UP", 1);
        BOTTOM_UP = fileWalkDirection2;
        FileWalkDirection[] fileWalkDirectionArr = {fileWalkDirection, fileWalkDirection2};
        E = fileWalkDirectionArr;
        F = new hu(fileWalkDirectionArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static FileWalkDirection valueOf(String str) {
        return (FileWalkDirection) Enum.valueOf(FileWalkDirection.class, str);
    }

    public static FileWalkDirection[] values() {
        return (FileWalkDirection[]) E.clone();
    }
}
