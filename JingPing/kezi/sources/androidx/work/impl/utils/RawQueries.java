package androidx.work.impl.utils;

import androidx.work.WorkInfo;
import androidx.work.WorkQuery;
import androidx.work.impl.model.WorkTypeConverters;
import defpackage.ae;
import defpackage.ce;
import defpackage.e5;
import defpackage.oy;
import defpackage.vl0;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public final class RawQueries {
    private static final void bindings(StringBuilder sb, int i) {
        if (i <= 0) {
            return;
        }
        ArrayList arrayList = new ArrayList(i);
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add("?");
        }
        sb.append(ae.d0(arrayList, ",", null, 62));
    }

    public static final vl0 toRawQuery(WorkQuery workQuery) {
        String str;
        oy.j(workQuery, "<this>");
        ArrayList arrayList = new ArrayList();
        StringBuilder sb = new StringBuilder("SELECT * FROM workspec");
        List<WorkInfo.State> states = workQuery.getStates();
        oy.i(states, "states");
        String str2 = " AND";
        if (states.isEmpty()) {
            str = " WHERE";
        } else {
            List<WorkInfo.State> states2 = workQuery.getStates();
            oy.i(states2, "states");
            ArrayList arrayList2 = new ArrayList(ce.Z(states2));
            for (WorkInfo.State state : states2) {
                oy.g(state);
                arrayList2.add(Integer.valueOf(WorkTypeConverters.stateToInt(state)));
            }
            sb.append(" WHERE state IN (");
            bindings(sb, arrayList2.size());
            sb.append(")");
            arrayList.addAll(arrayList2);
            str = " AND";
        }
        List<UUID> ids = workQuery.getIds();
        oy.i(ids, "ids");
        if (!ids.isEmpty()) {
            List<UUID> ids2 = workQuery.getIds();
            oy.i(ids2, "ids");
            ArrayList arrayList3 = new ArrayList(ce.Z(ids2));
            Iterator<T> it = ids2.iterator();
            while (it.hasNext()) {
                arrayList3.add(((UUID) it.next()).toString());
            }
            sb.append(str.concat(" id IN ("));
            bindings(sb, workQuery.getIds().size());
            sb.append(")");
            arrayList.addAll(arrayList3);
            str = " AND";
        }
        List<String> tags = workQuery.getTags();
        oy.i(tags, "tags");
        if (tags.isEmpty()) {
            str2 = str;
        } else {
            sb.append(str.concat(" id IN (SELECT work_spec_id FROM worktag WHERE tag IN ("));
            bindings(sb, workQuery.getTags().size());
            sb.append("))");
            List<String> tags2 = workQuery.getTags();
            oy.i(tags2, "tags");
            arrayList.addAll(tags2);
        }
        List<String> uniqueWorkNames = workQuery.getUniqueWorkNames();
        oy.i(uniqueWorkNames, "uniqueWorkNames");
        if (!uniqueWorkNames.isEmpty()) {
            sb.append(str2.concat(" id IN (SELECT work_spec_id FROM workname WHERE name IN ("));
            bindings(sb, workQuery.getUniqueWorkNames().size());
            sb.append("))");
            List<String> uniqueWorkNames2 = workQuery.getUniqueWorkNames();
            oy.i(uniqueWorkNames2, "uniqueWorkNames");
            arrayList.addAll(uniqueWorkNames2);
        }
        sb.append(";");
        String string = sb.toString();
        oy.i(string, "builder.toString()");
        return new e5(string, arrayList.toArray(new Object[0]));
    }
}
