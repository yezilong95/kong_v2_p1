package defpackage;

import android.view.ContentInfo;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class b51 {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static ki b(View view, ki kiVar) {
        ContentInfo contentInfoJ = kiVar.a.j();
        Objects.requireNonNull(contentInfoJ);
        ContentInfo contentInfoG = ab.g(contentInfoJ);
        ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfoG);
        if (contentInfoPerformReceiveContent == null) {
            return null;
        }
        return contentInfoPerformReceiveContent == contentInfoG ? kiVar : new ki(new z8(contentInfoPerformReceiveContent));
    }

    public static void c(View view, String[] strArr, jj0 jj0Var) {
        if (jj0Var == null) {
            view.setOnReceiveContentListener(strArr, null);
        } else {
            view.setOnReceiveContentListener(strArr, new c51(jj0Var));
        }
    }
}
