package defpackage;

import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.R$dimen;
import androidx.appcompat.widget.SearchView;
import com.google.android.material.navigation.NavigationBarItemView;

/* JADX INFO: loaded from: classes.dex */
public final class ng0 implements View.OnLayoutChangeListener {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ ng0(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // android.view.View.OnLayoutChangeListener
    public final void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        n9 n9Var;
        switch (this.a) {
            case 0:
                NavigationBarItemView navigationBarItemView = (NavigationBarItemView) this.b;
                ImageView imageView = navigationBarItemView.R;
                if (imageView.getVisibility() == 0 && (n9Var = navigationBarItemView.m0) != null) {
                    Rect rect = new Rect();
                    imageView.getDrawingRect(rect);
                    n9Var.setBounds(rect);
                    n9Var.i(imageView, null);
                    break;
                }
                break;
            case 1:
                SearchView searchView = (SearchView) this.b;
                View view2 = searchView.e0;
                if (view2.getWidth() > 1) {
                    Resources resources = searchView.getContext().getResources();
                    int paddingLeft = searchView.V.getPaddingLeft();
                    Rect rect2 = new Rect();
                    boolean zA = q61.a(searchView);
                    int dimensionPixelSize = searchView.t0 ? resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_text_padding_left) + resources.getDimensionPixelSize(R$dimen.abc_dropdownitem_icon_width) : 0;
                    SearchView.SearchAutoComplete searchAutoComplete = searchView.T;
                    searchAutoComplete.getDropDownBackground().getPadding(rect2);
                    searchAutoComplete.setDropDownHorizontalOffset(zA ? -rect2.left : paddingLeft - (rect2.left + dimensionPixelSize));
                    searchAutoComplete.setDropDownWidth((((view2.getWidth() + rect2.left) + rect2.right) + dimensionPixelSize) - paddingLeft);
                }
                break;
            default:
                j11 j11Var = (j11) this.b;
                j11Var.getClass();
                int[] iArr = new int[2];
                view.getLocationOnScreen(iArr);
                j11Var.o0 = iArr[0];
                view.getWindowVisibleDisplayFrame(j11Var.h0);
                break;
        }
    }
}
