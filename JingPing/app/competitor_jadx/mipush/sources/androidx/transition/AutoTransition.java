package androidx.transition;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AutoTransition extends TransitionSet {
    public AutoTransition(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        M();
    }

    public final void M() {
        L(1);
        I(new Fade(2));
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.f0 = false;
        I(changeBounds);
        I(new Fade(1));
    }

    public AutoTransition() {
        this.f0 = new ArrayList();
        this.g0 = true;
        this.i0 = false;
        this.j0 = 0;
        M();
    }
}
