package androidx.emoji2.text;

import android.content.Context;
import androidx.lifecycle.ProcessLifecycleInitializer;
import defpackage.cy;
import defpackage.g1;
import defpackage.hn;
import defpackage.in;
import defpackage.p10;
import defpackage.po;
import defpackage.qs;
import defpackage.u10;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements cy {
    @Override // defpackage.cy
    public final Object create(Context context) {
        Object objQ;
        g1 g1Var = new g1();
        g1Var.a = context.getApplicationContext();
        qs qsVar = new qs(g1Var);
        qsVar.a = 1;
        if (hn.j == null) {
            synchronized (hn.i) {
                try {
                    if (hn.j == null) {
                        hn.j = new hn(qsVar);
                    }
                } finally {
                }
            }
        }
        po poVarC = po.C(context);
        poVarC.getClass();
        synchronized (po.h) {
            try {
                objQ = ((HashMap) poVarC.c).get(ProcessLifecycleInitializer.class);
                if (objQ == null) {
                    objQ = poVarC.q(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        p10 lifecycle = ((u10) objQ).getLifecycle();
        lifecycle.a(new in(this, lifecycle));
        return Boolean.TRUE;
    }

    @Override // defpackage.cy
    public final List dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }
}
