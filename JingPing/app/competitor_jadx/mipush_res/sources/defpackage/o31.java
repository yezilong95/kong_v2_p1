package defpackage;

import io.nats.client.Connection;
import io.nats.client.impl.ErrorListenerLoggerImpl;
import io.nats.client.support.Validator;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class o31 implements Supplier {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;
    public final /* synthetic */ Object c;

    public /* synthetic */ o31(Object obj, int i, Object obj2) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // java.util.function.Supplier
    public final Object get() {
        switch (this.a) {
            case 0:
                return Validator.lambda$validateNonWildcardKvKey$8((String) this.b, (String) this.c);
            case 1:
                return Validator.lambda$validatePrefixOrDomain$0((String) this.b, (String) this.c);
            case 2:
                return Validator.lambda$validatePrintable$2((String) this.b, (String) this.c);
            case 3:
                return Validator.lambda$validatePrintableExceptWildDotGt$3((String) this.b, (String) this.c);
            case 4:
                return Validator.lambda$validatePrintableExceptWildGt$5((String) this.b, (String) this.c);
            case 5:
                return Validator.lambda$validatePrintableExceptWildDotGtSlashes$4((String) this.b, (String) this.c);
            case 6:
                return Validator.lambda$validateSemVer$9((String) this.b, (String) this.c);
            case 7:
                return Validator.lambda$validateIsRestrictedTerm$6((String) this.b, (String) this.c);
            case 8:
                return Validator.lambda$validateWildcardKvKey$7((String) this.b, (String) this.c);
            default:
                return ((ErrorListenerLoggerImpl) this.b).lambda$socketWriteTimeout$9((Connection) this.c);
        }
    }
}
