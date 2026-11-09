package defpackage;

import androidx.constraintlayout.solver.SolverVariable$Type;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.work.impl.WorkDatabaseVersions;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class mh {
    public final yh b;
    public final ConstraintAnchor$Type c;
    public mh d;
    public hw0 g;
    public HashSet a = null;
    public int e = 0;
    public int f = -1;

    public mh(yh yhVar, ConstraintAnchor$Type constraintAnchor$Type) {
        this.b = yhVar;
        this.c = constraintAnchor$Type;
    }

    public final void a(mh mhVar, int i) {
        b(mhVar, i, -1, false);
    }

    public final boolean b(mh mhVar, int i, int i2, boolean z) {
        if (mhVar == null) {
            h();
            return true;
        }
        if (!z && !g(mhVar)) {
            return false;
        }
        this.d = mhVar;
        if (mhVar.a == null) {
            mhVar.a = new HashSet();
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
        mh mhVar;
        if (this.b.X == 8) {
            return 0;
        }
        int i = this.f;
        return (i <= -1 || (mhVar = this.d) == null || mhVar.b.X != 8) ? this.e : i;
    }

    public final mh d() {
        int[] iArr = lh.a;
        ConstraintAnchor$Type constraintAnchor$Type = this.c;
        int i = iArr[constraintAnchor$Type.ordinal()];
        yh yhVar = this.b;
        switch (i) {
            case 1:
            case 6:
            case 7:
            case 8:
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return null;
            case 2:
                return yhVar.A;
            case 3:
                return yhVar.y;
            case 4:
                return yhVar.B;
            case 5:
                return yhVar.z;
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
            if (((mh) it.next()).d().f()) {
                return true;
            }
        }
        return false;
    }

    public final boolean f() {
        return this.d != null;
    }

    public final boolean g(mh mhVar) {
        if (mhVar == null) {
            return false;
        }
        ConstraintAnchor$Type constraintAnchor$Type = this.c;
        yh yhVar = mhVar.b;
        ConstraintAnchor$Type constraintAnchor$Type2 = mhVar.c;
        if (constraintAnchor$Type2 == constraintAnchor$Type) {
            return constraintAnchor$Type != ConstraintAnchor$Type.BASELINE || (yhVar.w && this.b.w);
        }
        switch (lh.a[constraintAnchor$Type.ordinal()]) {
            case 1:
                return (constraintAnchor$Type2 == ConstraintAnchor$Type.BASELINE || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_X || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_Y) ? false : true;
            case 2:
            case 3:
                boolean z = constraintAnchor$Type2 == ConstraintAnchor$Type.LEFT || constraintAnchor$Type2 == ConstraintAnchor$Type.RIGHT;
                return yhVar instanceof o20 ? z || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_X : z;
            case 4:
            case 5:
                boolean z2 = constraintAnchor$Type2 == ConstraintAnchor$Type.TOP || constraintAnchor$Type2 == ConstraintAnchor$Type.BOTTOM;
                return yhVar instanceof o20 ? z2 || constraintAnchor$Type2 == ConstraintAnchor$Type.CENTER_Y : z2;
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
        mh mhVar = this.d;
        if (mhVar != null && (hashSet = mhVar.a) != null) {
            hashSet.remove(this);
        }
        this.d = null;
        this.e = 0;
        this.f = -1;
    }

    public final void i() {
        hw0 hw0Var = this.g;
        if (hw0Var == null) {
            this.g = new hw0(SolverVariable$Type.UNRESTRICTED);
        } else {
            hw0Var.c();
        }
    }

    public final String toString() {
        return this.b.Y + ":" + this.c.toString();
    }
}
