package androidx.work.impl;

import androidx.work.Operation;
import androidx.work.impl.utils.futures.SettableFuture;
import defpackage.ca0;
import defpackage.ea0;
import defpackage.wf0;

/* JADX INFO: loaded from: classes.dex */
public class OperationImpl implements Operation {
    private final wf0 mOperationState = new wf0();
    private final SettableFuture<Operation.State.SUCCESS> mOperationFuture = SettableFuture.create();

    public OperationImpl() {
        markState(Operation.IN_PROGRESS);
    }

    @Override // androidx.work.Operation
    public ca0 getResult() {
        return this.mOperationFuture;
    }

    @Override // androidx.work.Operation
    public ea0 getState() {
        return this.mOperationState;
    }

    public void markState(Operation.State state) {
        this.mOperationState.f(state);
        if (state instanceof Operation.State.SUCCESS) {
            this.mOperationFuture.set((Operation.State.SUCCESS) state);
        } else if (state instanceof Operation.State.FAILURE) {
            this.mOperationFuture.setException(((Operation.State.FAILURE) state).getThrowable());
        }
    }
}
