package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewStub;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.appcompat.app.AlertController$RecycleListView;
import androidx.core.widget.NestedScrollView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public final class i4 {
    public final Context a;
    public final k4 b;
    public final Window c;
    public CharSequence d;
    public AlertController$RecycleListView e;
    public Button f;
    public Button g;
    public Button h;
    public NestedScrollView i;
    public Drawable j;
    public ImageView k;
    public TextView l;
    public TextView m;
    public View n;
    public ListAdapter o;
    public final int q;
    public final int r;
    public final int s;
    public final int t;
    public final boolean u;
    public final g4 v;
    public int p = -1;
    public final y2 w = new y2(1, this);

    public i4(Context context, k4 k4Var, Window window) {
        this.a = context;
        this.b = k4Var;
        this.c = window;
        g4 g4Var = new g4();
        g4Var.a = new WeakReference(k4Var);
        this.v = g4Var;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, R$styleable.AlertDialog, R$attr.alertDialogStyle, 0);
        this.q = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_android_layout, 0);
        typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_buttonPanelSideLayout, 0);
        this.r = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listLayout, 0);
        typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_multiChoiceItemLayout, 0);
        this.s = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_singleChoiceItemLayout, 0);
        this.t = typedArrayObtainStyledAttributes.getResourceId(R$styleable.AlertDialog_listItemLayout, 0);
        this.u = typedArrayObtainStyledAttributes.getBoolean(R$styleable.AlertDialog_showTitle, true);
        typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.AlertDialog_buttonIconDimen, 0);
        typedArrayObtainStyledAttributes.recycle();
        k4Var.c().h(1);
    }

    public static ViewGroup a(View view, View view2) {
        if (view == null) {
            if (view2 instanceof ViewStub) {
                view2 = ((ViewStub) view2).inflate();
            }
            return (ViewGroup) view2;
        }
        if (view2 != null) {
            ViewParent parent = view2.getParent();
            if (parent instanceof ViewGroup) {
                ((ViewGroup) parent).removeView(view2);
            }
        }
        if (view instanceof ViewStub) {
            view = ((ViewStub) view).inflate();
        }
        return (ViewGroup) view;
    }
}
