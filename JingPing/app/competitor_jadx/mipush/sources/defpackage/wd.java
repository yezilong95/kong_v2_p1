package defpackage;

import android.view.View;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class wd {
    public final HashMap a = new HashMap();
    public final HashSet b = new HashSet();
    public vd c;
    public boolean d;
    public boolean e;

    public final boolean a(ed0 ed0Var) {
        int id = ed0Var.getId();
        HashSet hashSet = this.b;
        if (hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        ed0 ed0Var2 = (ed0) this.a.get(Integer.valueOf(c()));
        if (ed0Var2 != null) {
            e(ed0Var2, false);
        }
        boolean zAdd = hashSet.add(Integer.valueOf(id));
        if (!ed0Var.isChecked()) {
            ed0Var.setChecked(true);
        }
        return zAdd;
    }

    public final ArrayList b(ChipGroup chipGroup) {
        HashSet hashSet = new HashSet(this.b);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            View childAt = chipGroup.getChildAt(i);
            if ((childAt instanceof ed0) && hashSet.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    public final int c() {
        if (!this.d) {
            return -1;
        }
        HashSet hashSet = this.b;
        if (hashSet.isEmpty()) {
            return -1;
        }
        return ((Integer) hashSet.iterator().next()).intValue();
    }

    public final void d() {
        vd vdVar = this.c;
        if (vdVar != null) {
            new HashSet(this.b);
            ChipGroup chipGroup = (ChipGroup) ((p50) vdVar).F;
            je jeVar = chipGroup.K;
            if (jeVar != null) {
                chipGroup.L.b(chipGroup);
                ChipGroup chipGroup2 = (ChipGroup) ((z8) jeVar).F;
                if (chipGroup2.L.d) {
                    chipGroup2.getCheckedChipId();
                    throw null;
                }
            }
        }
    }

    public final boolean e(ed0 ed0Var, boolean z) {
        int id = ed0Var.getId();
        HashSet hashSet = this.b;
        if (!hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        if (z && hashSet.size() == 1 && hashSet.contains(Integer.valueOf(id))) {
            ed0Var.setChecked(true);
            return false;
        }
        boolean zRemove = hashSet.remove(Integer.valueOf(id));
        if (ed0Var.isChecked()) {
            ed0Var.setChecked(false);
        }
        return zRemove;
    }

    public void setOnCheckedStateChangeListener(vd vdVar) {
        this.c = vdVar;
    }
}
