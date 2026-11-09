package defpackage;

import android.view.ContentInfo;
import android.view.View;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public abstract class tr0 {
    public static String[] a(View view) {
        return view.getReceiveContentMimeTypes();
    }

    public static vg b(View view, vg vgVar) {
        ContentInfo contentInfoG = vgVar.a.g();
        Objects.requireNonNull(contentInfoG);
        ContentInfo contentInfoG2 = c9.g(contentInfoG);
        ContentInfo contentInfoPerformReceiveContent = view.performReceiveContent(contentInfoG2);
        if (contentInfoPerformReceiveContent == null) {
            return null;
        }
        return contentInfoPerformReceiveContent == contentInfoG2 ? vgVar : new vg(new o70(contentInfoPerformReceiveContent));
    }

    public static void c(View view, String[] strArr, ua0 ua0Var) {
        if (ua0Var == null) {
            view.setOnReceiveContentListener(strArr, null);
        } else {
            view.setOnReceiveContentListener(strArr, new ur0(ua0Var));
        }
    }
}
