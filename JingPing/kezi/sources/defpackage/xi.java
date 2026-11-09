package defpackage;

import android.R;
import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Property;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.BaseInterpolator;
import android.widget.EdgeEffect;
import androidx.recyclerview.widget.k;
import androidx.transition.R$id;
import androidx.transition.Visibility;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.WeakHashMap;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import kotlin.Pair;
import kotlin.Result;
import kotlin.a;
import kotlin.collections.EmptyList;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import okhttp3.TlsVersion;

/* JADX INFO: loaded from: classes.dex */
public abstract class xi implements kj0 {
    public static vd j = null;
    public static boolean n = false;
    public static Method o = null;
    public static boolean p = false;
    public static Field q;
    public static final int[] a = {R.attr.name, R.attr.tint, R.attr.height, R.attr.width, R.attr.alpha, R.attr.autoMirrored, R.attr.tintMode, R.attr.viewportWidth, R.attr.viewportHeight};
    public static final int[] b = {R.attr.name, R.attr.pivotX, R.attr.pivotY, R.attr.scaleX, R.attr.scaleY, R.attr.rotation, R.attr.translateX, R.attr.translateY};
    public static final int[] c = {R.attr.name, R.attr.fillColor, R.attr.pathData, R.attr.strokeColor, R.attr.strokeWidth, R.attr.trimPathStart, R.attr.trimPathEnd, R.attr.trimPathOffset, R.attr.strokeLineCap, R.attr.strokeLineJoin, R.attr.strokeMiterLimit, R.attr.strokeAlpha, R.attr.fillAlpha, R.attr.fillType};
    public static final int[] d = {R.attr.name, R.attr.pathData, R.attr.fillType};
    public static final int[] e = {R.attr.drawable};
    public static final int[] f = {R.attr.name, R.attr.animation};
    public static final int[] g = new int[0];
    public static final Object[] h = new Object[0];
    public static final vd i = new vd(null, null, null);
    public static final yo k = new yo(18);
    public static final yo l = new yo(19);
    public static final yo m = new yo(20);

