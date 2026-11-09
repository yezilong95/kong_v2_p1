package defpackage;

import android.view.View;
import androidx.constraintlayout.solver.widgets.ConstraintAnchor$Type;
import androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour;
import androidx.work.impl.WorkDatabaseVersions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class mg {
    public final ag A;
    public final ag B;
    public final ag C;
    public final ag D;
    public final ag E;
    public final ag F;
    public final ag[] G;
    public final ArrayList H;
    public final boolean[] I;
    public ConstraintWidget$DimensionBehaviour[] J;
    public mg K;
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
    public eb b;
    public final float[] b0;
    public eb c;
    public final mg[] c0;
    public final yv d;
    public final mg[] d0;
    public final dr0 e;
    public mg e0;
    public final boolean[] f;
    public mg f0;
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
    public final ag y;
    public final ag z;

    public mg() {
        yv yvVar = new yv(this);
        yvVar.h.e = 4;
        yvVar.i.e = 5;
        yvVar.f = 0;
        this.d = yvVar;
        dr0 dr0Var = new dr0(this);
        ik ikVar = new ik(dr0Var);
        dr0Var.k = ikVar;
        dr0Var.l = null;
        dr0Var.h.e = 6;
        dr0Var.i.e = 7;
        ikVar.e = 8;
        dr0Var.f = 1;
        this.e = dr0Var;
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
        ag agVar = new ag(this, ConstraintAnchor$Type.LEFT);
        this.y = agVar;
        ag agVar2 = new ag(this, ConstraintAnchor$Type.TOP);
        this.z = agVar2;
        ag agVar3 = new ag(this, ConstraintAnchor$Type.RIGHT);
        this.A = agVar3;
        ag agVar4 = new ag(this, ConstraintAnchor$Type.BOTTOM);
        this.B = agVar4;
        ag agVar5 = new ag(this, ConstraintAnchor$Type.BASELINE);
        this.C = agVar5;
        ag agVar6 = new ag(this, ConstraintAnchor$Type.CENTER_X);
        this.D = agVar6;
        ag agVar7 = new ag(this, ConstraintAnchor$Type.CENTER_Y);
        this.E = agVar7;
        ag agVar8 = new ag(this, ConstraintAnchor$Type.CENTER);
        this.F = agVar8;
        this.G = new ag[]{agVar, agVar3, agVar2, agVar4, agVar5, agVar8};
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
        this.c0 = new mg[]{null, null};
        this.d0 = new mg[]{null, null};
        this.e0 = null;
        this.f0 = null;
        arrayList.add(agVar);
        arrayList.add(agVar2);
        arrayList.add(agVar3);
        arrayList.add(agVar4);
        arrayList.add(agVar6);
        arrayList.add(agVar7);
        arrayList.add(agVar8);
        arrayList.add(agVar5);
    }

    public void A(boolean z, boolean z2) {
        int i;
        int i2;
        yv yvVar = this.d;
        boolean z3 = z & yvVar.g;
        dr0 dr0Var = this.e;
        boolean z4 = z2 & dr0Var.g;
        int i3 = yvVar.h.g;
        int i4 = dr0Var.h.g;
        int i5 = yvVar.i.g;
        int i6 = dr0Var.i.g;
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

    public void B(f20 f20Var) {
        int i;
        int i2;
        ag agVar = this.y;
        f20Var.getClass();
        int iM = f20.m(agVar);
        int iM2 = f20.m(this.z);
        int iM3 = f20.m(this.A);
        int iM4 = f20.m(this.B);
        yv yvVar = this.d;
        ik ikVar = yvVar.h;
        if (ikVar.j) {
            ik ikVar2 = yvVar.i;
            if (ikVar2.j) {
                iM = ikVar.g;
                iM3 = ikVar2.g;
            }
        }
        dr0 dr0Var = this.e;
        ik ikVar3 = dr0Var.h;
        if (ikVar3.j) {
            ik ikVar4 = dr0Var.i;
            if (ikVar4.j) {
                iM2 = ikVar3.g;
                iM4 = ikVar4.g;
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void a(defpackage.f20 r59) {
        /*
            Method dump skipped, instruction units count: 1594
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mg.a(f20):void");
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(defpackage.f20 r28, boolean r29, boolean r30, boolean r31, boolean r32, defpackage.sj0 r33, defpackage.sj0 r34, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour r35, boolean r36, defpackage.ag r37, defpackage.ag r38, int r39, int r40, int r41, int r42, float r43, boolean r44, boolean r45, boolean r46, boolean r47, int r48, int r49, int r50, int r51, float r52, boolean r53) {
        /*
            Method dump skipped, instruction units count: 1014
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mg.c(f20, boolean, boolean, boolean, boolean, sj0, sj0, androidx.constraintlayout.solver.widgets.ConstraintWidget$DimensionBehaviour, boolean, ag, ag, int, int, int, int, float, boolean, boolean, boolean, boolean, int, int, int, int, float, boolean):void");
    }

    public final void d(ag agVar, ag agVar2, int i) {
        if (agVar.b == this) {
            e(agVar.c, agVar2.b, agVar2.c, i);
        }
    }

    public final void e(ConstraintAnchor$Type constraintAnchor$Type, mg mgVar, ConstraintAnchor$Type constraintAnchor$Type2, int i) {
        ConstraintAnchor$Type constraintAnchor$Type3;
        ConstraintAnchor$Type constraintAnchor$Type4;
        boolean z;
        ConstraintAnchor$Type constraintAnchor$Type5 = ConstraintAnchor$Type.CENTER;
        if (constraintAnchor$Type == constraintAnchor$Type5) {
            if (constraintAnchor$Type2 != constraintAnchor$Type5) {
                ConstraintAnchor$Type constraintAnchor$Type6 = ConstraintAnchor$Type.LEFT;
                if (constraintAnchor$Type2 == constraintAnchor$Type6 || constraintAnchor$Type2 == ConstraintAnchor$Type.RIGHT) {
                    e(constraintAnchor$Type6, mgVar, constraintAnchor$Type2, 0);
                    e(ConstraintAnchor$Type.RIGHT, mgVar, constraintAnchor$Type2, 0);
                    h(constraintAnchor$Type5).a(mgVar.h(constraintAnchor$Type2), 0);
                    return;
                }
                ConstraintAnchor$Type constraintAnchor$Type7 = ConstraintAnchor$Type.TOP;
                if (constraintAnchor$Type2 == constraintAnchor$Type7 || constraintAnchor$Type2 == ConstraintAnchor$Type.BOTTOM) {
                    e(constraintAnchor$Type7, mgVar, constraintAnchor$Type2, 0);
                    e(ConstraintAnchor$Type.BOTTOM, mgVar, constraintAnchor$Type2, 0);
                    h(constraintAnchor$Type5).a(mgVar.h(constraintAnchor$Type2), 0);
                    return;
                }
                return;
            }
            ConstraintAnchor$Type constraintAnchor$Type8 = ConstraintAnchor$Type.LEFT;
            ag agVarH = h(constraintAnchor$Type8);
            ConstraintAnchor$Type constraintAnchor$Type9 = ConstraintAnchor$Type.RIGHT;
            ag agVarH2 = h(constraintAnchor$Type9);
            ConstraintAnchor$Type constraintAnchor$Type10 = ConstraintAnchor$Type.TOP;
            ag agVarH3 = h(constraintAnchor$Type10);
            ConstraintAnchor$Type constraintAnchor$Type11 = ConstraintAnchor$Type.BOTTOM;
            ag agVarH4 = h(constraintAnchor$Type11);
            boolean z2 = true;
            if ((agVarH == null || !agVarH.f()) && (agVarH2 == null || !agVarH2.f())) {
                e(constraintAnchor$Type8, mgVar, constraintAnchor$Type8, 0);
                e(constraintAnchor$Type9, mgVar, constraintAnchor$Type9, 0);
                z = true;
            } else {
                z = false;
            }
            if ((agVarH3 == null || !agVarH3.f()) && (agVarH4 == null || !agVarH4.f())) {
                e(constraintAnchor$Type10, mgVar, constraintAnchor$Type10, 0);
                e(constraintAnchor$Type11, mgVar, constraintAnchor$Type11, 0);
            } else {
                z2 = false;
            }
            if (z && z2) {
                h(constraintAnchor$Type5).a(mgVar.h(constraintAnchor$Type5), 0);
                return;
            }
            if (z) {
                ConstraintAnchor$Type constraintAnchor$Type12 = ConstraintAnchor$Type.CENTER_X;
                h(constraintAnchor$Type12).a(mgVar.h(constraintAnchor$Type12), 0);
                return;
            } else {
                if (z2) {
                    ConstraintAnchor$Type constraintAnchor$Type13 = ConstraintAnchor$Type.CENTER_Y;
                    h(constraintAnchor$Type13).a(mgVar.h(constraintAnchor$Type13), 0);
                    return;
                }
                return;
            }
        }
        ConstraintAnchor$Type constraintAnchor$Type14 = ConstraintAnchor$Type.CENTER_X;
        if (constraintAnchor$Type == constraintAnchor$Type14 && (constraintAnchor$Type2 == (constraintAnchor$Type4 = ConstraintAnchor$Type.LEFT) || constraintAnchor$Type2 == ConstraintAnchor$Type.RIGHT)) {
            ag agVarH5 = h(constraintAnchor$Type4);
            ag agVarH6 = mgVar.h(constraintAnchor$Type2);
            ag agVarH7 = h(ConstraintAnchor$Type.RIGHT);
            agVarH5.a(agVarH6, 0);
            agVarH7.a(agVarH6, 0);
            h(constraintAnchor$Type14).a(agVarH6, 0);
            return;
        }
        ConstraintAnchor$Type constraintAnchor$Type15 = ConstraintAnchor$Type.CENTER_Y;
        if (constraintAnchor$Type == constraintAnchor$Type15 && (constraintAnchor$Type2 == (constraintAnchor$Type3 = ConstraintAnchor$Type.TOP) || constraintAnchor$Type2 == ConstraintAnchor$Type.BOTTOM)) {
            ag agVarH8 = mgVar.h(constraintAnchor$Type2);
            h(constraintAnchor$Type3).a(agVarH8, 0);
            h(ConstraintAnchor$Type.BOTTOM).a(agVarH8, 0);
            h(constraintAnchor$Type15).a(agVarH8, 0);
            return;
        }
        if (constraintAnchor$Type == constraintAnchor$Type14 && constraintAnchor$Type2 == constraintAnchor$Type14) {
            ConstraintAnchor$Type constraintAnchor$Type16 = ConstraintAnchor$Type.LEFT;
            h(constraintAnchor$Type16).a(mgVar.h(constraintAnchor$Type16), 0);
            ConstraintAnchor$Type constraintAnchor$Type17 = ConstraintAnchor$Type.RIGHT;
            h(constraintAnchor$Type17).a(mgVar.h(constraintAnchor$Type17), 0);
            h(constraintAnchor$Type14).a(mgVar.h(constraintAnchor$Type2), 0);
            return;
        }
        if (constraintAnchor$Type == constraintAnchor$Type15 && constraintAnchor$Type2 == constraintAnchor$Type15) {
            ConstraintAnchor$Type constraintAnchor$Type18 = ConstraintAnchor$Type.TOP;
            h(constraintAnchor$Type18).a(mgVar.h(constraintAnchor$Type18), 0);
            ConstraintAnchor$Type constraintAnchor$Type19 = ConstraintAnchor$Type.BOTTOM;
            h(constraintAnchor$Type19).a(mgVar.h(constraintAnchor$Type19), 0);
            h(constraintAnchor$Type15).a(mgVar.h(constraintAnchor$Type2), 0);
            return;
        }
        ag agVarH9 = h(constraintAnchor$Type);
        ag agVarH10 = mgVar.h(constraintAnchor$Type2);
        if (agVarH9.g(agVarH10)) {
            ConstraintAnchor$Type constraintAnchor$Type20 = ConstraintAnchor$Type.BASELINE;
            if (constraintAnchor$Type == constraintAnchor$Type20) {
                ag agVarH11 = h(ConstraintAnchor$Type.TOP);
                ag agVarH12 = h(ConstraintAnchor$Type.BOTTOM);
                if (agVarH11 != null) {
                    agVarH11.h();
                }
                if (agVarH12 != null) {
                    agVarH12.h();
                }
                i = 0;
            } else if (constraintAnchor$Type == ConstraintAnchor$Type.TOP || constraintAnchor$Type == ConstraintAnchor$Type.BOTTOM) {
                ag agVarH13 = h(constraintAnchor$Type20);
                if (agVarH13 != null) {
                    agVarH13.h();
                }
                ag agVarH14 = h(constraintAnchor$Type5);
                if (agVarH14.d != agVarH10) {
                    agVarH14.h();
                }
                ag agVarD = h(constraintAnchor$Type).d();
                ag agVarH15 = h(constraintAnchor$Type15);
                if (agVarH15.f()) {
                    agVarD.h();
                    agVarH15.h();
                }
            } else if (constraintAnchor$Type == ConstraintAnchor$Type.LEFT || constraintAnchor$Type == ConstraintAnchor$Type.RIGHT) {
                ag agVarH16 = h(constraintAnchor$Type5);
                if (agVarH16.d != agVarH10) {
                    agVarH16.h();
                }
                ag agVarD2 = h(constraintAnchor$Type).d();
                ag agVarH17 = h(constraintAnchor$Type14);
                if (agVarH17.f()) {
                    agVarD2.h();
                    agVarH17.h();
                }
            }
            agVarH9.a(agVarH10, i);
        }
    }

    public void f(mg mgVar, HashMap map) {
        this.h = mgVar.h;
        this.i = mgVar.i;
        this.j = mgVar.j;
        this.k = mgVar.k;
        int[] iArr = mgVar.l;
        int i = iArr[0];
        int[] iArr2 = this.l;
        iArr2[0] = i;
        iArr2[1] = iArr[1];
        this.m = mgVar.m;
        this.n = mgVar.n;
        this.p = mgVar.p;
        this.q = mgVar.q;
        this.r = mgVar.r;
        this.s = mgVar.s;
        this.t = mgVar.t;
        int[] iArr3 = mgVar.u;
        this.u = Arrays.copyOf(iArr3, iArr3.length);
        this.v = mgVar.v;
        this.w = mgVar.w;
        this.x = mgVar.x;
        this.y.h();
        this.z.h();
        this.A.h();
        this.B.h();
        this.C.h();
        this.D.h();
        this.E.h();
        this.F.h();
        this.J = (ConstraintWidget$DimensionBehaviour[]) Arrays.copyOf(this.J, 2);
        this.K = this.K == null ? null : (mg) map.get(mgVar.K);
        this.L = mgVar.L;
        this.M = mgVar.M;
        this.N = mgVar.N;
        this.O = mgVar.O;
        this.P = mgVar.P;
        this.Q = mgVar.Q;
        this.R = mgVar.R;
        this.S = mgVar.S;
        this.T = mgVar.T;
        this.U = mgVar.U;
        this.V = mgVar.V;
        this.W = mgVar.W;
        this.X = mgVar.X;
        this.Y = mgVar.Y;
        this.Z = mgVar.Z;
        this.a0 = mgVar.a0;
        float[] fArr = mgVar.b0;
        float f = fArr[0];
        float[] fArr2 = this.b0;
        fArr2[0] = f;
        fArr2[1] = fArr[1];
        mg[] mgVarArr = mgVar.c0;
        mg mgVar2 = mgVarArr[0];
        mg[] mgVarArr2 = this.c0;
        mgVarArr2[0] = mgVar2;
        mgVarArr2[1] = mgVarArr[1];
        mg[] mgVarArr3 = mgVar.d0;
        mg mgVar3 = mgVarArr3[0];
        mg[] mgVarArr4 = this.d0;
        mgVarArr4[0] = mgVar3;
        mgVarArr4[1] = mgVarArr3[1];
        mg mgVar4 = mgVar.e0;
        this.e0 = mgVar4 == null ? null : (mg) map.get(mgVar4);
        mg mgVar5 = mgVar.f0;
        this.f0 = mgVar5 != null ? (mg) map.get(mgVar5) : null;
    }

    public final void g(f20 f20Var) {
        f20Var.j(this.y);
        f20Var.j(this.z);
        f20Var.j(this.A);
        f20Var.j(this.B);
        if (this.R > 0) {
            f20Var.j(this.C);
        }
    }

    public ag h(ConstraintAnchor$Type constraintAnchor$Type) {
        switch (lg.a[constraintAnchor$Type.ordinal()]) {
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

    public final mg k(int i) {
        ag agVar;
        ag agVar2;
        if (i != 0) {
            if (i == 1 && (agVar2 = (agVar = this.B).d) != null && agVar2.d == agVar) {
                return agVar2.b;
            }
            return null;
        }
        ag agVar3 = this.A;
        ag agVar4 = agVar3.d;
        if (agVar4 == null || agVar4.d != agVar3) {
            return null;
        }
        return agVar4.b;
    }

    public final mg l(int i) {
        ag agVar;
        ag agVar2;
        if (i != 0) {
            if (i == 1 && (agVar2 = (agVar = this.z).d) != null && agVar2.d == agVar) {
                return agVar2.b;
            }
            return null;
        }
        ag agVar3 = this.y;
        ag agVar4 = agVar3.d;
        if (agVar4 == null || agVar4.d != agVar3) {
            return null;
        }
        return agVar4.b;
    }

    public final int m() {
        if (this.X == 8) {
            return 0;
        }
        return this.L;
    }

    public final int n() {
        mg mgVar = this.K;
        return (mgVar == null || !(mgVar instanceof ng)) ? this.P : ((ng) mgVar).m0 + this.P;
    }

    public final int o() {
        mg mgVar = this.K;
        return (mgVar == null || !(mgVar instanceof ng)) ? this.Q : ((ng) mgVar).n0 + this.Q;
    }

    public final void p(ConstraintAnchor$Type constraintAnchor$Type, mg mgVar, ConstraintAnchor$Type constraintAnchor$Type2, int i, int i2) {
        h(constraintAnchor$Type).b(mgVar.h(constraintAnchor$Type2), i, i2, true);
    }

    public final boolean q(int i) {
        ag agVar;
        ag agVar2;
        int i2 = i * 2;
        ag[] agVarArr = this.G;
        ag agVar3 = agVarArr[i2];
        ag agVar4 = agVar3.d;
        return (agVar4 == null || agVar4.d == agVar3 || (agVar2 = (agVar = agVarArr[i2 + 1]).d) == null || agVar2.d != agVar) ? false : true;
    }

    public final boolean r() {
        ag agVar = this.y;
        ag agVar2 = agVar.d;
        if (agVar2 != null && agVar2.d == agVar) {
            return true;
        }
        ag agVar3 = this.A;
        ag agVar4 = agVar3.d;
        return agVar4 != null && agVar4.d == agVar3;
    }

    public final boolean s() {
        ag agVar = this.z;
        ag agVar2 = agVar.d;
        if (agVar2 != null && agVar2.d == agVar) {
            return true;
        }
        ag agVar3 = this.B;
        ag agVar4 = agVar3.d;
        return agVar4 != null && agVar4.d == agVar3;
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
        String strK = ExtensionRequestData.EMPTY_VALUE;
        StringBuilder sbL = hk.l(ExtensionRequestData.EMPTY_VALUE);
        if (this.Y != null) {
            strK = hk.k(new StringBuilder("id: "), this.Y, " ");
        }
        sbL.append(strK);
        sbL.append("(");
        sbL.append(this.P);
        sbL.append(", ");
        sbL.append(this.Q);
        sbL.append(") - (");
        sbL.append(this.L);
        sbL.append(" x ");
        sbL.append(this.M);
        sbL.append(")");
        return sbL.toString();
    }

    public final void u() {
        mg mgVar = this.K;
        if (mgVar != null && (mgVar instanceof ng)) {
            ((ng) mgVar).getClass();
        }
        ArrayList arrayList = this.H;
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((ag) arrayList.get(i)).h();
        }
    }

    public void v(ba baVar) {
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
