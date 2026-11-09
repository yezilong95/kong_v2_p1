package defpackage;

import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import androidx.fragment.app.c;
import androidx.work.impl.WorkDatabaseVersions;
import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListMap;
import org.slf4j.helpers.NOPLogger;

/* JADX INFO: loaded from: classes.dex */
public class di implements qi0, aj, ya0, gy0, i30, xl0 {
    public static Set F;
    public static di G;
    public final /* synthetic */ int E;

    public /* synthetic */ di(int i) {
        this.E = i;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0045, code lost:
    
        if (java.lang.Character.isHighSurrogate(r5) != false) goto L33;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x0082, code lost:
    
        if (java.lang.Character.isLowSurrogate(r5) != false) goto L58;
     */
    /* JADX WARN: Removed duplicated region for block: B:46:0x006c A[EDGE_INSN: B:92:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085], EDGE_INSN: B:93:0x006c->B:46:0x006c BREAK  A[LOOP:2: B:47:0x006e->B:58:0x0085, LOOP_LABEL: LOOP:2: B:47:0x006e->B:58:0x0085]] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00a2 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean g(defpackage.dt r7, android.text.Editable r8, int r9, int r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 240
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.di.g(dt, android.text.Editable, int, int, boolean):boolean");
    }

    @Override // defpackage.gy0
    public hy0 a(fy0 fy0Var) {
        return new w00(fy0Var.a, fy0Var.b, fy0Var.c, fy0Var.d, fy0Var.e);
    }

    @Override // defpackage.i30
    public ua0 b(String str) {
        return NOPLogger.NOP_LOGGER;
    }

    @Override // defpackage.xl0
    public void c(int i, Serializable serializable) {
        String str;
        switch (this.E) {
            case 26:
                break;
            default:
                switch (i) {
                    case 1:
                        str = "RESULT_INSTALL_SUCCESS";
                        break;
                    case 2:
                        str = "RESULT_ALREADY_INSTALLED";
                        break;
                    case 3:
                        str = "RESULT_UNSUPPORTED_ART_VERSION";
                        break;
                    case 4:
                        str = "RESULT_NOT_WRITABLE";
                        break;
                    case 5:
                        str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                        break;
                    case 6:
                        str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                        break;
                    case 7:
                        str = "RESULT_IO_EXCEPTION";
                        break;
                    case 8:
                        str = "RESULT_PARSE_EXCEPTION";
                        break;
                    case WorkDatabaseVersions.VERSION_9 /* 9 */:
                    default:
                        str = "";
                        break;
                    case 10:
                        str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                        break;
                    case WorkDatabaseVersions.VERSION_11 /* 11 */:
                        str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                        break;
                }
                if (i == 6 || i == 7 || i == 8) {
                    Log.e("ProfileInstaller", str, (Throwable) serializable);
                } else {
                    Log.d("ProfileInstaller", str);
                }
                break;
        }
    }

    public float d(float f, float f2) {
        return 1.0f;
    }

    @Override // defpackage.xl0
    public void e() {
        switch (this.E) {
            case 26:
                break;
            default:
                Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
                break;
        }
    }

    public Signature[] f(PackageManager packageManager, String str) {
        return packageManager.getPackageInfo(str, 64).signatures;
    }

    @Override // defpackage.qi0
    public Object q() {
        return new ConcurrentSkipListMap();
    }

    public di(c cVar) {
        this.E = 15;
    }

    private final void h() {
    }

    private final void i(int i, Serializable serializable) {
    }
}
