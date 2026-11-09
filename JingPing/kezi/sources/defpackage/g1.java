package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$bool;
import androidx.appcompat.R$dimen;
import androidx.appcompat.R$styleable;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class g1 implements gn {
    public Context a;

    public static g1 b(Context context) {
        g1 g1Var = new g1();
        g1Var.a = context;
        return g1Var;
    }

    @Override // defpackage.gn
    public void a(za0 za0Var) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 1, 15L, TimeUnit.SECONDS, new LinkedBlockingDeque(), new gf("EmojiCompatInitializer"));
        threadPoolExecutor.allowCoreThreadTimeOut(true);
        threadPoolExecutor.execute(new xm(this, za0Var, threadPoolExecutor, 1));
    }

    public int c() {
        Configuration configuration = this.a.getResources().getConfiguration();
        int i = configuration.screenWidthDp;
        int i2 = configuration.screenHeightDp;
        if (configuration.smallestScreenWidthDp > 600 || i > 600) {
            return 5;
        }
        if (i > 960 && i2 > 720) {
            return 5;
        }
        if (i > 720 && i2 > 960) {
            return 5;
        }
        if (i >= 500) {
            return 4;
        }
        if (i > 640 && i2 > 480) {
            return 4;
        }
        if (i <= 480 || i2 <= 640) {
            return i >= 360 ? 3 : 2;
        }
        return 4;
    }

    public int d() {
        int[] iArr = R$styleable.ActionBar;
        int i = R$attr.actionBarStyle;
        Context context = this.a;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(null, iArr, i, 0);
        int layoutDimension = typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0);
        Resources resources = context.getResources();
        if (!this.a.getResources().getBoolean(R$bool.abc_action_bar_embed_tabs)) {
            layoutDimension = Math.min(layoutDimension, resources.getDimensionPixelSize(R$dimen.abc_action_bar_stacked_max_height));
        }
        typedArrayObtainStyledAttributes.recycle();
        return layoutDimension;
    }
}
