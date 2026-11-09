package defpackage;

import android.view.MotionEvent;
import android.view.View;
import com.google.android.material.search.SearchView;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class hq implements View.OnTouchListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ hq(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        Object obj = this.F;
        switch (this.E) {
            case 0:
                jq jqVar = (jq) obj;
                jqVar.getClass();
                if (motionEvent.getAction() == 1) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - jqVar.o;
                    if (jCurrentTimeMillis < 0 || jCurrentTimeMillis > 300) {
                        jqVar.m = false;
                    }
                    jqVar.u();
                    jqVar.m = true;
                    jqVar.o = System.currentTimeMillis();
                }
                break;
            default:
                int i = SearchView.k0;
                SearchView searchView = (SearchView) obj;
                if (searchView.g()) {
                    searchView.f();
                }
                break;
        }
        return false;
    }
}
