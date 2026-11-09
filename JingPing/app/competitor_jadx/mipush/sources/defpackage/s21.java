package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.Font;
import android.graphics.fonts.FontFamily;
import android.graphics.fonts.FontStyle;
import android.os.ParcelFileDescriptor;
import io.nats.client.support.Status;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public final class s21 extends ma0 {
    public static Font B0(FontFamily fontFamily, int i) {
        FontStyle fontStyle = new FontStyle((i & 1) != 0 ? 700 : Status.BAD_REQUEST_CODE, (i & 2) != 0 ? 1 : 0);
        Font font = fontFamily.getFont(0);
        int iC0 = C0(fontStyle, font.getStyle());
        for (int i2 = 1; i2 < fontFamily.getSize(); i2++) {
            Font font2 = fontFamily.getFont(i2);
            int iC02 = C0(fontStyle, font2.getStyle());
            if (iC02 < iC0) {
                font = font2;
                iC0 = iC02;
            }
        }
        return font;
    }

    public static int C0(FontStyle fontStyle, FontStyle fontStyle2) {
        return (Math.abs(fontStyle.getWeight() - fontStyle2.getWeight()) / 100) + (fontStyle.getSlant() == fontStyle2.getSlant() ? 0 : 2);
    }

    @Override // defpackage.ma0
    public final oz A(int i, oz[] ozVarArr) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // defpackage.ma0
    public final Typeface o(Context context, lz lzVar, Resources resources, int i) {
        try {
            FontFamily.Builder builder = null;
            for (mz mzVar : lzVar.a) {
                try {
                    Font fontBuild = new Font.Builder(resources, mzVar.f).setWeight(mzVar.b).setSlant(mzVar.c ? 1 : 0).setTtcIndex(mzVar.e).setFontVariationSettings(mzVar.d).build();
                    if (builder == null) {
                        builder = new FontFamily.Builder(fontBuild);
                    } else {
                        builder.addFont(fontBuild);
                    }
                } catch (IOException unused) {
                }
            }
            if (builder == null) {
                return null;
            }
            FontFamily fontFamilyBuild = builder.build();
            return new Typeface.CustomFallbackBuilder(fontFamilyBuild).setStyle(B0(fontFamilyBuild, i).getStyle()).build();
        } catch (Exception unused2) {
            return null;
        }
    }

    @Override // defpackage.ma0
    public final Typeface p(Context context, oz[] ozVarArr, int i) {
        ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor;
        ContentResolver contentResolver = context.getContentResolver();
        try {
            FontFamily.Builder builder = null;
            for (oz ozVar : ozVarArr) {
                try {
                    parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(ozVar.a, "r", null);
                } catch (IOException unused) {
                }
                if (parcelFileDescriptorOpenFileDescriptor == null) {
                    if (parcelFileDescriptorOpenFileDescriptor != null) {
                    }
                } else {
                    try {
                        Font fontBuild = new Font.Builder(parcelFileDescriptorOpenFileDescriptor).setWeight(ozVar.c).setSlant(ozVar.d ? 1 : 0).setTtcIndex(ozVar.b).build();
                        if (builder == null) {
                            builder = new FontFamily.Builder(fontBuild);
                        } else {
                            builder.addFont(fontBuild);
                        }
                    } catch (Throwable th) {
                        try {
                            parcelFileDescriptorOpenFileDescriptor.close();
                        } catch (Throwable th2) {
                            th.addSuppressed(th2);
                        }
                        throw th;
                    }
                }
                parcelFileDescriptorOpenFileDescriptor.close();
            }
            if (builder != null) {
                FontFamily fontFamilyBuild = builder.build();
                return new Typeface.CustomFallbackBuilder(fontFamilyBuild).setStyle(B0(fontFamilyBuild, i).getStyle()).build();
            }
        } catch (Exception unused2) {
        }
        return null;
    }

    @Override // defpackage.ma0
    public final Typeface q(Context context, InputStream inputStream) {
        throw new RuntimeException("Do not use this function in API 29 or later.");
    }

    @Override // defpackage.ma0
    public final Typeface r(Context context, Resources resources, int i, String str, int i2) {
        try {
            Font fontBuild = new Font.Builder(resources, i).build();
            return new Typeface.CustomFallbackBuilder(new FontFamily.Builder(fontBuild).build()).setStyle(fontBuild.getStyle()).build();
        } catch (Exception unused) {
            return null;
        }
    }
}
