package okhttp3.internal.publicsuffix;

import defpackage.ae;
import defpackage.dm;
import defpackage.ga0;
import defpackage.he;
import defpackage.hk;
import defpackage.j7;
import defpackage.jo0;
import defpackage.mv;
import defpackage.oy;
import defpackage.rb0;
import defpackage.rp0;
import defpackage.sc0;
import defpackage.vk0;
import defpackage.xh0;
import defpackage.xi;
import defpackage.yo;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.IDN;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Ref$ObjectRef;
import org.java_websocket.extensions.ExtensionRequestData;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public final class PublicSuffixDatabase {
    public static final byte[] e = {42};
    public static final List f = xi.G(Marker.ANY_MARKER);
    public static final PublicSuffixDatabase g = new PublicSuffixDatabase();
    public final AtomicBoolean a = new AtomicBoolean(false);
    public final CountDownLatch b = new CountDownLatch(1);
    public byte[] c;
    public byte[] d;

    public static List c(String str) {
        List listX0 = vk0.x0(str, new char[]{'.'});
        if (listX0.isEmpty()) {
            throw new NoSuchElementException("List is empty.");
        }
        if (!oy.c(listX0.get(listX0.size() - 1), ExtensionRequestData.EMPTY_VALUE)) {
            return listX0;
        }
        int size = listX0.size() - 1;
        if (size < 0) {
            size = 0;
        }
        if (size < 0) {
            throw new IllegalArgumentException(hk.h(size, "Requested element count ", " is less than zero.").toString());
        }
        if (size == 0) {
            return EmptyList.INSTANCE;
        }
        if (size >= listX0.size()) {
            return ae.f0(listX0);
        }
        if (size == 1) {
            if (listX0.isEmpty()) {
                throw new NoSuchElementException("List is empty.");
            }
            return xi.G(listX0.get(0));
        }
        ArrayList arrayList = new ArrayList(size);
        Iterator it = listX0.iterator();
        int i = 0;
        while (it.hasNext()) {
            arrayList.add(it.next());
            i++;
            if (i == size) {
                break;
            }
        }
        int size2 = arrayList.size();
        return size2 != 0 ? size2 != 1 ? arrayList : xi.G(arrayList.get(0)) : EmptyList.INSTANCE;
    }

    public final String a(String str) {
        String strG;
        String strG2;
        String strG3;
        List listX0;
        int size;
        int size2;
        int i = 0;
        String unicode = IDN.toUnicode(str);
        oy.i(unicode, "unicodeDomain");
        List listC = c(unicode);
        if (this.a.get() || !this.a.compareAndSet(false, true)) {
            try {
                this.b.await();
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        } else {
            boolean z = false;
            while (true) {
                try {
                    try {
                        b();
                        break;
                    } catch (InterruptedIOException unused2) {
                        Thread.interrupted();
                        z = true;
                    } catch (IOException e2) {
                        rb0 rb0Var = rb0.a;
                        rb0.a.getClass();
                        rb0.i(5, "Failed to read public suffix list", e2);
                        if (z) {
                        }
                    }
                } finally {
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
        if (this.c == null) {
            throw new IllegalStateException("Unable to load publicsuffixes.gz resource from the classpath.");
        }
        int size3 = listC.size();
        byte[][] bArr = new byte[size3][];
        for (int i2 = 0; i2 < size3; i2++) {
            String str2 = (String) listC.get(i2);
            Charset charset = StandardCharsets.UTF_8;
            oy.i(charset, "UTF_8");
            byte[] bytes = str2.getBytes(charset);
            oy.i(bytes, "this as java.lang.String).getBytes(charset)");
            bArr[i2] = bytes;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= size3) {
                strG = null;
                break;
            }
            byte[] bArr2 = this.c;
            if (bArr2 == null) {
                oy.S("publicSuffixListBytes");
                throw null;
            }
            strG = yo.g(bArr2, bArr, i3);
            if (strG != null) {
                break;
            }
            i3++;
        }
        if (size3 > 1) {
            byte[][] bArr3 = (byte[][]) bArr.clone();
            int length = bArr3.length - 1;
            for (int i4 = 0; i4 < length; i4++) {
                bArr3[i4] = e;
                byte[] bArr4 = this.c;
                if (bArr4 == null) {
                    oy.S("publicSuffixListBytes");
                    throw null;
                }
                strG2 = yo.g(bArr4, bArr3, i4);
                if (strG2 != null) {
                    break;
                }
            }
            strG2 = null;
        } else {
            strG2 = null;
        }
        if (strG2 != null) {
            int i5 = size3 - 1;
            for (int i6 = 0; i6 < i5; i6++) {
                byte[] bArr5 = this.d;
                if (bArr5 == null) {
                    oy.S("publicSuffixExceptionListBytes");
                    throw null;
                }
                strG3 = yo.g(bArr5, bArr, i6);
                if (strG3 != null) {
                    break;
                }
            }
            strG3 = null;
        } else {
            strG3 = null;
        }
        if (strG3 != null) {
            listX0 = vk0.x0("!".concat(strG3), new char[]{'.'});
        } else if (strG == null && strG2 == null) {
            listX0 = f;
        } else {
            List listX02 = strG != null ? vk0.x0(strG, new char[]{'.'}) : EmptyList.INSTANCE;
            listX0 = strG2 != null ? vk0.x0(strG2, new char[]{'.'}) : EmptyList.INSTANCE;
            if (listX02.size() > listX0.size()) {
                listX0 = listX02;
            }
        }
        if (listC.size() == listX0.size() && ((String) listX0.get(0)).charAt(0) != '!') {
            return null;
        }
        if (((String) listX0.get(0)).charAt(0) == '!') {
            size = listC.size();
            size2 = listX0.size();
        } else {
            size = listC.size();
            size2 = listX0.size() + 1;
        }
        int i7 = size - size2;
        List listC2 = c(str);
        oy.j(listC2, "<this>");
        xh0 heVar = new he(0, listC2);
        if (i7 < 0) {
            throw new IllegalArgumentException(hk.h(i7, "Requested element count ", " is less than zero.").toString());
        }
        if (i7 != 0) {
            heVar = new dm(heVar, i7);
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) ExtensionRequestData.EMPTY_VALUE);
        for (Object obj : heVar) {
            i++;
            if (i > 1) {
                sb.append((CharSequence) ".");
            }
            rp0.c(sb, obj, null);
        }
        sb.append((CharSequence) ExtensionRequestData.EMPTY_VALUE);
        String string = sb.toString();
        oy.i(string, "toString(...)");
        return string;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v4, types: [T, byte[]] */
    /* JADX WARN: Type inference failed for: r3v7, types: [T, byte[]] */
    public final void b() {
        try {
            Ref$ObjectRef ref$ObjectRef = new Ref$ObjectRef();
            Ref$ObjectRef ref$ObjectRef2 = new Ref$ObjectRef();
            InputStream resourceAsStream = PublicSuffixDatabase.class.getResourceAsStream("publicsuffixes.gz");
            if (resourceAsStream != null) {
                Logger logger = ga0.a;
                sc0 sc0Var = new sc0(new mv(new j7(resourceAsStream, 1, new jo0())));
                try {
                    long jF = sc0Var.F();
                    sc0Var.K(jF);
                    ref$ObjectRef.element = sc0Var.c.D(jF);
                    long jF2 = sc0Var.F();
                    sc0Var.K(jF2);
                    ref$ObjectRef2.element = sc0Var.c.D(jF2);
                    sc0Var.close();
                    synchronized (this) {
                        T t = ref$ObjectRef.element;
                        oy.g(t);
                        this.c = (byte[]) t;
                        T t2 = ref$ObjectRef2.element;
                        oy.g(t2);
                        this.d = (byte[]) t2;
                    }
                } finally {
                }
            }
        } finally {
            this.b.countDown();
        }
    }
}
