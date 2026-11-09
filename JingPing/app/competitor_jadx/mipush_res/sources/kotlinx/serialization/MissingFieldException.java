package kotlinx.serialization;

import defpackage.l21;
import defpackage.t40;
import defpackage.zn;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class MissingFieldException extends SerializationException {
    public final List E;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(List<String> list, String str, Throwable th) {
        super(str, th);
        t40.n(list, "missingFields");
        this.E = list;
    }

    public final List<String> getMissingFields() {
        return this.E;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public MissingFieldException(List<String> list, String str) {
        String str2;
        t40.n(list, "missingFields");
        t40.n(str, "serialName");
        if (list.size() == 1) {
            str2 = "Field '" + list.get(0) + "' is required for type with serial name '" + str + "', but it was missing";
        } else {
            str2 = "Fields " + list + " are required for type with serial name '" + str + "', but they were missing";
        }
        this(list, str2, null);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(String str, String str2) {
        this(l21.g0(str), "Field '" + str + "' is required for type with serial name '" + str2 + "', but it was missing", null);
        t40.n(str, "missingField");
        t40.n(str2, "serialName");
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public MissingFieldException(String str) {
        this(l21.g0(str), zn.n("Field '", str, "' is required, but it was missing"), null);
        t40.n(str, "missingField");
    }
}
