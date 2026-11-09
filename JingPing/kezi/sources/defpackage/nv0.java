package defpackage;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.Trace;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.transition.Transition;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.textfield.TextInputLayout;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.collections.builders.MapBuilder;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public abstract class nv0 {
    public static final nm b = new nm("CLOSED");
    public static final nm c = new nm("COMPLETING_ALREADY");
    public static final nm d = new nm("COMPLETING_WAITING_CHILDREN");
    public static final nm e = new nm("COMPLETING_RETRY");
    public static final nm f = new nm("TOO_LATE_TO_CANCEL");
    public static final nm g = new nm("SEALED");
    public static final xn h = new xn(false);
    public static final xn i = new xn(true);
    public static final byte[] j = {48, 49, 53, 0};
    public static final byte[] k = {48, 49, 48, 0};
    public static final byte[] l = {48, 48, 57, 0};
    public static final byte[] m = {48, 48, 53, 0};
    public static final byte[] n = {48, 48, 49, 0};
    public static final byte[] o = {48, 48, 49, 0};
    public static final byte[] p = {48, 48, 50, 0};
    public static final String[] q = {"android:visibilityPropagation:visibility", "android:visibilityPropagation:center"};
    public static long r;
    public static Method s;

    public static int A(mp0 mp0Var, int i2) {
        int[] iArr;
        if (mp0Var == null || (iArr = (int[]) mp0Var.a.get("android:visibilityPropagation:center")) == null) {
            return -1;
        }
        return iArr[i2];
    }

    public static int B(int i2) {
        if (i2 == 1) {
            return 0;
        }
        if (i2 == 2) {
            return 1;
        }
        if (i2 == 4) {
            return 2;
        }
        if (i2 == 8) {
            return 3;
        }
        if (i2 == 16) {
            return 4;
        }
        if (i2 == 32) {
            return 5;
        }
        if (i2 == 64) {
            return 6;
        }
        if (i2 == 128) {
            return 7;
        }
        if (i2 == 256) {
            return 8;
        }
        throw new IllegalArgumentException(hk.g(i2, "type needs to be >= FIRST and <= LAST, type="));
    }

    public static boolean C() {
        try {
            if (s == null) {
                return Trace.isEnabled();
            }
        } catch (NoClassDefFoundError | NoSuchMethodError unused) {
        }
        try {
            if (s == null) {
                r = Trace.class.getField("TRACE_TAG_APP").getLong(null);
                s = Trace.class.getMethod("isTagEnabled", Long.TYPE);
            }
            return ((Boolean) s.invoke(null, Long.valueOf(r))).booleanValue();
        } catch (Exception e2) {
            if (!(e2 instanceof InvocationTargetException)) {
                Log.v("Trace", "Unable to call isTagEnabled via reflection", e2);
                return false;
            }
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            throw new RuntimeException(cause);
        }
    }

    public static Typeface D(Configuration configuration, Typeface typeface) {
        if (Build.VERSION.SDK_INT < 31 || configuration.fontWeightAdjustment == Integer.MAX_VALUE || configuration.fontWeightAdjustment == 0 || typeface == null) {
            return null;
        }
        return Typeface.create(typeface, rp0.n(configuration.fontWeightAdjustment + typeface.getWeight(), 1, CloseFrame.NORMAL), typeface.isItalic());
    }

    public static e70 H(MappedByteBuffer mappedByteBuffer) throws IOException {
        long j2;
        ByteBuffer byteBufferDuplicate = mappedByteBuffer.duplicate();
        byteBufferDuplicate.order(ByteOrder.BIG_ENDIAN);
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
        int i2 = byteBufferDuplicate.getShort() & 65535;
        if (i2 > 100) {
            throw new IOException("Cannot read metadata.");
        }
        byteBufferDuplicate.position(byteBufferDuplicate.position() + 6);
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                j2 = -1;
                break;
            }
            int i4 = byteBufferDuplicate.getInt();
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            j2 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 4);
            if (1835365473 == i4) {
                break;
            }
            i3++;
        }
        if (j2 != -1) {
            byteBufferDuplicate.position(byteBufferDuplicate.position() + ((int) (j2 - ((long) byteBufferDuplicate.position()))));
            byteBufferDuplicate.position(byteBufferDuplicate.position() + 12);
            long j3 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
            for (int i5 = 0; i5 < j3; i5++) {
                int i6 = byteBufferDuplicate.getInt();
                long j4 = ((long) byteBufferDuplicate.getInt()) & 4294967295L;
                byteBufferDuplicate.getInt();
                if (1164798569 == i6 || 1701669481 == i6) {
                    byteBufferDuplicate.position((int) (j4 + j2));
                    e70 e70Var = new e70();
                    byteBufferDuplicate.order(ByteOrder.LITTLE_ENDIAN);
                    int iPosition = byteBufferDuplicate.position() + byteBufferDuplicate.getInt(byteBufferDuplicate.position());
                    e70Var.e = byteBufferDuplicate;
                    e70Var.b = iPosition;
                    int i7 = iPosition - byteBufferDuplicate.getInt(iPosition);
                    e70Var.c = i7;
                    e70Var.d = ((ByteBuffer) e70Var.e).getShort(i7);
                    return e70Var;
                }
            }
        }
        throw new IOException("Cannot read metadata.");
    }

    public static void I(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (checkableImageButton.getDrawable() == null || colorStateList == null || !colorStateList.isStateful()) {
            return;
        }
        int[] drawableState = textInputLayout.getDrawableState();
        int[] drawableState2 = checkableImageButton.getDrawableState();
        int length = drawableState.length;
        int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
        System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
        int colorForState = colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor());
        Drawable drawableMutate = drawable.mutate();
        hl.h(drawableMutate, ColorStateList.valueOf(colorForState));
        checkableImageButton.setImageDrawable(drawableMutate);
    }

    public static TypedValue J(Context context, int i2) {
        TypedValue typedValue = new TypedValue();
        if (context.getTheme().resolveAttribute(i2, typedValue, true)) {
            return typedValue;
        }
        return null;
    }

    public static boolean K(Context context, int i2, boolean z) {
        TypedValue typedValueJ = J(context, i2);
        return (typedValueJ == null || typedValueJ.type != 18) ? z : typedValueJ.data != 0;
    }

    public static TypedValue L(Context context, int i2, String str) {
        TypedValue typedValueJ = J(context, i2);
        if (typedValueJ != null) {
            return typedValueJ;
        }
        throw new IllegalArgumentException(String.format("%1$s requires a value for the %2$s attribute to be set in your app theme. You can either set the attribute in your theme or update your theme to inherit from Theme.MaterialComponents (or a descendant).", str, context.getResources().getResourceName(i2)));
    }

    public static void M(CheckableImageButton checkableImageButton, View.OnLongClickListener onLongClickListener) {
        WeakHashMap weakHashMap = xr0.a;
        boolean zHasOnClickListeners = checkableImageButton.hasOnClickListeners();
        boolean z = onLongClickListener != null;
        boolean z2 = zHasOnClickListeners || z;
        checkableImageButton.setFocusable(z2);
        checkableImageButton.setClickable(zHasOnClickListeners);
        checkableImageButton.setPressable(zHasOnClickListeners);
        checkableImageButton.setLongClickable(z);
        checkableImageButton.setImportantForAccessibility(z2 ? 1 : 2);
    }

    public static void N(Drawable drawable, int i2) {
        hl.g(drawable, i2);
    }

    public static void O(Drawable drawable, ColorStateList colorStateList) {
        hl.h(drawable, colorStateList);
    }

    public static void P(Drawable drawable, PorterDuff.Mode mode) {
        hl.i(drawable, mode);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static Drawable Q(Drawable drawable) {
        if (!(drawable instanceof iv0)) {
            return drawable;
        }
        ((jv0) ((iv0) drawable)).getClass();
        return null;
    }

    public static void c(Throwable th, Throwable th2) throws IllegalAccessException, InvocationTargetException {
        oy.j(th, "<this>");
        oy.j(th2, "exception");
        if (th != th2) {
            Integer num = vy.a;
            if (num == null || num.intValue() >= 19) {
                th.addSuppressed(th2);
                return;
            }
            Method method = sb0.a;
            if (method != null) {
                method.invoke(th, th2);
            }
        }
    }

    public static void e(TextInputLayout textInputLayout, CheckableImageButton checkableImageButton, ColorStateList colorStateList, PorterDuff.Mode mode) {
        Drawable drawable = checkableImageButton.getDrawable();
        if (drawable != null) {
            drawable = drawable.mutate();
            if (colorStateList == null || !colorStateList.isStateful()) {
                hl.h(drawable, colorStateList);
            } else {
                int[] drawableState = textInputLayout.getDrawableState();
                int[] drawableState2 = checkableImageButton.getDrawableState();
                int length = drawableState.length;
                int[] iArrCopyOf = Arrays.copyOf(drawableState, drawableState.length + drawableState2.length);
                System.arraycopy(drawableState2, 0, iArrCopyOf, length, drawableState2.length);
                hl.h(drawable, ColorStateList.valueOf(colorStateList.getColorForState(iArrCopyOf, colorStateList.getDefaultColor())));
            }
            if (mode != null) {
                hl.i(drawable, mode);
            }
        }
        if (checkableImageButton.getDrawable() != drawable) {
            checkableImageButton.setImageDrawable(drawable);
        }
    }

    public static void f(ul0 ul0Var, Object[] objArr) {
        if (objArr == null) {
            return;
        }
        int length = objArr.length;
        int i2 = 0;
        while (i2 < length) {
            Object obj = objArr[i2];
            i2++;
            if (obj == null) {
                ul0Var.r(i2);
            } else if (obj instanceof byte[]) {
                ul0Var.B((byte[]) obj, i2);
            } else if (obj instanceof Float) {
                ul0Var.s(i2, ((Number) obj).floatValue());
            } else if (obj instanceof Double) {
                ul0Var.s(i2, ((Number) obj).doubleValue());
            } else if (obj instanceof Long) {
                ul0Var.x(((Number) obj).longValue(), i2);
            } else if (obj instanceof Integer) {
                ul0Var.x(((Number) obj).intValue(), i2);
            } else if (obj instanceof Short) {
                ul0Var.x(((Number) obj).shortValue(), i2);
            } else if (obj instanceof Byte) {
                ul0Var.x(((Number) obj).byteValue(), i2);
            } else if (obj instanceof String) {
                ul0Var.j(i2, (String) obj);
            } else {
                if (!(obj instanceof Boolean)) {
                    throw new IllegalArgumentException("Cannot bind " + obj + " at index " + i2 + " Supported types: Null, ByteArray, Float, Double, Long, Int, Short, Byte, String");
                }
                ul0Var.x(((Boolean) obj).booleanValue() ? 1L : 0L, i2);
            }
        }
    }

    public static int g(Context context, String str) {
        int iC;
        int iMyPid = Process.myPid();
        int iMyUid = Process.myUid();
        String packageName = context.getPackageName();
        if (context.checkPermission(str, iMyPid, iMyUid) != -1) {
            String strD = o6.d(str);
            if (strD != null) {
                if (packageName == null) {
                    String[] packagesForUid = context.getPackageManager().getPackagesForUid(iMyUid);
                    if (packagesForUid != null && packagesForUid.length > 0) {
                        packageName = packagesForUid[0];
                    }
                }
                int iMyUid2 = Process.myUid();
                String packageName2 = context.getPackageName();
                if (iMyUid2 == iMyUid && Objects.equals(packageName2, packageName) && Build.VERSION.SDK_INT >= 29) {
                    AppOpsManager appOpsManagerC = p6.c(context);
                    iC = p6.a(appOpsManagerC, strD, Binder.getCallingUid(), packageName);
                    if (iC == 0) {
                        iC = p6.a(appOpsManagerC, strD, iMyUid, p6.b(context));
                    }
                } else {
                    iC = o6.c((AppOpsManager) o6.a(context, AppOpsManager.class), strD, packageName);
                }
                if (iC != 0) {
                    return -2;
                }
            }
            return 0;
        }
        return -1;
    }

    public static ImageView.ScaleType h(int i2) {
        return i2 != 0 ? i2 != 1 ? i2 != 2 ? i2 != 3 ? i2 != 5 ? i2 != 6 ? ImageView.ScaleType.CENTER : ImageView.ScaleType.CENTER_INSIDE : ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.FIT_END : ImageView.ScaleType.FIT_CENTER : ImageView.ScaleType.FIT_START : ImageView.ScaleType.FIT_XY;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static AnimatorSet i(gd gdVar, float f2, float f3, float f4) {
        ObjectAnimator objectAnimatorOfObject = ObjectAnimator.ofObject(gdVar, dd.a, cd.b, new fd(f2, f3, f4));
        fd revealInfo = gdVar.getRevealInfo();
        if (revealInfo == null) {
            throw new IllegalStateException("Caller must set a non-null RevealInfo before calling this.");
        }
        Animator animatorCreateCircularReveal = ViewAnimationUtils.createCircularReveal((View) gdVar, (int) f2, (int) f3, revealInfo.c, f4);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfObject, animatorCreateCircularReveal);
        return animatorSet;
    }

    public static mg0 j(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            bundle = bundle2;
        }
        if (bundle == null) {
            mg0 mg0Var = new mg0();
            new LinkedHashMap();
            mg0Var.a = new o2(ao.b);
            return mg0Var;
        }
        ClassLoader classLoader = mg0.class.getClassLoader();
        oy.g(classLoader);
        bundle.setClassLoader(classLoader);
        MapBuilder mapBuilder = new MapBuilder(bundle.size());
        for (String str : bundle.keySet()) {
            oy.g(str);
            mapBuilder.put(str, bundle.get(str));
        }
        Map mapBuild = mapBuilder.build();
        oy.j(mapBuild, "initialState");
        mg0 mg0Var2 = new mg0();
        new LinkedHashMap();
        mg0Var2.a = new o2(mapBuild);
        return mg0Var2;
    }

    public static gs0 l(Class cls) throws InvocationTargetException {
        try {
            Object objNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            oy.g(objNewInstance);
            return (gs0) objNewInstance;
        } catch (IllegalAccessException e2) {
            throw new RuntimeException("Cannot create an instance of " + cls, e2);
        } catch (InstantiationException e3) {
            throw new RuntimeException("Cannot create an instance of " + cls, e3);
        } catch (NoSuchMethodException e4) {
            throw new RuntimeException("Cannot create an instance of " + cls, e4);
        }
    }

    public static boolean m(String str, String str2) {
        oy.j(str, "current");
        if (str.equals(str2)) {
            return true;
        }
        if (str.length() != 0) {
            int i2 = 0;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (i2 < str.length()) {
                    char cCharAt = str.charAt(i2);
                    int i5 = i4 + 1;
                    if (i4 == 0 && cCharAt != '(') {
                        break;
                    }
                    if (cCharAt != '(') {
                        if (cCharAt == ')' && i3 - 1 == 0 && i4 != str.length() - 1) {
                            break;
                        }
                    } else {
                        i3++;
                    }
                    i2++;
                    i4 = i5;
                } else if (i3 == 0) {
                    String strSubstring = str.substring(1, str.length() - 1);
                    oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                    return oy.c(vk0.B0(strSubstring).toString(), str2);
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0, types: [vb] */
    /* JADX WARN: Type inference failed for: r4v1, types: [if, java.lang.Object, uh0] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r7v0, types: [t9] */
    public static final Object n(vb vbVar, long j2, t9 t9Var) {
        while (true) {
            long j3 = vbVar.c;
            if (j3 >= j2 && !vbVar.c()) {
                return vbVar;
            }
            Object obj = Cif.a.get(vbVar);
            nm nmVar = b;
            if (obj == nmVar) {
                return nmVar;
            }
            uh0 uh0Var = (uh0) ((Cif) obj);
            if (uh0Var != null) {
                vbVar = uh0Var;
            } else {
                uh0 uh0Var2 = (uh0) t9Var.invoke(Long.valueOf(j3 + 1), vbVar);
                while (true) {
                    AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = Cif.a;
                    if (atomicReferenceFieldUpdater.compareAndSet(vbVar, null, uh0Var2)) {
                        if (vbVar.c()) {
                            vbVar.d();
                        }
                        vbVar = uh0Var2;
                    } else if (atomicReferenceFieldUpdater.get(vbVar) != null) {
                        break;
                    }
                }
            }
        }
    }

    public static nv0 o(int i2, double[] dArr, double[][] dArr2) {
        int i3 = dArr.length == 1 ? 2 : i2;
        double d2 = 0.0d;
        if (i3 != 0) {
            if (i3 == 2) {
                double d3 = dArr[0];
                double[] dArr3 = dArr2[0];
                gi giVar = new gi();
                giVar.t = d3;
                giVar.u = dArr3;
                return giVar;
            }
            r6 r6Var = new r6();
            int length = dArr2[0].length;
            r6Var.u = dArr;
            r6Var.v = dArr2;
            if (length > 2) {
                double d4 = 0.0d;
                int i4 = 0;
                while (i4 < dArr.length) {
                    double d5 = dArr2[i4][0];
                    if (i4 > 0) {
                        Math.hypot(d5 - d2, d5 - d4);
                    }
                    i4++;
                    d4 = d5;
                    d2 = d4;
                }
            }
            return r6Var;
        }
        h70 h70Var = new h70();
        int length2 = dArr.length;
        int length3 = dArr2[0].length;
        int i5 = length2 - 1;
        Class cls = Double.TYPE;
        double[][] dArr4 = (double[][]) Array.newInstance((Class<?>) cls, i5, length3);
        double[][] dArr5 = (double[][]) Array.newInstance((Class<?>) cls, length2, length3);
        for (int i6 = 0; i6 < length3; i6++) {
            int i7 = 0;
            while (i7 < i5) {
                int i8 = i7 + 1;
                double d6 = dArr[i8] - dArr[i7];
                double[] dArr6 = dArr4[i7];
                double d7 = (dArr2[i8][i6] - dArr2[i7][i6]) / d6;
                dArr6[i6] = d7;
                if (i7 == 0) {
                    dArr5[i7][i6] = d7;
                } else {
                    dArr5[i7][i6] = (dArr4[i7 - 1][i6] + d7) * 0.5d;
                }
                i7 = i8;
            }
            dArr5[i5][i6] = dArr4[length2 - 2][i6];
        }
        int i9 = 0;
        while (i9 < i5) {
            int i10 = 0;
            while (i10 < length3) {
                double d8 = dArr4[i9][i10];
                if (d8 == d2) {
                    dArr5[i9][i10] = d2;
                    dArr5[i9 + 1][i10] = d2;
                } else {
                    double d9 = dArr5[i9][i10] / d8;
                    int i11 = i9 + 1;
                    double d10 = dArr5[i11][i10] / d8;
                    double dHypot = Math.hypot(d9, d10);
                    if (dHypot > 9.0d) {
                        double d11 = 3.0d / dHypot;
                        double[] dArr7 = dArr5[i9];
                        double[] dArr8 = dArr4[i9];
                        dArr7[i10] = d9 * d11 * dArr8[i10];
                        dArr5[i11][i10] = d11 * d10 * dArr8[i10];
                    }
                }
                i10++;
                d2 = 0.0d;
            }
            i9++;
            d2 = 0.0d;
        }
        h70Var.t = dArr;
        h70Var.u = dArr2;
        h70Var.v = dArr5;
        return h70Var;
    }

    public static void q() {
        oy.s("AriaConfig", "请使用init()初始化");
    }

    public static final ma r(fh fhVar) {
        ma maVar;
        ma maVar2;
        if (!(fhVar instanceof yk)) {
            return new ma(1, fhVar);
        }
        yk ykVar = (yk) fhVar;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = yk.i;
            Object obj = atomicReferenceFieldUpdater.get(ykVar);
            nm nmVar = oy.c;
            maVar = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(ykVar, nmVar);
                maVar2 = null;
                break;
            }
            if (obj instanceof ma) {
                while (!atomicReferenceFieldUpdater.compareAndSet(ykVar, obj, nmVar)) {
                    if (atomicReferenceFieldUpdater.get(ykVar) != obj) {
                        break;
                    }
                }
                maVar2 = (ma) obj;
                break loop0;
            }
            if (obj != nmVar && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (maVar2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = ma.h;
            Object obj2 = atomicReferenceFieldUpdater2.get(maVar2);
            if (!(obj2 instanceof se) || ((se) obj2).c == null) {
                ma.g.set(maVar2, 536870911);
                atomicReferenceFieldUpdater2.set(maVar2, s1.a);
                maVar = maVar2;
            } else {
                maVar2.k();
            }
            if (maVar != null) {
                return maVar;
            }
        }
        return new ma(2, fhVar);
    }

    public abstract View E(int i2);

    public abstract boolean F();

    public abstract Object G(Intent intent, int i2);

    public abstract Intent k(Context context, Intent intent);

    public abstract void p(ui0 ui0Var, float f2, float f3);

    public abstract double s(double d2);

    public abstract void t(double d2, double[] dArr);

    public abstract void u(double d2, float[] fArr);

    public abstract double v(double d2);

    public abstract void w(double d2, double[] dArr);

    public abstract long x(ViewGroup viewGroup, Transition transition, mp0 mp0Var, mp0 mp0Var2);

    public g2 y(Context context, Intent intent) {
        return null;
    }

    public abstract double[] z();
}
