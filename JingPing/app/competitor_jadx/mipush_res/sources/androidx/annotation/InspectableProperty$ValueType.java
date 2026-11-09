package androidx.annotation;

import defpackage.gu;
import defpackage.hu;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class InspectableProperty$ValueType {
    public static final InspectableProperty$ValueType COLOR;
    public static final /* synthetic */ InspectableProperty$ValueType[] E;
    public static final /* synthetic */ hu F;
    public static final InspectableProperty$ValueType GRAVITY;
    public static final InspectableProperty$ValueType INFERRED;
    public static final InspectableProperty$ValueType INT_ENUM;
    public static final InspectableProperty$ValueType INT_FLAG;
    public static final InspectableProperty$ValueType NONE;
    public static final InspectableProperty$ValueType RESOURCE_ID;

    static {
        InspectableProperty$ValueType inspectableProperty$ValueType = new InspectableProperty$ValueType("NONE", 0);
        NONE = inspectableProperty$ValueType;
        InspectableProperty$ValueType inspectableProperty$ValueType2 = new InspectableProperty$ValueType("INFERRED", 1);
        INFERRED = inspectableProperty$ValueType2;
        InspectableProperty$ValueType inspectableProperty$ValueType3 = new InspectableProperty$ValueType("INT_ENUM", 2);
        INT_ENUM = inspectableProperty$ValueType3;
        InspectableProperty$ValueType inspectableProperty$ValueType4 = new InspectableProperty$ValueType("INT_FLAG", 3);
        INT_FLAG = inspectableProperty$ValueType4;
        InspectableProperty$ValueType inspectableProperty$ValueType5 = new InspectableProperty$ValueType("COLOR", 4);
        COLOR = inspectableProperty$ValueType5;
        InspectableProperty$ValueType inspectableProperty$ValueType6 = new InspectableProperty$ValueType("GRAVITY", 5);
        GRAVITY = inspectableProperty$ValueType6;
        InspectableProperty$ValueType inspectableProperty$ValueType7 = new InspectableProperty$ValueType("RESOURCE_ID", 6);
        RESOURCE_ID = inspectableProperty$ValueType7;
        InspectableProperty$ValueType[] inspectableProperty$ValueTypeArr = {inspectableProperty$ValueType, inspectableProperty$ValueType2, inspectableProperty$ValueType3, inspectableProperty$ValueType4, inspectableProperty$ValueType5, inspectableProperty$ValueType6, inspectableProperty$ValueType7};
        E = inspectableProperty$ValueTypeArr;
        F = new hu(inspectableProperty$ValueTypeArr);
    }

    public static gu getEntries() {
        return F;
    }

    public static InspectableProperty$ValueType valueOf(String str) {
        return (InspectableProperty$ValueType) Enum.valueOf(InspectableProperty$ValueType.class, str);
    }

    public static InspectableProperty$ValueType[] values() {
        return (InspectableProperty$ValueType[]) E.clone();
    }
}
