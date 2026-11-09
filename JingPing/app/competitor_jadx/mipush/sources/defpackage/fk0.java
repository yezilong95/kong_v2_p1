package defpackage;

import io.nats.client.support.JsonUtils;
import io.nats.client.support.NatsConstants;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class fk0 {
    public final Object a;
    public final Object b;

    public fk0(Object obj, Object obj2) {
        this.a = obj;
        this.b = obj2;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof fk0)) {
            return false;
        }
        fk0 fk0Var = (fk0) obj;
        return Objects.equals(fk0Var.a, this.a) && Objects.equals(fk0Var.b, this.b);
    }

    public final int hashCode() {
        Object obj = this.a;
        int iHashCode = obj == null ? 0 : obj.hashCode();
        Object obj2 = this.b;
        return (obj2 != null ? obj2.hashCode() : 0) ^ iHashCode;
    }

    public final String toString() {
        return "Pair{" + this.a + NatsConstants.SPACE + this.b + JsonUtils.CLOSE;
    }
}
