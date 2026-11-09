package defpackage;

import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonToken;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class vw0 extends e21 {
    public static final o8 c = new o8(3);
    public static final o8 d = new o8(4);
    public static final o8 e = new o8(1);
    public final /* synthetic */ int a;
    public final Object b;

    public vw0(int i) {
        this.a = i;
        switch (i) {
            case 1:
                this.b = new SimpleDateFormat("hh:mm:ss a");
                break;
            case 2:
                ArrayList arrayList = new ArrayList();
                this.b = arrayList;
                Locale locale = Locale.US;
                arrayList.add(DateFormat.getDateTimeInstance(2, 2, locale));
                if (!Locale.getDefault().equals(locale)) {
                    arrayList.add(DateFormat.getDateTimeInstance(2, 2));
                }
                if (e50.a >= 9) {
                    arrayList.add(new SimpleDateFormat("MMM d, yyyy h:mm:ss a", locale));
                }
                break;
            default:
                this.b = new SimpleDateFormat("MMM d, yyyy");
                break;
        }
    }

    @Override // defpackage.e21
    public final Object a(a60 a60Var) {
        switch (this.a) {
            case 0:
                synchronized (this) {
                    if (a60Var.O() == JsonToken.NULL) {
                        a60Var.K();
                        return null;
                    }
                    try {
                        return new Date(((SimpleDateFormat) this.b).parse(a60Var.M()).getTime());
                    } catch (ParseException e2) {
                        throw new JsonSyntaxException(e2);
                    }
                }
            case 1:
                synchronized (this) {
                    if (a60Var.O() == JsonToken.NULL) {
                        a60Var.K();
                        return null;
                    }
                    try {
                        return new Time(((SimpleDateFormat) this.b).parse(a60Var.M()).getTime());
                    } catch (ParseException e3) {
                        throw new JsonSyntaxException(e3);
                    }
                }
            default:
                if (a60Var.O() == JsonToken.NULL) {
                    a60Var.K();
                    return null;
                }
                String strM = a60Var.M();
                synchronized (this) {
                    Iterator it = ((ArrayList) this.b).iterator();
                    while (it.hasNext()) {
                        try {
                            return ((DateFormat) it.next()).parse(strM);
                        } catch (ParseException unused) {
                        }
                    }
                    try {
                        return j30.b(strM, new ParsePosition(0));
                    } catch (ParseException e4) {
                        throw new JsonSyntaxException(strM, e4);
                    }
                }
        }
    }

    @Override // defpackage.e21
    public final void b(f60 f60Var, Object obj) {
        switch (this.a) {
            case 0:
                Date date = (Date) obj;
                synchronized (this) {
                    f60Var.I(date == null ? null : ((SimpleDateFormat) this.b).format((java.util.Date) date));
                }
                return;
            case 1:
                Time time = (Time) obj;
                synchronized (this) {
                    f60Var.I(time == null ? null : ((SimpleDateFormat) this.b).format((java.util.Date) time));
                }
                return;
            default:
                java.util.Date date2 = (java.util.Date) obj;
                synchronized (this) {
                    if (date2 == null) {
                        f60Var.C();
                    } else {
                        f60Var.I(((DateFormat) ((ArrayList) this.b).get(0)).format(date2));
                    }
                }
                return;
        }
    }
}
