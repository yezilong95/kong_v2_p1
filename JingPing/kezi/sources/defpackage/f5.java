package defpackage;

import android.app.Activity;
import android.content.ClipData;
import android.os.Build;
import android.text.Selection;
import android.text.Spannable;
import android.view.DragEvent;
import android.view.View;
import android.widget.TextView;

/* JADX INFO: loaded from: classes.dex */
public abstract class f5 {
    public static boolean a(DragEvent dragEvent, TextView textView, Activity activity) {
        sg o70Var;
        activity.requestDragAndDropPermissions(dragEvent);
        int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
        textView.beginBatchEdit();
        try {
            Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
            ClipData clipData = dragEvent.getClipData();
            if (Build.VERSION.SDK_INT >= 31) {
                o70Var = new o70(clipData, 3);
            } else {
                tg tgVar = new tg();
                tgVar.c = clipData;
                tgVar.d = 3;
                o70Var = tgVar;
            }
            xr0.k(textView, o70Var.d());
            textView.endBatchEdit();
            return true;
        } catch (Throwable th) {
            textView.endBatchEdit();
            throw th;
        }
    }

    public static boolean b(DragEvent dragEvent, View view, Activity activity) {
        sg o70Var;
        activity.requestDragAndDropPermissions(dragEvent);
        ClipData clipData = dragEvent.getClipData();
        if (Build.VERSION.SDK_INT >= 31) {
            o70Var = new o70(clipData, 3);
        } else {
            tg tgVar = new tg();
            tgVar.c = clipData;
            tgVar.d = 3;
            o70Var = tgVar;
        }
        xr0.k(view, o70Var.d());
        return true;
    }
}
