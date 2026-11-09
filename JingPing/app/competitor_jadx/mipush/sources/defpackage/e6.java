package defpackage;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$color;
import androidx.appcompat.R$drawable;

/* JADX INFO: loaded from: classes.dex */
public final class e6 {
    public final Object a;
    public final Object b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;

    public e6(o71 o71Var, Context context) {
        this.d = null;
        this.e = null;
        this.f = null;
        this.b = context.getApplicationContext();
        this.a = o71Var;
        this.c = o71Var.d;
        this.d = new or0();
        pr0 pr0Var = new pr0();
        pr0Var.a = null;
        pr0Var.b = null;
        pr0Var.c = null;
        pr0Var.d = null;
        this.e = pr0Var;
        this.f = new zb(context);
    }

    public static boolean a(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static ColorStateList b(Context context, int i) {
        int iC = j01.c(context, R$attr.colorControlHighlight);
        int iB = j01.b(context, R$attr.colorButtonNormal);
        int[] iArr = j01.b;
        int[] iArr2 = j01.d;
        int iC2 = dg.c(iC, i);
        return new ColorStateList(new int[][]{iArr, iArr2, j01.c, j01.f}, new int[]{iB, iC2, dg.c(iC, i), i});
    }

    public static LayerDrawable c(uo0 uo0Var, Context context, int i) {
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i);
        Drawable drawableC = uo0Var.c(context, R$drawable.abc_star_black_48dp);
        Drawable drawableC2 = uo0Var.c(context, R$drawable.abc_star_half_black_48dp);
        if ((drawableC instanceof BitmapDrawable) && drawableC.getIntrinsicWidth() == dimensionPixelSize && drawableC.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable = (BitmapDrawable) drawableC;
            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
        } else {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableC.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableC.draw(canvas);
            bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            bitmapDrawable2 = new BitmapDrawable(bitmapCreateBitmap);
        }
        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
        if ((drawableC2 instanceof BitmapDrawable) && drawableC2.getIntrinsicWidth() == dimensionPixelSize && drawableC2.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable3 = (BitmapDrawable) drawableC2;
        } else {
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
            drawableC2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableC2.draw(canvas2);
            bitmapDrawable3 = new BitmapDrawable(bitmapCreateBitmap2);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
        layerDrawable.setId(0, R.id.background);
        layerDrawable.setId(1, R.id.secondaryProgress);
        layerDrawable.setId(2, R.id.progress);
        return layerDrawable;
    }

    public static void e(Drawable drawable, int i, PorterDuff.Mode mode) {
        int[] iArr = op.a;
        Drawable drawableMutate = drawable.mutate();
        if (mode == null) {
            mode = f6.b;
        }
        drawableMutate.setColorFilter(f6.c(i, mode));
    }

    public ColorStateList d(Context context, int i) {
        if (i == R$drawable.abc_edit_text_material) {
            return t40.Y(context, R$color.abc_tint_edittext);
        }
        if (i == R$drawable.abc_switch_track_mtrl_alpha) {
            return t40.Y(context, R$color.abc_tint_switch_track);
        }
        if (i != R$drawable.abc_switch_thumb_material) {
            if (i == R$drawable.abc_btn_default_mtrl_shape) {
                return b(context, j01.c(context, R$attr.colorButtonNormal));
            }
            if (i == R$drawable.abc_btn_borderless_material) {
                return b(context, 0);
            }
            if (i == R$drawable.abc_btn_colored_material) {
                return b(context, j01.c(context, R$attr.colorAccent));
            }
            if (i == R$drawable.abc_spinner_mtrl_am_alpha || i == R$drawable.abc_spinner_textfield_background_material) {
                return t40.Y(context, R$color.abc_tint_spinner);
            }
            if (a((int[]) this.b, i)) {
                return j01.d(context, R$attr.colorControlNormal);
            }
            if (a((int[]) this.e, i)) {
                return t40.Y(context, R$color.abc_tint_default);
            }
            if (a((int[]) this.f, i)) {
                return t40.Y(context, R$color.abc_tint_btn_checkable);
            }
            if (i == R$drawable.abc_seekbar_thumb_material) {
                return t40.Y(context, R$color.abc_tint_seek_thumb);
            }
            return null;
        }
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList colorStateListD = j01.d(context, R$attr.colorSwitchThumbNormal);
        if (colorStateListD == null || !colorStateListD.isStateful()) {
            iArr[0] = j01.b;
            iArr2[0] = j01.b(context, R$attr.colorSwitchThumbNormal);
            iArr[1] = j01.e;
            iArr2[1] = j01.c(context, R$attr.colorControlActivated);
            iArr[2] = j01.f;
            iArr2[2] = j01.c(context, R$attr.colorSwitchThumbNormal);
        } else {
            int[] iArr3 = j01.b;
            iArr[0] = iArr3;
            iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
            iArr[1] = j01.e;
            iArr2[1] = j01.c(context, R$attr.colorControlActivated);
            iArr[2] = j01.f;
            iArr2[2] = colorStateListD.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    public e6() {
        this.a = new int[]{R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};
        this.b = new int[]{R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};
        this.c = new int[]{R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl, R$drawable.abc_text_select_handle_middle_mtrl, R$drawable.abc_text_select_handle_right_mtrl};
        this.d = new int[]{R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
        this.e = new int[]{R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
        this.f = new int[]{R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};
    }
}
