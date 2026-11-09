package com.google.android.material.datepicker;

import com.google.android.material.datepicker.CalendarConstraints;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class c implements f {
    @Override // com.google.android.material.datepicker.f
    public final boolean a(ArrayList arrayList, long j) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            CalendarConstraints.DateValidator dateValidator = (CalendarConstraints.DateValidator) it.next();
            if (dateValidator != null && dateValidator.f(j)) {
                return true;
            }
        }
        return false;
    }

    @Override // com.google.android.material.datepicker.f
    public final int getId() {
        return 1;
    }
}
