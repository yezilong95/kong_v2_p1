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
public final class u40 extends ArrayAdapter {
    public ColorStateList b;
    public ColorStateList c;
    public final /* synthetic */ MaterialAutoCompleteTextView d;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public u40(MaterialAutoCompleteTextView materialAutoCompleteTextView, Context context, int i, String[] strArr) {
        super(context, i, strArr);
        this.d = materialAutoCompleteTextView;
        a();
    }

    public final void a() {
        ColorStateList colorStateList;
        MaterialAutoCompleteTextView materialAutoCompleteTextView = this.d;
        ColorStateList colorStateList2 = materialAutoCompleteTextView.m;
        ColorStateList colorStateList3 = null;
        if (colorStateList2 != null) {
            int[] iArr = {R.attr.state_pressed};
            colorStateList = new ColorStateList(new int[][]{iArr, new int[0]}, new int[]{colorStateList2.getColorForState(iArr, 0), 0});
        } else {
            colorStateList = null;
        }
        this.c = colorStateList;
        if (materialAutoCompleteTextView.l != 0 && materialAutoCompleteTextView.m != null) {
            int[] iArr2 = {R.attr.state_hovered, -16842919};
            int[] iArr3 = {R.attr.state_selected, -16842919};
            colorStateList3 = new ColorStateList(new int[][]{iArr3, iArr2, new int[0]}, new int[]{je.c(materialAutoCompleteTextView.m.getColorForState(iArr3, 0), materialAutoCompleteTextView.l), je.c(materialAutoCompleteTextView.m.getColorForState(iArr2, 0), materialAutoCompleteTextView.l), materialAutoCompleteTextView.l});
        }
        this.b = colorStateList3;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int i, View view, ViewGroup viewGroup) {
        View view2 = super.getView(i, view, viewGroup);
        if (view2 instanceof TextView) {
            TextView textView = (TextView) view2;
            MaterialAutoCompleteTextView materialAutoCompleteTextView = this.d;
            Drawable rippleDrawable = null;
            if (materialAutoCompleteTextView.getText().toString().contentEquals(textView.getText()) && materialAutoCompleteTextView.l != 0) {
                ColorDrawable colorDrawable = new ColorDrawable(materialAutoCompleteTextView.l);
                if (this.c != null) {
                    hl.h(colorDrawable, this.b);
                    rippleDrawable = new RippleDrawable(this.c, colorDrawable, null);
                } else {
                    rippleDrawable = colorDrawable;
                }
            }
            WeakHashMap weakHashMap = xr0.a;
            textView.setBackground(rippleDrawable);
        }
        return view2;
    }
}
