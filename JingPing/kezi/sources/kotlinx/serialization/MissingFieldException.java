package kotlinx.serialization;

import defpackage.hk;
import defpackage.oy;
import defpackage.xi;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MissingFieldException extends SerializationException {
    public final List b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(List<String> list, String str, Throwable th) {
        super(str, th);
        oy.j(list, "missingFields");
        this.b = list;
    }

    public final List<String> getMissingFields() {
        return this.b;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MissingFieldException(List<String> list, String str) {
        String str2;
        oy.j(list, "missingFields");
        oy.j(str, "serialName");
        if (list.size() == 1) {
            str2 = "Field '" + list.get(0) + "' is required for type with serial name '" + str + "', but it was missing";
        } else {
            str2 = "Fields " + list + " are required for type with serial name '" + str + "', but they were missing";
        }
        this(list, str2, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(String str, String str2) {
        this(xi.G(str), "Field '" + str + "' is required for type with serial name '" + str2 + "', but it was missing", null);
        oy.j(str, "missingField");
        oy.j(str2, "serialName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(String str) {
        this(xi.G(str), hk.j("Field '", str, "' is required, but it was missing"), null);
        oy.j(str, "missingField");
    }
}
