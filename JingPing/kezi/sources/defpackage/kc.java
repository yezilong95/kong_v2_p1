package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import com.google.android.material.R$string;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public final class kc extends ap {
    public final /* synthetic */ Chip q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public kc(Chip chip, Chip chip2) {
        super(chip2);
        this.q = chip;
    }

    @Override // defpackage.ap
    public final int n(float f, float f2) {
        int i = Chip.y;
        Chip chip = this.q;
        return (chip.d() && chip.getCloseIconTouchBounds().contains(f, f2)) ? 1 : 0;
    }

    @Override // defpackage.ap
    public final void o(ArrayList arrayList) {
        mc mcVar;
        arrayList.add(0);
        int i = Chip.y;
        Chip chip = this.q;
        if (!chip.d() || (mcVar = chip.f) == null || !mcVar.L || chip.i == null) {
            return;
        }
        arrayList.add(1);
    }

    @Override // defpackage.ap
    public final boolean s(int i, int i2, Bundle bundle) {
        boolean z = false;
        if (i2 == 16) {
            Chip chip = this.q;
            if (i == 0) {
                return chip.performClick();
            }
            if (i == 1) {
                chip.playSoundEffect(0);
                View.OnClickListener onClickListener = chip.i;
                if (onClickListener != null) {
                    onClickListener.onClick(chip);
                    z = true;
                }
                if (chip.u) {
                    chip.t.x(1, 1);
                }
            }
        }
        return z;
    }

    @Override // defpackage.ap
    public final void t(o0 o0Var) {
        Chip chip = this.q;
        mc mcVar = chip.f;
        boolean z = mcVar != null && mcVar.R;
        AccessibilityNodeInfo accessibilityNodeInfo = o0Var.a;
        accessibilityNodeInfo.setCheckable(z);
        accessibilityNodeInfo.setClickable(chip.isClickable());
        o0Var.i(chip.getAccessibilityClassName());
        o0Var.m(chip.getText());
    }

    @Override // defpackage.ap
    public final void u(int i, o0 o0Var) {
        AccessibilityNodeInfo accessibilityNodeInfo = o0Var.a;
        CharSequence charSequence = ExtensionRequestData.EMPTY_VALUE;
        if (i != 1) {
            accessibilityNodeInfo.setContentDescription(ExtensionRequestData.EMPTY_VALUE);
            accessibilityNodeInfo.setBoundsInParent(Chip.z);
            return;
        }
        Chip chip = this.q;
        CharSequence closeIconContentDescription = chip.getCloseIconContentDescription();
        if (closeIconContentDescription != null) {
            accessibilityNodeInfo.setContentDescription(closeIconContentDescription);
        } else {
            CharSequence text = chip.getText();
            Context context = chip.getContext();
            int i2 = R$string.mtrl_chip_close_icon_content_description;
            if (!TextUtils.isEmpty(text)) {
                charSequence = text;
            }
            accessibilityNodeInfo.setContentDescription(context.getString(i2, charSequence).trim());
        }
        accessibilityNodeInfo.setBoundsInParent(chip.getCloseIconTouchBoundsInt());
        o0Var.b(j0.g);
        accessibilityNodeInfo.setEnabled(chip.isEnabled());
    }

    @Override // defpackage.ap
    public final void v(int i, boolean z) {
        if (i == 1) {
            Chip chip = this.q;
            chip.o = z;
            chip.refreshDrawableState();
        }
    }
}
