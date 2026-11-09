package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import android.widget.PopupWindow;
import androidx.appcompat.widget.ListPopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class v20 implements View.OnTouchListener {
    public final /* synthetic */ int b;
    public final /* synthetic */ Object c;

    public /* synthetic */ v20(int i, Object obj) {
        this.b = i;
        this.c = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        PopupWindow popupWindow;
        switch (this.b) {
            case 0:
                int action = motionEvent.getAction();
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                ListPopupWindow listPopupWindow = (ListPopupWindow) this.c;
                if (action == 0 && (popupWindow = listPopupWindow.A) != null && popupWindow.isShowing() && x >= 0 && x < listPopupWindow.A.getWidth() && y >= 0 && y < listPopupWindow.A.getHeight()) {
                    listPopupWindow.w.postDelayed(listPopupWindow.s, 250L);
                    return false;
                }
                if (action != 1) {
                    return false;
                }
                listPopupWindow.w.removeCallbacks(listPopupWindow.s);
                return false;
            default:
                if (((Checkable) view).isChecked()) {
                    return ((GestureDetector) this.c).onTouchEvent(motionEvent);
                }
                return false;
        }
    }
}
