package kotlin.io;

import defpackage.aj;
import defpackage.oy;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class FileSystemException extends IOException {
    public final File b;
    public final File c;
    public final String d;

    public FileSystemException(File file, File file2, String str) {
        oy.j(file, "file");
        StringBuilder sb = new StringBuilder(file.toString());
        if (file2 != null) {
            sb.append(" -> " + file2);
        }
        if (str != null) {
            sb.append(": ".concat(str));
        }
        String string = sb.toString();
        oy.i(string, "toString(...)");
        super(string);
        this.b = file;
        this.c = file2;
        this.d = str;
    }

    public final File getFile() {
        return this.b;
    }

    public final File getOther() {
        return this.c;
    }

    public final String getReason() {
        return this.d;
    }

    public /* synthetic */ FileSystemException(File file, File file2, String str, int i, aj ajVar) {
        this(file, (i & 2) != 0 ? null : file2, (i & 4) != 0 ? null : str);
    }
}
