package defpackage;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class ke implements ViewGroup.OnHierarchyChangeListener {
    public ViewGroup.OnHierarchyChangeListener a;
    public final /* synthetic */ ChipGroup b;

    public ke(ChipGroup chipGroup) {
        this.b = chipGroup;
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewAdded(View view, View view2) {
        ChipGroup chipGroup = this.b;
        if (view == chipGroup && (view2 instanceof Chip)) {
            if (view2.getId() == -1) {
                WeakHashMap weakHashMap = f51.a;
                view2.setId(View.generateViewId());
            }
            Chip chip = (Chip) view2;
            wd wdVar = chipGroup.L;
            wdVar.a.put(Integer.valueOf(chip.getId()), chip);
            if (chip.isChecked()) {
                wdVar.a(chip);
            }
            chip.setInternalOnCheckedChangeListener(new z8(9, wdVar));
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.a;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewAdded(view, view2);
        }
    }

    @Override // android.view.ViewGroup.OnHierarchyChangeListener
    public final void onChildViewRemoved(View view, View view2) {
        ChipGroup chipGroup = this.b;
        if (view == chipGroup && (view2 instanceof Chip)) {
            Chip chip = (Chip) view2;
            wd wdVar = chipGroup.L;
            wdVar.getClass();
            chip.setInternalOnCheckedChangeListener(null);
            wdVar.a.remove(Integer.valueOf(chip.getId()));
            wdVar.b.remove(Integer.valueOf(chip.getId()));
        }
        ViewGroup.OnHierarchyChangeListener onHierarchyChangeListener = this.a;
        if (onHierarchyChangeListener != null) {
            onHierarchyChangeListener.onChildViewRemoved(view, view2);
        }
    }
}
