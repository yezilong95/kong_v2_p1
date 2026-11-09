package defpackage;

import android.view.ViewGroup;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

/* JADX INFO: loaded from: classes.dex */
public final class bp implements fp {
    public final /* synthetic */ int b;
    public final /* synthetic */ ExtendedFloatingActionButton c;

    public /* synthetic */ bp(ExtendedFloatingActionButton extendedFloatingActionButton, int i) {
        this.b = i;
        this.c = extendedFloatingActionButton;
    }

    @Override // defpackage.fp
    public final int c() {
        switch (this.b) {
            case 0:
                return this.c.getCollapsedPadding();
            default:
                return this.c.C;
        }
    }

    @Override // defpackage.fp
    public final int f() {
        switch (this.b) {
            case 0:
                return this.c.getCollapsedPadding();
            default:
                return this.c.B;
        }
    }

    @Override // defpackage.fp
    public final ViewGroup.LayoutParams g() {
        switch (this.b) {
            case 0:
                ExtendedFloatingActionButton extendedFloatingActionButton = this.c;
                return new ViewGroup.LayoutParams(extendedFloatingActionButton.getCollapsedSize(), extendedFloatingActionButton.getCollapsedSize());
            default:
                return new ViewGroup.LayoutParams(-2, -2);
        }
    }

    @Override // defpackage.fp
    public final int getHeight() {
        switch (this.b) {
            case 0:
                return this.c.getCollapsedSize();
            default:
                return this.c.getMeasuredHeight();
        }
    }

    @Override // defpackage.fp
    public final int getWidth() {
        switch (this.b) {
            case 0:
                return this.c.getCollapsedSize();
            default:
                ExtendedFloatingActionButton extendedFloatingActionButton = this.c;
                return (extendedFloatingActionButton.getMeasuredWidth() - (extendedFloatingActionButton.getCollapsedPadding() * 2)) + extendedFloatingActionButton.B + extendedFloatingActionButton.C;
        }
    }
}
