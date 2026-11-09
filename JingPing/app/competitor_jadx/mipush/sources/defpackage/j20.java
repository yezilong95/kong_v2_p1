package defpackage;

import androidx.work.Configuration;
import androidx.work.impl.WorkDatabaseVersions;
import androidx.work.impl.WorkManagerImpl;
import com.google.gson.internal.LazilyParsedNumber;
import io.nats.client.BaseConsumeOptions;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.InetAddress;
import java.net.URI;
import java.net.URL;
import java.util.BitSet;
import java.util.Calendar;
import java.util.Currency;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/* JADX INFO: loaded from: classes.dex */
public final class j20 extends e21 {
    public final /* synthetic */ int a;

    public /* synthetic */ j20(int i) {
        this.a = i;
    }

    public static w50 c(a60 a60Var) {
        switch (j21.a[a60Var.O().ordinal()]) {
            case 1:
                return new z50(new LazilyParsedNumber(a60Var.M()));
            case 2:
                Boolean boolValueOf = Boolean.valueOf(a60Var.E());
                z50 z50Var = new z50();
                z50Var.d(boolValueOf);
                return z50Var;
            case 3:
                return new z50(a60Var.M());
            case 4:
                a60Var.K();
                return x50.E;
            case 5:
                v50 v50Var = new v50();
                a60Var.j();
                while (a60Var.B()) {
                    v50Var.E.add(c(a60Var));
                }
                a60Var.y();
                return v50Var;
            case 6:
                y50 y50Var = new y50();
                a60Var.l();
                while (a60Var.B()) {
                    y50Var.E.put(a60Var.I(), c(a60Var));
                }
                a60Var.z();
                return y50Var;
            default:
                throw new IllegalArgumentException();
        }
    }

