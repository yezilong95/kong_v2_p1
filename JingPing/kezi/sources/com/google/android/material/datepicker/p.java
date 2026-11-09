package com.google.android.material.datepicker;

import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.q50;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class p implements AdapterView.OnItemClickListener {
    public final /* synthetic */ MaterialCalendarGridView b;
    public final /* synthetic */ r c;

    public p(r rVar, MaterialCalendarGridView materialCalendarGridView) {
        this.c = rVar;
        this.b = materialCalendarGridView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        MaterialCalendarGridView materialCalendarGridView = this.b;
        o oVarA = materialCalendarGridView.a();
        if (i < oVarA.a() || i > oVarA.c()) {
            return;
        }
        i iVar = this.c.f;
        long jLongValue = materialCalendarGridView.a().getItem(i).longValue();
        l lVar = iVar.a;
        if (lVar.V.d.f(jLongValue)) {
            lVar.U.h(jLongValue);
            Iterator it = lVar.S.iterator();
            while (it.hasNext()) {
                ((q50) it.next()).b(lVar.U.a());
            }
            lVar.b0.getAdapter().a.b();
            RecyclerView recyclerView = lVar.a0;
            if (recyclerView != null) {
                recyclerView.getAdapter().a.b();
            }
        }
    }
}
