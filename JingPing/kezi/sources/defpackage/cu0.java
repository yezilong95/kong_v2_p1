package defpackage;

import android.view.ViewGroup;
import android.view.WindowId;

/* JADX INFO: loaded from: classes.dex */
public final class cu0 {
    public final WindowId a;

    public cu0(ViewGroup viewGroup) {
        this.a = viewGroup.getWindowId();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof cu0) && ((cu0) obj).a.equals(this.a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }
}
