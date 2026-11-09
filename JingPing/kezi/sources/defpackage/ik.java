package defpackage;

import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class ik implements fk {
    public final ut0 d;
    public int f;
    public int g;
    public ut0 a = null;
    public boolean b = false;
    public boolean c = false;
    public int e = 1;
    public int h = 1;
    public xk i = null;
    public boolean j = false;
    public final ArrayList k = new ArrayList();
    public final ArrayList l = new ArrayList();

    public ik(ut0 ut0Var) {
        this.d = ut0Var;
    }

    @Override // defpackage.fk
    public final void a(fk fkVar) {
        ArrayList<ik> arrayList = this.l;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (!((ik) it.next()).j) {
                return;
            }
        }
        this.c = true;
        ut0 ut0Var = this.a;
        if (ut0Var != null) {
            ut0Var.a(this);
        }
        if (this.b) {
            this.d.a(this);
            return;
        }
        ik ikVar = null;
        int i = 0;
        for (ik ikVar2 : arrayList) {
            if (!(ikVar2 instanceof xk)) {
                i++;
                ikVar = ikVar2;
            }
        }
        if (ikVar != null && i == 1 && ikVar.j) {
            xk xkVar = this.i;
            if (xkVar != null) {
                if (!xkVar.j) {
                    return;
                } else {
                    this.f = this.h * xkVar.g;
                }
            }
            d(ikVar.g + this.f);
        }
        ut0 ut0Var2 = this.a;
        if (ut0Var2 != null) {
            ut0Var2.a(this);
        }
    }

    public final void b(ut0 ut0Var) {
        this.k.add(ut0Var);
        if (this.j) {
            ut0Var.a(ut0Var);
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
        for (fk fkVar : this.k) {
            fkVar.a(fkVar);
        }
    }

    public final String toString() {
        String str;
        StringBuilder sb = new StringBuilder();
        sb.append(this.d.b.Y);
        sb.append(":");
        switch (this.e) {
            case 1:
                str = "UNKNOWN";
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
        sb.append(">");
        return sb.toString();
    }
}
