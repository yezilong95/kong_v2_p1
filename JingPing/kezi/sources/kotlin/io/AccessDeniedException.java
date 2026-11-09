package kotlin.io;

import defpackage.aj;
import defpackage.oy;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public final class AccessDeniedException extends FileSystemException {
    public /* synthetic */ AccessDeniedException(File file, File file2, String str, int i, aj ajVar) {
        this(file, (i & 2) != 0 ? null : file2, (i & 4) != 0 ? null : str);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AccessDeniedException(File file, File file2, String str) {
        super(file, file2, str);
        oy.j(file, "file");
    }
}
