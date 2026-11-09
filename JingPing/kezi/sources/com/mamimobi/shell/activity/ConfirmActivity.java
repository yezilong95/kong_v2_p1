package com.mamimobi.shell.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: loaded from: classes.dex */
public class ConfirmActivity extends Activity {
    @Override // android.app.Activity
    public final void onActivityResult(int i, int i2, Intent intent) {
        if (i == 322) {
            super.finish();
        }
        super.onActivityResult(i, i2, intent);
    }

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        intent.getIntExtra("session_id", -1);
        Intent intent2 = (Intent) intent.getParcelableExtra("confirmation_intent");
        if (bundle != null || intent2 == null) {
            super.finish();
            return;
        }
        try {
            startActivityForResult(intent2, 322);
        } catch (Exception unused) {
            super.finish();
        }
    }
}
