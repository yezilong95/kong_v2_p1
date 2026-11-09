package defpackage;

import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class em implements View.OnTouchListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ em(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        Object obj = this.c;
        switch (this.b) {
            case 0:
                gm gmVar = (gm) obj;
                gmVar.getClass();
                if (motionEvent.getAction() == 1) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - gmVar.o;
                    if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
                        gmVar.m = false;
                    }
                    gmVar.u();
                    gmVar.m = true;
                    gmVar.o = System.currentTimeMillis();
                }
                break;
            default:
                int i = SearchView.E;
                SearchView searchView = (SearchView) obj;
                if (searchView.g()) {
                    searchView.f();
                }
                break;
        }
        return false;
    }
}
