package com.google.gson;

import com.google.gson.FieldNamingPolicy;
import java.lang.reflect.Field;
import java.util.Locale;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: loaded from: classes.dex */
public abstract class FieldNamingPolicy {
    public static final FieldNamingPolicy IDENTITY;
    public static final FieldNamingPolicy LOWER_CASE_WITH_DASHES;
    public static final FieldNamingPolicy LOWER_CASE_WITH_DOTS;
    public static final FieldNamingPolicy LOWER_CASE_WITH_UNDERSCORES;
    public static final FieldNamingPolicy UPPER_CAMEL_CASE;
    public static final FieldNamingPolicy UPPER_CAMEL_CASE_WITH_SPACES;
    public static final /* synthetic */ FieldNamingPolicy[] b;

    static {
        FieldNamingPolicy fieldNamingPolicy = new FieldNamingPolicy() { // from class: rp
            @Override // com.google.gson.FieldNamingPolicy
            public final String translateName(Field field) {
                return field.getName();
            }
        };
        IDENTITY = fieldNamingPolicy;
        FieldNamingPolicy fieldNamingPolicy2 = new FieldNamingPolicy() { // from class: sp
            @Override // com.google.gson.FieldNamingPolicy
            public final String translateName(Field field) {
                return FieldNamingPolicy.b(field.getName());
            }
        };
        UPPER_CAMEL_CASE = fieldNamingPolicy2;
        FieldNamingPolicy fieldNamingPolicy3 = new FieldNamingPolicy() { // from class: tp
            @Override // com.google.gson.FieldNamingPolicy
            public final String translateName(Field field) {
                return FieldNamingPolicy.b(FieldNamingPolicy.a(field.getName(), " "));
            }
        };
        UPPER_CAMEL_CASE_WITH_SPACES = fieldNamingPolicy3;
        FieldNamingPolicy fieldNamingPolicy4 = new FieldNamingPolicy() { // from class: up
            @Override // com.google.gson.FieldNamingPolicy
            public final String translateName(Field field) {
                return FieldNamingPolicy.a(field.getName(), "_").toLowerCase(Locale.ENGLISH);
            }
        };
        LOWER_CASE_WITH_UNDERSCORES = fieldNamingPolicy4;
        FieldNamingPolicy fieldNamingPolicy5 = new FieldNamingPolicy() { // from class: vp
            @Override // com.google.gson.FieldNamingPolicy
            public final String translateName(Field field) {
                return FieldNamingPolicy.a(field.getName(), "-").toLowerCase(Locale.ENGLISH);
            }
        };
        LOWER_CASE_WITH_DASHES = fieldNamingPolicy5;
        FieldNamingPolicy fieldNamingPolicy6 = new FieldNamingPolicy() { // from class: wp
            @Override // com.google.gson.FieldNamingPolicy
            public final String translateName(Field field) {
                return FieldNamingPolicy.a(field.getName(), ".").toLowerCase(Locale.ENGLISH);
            }
        };
        LOWER_CASE_WITH_DOTS = fieldNamingPolicy6;
        b = new FieldNamingPolicy[]{fieldNamingPolicy, fieldNamingPolicy2, fieldNamingPolicy3, fieldNamingPolicy4, fieldNamingPolicy5, fieldNamingPolicy6};
    }

    public static String a(String str, String str2) {
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (Character.isUpperCase(cCharAt) && sb.length() != 0) {
                sb.append(str2);
            }
            sb.append(cCharAt);
        }
        return sb.toString();
    }

    public static String b(String str) {
        String strValueOf;
        StringBuilder sb = new StringBuilder();
        int i = 0;
        char cCharAt = str.charAt(0);
        int length = str.length();
        while (i < length - 1 && !Character.isLetter(cCharAt)) {
            sb.append(cCharAt);
            i++;
            cCharAt = str.charAt(i);
        }
        if (Character.isUpperCase(cCharAt)) {
            return str;
        }
        char upperCase = Character.toUpperCase(cCharAt);
        int i2 = i + 1;
        if (i2 < str.length()) {
            strValueOf = upperCase + str.substring(i2);
        } else {
            strValueOf = String.valueOf(upperCase);
        }
        sb.append(strValueOf);
        return sb.toString();
    }

    public static FieldNamingPolicy valueOf(String str) {
        return (FieldNamingPolicy) Enum.valueOf(FieldNamingPolicy.class, str);
    }

    public static FieldNamingPolicy[] values() {
        return (FieldNamingPolicy[]) b.clone();
    }

    public abstract /* synthetic */ String translateName(Field field);
}
