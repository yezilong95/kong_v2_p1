package kotlin.io;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class FileWalkDirection {
    public static final FileWalkDirection BOTTOM_UP;
    public static final FileWalkDirection TOP_DOWN;
    public static final /* synthetic */ FileWalkDirection[] b;
    public static final /* synthetic */ ko c;

    static {
        FileWalkDirection fileWalkDirection = new FileWalkDirection("TOP_DOWN", 0);
        TOP_DOWN = fileWalkDirection;
        FileWalkDirection fileWalkDirection2 = new FileWalkDirection("BOTTOM_UP", 1);
        BOTTOM_UP = fileWalkDirection2;
        FileWalkDirection[] fileWalkDirectionArr = {fileWalkDirection, fileWalkDirection2};
        b = fileWalkDirectionArr;
        c = new ko(fileWalkDirectionArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static FileWalkDirection valueOf(String str) {
        return (FileWalkDirection) Enum.valueOf(FileWalkDirection.class, str);
    }

    public static FileWalkDirection[] values() {
        return (FileWalkDirection[]) b.clone();
    }
}
