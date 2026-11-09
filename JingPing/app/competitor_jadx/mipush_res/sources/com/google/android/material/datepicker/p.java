package com.google.android.material.datepicker;

import android.view.View;
import android.widget.AdapterView;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.hd0;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class p implements AdapterView.OnItemClickListener {
    public final /* synthetic */ MaterialCalendarGridView E;
    public final /* synthetic */ r F;

    public p(r rVar, MaterialCalendarGridView materialCalendarGridView) {
        this.F = rVar;
        this.E = materialCalendarGridView;
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
        MaterialCalendarGridView materialCalendarGridView = this.E;
        o oVarA = materialCalendarGridView.a();
        if (i < oVarA.a() || i > oVarA.c()) {
            return;
        }
        i iVar = this.F.f;
        long jLongValue = materialCalendarGridView.a().getItem(i).longValue();
        l lVar = iVar.a;
        if (lVar.y0.G.e(jLongValue)) {
            lVar.x0.i(jLongValue);
            Iterator it = lVar.v0.iterator();
            while (it.hasNext()) {
                ((hd0) it.next()).b(lVar.x0.h());
            }
            lVar.E0.getAdapter().a.b();
            RecyclerView recyclerView = lVar.D0;
            if (recyclerView != null) {
                recyclerView.getAdapter().a.b();
            }
        }
    }
}
