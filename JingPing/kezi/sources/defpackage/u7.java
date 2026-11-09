package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Xml;
import com.google.android.material.R$dimen;
import com.google.android.material.R$plurals;
import com.google.android.material.R$string;
import com.google.android.material.R$style;
import com.google.android.material.R$styleable;
import com.google.android.material.badge.BadgeState$State;
import java.io.IOException;
import java.util.Locale;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public final class u7 {
    public final BadgeState$State a;
    public final BadgeState$State b;
    public final float c;
    public final float d;
    public final float e;
    public final float f;
    public final float g;
    public final float h;
    public final int i;
    public final int j;
    public final int k;

    public u7(Context context, BadgeState$State badgeState$State) {
        AttributeSet attributeSetAsAttributeSet;
        int styleAttribute;
        int next;
        int i = t7.p;
        int i2 = t7.o;
        this.b = new BadgeState$State();
        badgeState$State = badgeState$State == null ? new BadgeState$State() : badgeState$State;
        int i3 = badgeState$State.b;
        if (i3 != 0) {
            try {
                XmlResourceParser xml = context.getResources().getXml(i3);
                do {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } while (next != 1);
                if (next != 2) {
                    throw new XmlPullParserException("No start tag found");
                }
                if (!TextUtils.equals(xml.getName(), "badge")) {
                    throw new XmlPullParserException("Must have a <" + ((Object) "badge") + "> start tag");
                }
                attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
                styleAttribute = attributeSetAsAttributeSet.getStyleAttribute();
            } catch (IOException | XmlPullParserException e) {
                Resources.NotFoundException notFoundException = new Resources.NotFoundException("Can't load badge resource ID #0x" + Integer.toHexString(i3));
                notFoundException.initCause(e);
                throw notFoundException;
            }
        } else {
            attributeSetAsAttributeSet = null;
            styleAttribute = 0;
        }
        TypedArray typedArrayD = un0.d(context, attributeSetAsAttributeSet, R$styleable.Badge, i, styleAttribute == 0 ? i2 : styleAttribute, new int[0]);
        Resources resources = context.getResources();
        this.c = typedArrayD.getDimensionPixelSize(R$styleable.Badge_badgeRadius, -1);
        this.i = context.getResources().getDimensionPixelSize(R$dimen.mtrl_badge_horizontal_edge_offset);
        this.j = context.getResources().getDimensionPixelSize(R$dimen.mtrl_badge_text_horizontal_edge_offset);
        this.d = typedArrayD.getDimensionPixelSize(R$styleable.Badge_badgeWithTextRadius, -1);
        this.e = typedArrayD.getDimension(R$styleable.Badge_badgeWidth, resources.getDimension(R$dimen.m3_badge_size));
        this.g = typedArrayD.getDimension(R$styleable.Badge_badgeWithTextWidth, resources.getDimension(R$dimen.m3_badge_with_text_size));
        this.f = typedArrayD.getDimension(R$styleable.Badge_badgeHeight, resources.getDimension(R$dimen.m3_badge_size));
        this.h = typedArrayD.getDimension(R$styleable.Badge_badgeWithTextHeight, resources.getDimension(R$dimen.m3_badge_with_text_size));
        this.k = typedArrayD.getInt(R$styleable.Badge_offsetAlignmentMode, 1);
        BadgeState$State badgeState$State2 = this.b;
        int i4 = badgeState$State.j;
        badgeState$State2.j = i4 == -2 ? 255 : i4;
        int i5 = badgeState$State.l;
        if (i5 != -2) {
            badgeState$State2.l = i5;
        } else if (typedArrayD.hasValue(R$styleable.Badge_number)) {
            this.b.l = typedArrayD.getInt(R$styleable.Badge_number, 0);
        } else {
            this.b.l = -1;
        }
        String str = badgeState$State.k;
        if (str != null) {
            this.b.k = str;
        } else if (typedArrayD.hasValue(R$styleable.Badge_badgeText)) {
            this.b.k = typedArrayD.getString(R$styleable.Badge_badgeText);
        }
        BadgeState$State badgeState$State3 = this.b;
        badgeState$State3.p = badgeState$State.p;
        CharSequence charSequence = badgeState$State.q;
        badgeState$State3.q = charSequence == null ? context.getString(R$string.mtrl_badge_numberless_content_description) : charSequence;
        BadgeState$State badgeState$State4 = this.b;
        int i6 = badgeState$State.r;
        badgeState$State4.r = i6 == 0 ? R$plurals.mtrl_badge_content_description : i6;
        int i7 = badgeState$State.s;
        badgeState$State4.s = i7 == 0 ? R$string.mtrl_exceed_max_badge_number_content_description : i7;
        Boolean bool = badgeState$State.u;
        badgeState$State4.u = Boolean.valueOf(bool == null || bool.booleanValue());
        BadgeState$State badgeState$State5 = this.b;
        int i8 = badgeState$State.m;
        badgeState$State5.m = i8 == -2 ? typedArrayD.getInt(R$styleable.Badge_maxCharacterCount, -2) : i8;
        BadgeState$State badgeState$State6 = this.b;
        int i9 = badgeState$State.n;
        badgeState$State6.n = i9 == -2 ? typedArrayD.getInt(R$styleable.Badge_maxNumber, -2) : i9;
        BadgeState$State badgeState$State7 = this.b;
        Integer num = badgeState$State.f;
        badgeState$State7.f = Integer.valueOf(num == null ? typedArrayD.getResourceId(R$styleable.Badge_badgeShapeAppearance, R$style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : num.intValue());
        BadgeState$State badgeState$State8 = this.b;
        Integer num2 = badgeState$State.g;
        badgeState$State8.g = Integer.valueOf(num2 == null ? typedArrayD.getResourceId(R$styleable.Badge_badgeShapeAppearanceOverlay, 0) : num2.intValue());
        BadgeState$State badgeState$State9 = this.b;
        Integer num3 = badgeState$State.h;
        badgeState$State9.h = Integer.valueOf(num3 == null ? typedArrayD.getResourceId(R$styleable.Badge_badgeWithTextShapeAppearance, R$style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : num3.intValue());
        BadgeState$State badgeState$State10 = this.b;
        Integer num4 = badgeState$State.i;
        badgeState$State10.i = Integer.valueOf(num4 == null ? typedArrayD.getResourceId(R$styleable.Badge_badgeWithTextShapeAppearanceOverlay, 0) : num4.intValue());
        BadgeState$State badgeState$State11 = this.b;
        Integer num5 = badgeState$State.c;
        badgeState$State11.c = Integer.valueOf(num5 == null ? xi.s(context, typedArrayD, R$styleable.Badge_backgroundColor).getDefaultColor() : num5.intValue());
        BadgeState$State badgeState$State12 = this.b;
        Integer num6 = badgeState$State.e;
        badgeState$State12.e = Integer.valueOf(num6 == null ? typedArrayD.getResourceId(R$styleable.Badge_badgeTextAppearance, R$style.TextAppearance_MaterialComponents_Badge) : num6.intValue());
        Integer num7 = badgeState$State.d;
        if (num7 != null) {
            this.b.d = num7;
        } else if (typedArrayD.hasValue(R$styleable.Badge_badgeTextColor)) {
            this.b.d = Integer.valueOf(xi.s(context, typedArrayD, R$styleable.Badge_badgeTextColor).getDefaultColor());
        } else {
            int iIntValue = this.b.e.intValue();
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iIntValue, R$styleable.TextAppearance);
            typedArrayObtainStyledAttributes.getDimension(R$styleable.TextAppearance_android_textSize, 0.0f);
            ColorStateList colorStateListS = xi.s(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColor);
            xi.s(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorHint);
            xi.s(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_textColorLink);
            typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_textStyle, 0);
            typedArrayObtainStyledAttributes.getInt(R$styleable.TextAppearance_android_typeface, 1);
            int i10 = R$styleable.TextAppearance_fontFamily;
            i10 = typedArrayObtainStyledAttributes.hasValue(i10) ? i10 : R$styleable.TextAppearance_android_fontFamily;
            typedArrayObtainStyledAttributes.getResourceId(i10, 0);
            typedArrayObtainStyledAttributes.getString(i10);
            typedArrayObtainStyledAttributes.getBoolean(R$styleable.TextAppearance_textAllCaps, false);
            xi.s(context, typedArrayObtainStyledAttributes, R$styleable.TextAppearance_android_shadowColor);
            typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDx, 0.0f);
            typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowDy, 0.0f);
            typedArrayObtainStyledAttributes.getFloat(R$styleable.TextAppearance_android_shadowRadius, 0.0f);
            typedArrayObtainStyledAttributes.recycle();
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(iIntValue, R$styleable.MaterialTextAppearance);
            typedArrayObtainStyledAttributes2.hasValue(R$styleable.MaterialTextAppearance_android_letterSpacing);
            typedArrayObtainStyledAttributes2.getFloat(R$styleable.MaterialTextAppearance_android_letterSpacing, 0.0f);
            typedArrayObtainStyledAttributes2.recycle();
            this.b.d = Integer.valueOf(colorStateListS.getDefaultColor());
        }
        BadgeState$State badgeState$State13 = this.b;
        Integer num8 = badgeState$State.t;
        badgeState$State13.t = Integer.valueOf(num8 == null ? typedArrayD.getInt(R$styleable.Badge_badgeGravity, 8388661) : num8.intValue());
        BadgeState$State badgeState$State14 = this.b;
        Integer num9 = badgeState$State.v;
        badgeState$State14.v = Integer.valueOf(num9 == null ? typedArrayD.getDimensionPixelSize(R$styleable.Badge_badgeWidePadding, resources.getDimensionPixelSize(R$dimen.mtrl_badge_long_text_horizontal_padding)) : num9.intValue());
        BadgeState$State badgeState$State15 = this.b;
        Integer num10 = badgeState$State.w;
        badgeState$State15.w = Integer.valueOf(num10 == null ? typedArrayD.getDimensionPixelSize(R$styleable.Badge_badgeVerticalPadding, resources.getDimensionPixelSize(R$dimen.m3_badge_with_text_vertical_padding)) : num10.intValue());
        BadgeState$State badgeState$State16 = this.b;
        Integer num11 = badgeState$State.x;
        badgeState$State16.x = Integer.valueOf(num11 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_horizontalOffset, 0) : num11.intValue());
        BadgeState$State badgeState$State17 = this.b;
        Integer num12 = badgeState$State.y;
        badgeState$State17.y = Integer.valueOf(num12 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_verticalOffset, 0) : num12.intValue());
        BadgeState$State badgeState$State18 = this.b;
        Integer num13 = badgeState$State.z;
        badgeState$State18.z = Integer.valueOf(num13 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_horizontalOffsetWithText, badgeState$State18.x.intValue()) : num13.intValue());
        BadgeState$State badgeState$State19 = this.b;
        Integer num14 = badgeState$State.A;
        badgeState$State19.A = Integer.valueOf(num14 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_verticalOffsetWithText, badgeState$State19.y.intValue()) : num14.intValue());
        BadgeState$State badgeState$State20 = this.b;
        Integer num15 = badgeState$State.D;
        badgeState$State20.D = Integer.valueOf(num15 == null ? typedArrayD.getDimensionPixelOffset(R$styleable.Badge_largeFontVerticalOffsetAdjustment, 0) : num15.intValue());
        BadgeState$State badgeState$State21 = this.b;
        Integer num16 = badgeState$State.B;
        badgeState$State21.B = Integer.valueOf(num16 == null ? 0 : num16.intValue());
        BadgeState$State badgeState$State22 = this.b;
        Integer num17 = badgeState$State.C;
        badgeState$State22.C = Integer.valueOf(num17 == null ? 0 : num17.intValue());
        BadgeState$State badgeState$State23 = this.b;
        Boolean bool2 = badgeState$State.E;
        badgeState$State23.E = Boolean.valueOf(bool2 == null ? typedArrayD.getBoolean(R$styleable.Badge_autoAdjustToWithinGrandparentBounds, false) : bool2.booleanValue());
        typedArrayD.recycle();
        Locale locale = badgeState$State.o;
        if (locale == null) {
            this.b.o = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            this.b.o = locale;
        }
        this.a = badgeState$State;
    }
}
