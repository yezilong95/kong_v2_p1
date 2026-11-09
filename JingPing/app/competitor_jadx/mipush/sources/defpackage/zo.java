package defpackage;

import android.view.DisplayCutout;
import io.nats.client.support.JsonUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class zo {
    public final DisplayCutout a;

    public zo(DisplayCutout displayCutout) {
        this.a = displayCutout;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || zo.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((zo) obj).a);
    }

    public final int hashCode() {
        return this.a.hashCode();
    }

    public final String toString() {
        return "DisplayCutoutCompat{" + this.a + JsonUtils.CLOSE;
    }
}
