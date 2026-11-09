package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.work.impl.WorkDatabaseVersions;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class ag {
    public final mg b;
    public final ConstraintAnchor$Type c;
    public ag d;
    public sj0 g;
    public HashSet a = null;
    public int e = 0;
    public int f = -1;

    public ag(mg mgVar, ConstraintAnchor$Type constraintAnchor$Type) {
        this.b = mgVar;
        this.c = constraintAnchor$Type;
    }

    public final void a(ag agVar, int i) {
        b(agVar, i, -1, false);
    }

    public final boolean b(ag agVar, int i, int i2, boolean z) {
        if (agVar == null) {
            h();
            return true;
        }
        if (!z && !g(agVar)) {
            return false;
        }
        this.d = agVar;
        if (agVar.a == null) {
            agVar.a = new HashSet();
        }
        this.d.a.add(this);
        if (i > 0) {
            this.e = i;
        } else {
            this.e = 0;
        }
        this.f = i2;
        return true;
    }

    public final int c() {
        ag agVar;
        if (this.b.X == 8) {
            return 0;
        }
        int i = this.f;
        return (i <= -1 || (agVar = this.d) == null || agVar.b.X != 8) ? this.e : i;
    }

    public final ag d() {
        int[] iArr = zf.a;
        ConstraintAnchor$Type constraintAnchor$Type = this.c;
        int i = iArr[constraintAnchor$Type.ordinal()];
        mg mgVar = this.b;
        switch (i) {
            case 1:
            case 6:
            case 7:
            case 8:
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return null;
            case 2:
                return mgVar.A;
            case 3:
                return mgVar.y;
            case 4:
                return mgVar.B;
            case 5:
                return mgVar.z;
            default:
                throw new AssertionError(constraintAnchor$Type.name());
        }
    }

    public final boolean e() {
        HashSet hashSet = this.a;
        if (hashSet == null) {
            return false;
        }
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            if (((ag) it.next()).d().f()) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        return this.d != null;
    }

    public final boolean g(ag agVar) {
        if (agVar == null) {
            return false;
        }
        ConstraintAnchor$Type constraintAnchor$Type = this.c;
        mg mgVar = agVar.b;
        ConstraintAnchor$Type constraintAnchor$Type2 = agVar.c;
        if (constraintAnchor$Type2 == constraintAnchor$Type) {
            return constraintAnchor$Type != ConstraintAnchor$Type.BASELINE || (mgVar.w && this.b.w);
        }
        switch (zf.a[constraintAnchor$Type.ordinal()]) {
            case 1:
                return (constraintAnchor$Type2 == ConstraintAnchor$Type.BASELINE || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_X || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = constraintAnchor$Type2 == ConstraintAnchor$Type.LEFT || constraintAnchor$Type2 == ConstraintAnchor$Type.RIGHT;
                return mgVar instanceof kv ? z || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_X : z;
            case 4:
            case 5:
                boolean z2 = constraintAnchor$Type2 == ConstraintAnchor$Type.TOP || constraintAnchor$Type2 == ConstraintAnchor$Type.BOTTOM;
                return mgVar instanceof kv ? z2 || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_Y : z2;
            case 6:
            case 7:
            case 8:
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return false;
            default:
                throw new AssertionError(constraintAnchor$Type.name());
        }
    }

    public final void h() {
        HashSet hashSet;
        ag agVar = this.d;
        if (agVar != null && (hashSet = agVar.a) != null) {
            hashSet.remove(this);
        }
        this.d = null;
        this.e = 0;
        this.f = -1;
    }

    public final void i() {
        sj0 sj0Var = this.g;
        if (sj0Var == null) {
            this.g = new sj0(SolverVariable$Type.UNRESTRICTED);
        } else {
            sj0Var.c();
        }
    }

    public final String toString() {
        return this.b.Y + ":" + this.c.toString();
    }
}
