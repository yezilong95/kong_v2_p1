package defpackage;

import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import kotlin.text.Regex;
import org.java_websocket.WebSocketImpl;

/* JADX INFO: loaded from: classes.dex */
public abstract class kq0 {
    public static final byte[] a;
    public static final tv b = oy.H(new String[0]);
    public static final hf0 c;
    public static final bb0 d;
    public static final TimeZone e;
    public static final Regex f;
    public static final String g;

    /* JADX WARN: Code restructure failed: missing block: B:83:0x0181, code lost:
    
        continue;
     */
    static {
        /*
            Method dump skipped, instruction units count: 512
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kq0.<clinit>():void");
    }

    public static final boolean a(fx fxVar, fx fxVar2) {
        oy.j(fxVar, "<this>");
        oy.j(fxVar2, "other");
        return oy.c(fxVar.d, fxVar2.d) && fxVar.e == fxVar2.e && oy.c(fxVar.a, fxVar2.a);
    }

    public static final int b(long j) {
        TimeUnit timeUnit = TimeUnit.SECONDS;
        if (j < 0) {
            throw new IllegalStateException("timeout".concat(" < 0").toString());
        }
        if (timeUnit == null) {
            throw new IllegalStateException("unit == null");
        }
        long millis = timeUnit.toMillis(j);
        if (millis > 2147483647L) {
            throw new IllegalArgumentException("timeout".concat(" too large.").toString());
        }
        if (millis != 0 || j <= 0) {
            return (int) millis;
        }
        throw new IllegalArgumentException("timeout".concat(" too small.").toString());
    }

    public static final void c(long j, long j2, long j3) {
        if ((j2 | j3) < 0 || j2 > j || j - j2 < j3) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static final void d(Closeable closeable) {
        oy.j(closeable, "<this>");
        try {
            closeable.close();
        } catch (RuntimeException e2) {
            throw e2;
        } catch (Exception unused) {
        }
    }

    public static final void e(Socket socket) {
        oy.j(socket, "<this>");
        try {
            socket.close();
        } catch (AssertionError e2) {
            throw e2;
        } catch (RuntimeException e3) {
            if (!oy.c(e3.getMessage(), "bio == null")) {
                throw e3;
            }
        } catch (Exception unused) {
        }
    }

    public static final int f(int i, int i2, String str, String str2) {
        while (i < i2) {
            if (vk0.k0(str2, str.charAt(i))) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int g(String str, char c2, int i, int i2) {
        while (i < i2) {
            if (str.charAt(i) == c2) {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final boolean h(uj0 uj0Var) {
        oy.j(TimeUnit.MILLISECONDS, "timeUnit");
        try {
            return u(uj0Var, 100);
        } catch (IOException unused) {
            return false;
        }
    }

    public static final String i(String str, Object... objArr) {
        oy.j(str, "format");
        Locale locale = Locale.US;
        Object[] objArrCopyOf = Arrays.copyOf(objArr, objArr.length);
        return String.format(locale, str, Arrays.copyOf(objArrCopyOf, objArrCopyOf.length));
    }

    public static final boolean j(String[] strArr, String[] strArr2, Comparator comparator) {
        oy.j(strArr, "<this>");
        if (strArr.length != 0 && strArr2 != null && strArr2.length != 0) {
            for (String str : strArr) {
                i iVarW = k9.w(strArr2);
                while (iVarW.hasNext()) {
                    if (comparator.compare(str, (String) iVarW.next()) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final long k(gf0 gf0Var) {
        String strA = gf0Var.g.a("Content-Length");
        if (strA == null) {
            return -1L;
        }
        try {
            return Long.parseLong(strA);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public static final List l(Object... objArr) {
        oy.j(objArr, "elements");
        Object[] objArr2 = (Object[]) objArr.clone();
        List listUnmodifiableList = Collections.unmodifiableList(be.X(Arrays.copyOf(objArr2, objArr2.length)));
        oy.i(listUnmodifiableList, "unmodifiableList(listOf(*elements.clone()))");
        return listUnmodifiableList;
    }

    public static final int m(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (oy.n(cCharAt, 31) <= 0 || oy.n(cCharAt, 127) >= 0) {
                return i;
            }
        }
        return -1;
    }

    public static final int n(String str, int i, int i2) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                return i;
            }
            i++;
        }
        return i2;
    }

    public static final int o(String str, int i, int i2) {
        int i3 = i2 - 1;
        if (i <= i3) {
            while (true) {
                char cCharAt = str.charAt(i3);
                if (cCharAt != '\t' && cCharAt != '\n' && cCharAt != '\f' && cCharAt != '\r' && cCharAt != ' ') {
                    return i3 + 1;
                }
                if (i3 == i) {
                    break;
                }
                i3--;
            }
        }
        return i;
    }

    public static final String[] p(String[] strArr, String[] strArr2, Comparator comparator) {
        oy.j(strArr2, "other");
        ArrayList arrayList = new ArrayList();
        for (String str : strArr) {
            int length = strArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                if (comparator.compare(str, strArr2[i]) == 0) {
                    arrayList.add(str);
                    break;
                }
                i++;
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    public static final boolean q(String str) {
        oy.j(str, "name");
        return str.equalsIgnoreCase("Authorization") || str.equalsIgnoreCase("Cookie") || str.equalsIgnoreCase("Proxy-Authorization") || str.equalsIgnoreCase("Set-Cookie");
    }

    public static final int r(char c2) {
        if ('0' <= c2 && c2 < ':') {
            return c2 - '0';
        }
        if ('a' <= c2 && c2 < 'g') {
            return c2 - 'W';
        }
        if ('A' > c2 || c2 >= 'G') {
            return -1;
        }
        return c2 - '7';
    }

    public static final Charset s(w9 w9Var, Charset charset) {
        oy.j(w9Var, "<this>");
        oy.j(charset, "default");
        int iO = w9Var.o(d);
        if (iO == -1) {
            return charset;
        }
        if (iO == 0) {
            Charset charset2 = StandardCharsets.UTF_8;
            oy.i(charset2, "UTF_8");
            return charset2;
        }
        if (iO == 1) {
            Charset charset3 = StandardCharsets.UTF_16BE;
            oy.i(charset3, "UTF_16BE");
            return charset3;
        }
        if (iO == 2) {
            Charset charset4 = StandardCharsets.UTF_16LE;
            oy.i(charset4, "UTF_16LE");
            return charset4;
        }
        if (iO == 3) {
            Charset charset5 = zb.a;
            Charset charset6 = zb.c;
            if (charset6 != null) {
                return charset6;
            }
            Charset charsetForName = Charset.forName("UTF-32BE");
            oy.i(charsetForName, "forName(...)");
            zb.c = charsetForName;
            return charsetForName;
        }
        if (iO != 4) {
            throw new AssertionError();
        }
        Charset charset7 = zb.a;
        Charset charset8 = zb.b;
        if (charset8 != null) {
            return charset8;
        }
        Charset charsetForName2 = Charset.forName("UTF-32LE");
        oy.i(charsetForName2, "forName(...)");
        zb.b = charsetForName2;
        return charsetForName2;
    }

    public static final int t(sc0 sc0Var) {
        oy.j(sc0Var, "<this>");
        return (sc0Var.C() & 255) | ((sc0Var.C() & 255) << 16) | ((sc0Var.C() & 255) << 8);
    }

    public static final boolean u(uj0 uj0Var, int i) {
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        oy.j(timeUnit, "timeUnit");
        long jNanoTime = System.nanoTime();
        long jC = uj0Var.b().e() ? uj0Var.b().c() - jNanoTime : Long.MAX_VALUE;
        uj0Var.b().d(Math.min(jC, timeUnit.toNanos(i)) + jNanoTime);
        try {
            p9 p9Var = new p9();
            while (uj0Var.d(8192L, p9Var) != -1) {
                p9Var.I(p9Var.c);
            }
            if (jC == Long.MAX_VALUE) {
                uj0Var.b().a();
                return true;
            }
            uj0Var.b().d(jNanoTime + jC);
            return true;
        } catch (InterruptedIOException unused) {
            if (jC == Long.MAX_VALUE) {
                uj0Var.b().a();
                return false;
            }
            uj0Var.b().d(jNanoTime + jC);
            return false;
        } catch (Throwable th) {
            if (jC == Long.MAX_VALUE) {
                uj0Var.b().a();
            } else {
                uj0Var.b().d(jNanoTime + jC);
            }
            throw th;
        }
    }

    public static final tv v(List list) {
        ArrayList arrayList = new ArrayList(20);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            sv svVar = (sv) it.next();
            String strUtf8 = svVar.a.utf8();
            String strUtf82 = svVar.b.utf8();
            oy.j(strUtf8, "name");
            oy.j(strUtf82, "value");
            arrayList.add(strUtf8);
            arrayList.add(vk0.B0(strUtf82).toString());
        }
        return new tv((String[]) arrayList.toArray(new String[0]));
    }

    public static final String w(fx fxVar, boolean z) {
        oy.j(fxVar, "<this>");
        String str = fxVar.d;
        if (vk0.l0(str, ":")) {
            str = "[" + str + ']';
        }
        int i = fxVar.e;
        if (!z) {
            String str2 = fxVar.a;
            oy.j(str2, "scheme");
            if (i == (str2.equals("http") ? 80 : str2.equals("https") ? WebSocketImpl.DEFAULT_WSS_PORT : -1)) {
                return str;
            }
        }
        return str + ':' + i;
    }

    public static final List x(List list) {
        oy.j(list, "<this>");
        List listUnmodifiableList = Collections.unmodifiableList(new ArrayList(list));
        oy.i(listUnmodifiableList, "unmodifiableList(toMutableList())");
        return listUnmodifiableList;
    }

    public static final int y(int i, String str) {
        if (str == null) {
            return i;
        }
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return Integer.MAX_VALUE;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static final String z(String str, int i, int i2) {
        int iN = n(str, i, i2);
        String strSubstring = str.substring(iN, o(str, iN, i2));
        oy.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
        return strSubstring;
    }
}
