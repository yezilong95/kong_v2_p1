package defpackage;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.R$string;
import com.google.android.material.bottomsheet.BottomSheetDragHandleView;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.button.MaterialButtonToggleGroup;
import com.google.android.material.datepicker.l;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.NavigationMenuItemView;

/* JADX INFO: loaded from: classes.dex */
public final class j9 extends b0 {
    public final /* synthetic */ int d;
    public final /* synthetic */ Object e;

    public /* synthetic */ j9(int i, Object obj) {
        this.d = i;
        this.e = obj;
    }

    @Override // defpackage.b0
    public void c(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 1:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setChecked(((CheckableImageButton) this.e).e);
                break;
            case 5:
                super.c(view, accessibilityEvent);
                accessibilityEvent.setClassName(ViewPager.class.getName());
                ViewPager viewPager = (ViewPager) this.e;
                viewPager.getClass();
                accessibilityEvent.setScrollable(false);
                if (accessibilityEvent.getEventType() == 4096) {
                    viewPager.getClass();
                }
                break;
            default:
                super.c(view, accessibilityEvent);
                break;
        }
    }

    @Override // defpackage.b0
    public void d(View view, o0 o0Var) {
        int i;
        Object obj = this.e;
        View.AccessibilityDelegate accessibilityDelegate = this.a;
        switch (this.d) {
            case 1:
                AccessibilityNodeInfo accessibilityNodeInfo = o0Var.a;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo);
                CheckableImageButton checkableImageButton = (CheckableImageButton) obj;
                accessibilityNodeInfo.setCheckable(checkableImageButton.f);
                accessibilityNodeInfo.setChecked(checkableImageButton.e);
                break;
            case 2:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, o0Var.a);
                int i2 = MaterialButtonToggleGroup.l;
                MaterialButtonToggleGroup materialButtonToggleGroup = (MaterialButtonToggleGroup) obj;
                if (view instanceof MaterialButton) {
                    i = 0;
                    for (int i3 = 0; i3 < materialButtonToggleGroup.getChildCount(); i3++) {
                        if (materialButtonToggleGroup.getChildAt(i3) != view) {
                            if ((materialButtonToggleGroup.getChildAt(i3) instanceof MaterialButton) && materialButtonToggleGroup.c(i3)) {
                                i++;
                            }
                        }
                    }
                    i = -1;
                } else {
                    i = -1;
                }
                o0Var.j(n0.a(0, 1, i, 1, false, ((MaterialButton) view).p));
                break;
            case 3:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, o0Var.a);
                l lVar = (l) obj;
                o0Var.k(lVar.f0.getVisibility() == 0 ? lVar.y().getResources().getString(R$string.mtrl_picker_toggle_to_year_selection) : lVar.y().getResources().getString(R$string.mtrl_picker_toggle_to_day_selection));
                break;
            case 4:
                AccessibilityNodeInfo accessibilityNodeInfo2 = o0Var.a;
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, accessibilityNodeInfo2);
                accessibilityNodeInfo2.setCheckable(((NavigationMenuItemView) obj).y);
                break;
            case 5:
                accessibilityDelegate.onInitializeAccessibilityNodeInfo(view, o0Var.a);
                o0Var.i(ViewPager.class.getName());
                ViewPager viewPager = (ViewPager) obj;
                viewPager.getClass();
                o0Var.l(false);
                viewPager.getClass();
                break;
            default:
                super.d(view, o0Var);
                break;
        }
    }

    @Override // defpackage.b0
    public void e(View view, AccessibilityEvent accessibilityEvent) {
        switch (this.d) {
            case 0:
                super.e(view, accessibilityEvent);
                if (accessibilityEvent.getEventType() == 1) {
                    int i = BottomSheetDragHandleView.n;
                    ((BottomSheetDragHandleView) this.e).c();
                }
                break;
            default:
                super.e(view, accessibilityEvent);
                break;
        }
    }

    @Override // defpackage.b0
    public boolean g(View view, int i, Bundle bundle) {
        switch (this.d) {
            case 5:
                if (super.g(view, i, bundle)) {
                    return true;
                }
                ViewPager viewPager = (ViewPager) this.e;
                if (i == 4096 || i == 8192) {
                    viewPager.getClass();
                }
                return false;
            default:
                return super.g(view, i, bundle);
        }
    }
}
