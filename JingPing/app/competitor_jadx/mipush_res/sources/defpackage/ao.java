package defpackage;

import io.nats.client.support.NatsConstants;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ao implements xn {
    public final q71 d;
    public int f;
    public int g;
    public q71 a = null;
    public boolean b = false;
    public boolean c = false;
    public int e = 1;
    public int h = 1;
    public so i = null;
    public boolean j = false;
    public final ArrayList k = new ArrayList();
    public final ArrayList l = new ArrayList();

    public ao(q71 q71Var) {
        this.d = q71Var;
    }

    @Override // defpackage.xn
    public final void a(xn xnVar) {
        ArrayList<ao> arrayList = this.l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((ao) it.next()).j) {
                return;
            }
        }
        this.c = true;
        q71 q71Var = this.a;
        if (q71Var != null) {
            q71Var.a(this);
        }
        if (this.b) {
            this.d.a(this);
            return;
        }
        ao aoVar = null;
        int i = 0;
        for (ao aoVar2 : arrayList) {
            if (!(aoVar2 instanceof so)) {
                i++;
                aoVar = aoVar2;
            }
        }
        if (aoVar != null && i == 1 && aoVar.j) {
            so soVar = this.i;
            if (soVar != null) {
                if (!soVar.j) {
                    return;
                } else {
                    this.f = this.h * soVar.g;
                }
            }
            d(aoVar.g + this.f);
        }
        q71 q71Var2 = this.a;
        if (q71Var2 != null) {
            q71Var2.a(this);
        }
    }

    public final void b(q71 q71Var) {
        this.k.add(q71Var);
        if (this.j) {
            q71Var.a(q71Var);
        }
    }

    public final void c() {
        this.l.clear();
        this.k.clear();
        this.j = false;
        this.g = 0;
        this.c = false;
        this.b = false;
    }

    public void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (xn xnVar : this.k) {
            xnVar.a(xnVar);
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.b.Y);
        sb.append(":");
        switch (this.e) {
            case 1:
                str = NatsConstants.UNKNOWN_OP;
                break;
            case 2:
                str = "HORIZONTAL_DIMENSION";
                break;
            case 3:
                str = "VERTICAL_DIMENSION";
                break;
            case 4:
                str = "LEFT";
                break;
            case 5:
                str = "RIGHT";
                break;
            case 6:
                str = "TOP";
                break;
            case 7:
                str = "BOTTOM";
                break;
            case 8:
                str = "BASELINE";
                break;
            default:
                str = "null";
                break;
        }
        sb.append(str);
        sb.append("(");
        sb.append(this.j ? Integer.valueOf(this.g) : "unresolved");
        sb.append(") <t=");
        sb.append(this.l.size());
        sb.append(":d=");
        sb.append(this.k.size());
        sb.append(NatsConstants.GREATER_THAN);
        return sb.toString();
    }
}
