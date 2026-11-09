package defpackage;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import java.util.Comparator;

/* JADX INFO: loaded from: classes.dex */
public final class uc0 implements Comparator {
    public final /* synthetic */ MaterialButtonToggleGroup a;

    public uc0(MaterialButtonToggleGroup materialButtonToggleGroup) {
        this.a = materialButtonToggleGroup;
    }

    @Override // java.util.Comparator
    public final int compare(Object obj, Object obj2) {
        MaterialButton materialButton = (MaterialButton) obj;
        MaterialButton materialButton2 = (MaterialButton) obj2;
        int iCompareTo = Boolean.valueOf(materialButton.S).compareTo(Boolean.valueOf(materialButton2.S));
        if (iCompareTo != 0) {
            return iCompareTo;
        }
        int iCompareTo2 = Boolean.valueOf(materialButton.isPressed()).compareTo(Boolean.valueOf(materialButton2.isPressed()));
        if (iCompareTo2 != 0) {
            return iCompareTo2;
        }
        MaterialButtonToggleGroup materialButtonToggleGroup = this.a;
        return Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton)).compareTo(Integer.valueOf(materialButtonToggleGroup.indexOfChild(materialButton2)));
    }
}
