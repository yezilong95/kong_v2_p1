package defpackage;

import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class bb extends Lambda implements ou {
    public final /* synthetic */ int b;
    public final /* synthetic */ cb c;
    public final /* synthetic */ Object d;
    public final /* synthetic */ Object e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ bb(cb cbVar, Object obj, Object obj2, int i) {
        super(0);
        this.b = i;
        this.c = cbVar;
        this.d = obj;
        this.e = obj2;
    }

    @Override // defpackage.ou
    public final Object invoke() {
        switch (this.b) {
            case 0:
                oy oyVar = this.c.b;
                List<Certificate> listL = (List) this.d;
                if (oyVar != null) {
                    listL = oyVar.l(listL, (String) this.e);
                }
                ArrayList arrayList = new ArrayList(ce.Z(listL));
                for (Certificate certificate : listL) {
                    oy.h(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    arrayList.add((X509Certificate) certificate);
                }
                return arrayList;
            default:
                oy oyVar2 = this.c.b;
                oy.g(oyVar2);
                return oyVar2.l(((pv) this.d).a(), ((p2) this.e).h.d);
        }
    }
}
