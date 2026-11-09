package kotlin.io;

import defpackage.t40;
import defpackage.tm;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class FileSystemException extends IOException {
    public final File E;
    public final File F;
    public final String G;

    public FileSystemException(File file, File file2, String str) {
        t40.n(file, "file");
        StringBuilder sb = new StringBuilder(file.toString());
        if (file2 != null) {
            sb.append(" -> " + file2);
        }
        if (str != null) {
            sb.append(": ".concat(str));
        }
        String string = sb.toString();
        t40.m(string, "toString(...)");
        super(string);
        this.E = file;
        this.F = file2;
        this.G = str;
    }

    public final File getFile() {
        return this.E;
    }

    public final File getOther() {
        return this.F;
    }

    public final String getReason() {
        return this.G;
    }

    public /* synthetic */ FileSystemException(File file, File file2, String str, int i, tm tmVar) {
        this(file, (i & 2) != 0 ? null : file2, (i & 4) != 0 ? null : str);
    }
}
