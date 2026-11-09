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
public abstract class j6 {
    public static boolean a(DragEvent dragEvent, TextView textView, Activity activity) {
        hi p50Var;
        activity.requestDragAndDropPermissions(dragEvent);
        int offsetForPosition = textView.getOffsetForPosition(dragEvent.getX(), dragEvent.getY());
        textView.beginBatchEdit();
        try {
            Selection.setSelection((Spannable) textView.getText(), offsetForPosition);
            ClipData clipData = dragEvent.getClipData();
            if (Build.VERSION.SDK_INT >= 31) {
                p50Var = new p50(clipData, 3);
            } else {
                ii iiVar = new ii();
                iiVar.F = clipData;
                iiVar.G = 3;
                p50Var = iiVar;
            }
            f51.k(textView, p50Var.a());
            textView.endBatchEdit();
            return true;
        } catch (Throwable th) {
            textView.endBatchEdit();
            throw th;
        }
    }

    public static boolean b(DragEvent dragEvent, View view, Activity activity) {
        hi p50Var;
        activity.requestDragAndDropPermissions(dragEvent);
        ClipData clipData = dragEvent.getClipData();
        if (Build.VERSION.SDK_INT >= 31) {
            p50Var = new p50(clipData, 3);
        } else {
            ii iiVar = new ii();
            iiVar.F = clipData;
            iiVar.G = 3;
            p50Var = iiVar;
        }
        f51.k(view, p50Var.a());
        return true;
    }
}
