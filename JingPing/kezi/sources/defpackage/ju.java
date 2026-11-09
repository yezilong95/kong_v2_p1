package defpackage;

import android.content.Context;
import java.io.File;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import javax.net.ssl.SSLPeerUnverifiedException;
import kotlin.collections.EmptyList;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class ju extends Lambda implements ou {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ju(int i, Object obj) {
        super(0);
        this.b = i;
        this.c = obj;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [kotlin.jvm.internal.Lambda, ou] */
    @Override // defpackage.ou
    public final Object invoke() {
        iu iuVar;
        switch (this.b) {
            case 0:
                ku kuVar = (ku) this.c;
                String str = kuVar.c;
                Context context = kuVar.b;
                if (str == null || !kuVar.e) {
                    iuVar = new iu(context, kuVar.c, new o70(22), kuVar.d, kuVar.f);
                } else {
                    File noBackupFilesDir = context.getNoBackupFilesDir();
                    oy.i(noBackupFilesDir, "context.noBackupFilesDir");
                    iuVar = new iu(context, new File(noBackupFilesDir, kuVar.c).getAbsolutePath(), new o70(22), kuVar.d, kuVar.f);
                }
                iuVar.setWriteAheadLoggingEnabled(kuVar.h);
                return iuVar;
            case 1:
                return (List) this.c;
            case 2:
                try {
                    return (List) ((Lambda) this.c).invoke();
                } catch (SSLPeerUnverifiedException unused) {
                    return EmptyList.INSTANCE;
                }
            case 3:
                pv pvVar = ((yc0) this.c).e;
                oy.g(pvVar);
                List<Certificate> listA = pvVar.a();
                ArrayList arrayList = new ArrayList(ce.Z(listA));
                for (Certificate certificate : listA) {
                    oy.h(certificate, "null cannot be cast to non-null type java.security.cert.X509Certificate");
                    arrayList.add((X509Certificate) certificate);
                }
                return arrayList;
            default:
                aj0 aj0Var = (aj0) this.c;
                return aj0Var.database.compileStatement(aj0Var.createQuery());
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ju(ou ouVar) {
        super(0);
        this.b = 2;
        this.c = (Lambda) ouVar;
    }
}
