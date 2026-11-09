package defpackage;

import android.view.View;
import android.view.WindowInsets;
import androidx.drawerlayout.widget.DrawerLayout;

/* JADX INFO: loaded from: classes.dex */
public final class pl implements View.OnApplyWindowInsetsListener {
    @Override // android.view.View.OnApplyWindowInsetsListener
    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
        DrawerLayout drawerLayout = (DrawerLayout) view;
        boolean z = false;
        boolean z2 = windowInsets.getSystemWindowInsetTop() > 0;
        drawerLayout.y = windowInsets;
        drawerLayout.z = z2;
        if (!z2 && drawerLayout.getBackground() == null) {
            z = true;
        }
        drawerLayout.setWillNotDraw(z);
        drawerLayout.requestLayout();
        return windowInsets.consumeSystemWindowInsets();
    }
}
