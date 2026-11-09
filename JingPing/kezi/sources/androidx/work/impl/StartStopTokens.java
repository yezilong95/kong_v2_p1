package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import defpackage.ae;
import defpackage.oy;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public final class StartStopTokens {
    private final Object lock = new Object();
    private final Map<WorkGenerationalId, StartStopToken> runs = new LinkedHashMap();

    public final boolean contains(WorkGenerationalId workGenerationalId) {
        boolean zContainsKey;
        oy.j(workGenerationalId, "id");
        synchronized (this.lock) {
            zContainsKey = this.runs.containsKey(workGenerationalId);
        }
        return zContainsKey;
    }

    public final StartStopToken remove(WorkGenerationalId workGenerationalId) {
        StartStopToken startStopTokenRemove;
        oy.j(workGenerationalId, "id");
        synchronized (this.lock) {
            startStopTokenRemove = this.runs.remove(workGenerationalId);
        }
        return startStopTokenRemove;
    }

    public final StartStopToken tokenFor(WorkGenerationalId workGenerationalId) {
        StartStopToken startStopToken;
        oy.j(workGenerationalId, "id");
        synchronized (this.lock) {
            try {
                Map<WorkGenerationalId, StartStopToken> map = this.runs;
                StartStopToken startStopToken2 = map.get(workGenerationalId);
                if (startStopToken2 == null) {
                    startStopToken2 = new StartStopToken(workGenerationalId);
                    map.put(workGenerationalId, startStopToken2);
                }
                startStopToken = startStopToken2;
            } catch (Throwable th) {
                throw th;
            }
        }
        return startStopToken;
    }

    public final List<StartStopToken> remove(String str) {
        List<StartStopToken> listF0;
        oy.j(str, "workSpecId");
        synchronized (this.lock) {
            try {
                Map<WorkGenerationalId, StartStopToken> map = this.runs;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry<WorkGenerationalId, StartStopToken> entry : map.entrySet()) {
                    if (oy.c(entry.getKey().getWorkSpecId(), str)) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                Iterator it = linkedHashMap.keySet().iterator();
                while (it.hasNext()) {
                    this.runs.remove((WorkGenerationalId) it.next());
                }
                listF0 = ae.f0(linkedHashMap.values());
            } catch (Throwable th) {
                throw th;
            }
        }
        return listF0;
    }

    public final StartStopToken tokenFor(WorkSpec workSpec) {
        oy.j(workSpec, "spec");
        return tokenFor(WorkSpecKt.generationalId(workSpec));
    }

    public final StartStopToken remove(WorkSpec workSpec) {
        oy.j(workSpec, "spec");
        return remove(WorkSpecKt.generationalId(workSpec));
    }
}
