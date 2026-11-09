package defpackage;

import android.content.res.Resources;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class yo0 {
    public final Resources a;
    public final Resources.Theme b;

    public yo0(Resources resources, Resources.Theme theme) {
        this.a = resources;
        this.b = theme;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && yo0.class == obj.getClass()) {
            yo0 yo0Var = (yo0) obj;
            if (this.a.equals(yo0Var.a) && Objects.equals(this.b, yo0Var.b)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hash(this.a, this.b);
    }
}
