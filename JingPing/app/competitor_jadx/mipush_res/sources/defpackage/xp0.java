package defpackage;

import android.accounts.Account;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.SyncResult;
import android.os.Bundle;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public final class xp0 extends AbstractThreadedSyncAdapter {
    @Override // android.content.AbstractThreadedSyncAdapter
    public final void onPerformSync(Account account, Bundle bundle, String str, ContentProviderClient contentProviderClient, SyncResult syncResult) {
        Log.d("SyncAdapter", "-----------account sync onPerformSync--------");
    }
}
