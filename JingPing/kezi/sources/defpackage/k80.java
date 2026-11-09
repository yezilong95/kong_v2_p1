package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.navigation.NavigationBarItemView;

/* JADX INFO: loaded from: classes.dex */
public final class k80 implements View.OnLayoutChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ k80(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        t7 t7Var;
        switch (this.a) {
            case 0:
                NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.b;
                ImageView imageView = navigationBarItemView.o;
                if (imageView.getVisibility() == 0 && (t7Var = navigationBarItemView.G) != null) {
                    Rect rect = new Rect();
                    imageView.getDrawingRect(rect);
                    t7Var.setBounds(rect);
                    t7Var.i(imageView, null);
                    break;
                }
                break;
            case 1:
                SearchView searchView = (SearchView) this.b;
                View view2 = searchView.y;
                if (view2.getWidth() > 1) {
                    Resources resources = searchView.getContext().getResources();
                    int paddingLeft = searchView.s.getPaddingLeft();
                    Rect rect2 = new Rect();
                    boolean zA = kt0.a(searchView);
                    int dimensionPixelSize = searchView.N ? resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) : 0;
                    SearchView.SearchAutoComplete searchAutoComplete = searchView.q;
                    searchAutoComplete.getDropDownBackground().getPadding(rect2);
                    searchAutoComplete.setDropDownHorizontalOffset(zA ? -rect2.left : paddingLeft - (rect2.left + dimensionPixelSize));
                    searchAutoComplete.setDropDownWidth((((view2.getWidth() + rect2.left) + rect2.right) + dimensionPixelSize) - paddingLeft);
                }
                break;
            default:
                xo0 xo0Var = (xo0) this.b;
                xo0Var.getClass();
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                xo0Var.J = iArr[0];
                view.getWindowVisibleDisplayFrame(xo0Var.D);
                break;
        }
    }
}
