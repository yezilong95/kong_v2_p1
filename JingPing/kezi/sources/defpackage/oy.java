package defpackage;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;
import defpackage.oy;
import defpackage.pu;
import defpackage.rp0;
import defpackage.ru;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.net.ProtocolException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Result;
import kotlin.UninitializedPropertyAccessException;
import kotlin.a;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.CoroutineSingletons;
import kotlin.coroutines.jvm.internal.BaseContinuationImpl;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.RestrictedContinuationImpl;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import okhttp3.Protocol;
import org.java_websocket.extensions.ExtensionRequestData;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public abstract class oy {
    public static final nm a = new nm("NO_DECISION");
    public static final nm b = new nm("UNDEFINED");
    public static final nm c = new nm("REUSABLE_CLAIMED");
    public static final nm d = new nm("NULL");
    public static final nm e = new nm("UNINITIALIZED");
    public static final nm f = new nm("NONE");
    public static final nm g = new nm("PENDING");
    public static int h = 3;
    public static boolean i = true;
    public static Method j;
    public static boolean k;

    public static String A(Activity activity, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String string;
        ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(componentName, Build.VERSION.SDK_INT >= 29 ? 269222528 : 787072);
        String str = activityInfo.parentActivityName;
        if (str != null) {
            return str;
        }
        Bundle bundle = activityInfo.metaData;
        if (bundle == null || (string = bundle.getString("android.support.PARENT_ACTIVITY")) == null) {
            return null;
        }
        if (string.charAt(0) != '.') {
            return string;
        }
        return activity.getPackageName() + string;
    }

    public static final uh0 B(Object obj) {
        if (obj == nv0.b) {
            throw new IllegalStateException("Does not contain segment");
        }
        h(obj, "null cannot be cast to non-null type S of kotlinx.coroutines.internal.SegmentOrClosed");
        return (uh0) obj;
    }

    public static String C(String str, String str2) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        Charset charset = StandardCharsets.UTF_8;
        mac.init(new SecretKeySpec(str2.getBytes(charset), "HmacSHA256"));
        return Base64.encodeToString(mac.doFinal(str.getBytes(charset)), 2);
    }

    public static fh D(fh fhVar) {
        fh fhVarIntercepted;
        j(fhVar, "<this>");
        ContinuationImpl continuationImpl = fhVar instanceof ContinuationImpl ? (ContinuationImpl) fhVar : null;
        return (continuationImpl == null || (fhVarIntercepted = continuationImpl.intercepted()) == null) ? fhVar : fhVarIntercepted;
    }

    public static final boolean E(Object obj) {
        return obj == nv0.b;
    }

    public static boolean F(EditText editText) {
        return editText.getInputType() != 0;
    }

    public static im0 G(ou ouVar) {
        j(ouVar, "initializer");
        return new im0(ouVar);
    }

    public static tv H(String... strArr) {
        if (strArr.length % 2 != 0) {
            throw new IllegalArgumentException("Expected alternating header names and values");
        }
        String[] strArr2 = (String[]) strArr.clone();
        int length = strArr2.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            String str = strArr2[i3];
            if (str == null) {
                throw new IllegalArgumentException("Headers cannot be null");
            }
            strArr2[i3] = vk0.B0(str).toString();
        }
        int iA = xi.A(0, strArr2.length - 1, 2);
        if (iA >= 0) {
            while (true) {
                String str2 = strArr2[i2];
                String str3 = strArr2[i2 + 1];
                f(str2);
                k(str3, str2);
                if (i2 == iA) {
                    break;
                }
                i2 += 2;
            }
        }
        return new tv(strArr2);
    }

    public static d5 I(String str) throws ProtocolException {
        Protocol protocol;
        int i2;
        String strSubstring;
        j(str, "statusLine");
        if (dl0.j0(str, "HTTP/1.", false)) {
            i2 = 9;
            if (str.length() < 9 || str.charAt(8) != ' ') {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            int iCharAt = str.charAt(7) - '0';
            if (iCharAt == 0) {
                protocol = Protocol.HTTP_1_0;
            } else {
                if (iCharAt != 1) {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                protocol = Protocol.HTTP_1_1;
            }
        } else {
            if (!dl0.j0(str, "ICY ", false)) {
                throw new ProtocolException("Unexpected status line: ".concat(str));
            }
            protocol = Protocol.HTTP_1_0;
            i2 = 4;
        }
        int i3 = i2 + 3;
        if (str.length() < i3) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
        try {
            String strSubstring2 = str.substring(i2, i3);
            i(strSubstring2, "this as java.lang.String…ing(startIndex, endIndex)");
            int i4 = Integer.parseInt(strSubstring2);
            if (str.length() <= i3) {
                strSubstring = ExtensionRequestData.EMPTY_VALUE;
            } else {
                if (str.charAt(i3) != ' ') {
                    throw new ProtocolException("Unexpected status line: ".concat(str));
                }
                strSubstring = str.substring(i2 + 4);
                i(strSubstring, "this as java.lang.String).substring(startIndex)");
            }
            return new d5(protocol, i4, strSubstring);
        } catch (NumberFormatException unused) {
            throw new ProtocolException("Unexpected status line: ".concat(str));
        }
    }

    public static int J(int i2, String str, String str2) {
        if (h > i2) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            str2 = "null";
        }
        return Log.println(i2, str, str2);
    }

    public static final void K(fh fhVar, Object obj) {
        if (!(fhVar instanceof yk)) {
            fhVar.resumeWith(obj);
            return;
        }
        yk ykVar = (yk) fhVar;
        Throwable thM6exceptionOrNullimpl = Result.m6exceptionOrNullimpl(obj);
        Object ueVar = thM6exceptionOrNullimpl == null ? obj : new ue(false, thM6exceptionOrNullimpl);
        ContinuationImpl continuationImpl = ykVar.f;
        continuationImpl.getContext();
        qh qhVar = ykVar.e;
        if (qhVar.D()) {
            ykVar.g = ueVar;
            ykVar.d = 1;
            qhVar.t(continuationImpl.getContext(), ykVar);
            return;
        }
        oo ooVarA = xn0.a();
        if (ooVarA.d >= 4294967296L) {
            ykVar.g = ueVar;
            ykVar.d = 1;
            ooVarA.F(ykVar);
            return;
        }
        ooVarA.H(true);
        try {
            bz bzVar = (bz) continuationImpl.getContext().get(qm.f);
            if (bzVar == null || bzVar.a()) {
                Object obj2 = ykVar.h;
                nh context = continuationImpl.getContext();
                Object objC = wn0.c(context, obj2);
                if (objC != wn0.a) {
                    xi.T(continuationImpl, context);
                }
                try {
                    continuationImpl.resumeWith(obj);
                } finally {
                    wn0.a(context, objC);
                }
            } else {
                CancellationException cancellationExceptionL = ((nz) bzVar).l();
                ykVar.b(ueVar, cancellationExceptionL);
                ykVar.resumeWith(Result.m3constructorimpl(a.a(cancellationExceptionL)));
            }
            while (ooVarA.I()) {
            }
        } finally {
            try {
            } finally {
            }
        }
    }

    public static void L(String str, sd sdVar) {
        try {
            xi.V(str, Base64.decode(sdVar.e.getBytes(), 2));
            String str2 = sdVar.f;
            String str3 = sdVar.g;
            String str4 = sdVar.d;
            SecretKeySpec secretKeySpec = new SecretKeySpec(Base64.decode(str2, 2), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(Base64.decode(str3, 2)));
            byte[] bArrDoFinal = cipher.doFinal(Base64.decode(str4, 2));
            RandomAccessFile randomAccessFile = new RandomAccessFile(str, "rw");
            try {
                randomAccessFile.seek(0L);
                randomAccessFile.write(bArrDoFinal);
                randomAccessFile.close();
            } finally {
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void M(RuntimeException runtimeException, String str) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.equals(stackTrace[i3].getClassName())) {
                i2 = i3;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i2 + 1, length));
    }

    public static void N(ViewGroup viewGroup, float f2) {
        Drawable background = viewGroup.getBackground();
        if (background instanceof u50) {
            ((u50) background).l(f2);
        }
    }

    public static void O(View view, u50 u50Var) {
        bn bnVar = u50Var.b.b;
        if (bnVar == null || !bnVar.a) {
            return;
        }
        float fI = 0.0f;
        for (ViewParent parent = view.getParent(); parent instanceof View; parent = parent.getParent()) {
            WeakHashMap weakHashMap = xr0.a;
            fI += lr0.i((View) parent);
        }
        t50 t50Var = u50Var.b;
        if (t50Var.l != fI) {
            t50Var.l = fI;
            u50Var.v();
        }
    }

    public static void P(ViewGroup viewGroup) {
        Drawable background = viewGroup.getBackground();
        if (background instanceof u50) {
            O(viewGroup, (u50) background);
        }
    }

    public static void Q(ViewGroup viewGroup, boolean z) {
        if (Build.VERSION.SDK_INT >= 29) {
            viewGroup.suppressLayout(z);
        } else if (i) {
            try {
                viewGroup.suppressLayout(z);
            } catch (NoSuchMethodError unused) {
                i = false;
            }
        }
    }

    public static void R() {
        throw new UnsupportedOperationException("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
    }

    public static void S(String str) {
        UninitializedPropertyAccessException uninitializedPropertyAccessException = new UninitializedPropertyAccessException(hk.j("lateinit property ", str, " has not been initialized"));
        M(uninitializedPropertyAccessException, oy.class.getName());
        throw uninitializedPropertyAccessException;
    }

    public static void T(qz qzVar, oz ozVar) throws IOException {
        boolean zBooleanValue;
        boolean z = ozVar instanceof pz;
        if (!z) {
            throw new IllegalArgumentException("Couldn't write " + ozVar.getClass());
        }
        if (!z) {
            throw new IllegalStateException("Not a JSON Primitive: " + ozVar);
        }
        pz pzVar = (pz) ozVar;
        Object obj = pzVar.a;
        boolean z2 = obj instanceof Number;
        StringWriter stringWriter = qzVar.b;
        if (z2) {
            Number numberA = pzVar.a();
            if (numberA == null) {
                qzVar.t();
                return;
            }
            qzVar.E();
            String string = numberA.toString();
            if (qzVar.f || !(string.equals("-Infinity") || string.equals("Infinity") || string.equals("NaN"))) {
                qzVar.a();
                stringWriter.append((CharSequence) string);
                return;
            } else {
                throw new IllegalArgumentException("Numeric values must be finite, but was " + numberA);
            }
        }
        if (obj instanceof Boolean) {
            if (obj != null) {
                zBooleanValue = ((Boolean) obj).booleanValue();
            } else {
                Object obj2 = pzVar.a;
                zBooleanValue = Boolean.parseBoolean(obj2 instanceof Number ? pzVar.a().toString() : obj2 instanceof Boolean ? ((Boolean) obj2).toString() : (String) obj2);
            }
            qzVar.E();
            qzVar.a();
            stringWriter.write(zBooleanValue ? "true" : "false");
            return;
        }
        String string2 = obj instanceof Number ? pzVar.a().toString() : obj instanceof Boolean ? ((Boolean) obj).toString() : (String) obj;
        if (string2 == null) {
            qzVar.t();
            return;
        }
        qzVar.E();
        qzVar.a();
        qzVar.D(string2);
    }

    public static final StackTraceElement a(String str, Exception exc) {
        StackTraceElement stackTraceElement = exc.getStackTrace()[0];
        return new StackTraceElement("_COROUTINE.".concat(str), "_", stackTraceElement.getFileName(), stackTraceElement.getLineNumber());
    }

    public static final void b(vm0 vm0Var, zm0 zm0Var, String str) {
        an0.i.fine(zm0Var.b + ' ' + String.format("%-22s", Arrays.copyOf(new Object[]{str}, 1)) + ": " + vm0Var.a);
    }

    public static boolean c(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static k7 d() throws InterruptedException {
        k7 k7Var = k7.l;
        g(k7Var);
        k7 k7Var2 = k7Var.f;
        if (k7Var2 == null) {
            long jNanoTime = System.nanoTime();
            k7.i.await(k7.j, TimeUnit.MILLISECONDS);
            k7 k7Var3 = k7.l;
            g(k7Var3);
            if (k7Var3.f != null || System.nanoTime() - jNanoTime < k7.k) {
                return null;
            }
            return k7.l;
        }
        long jNanoTime2 = k7Var2.g - System.nanoTime();
        if (jNanoTime2 > 0) {
            k7.i.await(jNanoTime2, TimeUnit.NANOSECONDS);
            return null;
        }
        k7 k7Var4 = k7.l;
        g(k7Var4);
        k7Var4.f = k7Var2.f;
        k7Var2.f = null;
        return k7Var2;
    }

    public static final String e(Number number, Number number2) {
        return "Random range is empty: [" + number + ", " + number2 + ").";
    }

    public static void f(String str) {
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if ('!' > cCharAt || cCharAt >= 127) {
                throw new IllegalArgumentException(kq0.i("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i2), str).toString());
            }
        }
    }

    public static void g(Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException();
        M(nullPointerException, oy.class.getName());
        throw nullPointerException;
    }

    public static void h(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str);
        M(nullPointerException, oy.class.getName());
        throw nullPointerException;
    }

    public static void i(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str.concat(" must not be null"));
        M(nullPointerException, oy.class.getName());
        throw nullPointerException;
    }

    public static void j(Object obj, String str) {
        if (obj == null) {
            StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
            String name = oy.class.getName();
            int i2 = 0;
            while (!stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            while (stackTrace[i2].getClassName().equals(name)) {
                i2++;
            }
            StackTraceElement stackTraceElement = stackTrace[i2];
            NullPointerException nullPointerException = new NullPointerException("Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str);
            M(nullPointerException, oy.class.getName());
            throw nullPointerException;
        }
    }

    public static void k(String str, String str2) {
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt != '\t' && (' ' > cCharAt || cCharAt >= 127)) {
                StringBuilder sb = new StringBuilder();
                sb.append(kq0.i("Unexpected char %#04x at %d in %s value", Integer.valueOf(cCharAt), Integer.valueOf(i2), str2));
                sb.append(kq0.q(str2) ? ExtensionRequestData.EMPTY_VALUE : ": ".concat(str));
                throw new IllegalArgumentException(sb.toString().toString());
            }
        }
    }

    public static final Object m(qq qqVar, oq[] oqVarArr, xb xbVar, su suVar, SuspendLambda suspendLambda) throws Throwable {
        Object ueVar;
        Object objS;
        sx sxVar;
        ne neVar = new ne(oqVarArr, xbVar, suVar, qqVar, null);
        rq rqVar = new rq(suspendLambda.getContext(), suspendLambda);
        try {
            rp0.e(2, neVar);
            ueVar = neVar.invoke(rqVar, rqVar);
        } catch (Throwable th) {
            ueVar = new ue(false, th);
        }
        CoroutineSingletons coroutineSingletons = CoroutineSingletons.COROUTINE_SUSPENDED;
        if (ueVar == coroutineSingletons || (objS = rqVar.s(ueVar)) == nv0.d) {
            objS = coroutineSingletons;
        } else {
            if (objS instanceof ue) {
                throw ((ue) objS).a;
            }
            tx txVar = objS instanceof tx ? (tx) objS : null;
            if (txVar != null && (sxVar = txVar.a) != null) {
                objS = sxVar;
            }
        }
        return objS == coroutineSingletons ? objS : bq0.a;
    }

    public static int n(int i2, int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i2 == i3 ? 0 : 1;
    }

    public static String o(Context context) {
        InputStream inputStreamOpen;
        File file = new File(context.getCacheDir(), "data.zip");
        try {
            inputStreamOpen = context.getAssets().open("data.zip");
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[4096];
                while (true) {
                    int i2 = inputStreamOpen.read(bArr);
                    if (i2 <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, i2);
                }
                fileOutputStream.close();
                inputStreamOpen.close();
                return file.getAbsolutePath();
            } finally {
            }
        } finally {
        }
    }

    public static nv0 p(int i2) {
        return i2 != 0 ? i2 != 1 ? new yf0() : new ii() : new yf0();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static fh q(final pu puVar, final fh fhVar) {
        j(puVar, "<this>");
        j(fhVar, "completion");
        if (puVar instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) puVar).create(fhVar);
        }
        final nh context = fhVar.getContext();
        return context == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(fhVar, puVar) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$1
            public int c;
            public final /* synthetic */ pu d;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(fhVar);
                this.d = puVar;
                oy.h(fhVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                int i2 = this.c;
                if (i2 != 0) {
                    if (i2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                    this.c = 2;
                    a.b(obj);
                    return obj;
                }
                this.c = 1;
                a.b(obj);
                pu puVar2 = this.d;
                oy.h(puVar2, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                rp0.e(1, puVar2);
                return puVar2.invoke(this);
            }
        } : new ContinuationImpl(fhVar, context, puVar) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$2
            public int e;
            public final /* synthetic */ pu f;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(fhVar, context);
                this.f = puVar;
                oy.h(fhVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) throws Throwable {
                int i2 = this.e;
                if (i2 != 0) {
                    if (i2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                    this.e = 2;
                    a.b(obj);
                    return obj;
                }
                this.e = 1;
                a.b(obj);
                pu puVar2 = this.f;
                oy.h(puVar2, "null cannot be cast to non-null type kotlin.Function1<kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$0>, kotlin.Any?>");
                rp0.e(1, puVar2);
                return puVar2.invoke(this);
            }
        };
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static fh r(final ru ruVar, final Object obj, final fh fhVar) {
        j(ruVar, "<this>");
        j(fhVar, "completion");
        if (ruVar instanceof BaseContinuationImpl) {
            return ((BaseContinuationImpl) ruVar).create(obj, fhVar);
        }
        final nh context = fhVar.getContext();
        return context == EmptyCoroutineContext.INSTANCE ? new RestrictedContinuationImpl(fhVar, ruVar, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$3
            public int c;
            public final /* synthetic */ ru d;
            public final /* synthetic */ Object e;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(fhVar);
                this.d = ruVar;
                this.e = obj;
                oy.h(fhVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj2) throws Throwable {
                int i2 = this.c;
                if (i2 != 0) {
                    if (i2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                    this.c = 2;
                    a.b(obj2);
                    return obj2;
                }
                this.c = 1;
                a.b(obj2);
                ru ruVar2 = this.d;
                oy.h(ruVar2, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                rp0.e(2, ruVar2);
                return ruVar2.invoke(this.e, this);
            }
        } : new ContinuationImpl(fhVar, context, ruVar, obj) { // from class: kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt$createCoroutineUnintercepted$$inlined$createCoroutineFromSuspendFunction$IntrinsicsKt__IntrinsicsJvmKt$4
            public int e;
            public final /* synthetic */ ru f;
            public final /* synthetic */ Object g;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(fhVar, context);
                this.f = ruVar;
                this.g = obj;
                oy.h(fhVar, "null cannot be cast to non-null type kotlin.coroutines.Continuation<kotlin.Any?>");
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj2) throws Throwable {
                int i2 = this.e;
                if (i2 != 0) {
                    if (i2 != 1) {
                        throw new IllegalStateException("This coroutine had already completed");
                    }
                    this.e = 2;
                    a.b(obj2);
                    return obj2;
                }
                this.e = 1;
                a.b(obj2);
                ru ruVar2 = this.f;
                oy.h(ruVar2, "null cannot be cast to non-null type kotlin.Function2<R of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1, kotlin.coroutines.Continuation<T of kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsJvmKt.createCoroutineUnintercepted$lambda$1>, kotlin.Any?>");
                rp0.e(2, ruVar2);
                return ruVar2.invoke(this.g, this);
            }
        };
    }

    public static void s(String str, String str2) {
        J(6, str, str2);
    }

    public static final String t(long j2) {
        String str;
        if (j2 <= -999500000) {
            str = ((j2 - ((long) 500000000)) / ((long) 1000000000)) + " s ";
        } else if (j2 <= -999500) {
            str = ((j2 - ((long) 500000)) / ((long) 1000000)) + " ms";
        } else if (j2 <= 0) {
            str = ((j2 - ((long) 500)) / ((long) CloseFrame.NORMAL)) + " µs";
        } else if (j2 < 999500) {
            str = ((j2 + ((long) 500)) / ((long) CloseFrame.NORMAL)) + " µs";
        } else if (j2 < 999500000) {
            str = ((j2 + ((long) 500000)) / ((long) 1000000)) + " ms";
        } else {
            str = ((j2 + ((long) 500000000)) / ((long) 1000000000)) + " s ";
        }
        return String.format("%6s", Arrays.copyOf(new Object[]{str}, 1));
    }

    public static String u(Context context) {
        try {
            String strQ = xi.Q(context, "androidid");
            if (strQ != null) {
                return strQ;
            }
            String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
            xi.U(context, "androidid", string);
            return string;
        } catch (Exception unused) {
            return null;
        }
    }

    public static String v() {
        StackTraceElement stackTraceElement = new Throwable().getStackTrace()[1];
        return ".(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
    }

    public static String w(Context context, int i2) {
        if (i2 == -1) {
            return "UNKNOWN";
        }
        try {
            return context.getResources().getResourceEntryName(i2);
        } catch (Exception unused) {
            return hk.g(i2, "?");
        }
    }

    public static String x(View view) {
        try {
            return view.getContext().getResources().getResourceEntryName(view.getId());
        } catch (Exception unused) {
            return "UNKNOWN";
        }
    }

    public static Intent y(AppCompatActivity appCompatActivity) {
        Intent parentActivityIntent = appCompatActivity.getParentActivityIntent();
        if (parentActivityIntent != null) {
            return parentActivityIntent;
        }
        try {
            String strA = A(appCompatActivity, appCompatActivity.getComponentName());
            if (strA == null) {
                return null;
            }
            ComponentName componentName = new ComponentName(appCompatActivity, strA);
            try {
                return A(appCompatActivity, componentName) == null ? Intent.makeMainActivity(componentName) : new Intent().setComponent(componentName);
            } catch (PackageManager.NameNotFoundException unused) {
                Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + strA + "' in manifest");
                return null;
            }
        } catch (PackageManager.NameNotFoundException e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static Intent z(AppCompatActivity appCompatActivity, ComponentName componentName) throws PackageManager.NameNotFoundException {
        String strA = A(appCompatActivity, componentName);
        if (strA == null) {
            return null;
        }
        ComponentName componentName2 = new ComponentName(componentName.getPackageName(), strA);
        return A(appCompatActivity, componentName2) == null ? Intent.makeMainActivity(componentName2) : new Intent().setComponent(componentName2);
    }

    public abstract List l(List list, String str);
}
