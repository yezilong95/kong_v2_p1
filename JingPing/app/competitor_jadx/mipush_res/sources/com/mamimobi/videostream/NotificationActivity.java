package com.mamimobi.videostream;

import android.R;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.mamimobi.mipush.R$id;
import com.mamimobi.mipush.R$layout;
import com.mamimobi.videostream.NotificationActivity;

/* JADX INFO: loaded from: classes.dex */
public class NotificationActivity extends Activity {
    public static final /* synthetic */ int E = 0;

    @Override // android.app.Activity
    public final void onCreate(Bundle bundle) {
        Bitmap bitmapDecodeByteArray;
        super.onCreate(bundle);
        String stringExtra = getIntent().getStringExtra("title");
        String stringExtra2 = getIntent().getStringExtra("icon");
        String stringExtra3 = getIntent().getStringExtra("content");
        final String stringExtra4 = getIntent().getStringExtra("pkgname");
        try {
            if (stringExtra2.contains(",")) {
                stringExtra2 = stringExtra2.split(",")[1];
            }
            byte[] bArrDecode = Base64.decode(stringExtra2, 0);
            bitmapDecodeByteArray = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
        } catch (Exception e) {
            e.printStackTrace();
            bitmapDecodeByteArray = null;
        }
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R$layout.dialog_notification);
        dialog.getWindow().setBackgroundDrawableResource(R.color.transparent);
        dialog.getWindow().setLayout((int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.8d), -2);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 17;
        attributes.dimAmount = 0.4f;
        dialog.getWindow().setAttributes(attributes);
        ImageView imageView = (ImageView) dialog.findViewById(R$id.notification_icon);
        TextView textView = (TextView) dialog.findViewById(R$id.notification_title);
        TextView textView2 = (TextView) dialog.findViewById(R$id.notification_content);
        Button button = (Button) dialog.findViewById(R$id.btn_confirm);
        imageView.setImageBitmap(bitmapDecodeByteArray);
        textView.setText(stringExtra);
        textView2.setText(stringExtra3);
        button.setOnClickListener(new View.OnClickListener() { // from class: yh0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                int i = NotificationActivity.E;
                NotificationActivity notificationActivity = this.E;
                Intent launchIntentForPackage = notificationActivity.getPackageManager().getLaunchIntentForPackage(stringExtra4);
                if (launchIntentForPackage != null) {
                    notificationActivity.startActivity(launchIntentForPackage);
                }
                dialog.dismiss();
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: zh0
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                int i = NotificationActivity.E;
                NotificationActivity notificationActivity = this.E;
                if (notificationActivity.isFinishing()) {
                    return;
                }
                notificationActivity.finish();
            }
        });
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override // android.app.Activity
    public final void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i == 1001) {
            if (iArr.length <= 0 || iArr[0] != 0) {
                Intent intent = new Intent(this, (Class<?>) NotificationService.class);
                intent.setAction("com.mamimobi.videostream.FLUSH_PENDING");
                startService(intent);
                finish();
                return;
            }
            Intent intent2 = new Intent(this, (Class<?>) NotificationService.class);
            intent2.setAction("com.mamimobi.videostream.FLUSH_PENDING");
            startService(intent2);
            finish();
        }
    }
}
