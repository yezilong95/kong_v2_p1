package defpackage;

import android.content.Context;
import java.io.File;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes.dex */
public final class v00 extends Lambda implements a10 {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ v00(int i, Object obj) {
        super(0);
        this.E = i;
        this.F = obj;
    }

    @Override // defpackage.a10
    public final Object invoke() {
        u00 u00Var;
        switch (this.E) {
            case 0:
                w00 w00Var = (w00) this.F;
                String str = w00Var.F;
                Context context = w00Var.E;
                if (str == null || !w00Var.H) {
                    u00Var = new u00(context, w00Var.F, new p50(23), w00Var.G, w00Var.I);
                } else {
                    File noBackupFilesDir = context.getNoBackupFilesDir();
                    t40.m(noBackupFilesDir, "context.noBackupFilesDir");
                    u00Var = new u00(context, new File(noBackupFilesDir, w00Var.F).getAbsolutePath(), new p50(23), w00Var.G, w00Var.I);
                }
                u00Var.setWriteAheadLoggingEnabled(w00Var.K);
                return u00Var;
            default:
                pv0 pv0Var = (pv0) this.F;
                return pv0Var.database.compileStatement(pv0Var.createQuery());
        }
    }
}
