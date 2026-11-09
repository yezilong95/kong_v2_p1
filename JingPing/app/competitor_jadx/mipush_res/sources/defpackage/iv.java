package defpackage;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import com.mamimobi.activity.FakePageActivity;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class iv implements View.OnKeyListener {
    @Override // android.view.View.OnKeyListener
    public final boolean onKey(View view, int i, KeyEvent keyEvent) {
        Context context = FakePageActivity.F;
        if (keyEvent.getAction() == 0) {
            return i == 4 || i == 82;
        }
        return false;
    }
}
