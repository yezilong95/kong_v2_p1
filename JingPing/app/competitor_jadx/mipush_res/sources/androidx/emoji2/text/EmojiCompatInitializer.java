package androidx.emoji2.text;

import android.content.Context;
import androidx.lifecycle.ProcessLifecycleInitializer;
import defpackage.at;
import defpackage.ez;
import defpackage.f40;
import defpackage.ou;
import defpackage.s80;
import defpackage.x80;
import defpackage.z8;
import defpackage.zs;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EmojiCompatInitializer implements f40 {
    @Override // defpackage.f40
    public final Object create(Context context) {
        Object objR;
        ez ezVar = new ez(new z8(context));
        ezVar.a = 1;
        if (zs.j == null) {
            synchronized (zs.i) {
                try {
                    if (zs.j == null) {
                        zs.j = new zs(ezVar);
                    }
                } finally {
                }
            }
        }
        ou ouVarE = ou.E(context);
        ouVarE.getClass();
        synchronized (ou.K) {
            try {
                objR = ((HashMap) ouVarE.F).get(ProcessLifecycleInitializer.class);
                if (objR == null) {
                    objR = ouVarE.r(ProcessLifecycleInitializer.class, new HashSet());
                }
            } finally {
            }
        }
        s80 lifecycle = ((x80) objR).getLifecycle();
        lifecycle.a(new at(this, lifecycle));
        return Boolean.TRUE;
    }

    @Override // defpackage.f40
    public final List dependencies() {
        return Collections.singletonList(ProcessLifecycleInitializer.class);
    }
}
