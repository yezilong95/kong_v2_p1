package defpackage;

import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/* JADX INFO: loaded from: classes.dex */
public final class k20 extends e21 {
    public final /* synthetic */ int a;
    public final /* synthetic */ e21 b;

    public /* synthetic */ k20(e21 e21Var, int i) {
        this.a = i;
        this.b = e21Var;
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) {
        switch (this.a) {
            case 0:
                return new AtomicLong(((Number) this.b.a(a60Var)).longValue());
            case 1:
                ArrayList arrayList = new ArrayList();
                a60Var.j();
                while (a60Var.B()) {
                    arrayList.add(Long.valueOf(((Number) this.b.a(a60Var)).longValue()));
                }
                a60Var.y();
                int size = arrayList.size();
                AtomicLongArray atomicLongArray = new AtomicLongArray(size);
                for (int i = 0; i < size; i++) {
                    atomicLongArray.set(i, ((Long) arrayList.get(i)).longValue());
                }
                return atomicLongArray;
            case 2:
                if (a60Var.O() != JsonToken.NULL) {
                    return this.b.a(a60Var);
                }
                a60Var.K();
                return null;
            default:
                Date date = (Date) this.b.a(a60Var);
                if (date != null) {
                    return new Timestamp(date.getTime());
                }
                return null;
        }
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) throws IOException {
        switch (this.a) {
            case 0:
                this.b.b(f60Var, Long.valueOf(((AtomicLong) obj).get()));
                break;
            case 1:
                AtomicLongArray atomicLongArray = (AtomicLongArray) obj;
                f60Var.l();
                int length = atomicLongArray.length();
                for (int i = 0; i < length; i++) {
                    this.b.b(f60Var, Long.valueOf(atomicLongArray.get(i)));
                }
                f60Var.y();
                break;
            case 2:
                if (obj == null) {
                    f60Var.C();
                } else {
                    this.b.b(f60Var, obj);
                }
                break;
            default:
                this.b.b(f60Var, (Timestamp) obj);
                break;
        }
    }
}
