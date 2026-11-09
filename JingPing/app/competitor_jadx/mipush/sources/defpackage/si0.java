package defpackage;

import com.google.gson.internal.LinkedTreeMap;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class si0 extends e21 {
    public static final o8 b = new o8(2);
    public final m20 a;

    public si0(m20 m20Var) {
        this.a = m20Var;
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) {
        switch (ri0.a[a60Var.O().ordinal()]) {
            case 1:
                ArrayList arrayList = new ArrayList();
                a60Var.j();
                while (a60Var.B()) {
                    arrayList.add(a(a60Var));
                }
                a60Var.y();
                return arrayList;
            case 2:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                a60Var.l();
                while (a60Var.B()) {
                    linkedTreeMap.put(a60Var.I(), a(a60Var));
                }
                a60Var.z();
                return linkedTreeMap;
            case 3:
                return a60Var.M();
            case 4:
                return Double.valueOf(a60Var.F());
            case 5:
                return Boolean.valueOf(a60Var.E());
            case 6:
                a60Var.K();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) throws IOException {
        if (obj == null) {
            f60Var.C();
            return;
        }
        Class<?> cls = obj.getClass();
        m20 m20Var = this.a;
        m20Var.getClass();
        e21 e21VarD = m20Var.d(new m21(cls));
        if (!(e21VarD instanceof si0)) {
            e21VarD.b(f60Var, obj);
        } else {
            f60Var.w();
            f60Var.z();
        }
    }
}
