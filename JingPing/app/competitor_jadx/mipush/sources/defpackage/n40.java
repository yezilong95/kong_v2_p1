package defpackage;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import com.mamimobi.AccessService;
import com.mamimobi.activity.InstallPermissionActivity;
import org.java_websocket.framing.CloseFrame;

/* JADX INFO: loaded from: classes.dex */
public final class n40 implements DialogInterface.OnClickListener {
    public final /* synthetic */ InstallPermissionActivity E;

    public n40(InstallPermissionActivity installPermissionActivity) {
        this.E = installPermissionActivity;
    }

    @Override // android.content.DialogInterface.OnClickListener
    public final void onClick(DialogInterface dialogInterface, int i) {
        AccessService.v0.set(true);
        StringBuilder sb = new StringBuilder("package:");
        InstallPermissionActivity installPermissionActivity = this.E;
        sb.append(installPermissionActivity.getPackageName());
        Intent intent = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES", Uri.parse(sb.toString()));
        intent.addFlags(268435456);
        installPermissionActivity.startActivityForResult(intent, CloseFrame.GOING_AWAY);
    }
}
