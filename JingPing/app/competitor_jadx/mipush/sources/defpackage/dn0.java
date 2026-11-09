package defpackage;

import android.database.Observable;

/* JADX INFO: loaded from: classes.dex */
public final class dn0 extends Observable {
    public final boolean a() {
        return !((Observable) this).mObservers.isEmpty();
    }

    public final void b() {
        for (int size = ((Observable) this).mObservers.size() - 1; size >= 0; size--) {
            ((en0) ((Observable) this).mObservers.get(size)).a();
        }
    }
}
