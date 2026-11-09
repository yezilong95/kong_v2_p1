package defpackage;

import android.view.View;
import com.google.android.material.chip.ChipGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public final class bc {
    public final HashMap a = new HashMap();
    public final HashSet b = new HashSet();
    public ac c;
    public boolean d;
    public boolean e;

    public final boolean a(o50 o50Var) {
        int id = o50Var.getId();
        HashSet hashSet = this.b;
        if (hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        o50 o50Var2 = (o50) this.a.get(Integer.valueOf(c()));
        if (o50Var2 != null) {
            e(o50Var2, false);
        }
        boolean zAdd = hashSet.add(Integer.valueOf(id));
        if (!o50Var.isChecked()) {
            o50Var.setChecked(true);
        }
        return zAdd;
    }

    public final ArrayList b(ChipGroup chipGroup) {
        HashSet hashSet = new HashSet(this.b);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            View childAt = chipGroup.getChildAt(i);
            if ((childAt instanceof o50) && hashSet.contains(Integer.valueOf(childAt.getId()))) {
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
        ac acVar = this.c;
        if (acVar != null) {
            new HashSet(this.b);
            ChipGroup chipGroup = ((nc) acVar).a;
            pc pcVar = chipGroup.h;
            if (pcVar != null) {
                chipGroup.i.b(chipGroup);
                ChipGroup chipGroup2 = ((nc) pcVar).a;
                if (chipGroup2.i.d) {
                    chipGroup2.getCheckedChipId();
                    throw null;
                }
            }
        }
    }

    public final boolean e(o50 o50Var, boolean z) {
        int id = o50Var.getId();
        HashSet hashSet = this.b;
        if (!hashSet.contains(Integer.valueOf(id))) {
            return false;
        }
        if (z && hashSet.size() == 1 && hashSet.contains(Integer.valueOf(id))) {
            o50Var.setChecked(true);
            return false;
        }
        boolean zRemove = hashSet.remove(Integer.valueOf(id));
        if (o50Var.isChecked()) {
            o50Var.setChecked(false);
        }
        return zRemove;
    }

    public void setOnCheckedStateChangeListener(ac acVar) {
        this.c = acVar;
    }
}
