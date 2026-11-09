package androidx.transition;

import android.content.Context;
import android.util.AttributeSet;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AutoTransition extends TransitionSet {
    public AutoTransition(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        J();
    }

    public final void J() {
        I(1);
        F(new Fade(2));
        ChangeBounds changeBounds = new ChangeBounds();
        changeBounds.y = false;
        F(changeBounds);
        F(new Fade(1));
    }

    public AutoTransition() {
        this.y = new ArrayList();
        this.z = true;
        this.B = false;
        this.C = 0;
        J();
    }
}
