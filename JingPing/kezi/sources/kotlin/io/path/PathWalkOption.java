package kotlin.io.path;

import defpackage.jo;
import defpackage.ko;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public final class PathWalkOption {
    public static final PathWalkOption BREADTH_FIRST;
    public static final PathWalkOption FOLLOW_LINKS;
    public static final PathWalkOption INCLUDE_DIRECTORIES;
    public static final /* synthetic */ PathWalkOption[] b;
    public static final /* synthetic */ ko c;

    static {
        PathWalkOption pathWalkOption = new PathWalkOption("INCLUDE_DIRECTORIES", 0);
        INCLUDE_DIRECTORIES = pathWalkOption;
        PathWalkOption pathWalkOption2 = new PathWalkOption("BREADTH_FIRST", 1);
        BREADTH_FIRST = pathWalkOption2;
        PathWalkOption pathWalkOption3 = new PathWalkOption("FOLLOW_LINKS", 2);
        FOLLOW_LINKS = pathWalkOption3;
        PathWalkOption[] pathWalkOptionArr = {pathWalkOption, pathWalkOption2, pathWalkOption3};
        b = pathWalkOptionArr;
        c = new ko(pathWalkOptionArr);
    }

    public static jo getEntries() {
        return c;
    }

    public static PathWalkOption valueOf(String str) {
        return (PathWalkOption) Enum.valueOf(PathWalkOption.class, str);
    }

    public static PathWalkOption[] values() {
        return (PathWalkOption[]) b.clone();
    }
}
