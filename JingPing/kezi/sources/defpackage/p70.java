package defpackage;

import android.util.Log;
import android.util.SparseArray;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.d;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class p70 {
    public float a = Float.NaN;
    public float b = Float.NaN;
    public int c = -1;
    public int d = -1;
    public final /* synthetic */ MotionLayout e;

    public p70(MotionLayout motionLayout) {
        this.e = motionLayout;
    }

    public final void a() {
        int i = this.c;
        MotionLayout motionLayout = this.e;
        if (i != -1 || this.d != -1) {
            r70 r70Var = r70.c;
            if (i == -1) {
                motionLayout.B(this.d);
            } else {
                int i2 = this.d;
                if (i2 == -1) {
                    motionLayout.setState(r70Var);
                    motionLayout.v = i;
                    motionLayout.u = -1;
                    motionLayout.w = -1;
                    gg ggVar = motionLayout.l;
                    if (ggVar != null) {
                        float f = -1;
                        int i3 = ggVar.a;
                        SparseArray sparseArray = (SparseArray) ggVar.d;
                        int i4 = 0;
                        ConstraintLayout constraintLayout = (ConstraintLayout) ggVar.c;
                        if (i3 == i) {
                            eg egVar = i == -1 ? (eg) sparseArray.valueAt(0) : (eg) sparseArray.get(i3);
                            int i5 = ggVar.b;
                            if (i5 == -1 || !((fg) egVar.b.get(i5)).a(f, f)) {
                                while (true) {
                                    ArrayList arrayList = egVar.b;
                                    if (i4 >= arrayList.size()) {
                                        i4 = -1;
                                        break;
                                    } else if (((fg) arrayList.get(i4)).a(f, f)) {
                                        break;
                                    } else {
                                        i4++;
                                    }
                                }
                                if (ggVar.b != i4) {
                                    ArrayList arrayList2 = egVar.b;
                                    d dVar = i4 == -1 ? null : ((fg) arrayList2.get(i4)).f;
                                    if (i4 != -1) {
                                        int i6 = ((fg) arrayList2.get(i4)).e;
                                    }
                                    if (dVar != null) {
                                        ggVar.b = i4;
                                        dVar.b(constraintLayout);
                                    }
                                }
                            }
                        } else {
                            ggVar.a = i;
                            eg egVar2 = (eg) sparseArray.get(i);
                            while (true) {
                                ArrayList arrayList3 = egVar2.b;
                                if (i4 >= arrayList3.size()) {
                                    i4 = -1;
                                    break;
                                } else if (((fg) arrayList3.get(i4)).a(f, f)) {
                                    break;
                                } else {
                                    i4++;
                                }
                            }
                            ArrayList arrayList4 = egVar2.b;
                            d dVar2 = i4 == -1 ? egVar2.d : ((fg) arrayList4.get(i4)).f;
                            if (i4 != -1) {
                                int i7 = ((fg) arrayList4.get(i4)).e;
                            }
                            if (dVar2 == null) {
                                Log.v("ConstraintLayoutStates", "NO Constraint set found ! id=" + i + ", dim =-1.0, -1.0");
                            } else {
                                ggVar.b = i4;
                                dVar2.b(constraintLayout);
                            }
                        }
                    } else {
                        w70 w70Var = motionLayout.r;
                        if (w70Var != null) {
                            w70Var.b(i).b(motionLayout);
                        }
                    }
                } else {
                    motionLayout.z(i, i2);
                }
            }
            motionLayout.setState(r70Var);
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