    public static final int A(int i2, int i3, int i4) {
        if (i4 > 0) {
            if (i2 < i3) {
                int i5 = i3 % i4;
                if (i5 < 0) {
                    i5 += i4;
                }
                int i6 = i2 % i4;
                if (i6 < 0) {
                    i6 += i4;
                }
                int i7 = (i5 - i6) % i4;
                if (i7 < 0) {
                    i7 += i4;
                }
                return i3 - i7;
            }
        } else {
            if (i4 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (i2 > i3) {
                int i8 = -i4;
                int i9 = i2 % i8;
                if (i9 < 0) {
                    i9 += i8;
                }
                int i10 = i3 % i8;
                if (i10 < 0) {
                    i10 += i8;
                }
                int i11 = (i9 - i10) % i8;
                if (i11 < 0) {
                    i11 += i8;
                }
                return i11 + i3;
            }
        }
        return i3;
    }

    public static final qg0 B(ls0 ls0Var) {
        og0 og0Var = new og0();
        bi biVarA = ls0Var instanceof qv ? ((qv) ls0Var).a() : ai.b;
        oy.j(biVarA, "extras");
        ks0 ks0VarD = ls0Var.d();
        oy.j(ks0VarD, "store");
        return (qg0) new ba(ks0VarD, og0Var, biVarA).d(ce0.a(qg0.class), "androidx.lifecycle.internal.SavedStateHandlesVM");
    }

    public static String E(long j2, Locale locale) {
        return fq0.b("yMMMd", locale).format(new Date(j2));
    }

    public static boolean F(Context context) {
        return context.getResources().getConfiguration().fontScale >= 1.3f;
    }

    public static List G(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        oy.i(listSingletonList, "singletonList(...)");
        return listSingletonList;
    }

    public static float J(EdgeEffect edgeEffect, float f2, float f3) {
        if (Build.VERSION.SDK_INT >= 31) {
            return pm.c(edgeEffect, f2, f3);
        }
        om.a(edgeEffect, f2, f3);
        return f2;
    }

    public static void O(AnimatorSet animatorSet, ArrayList arrayList) {
        int size = arrayList.size();
        long jMax = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Animator animator = (Animator) arrayList.get(i2);
            jMax = Math.max(jMax, animator.getDuration() + animator.getStartDelay());
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(0, 0);
        valueAnimatorOfInt.setDuration(jMax);
        arrayList.add(0, valueAnimatorOfInt);
        animatorSet.playTogether(arrayList);
    }

    public static final Object P(Object obj, Object obj2) {
        if (obj == null) {
            return obj2;
        }
        if (obj instanceof ArrayList) {
            ((ArrayList) obj).add(obj2);
            return obj;
        }
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(obj);
        arrayList.add(obj2);
        return arrayList;
    }

    public static String Q(Context context, String str) {
        return context.getSharedPreferences(context.getPackageName(), 0).getString(str, null);
    }

    public static final String R(fh fhVar) {
        Object objM3constructorimpl;
        if (fhVar instanceof yk) {
            return fhVar.toString();
        }
        try {
            objM3constructorimpl = Result.m3constructorimpl(fhVar + '@' + y(fhVar));
        } catch (Throwable th) {
            objM3constructorimpl = Result.m3constructorimpl(a.a(th));
        }
        if (Result.m6exceptionOrNullimpl(objM3constructorimpl) != null) {
            objM3constructorimpl = fhVar.getClass().getName() + '@' + y(fhVar);
        }
        return (String) objM3constructorimpl;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [kotlin.coroutines.jvm.internal.ContinuationImpl] */
    /* JADX WARN: Type inference failed for: r1v1, types: [yh] */
    /* JADX WARN: Type inference failed for: r1v2, types: [yh] */
    public static final void T(ContinuationImpl continuationImpl, nh nhVar) {
        if (continuationImpl == 0 || nhVar.get(aq0.b) == null) {
            return;
        }
        do {
            continuationImpl = continuationImpl.getCallerFrame();
        } while (continuationImpl != 0);
    }

    public static void U(Context context, String str, String str2) {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(context.getPackageName(), 0).edit();
        editorEdit.putString(str, str2);
        editorEdit.apply();
    }

    public static void V(String str, byte[] bArr) {
        RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
        try {
            randomAccessFile.seek(1024L);
            int i2 = (int) 1024;
            byte[] bArr2 = new byte[i2];
            randomAccessFile.readFully(bArr2);
            for (int i3 = 0; i3 < i2; i3++) {
                bArr2[i3] = (byte) (bArr2[i3] ^ bArr[i3 % bArr.length]);
            }
            randomAccessFile.seek(1024L);
            randomAccessFile.write(bArr2);
            randomAccessFile.close();
        } catch (Throwable th) {
            try {
                randomAccessFile.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public static final boolean c(byte[] bArr, int i2, byte[] bArr2, int i3, int i4) {
        oy.j(bArr, "a");
        for (int i5 = 0; i5 < i4; i5++) {
            if (bArr[i5 + i2] != bArr2[i5 + i3]) {
                return false;
            }
        }
        return true;
    }

    public static int d(int i2, int i3, int[] iArr) {
        int i4 = i2 - 1;
        int i5 = 0;
        while (i5 <= i4) {
            int i6 = (i5 + i4) >>> 1;
            int i7 = iArr[i6];
            if (i7 < i3) {
                i5 = i6 + 1;
            } else {
                if (i7 <= i3) {
                    return i6;
                }
                i4 = i6 - 1;
            }
        }
        return ~i5;
    }

    public static int e(long[] jArr, int i2, long j2) {
        int i3 = i2 - 1;
        int i4 = 0;
        while (i4 <= i3) {
            int i5 = (i4 + i3) >>> 1;
            long j3 = jArr[i5];
            if (j3 < j2) {
                i4 = i5 + 1;
            } else {
                if (j3 <= j2) {
                    return i5;
                }
                i3 = i5 - 1;
            }
        }
        return ~i4;
    }

    public static final void f(long j2, long j3, long j4) {
        if ((j3 | j4) < 0 || j3 > j2 || j2 - j3 < j4) {
            throw new ArrayIndexOutOfBoundsException("size=" + j2 + " offset=" + j3 + " byteCount=" + j4);
        }
    }

    public static int i(yd0 yd0Var, en enVar, View view, View view2, k kVar, boolean z) {
        if (kVar.v() == 0 || yd0Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return Math.abs(k.F(view) - k.F(view2)) + 1;
        }
        return Math.min(enVar.l(), enVar.b(view2) - enVar.e(view));
    }

    public static int j(yd0 yd0Var, en enVar, View view, View view2, k kVar, boolean z, boolean z2) {
        if (kVar.v() == 0 || yd0Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        int iMax = z2 ? Math.max(0, (yd0Var.b() - Math.max(k.F(view), k.F(view2))) - 1) : Math.max(0, Math.min(k.F(view), k.F(view2)));
        if (z) {
            return Math.round((iMax * (Math.abs(enVar.b(view2) - enVar.e(view)) / (Math.abs(k.F(view) - k.F(view2)) + 1))) + (enVar.k() - enVar.e(view)));
        }
        return iMax;
    }

    public static int k(yd0 yd0Var, en enVar, View view, View view2, k kVar, boolean z) {
        if (kVar.v() == 0 || yd0Var.b() == 0 || view == null || view2 == null) {
            return 0;
        }
        if (!z) {
            return yd0Var.b();
        }
        return (int) (((enVar.b(view2) - enVar.e(view)) / (Math.abs(k.F(view) - k.F(view2)) + 1)) * yd0Var.b());
    }

    public static final void l(int i2, int i3) {
        if (i2 <= i3) {
            return;
        }
        throw new IndexOutOfBoundsException("toIndex (" + i2 + ") is greater than size (" + i3 + ").");
    }

    public static ObjectAnimator m(View view, mp0 mp0Var, int i2, int i3, float f2, float f3, float f4, float f5, BaseInterpolator baseInterpolator, Visibility visibility) {
        float f6;
        float f7;
        float translationX = view.getTranslationX();
        float translationY = view.getTranslationY();
        if (((int[]) mp0Var.b.getTag(R$id.transition_position)) != null) {
            f6 = (r5[0] - i2) + translationX;
            f7 = (r5[1] - i3) + translationY;
        } else {
            f6 = f2;
            f7 = f3;
        }
        int iRound = Math.round(f6 - translationX) + i2;
        int iRound2 = Math.round(f7 - translationY) + i3;
        view.setTranslationX(f6);
        view.setTranslationY(f7);
        if (f6 == f4 && f7 == f5) {
            return null;
        }
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_X, f6, f4), PropertyValuesHolder.ofFloat((Property<?, Float>) View.TRANSLATION_Y, f7, f5));
        np0 np0Var = new np0(view, mp0Var.b, iRound, iRound2, translationX, translationY);
        visibility.a(np0Var);
        objectAnimatorOfPropertyValuesHolder.addListener(np0Var);
        objectAnimatorOfPropertyValuesHolder.addPauseListener(np0Var);
        objectAnimatorOfPropertyValuesHolder.setInterpolator(baseInterpolator);
        return objectAnimatorOfPropertyValuesHolder;
    }

    public static final mg0 n(b80 b80Var) {
        yo yoVar = k;
        LinkedHashMap linkedHashMap = b80Var.a;
        vg0 vg0Var = (vg0) linkedHashMap.get(yoVar);
        if (vg0Var == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        ls0 ls0Var = (ls0) linkedHashMap.get(l);
        if (ls0Var == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle = (Bundle) linkedHashMap.get(m);
        String str = (String) linkedHashMap.get(o70.e);
        if (str == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
        }
        sg0 sg0VarZ = vg0Var.b().z();
        Bundle bundle2 = null;
        pg0 pg0Var = sg0VarZ instanceof pg0 ? (pg0) sg0VarZ : null;
        if (pg0Var == null) {
            throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
        }
        LinkedHashMap linkedHashMap2 = B(ls0Var).b;
        mg0 mg0Var = (mg0) linkedHashMap2.get(str);
        if (mg0Var != null) {
            return mg0Var;
        }
        pg0Var.b();
        Bundle bundle3 = pg0Var.c;
        if (bundle3 != null && bundle3.containsKey(str)) {
            Bundle bundle4 = bundle3.getBundle(str);
            if (bundle4 == null) {
                bundle4 = rp0.g((Pair[]) Arrays.copyOf(new Pair[0], 0));
            }
            bundle3.remove(str);
            if (bundle3.isEmpty()) {
                pg0Var.c = null;
            }
            bundle2 = bundle4;
        }
        mg0 mg0VarJ = nv0.j(bundle2, bundle);
        linkedHashMap2.put(str, mg0VarJ);
        return mg0VarJ;
    }

    public static boolean o(View view, KeyEvent keyEvent) {
        ArrayList arrayList;
        int size;
        int iIndexOfKey;
        WeakHashMap weakHashMap = xr0.a;
        if (Build.VERSION.SDK_INT >= 28) {
            return false;
        }
        ArrayList arrayList2 = wr0.d;
        wr0 wr0Var = (wr0) view.getTag(androidx.core.R$id.tag_unhandled_key_event_manager);
        WeakReference weakReference = null;
        if (wr0Var == null) {
            wr0Var = new wr0();
            wr0Var.a = null;
            wr0Var.b = null;
            wr0Var.c = null;
            view.setTag(androidx.core.R$id.tag_unhandled_key_event_manager, wr0Var);
        }
        WeakReference weakReference2 = wr0Var.c;
        if (weakReference2 != null && weakReference2.get() == keyEvent) {
            return false;
        }
        wr0Var.c = new WeakReference(keyEvent);
        if (wr0Var.b == null) {
            wr0Var.b = new SparseArray();
        }
        SparseArray sparseArray = wr0Var.b;
        if (keyEvent.getAction() == 1 && (iIndexOfKey = sparseArray.indexOfKey(keyEvent.getKeyCode())) >= 0) {
            weakReference = (WeakReference) sparseArray.valueAt(iIndexOfKey);
            sparseArray.removeAt(iIndexOfKey);
        }
        if (weakReference == null) {
            weakReference = (WeakReference) sparseArray.get(keyEvent.getKeyCode());
        }
        if (weakReference == null) {
            return false;
        }
        View view2 = (View) weakReference.get();
        if (view2 == null || !view2.isAttachedToWindow() || (arrayList = (ArrayList) view2.getTag(androidx.core.R$id.tag_unhandled_key_listeners)) == null || (size = arrayList.size() - 1) < 0) {
            return true;
        }
        arrayList.get(size).getClass();
        throw new ClassCastException();
    }

    public static boolean p(x00 x00Var, View view, Window.Callback callback, KeyEvent keyEvent) {
        DialogInterface.OnKeyListener onKeyListener;
        boolean zBooleanValue = false;
        if (x00Var != null) {
            if (Build.VERSION.SDK_INT >= 28) {
                return x00Var.c(keyEvent);
            }
            if (callback instanceof Activity) {
                Activity activity = (Activity) callback;
                activity.onUserInteraction();
                Window window = activity.getWindow();
                if (window.hasFeature(8)) {
                    ActionBar actionBar = activity.getActionBar();
                    if (keyEvent.getKeyCode() == 82 && actionBar != null) {
                        if (!n) {
                            try {
                                o = actionBar.getClass().getMethod("onMenuKeyEvent", KeyEvent.class);
                            } catch (NoSuchMethodException unused) {
                            }
                            n = true;
                        }
                        Method method = o;
                        if (method != null) {
                            try {
                                Object objInvoke = method.invoke(actionBar, keyEvent);
                                if (objInvoke != null) {
                                    zBooleanValue = ((Boolean) objInvoke).booleanValue();
                                }
                            } catch (IllegalAccessException | InvocationTargetException unused2) {
                            }
                        }
                        if (zBooleanValue) {
                            return true;
                        }
                    }
                }
                if (window.superDispatchKeyEvent(keyEvent)) {
                    return true;
                }
                View decorView = window.getDecorView();
                if (xr0.c(decorView, keyEvent)) {
                    return true;
                }
                return keyEvent.dispatch(activity, decorView != null ? decorView.getKeyDispatcherState() : null, activity);
            }
            if (callback instanceof Dialog) {
                Dialog dialog = (Dialog) callback;
                if (!p) {
                    try {
                        Field declaredField = Dialog.class.getDeclaredField("mOnKeyListener");
                        q = declaredField;
                        declaredField.setAccessible(true);
                    } catch (NoSuchFieldException unused3) {
                    }
                    p = true;
                }
                Field field = q;
                if (field != null) {
                    try {
                        onKeyListener = (DialogInterface.OnKeyListener) field.get(dialog);
                    } catch (IllegalAccessException unused4) {
                        onKeyListener = null;
                    }
                } else {
                    onKeyListener = null;
                }
                if (onKeyListener != null && onKeyListener.onKey(dialog, keyEvent.getKeyCode(), keyEvent)) {
                    return true;
                }
                Window window2 = dialog.getWindow();
                if (window2.superDispatchKeyEvent(keyEvent)) {
                    return true;
                }
                View decorView2 = window2.getDecorView();
                if (xr0.c(decorView2, keyEvent)) {
                    return true;
                }
                return keyEvent.dispatch(dialog, decorView2 != null ? decorView2.getKeyDispatcherState() : null, dialog);
            }
            if ((view != null && xr0.c(view, keyEvent)) || x00Var.c(keyEvent)) {
                return true;
            }
        }
        return false;
    }

    public static pv q(SSLSession sSLSession) throws IOException {
        Object objL;
        String cipherSuite = sSLSession.getCipherSuite();
        if (cipherSuite == null) {
            throw new IllegalStateException("cipherSuite == null");
        }
        if (cipherSuite.equals("TLS_NULL_WITH_NULL_NULL") ? true : cipherSuite.equals("SSL_NULL_WITH_NULL_NULL")) {
            throw new IOException("cipherSuite == ".concat(cipherSuite));
        }
        rc rcVarH = rc.b.h(cipherSuite);
        String protocol = sSLSession.getProtocol();
        if (protocol == null) {
            throw new IllegalStateException("tlsVersion == null");
        }
        if ("NONE".equals(protocol)) {
            throw new IOException("tlsVersion == NONE");
        }
        TlsVersion.Companion.getClass();
        TlsVersion tlsVersionA = no0.a(protocol);
        try {
            Certificate[] peerCertificates = sSLSession.getPeerCertificates();
            objL = peerCertificates != null ? kq0.l(Arrays.copyOf(peerCertificates, peerCertificates.length)) : EmptyList.INSTANCE;
        } catch (SSLPeerUnverifiedException unused) {
            objL = EmptyList.INSTANCE;
        }
        Certificate[] localCertificates = sSLSession.getLocalCertificates();
        return new pv(tlsVersionA, rcVarH, localCertificates != null ? kq0.l(Arrays.copyOf(localCertificates, localCertificates.length)) : EmptyList.INSTANCE, new ju(1, objL));
    }

    public static ColorStateList r(Context context, po poVar, int i2) {
        int resourceId;
        ColorStateList colorStateListW;
        TypedArray typedArray = (TypedArray) poVar.d;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (colorStateListW = rp0.w(context, resourceId)) == null) ? poVar.w(i2) : colorStateListW;
    }

    public static ColorStateList s(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        ColorStateList colorStateListW;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (colorStateListW = rp0.w(context, resourceId)) == null) ? typedArray.getColorStateList(i2) : colorStateListW;
    }

    public static lb0 t(Long l2, Long l3) {
        if (l2 == null && l3 == null) {
            return new lb0(null, null);
        }
        if (l2 == null) {
            return new lb0(null, u(l3.longValue()));
        }
        if (l3 == null) {
            return new lb0(u(l2.longValue()), null);
        }
        Calendar calendarF = fq0.f();
        Calendar calendarG = fq0.g(null);
        calendarG.setTimeInMillis(l2.longValue());
        Calendar calendarG2 = fq0.g(null);
        calendarG2.setTimeInMillis(l3.longValue());
        return calendarG.get(1) == calendarG2.get(1) ? calendarG.get(1) == calendarF.get(1) ? new lb0(z(l2.longValue(), Locale.getDefault()), z(l3.longValue(), Locale.getDefault())) : new lb0(z(l2.longValue(), Locale.getDefault()), E(l3.longValue(), Locale.getDefault())) : new lb0(E(l2.longValue(), Locale.getDefault()), E(l3.longValue(), Locale.getDefault()));
    }

    public static String u(long j2) {
        Calendar calendarF = fq0.f();
        Calendar calendarG = fq0.g(null);
        calendarG.setTimeInMillis(j2);
        return calendarF.get(1) == calendarG.get(1) ? z(j2, Locale.getDefault()) : E(j2, Locale.getDefault());
    }

    public static int v(Context context, TypedArray typedArray, int i2, int i3) {
        TypedValue typedValue = new TypedValue();
        if (!typedArray.getValue(i2, typedValue) || typedValue.type != 2) {
            return typedArray.getDimensionPixelSize(i2, i3);
        }
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(new int[]{typedValue.data});
        int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(0, i3);
        typedArrayObtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    public static float w(EdgeEffect edgeEffect) {
        if (Build.VERSION.SDK_INT >= 31) {
            return pm.b(edgeEffect);
        }
        return 0.0f;
    }

    public static Drawable x(Context context, TypedArray typedArray, int i2) {
        int resourceId;
        Drawable drawableY;
        return (!typedArray.hasValue(i2) || (resourceId = typedArray.getResourceId(i2, 0)) == 0 || (drawableY = rp0.y(context, resourceId)) == null) ? typedArray.getDrawable(i2) : drawableY;
    }

    public static final String y(Object obj) {
        return Integer.toHexString(System.identityHashCode(obj));
    }

    public static String z(long j2, Locale locale) {
        return fq0.b("MMMd", locale).format(new Date(j2));
    }

    public int C(View view) {
        return 0;
    }

    public int D() {
        return 0;
    }

    public abstract void L(int i2);

    public abstract void M(View view, int i2, int i3);

    public abstract void N(View view, float f2, float f3);

    public abstract boolean S(View view, int i2);

    @Override // defpackage.kj0
    public float a(ViewGroup viewGroup, View view) {
        return view.getTranslationX();
    }

    public abstract int g(View view, int i2);

    public abstract int h(View view, int i2);

    public void I() {
    }

    public void H(int i2, int i3) {
    }

    public void K(View view, int i2) {
    }
}
