package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import androidx.constraintlayout.widget.R$styleable;
import androidx.constraintlayout.widget.d;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import io.nats.client.BaseConsumeOptions;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class th {
    public static final SparseIntArray k0;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public float P;
    public float Q;
    public int R;
    public int S;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public float Z;
    public boolean a;
    public float a0;
    public boolean b;
    public int b0;
    public int c;
    public int c0;
    public int d;
    public int d0;
    public int e;
    public int[] e0;
    public int f;
    public String f0;
    public float g;
    public String g0;
    public int h;
    public boolean h0;
    public int i;
    public boolean i0;
    public int j;
    public boolean j0;
    public int k;
    public int l;
    public int m;
    public int n;
    public int o;
    public int p;
    public int q;
    public int r;
    public int s;
    public int t;
    public float u;
    public float v;
    public String w;
    public int x;
    public int y;
    public float z;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        k0 = sparseIntArray;
        sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toLeftOf, 24);
        sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_toRightOf, 25);
        sparseIntArray.append(R$styleable.Layout_layout_constraintRight_toLeftOf, 28);
        sparseIntArray.append(R$styleable.Layout_layout_constraintRight_toRightOf, 29);
        sparseIntArray.append(R$styleable.Layout_layout_constraintTop_toTopOf, 35);
        sparseIntArray.append(R$styleable.Layout_layout_constraintTop_toBottomOf, 34);
        sparseIntArray.append(R$styleable.Layout_layout_constraintBottom_toTopOf, 4);
        sparseIntArray.append(R$styleable.Layout_layout_constraintBottom_toBottomOf, 3);
        sparseIntArray.append(R$styleable.Layout_layout_constraintBaseline_toBaselineOf, 1);
        sparseIntArray.append(R$styleable.Layout_layout_editor_absoluteX, 6);
        sparseIntArray.append(R$styleable.Layout_layout_editor_absoluteY, 7);
        sparseIntArray.append(R$styleable.Layout_layout_constraintGuide_begin, 17);
        sparseIntArray.append(R$styleable.Layout_layout_constraintGuide_end, 18);
        sparseIntArray.append(R$styleable.Layout_layout_constraintGuide_percent, 19);
        sparseIntArray.append(R$styleable.Layout_android_orientation, 26);
        sparseIntArray.append(R$styleable.Layout_layout_constraintStart_toEndOf, 31);
        sparseIntArray.append(R$styleable.Layout_layout_constraintStart_toStartOf, 32);
        sparseIntArray.append(R$styleable.Layout_layout_constraintEnd_toStartOf, 10);
        sparseIntArray.append(R$styleable.Layout_layout_constraintEnd_toEndOf, 9);
        sparseIntArray.append(R$styleable.Layout_layout_goneMarginLeft, 13);
        sparseIntArray.append(R$styleable.Layout_layout_goneMarginTop, 16);
        sparseIntArray.append(R$styleable.Layout_layout_goneMarginRight, 14);
        sparseIntArray.append(R$styleable.Layout_layout_goneMarginBottom, 11);
        sparseIntArray.append(R$styleable.Layout_layout_goneMarginStart, 15);
        sparseIntArray.append(R$styleable.Layout_layout_goneMarginEnd, 12);
        sparseIntArray.append(R$styleable.Layout_layout_constraintVertical_weight, 38);
        sparseIntArray.append(R$styleable.Layout_layout_constraintHorizontal_weight, 37);
        sparseIntArray.append(R$styleable.Layout_layout_constraintHorizontal_chainStyle, 39);
        sparseIntArray.append(R$styleable.Layout_layout_constraintVertical_chainStyle, 40);
        sparseIntArray.append(R$styleable.Layout_layout_constraintHorizontal_bias, 20);
        sparseIntArray.append(R$styleable.Layout_layout_constraintVertical_bias, 36);
        sparseIntArray.append(R$styleable.Layout_layout_constraintDimensionRatio, 5);
        sparseIntArray.append(R$styleable.Layout_layout_constraintLeft_creator, 76);
        sparseIntArray.append(R$styleable.Layout_layout_constraintTop_creator, 76);
        sparseIntArray.append(R$styleable.Layout_layout_constraintRight_creator, 76);
        sparseIntArray.append(R$styleable.Layout_layout_constraintBottom_creator, 76);
        sparseIntArray.append(R$styleable.Layout_layout_constraintBaseline_creator, 76);
        sparseIntArray.append(R$styleable.Layout_android_layout_marginLeft, 23);
        sparseIntArray.append(R$styleable.Layout_android_layout_marginRight, 27);
        sparseIntArray.append(R$styleable.Layout_android_layout_marginStart, 30);
        sparseIntArray.append(R$styleable.Layout_android_layout_marginEnd, 8);
        sparseIntArray.append(R$styleable.Layout_android_layout_marginTop, 33);
        sparseIntArray.append(R$styleable.Layout_android_layout_marginBottom, 2);
        sparseIntArray.append(R$styleable.Layout_android_layout_width, 22);
        sparseIntArray.append(R$styleable.Layout_android_layout_height, 21);
        sparseIntArray.append(R$styleable.Layout_layout_constraintCircle, 61);
        sparseIntArray.append(R$styleable.Layout_layout_constraintCircleRadius, 62);
        sparseIntArray.append(R$styleable.Layout_layout_constraintCircleAngle, 63);
        sparseIntArray.append(R$styleable.Layout_layout_constraintWidth_percent, 69);
        sparseIntArray.append(R$styleable.Layout_layout_constraintHeight_percent, 70);
        sparseIntArray.append(R$styleable.Layout_chainUseRtl, 71);
        sparseIntArray.append(R$styleable.Layout_barrierDirection, 72);
        sparseIntArray.append(R$styleable.Layout_barrierMargin, 73);
        sparseIntArray.append(R$styleable.Layout_constraint_referenced_ids, 74);
        sparseIntArray.append(R$styleable.Layout_barrierAllowsGoneWidgets, 75);
    }

    public final void a(th thVar) {
        this.a = thVar.a;
        this.c = thVar.c;
        this.b = thVar.b;
        this.d = thVar.d;
        this.e = thVar.e;
        this.f = thVar.f;
        this.g = thVar.g;
        this.h = thVar.h;
        this.i = thVar.i;
        this.j = thVar.j;
        this.k = thVar.k;
        this.l = thVar.l;
        this.m = thVar.m;
        this.n = thVar.n;
        this.o = thVar.o;
        this.p = thVar.p;
        this.q = thVar.q;
        this.r = thVar.r;
        this.s = thVar.s;
        this.t = thVar.t;
        this.u = thVar.u;
        this.v = thVar.v;
        this.w = thVar.w;
        this.x = thVar.x;
        this.y = thVar.y;
        this.z = thVar.z;
        this.A = thVar.A;
        this.B = thVar.B;
        this.C = thVar.C;
        this.D = thVar.D;
        this.E = thVar.E;
        this.F = thVar.F;
        this.G = thVar.G;
        this.H = thVar.H;
        this.I = thVar.I;
        this.J = thVar.J;
        this.K = thVar.K;
        this.L = thVar.L;
        this.M = thVar.M;
        this.N = thVar.N;
        this.O = thVar.O;
        this.P = thVar.P;
        this.Q = thVar.Q;
        this.R = thVar.R;
        this.S = thVar.S;
        this.T = thVar.T;
        this.U = thVar.U;
        this.V = thVar.V;
        this.W = thVar.W;
        this.X = thVar.X;
        this.Y = thVar.Y;
        this.Z = thVar.Z;
        this.a0 = thVar.a0;
        this.b0 = thVar.b0;
        this.c0 = thVar.c0;
        this.d0 = thVar.d0;
        this.g0 = thVar.g0;
        int[] iArr = thVar.e0;
        if (iArr != null) {
            this.e0 = Arrays.copyOf(iArr, iArr.length);
        } else {
            this.e0 = null;
        }
        this.f0 = thVar.f0;
        this.h0 = thVar.h0;
        this.i0 = thVar.i0;
        this.j0 = thVar.j0;
    }

    public final void b(Context context, AttributeSet attributeSet) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Layout);
        this.b = true;
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            SparseIntArray sparseIntArray = k0;
            int i2 = sparseIntArray.get(index);
            if (i2 == 80) {
                this.h0 = typedArrayObtainStyledAttributes.getBoolean(index, this.h0);
            } else if (i2 != 81) {
                switch (i2) {
                    case 1:
                        this.p = d.j(typedArrayObtainStyledAttributes, index, this.p);
                        break;
                    case 2:
                        this.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.G);
                        break;
                    case 3:
                        this.o = d.j(typedArrayObtainStyledAttributes, index, this.o);
                        break;
                    case 4:
                        this.n = d.j(typedArrayObtainStyledAttributes, index, this.n);
                        break;
                    case 5:
                        this.w = typedArrayObtainStyledAttributes.getString(index);
                        break;
                    case 6:
                        this.A = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.A);
                        break;
                    case 7:
                        this.B = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.B);
                        break;
                    case 8:
                        this.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.H);
                        break;
                    case WorkDatabaseVersions.VERSION_9 /* 9 */:
                        this.t = d.j(typedArrayObtainStyledAttributes, index, this.t);
                        break;
                    case 10:
                        this.s = d.j(typedArrayObtainStyledAttributes, index, this.s);
                        break;
                    case WorkDatabaseVersions.VERSION_11 /* 11 */:
                        this.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.M);
                        break;
                    case WorkDatabaseVersions.VERSION_12 /* 12 */:
                        this.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.N);
                        break;
                    case WorkDatabaseVersions.VERSION_13 /* 13 */:
                        this.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.J);
                        break;
                    case WorkDatabaseVersions.VERSION_14 /* 14 */:
                        this.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.L);
                        break;
                    case WorkDatabaseVersions.VERSION_15 /* 15 */:
                        this.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.O);
                        break;
                    case 16:
                        this.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.K);
                        break;
                    case 17:
                        this.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.e);
                        break;
                    case 18:
                        this.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, this.f);
                        break;
                    case 19:
                        this.g = typedArrayObtainStyledAttributes.getFloat(index, this.g);
                        break;
                    case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                        this.u = typedArrayObtainStyledAttributes.getFloat(index, this.u);
                        break;
                    case 21:
                        this.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.d);
                        break;
                    case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                        this.c = typedArrayObtainStyledAttributes.getLayoutDimension(index, this.c);
                        break;
                    case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                        this.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.D);
                        break;
                    case 24:
                        this.h = d.j(typedArrayObtainStyledAttributes, index, this.h);
                        break;
                    case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                        this.i = d.j(typedArrayObtainStyledAttributes, index, this.i);
                        break;
                    case 26:
                        this.C = typedArrayObtainStyledAttributes.getInt(index, this.C);
                        break;
                    case 27:
                        this.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.E);
                        break;
                    case 28:
                        this.j = d.j(typedArrayObtainStyledAttributes, index, this.j);
                        break;
                    case 29:
                        this.k = d.j(typedArrayObtainStyledAttributes, index, this.k);
                        break;
                    case 30:
                        this.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.I);
                        break;
                    case 31:
                        this.q = d.j(typedArrayObtainStyledAttributes, index, this.q);
                        break;
                    case 32:
                        this.r = d.j(typedArrayObtainStyledAttributes, index, this.r);
                        break;
                    case 33:
                        this.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.F);
                        break;
                    case 34:
                        this.m = d.j(typedArrayObtainStyledAttributes, index, this.m);
                        break;
                    case 35:
                        this.l = d.j(typedArrayObtainStyledAttributes, index, this.l);
                        break;
                    case 36:
                        this.v = typedArrayObtainStyledAttributes.getFloat(index, this.v);
                        break;
                    case 37:
                        this.Q = typedArrayObtainStyledAttributes.getFloat(index, this.Q);
                        break;
                    case 38:
                        this.P = typedArrayObtainStyledAttributes.getFloat(index, this.P);
                        break;
                    case 39:
                        this.R = typedArrayObtainStyledAttributes.getInt(index, this.R);
                        break;
                    case 40:
                        this.S = typedArrayObtainStyledAttributes.getInt(index, this.S);
                        break;
                    default:
                        switch (i2) {
                            case 54:
                                this.T = typedArrayObtainStyledAttributes.getInt(index, this.T);
                                break;
                            case 55:
                                this.U = typedArrayObtainStyledAttributes.getInt(index, this.U);
                                break;
                            case 56:
                                this.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.V);
                                break;
                            case 57:
                                this.W = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.W);
                                break;
                            case 58:
                                this.X = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.X);
                                break;
                            case 59:
                                this.Y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.Y);
                                break;
                            default:
                                switch (i2) {
                                    case 61:
                                        this.x = d.j(typedArrayObtainStyledAttributes, index, this.x);
                                        break;
                                    case 62:
                                        this.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.y);
                                        break;
                                    case 63:
                                        this.z = typedArrayObtainStyledAttributes.getFloat(index, this.z);
                                        break;
                                    default:
                                        switch (i2) {
                                            case 69:
                                                this.Z = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                break;
                                            case 70:
                                                this.a0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                                                break;
                                            case 71:
                                                Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                                                break;
                                            case 72:
                                                this.b0 = typedArrayObtainStyledAttributes.getInt(index, this.b0);
                                                break;
                                            case 73:
                                                this.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, this.c0);
                                                break;
                                            case 74:
                                                this.f0 = typedArrayObtainStyledAttributes.getString(index);
                                                break;
                                            case 75:
                                                this.j0 = typedArrayObtainStyledAttributes.getBoolean(index, this.j0);
                                                break;
                                            case 76:
                                                Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                                                break;
                                            case 77:
                                                this.g0 = typedArrayObtainStyledAttributes.getString(index);
                                                break;
                                            default:
                                                Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                                                break;
                                        }
                                        break;
                                }
                                break;
                        }
                        break;
                }
            } else {
                this.i0 = typedArrayObtainStyledAttributes.getBoolean(index, this.i0);
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }
}
