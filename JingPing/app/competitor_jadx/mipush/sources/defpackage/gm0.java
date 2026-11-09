package defpackage;

import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class gm0 {
    public static final HashMap a;

    static {
        HashMap map = new HashMap();
        a = map;
        map.put(vj0.g, new fm0(0));
        map.put(yf0.R, new fm0(2));
        map.put(yf0.S, new fm0(2));
        map.put(yf0.T, new fm0(2));
        map.put(yf0.n, new fm0(1));
        map.put(yf0.o, new fm0(1));
        map.put(yf0.p, new fm0(1));
        map.put(yf0.q, new fm0(1));
        map.put(yf0.r, new fm0(1));
        map.put(yf0.s, new fm0(1));
        zn.u(map, yf0.t);
        zn.u(map, yf0.u);
        zn.u(map, yf0.v);
        zn.u(map, yf0.w);
        zn.u(map, yf0.x);
        zn.u(map, yf0.y);
        zn.u(map, yf0.z);
        zn.u(map, yf0.A);
        zn.u(map, yf0.B);
        zn.u(map, yf0.C);
        zn.u(map, yf0.D);
        zn.u(map, yf0.E);
        zn.u(map, yf0.F);
        zn.u(map, yf0.G);
        map.put(yf0.H, new fm0(3));
        zn.u(map, yf0.I);
        zn.u(map, yf0.J);
        zn.u(map, yf0.K);
        zn.u(map, yf0.L);
        zn.u(map, yf0.M);
        zn.u(map, yf0.N);
        zn.u(map, yf0.O);
        zn.u(map, yf0.P);
        zn.u(map, yf0.Q);
    }

    public static s8 a(sx0 sx0Var) throws Throwable {
        s8 s8VarA;
        if (sx0Var == null) {
            throw new IllegalArgumentException("keyInfo argument null");
        }
        HashMap map = a;
        l4 l4Var = sx0Var.E;
        fm0 fm0Var = (fm0) map.get(l4Var.E);
        if (fm0Var == null) {
            throw new IOException("algorithm identifier in public key not recognised: " + l4Var.E);
        }
        switch (fm0Var.a) {
            case 0:
                byte[] bArrR = e0.o(g0.l(sx0Var.F.r())).E;
                if (ma0.g(0, bArrR) == 1) {
                    s8VarA = d80.a(z91.r(bArrR, 4, bArrR.length));
                } else {
                    if (bArrR.length == 64) {
                        bArrR = z91.r(bArrR, 4, bArrR.length);
                    }
                    s8VarA = t20.a(bArrR);
                }
                return s8VarA;
            case 1:
                return fm0.a((hb0) l31.d.get(sx0Var.E.E), sx0Var.F);
            case 2:
                return new ob0((mb0) l31.b.get(sx0Var.E.E), sx0Var.F.r());
            default:
                try {
                    byte[] bArr = e0.o(g0.l(sx0Var.F.r())).E;
                    return new lq0((jq0) l31.f.get(sx0Var.E.E), z91.r(bArr, 4, bArr.length));
                } catch (Exception unused) {
                    return new lq0((jq0) l31.f.get(sx0Var.E.E), sx0Var.F.r());
                }
        }
    }
}
