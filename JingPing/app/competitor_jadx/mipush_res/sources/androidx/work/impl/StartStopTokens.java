package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import defpackage.t40;
import defpackage.vf;
import io.nats.client.support.ApiConstants;
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
        t40.n(workGenerationalId, ApiConstants.ID);
        synchronized (this.lock) {
            zContainsKey = this.runs.containsKey(workGenerationalId);
        }
        return zContainsKey;
    }

    public final StartStopToken remove(WorkGenerationalId workGenerationalId) {
        StartStopToken startStopTokenRemove;
        t40.n(workGenerationalId, ApiConstants.ID);
        synchronized (this.lock) {
            startStopTokenRemove = this.runs.remove(workGenerationalId);
        }
        return startStopTokenRemove;
    }

    public final StartStopToken tokenFor(WorkGenerationalId workGenerationalId) {
        StartStopToken startStopToken;
        t40.n(workGenerationalId, ApiConstants.ID);
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
        List<StartStopToken> listM1;
        t40.n(str, "workSpecId");
        synchronized (this.lock) {
            try {
                Map<WorkGenerationalId, StartStopToken> map = this.runs;
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                for (Map.Entry<WorkGenerationalId, StartStopToken> entry : map.entrySet()) {
                    if (t40.f(entry.getKey().getWorkSpecId(), str)) {
                        linkedHashMap.put(entry.getKey(), entry.getValue());
                    }
                }
                Iterator it = linkedHashMap.keySet().iterator();
                while (it.hasNext()) {
                    this.runs.remove((WorkGenerationalId) it.next());
                }
                listM1 = vf.m1(linkedHashMap.values());
            } catch (Throwable th) {
                throw th;
            }
        }
        return listM1;
    }

    public final StartStopToken tokenFor(WorkSpec workSpec) {
        t40.n(workSpec, "spec");
        return tokenFor(WorkSpecKt.generationalId(workSpec));
    }

    public final StartStopToken remove(WorkSpec workSpec) {
        t40.n(workSpec, "spec");
        return remove(WorkSpecKt.generationalId(workSpec));
    }
}
