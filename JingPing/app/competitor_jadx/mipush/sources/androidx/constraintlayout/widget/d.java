package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import defpackage.oh;
import defpackage.t40;
import defpackage.th;
import defpackage.uh;
import defpackage.vh;
import defpackage.vr;
import defpackage.wh;
import io.nats.client.BaseConsumeOptions;
import io.nats.client.Options;
import io.nats.client.support.ApiConstants;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.java_websocket.WebSocketImpl;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static final int[] d = {0, 4, 8};
    public static final SparseIntArray e;
    public final HashMap a = new HashMap();
    public boolean b = true;
    public final HashMap c = new HashMap();

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        e = sparseIntArray;
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toLeftOf, 25);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_toRightOf, 26);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintRight_toLeftOf, 29);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintRight_toRightOf, 30);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTop_toTopOf, 36);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTop_toBottomOf, 35);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBottom_toTopOf, 4);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBottom_toBottomOf, 3);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBaseline_toBaselineOf, 1);
        sparseIntArray.append(R$styleable.Constraint_layout_editor_absoluteX, 6);
        sparseIntArray.append(R$styleable.Constraint_layout_editor_absoluteY, 7);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintGuide_begin, 17);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintGuide_end, 18);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintGuide_percent, 19);
        sparseIntArray.append(R$styleable.Constraint_android_orientation, 27);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintStart_toEndOf, 32);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintStart_toStartOf, 33);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintEnd_toStartOf, 10);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintEnd_toEndOf, 9);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginLeft, 13);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginTop, 16);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginRight, 14);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginBottom, 11);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginStart, 15);
        sparseIntArray.append(R$styleable.Constraint_layout_goneMarginEnd, 12);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintVertical_weight, 40);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHorizontal_weight, 39);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHorizontal_chainStyle, 41);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintVertical_chainStyle, 42);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHorizontal_bias, 20);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintVertical_bias, 37);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintDimensionRatio, 5);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintLeft_creator, 82);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTop_creator, 82);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintRight_creator, 82);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBottom_creator, 82);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintBaseline_creator, 82);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginLeft, 24);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginRight, 28);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginStart, 31);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginEnd, 8);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginTop, 34);
        sparseIntArray.append(R$styleable.Constraint_android_layout_marginBottom, 2);
        sparseIntArray.append(R$styleable.Constraint_android_layout_width, 23);
        sparseIntArray.append(R$styleable.Constraint_android_layout_height, 21);
        sparseIntArray.append(R$styleable.Constraint_android_visibility, 22);
        sparseIntArray.append(R$styleable.Constraint_android_alpha, 43);
        sparseIntArray.append(R$styleable.Constraint_android_elevation, 44);
        sparseIntArray.append(R$styleable.Constraint_android_rotationX, 45);
        sparseIntArray.append(R$styleable.Constraint_android_rotationY, 46);
        sparseIntArray.append(R$styleable.Constraint_android_rotation, 60);
        sparseIntArray.append(R$styleable.Constraint_android_scaleX, 47);
        sparseIntArray.append(R$styleable.Constraint_android_scaleY, 48);
        sparseIntArray.append(R$styleable.Constraint_android_transformPivotX, 49);
        sparseIntArray.append(R$styleable.Constraint_android_transformPivotY, 50);
        sparseIntArray.append(R$styleable.Constraint_android_translationX, 51);
        sparseIntArray.append(R$styleable.Constraint_android_translationY, 52);
        sparseIntArray.append(R$styleable.Constraint_android_translationZ, 53);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_default, 54);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_default, 55);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_max, 56);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_max, 57);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_min, 58);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_min, 59);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintCircle, 61);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintCircleRadius, 62);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintCircleAngle, 63);
        sparseIntArray.append(R$styleable.Constraint_animate_relativeTo, 64);
        sparseIntArray.append(R$styleable.Constraint_transitionEasing, 65);
        sparseIntArray.append(R$styleable.Constraint_drawPath, 66);
        sparseIntArray.append(R$styleable.Constraint_transitionPathRotate, 67);
        sparseIntArray.append(R$styleable.Constraint_motionStagger, 79);
        sparseIntArray.append(R$styleable.Constraint_android_id, 38);
        sparseIntArray.append(R$styleable.Constraint_motionProgress, 68);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintWidth_percent, 69);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintHeight_percent, 70);
        sparseIntArray.append(R$styleable.Constraint_chainUseRtl, 71);
        sparseIntArray.append(R$styleable.Constraint_barrierDirection, 72);
        sparseIntArray.append(R$styleable.Constraint_barrierMargin, 73);
        sparseIntArray.append(R$styleable.Constraint_constraint_referenced_ids, 74);
        sparseIntArray.append(R$styleable.Constraint_barrierAllowsGoneWidgets, 75);
        sparseIntArray.append(R$styleable.Constraint_pathMotionArc, 76);
        sparseIntArray.append(R$styleable.Constraint_layout_constraintTag, 77);
        sparseIntArray.append(R$styleable.Constraint_visibilityMode, 78);
        sparseIntArray.append(R$styleable.Constraint_layout_constrainedWidth, 80);
        sparseIntArray.append(R$styleable.Constraint_layout_constrainedHeight, 81);
    }

    public static int[] e(Barrier barrier, String str) {
        int iIntValue;
        String[] strArrSplit = str.split(",");
        Context context = barrier.getContext();
        int[] iArr = new int[strArrSplit.length];
        int i = 0;
        int i2 = 0;
        while (i < strArrSplit.length) {
            String strTrim = strArrSplit[i].trim();
            Object obj = null;
            try {
                iIntValue = R$id.class.getField(strTrim).getInt(null);
            } catch (Exception unused) {
                iIntValue = 0;
            }
            if (iIntValue == 0) {
                iIntValue = context.getResources().getIdentifier(strTrim, ApiConstants.ID, context.getPackageName());
            }
            if (iIntValue == 0 && barrier.isInEditMode() && (barrier.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) barrier.getParent();
                if (strTrim != null) {
                    HashMap map = constraintLayout.Q;
                    if (map != null && map.containsKey(strTrim)) {
                        obj = constraintLayout.Q.get(strTrim);
                    }
                } else {
                    constraintLayout.getClass();
                }
                if (obj != null && (obj instanceof Integer)) {
                    iIntValue = ((Integer) obj).intValue();
                }
            }
            iArr[i2] = iIntValue;
            i++;
            i2++;
        }
        return i2 != strArrSplit.length ? Arrays.copyOf(iArr, i2) : iArr;
    }

    public static c f(Context context, AttributeSet attributeSet) {
        c cVar = new c();
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Constraint);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            int i2 = R$styleable.Constraint_android_id;
            vh vhVar = cVar.b;
            uh uhVar = cVar.c;
            wh whVar = cVar.e;
            th thVar = cVar.d;
            if (index != i2 && R$styleable.Constraint_android_layout_marginStart != index && R$styleable.Constraint_android_layout_marginEnd != index) {
                uhVar.a = true;
                thVar.b = true;
                vhVar.a = true;
                whVar.a = true;
            }
            SparseIntArray sparseIntArray = e;
            switch (sparseIntArray.get(index)) {
                case 1:
                    thVar.p = j(typedArrayObtainStyledAttributes, index, thVar.p);
                    break;
                case 2:
                    thVar.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.G);
                    break;
                case 3:
                    thVar.o = j(typedArrayObtainStyledAttributes, index, thVar.o);
                    break;
                case 4:
                    thVar.n = j(typedArrayObtainStyledAttributes, index, thVar.n);
                    break;
                case 5:
                    thVar.w = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 6:
                    thVar.A = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, thVar.A);
                    break;
                case 7:
                    thVar.B = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, thVar.B);
                    break;
                case 8:
                    thVar.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.H);
                    break;
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    thVar.t = j(typedArrayObtainStyledAttributes, index, thVar.t);
                    break;
                case 10:
                    thVar.s = j(typedArrayObtainStyledAttributes, index, thVar.s);
                    break;
                case WorkDatabaseVersions.VERSION_11 /* 11 */:
                    thVar.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.M);
                    break;
                case WorkDatabaseVersions.VERSION_12 /* 12 */:
                    thVar.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.N);
                    break;
                case WorkDatabaseVersions.VERSION_13 /* 13 */:
                    thVar.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.J);
                    break;
                case WorkDatabaseVersions.VERSION_14 /* 14 */:
                    thVar.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.L);
                    break;
                case WorkDatabaseVersions.VERSION_15 /* 15 */:
                    thVar.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.O);
                    break;
                case 16:
                    thVar.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.K);
                    break;
                case 17:
                    thVar.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, thVar.e);
                    break;
                case 18:
                    thVar.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, thVar.f);
                    break;
                case 19:
                    thVar.g = typedArrayObtainStyledAttributes.getFloat(index, thVar.g);
                    break;
                case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                    thVar.u = typedArrayObtainStyledAttributes.getFloat(index, thVar.u);
                    break;
                case 21:
                    thVar.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, thVar.d);
                    break;
                case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, vhVar.b);
                    vhVar.b = i3;
                    vhVar.b = d[i3];
                    break;
                case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                    thVar.c = typedArrayObtainStyledAttributes.getLayoutDimension(index, thVar.c);
                    break;
                case 24:
                    thVar.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.D);
                    break;
                case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                    thVar.h = j(typedArrayObtainStyledAttributes, index, thVar.h);
                    break;
                case 26:
                    thVar.i = j(typedArrayObtainStyledAttributes, index, thVar.i);
                    break;
                case 27:
                    thVar.C = typedArrayObtainStyledAttributes.getInt(index, thVar.C);
                    break;
                case 28:
                    thVar.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.E);
                    break;
                case 29:
                    thVar.j = j(typedArrayObtainStyledAttributes, index, thVar.j);
                    break;
                case 30:
                    thVar.k = j(typedArrayObtainStyledAttributes, index, thVar.k);
                    break;
                case 31:
                    thVar.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.I);
                    break;
                case 32:
                    thVar.q = j(typedArrayObtainStyledAttributes, index, thVar.q);
                    break;
                case 33:
                    thVar.r = j(typedArrayObtainStyledAttributes, index, thVar.r);
                    break;
                case 34:
                    thVar.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.F);
                    break;
                case 35:
                    thVar.m = j(typedArrayObtainStyledAttributes, index, thVar.m);
                    break;
                case 36:
                    thVar.l = j(typedArrayObtainStyledAttributes, index, thVar.l);
                    break;
                case 37:
                    thVar.v = typedArrayObtainStyledAttributes.getFloat(index, thVar.v);
                    break;
                case 38:
                    cVar.a = typedArrayObtainStyledAttributes.getResourceId(index, cVar.a);
                    break;
                case 39:
                    thVar.Q = typedArrayObtainStyledAttributes.getFloat(index, thVar.Q);
                    break;
                case 40:
                    thVar.P = typedArrayObtainStyledAttributes.getFloat(index, thVar.P);
                    break;
                case 41:
                    thVar.R = typedArrayObtainStyledAttributes.getInt(index, thVar.R);
                    break;
                case 42:
                    thVar.S = typedArrayObtainStyledAttributes.getInt(index, thVar.S);
                    break;
                case 43:
                    vhVar.d = typedArrayObtainStyledAttributes.getFloat(index, vhVar.d);
                    break;
                case 44:
                    whVar.l = true;
                    whVar.m = typedArrayObtainStyledAttributes.getDimension(index, whVar.m);
                    break;
                case 45:
                    whVar.c = typedArrayObtainStyledAttributes.getFloat(index, whVar.c);
                    break;
                case 46:
                    whVar.d = typedArrayObtainStyledAttributes.getFloat(index, whVar.d);
                    break;
                case 47:
                    whVar.e = typedArrayObtainStyledAttributes.getFloat(index, whVar.e);
                    break;
                case 48:
                    whVar.f = typedArrayObtainStyledAttributes.getFloat(index, whVar.f);
                    break;
                case 49:
                    whVar.g = typedArrayObtainStyledAttributes.getDimension(index, whVar.g);
                    break;
                case 50:
                    whVar.h = typedArrayObtainStyledAttributes.getDimension(index, whVar.h);
                    break;
                case 51:
                    whVar.i = typedArrayObtainStyledAttributes.getDimension(index, whVar.i);
                    break;
                case 52:
                    whVar.j = typedArrayObtainStyledAttributes.getDimension(index, whVar.j);
                    break;
                case 53:
                    whVar.k = typedArrayObtainStyledAttributes.getDimension(index, whVar.k);
                    break;
                case 54:
                    thVar.T = typedArrayObtainStyledAttributes.getInt(index, thVar.T);
                    break;
                case 55:
                    thVar.U = typedArrayObtainStyledAttributes.getInt(index, thVar.U);
                    break;
                case 56:
                    thVar.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.V);
                    break;
                case 57:
                    thVar.W = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.W);
                    break;
                case 58:
                    thVar.X = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.X);
                    break;
                case 59:
                    thVar.Y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.Y);
                    break;
                case Options.DEFAULT_MAX_RECONNECT /* 60 */:
                    whVar.b = typedArrayObtainStyledAttributes.getFloat(index, whVar.b);
                    break;
                case 61:
                    thVar.x = j(typedArrayObtainStyledAttributes, index, thVar.x);
                    break;
                case 62:
                    thVar.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.y);
                    break;
                case 63:
                    thVar.z = typedArrayObtainStyledAttributes.getFloat(index, thVar.z);
                    break;
                case 64:
                    uhVar.b = j(typedArrayObtainStyledAttributes, index, uhVar.b);
                    break;
                case 65:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        uhVar.c = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        uhVar.c = vr.d[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    uhVar.e = typedArrayObtainStyledAttributes.getInt(index, 0);
                    break;
                case 67:
                    uhVar.g = typedArrayObtainStyledAttributes.getFloat(index, uhVar.g);
                    break;
                case 68:
                    vhVar.e = typedArrayObtainStyledAttributes.getFloat(index, vhVar.e);
                    break;
                case 69:
                    thVar.Z = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                    break;
                case 70:
                    thVar.a0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    thVar.b0 = typedArrayObtainStyledAttributes.getInt(index, thVar.b0);
                    break;
                case 73:
                    thVar.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, thVar.c0);
                    break;
                case 74:
                    thVar.f0 = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 75:
                    thVar.j0 = typedArrayObtainStyledAttributes.getBoolean(index, thVar.j0);
                    break;
                case 76:
                    uhVar.d = typedArrayObtainStyledAttributes.getInt(index, uhVar.d);
                    break;
                case 77:
                    thVar.g0 = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 78:
                    vhVar.c = typedArrayObtainStyledAttributes.getInt(index, vhVar.c);
                    break;
                case 79:
                    uhVar.f = typedArrayObtainStyledAttributes.getFloat(index, uhVar.f);
                    break;
                case WebSocketImpl.DEFAULT_PORT /* 80 */:
                    thVar.h0 = typedArrayObtainStyledAttributes.getBoolean(index, thVar.h0);
                    break;
                case 81:
                    thVar.i0 = typedArrayObtainStyledAttributes.getBoolean(index, thVar.i0);
                    break;
                case 82:
                    Log.w("ConstraintSet", "unused attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                    break;
                default:
                    Log.w("ConstraintSet", "Unknown attribute 0x" + Integer.toHexString(index) + "   " + sparseIntArray.get(index));
                    break;
            }
        }
        typedArrayObtainStyledAttributes.recycle();
        return cVar;
    }

    public static int j(TypedArray typedArray, int i, int i2) {
        int resourceId = typedArray.getResourceId(i, i2);
        return resourceId == -1 ? typedArray.getInt(i, -1) : resourceId;
    }

    public final void a(MotionLayout motionLayout) {
        int childCount = motionLayout.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = motionLayout.getChildAt(i);
            int id = childAt.getId();
            HashMap map = this.c;
            if (!map.containsKey(Integer.valueOf(id))) {
                Log.v("ConstraintSet", "id unknown " + t40.g0(childAt));
            } else {
                if (this.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (map.containsKey(Integer.valueOf(id))) {
                    oh.f(childAt, ((c) map.get(Integer.valueOf(id))).f);
                }
            }
        }
    }

    public final void b(ConstraintLayout constraintLayout) {
        c(constraintLayout);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    public final void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashMap map = this.c;
        HashSet<Integer> hashSet = new HashSet(map.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!map.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + t40.g0(childAt));
            } else {
                if (this.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (id != -1) {
                    if (map.containsKey(Integer.valueOf(id))) {
                        hashSet.remove(Integer.valueOf(id));
                        c cVar = (c) map.get(Integer.valueOf(id));
                        if (childAt instanceof Barrier) {
                            cVar.d.d0 = 1;
                        }
                        int i2 = cVar.d.d0;
                        if (i2 != -1 && i2 == 1) {
                            Barrier barrier = (Barrier) childAt;
                            barrier.setId(id);
                            th thVar = cVar.d;
                            barrier.setType(thVar.b0);
                            barrier.setMargin(thVar.c0);
                            barrier.setAllowsGoneWidget(thVar.j0);
                            int[] iArr = thVar.e0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = thVar.f0;
                                if (str != null) {
                                    int[] iArrE = e(barrier, str);
                                    thVar.e0 = iArrE;
                                    barrier.setReferencedIds(iArrE);
                                }
                            }
                        }
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                        layoutParams.a();
                        cVar.a(layoutParams);
                        oh.f(childAt, cVar.f);
                        childAt.setLayoutParams(layoutParams);
                        vh vhVar = cVar.b;
                        if (vhVar.c == 0) {
                            childAt.setVisibility(vhVar.b);
                        }
                        childAt.setAlpha(vhVar.d);
                        wh whVar = cVar.e;
                        childAt.setRotation(whVar.b);
                        childAt.setRotationX(whVar.c);
                        childAt.setRotationY(whVar.d);
                        childAt.setScaleX(whVar.e);
                        childAt.setScaleY(whVar.f);
                        if (!Float.isNaN(whVar.g)) {
                            childAt.setPivotX(whVar.g);
                        }
                        if (!Float.isNaN(whVar.h)) {
                            childAt.setPivotY(whVar.h);
                        }
                        childAt.setTranslationX(whVar.i);
                        childAt.setTranslationY(whVar.j);
                        childAt.setTranslationZ(whVar.k);
                        if (whVar.l) {
                            childAt.setElevation(whVar.m);
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            c cVar2 = (c) map.get(num);
            th thVar2 = cVar2.d;
            int i3 = thVar2.d0;
            if (i3 != -1 && i3 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                int[] iArr2 = thVar2.e0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = thVar2.f0;
                    if (str2 != null) {
                        int[] iArrE2 = e(barrier2, str2);
                        thVar2.e0 = iArrE2;
                        barrier2.setReferencedIds(iArrE2);
                    }
                }
                barrier2.setType(thVar2.b0);
                barrier2.setMargin(thVar2.c0);
                int i4 = ConstraintLayout.T;
                ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams();
                barrier2.l();
                cVar2.a(layoutParams2);
                constraintLayout.addView(barrier2, layoutParams2);
            }
            if (thVar2.a) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                int i5 = ConstraintLayout.T;
                ConstraintLayout.LayoutParams layoutParams3 = new ConstraintLayout.LayoutParams();
                cVar2.a(layoutParams3);
                constraintLayout.addView(guideline, layoutParams3);
            }
        }
    }

    public final void d(ConstraintLayout constraintLayout) {
        int i;
        d dVar = this;
        int childCount = constraintLayout.getChildCount();
        HashMap map = dVar.c;
        map.clear();
        int i2 = 0;
        while (i2 < childCount) {
            View childAt = constraintLayout.getChildAt(i2);
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
            int id = childAt.getId();
            if (dVar.b && id == -1) {
                throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
            }
            if (!map.containsKey(Integer.valueOf(id))) {
                map.put(Integer.valueOf(id), new c());
            }
            c cVar = (c) map.get(Integer.valueOf(id));
            HashMap map2 = dVar.a;
            HashMap map3 = new HashMap();
            Class<?> cls = childAt.getClass();
            for (String str : map2.keySet()) {
                oh ohVar = (oh) map2.get(str);
                try {
                    if (str.equals("BackgroundColor")) {
                        map3.put(str, new oh(ohVar, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                        i = childCount;
                    } else {
                        i = childCount;
                        try {
                            map3.put(str, new oh(ohVar, cls.getMethod("getMap" + str, new Class[0]).invoke(childAt, new Object[0])));
                        } catch (IllegalAccessException e2) {
                            e = e2;
                            e.printStackTrace();
                        } catch (NoSuchMethodException e3) {
                            e = e3;
                            e.printStackTrace();
                        } catch (InvocationTargetException e4) {
                            e = e4;
                            e.printStackTrace();
                        }
                    }
                } catch (IllegalAccessException e5) {
                    e = e5;
                    i = childCount;
                } catch (NoSuchMethodException e6) {
                    e = e6;
                    i = childCount;
                } catch (InvocationTargetException e7) {
                    e = e7;
                    i = childCount;
                }
                childCount = i;
            }
            int i3 = childCount;
            cVar.f = map3;
            cVar.b(id, layoutParams);
            int visibility = childAt.getVisibility();
            vh vhVar = cVar.b;
            vhVar.b = visibility;
            vhVar.d = childAt.getAlpha();
            float rotation = childAt.getRotation();
            wh whVar = cVar.e;
            whVar.b = rotation;
            whVar.c = childAt.getRotationX();
            whVar.d = childAt.getRotationY();
            whVar.e = childAt.getScaleX();
            whVar.f = childAt.getScaleY();
            float pivotX = childAt.getPivotX();
            float pivotY = childAt.getPivotY();
            if (pivotX != 0.0d || pivotY != 0.0d) {
                whVar.g = pivotX;
                whVar.h = pivotY;
            }
            whVar.i = childAt.getTranslationX();
            whVar.j = childAt.getTranslationY();
            whVar.k = childAt.getTranslationZ();
            if (whVar.l) {
                whVar.m = childAt.getElevation();
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                boolean z = barrier.N.j0;
                th thVar = cVar.d;
                thVar.j0 = z;
                thVar.e0 = barrier.getReferencedIds();
                thVar.b0 = barrier.getType();
                thVar.c0 = barrier.getMargin();
            }
            i2++;
            dVar = this;
            childCount = i3;
        }
    }

    public final c g(int i) {
        HashMap map = this.c;
        if (!map.containsKey(Integer.valueOf(i))) {
            map.put(Integer.valueOf(i), new c());
        }
        return (c) map.get(Integer.valueOf(i));
    }

    public final void h(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    c cVarF = f(context, Xml.asAttributeSet(xml));
                    if (name.equalsIgnoreCase("Guideline")) {
                        cVarF.d.a = true;
                    }
                    this.c.put(Integer.valueOf(cVarF.a), cVarF);
                }
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (XmlPullParserException e3) {
            e3.printStackTrace();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:101:0x017e, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0096  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void i(android.content.Context r9, android.content.res.XmlResourceParser r10) {
        /*
            Method dump skipped, instruction units count: 450
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.d.i(android.content.Context, android.content.res.XmlResourceParser):void");
    }
}
