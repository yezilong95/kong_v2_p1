package defpackage;

import android.database.Cursor;
import java.io.IOException;
import java.util.Set;
import kotlin.collections.builders.SetBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class f implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ f(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    public Set a() throws IOException {
        y40 y40Var = (y40) this.F;
        SetBuilder setBuilder = new SetBuilder();
        Cursor cursorQuery$default = kp0.query$default(y40Var.a, new i6("SELECT * FROM room_table_modification_log WHERE invalidated = 1;"), null, 2, null);
        while (cursorQuery$default.moveToNext()) {
            try {
                setBuilder.add(Integer.valueOf(cursorQuery$default.getInt(0)));
            } finally {
            }
        }
        cursorQuery$default.close();
        Set setBuild = setBuilder.build();
        if (setBuild.isEmpty()) {
            return setBuild;
        }
        if (((y40) this.F).h == null) {
            throw new IllegalStateException("Required value was null.");
        }
        ky0 ky0Var = ((y40) this.F).h;
        if (ky0Var == null) {
            throw new IllegalArgumentException("Required value was null.");
        }
        ky0Var.f();
        return setBuild;
    }

    /* JADX INFO: Infinite loop detected, blocks: 9, insns: 0 */
    /* JADX WARN: Can't wrap try/catch for region: R(5:351|(2:352|(4:354|(2:451|356)(1:452)|(1:380)|(1:382))(2:450|361))|436|362|(2:(1:365)|(0))(3:368|(1:370)|(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x0718, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Code restructure failed: missing block: B:374:0x0719, code lost:
    
        if (r7 != null) goto L375;
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x071b, code lost:
    
        r7.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:376:0x071e, code lost:
    
        if (r2 != null) goto L377;
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x0720, code lost:
    
        r2.recycle();
     */
    /* JADX WARN: Code restructure failed: missing block: B:378:0x0723, code lost:
    
        throw r0;
     */
    /* JADX WARN: Removed duplicated region for block: B:380:0x0726  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x072b A[PHI: r2
      0x072b: PHI (r2v13 android.view.accessibility.AccessibilityNodeInfo) = 
      (r2v12 android.view.accessibility.AccessibilityNodeInfo)
      (r2v18 android.view.accessibility.AccessibilityNodeInfo)
      (r2v18 android.view.accessibility.AccessibilityNodeInfo)
     binds: [B:381:0x0729, B:371:0x0715, B:366:0x0708] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void run() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 2060
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f.run():void");
    }
}
