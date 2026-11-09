package defpackage;

import android.view.DisplayCutout;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class cl {
    public final DisplayCutout a;

    public cl(DisplayCutout displayCutout) {
        this.a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || cl.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((cl) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.a + "}";
    }
}
