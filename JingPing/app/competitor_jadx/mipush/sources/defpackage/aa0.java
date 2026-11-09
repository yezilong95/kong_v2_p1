package defpackage;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Checkable;
import android.widget.PopupWindow;
import androidx.appcompat.widget.ListPopupWindow;

/* JADX INFO: loaded from: classes.dex */
public final class aa0 implements View.OnTouchListener {
    public final /* synthetic */ int E;
    public final /* synthetic */ Object F;

    public /* synthetic */ aa0(int i, Object obj) {
        this.E = i;
        this.F = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.view.View.OnTouchListener
    public final boolean onTouch(View view, MotionEvent motionEvent) {
        PopupWindow popupWindow;
        switch (this.E) {
            case 0:
                int action = motionEvent.getAction();
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                ListPopupWindow listPopupWindow = (ListPopupWindow) this.F;
                if (action == 0 && (popupWindow = listPopupWindow.d0) != null && popupWindow.isShowing() && x >= 0 && x < listPopupWindow.d0.getWidth() && y >= 0 && y < listPopupWindow.d0.getHeight()) {
                    listPopupWindow.Z.postDelayed(listPopupWindow.V, 250L);
                    return false;
                }
                if (action != 1) {
                    return false;
                }
                listPopupWindow.Z.removeCallbacks(listPopupWindow.V);
                return false;
            default:
                if (((Checkable) view).isChecked()) {
                    return ((GestureDetector) this.F).onTouchEvent(motionEvent);
                }
                return false;
        }
    }
}
