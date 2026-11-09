package defpackage;

import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.work.impl.WorkDatabaseVersions;
import io.nats.client.support.NatsConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class yh {
    public final mh A;
    public final mh B;
    public final mh C;
    public final mh D;
    public final mh E;
    public final mh F;
    public final mh[] G;
    public final ArrayList H;
    public final boolean[] I;
    public ConstraintWidget$DimensionBehaviour[] J;
    public yh K;
    public int L;
    public int M;
    public float N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public int T;
    public float U;
    public float V;
    public View W;
    public int X;
    public String Y;
    public int Z;
    public boolean a = false;
    public int a0;
    public xc b;
    public final float[] b0;
    public xc c;
    public final yh[] c0;
    public final g30 d;
    public final yh[] d0;
    public final i41 e;
    public yh e0;
    public final boolean[] f;
    public yh f0;
    public final int[] g;
    public int h;
    public int i;
    public int j;
    public int k;
    public final int[] l;
    public int m;
    public int n;
    public float o;
    public int p;
    public int q;
    public float r;
    public int s;
    public float t;
    public int[] u;
    public float v;
    public boolean w;
    public boolean x;
    public final mh y;
    public final mh z;

    public yh() {
        g30 g30Var = new g30(this);
        g30Var.h.e = 4;
        g30Var.i.e = 5;
        g30Var.f = 0;
        this.d = g30Var;
        i41 i41Var = new i41(this);
        ao aoVar = new ao(i41Var);
        i41Var.k = aoVar;
        i41Var.l = null;
        i41Var.h.e = 6;
        i41Var.i.e = 7;
        aoVar.e = 8;
        i41Var.f = 1;
        this.e = i41Var;
        this.f = new boolean[]{true, true};
        this.g = new int[]{0, 0, 0, 0};
        this.h = -1;
        this.i = -1;
        this.j = 0;
        this.k = 0;
        this.l = new int[2];
        this.m = 0;
        this.n = 0;
        this.o = 1.0f;
        this.p = 0;
        this.q = 0;
        this.r = 1.0f;
        this.s = -1;
        this.t = 1.0f;
        this.u = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE};
        this.v = 0.0f;
        this.w = false;
        mh mhVar = new mh(this, ConstraintAnchor$Type.LEFT);
        this.y = mhVar;
        mh mhVar2 = new mh(this, ConstraintAnchor$Type.TOP);
        this.z = mhVar2;
        mh mhVar3 = new mh(this, ConstraintAnchor$Type.RIGHT);
        this.A = mhVar3;
        mh mhVar4 = new mh(this, ConstraintAnchor$Type.BOTTOM);
        this.B = mhVar4;
        mh mhVar5 = new mh(this, ConstraintAnchor$Type.BASELINE);
        this.C = mhVar5;
        mh mhVar6 = new mh(this, ConstraintAnchor$Type.CENTER_X);
        this.D = mhVar6;
        mh mhVar7 = new mh(this, ConstraintAnchor$Type.CENTER_Y);
        this.E = mhVar7;
        mh mhVar8 = new mh(this, ConstraintAnchor$Type.CENTER);
        this.F = mhVar8;
        this.G = new mh[]{mhVar, mhVar3, mhVar2, mhVar4, mhVar5, mhVar8};
        ArrayList arrayList = new ArrayList();
        this.H = arrayList;
        this.I = new boolean[2];
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.FIXED;
        this.J = new ConstraintWidget$DimensionBehaviour[]{constraintWidget$DimensionBehaviour, constraintWidget$DimensionBehaviour};
        this.K = null;
        this.L = 0;
        this.M = 0;
        this.N = 0.0f;
        this.O = -1;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.U = 0.5f;
        this.V = 0.5f;
        this.X = 0;
        this.Y = null;
        this.Z = 0;
        this.a0 = 0;
        this.b0 = new float[]{-1.0f, -1.0f};
        this.c0 = new yh[]{null, null};
        this.d0 = new yh[]{null, null};
        this.e0 = null;
        this.f0 = null;
        arrayList.add(mhVar);
        arrayList.add(mhVar2);
        arrayList.add(mhVar3);
        arrayList.add(mhVar4);
        arrayList.add(mhVar6);
        arrayList.add(mhVar7);
        arrayList.add(mhVar8);
        arrayList.add(mhVar5);
    }

    public void A(boolean z, boolean z2) {
        int i;
        int i2;
        g30 g30Var = this.d;
        boolean z3 = z & g30Var.g;
        i41 i41Var = this.e;
        boolean z4 = z2 & i41Var.g;
        int i3 = g30Var.h.g;
        int i4 = i41Var.h.g;
        int i5 = g30Var.i.g;
        int i6 = i41Var.i.g;
        int i7 = i6 - i4;
        if (i5 - i3 < 0 || i7 < 0 || i3 == Integer.MIN_VALUE || i3 == Integer.MAX_VALUE || i4 == Integer.MIN_VALUE || i4 == Integer.MAX_VALUE || i5 == Integer.MIN_VALUE || i5 == Integer.MAX_VALUE || i6 == Integer.MIN_VALUE || i6 == Integer.MAX_VALUE) {
            i5 = 0;
            i6 = 0;
            i3 = 0;
            i4 = 0;
        }
        int i8 = i5 - i3;
        int i9 = i6 - i4;
        if (z3) {
            this.P = i3;
        }
        if (z4) {
            this.Q = i4;
        }
        if (this.X == 8) {
            this.L = 0;
            this.M = 0;
            return;
        }
        if (z3) {
            if (this.J[0] == ConstraintWidget$DimensionBehaviour.FIXED && i8 < (i2 = this.L)) {
                i8 = i2;
            }
            this.L = i8;
            int i10 = this.S;
            if (i8 < i10) {
                this.L = i10;
            }
        }
        if (z4) {
            if (this.J[1] == ConstraintWidget$DimensionBehaviour.FIXED && i9 < (i = this.M)) {
                i9 = i;
            }
            this.M = i9;
            int i11 = this.T;
            if (i9 < i11) {
                this.M = i11;
            }
        }
    }

    public void B(j90 j90Var) {
        int i;
        int i2;
        mh mhVar = this.y;
        j90Var.getClass();
        int iM = j90.m(mhVar);
        int iM2 = j90.m(this.z);
        int iM3 = j90.m(this.A);
        int iM4 = j90.m(this.B);
        g30 g30Var = this.d;
        ao aoVar = g30Var.h;
        if (aoVar.j) {
            ao aoVar2 = g30Var.i;
            if (aoVar2.j) {
                iM = aoVar.g;
                iM3 = aoVar2.g;
            }
        }
        i41 i41Var = this.e;
        ao aoVar3 = i41Var.h;
        if (aoVar3.j) {
            ao aoVar4 = i41Var.i;
            if (aoVar4.j) {
                iM2 = aoVar3.g;
                iM4 = aoVar4.g;
            }
        }
        int i3 = iM4 - iM2;
        if (iM3 - iM < 0 || i3 < 0 || iM == Integer.MIN_VALUE || iM == Integer.MAX_VALUE || iM2 == Integer.MIN_VALUE || iM2 == Integer.MAX_VALUE || iM3 == Integer.MIN_VALUE || iM3 == Integer.MAX_VALUE || iM4 == Integer.MIN_VALUE || iM4 == Integer.MAX_VALUE) {
            iM = 0;
            iM2 = 0;
            iM3 = 0;
            iM4 = 0;
        }
        int i4 = iM3 - iM;
        int i5 = iM4 - iM2;
        this.P = iM;
        this.Q = iM2;
        if (this.X == 8) {
            this.L = 0;
            this.M = 0;
            return;
        }
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = this.J;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = constraintWidget$DimensionBehaviourArr[0];
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour2 = ConstraintWidget$DimensionBehaviour.FIXED;
        if (constraintWidget$DimensionBehaviour == constraintWidget$DimensionBehaviour2 && i4 < (i2 = this.L)) {
            i4 = i2;
        }
        if (constraintWidget$DimensionBehaviourArr[1] == constraintWidget$DimensionBehaviour2 && i5 < (i = this.M)) {
            i5 = i;
        }
        this.L = i4;
        this.M = i5;
        int i6 = this.T;
        if (i5 < i6) {
            this.M = i6;
        }
        int i7 = this.S;
        if (i4 < i7) {
            this.L = i7;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:192:0x02e5  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x02ee A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:200:0x02fa  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x0300  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x0303  */
    /* JADX WARN: Removed duplicated region for block: B:206:0x0305  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:233:0x03fd  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x0422  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0463  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0473  */
    /* JADX WARN: Removed duplicated region for block: B:253:0x0476  */
    /* JADX WARN: Removed duplicated region for block: B:280:0x04b8  */
    /* JADX WARN: Removed duplicated region for block: B:290:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:292:0x052a  */
    /* JADX WARN: Removed duplicated region for block: B:298:0x0588  */
    /* JADX WARN: Removed duplicated region for block: B:304:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void a(defpackage.j90 r59) {
        /*
            Method dump skipped, instruction units count: 1594
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yh.a(j90):void");
    }

    public boolean b() {
        return this.X != 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x0294 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:175:0x029d  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02a3  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02ca  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02de  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x03b7 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:285:0x03ec  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:292:? A[ADDED_TO_REGION, RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:296:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0074  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0187 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void c(defpackage.j90 r28, boolean r29, boolean r30, boolean r31, boolean r32, defpackage.hw0 r33, defpackage.hw0 r34, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r35, boolean r36, defpackage.mh r37, defpackage.mh r38, int r39, int r40, int r41, int r42, float r43, boolean r44, boolean r45, boolean r46, boolean r47, int r48, int r49, int r50, int r51, float r52, boolean r53) {
        /*
            Method dump skipped, instruction units count: 1014
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.yh.c(j90, boolean, boolean, boolean, boolean, hw0, hw0, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, mh, mh, int, int, int, int, float, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    public final void d(mh mhVar, mh mhVar2, int i) {
        if (mhVar.b == this) {
            e(mhVar.c, mhVar2.b, mhVar2.c, i);
        }
    }

    public final void e(ConstraintAnchor$Type constraintAnchor$Type, yh yhVar, ConstraintAnchor$Type constraintAnchor$Type2, int i) {
        ConstraintAnchor$Type constraintAnchor$Type3;
        ConstraintAnchor$Type constraintAnchor$Type4;
        boolean z;
        ConstraintAnchor$Type constraintAnchor$Type5 = ConstraintAnchor$Type.CENTER;
        if (constraintAnchor$Type == constraintAnchor$Type5) {
            if (constraintAnchor$Type2 != constraintAnchor$Type5) {
                ConstraintAnchor$Type constraintAnchor$Type6 = ConstraintAnchor$Type.LEFT;
                if (constraintAnchor$Type2 == constraintAnchor$Type6 || constraintAnchor$Type2 == ConstraintAnchor$Type.RIGHT) {
                    e(constraintAnchor$Type6, yhVar, constraintAnchor$Type2, 0);
                    e(ConstraintAnchor$Type.RIGHT, yhVar, constraintAnchor$Type2, 0);
                    h(constraintAnchor$Type5).a(yhVar.h(constraintAnchor$Type2), 0);
                    return;
                }
                ConstraintAnchor$Type constraintAnchor$Type7 = ConstraintAnchor$Type.TOP;
                if (constraintAnchor$Type2 == constraintAnchor$Type7 || constraintAnchor$Type2 == ConstraintAnchor$Type.BOTTOM) {
                    e(constraintAnchor$Type7, yhVar, constraintAnchor$Type2, 0);
                    e(ConstraintAnchor$Type.BOTTOM, yhVar, constraintAnchor$Type2, 0);
                    h(constraintAnchor$Type5).a(yhVar.h(constraintAnchor$Type2), 0);
                    return;
                }
                return;
            }
            ConstraintAnchor$Type constraintAnchor$Type8 = ConstraintAnchor$Type.LEFT;
            mh mhVarH = h(constraintAnchor$Type8);
            ConstraintAnchor$Type constraintAnchor$Type9 = ConstraintAnchor$Type.RIGHT;
            mh mhVarH2 = h(constraintAnchor$Type9);
            ConstraintAnchor$Type constraintAnchor$Type10 = ConstraintAnchor$Type.TOP;
            mh mhVarH3 = h(constraintAnchor$Type10);
            ConstraintAnchor$Type constraintAnchor$Type11 = ConstraintAnchor$Type.BOTTOM;
            mh mhVarH4 = h(constraintAnchor$Type11);
            boolean z2 = true;
            if ((mhVarH == null || !mhVarH.f()) && (mhVarH2 == null || !mhVarH2.f())) {
                e(constraintAnchor$Type8, yhVar, constraintAnchor$Type8, 0);
                e(constraintAnchor$Type9, yhVar, constraintAnchor$Type9, 0);
                z = true;
            } else {
                z = false;
            }
            if ((mhVarH3 == null || !mhVarH3.f()) && (mhVarH4 == null || !mhVarH4.f())) {
                e(constraintAnchor$Type10, yhVar, constraintAnchor$Type10, 0);
                e(constraintAnchor$Type11, yhVar, constraintAnchor$Type11, 0);
            } else {
                z2 = false;
            }
            if (z && z2) {
                h(constraintAnchor$Type5).a(yhVar.h(constraintAnchor$Type5), 0);
                return;
            }
            if (z) {
                ConstraintAnchor$Type constraintAnchor$Type12 = ConstraintAnchor$Type.CENTER_X;
                h(constraintAnchor$Type12).a(yhVar.h(constraintAnchor$Type12), 0);
                return;
            } else {
                if (z2) {
                    ConstraintAnchor$Type constraintAnchor$Type13 = ConstraintAnchor$Type.CENTER_Y;
                    h(constraintAnchor$Type13).a(yhVar.h(constraintAnchor$Type13), 0);
                    return;
                }
                return;
            }
        }
        ConstraintAnchor$Type constraintAnchor$Type14 = ConstraintAnchor$Type.CENTER_X;
        if (constraintAnchor$Type == constraintAnchor$Type14 && (constraintAnchor$Type2 == (constraintAnchor$Type4 = ConstraintAnchor$Type.LEFT) || constraintAnchor$Type2 == ConstraintAnchor$Type.RIGHT)) {
            mh mhVarH5 = h(constraintAnchor$Type4);
            mh mhVarH6 = yhVar.h(constraintAnchor$Type2);
            mh mhVarH7 = h(ConstraintAnchor$Type.RIGHT);
            mhVarH5.a(mhVarH6, 0);
            mhVarH7.a(mhVarH6, 0);
            h(constraintAnchor$Type14).a(mhVarH6, 0);
            return;
        }
        ConstraintAnchor$Type constraintAnchor$Type15 = ConstraintAnchor$Type.CENTER_Y;
        if (constraintAnchor$Type == constraintAnchor$Type15 && (constraintAnchor$Type2 == (constraintAnchor$Type3 = ConstraintAnchor$Type.TOP) || constraintAnchor$Type2 == ConstraintAnchor$Type.BOTTOM)) {
            mh mhVarH8 = yhVar.h(constraintAnchor$Type2);
            h(constraintAnchor$Type3).a(mhVarH8, 0);
            h(ConstraintAnchor$Type.BOTTOM).a(mhVarH8, 0);
            h(constraintAnchor$Type15).a(mhVarH8, 0);
            return;
        }
        if (constraintAnchor$Type == constraintAnchor$Type14 && constraintAnchor$Type2 == constraintAnchor$Type14) {
            ConstraintAnchor$Type constraintAnchor$Type16 = ConstraintAnchor$Type.LEFT;
            h(constraintAnchor$Type16).a(yhVar.h(constraintAnchor$Type16), 0);
            ConstraintAnchor$Type constraintAnchor$Type17 = ConstraintAnchor$Type.RIGHT;
            h(constraintAnchor$Type17).a(yhVar.h(constraintAnchor$Type17), 0);
            h(constraintAnchor$Type14).a(yhVar.h(constraintAnchor$Type2), 0);
            return;
        }
        if (constraintAnchor$Type == constraintAnchor$Type15 && constraintAnchor$Type2 == constraintAnchor$Type15) {
            ConstraintAnchor$Type constraintAnchor$Type18 = ConstraintAnchor$Type.TOP;
            h(constraintAnchor$Type18).a(yhVar.h(constraintAnchor$Type18), 0);
            ConstraintAnchor$Type constraintAnchor$Type19 = ConstraintAnchor$Type.BOTTOM;
            h(constraintAnchor$Type19).a(yhVar.h(constraintAnchor$Type19), 0);
            h(constraintAnchor$Type15).a(yhVar.h(constraintAnchor$Type2), 0);
            return;
        }
        mh mhVarH9 = h(constraintAnchor$Type);
        mh mhVarH10 = yhVar.h(constraintAnchor$Type2);
        if (mhVarH9.g(mhVarH10)) {
            ConstraintAnchor$Type constraintAnchor$Type20 = ConstraintAnchor$Type.BASELINE;
            if (constraintAnchor$Type == constraintAnchor$Type20) {
                mh mhVarH11 = h(ConstraintAnchor$Type.TOP);
                mh mhVarH12 = h(ConstraintAnchor$Type.BOTTOM);
                if (mhVarH11 != null) {
                    mhVarH11.h();
                }
                if (mhVarH12 != null) {
                    mhVarH12.h();
                }
                i = 0;
            } else if (constraintAnchor$Type == ConstraintAnchor$Type.TOP || constraintAnchor$Type == ConstraintAnchor$Type.BOTTOM) {
                mh mhVarH13 = h(constraintAnchor$Type20);
                if (mhVarH13 != null) {
                    mhVarH13.h();
                }
                mh mhVarH14 = h(constraintAnchor$Type5);
                if (mhVarH14.d != mhVarH10) {
                    mhVarH14.h();
                }
                mh mhVarD = h(constraintAnchor$Type).d();
                mh mhVarH15 = h(constraintAnchor$Type15);
                if (mhVarH15.f()) {
                    mhVarD.h();
                    mhVarH15.h();
                }
            } else if (constraintAnchor$Type == ConstraintAnchor$Type.LEFT || constraintAnchor$Type == ConstraintAnchor$Type.RIGHT) {
                mh mhVarH16 = h(constraintAnchor$Type5);
                if (mhVarH16.d != mhVarH10) {
                    mhVarH16.h();
                }
                mh mhVarD2 = h(constraintAnchor$Type).d();
                mh mhVarH17 = h(constraintAnchor$Type14);
                if (mhVarH17.f()) {
                    mhVarD2.h();
                    mhVarH17.h();
                }
            }
            mhVarH9.a(mhVarH10, i);
        }
    }

    public void f(yh yhVar, HashMap map) {
        this.h = yhVar.h;
        this.i = yhVar.i;
        this.j = yhVar.j;
        this.k = yhVar.k;
        int[] iArr = yhVar.l;
        int i = iArr[0];
        int[] iArr2 = this.l;
        iArr2[0] = i;
        iArr2[1] = iArr[1];
        this.m = yhVar.m;
        this.n = yhVar.n;
        this.p = yhVar.p;
        this.q = yhVar.q;
        this.r = yhVar.r;
        this.s = yhVar.s;
        this.t = yhVar.t;
        int[] iArr3 = yhVar.u;
        this.u = Arrays.copyOf(iArr3, iArr3.length);
        this.v = yhVar.v;
        this.w = yhVar.w;
        this.x = yhVar.x;
        this.y.h();
        this.z.h();
        this.A.h();
        this.B.h();
        this.C.h();
        this.D.h();
        this.E.h();
        this.F.h();
        this.J = (ConstraintWidget$DimensionBehaviour[]) Arrays.copyOf(this.J, 2);
        this.K = this.K == null ? null : (yh) map.get(yhVar.K);
        this.L = yhVar.L;
        this.M = yhVar.M;
        this.N = yhVar.N;
        this.O = yhVar.O;
        this.P = yhVar.P;
        this.Q = yhVar.Q;
        this.R = yhVar.R;
        this.S = yhVar.S;
        this.T = yhVar.T;
        this.U = yhVar.U;
        this.V = yhVar.V;
        this.W = yhVar.W;
        this.X = yhVar.X;
        this.Y = yhVar.Y;
        this.Z = yhVar.Z;
        this.a0 = yhVar.a0;
        float[] fArr = yhVar.b0;
        float f = fArr[0];
        float[] fArr2 = this.b0;
        fArr2[0] = f;
        fArr2[1] = fArr[1];
        yh[] yhVarArr = yhVar.c0;
        yh yhVar2 = yhVarArr[0];
        yh[] yhVarArr2 = this.c0;
        yhVarArr2[0] = yhVar2;
        yhVarArr2[1] = yhVarArr[1];
        yh[] yhVarArr3 = yhVar.d0;
        yh yhVar3 = yhVarArr3[0];
        yh[] yhVarArr4 = this.d0;
        yhVarArr4[0] = yhVar3;
        yhVarArr4[1] = yhVarArr3[1];
        yh yhVar4 = yhVar.e0;
        this.e0 = yhVar4 == null ? null : (yh) map.get(yhVar4);
        yh yhVar5 = yhVar.f0;
        this.f0 = yhVar5 != null ? (yh) map.get(yhVar5) : null;
    }

    public final void g(j90 j90Var) {
        j90Var.j(this.y);
        j90Var.j(this.z);
        j90Var.j(this.A);
        j90Var.j(this.B);
        if (this.R > 0) {
            j90Var.j(this.C);
        }
    }

    public mh h(ConstraintAnchor$Type constraintAnchor$Type) {
        switch (xh.a[constraintAnchor$Type.ordinal()]) {
            case 1:
                return this.y;
            case 2:
                return this.z;
            case 3:
                return this.A;
            case 4:
                return this.B;
            case 5:
                return this.C;
            case 6:
                return this.F;
            case 7:
                return this.D;
            case 8:
                return this.E;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                return null;
            default:
                throw new AssertionError(constraintAnchor$Type.name());
        }
    }

    public final ConstraintWidget$DimensionBehaviour i(int i) {
        if (i == 0) {
            return this.J[0];
        }
        if (i == 1) {
            return this.J[1];
        }
        return null;
    }

    public final int j() {
        if (this.X == 8) {
            return 0;
        }
        return this.M;
    }

    public final yh k(int i) {
        mh mhVar;
        mh mhVar2;
        if (i != 0) {
            if (i == 1 && (mhVar2 = (mhVar = this.B).d) != null && mhVar2.d == mhVar) {
                return mhVar2.b;
            }
            return null;
        }
        mh mhVar3 = this.A;
        mh mhVar4 = mhVar3.d;
        if (mhVar4 == null || mhVar4.d != mhVar3) {
            return null;
        }
        return mhVar4.b;
    }

    public final yh l(int i) {
        mh mhVar;
        mh mhVar2;
        if (i != 0) {
            if (i == 1 && (mhVar2 = (mhVar = this.z).d) != null && mhVar2.d == mhVar) {
                return mhVar2.b;
            }
            return null;
        }
        mh mhVar3 = this.y;
        mh mhVar4 = mhVar3.d;
        if (mhVar4 == null || mhVar4.d != mhVar3) {
            return null;
        }
        return mhVar4.b;
    }

    public final int m() {
        if (this.X == 8) {
            return 0;
        }
        return this.L;
    }

    public final int n() {
        yh yhVar = this.K;
        return (yhVar == null || !(yhVar instanceof zh)) ? this.P : ((zh) yhVar).m0 + this.P;
    }

    public final int o() {
        yh yhVar = this.K;
        return (yhVar == null || !(yhVar instanceof zh)) ? this.Q : ((zh) yhVar).n0 + this.Q;
    }

    public final void p(ConstraintAnchor$Type constraintAnchor$Type, yh yhVar, ConstraintAnchor$Type constraintAnchor$Type2, int i, int i2) {
        h(constraintAnchor$Type).b(yhVar.h(constraintAnchor$Type2), i, i2, true);
    }

    public final boolean q(int i) {
        mh mhVar;
        mh mhVar2;
        int i2 = i * 2;
        mh[] mhVarArr = this.G;
        mh mhVar3 = mhVarArr[i2];
        mh mhVar4 = mhVar3.d;
        return (mhVar4 == null || mhVar4.d == mhVar3 || (mhVar2 = (mhVar = mhVarArr[i2 + 1]).d) == null || mhVar2.d != mhVar) ? false : true;
    }

    public final boolean r() {
        mh mhVar = this.y;
        mh mhVar2 = mhVar.d;
        if (mhVar2 != null && mhVar2.d == mhVar) {
            return true;
        }
        mh mhVar3 = this.A;
        mh mhVar4 = mhVar3.d;
        return mhVar4 != null && mhVar4.d == mhVar3;
    }

    public final boolean s() {
        mh mhVar = this.z;
        mh mhVar2 = mhVar.d;
        if (mhVar2 != null && mhVar2.d == mhVar) {
            return true;
        }
        mh mhVar3 = this.B;
        mh mhVar4 = mhVar3.d;
        return mhVar4 != null && mhVar4.d == mhVar3;
    }

    public void t() {
        this.y.h();
        this.z.h();
        this.A.h();
        this.B.h();
        this.C.h();
        this.D.h();
        this.E.h();
        this.F.h();
        this.K = null;
        this.v = 0.0f;
        this.L = 0;
        this.M = 0;
        this.N = 0.0f;
        this.O = -1;
        this.P = 0;
        this.Q = 0;
        this.R = 0;
        this.S = 0;
        this.T = 0;
        this.U = 0.5f;
        this.V = 0.5f;
        ConstraintWidget$DimensionBehaviour[] constraintWidget$DimensionBehaviourArr = this.J;
        ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour = ConstraintWidget$DimensionBehaviour.FIXED;
        constraintWidget$DimensionBehaviourArr[0] = constraintWidget$DimensionBehaviour;
        constraintWidget$DimensionBehaviourArr[1] = constraintWidget$DimensionBehaviour;
        this.W = null;
        this.X = 0;
        this.Z = 0;
        this.a0 = 0;
        float[] fArr = this.b0;
        fArr[0] = -1.0f;
        fArr[1] = -1.0f;
        this.h = -1;
        this.i = -1;
        int[] iArr = this.u;
        iArr[0] = Integer.MAX_VALUE;
        iArr[1] = Integer.MAX_VALUE;
        this.j = 0;
        this.k = 0;
        this.o = 1.0f;
        this.r = 1.0f;
        this.n = Integer.MAX_VALUE;
        this.q = Integer.MAX_VALUE;
        this.m = 0;
        this.p = 0;
        this.s = -1;
        this.t = 1.0f;
        boolean[] zArr = this.f;
        zArr[0] = true;
        zArr[1] = true;
        boolean[] zArr2 = this.I;
        zArr2[0] = false;
        zArr2[1] = false;
    }

    public String toString() {
        StringBuilder sbP = zn.p("");
        sbP.append(this.Y != null ? zn.o(new StringBuilder("id: "), this.Y, NatsConstants.SPACE) : "");
        sbP.append("(");
        sbP.append(this.P);
        sbP.append(", ");
        sbP.append(this.Q);
        sbP.append(") - (");
        sbP.append(this.L);
        sbP.append(" x ");
        sbP.append(this.M);
        sbP.append(")");
        return sbP.toString();
    }

    public final void u() {
        yh yhVar = this.K;
        if (yhVar != null && (yhVar instanceof zh)) {
            ((zh) yhVar).getClass();
        }
        ArrayList arrayList = this.H;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((mh) arrayList.get(i)).h();
        }
    }

    public void v(tb tbVar) {
        this.y.i();
        this.z.i();
        this.A.i();
        this.B.i();
        this.C.i();
        this.F.i();
        this.D.i();
        this.E.i();
    }

    public final void w(int i) {
        this.M = i;
        int i2 = this.T;
        if (i < i2) {
            this.M = i2;
        }
    }

    public final void x(ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour) {
        this.J[0] = constraintWidget$DimensionBehaviour;
    }

    public final void y(ConstraintWidget$DimensionBehaviour constraintWidget$DimensionBehaviour) {
        this.J[1] = constraintWidget$DimensionBehaviour;
    }

    public final void z(int i) {
        this.L = i;
        int i2 = this.S;
        if (i < i2) {
            this.L = i2;
        }
    }
}
