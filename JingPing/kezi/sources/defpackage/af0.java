package defpackage;

import android.view.View;
import com.google.android.material.sidesheet.SideSheetBehavior;
import org.webrtc.SurfaceTextureHelper;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class af0 implements Runnable {
    public final /* synthetic */ int b;
    public final /* synthetic */ int c;
    public final /* synthetic */ Object d;

    public /* synthetic */ af0(Object obj, int i, int i2) {
        this.b = i2;
        this.d = obj;
        this.c = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.b) {
            case 0:
                ((rp0) this.d).L(this.c);
                break;
            case 1:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.d;
                View view = (View) sideSheetBehavior.q.get();
                if (view != null) {
                    sideSheetBehavior.z(view, this.c, false);
                }
                break;
            default:
                ((SurfaceTextureHelper) this.d).lambda$setFrameRotation$4(this.c);
                break;
        }
    }
}
