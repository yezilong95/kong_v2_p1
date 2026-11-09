package defpackage;

import android.view.View;
import com.google.android.material.sidesheet.SideSheetBehavior;
import org.webrtc.SurfaceTextureHelper;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class zo0 implements Runnable {
    public final /* synthetic */ int E;
    public final /* synthetic */ int F;
    public final /* synthetic */ Object G;

    public /* synthetic */ zo0(Object obj, int i, int i2) {
        this.E = i2;
        this.G = obj;
        this.F = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.E) {
            case 0:
                ((o80) this.G).W(this.F);
                break;
            case 1:
                SideSheetBehavior sideSheetBehavior = (SideSheetBehavior) this.G;
                View view = (View) sideSheetBehavior.T.get();
                if (view != null) {
                    sideSheetBehavior.z(view, this.F, false);
                }
                break;
            default:
                ((SurfaceTextureHelper) this.G).lambda$setFrameRotation$4(this.F);
                break;
        }
    }
}
