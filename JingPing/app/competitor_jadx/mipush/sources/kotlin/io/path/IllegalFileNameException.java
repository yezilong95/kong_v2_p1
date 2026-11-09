package kotlin.io.path;

import defpackage.t40;
import java.nio.file.FileSystemException;
import java.nio.file.Path;

/* JADX INFO: loaded from: classes.dex */
public final class IllegalFileNameException extends FileSystemException {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public IllegalFileNameException(Path path, Path path2, String str) {
        super(path.toString(), path2 != null ? path2.toString() : null, str);
        t40.n(path, "file");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public IllegalFileNameException(Path path) {
        this(path, null, null);
        t40.n(path, "file");
    }
}
