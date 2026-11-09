package androidx.work.impl;

import androidx.work.Operation;
import androidx.work.impl.utils.futures.SettableFuture;
import defpackage.b30;
import defpackage.c80;
import defpackage.x20;

/* JADX INFO: loaded from: classes.dex */
public class OperationImpl implements Operation {
    private final c80 mOperationState = new c80();
    private final SettableFuture<Operation.State.SUCCESS> mOperationFuture = SettableFuture.create();

    public OperationImpl() {
        markState(Operation.IN_PROGRESS);
    }

    @Override // androidx.work.Operation
    public x20 getResult() {
        return this.mOperationFuture;
    }

    @Override // androidx.work.Operation
    public b30 getState() {
        return this.mOperationState;
    }

    public void markState(Operation.State state) {
        this.mOperationState.g(state);
        if (state instanceof Operation.State.SUCCESS) {
            this.mOperationFuture.set((Operation.State.SUCCESS) state);
        } else if (state instanceof Operation.State.FAILURE) {
            this.mOperationFuture.setException(((Operation.State.FAILURE) state).getThrowable());
        }
    }
}
