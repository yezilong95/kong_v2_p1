package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.R$string;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class fe extends wu {
    public final /* synthetic */ Chip q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public fe(Chip chip, Chip chip2) {
        super(chip2);
        this.q = chip;
    }

    @Override // defpackage.wu
    public final int n(float f, float f2) {
        int i = Chip.e0;
        Chip chip = this.q;
        return (chip.d() && chip.getCloseIconTouchBounds().contains(f, f2)) ? 1 : 0;
    }

    @Override // defpackage.wu
    public final void o(ArrayList arrayList) {
        he heVar;
        arrayList.add(0);
        int i = Chip.e0;
        Chip chip = this.q;
        if (!chip.d() || (heVar = chip.I) == null || !heVar.p0 || chip.L == null) {
            return;
        }
        arrayList.add(1);
    }

    @Override // defpackage.wu
    public final boolean s(int i, int i2, Bundle bundle) {
        boolean z = false;
        if (i2 == 16) {
            Chip chip = this.q;
            if (i == 0) {
                return chip.performClick();
            }
            if (i == 1) {
                chip.playSoundEffect(0);
                View.OnClickListener onClickListener = chip.L;
                if (onClickListener != null) {
                    onClickListener.onClick(chip);
                    z = true;
                }
                if (chip.a0) {
                    chip.W.x(1, 1);
                }
            }
        }
        return z;
    }

    @Override // defpackage.wu
    public final void t(j2 j2Var) {
        Chip chip = this.q;
        he heVar = chip.I;
        boolean z = heVar != null && heVar.v0;
        AccessibilityNodeInfo accessibilityNodeInfo = j2Var.a;
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setClickable(chip.isClickable());
        j2Var.i(chip.getAccessibilityClassName());
        j2Var.m(chip.getText());
    }

    @Override // defpackage.wu
    public final void u(int i, j2 j2Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = j2Var.a;
        if (i != 1) {
            accessibilityNodeInfo.setContentDescription("");
            accessibilityNodeInfo.setBoundsInParent(Chip.f0);
            return;
        }
        Chip chip = this.q;
        CharSequence closeIconContentDescription = chip.getCloseIconContentDescription();
        if (closeIconContentDescription != null) {
            accessibilityNodeInfo.setContentDescription(closeIconContentDescription);
        } else {
            CharSequence text = chip.getText();
            accessibilityNodeInfo.setContentDescription(chip.getContext().getString(R$string.mtrl_chip_close_icon_content_description, TextUtils.isEmpty(text) ? "" : text).trim());
        }
        accessibilityNodeInfo.setBoundsInParent(chip.getCloseIconTouchBoundsInt());
        j2Var.b(e2.g);
        accessibilityNodeInfo.setEnabled(chip.isEnabled());
    }

    @Override // defpackage.wu
    public final void v(int i, boolean z) {
        if (i == 1) {
            Chip chip = this.q;
            chip.R = z;
            chip.refreshDrawableState();
        }
    }
}
