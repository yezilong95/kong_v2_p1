package defpackage;

import android.util.Log;
import android.util.SparseArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class jf0 {
    public float a = Float.NaN;
    public float b = Float.NaN;
    public int c = -1;
    public int d = -1;
    public final /* synthetic */ MotionLayout e;

    public jf0(MotionLayout motionLayout) {
        this.e = motionLayout;
    }

    public final void a() {
        int i = this.c;
        MotionLayout motionLayout = this.e;
        if (i != -1 || this.d != -1) {
            lf0 lf0Var = lf0.F;
            if (i == -1) {
                motionLayout.B(this.d);
            } else {
                int i2 = this.d;
                if (i2 == -1) {
                    motionLayout.setState(lf0Var);
                    motionLayout.b0 = i;
                    motionLayout.a0 = -1;
                    motionLayout.c0 = -1;
                    sh shVar = motionLayout.O;
                    if (shVar != null) {
                        float f = -1;
                        int i3 = shVar.a;
                        SparseArray sparseArray = (SparseArray) shVar.d;
                        int i4 = 0;
                        ConstraintLayout constraintLayout = (ConstraintLayout) shVar.c;
                        if (i3 == i) {
                            qh qhVar = i == -1 ? (qh) sparseArray.valueAt(0) : (qh) sparseArray.get(i3);
                            int i5 = shVar.b;
                            if (i5 == -1 || !((rh) qhVar.b.get(i5)).a(f, f)) {
                                while (true) {
                                    ArrayList arrayList = qhVar.b;
                                    if (i4 >= arrayList.size()) {
                                        i4 = -1;
                                        break;
                                    } else if (((rh) arrayList.get(i4)).a(f, f)) {
                                        break;
                                    } else {
                                        i4++;
                                    }
                                }
                                if (shVar.b != i4) {
                                    ArrayList arrayList2 = qhVar.b;
                                    d dVar = i4 == -1 ? null : ((rh) arrayList2.get(i4)).f;
                                    if (i4 != -1) {
                                        int i6 = ((rh) arrayList2.get(i4)).e;
                                    }
                                    if (dVar != null) {
                                        shVar.b = i4;
                                        dVar.b(constraintLayout);
                                    }
                                }
                            }
                        } else {
                            shVar.a = i;
                            qh qhVar2 = (qh) sparseArray.get(i);
                            while (true) {
                                ArrayList arrayList3 = qhVar2.b;
                                if (i4 >= arrayList3.size()) {
                                    i4 = -1;
                                    break;
                                } else if (((rh) arrayList3.get(i4)).a(f, f)) {
                                    break;
                                } else {
                                    i4++;
                                }
                            }
                            ArrayList arrayList4 = qhVar2.b;
                            d dVar2 = i4 == -1 ? qhVar2.d : ((rh) arrayList4.get(i4)).f;
                            if (i4 != -1) {
                                int i7 = ((rh) arrayList4.get(i4)).e;
                            }
                            if (dVar2 == null) {
                                Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i + ", dim =-1.0, -1.0");
                            } else {
                                shVar.b = i4;
                                dVar2.b(constraintLayout);
                            }
                        }
                    } else {
                        qf0 qf0Var = motionLayout.U;
                        if (qf0Var != null) {
                            qf0Var.b(i).b(motionLayout);
                        }
                    }
                } else {
                    motionLayout.z(i, i2);
                }
            }
            motionLayout.setState(lf0Var);
        }
        if (Float.isNaN(this.b)) {
            if (Float.isNaN(this.a)) {
                return;
            }
            motionLayout.setProgress(this.a);
        } else {
            motionLayout.y(this.a, this.b);
            this.a = Float.NaN;
            this.b = Float.NaN;
            this.c = -1;
            this.d = -1;
        }
    }
}
