package defpackage;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public final class kc0 extends ArrayAdapter {
    public ColorStateList E;
    public ColorStateList F;
    public final /* synthetic */ MaterialAutoCompleteTextView G;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public kc0(MaterialAutoCompleteTextView materialAutoCompleteTextView, Context context, int i, String[] strArr) {
        super(context, i, strArr);
        this.G = materialAutoCompleteTextView;
        a();
    }

    public final void a() {
        ColorStateList colorStateList;
        MaterialAutoCompleteTextView materialAutoCompleteTextView = this.G;
        ColorStateList colorStateList2 = materialAutoCompleteTextView.P;
        ColorStateList colorStateList3 = null;
        if (colorStateList2 != null) {
            int[] iArr = {R.attr.state_pressed};
            colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{colorStateList2.getColorForState(iArr, 0), 0});
        } else {
            colorStateList = null;
        }
        this.F = colorStateList;
        if (materialAutoCompleteTextView.O != 0 && materialAutoCompleteTextView.P != null) {
            int[] iArr2 = {R.attr.state_hovered, -16842919};
            int[] iArr3 = {R.attr.state_selected, -16842919};
            colorStateList3 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{dg.c(materialAutoCompleteTextView.P.getColorForState(iArr3, 0), materialAutoCompleteTextView.O), dg.c(materialAutoCompleteTextView.P.getColorForState(iArr2, 0), materialAutoCompleteTextView.O), materialAutoCompleteTextView.O});
        }
        this.E = colorStateList3;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        if (view2 instanceof TextView) {
            TextView textView = (TextView) view2;
            MaterialAutoCompleteTextView materialAutoCompleteTextView = this.G;
            Drawable rippleDrawable = null;
            if (materialAutoCompleteTextView.getText().toString().contentEquals(textView.getText()) && materialAutoCompleteTextView.O != 0) {
                ColorDrawable colorDrawable = new ColorDrawable(materialAutoCompleteTextView.O);
                if (this.F != null) {
                    ip.h(colorDrawable, this.E);
                    rippleDrawable = new RippleDrawable(this.F, colorDrawable, null);
                } else {
                    rippleDrawable = colorDrawable;
                }
            }
            WeakHashMap weakHashMap = f51.a;
            textView.setBackground(rippleDrawable);
        }
        return view2;
    }
}
