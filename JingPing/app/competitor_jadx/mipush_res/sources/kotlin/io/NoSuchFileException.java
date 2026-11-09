package kotlin.io;

import defpackage.t40;
import defpackage.tm;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class NoSuchFileException extends FileSystemException {
    public /* synthetic */ NoSuchFileException(File file, File file2, String str, int i, tm tmVar) {
        this(file, (i & 2) != 0 ? null : file2, (i & 4) != 0 ? null : str);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NoSuchFileException(File file, File file2, String str) {
        super(file, file2, str);
        t40.n(file, "file");
    }
}
