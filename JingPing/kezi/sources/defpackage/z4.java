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
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class z4 {
    public final /* synthetic */ int a;
    public final Object b;
    public Object c;
    public Object d;
    public Object e;
    public Object f;
    public Object g;

    public z4(fx fxVar, String str, tv tvVar, d5 d5Var, Map map) {
        this.a = 2;
        oy.j(fxVar, "url");
        oy.j(str, "method");
        this.b = fxVar;
        this.c = str;
        this.d = tvVar;
        this.e = d5Var;
        this.f = map;
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
        int iC = vn0.c(context, R$attr.colorControlHighlight);
        int iB = vn0.b(context, R$attr.colorButtonNormal);
        int[] iArr = vn0.b;
        int[] iArr2 = vn0.d;
        int iC2 = je.c(iC, i);
        return new ColorStateList(new int[][]{iArr, iArr2, vn0.c, vn0.f}, new int[]{iB, iC2, je.c(iC, i), i});
    }

    public static LayerDrawable c(ve0 ve0Var, Context context, int i) {
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i);
        Drawable drawableC = ve0Var.c(context, R$drawable.abc_star_black_48dp);
        Drawable drawableC2 = ve0Var.c(context, R$drawable.abc_star_half_black_48dp);
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

    public static void f(Drawable drawable, int i, PorterDuff.Mode mode) {
        int[] iArr = ll.a;
        Drawable drawableMutate = drawable.mutate();
        if (mode == null) {
            mode = a5.b;
        }
        drawableMutate.setColorFilter(a5.c(i, mode));
    }

    public ColorStateList d(Context context, int i) {
        if (i == R$drawable.abc_edit_text_material) {
            return rp0.w(context, R$color.abc_tint_edittext);
        }
        if (i == R$drawable.abc_switch_track_mtrl_alpha) {
            return rp0.w(context, R$color.abc_tint_switch_track);
        }
        if (i != R$drawable.abc_switch_thumb_material) {
            if (i == R$drawable.abc_btn_default_mtrl_shape) {
                return b(context, vn0.c(context, R$attr.colorButtonNormal));
            }
            if (i == R$drawable.abc_btn_borderless_material) {
                return b(context, 0);
            }
            if (i == R$drawable.abc_btn_colored_material) {
                return b(context, vn0.c(context, R$attr.colorAccent));
            }
            if (i == R$drawable.abc_spinner_mtrl_am_alpha || i == R$drawable.abc_spinner_textfield_background_material) {
                return rp0.w(context, R$color.abc_tint_spinner);
            }
            if (a((int[]) this.c, i)) {
                return vn0.d(context, R$attr.colorControlNormal);
            }
            if (a((int[]) this.f, i)) {
                return rp0.w(context, R$color.abc_tint_default);
            }
            if (a((int[]) this.g, i)) {
                return rp0.w(context, R$color.abc_tint_btn_checkable);
            }
            if (i == R$drawable.abc_seekbar_thumb_material) {
                return rp0.w(context, R$color.abc_tint_seek_thumb);
            }
            return null;
        }
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList colorStateListD = vn0.d(context, R$attr.colorSwitchThumbNormal);
        if (colorStateListD == null || !colorStateListD.isStateful()) {
            iArr[0] = vn0.b;
            iArr2[0] = vn0.b(context, R$attr.colorSwitchThumbNormal);
            iArr[1] = vn0.e;
            iArr2[1] = vn0.c(context, R$attr.colorControlActivated);
            iArr[2] = vn0.f;
            iArr2[2] = vn0.c(context, R$attr.colorSwitchThumbNormal);
        } else {
            int[] iArr3 = vn0.b;
            iArr[0] = iArr3;
            iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
            iArr[1] = vn0.e;
            iArr2[1] = vn0.c(context, R$attr.colorControlActivated);
            iArr[2] = vn0.f;
            iArr2[2] = colorStateListD.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    public o2 e() {
        o2 o2Var = new o2(false);
        o2Var.f = new LinkedHashMap();
        o2Var.b = (fx) this.b;
        o2Var.c = (String) this.c;
        o2Var.e = (d5) this.e;
        Map map = (Map) this.f;
        o2Var.f = map.isEmpty() ? new LinkedHashMap() : new LinkedHashMap(map);
        o2Var.d = ((tv) this.d).c();
        return o2Var;
    }

    public String toString() {
        switch (this.a) {
            case 2:
                StringBuilder sb = new StringBuilder("Request{method=");
                sb.append((String) this.c);
                sb.append(", url=");
                sb.append((fx) this.b);
                tv tvVar = (tv) this.d;
                if (tvVar.size() != 0) {
                    sb.append(", headers=[");
                    int i = 0;
                    for (Object obj : tvVar) {
                        int i2 = i + 1;
                        if (i < 0) {
                            be.Y();
                            throw null;
                        }
                        Pair pair = (Pair) obj;
                        String str = (String) pair.component1();
                        String str2 = (String) pair.component2();
                        if (i > 0) {
                            sb.append(", ");
                        }
                        sb.append(str);
                        sb.append(':');
                        sb.append(str2);
                        i = i2;
                    }
                    sb.append(']');
                }
                Map map = (Map) this.f;
                if (!map.isEmpty()) {
                    sb.append(", tags=");
                    sb.append(map);
                }
                sb.append('}');
                String string = sb.toString();
                oy.i(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            default:
                return super.toString();
        }
    }

    public z4() {
        this.a = 0;
        this.b = new int[]{R$drawable.abc_textfield_search_default_mtrl_alpha, R$drawable.abc_textfield_default_mtrl_alpha, R$drawable.abc_ab_share_pack_mtrl_alpha};
        this.c = new int[]{R$drawable.abc_ic_commit_search_api_mtrl_alpha, R$drawable.abc_seekbar_tick_mark_material, R$drawable.abc_ic_menu_share_mtrl_alpha, R$drawable.abc_ic_menu_copy_mtrl_am_alpha, R$drawable.abc_ic_menu_cut_mtrl_alpha, R$drawable.abc_ic_menu_selectall_mtrl_alpha, R$drawable.abc_ic_menu_paste_mtrl_am_alpha};
        this.d = new int[]{R$drawable.abc_textfield_activated_mtrl_alpha, R$drawable.abc_textfield_search_activated_mtrl_alpha, R$drawable.abc_cab_background_top_mtrl_alpha, R$drawable.abc_text_cursor_material, R$drawable.abc_text_select_handle_left_mtrl, R$drawable.abc_text_select_handle_middle_mtrl, R$drawable.abc_text_select_handle_right_mtrl};
        this.e = new int[]{R$drawable.abc_popup_background_mtrl_mult, R$drawable.abc_cab_background_internal_bg, R$drawable.abc_menu_hardkey_panel_mtrl_mult};
        this.f = new int[]{R$drawable.abc_tab_indicator_material, R$drawable.abc_textfield_search_material};
        this.g = new int[]{R$drawable.abc_btn_check_material, R$drawable.abc_btn_radio_material, R$drawable.abc_btn_check_material_anim, R$drawable.abc_btn_radio_material_anim};
    }

    public z4(an0 an0Var) {
        this.a = 1;
        oy.j(an0Var, "taskRunner");
        this.b = an0Var;
        this.g = lw.a;
    }
}