    public static void d(f60 f60Var, w50 w50Var) throws IOException {
        if (w50Var == null || (w50Var instanceof x50)) {
            f60Var.C();
            return;
        }
        boolean z = w50Var instanceof z50;
        if (z) {
            if (!z) {
                throw new IllegalStateException("Not a JSON Primitive: " + w50Var);
            }
            z50 z50Var = (z50) w50Var;
            Serializable serializable = z50Var.E;
            if (serializable instanceof Number) {
                f60Var.H(z50Var.a());
                return;
            } else if (serializable instanceof Boolean) {
                f60Var.J(serializable instanceof Boolean ? ((Boolean) serializable).booleanValue() : Boolean.parseBoolean(z50Var.b()));
                return;
            } else {
                f60Var.I(z50Var.b());
                return;
            }
        }
        boolean z2 = w50Var instanceof v50;
        if (z2) {
            f60Var.l();
            if (!z2) {
                throw new IllegalStateException("Not a JSON Array: " + w50Var);
            }
            Iterator it = ((v50) w50Var).E.iterator();
            while (it.hasNext()) {
                d(f60Var, (w50) it.next());
            }
            f60Var.y();
            return;
        }
        boolean z3 = w50Var instanceof y50;
        if (!z3) {
            throw new IllegalArgumentException("Couldn't write " + w50Var.getClass());
        }
        f60Var.w();
        if (!z3) {
            throw new IllegalStateException("Not a JSON Object: " + w50Var);
        }
        for (Map.Entry entry : ((y50) w50Var).E.entrySet()) {
            f60Var.A((String) entry.getKey());
            d(f60Var, (w50) entry.getValue());
        }
        f60Var.z();
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00e8  */
    @Override // defpackage.e21
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object a(defpackage.a60 r10) {
        /*
            Method dump skipped, instruction units count: 1118
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.j20.a(a60):java.lang.Object");
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) throws IOException {
        switch (this.a) {
            case 0:
                Number number = (Number) obj;
                if (number == null) {
                    f60Var.C();
                    return;
                } else {
                    m20.a(number.doubleValue());
                    f60Var.H(number);
                    return;
                }
            case 1:
                Number number2 = (Number) obj;
                if (number2 == null) {
                    f60Var.C();
                    return;
                } else {
                    m20.a(number2.floatValue());
                    f60Var.H(number2);
                    return;
                }
            case 2:
                f60Var.l();
                int length = ((AtomicIntegerArray) obj).length();
                for (int i = 0; i < length; i++) {
                    f60Var.G(r6.get(i));
                }
                f60Var.y();
                return;
            case 3:
                f60Var.H((Number) obj);
                return;
            case 4:
                f60Var.H((Number) obj);
                return;
            case 5:
                f60Var.H((Number) obj);
                return;
            case 6:
                f60Var.H((Number) obj);
                return;
            case 7:
                Character ch = (Character) obj;
                f60Var.I(ch == null ? null : String.valueOf(ch));
                return;
            case 8:
                f60Var.I((String) obj);
                return;
            case WorkDatabaseVersions.VERSION_9 /* 9 */:
                f60Var.H((BigDecimal) obj);
                return;
            case 10:
                f60Var.H((BigInteger) obj);
                return;
            case WorkDatabaseVersions.VERSION_11 /* 11 */:
                StringBuilder sb = (StringBuilder) obj;
                f60Var.I(sb == null ? null : sb.toString());
                return;
            case WorkDatabaseVersions.VERSION_12 /* 12 */:
                throw new UnsupportedOperationException("Attempted to serialize java.lang.Class: " + ((Class) obj).getName() + ". Forgot to register a type adapter?");
            case WorkDatabaseVersions.VERSION_13 /* 13 */:
                StringBuffer stringBuffer = (StringBuffer) obj;
                f60Var.I(stringBuffer == null ? null : stringBuffer.toString());
                return;
            case WorkDatabaseVersions.VERSION_14 /* 14 */:
                URL url = (URL) obj;
                f60Var.I(url == null ? null : url.toExternalForm());
                return;
            case WorkDatabaseVersions.VERSION_15 /* 15 */:
                URI uri = (URI) obj;
                f60Var.I(uri == null ? null : uri.toASCIIString());
                return;
            case 16:
                InetAddress inetAddress = (InetAddress) obj;
                f60Var.I(inetAddress == null ? null : inetAddress.getHostAddress());
                return;
            case 17:
                UUID uuid = (UUID) obj;
                f60Var.I(uuid == null ? null : uuid.toString());
                return;
            case 18:
                f60Var.I(((Currency) obj).getCurrencyCode());
                return;
            case 19:
                if (((Calendar) obj) == null) {
                    f60Var.C();
                    return;
                }
                f60Var.w();
                f60Var.A("year");
                f60Var.G(r6.get(1));
                f60Var.A("month");
                f60Var.G(r6.get(2));
                f60Var.A("dayOfMonth");
                f60Var.G(r6.get(5));
                f60Var.A("hourOfDay");
                f60Var.G(r6.get(11));
                f60Var.A("minute");
                f60Var.G(r6.get(12));
                f60Var.A("second");
                f60Var.G(r6.get(13));
                f60Var.z();
                return;
            case Configuration.MIN_SCHEDULER_LIMIT /* 20 */:
                Locale locale = (Locale) obj;
                f60Var.I(locale == null ? null : locale.toString());
                return;
            case 21:
                d(f60Var, (w50) obj);
                return;
            case WorkManagerImpl.MAX_PRE_JOB_SCHEDULER_API_LEVEL /* 22 */:
                BitSet bitSet = (BitSet) obj;
                f60Var.l();
                int length2 = bitSet.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    f60Var.G(bitSet.get(i2) ? 1L : 0L);
                }
                f60Var.y();
                return;
            case WorkManagerImpl.MIN_JOB_SCHEDULER_API_LEVEL /* 23 */:
                Boolean bool = (Boolean) obj;
                if (bool == null) {
                    f60Var.C();
                    return;
                }
                f60Var.K();
                f60Var.j();
                f60Var.E.write(bool.booleanValue() ? "true" : "false");
                return;
            case 24:
                Boolean bool2 = (Boolean) obj;
                f60Var.I(bool2 == null ? "null" : bool2.toString());
                return;
            case BaseConsumeOptions.DEFAULT_THRESHOLD_PERCENT /* 25 */:
                f60Var.H((Number) obj);
                return;
            case 26:
                f60Var.H((Number) obj);
                return;
            case 27:
                f60Var.H((Number) obj);
                return;
            case 28:
                f60Var.G(((AtomicInteger) obj).get());
                return;
            default:
                f60Var.J(((AtomicBoolean) obj).get());
                return;
        }
    }
}
