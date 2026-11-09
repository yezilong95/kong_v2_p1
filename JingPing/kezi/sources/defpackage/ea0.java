package defpackage;

import java.util.ArrayList;
import java.util.List;
import javax.net.SocketFactory;

/* JADX INFO: loaded from: classes.dex */
public final class ea0 {
    public final ba a = new ba(2);
    public final o70 b = new o70(12);
    public final ArrayList c = new ArrayList();
    public final ArrayList d = new ArrayList();
    public final vk e = new vk(11);
    public boolean f = true;
    public final qm g;
    public final boolean h;
    public final boolean i;
    public final qm j;
    public el k;
    public final qm l;
    public final SocketFactory m;
    public final List n;
    public final List o;
    public final da0 p;
    public cb q;
    public int r;
    public int s;
    public final int t;

    public ea0() {
        qm qmVar = qm.b;
        this.g = qmVar;
        this.h = true;
        this.i = true;
        this.j = qm.d;
        this.k = el.a;
        this.l = qmVar;
        SocketFactory socketFactory = SocketFactory.getDefault();
        oy.i(socketFactory, "getDefault()");
        this.m = socketFactory;
        this.n = fa0.B;
        this.o = fa0.A;
        this.p = da0.a;
        this.q = cb.c;
        this.r = 10000;
        this.s = 10000;
        this.t = 10000;
    }
}
