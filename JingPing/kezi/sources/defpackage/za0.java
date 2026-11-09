package defpackage;

import android.R;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewParent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle$State;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.IDN;
import java.net.InetAddress;
import java.net.Socket;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Logger;
import kotlinx.coroutines.internal.DiagnosticCoroutineContextException;
import kotlinx.coroutines.internal.ExceptionSuccessfullyProcessed;
import okio.ByteString;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public abstract class za0 implements dt0 {
    public static final float[][] a = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
    public static final float[][] b = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};
    public static final float[] c = {95.047f, 100.0f, 108.883f};
    public static final float[][] d = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};
    public static final String[] e = new String[0];
    public static final boolean[] f = new boolean[3];
    public static final int[] g = {R.attr.interpolator, R.attr.duration, R.attr.startDelay, R.attr.matchOrder};
    public static final int[] h = {R.attr.resizeClip};
    public static final int[] i = {R.attr.transitionVisibilityMode};
    public static final int[] j = {R.attr.fadingMode};
    public static final int[] k = {R.attr.reparent, R.attr.reparentWithOverlay};
    public static final int[] l = {R.attr.slideEdge};
    public static final int[] m = {R.attr.transitionOrdering};
    public static final int[] n = {R.attr.minimumHorizontalAngle, R.attr.minimumVerticalAngle, R.attr.maximumAngle};
    public static final int[] o = {R.attr.patternPathData};

    public static float A(int i2) {
        float f2 = i2 / 255.0f;
        return (f2 <= 0.04045f ? f2 / 12.92f : (float) Math.pow((f2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 100.0f;
    }

    public static int B(int i2, Rect rect, Rect rect2) {
        int i3;
        int i4;
        if (i2 == 17) {
            i3 = rect.left;
            i4 = rect2.right;
        } else if (i2 == 33) {
            i3 = rect.top;
            i4 = rect2.bottom;
        } else if (i2 == 66) {
            i3 = rect2.left;
            i4 = rect.right;
        } else {
            if (i2 != 130) {
                throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
            }
            i3 = rect2.top;
            i4 = rect.bottom;
        }
        return Math.max(0, i3 - i4);
    }

    public static int C(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return Math.abs(((rect.width() / 2) + rect.left) - ((rect2.width() / 2) + rect2.left));
        }
        return Math.abs(((rect.height() / 2) + rect.top) - ((rect2.height() / 2) + rect2.top));
    }

    public static TypedArray D(Resources resources, Resources.Theme theme, AttributeSet attributeSet, int[] iArr) {
        return theme == null ? resources.obtainAttributes(attributeSet, iArr) : theme.obtainStyledAttributes(attributeSet, iArr, 0, 0);
    }

    public static void E(EditorInfo editorInfo, InputConnection inputConnection, TextView textView) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = textView.getParent(); parent instanceof View; parent = parent.getParent()) {
        }
    }

    public static String J(X509Certificate x509Certificate) {
        oy.j(x509Certificate, "certificate");
        return "sha256/" + L(x509Certificate).base64();
    }

    public static void K(EditorInfo editorInfo, CharSequence charSequence, int i2, int i3) {
        if (editorInfo.extras == null) {
            editorInfo.extras = new Bundle();
        }
        editorInfo.extras.putCharSequence("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SURROUNDING_TEXT", charSequence != null ? new SpannableStringBuilder(charSequence) : null);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_HEAD", i2);
        editorInfo.extras.putInt("androidx.core.view.inputmethod.EditorInfoCompat.CONTENT_SELECTION_END", i3);
    }

    public static ByteString L(X509Certificate x509Certificate) {
        oy.j(x509Certificate, "<this>");
        aa aaVar = ByteString.Companion;
        byte[] encoded = x509Certificate.getPublicKey().getEncoded();
        oy.i(encoded, "publicKey.encoded");
        aaVar.getClass();
        return aa.d(encoded, 0, -1234567890).sha256();
    }

    public static final i7 M(Socket socket) throws IOException {
        Logger logger = ga0.a;
        pj0 pj0Var = new pj0(socket);
        OutputStream outputStream = socket.getOutputStream();
        oy.i(outputStream, "getOutputStream(...)");
        return new i7(pj0Var, new i7(outputStream, pj0Var));
    }

    public static final j7 N(Socket socket) throws IOException {
        Logger logger = ga0.a;
        pj0 pj0Var = new pj0(socket);
        InputStream inputStream = socket.getInputStream();
        oy.i(inputStream, "getInputStream(...)");
        return new j7(pj0Var, 0, new j7(inputStream, 1, pj0Var));
    }

    public static ky O(my myVar, int i2) {
        oy.j(myVar, "<this>");
        boolean z = i2 > 0;
        Integer numValueOf = Integer.valueOf(i2);
        if (z) {
            if (myVar.d <= 0) {
                i2 = -i2;
            }
            return new ky(myVar.b, myVar.c, i2);
        }
        throw new IllegalArgumentException("Step must be positive, was: " + numValueOf + '.');
    }

    public static final String P(String str) {
        oy.j(str, "<this>");
        int i2 = 0;
        int i3 = -1;
        if (!vk0.l0(str, ":")) {
            try {
                String ascii = IDN.toASCII(str);
                oy.i(ascii, "toASCII(host)");
                Locale locale = Locale.US;
                oy.i(locale, "US");
                String lowerCase = ascii.toLowerCase(locale);
                oy.i(lowerCase, "this as java.lang.String).toLowerCase(locale)");
                if (lowerCase.length() == 0) {
                    return null;
                }
                int length = lowerCase.length();
                for (int i4 = 0; i4 < length; i4++) {
                    char cCharAt = lowerCase.charAt(i4);
                    if (oy.n(cCharAt, 31) <= 0 || oy.n(cCharAt, 127) >= 0 || vk0.o0(" #%/:?@[\\]", cCharAt, 0, 6) != -1) {
                        return null;
                    }
                }
                return lowerCase;
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        InetAddress inetAddressL = (dl0.j0(str, "[", false) && dl0.e0(str, "]")) ? l(str, 1, str.length() - 1) : l(str, 0, str.length());
        if (inetAddressL == null) {
            return null;
        }
        byte[] address = inetAddressL.getAddress();
        if (address.length != 16) {
            if (address.length == 4) {
                return inetAddressL.getHostAddress();
            }
            throw new AssertionError("Invalid IPv6 address: '" + str + '\'');
        }
        int i5 = 0;
        int i6 = 0;
        while (i5 < address.length) {
            int i7 = i5;
            while (i7 < 16 && address[i7] == 0 && address[i7 + 1] == 0) {
                i7 += 2;
            }
            int i8 = i7 - i5;
            if (i8 > i6 && i8 >= 4) {
                i3 = i5;
                i6 = i8;
            }
            i5 = i7 + 2;
        }
        p9 p9Var = new p9();
        while (i2 < address.length) {
            if (i2 == i3) {
                p9Var.O(58);
                i2 += i6;
                if (i2 == 16) {
                    p9Var.O(58);
                }
            } else {
                if (i2 > 0) {
                    p9Var.O(58);
                }
                byte b2 = address[i2];
                byte[] bArr = kq0.a;
                p9Var.P(((b2 & 255) << 8) | (address[i2 + 1] & 255));
                i2 += 2;
            }
        }
        return p9Var.H(p9Var.c, zb.a);
    }

    public static my Q(int i2, int i3) {
        if (i3 > Integer.MIN_VALUE) {
            return new my(i2, i3 - 1, 1);
        }
        my myVar = my.e;
        return my.e;
    }

    public static float R() {
        return ((float) Math.pow((((double) 50.0f) + 16.0d) / 116.0d, 3.0d)) * 100.0f;
    }

    public static final void d(StringBuilder sb, int i2) {
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append("?");
            if (i3 < i2 - 1) {
                sb.append(",");
            }
        }
    }

    public static final void e(gs0 gs0Var, e5 e5Var, p10 p10Var) {
        AutoCloseable autoCloseable;
        oy.j(e5Var, "registry");
        oy.j(p10Var, "lifecycle");
        hs0 hs0Var = gs0Var.a;
        if (hs0Var != null) {
            synchronized (hs0Var.a) {
                autoCloseable = (AutoCloseable) hs0Var.b.get("androidx.lifecycle.savedstate.vm.tag");
            }
        } else {
            autoCloseable = null;
        }
        ng0 ng0Var = (ng0) autoCloseable;
        if (ng0Var == null || ng0Var.d) {
            return;
        }
        ng0Var.t(e5Var, p10Var);
        Lifecycle$State lifecycle$State = ((w10) p10Var).c;
        if (lifecycle$State == Lifecycle$State.INITIALIZED || lifecycle$State.isAtLeast(Lifecycle$State.STARTED)) {
            e5Var.P();
        } else {
            p10Var.a(new oj(p10Var, 1, e5Var));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean f(int r8, android.graphics.Rect r9, android.graphics.Rect r10, android.graphics.Rect r11) {
        /*
            boolean r0 = g(r8, r9, r10)
            boolean r1 = g(r8, r9, r11)
            if (r1 != 0) goto L75
            if (r0 != 0) goto Le
            goto L75
        Le:
            java.lang.String r0 = "direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}."
            r1 = 130(0x82, float:1.82E-43)
            r2 = 33
            r3 = 66
            r4 = 17
            r5 = 1
            if (r8 == r4) goto L3c
            if (r8 == r2) goto L35
            if (r8 == r3) goto L2e
            if (r8 != r1) goto L28
            int r6 = r9.bottom
            int r7 = r11.top
            if (r6 > r7) goto L74
            goto L42
        L28:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r0)
            throw r8
        L2e:
            int r6 = r9.right
            int r7 = r11.left
            if (r6 > r7) goto L74
            goto L42
        L35:
            int r6 = r9.top
            int r7 = r11.bottom
            if (r6 < r7) goto L74
            goto L42
        L3c:
            int r6 = r9.left
            int r7 = r11.right
            if (r6 < r7) goto L74
        L42:
            if (r8 == r4) goto L74
            if (r8 != r3) goto L47
            goto L74
        L47:
            int r10 = B(r8, r9, r10)
            if (r8 == r4) goto L69
            if (r8 == r2) goto L64
            if (r8 == r3) goto L5f
            if (r8 != r1) goto L59
            int r8 = r11.bottom
            int r9 = r9.bottom
        L57:
            int r8 = r8 - r9
            goto L6e
        L59:
            java.lang.IllegalArgumentException r8 = new java.lang.IllegalArgumentException
            r8.<init>(r0)
            throw r8
        L5f:
            int r8 = r11.right
            int r9 = r9.right
            goto L57
        L64:
            int r8 = r9.top
            int r9 = r11.top
            goto L57
        L69:
            int r8 = r9.left
            int r9 = r11.left
            goto L57
        L6e:
            int r8 = java.lang.Math.max(r5, r8)
            if (r10 >= r8) goto L75
        L74:
            return r5
        L75:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.za0.f(int, android.graphics.Rect, android.graphics.Rect, android.graphics.Rect):boolean");
    }

    public static boolean g(int i2, Rect rect, Rect rect2) {
        if (i2 != 17) {
            if (i2 != 33) {
                if (i2 != 66) {
                    if (i2 != 130) {
                        throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
                    }
                }
            }
            return rect2.right >= rect.left && rect2.left <= rect.right;
        }
        return rect2.bottom >= rect.top && rect2.top <= rect.bottom;
    }

    public static void h(int i2, int i3, int i4) {
        if (i2 >= 0 && i3 <= i4) {
            if (i2 > i3) {
                throw new IllegalArgumentException(hk.f(i2, i3, "fromIndex: ", " > toIndex: "));
            }
            return;
        }
        throw new IndexOutOfBoundsException("fromIndex: " + i2 + ", toIndex: " + i3 + ", size: " + i4);
    }

    public static void i(s9 s9Var) {
        boolean z;
        long j2;
        int i2;
        s9 s9Var2;
        Object obj;
        nm nmVar = u9.r;
        while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = s9.j;
            if (atomicReferenceFieldUpdater.compareAndSet(s9Var, nmVar, null)) {
                z = true;
                break;
            } else if (atomicReferenceFieldUpdater.get(s9Var) != nmVar) {
                z = false;
                break;
            }
        }
        while (true) {
            AtomicLongFieldUpdater atomicLongFieldUpdater = s9.c;
            long j3 = atomicLongFieldUpdater.get(s9Var);
            int i3 = (int) (j3 >> 60);
            if (i3 == 0) {
                j2 = 1152921504606846975L & j3;
                i2 = 2;
            } else if (i3 != 1) {
                s9Var2 = s9Var;
                break;
            } else {
                j2 = 1152921504606846975L & j3;
                i2 = 3;
            }
            s9Var2 = s9Var;
            if (atomicLongFieldUpdater.compareAndSet(s9Var2, j3, (((long) i2) << 60) + j2)) {
                break;
            } else {
                s9Var = s9Var2;
            }
        }
        s9Var2.g();
        if (z) {
            loop2: while (true) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = s9.k;
                obj = atomicReferenceFieldUpdater2.get(s9Var2);
                nm nmVar2 = obj == null ? u9.p : u9.q;
                while (!atomicReferenceFieldUpdater2.compareAndSet(s9Var2, obj, nmVar2)) {
                    if (atomicReferenceFieldUpdater2.get(s9Var2) != obj) {
                        break;
                    }
                }
            }
            if (obj == null) {
                return;
            }
            rp0.e(1, obj);
            ((pu) obj).invoke(s9Var2.k());
        }
    }

    public static long j(long j2, long j3, long j4) {
        if (j3 <= j4) {
            return j2 < j3 ? j3 : j2 > j4 ? j4 : j2;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j4 + " is less than minimum " + j3 + '.');
    }

    public static qs k(Context context) {
        ProviderInfo providerInfo;
        o2 o2Var;
        ApplicationInfo applicationInfo;
        qm bjVar = Build.VERSION.SDK_INT >= 28 ? new bj() : new qm();
        PackageManager packageManager = context.getPackageManager();
        k9.h(packageManager, "Package manager required to locate emoji font provider");
        Iterator<ResolveInfo> it = packageManager.queryIntentContentProviders(new Intent("androidx.content.action.LOAD_EMOJI_FONT"), 0).iterator();
        while (true) {
            if (!it.hasNext()) {
                providerInfo = null;
                break;
            }
            providerInfo = it.next().providerInfo;
            if (providerInfo != null && (applicationInfo = providerInfo.applicationInfo) != null && (applicationInfo.flags & 1) == 1) {
                break;
            }
        }
        if (providerInfo == null) {
            o2Var = null;
        } else {
            try {
                String str = providerInfo.authority;
                String str2 = providerInfo.packageName;
                Signature[] signatureArrK = bjVar.k(packageManager, str2);
                ArrayList arrayList = new ArrayList();
                for (Signature signature : signatureArrK) {
                    arrayList.add(signature.toByteArray());
                }
                o2Var = new o2(str, str2, "emojicompat-emoji-font", Collections.singletonList(arrayList));
            } catch (PackageManager.NameNotFoundException e2) {
                Log.wtf("emoji2.text.DefaultEmojiConfig", e2);
                o2Var = null;
            }
        }
        if (o2Var == null) {
            return null;
        }
        return new qs(new ps(context, o2Var));
    }

    /* JADX WARN: Code restructure failed: missing block: B:64:0x00cb, code lost:
    
        if (r7 == 16) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:65:0x00cd, code lost:
    
        if (r8 != (-1)) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x00d1, code lost:
    
        r0 = r7 - r8;
        java.lang.System.arraycopy(r3, r8, r3, 16 - r0, r0);
        java.util.Arrays.fill(r3, r8, (16 - r7) + r8, (byte) 0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x00e1, code lost:
    
        return java.net.InetAddress.getByAddress(r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:90:?, code lost:
    
        return null;
     */
    /* JADX WARN: Removed duplicated region for block: B:55:0x00a2  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.net.InetAddress l(java.lang.String r17, int r18, int r19) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.za0.l(java.lang.String, int, int):java.net.InetAddress");
    }

    public static boolean m(File file) {
        if (!file.isDirectory()) {
            file.delete();
            return true;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return false;
        }
        boolean z = true;
        for (File file2 : fileArrListFiles) {
            z = m(file2) && z;
        }
        return z;
    }

    public static float n(float f2, float f3, float f4, float f5) {
        return (float) Math.hypot(f4 - f2, f5 - f3);
    }

    public static float o(float f2, float f3, float f4, float f5) {
        float fN = n(f2, f3, 0.0f, 0.0f);
        float fN2 = n(f2, f3, f4, 0.0f);
        float fN3 = n(f2, f3, f4, f5);
        float fN4 = n(f2, f3, 0.0f, f5);
        return (fN <= fN2 || fN <= fN3 || fN <= fN4) ? (fN2 <= fN3 || fN2 <= fN4) ? fN3 > fN4 ? fN3 : fN4 : fN2 : fN;
    }

    public static d5 p(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme, String str, int i2) {
        d5 d5VarC;
        if (xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null) {
            TypedValue typedValue = new TypedValue();
            typedArray.getValue(i2, typedValue);
            int i3 = typedValue.type;
            if (i3 >= 28 && i3 <= 31) {
                return new d5((Shader) null, (ColorStateList) null, typedValue.data);
            }
            try {
                d5VarC = d5.c(typedArray.getResources(), typedArray.getResourceId(i2, 0), theme);
            } catch (Exception e2) {
                Log.e("ComplexColorCompat", "Failed to inflate ComplexColor.", e2);
                d5VarC = null;
            }
            if (d5VarC != null) {
                return d5VarC;
            }
        }
        return new d5((Shader) null, (ColorStateList) null, 0);
    }

    public static int q(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2, int i3) {
        return !u(xmlPullParser, str) ? i3 : typedArray.getInt(i2, i3);
    }

    public static String r(TypedArray typedArray, XmlPullParser xmlPullParser, String str, int i2) {
        if (u(xmlPullParser, str)) {
            return typedArray.getString(i2);
        }
        return null;
    }

    public static String s(String str, String str2) {
        oy.j(str, "tableName");
        oy.j(str2, "triggerType");
        return "`room_table_modification_trigger_" + str + '_' + str2 + '`';
    }

    public static final void t(nh nhVar, Throwable th) {
        Throwable runtimeException;
        Iterator it = rh.a.iterator();
        while (it.hasNext()) {
            try {
                ((d3) it.next()).t(th);
            } catch (ExceptionSuccessfullyProcessed unused) {
                return;
            } catch (Throwable th2) {
                if (th == th2) {
                    runtimeException = th;
                } else {
                    runtimeException = new RuntimeException("Exception while trying to handle coroutine exception", th2);
                    nv0.c(runtimeException, th);
                }
                Thread threadCurrentThread = Thread.currentThread();
                threadCurrentThread.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread, runtimeException);
            }
        }
        try {
            nv0.c(th, new DiagnosticCoroutineContextException(nhVar));
        } catch (Throwable unused2) {
        }
        Thread threadCurrentThread2 = Thread.currentThread();
        threadCurrentThread2.getUncaughtExceptionHandler().uncaughtException(threadCurrentThread2, th);
    }

    public static boolean u(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getAttributeValue("http://schemas.android.com/apk/res/android", str) != null;
    }

    public static int v(float f2) {
        if (f2 < 1.0f) {
            return -16777216;
        }
        if (f2 > 99.0f) {
            return -1;
        }
        float f3 = (f2 + 16.0f) / 116.0f;
        float f4 = f2 > 8.0f ? f3 * f3 * f3 : f2 / 903.2963f;
        float f5 = f3 * f3 * f3;
        boolean z = f5 > 0.008856452f;
        float f6 = z ? f5 : ((f3 * 116.0f) - 16.0f) / 903.2963f;
        if (!z) {
            f5 = ((f3 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = c;
        return je.a(f6 * fArr[0], f4 * fArr[1], f5 * fArr[2]);
    }

    public static final boolean w(AssertionError assertionError) {
        Logger logger = ga0.a;
        if (assertionError.getCause() != null) {
            String message = assertionError.getMessage();
            if (message != null ? vk0.l0(message, "getsockname failed") : false) {
                return true;
            }
        }
        return false;
    }

    public static boolean x(int i2, Rect rect, Rect rect2) {
        if (i2 == 17) {
            int i3 = rect.right;
            int i4 = rect2.right;
            return (i3 > i4 || rect.left >= i4) && rect.left > rect2.left;
        }
        if (i2 == 33) {
            int i5 = rect.bottom;
            int i6 = rect2.bottom;
            return (i5 > i6 || rect.top >= i6) && rect.top > rect2.top;
        }
        if (i2 == 66) {
            int i7 = rect.left;
            int i8 = rect2.left;
            return (i7 < i8 || rect.right <= i8) && rect.right < rect2.right;
        }
        if (i2 != 130) {
            throw new IllegalArgumentException("direction must be one of {FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, FOCUS_RIGHT}.");
        }
        int i9 = rect.top;
        int i10 = rect2.top;
        return (i9 < i10 || rect.bottom <= i10) && rect.bottom < rect2.bottom;
    }

    public static boolean y(Context context, long j2, String str) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(str, 0);
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return (Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : (long) packageInfo.versionCode) >= j2;
    }

    public static void z(Context context, String str, String str2) {
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addFlags(335544320);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setComponent(new ComponentName(str, str2));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            try {
                context.startActivity(intent);
            } catch (Exception unused) {
            }
        }
    }

    public abstract void F(Throwable th);

    public abstract void G(int i2);

    public abstract void H(Typeface typeface, boolean z);

    public abstract void I(ba baVar);

    @Override // defpackage.dt0
    public void b() {
    }

    @Override // defpackage.dt0
    public void c() {
    }
}
