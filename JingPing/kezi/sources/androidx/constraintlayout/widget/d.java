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
import androidx.work.impl.Scheduler;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import defpackage.cg;
import defpackage.hg;
import defpackage.ig;
import defpackage.jg;
import defpackage.kg;
import defpackage.nm;
import defpackage.oy;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import org.java_websocket.WebSocketImpl;
import org.webrtc.EglBase;
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
                iIntValue = context.getResources().getIdentifier(strTrim, "id", context.getPackageName());
            }
            if (iIntValue == 0 && barrier.isInEditMode() && (barrier.getParent() instanceof ConstraintLayout)) {
                ConstraintLayout constraintLayout = (ConstraintLayout) barrier.getParent();
                if (strTrim != null) {
                    HashMap map = constraintLayout.n;
                    if (map != null && map.containsKey(strTrim)) {
                        obj = constraintLayout.n.get(strTrim);
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
            jg jgVar = cVar.b;
            ig igVar = cVar.c;
            kg kgVar = cVar.e;
            hg hgVar = cVar.d;
            if (index != i2 && R$styleable.Constraint_android_layout_marginStart != index && R$styleable.Constraint_android_layout_marginEnd != index) {
                igVar.a = true;
                hgVar.b = true;
                jgVar.a = true;
                kgVar.a = true;
            }
            SparseIntArray sparseIntArray = e;
            switch (sparseIntArray.get(index)) {
                case 1:
                    hgVar.p = j(typedArrayObtainStyledAttributes, index, hgVar.p);
                    break;
                case 2:
                    hgVar.G = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.G);
                    break;
                case 3:
                    hgVar.o = j(typedArrayObtainStyledAttributes, index, hgVar.o);
                    break;
                case 4:
                    hgVar.n = j(typedArrayObtainStyledAttributes, index, hgVar.n);
                    break;
                case 5:
                    hgVar.w = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 6:
                    hgVar.A = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, hgVar.A);
                    break;
                case 7:
                    hgVar.B = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, hgVar.B);
                    break;
                case 8:
                    hgVar.H = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.H);
                    break;
                case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    hgVar.t = j(typedArrayObtainStyledAttributes, index, hgVar.t);
                    break;
                case WorkDatabaseVersions.VERSION_10 /* 10 */:
                    hgVar.s = j(typedArrayObtainStyledAttributes, index, hgVar.s);
                    break;
                case WorkDatabaseVersions.VERSION_11 /* 11 */:
                    hgVar.M = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.M);
                    break;
                case WorkDatabaseVersions.VERSION_12 /* 12 */:
                    hgVar.N = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.N);
                    break;
                case WorkDatabaseVersions.VERSION_13 /* 13 */:
                    hgVar.J = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.J);
                    break;
                case WorkDatabaseVersions.VERSION_14 /* 14 */:
                    hgVar.L = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.L);
                    break;
                case WorkDatabaseVersions.VERSION_15 /* 15 */:
                    hgVar.O = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.O);
                    break;
                case 16:
                    hgVar.K = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.K);
                    break;
                case 17:
                    hgVar.e = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, hgVar.e);
                    break;
                case 18:
                    hgVar.f = typedArrayObtainStyledAttributes.getDimensionPixelOffset(index, hgVar.f);
                    break;
                case 19:
                    hgVar.g = typedArrayObtainStyledAttributes.getFloat(index, hgVar.g);
                    break;
                case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                    hgVar.u = typedArrayObtainStyledAttributes.getFloat(index, hgVar.u);
                    break;
                case 21:
                    hgVar.d = typedArrayObtainStyledAttributes.getLayoutDimension(index, hgVar.d);
                    break;
                case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                    int i3 = typedArrayObtainStyledAttributes.getInt(index, jgVar.b);
                    jgVar.b = i3;
                    jgVar.b = d[i3];
                    break;
                case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                    hgVar.c = typedArrayObtainStyledAttributes.getLayoutDimension(index, hgVar.c);
                    break;
                case 24:
                    hgVar.D = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.D);
                    break;
                case 25:
                    hgVar.h = j(typedArrayObtainStyledAttributes, index, hgVar.h);
                    break;
                case 26:
                    hgVar.i = j(typedArrayObtainStyledAttributes, index, hgVar.i);
                    break;
                case 27:
                    hgVar.C = typedArrayObtainStyledAttributes.getInt(index, hgVar.C);
                    break;
                case 28:
                    hgVar.E = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.E);
                    break;
                case 29:
                    hgVar.j = j(typedArrayObtainStyledAttributes, index, hgVar.j);
                    break;
                case 30:
                    hgVar.k = j(typedArrayObtainStyledAttributes, index, hgVar.k);
                    break;
                case 31:
                    hgVar.I = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.I);
                    break;
                case 32:
                    hgVar.q = j(typedArrayObtainStyledAttributes, index, hgVar.q);
                    break;
                case 33:
                    hgVar.r = j(typedArrayObtainStyledAttributes, index, hgVar.r);
                    break;
                case 34:
                    hgVar.F = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.F);
                    break;
                case 35:
                    hgVar.m = j(typedArrayObtainStyledAttributes, index, hgVar.m);
                    break;
                case 36:
                    hgVar.l = j(typedArrayObtainStyledAttributes, index, hgVar.l);
                    break;
                case 37:
                    hgVar.v = typedArrayObtainStyledAttributes.getFloat(index, hgVar.v);
                    break;
                case 38:
                    cVar.a = typedArrayObtainStyledAttributes.getResourceId(index, cVar.a);
                    break;
                case 39:
                    hgVar.Q = typedArrayObtainStyledAttributes.getFloat(index, hgVar.Q);
                    break;
                case 40:
                    hgVar.P = typedArrayObtainStyledAttributes.getFloat(index, hgVar.P);
                    break;
                case 41:
                    hgVar.R = typedArrayObtainStyledAttributes.getInt(index, hgVar.R);
                    break;
                case 42:
                    hgVar.S = typedArrayObtainStyledAttributes.getInt(index, hgVar.S);
                    break;
                case 43:
                    jgVar.d = typedArrayObtainStyledAttributes.getFloat(index, jgVar.d);
                    break;
                case 44:
                    kgVar.l = true;
                    kgVar.m = typedArrayObtainStyledAttributes.getDimension(index, kgVar.m);
                    break;
                case 45:
                    kgVar.c = typedArrayObtainStyledAttributes.getFloat(index, kgVar.c);
                    break;
                case 46:
                    kgVar.d = typedArrayObtainStyledAttributes.getFloat(index, kgVar.d);
                    break;
                case 47:
                    kgVar.e = typedArrayObtainStyledAttributes.getFloat(index, kgVar.e);
                    break;
                case 48:
                    kgVar.f = typedArrayObtainStyledAttributes.getFloat(index, kgVar.f);
                    break;
                case 49:
                    kgVar.g = typedArrayObtainStyledAttributes.getDimension(index, kgVar.g);
                    break;
                case Scheduler.MAX_SCHEDULER_LIMIT /* 50 */:
                    kgVar.h = typedArrayObtainStyledAttributes.getDimension(index, kgVar.h);
                    break;
                case 51:
                    kgVar.i = typedArrayObtainStyledAttributes.getDimension(index, kgVar.i);
                    break;
                case 52:
                    kgVar.j = typedArrayObtainStyledAttributes.getDimension(index, kgVar.j);
                    break;
                case 53:
                    kgVar.k = typedArrayObtainStyledAttributes.getDimension(index, kgVar.k);
                    break;
                case 54:
                    hgVar.T = typedArrayObtainStyledAttributes.getInt(index, hgVar.T);
                    break;
                case 55:
                    hgVar.U = typedArrayObtainStyledAttributes.getInt(index, hgVar.U);
                    break;
                case 56:
                    hgVar.V = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.V);
                    break;
                case 57:
                    hgVar.W = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.W);
                    break;
                case 58:
                    hgVar.X = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.X);
                    break;
                case 59:
                    hgVar.Y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.Y);
                    break;
                case 60:
                    kgVar.b = typedArrayObtainStyledAttributes.getFloat(index, kgVar.b);
                    break;
                case 61:
                    hgVar.x = j(typedArrayObtainStyledAttributes, index, hgVar.x);
                    break;
                case 62:
                    hgVar.y = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.y);
                    break;
                case 63:
                    hgVar.z = typedArrayObtainStyledAttributes.getFloat(index, hgVar.z);
                    break;
                case EglBase.EGL_OPENGL_ES3_BIT /* 64 */:
                    igVar.b = j(typedArrayObtainStyledAttributes, index, igVar.b);
                    break;
                case 65:
                    if (typedArrayObtainStyledAttributes.peekValue(index).type == 3) {
                        igVar.c = typedArrayObtainStyledAttributes.getString(index);
                    } else {
                        igVar.c = nm.d[typedArrayObtainStyledAttributes.getInteger(index, 0)];
                    }
                    break;
                case 66:
                    igVar.e = typedArrayObtainStyledAttributes.getInt(index, 0);
                    break;
                case 67:
                    igVar.g = typedArrayObtainStyledAttributes.getFloat(index, igVar.g);
                    break;
                case 68:
                    jgVar.e = typedArrayObtainStyledAttributes.getFloat(index, jgVar.e);
                    break;
                case 69:
                    hgVar.Z = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                    break;
                case 70:
                    hgVar.a0 = typedArrayObtainStyledAttributes.getFloat(index, 1.0f);
                    break;
                case 71:
                    Log.e("ConstraintSet", "CURRENTLY UNSUPPORTED");
                    break;
                case 72:
                    hgVar.b0 = typedArrayObtainStyledAttributes.getInt(index, hgVar.b0);
                    break;
                case 73:
                    hgVar.c0 = typedArrayObtainStyledAttributes.getDimensionPixelSize(index, hgVar.c0);
                    break;
                case 74:
                    hgVar.f0 = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 75:
                    hgVar.j0 = typedArrayObtainStyledAttributes.getBoolean(index, hgVar.j0);
                    break;
                case 76:
                    igVar.d = typedArrayObtainStyledAttributes.getInt(index, igVar.d);
                    break;
                case 77:
                    hgVar.g0 = typedArrayObtainStyledAttributes.getString(index);
                    break;
                case 78:
                    jgVar.c = typedArrayObtainStyledAttributes.getInt(index, jgVar.c);
                    break;
                case 79:
                    igVar.f = typedArrayObtainStyledAttributes.getFloat(index, igVar.f);
                    break;
                case WebSocketImpl.DEFAULT_PORT /* 80 */:
                    hgVar.h0 = typedArrayObtainStyledAttributes.getBoolean(index, hgVar.h0);
                    break;
                case 81:
                    hgVar.i0 = typedArrayObtainStyledAttributes.getBoolean(index, hgVar.i0);
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
                Log.v("ConstraintSet", "id unknown " + oy.x(childAt));
            } else {
                if (this.b && id == -1) {
                    throw new RuntimeException("All children of ConstraintLayout must have ids to use ConstraintSet");
                }
                if (map.containsKey(Integer.valueOf(id))) {
                    cg.f(childAt, ((c) map.get(Integer.valueOf(id))).f);
                }
            }
        }
    }

    public final void b(ConstraintLayout constraintLayout) {
        c(constraintLayout);
        constraintLayout.setConstraintSet(null);
        constraintLayout.requestLayout();
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1104)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1058)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    public final void c(ConstraintLayout constraintLayout) {
        int childCount = constraintLayout.getChildCount();
        HashMap map = this.c;
        HashSet<Integer> hashSet = new HashSet(map.keySet());
        for (int i = 0; i < childCount; i++) {
            View childAt = constraintLayout.getChildAt(i);
            int id = childAt.getId();
            if (!map.containsKey(Integer.valueOf(id))) {
                Log.w("ConstraintSet", "id unknown " + oy.x(childAt));
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
                            hg hgVar = cVar.d;
                            barrier.setType(hgVar.b0);
                            barrier.setMargin(hgVar.c0);
                            barrier.setAllowsGoneWidget(hgVar.j0);
                            int[] iArr = hgVar.e0;
                            if (iArr != null) {
                                barrier.setReferencedIds(iArr);
                            } else {
                                String str = hgVar.f0;
                                if (str != null) {
                                    int[] iArrE = e(barrier, str);
                                    hgVar.e0 = iArrE;
                                    barrier.setReferencedIds(iArrE);
                                }
                            }
                        }
                        ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) childAt.getLayoutParams();
                        layoutParams.a();
                        cVar.a(layoutParams);
                        cg.f(childAt, cVar.f);
                        childAt.setLayoutParams(layoutParams);
                        jg jgVar = cVar.b;
                        if (jgVar.c == 0) {
                            childAt.setVisibility(jgVar.b);
                        }
                        childAt.setAlpha(jgVar.d);
                        kg kgVar = cVar.e;
                        childAt.setRotation(kgVar.b);
                        childAt.setRotationX(kgVar.c);
                        childAt.setRotationY(kgVar.d);
                        childAt.setScaleX(kgVar.e);
                        childAt.setScaleY(kgVar.f);
                        if (!Float.isNaN(kgVar.g)) {
                            childAt.setPivotX(kgVar.g);
                        }
                        if (!Float.isNaN(kgVar.h)) {
                            childAt.setPivotY(kgVar.h);
                        }
                        childAt.setTranslationX(kgVar.i);
                        childAt.setTranslationY(kgVar.j);
                        childAt.setTranslationZ(kgVar.k);
                        if (kgVar.l) {
                            childAt.setElevation(kgVar.m);
                        }
                    } else {
                        Log.v("ConstraintSet", "WARNING NO CONSTRAINTS for view " + id);
                    }
                }
            }
        }
        for (Integer num : hashSet) {
            c cVar2 = (c) map.get(num);
            hg hgVar2 = cVar2.d;
            int i3 = hgVar2.d0;
            if (i3 != -1 && i3 == 1) {
                Barrier barrier2 = new Barrier(constraintLayout.getContext());
                barrier2.setId(num.intValue());
                int[] iArr2 = hgVar2.e0;
                if (iArr2 != null) {
                    barrier2.setReferencedIds(iArr2);
                } else {
                    String str2 = hgVar2.f0;
                    if (str2 != null) {
                        int[] iArrE2 = e(barrier2, str2);
                        hgVar2.e0 = iArrE2;
                        barrier2.setReferencedIds(iArrE2);
                    }
                }
                barrier2.setType(hgVar2.b0);
                barrier2.setMargin(hgVar2.c0);
                int i4 = ConstraintLayout.q;
                ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams();
                barrier2.l();
                cVar2.a(layoutParams2);
                constraintLayout.addView(barrier2, layoutParams2);
            }
            if (hgVar2.a) {
                View guideline = new Guideline(constraintLayout.getContext());
                guideline.setId(num.intValue());
                int i5 = ConstraintLayout.q;
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
                cg cgVar = (cg) map2.get(str);
                try {
                    if (str.equals("BackgroundColor")) {
                        map3.put(str, new cg(cgVar, Integer.valueOf(((ColorDrawable) childAt.getBackground()).getColor())));
                        i = childCount;
                    } else {
                        i = childCount;
                        try {
                            map3.put(str, new cg(cgVar, cls.getMethod("getMap" + str, new Class[0]).invoke(childAt, new Object[0])));
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
            jg jgVar = cVar.b;
            jgVar.b = visibility;
            jgVar.d = childAt.getAlpha();
            float rotation = childAt.getRotation();
            kg kgVar = cVar.e;
            kgVar.b = rotation;
            kgVar.c = childAt.getRotationX();
            kgVar.d = childAt.getRotationY();
            kgVar.e = childAt.getScaleX();
            kgVar.f = childAt.getScaleY();
            float pivotX = childAt.getPivotX();
            float pivotY = childAt.getPivotY();
            if (pivotX != 0.0d || pivotY != 0.0d) {
                kgVar.g = pivotX;
                kgVar.h = pivotY;
            }
            kgVar.i = childAt.getTranslationX();
            kgVar.j = childAt.getTranslationY();
            kgVar.k = childAt.getTranslationZ();
            if (kgVar.l) {
                kgVar.m = childAt.getElevation();
            }
            if (childAt instanceof Barrier) {
                Barrier barrier = (Barrier) childAt;
                boolean z = barrier.k.j0;
                hg hgVar = cVar.d;
                hgVar.j0 = z;
                hgVar.e0 = barrier.getReferencedIds();
                hgVar.b0 = barrier.getType();
                hgVar.c0 = barrier.getMargin();
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
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void i(android.content.Context r9, android.content.res.XmlResourceParser r10) {
        /*
            Method dump skipped, instruction units count: 450
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.constraintlayout.widget.d.i(android.content.Context, android.content.res.XmlResourceParser):void");
    }
}
