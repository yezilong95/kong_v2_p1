package defpackage;

import android.view.inputmethod.EditorInfo;

/* JADX INFO: loaded from: classes.dex */
public abstract class ks {
    public static void a(EditorInfo editorInfo, CharSequence charSequence) {
        editorInfo.setInitialSurroundingSubText(charSequence, 0);
    }
}
