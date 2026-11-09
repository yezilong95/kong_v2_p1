package io.nats.client.support;

import defpackage.b60;
import defpackage.fb;
import defpackage.no;
import defpackage.zn;
import io.nats.client.impl.Headers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.LongConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public abstract class JsonUtils {
    private static final String AFTER_FIELD_RE = "\"\\s*:\\s*";
    private static final String BEFORE_FIELD_RE = "\"";
    private static final String BOOLEAN_RE = "(true|false)";
    public static final String CLOSE = "}";
    private static final String COMMA = ",";
    public static final String EMPTY_JSON = "{}";
    private static final String INDENT = "                                        ";
    private static final int INDENT_WIDTH = 4;
    private static final String INTEGER_RE = "(-?\\d+)";
    private static final String NUMBER_ARRAY_RE = "\\[\\s*(.+?)\\s*\\]";
    public static final String OPENQ = "{\"";
    private static final String Q = "\"";
    private static final String QCOLON = "\":";
    private static final String QCOLONQ = "\":\"";
    private static final String QCOMMA = "\",";
    private static final String STRING_ARRAY_RE = "\\[\\s*(\".+?\")\\s*\\]";
    private static final String STRING_RE = "\"(.+?)\"";

    @Deprecated
    public enum FieldType {
        jsonString(JsonUtils.STRING_RE),
        jsonBoolean(JsonUtils.BOOLEAN_RE),
        jsonInteger(JsonUtils.INTEGER_RE),
        jsonNumber(JsonUtils.INTEGER_RE),
        jsonStringArray(JsonUtils.STRING_ARRAY_RE);

        final String re;

        FieldType(String str) {
            this.re = str;
        }
    }

    public interface ListAdder<T> {
        void append(StringBuilder sb, T t);
    }

    private JsonUtils() {
    }

    private static void _addEmptyList(StringBuilder sb, String str) {
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append("\":[],");
    }

    public static <T> void _addList(StringBuilder sb, String str, List<T> list, ListAdder<T> listAdder) {
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append("\":[");
        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                sb.append(COMMA);
            }
            listAdder.append(sb, list.get(i));
        }
        sb.append("],");
    }

    private static void _addStrings(StringBuilder sb, String str, List<String> list) {
        _addList(sb, str, list, new fb(3, sb));
    }

    public static void addDurations(StringBuilder sb, String str, List<Duration> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        _addList(sb, str, list, new b60());
    }

    public static void addEnumWhenNot(StringBuilder sb, String str, Enum r2, Enum r3) {
        if (r2 == null || r2 == r3) {
            return;
        }
        addField(sb, str, r2.toString());
    }

    public static void addField(StringBuilder sb, String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLONQ);
        Encoding.jsonEncode(sb, str2);
        sb.append(QCOMMA);
    }

    public static void addFieldAsNanos(StringBuilder sb, String str, Duration duration) {
        if (duration == null || duration.isZero() || duration.isNegative()) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(duration.toNanos());
        sb.append(COMMA);
    }

    public static void addFieldEvenEmpty(StringBuilder sb, String str, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLONQ);
        Encoding.jsonEncode(sb, str2);
        sb.append(QCOMMA);
    }

    public static void addFieldWhenGreaterThan(StringBuilder sb, String str, Long l, long j) {
        if (l == null || l.longValue() <= j) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(l);
        sb.append(COMMA);
    }

    public static void addFieldWhenGtZero(StringBuilder sb, String str, Integer num) {
        if (num == null || num.intValue() <= 0) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(num);
        sb.append(COMMA);
    }

    public static void addFieldWhenGteMinusOne(StringBuilder sb, String str, Long l) {
        if (l == null || l.longValue() < -1) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(l);
        sb.append(COMMA);
    }

    public static void addFldWhenTrue(StringBuilder sb, String str, Boolean bool) {
        if (bool == null || !bool.booleanValue()) {
            return;
        }
        addField(sb, str, Boolean.TRUE);
    }

    public static void addJsons(StringBuilder sb, String str, List<? extends JsonSerializable> list) {
        addJsons(sb, str, list, false);
    }

    public static void addRawJson(StringBuilder sb, String str, String str2) {
        if (str2 == null || str2.length() <= 0) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(str2);
        sb.append(COMMA);
    }

    public static void addStrings(StringBuilder sb, String str, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        _addStrings(sb, str, Arrays.asList(strArr));
    }

    public static StringBuilder beginArray() {
        return new StringBuilder("[");
    }

    public static StringBuilder beginFormattedJson() {
        return new StringBuilder("{\n    ");
    }

    public static StringBuilder beginJson() {
        return new StringBuilder("{");
    }

    public static StringBuilder beginJsonPrefixed(String str) {
        if (str == null) {
            return beginJson();
        }
        StringBuilder sb = new StringBuilder(str);
        sb.append('{');
        return sb;
    }

    @Deprecated
    public static Pattern boolean_pattern(String str) {
        return buildPattern(str, BOOLEAN_RE);
    }

    @Deprecated
    public static Pattern buildPattern(String str, FieldType fieldType) {
        return buildPattern(str, fieldType.re);
    }

    public static StringBuilder endArray(StringBuilder sb) {
        int length = sb.length() - 1;
        if (sb.charAt(length) == ',') {
            sb.setCharAt(length, ']');
            return sb;
        }
        sb.append("]");
        return sb;
    }

    public static String endFormattedJson(StringBuilder sb) {
        sb.setLength(sb.length() - 1);
        sb.append("\n}");
        return sb.toString().replaceAll(COMMA, ",\n    ");
    }

    public static StringBuilder endJson(StringBuilder sb) {
        int length = sb.length() - 1;
        if (sb.charAt(length) == ',') {
            sb.setCharAt(length, '}');
            return sb;
        }
        sb.append(CLOSE);
        return sb;
    }

    private static int[] getBracketIndexes(String str, String str2, char c, char c2, int i) {
        int iIndexOf;
        int[] iArr = {-1, -1};
        int iIndexOf2 = str2.indexOf("\"" + str + "\"", i);
        if (iIndexOf2 == -1) {
            return null;
        }
        if (i != -1) {
            int iIndexOf3 = str2.indexOf(58, iIndexOf2) + 1;
            iIndexOf = str2.indexOf(c, iIndexOf3);
            for (int i2 = iIndexOf3; i2 < iIndexOf; i2++) {
                if (!Character.isWhitespace(str2.charAt(i2))) {
                    return getBracketIndexes(str, str2, c, c2, iIndexOf3);
                }
            }
        } else {
            iIndexOf = str2.indexOf(c, iIndexOf2);
        }
        int i3 = 1;
        for (int i4 = iIndexOf + 1; i4 < str2.length(); i4++) {
            char cCharAt = str2.charAt(i4);
            if (cCharAt == c) {
                i3++;
            } else if (cCharAt == c2 && i3 - 1 == 0) {
                iArr[0] = iIndexOf;
                iArr[1] = i4;
                return iArr;
            }
        }
        return null;
    }

    @Deprecated
    public static List<Duration> getDurationList(String str, String str2) {
        List<Long> longList = getLongList(str, str2);
        ArrayList arrayList = new ArrayList(longList.size());
        Iterator<Long> it = longList.iterator();
        while (it.hasNext()) {
            arrayList.add(Duration.ofNanos(it.next().longValue()));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x005f  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0070  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00ad  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getFormatted(java.lang.Object r12) {
        /*
            java.lang.String r12 = r12.toString()
            java.lang.String r0 = java.lang.System.lineSeparator()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 0
            java.lang.String r3 = ""
            r4 = r2
            r5 = r4
            r6 = r5
            r8 = r6
            r7 = r3
        L15:
            int r9 = r12.length()
            if (r4 >= r9) goto Lb4
            char r9 = r12.charAt(r4)
            r10 = 34
            r11 = 1
            if (r9 != r10) goto L34
            if (r6 == 0) goto L2d
            r1.append(r0)
            r1.append(r7)
            r6 = r2
        L2d:
            r1.append(r9)
            r5 = r5 ^ 1
            goto Lb1
        L34:
            if (r5 != 0) goto L93
            r10 = 44
            if (r9 == r10) goto L84
            r10 = 58
            if (r9 == r10) goto L7b
            r10 = 91
            if (r9 == r10) goto L70
            r10 = 93
            if (r9 == r10) goto L5f
            r10 = 123(0x7b, float:1.72E-43)
            if (r9 == r10) goto L70
            r10 = 125(0x7d, float:1.75E-43)
            if (r9 == r10) goto L5f
            boolean r10 = java.lang.Character.isWhitespace(r9)
            if (r10 == 0) goto L55
            goto Lb1
        L55:
            if (r6 == 0) goto L93
            r1.append(r0)
            r1.append(r7)
            r6 = r2
            goto L93
        L5f:
            int r8 = r8 + (-1)
            java.lang.String r7 = indent(r8)
            r1.append(r0)
            r1.append(r7)
            r1.append(r9)
            r6 = r2
            goto Lb1
        L70:
            r1.append(r9)
            int r8 = r8 + 1
            java.lang.String r7 = indent(r8)
            r6 = r11
            goto Lb1
        L7b:
            r1.append(r9)
            java.lang.String r9 = " "
            r1.append(r9)
            goto Lb1
        L84:
            r1.append(r9)
            r1.append(r0)
            if (r8 <= 0) goto L8e
            r9 = r7
            goto L8f
        L8e:
            r9 = r3
        L8f:
            r1.append(r9)
            goto Lb1
        L93:
            r1.append(r9)
            r10 = 92
            if (r9 != r10) goto Lad
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>(r3)
            int r4 = r4 + 1
            char r10 = r12.charAt(r4)
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            goto Lae
        Lad:
            r9 = r3
        Lae:
            r1.append(r9)
        Lb1:
            int r4 = r4 + r11
            goto L15
        Lb4:
            java.lang.String r12 = r1.toString()
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.support.JsonUtils.getFormatted(java.lang.Object):java.lang.String");
    }

    @Deprecated
    public static String getJsonObject(String str, String str2) {
        return getJsonObject(str, str2, EMPTY_JSON);
    }

    @Deprecated
    public static List<Long> getLongList(String str, String str2) {
        String strReplaceAll = str2.replaceAll("\r", "").replaceAll("\n", "");
        ArrayList arrayList = new ArrayList();
        Matcher matcher = number_array_pattern(str).matcher(strReplaceAll);
        if (matcher.find()) {
            for (String str3 : matcher.group(1).split(COMMA)) {
                arrayList.add(safeParseLong(str3.trim()));
            }
        }
        return arrayList;
    }

    @Deprecated
    public static Map<String, List<String>> getMapOfLists(String str) {
        HashMap map = new HashMap();
        int iIndexOf = str.indexOf(34);
        while (iIndexOf != -1) {
            int i = iIndexOf + 1;
            String strTrim = str.substring(i, str.indexOf(34, i)).trim();
            int[] bracketIndexes = getBracketIndexes(strTrim, str, '[', ']', iIndexOf);
            if (bracketIndexes != null) {
                map.put(strTrim, toList(str.substring(bracketIndexes[0] + 1, bracketIndexes[1])));
                iIndexOf = str.indexOf(34, bracketIndexes[1]);
            } else {
                iIndexOf = -1;
            }
        }
        return map;
    }

    @Deprecated
    public static Map<String, Long> getMapOfLongs(String str) {
        HashMap map = new HashMap();
        int iIndexOf = str.indexOf(34);
        while (iIndexOf != -1) {
            int i = iIndexOf + 1;
            int iIndexOf2 = str.indexOf(34, i);
            int iIndexOf3 = str.indexOf(58, iIndexOf2);
            int iIndexOf4 = str.indexOf(44, iIndexOf2);
            if (iIndexOf4 == -1) {
                iIndexOf4 = str.indexOf(125, iIndexOf2);
            }
            map.put(str.substring(i, iIndexOf2).trim(), Long.valueOf(safeParseLong(str.substring(iIndexOf3 + 1, iIndexOf4).trim(), 0L)));
            iIndexOf = str.indexOf(34, iIndexOf4);
        }
        return map;
    }

    @Deprecated
    public static Map<String, String> getMapOfObjects(String str) {
        HashMap map = new HashMap();
        int iIndexOf = str.indexOf(34);
        while (iIndexOf != -1) {
            int i = iIndexOf + 1;
            String strTrim = str.substring(i, str.indexOf(34, i)).trim();
            int[] bracketIndexes = getBracketIndexes(strTrim, str, '{', '}', iIndexOf);
            if (bracketIndexes != null) {
                map.put(strTrim, str.substring(bracketIndexes[0], bracketIndexes[1] + 1));
                iIndexOf = str.indexOf(34, bracketIndexes[1]);
            } else {
                iIndexOf = -1;
            }
        }
        return map;
    }

    @Deprecated
    public static List<String> getObjectList(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        int[] bracketIndexes = getBracketIndexes(str, str2, '[', ']', -1);
        if (bracketIndexes != null) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            for (int i2 = bracketIndexes[0] + 1; i2 < bracketIndexes[1]; i2++) {
                char cCharAt = str2.charAt(i2);
                if (cCharAt == '{') {
                    sb.append(cCharAt);
                    i++;
                } else if (cCharAt == '}') {
                    sb.append(cCharAt);
                    i--;
                    if (i == 0) {
                        arrayList.add(sb.toString());
                        sb.setLength(0);
                    }
                } else if (i > 0) {
                    sb.append(cCharAt);
                }
            }
        }
        return arrayList;
    }

    @Deprecated
    public static List<String> getStringList(String str, String str2) {
        Matcher matcher = string_array_pattern(str).matcher(str2.replaceAll("\r", "").replaceAll("\n", ""));
        return matcher.find() ? toList(matcher.group(1)) : new ArrayList();
    }

    private static String indent(int i) {
        return i <= 0 ? "" : INDENT.substring(0, i * 4);
    }

    @Deprecated
    public static Pattern integer_pattern(String str) {
        return buildPattern(str, INTEGER_RE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$_addStrings$0(StringBuilder sb, StringBuilder sb2, String str) {
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append("\"");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$addDurations$2(StringBuilder sb, Duration duration) {
        sb.append(duration.toNanos());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$addJsons$1(StringBuilder sb, JsonSerializable jsonSerializable) {
        sb.append(jsonSerializable.toJson());
    }

    public static <T> boolean listEquals(List<T> list, List<T> list2) {
        if (list == null) {
            return list2 == null;
        }
        if (list2 == null) {
            return false;
        }
        return list.equals(list2);
    }

    public static boolean mapEquals(Map<String, String> map, Map<String, String> map2) {
        if (map == null) {
            return map2 == null;
        }
        if (map2 == null || map.size() != map2.size()) {
            return false;
        }
        for (String str : map.keySet()) {
            if (!Objects.equals(map.get(str), map2.get(str))) {
                return false;
            }
        }
        return true;
    }

    @Deprecated
    public static String normalize(String str) {
        return Character.toString(str.charAt(0)).toUpperCase() + str.substring(1).toLowerCase();
    }

    @Deprecated
    public static Pattern number_array_pattern(String str) {
        return buildPattern(str, NUMBER_ARRAY_RE);
    }

    @Deprecated
    public static Pattern number_pattern(String str) {
        return integer_pattern(str);
    }

    @Deprecated
    public static String objectString(String str, Object obj) {
        return obj == null ? zn.m(str, "=null") : obj.toString();
    }

    public static void printFormatted(Object obj) {
        System.out.println(getFormatted(obj));
    }

    @Deprecated
    public static byte[] readBase64(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        String strGroup = matcher.find() ? matcher.group(1) : null;
        if (strGroup == null) {
            return null;
        }
        return Base64.getDecoder().decode(strGroup);
    }

    @Deprecated
    public static boolean readBoolean(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() && Boolean.parseBoolean(matcher.group(1));
    }

    @Deprecated
    public static byte[] readBytes(String str, Pattern pattern) {
        String string = readString(str, pattern, null);
        if (string == null) {
            return null;
        }
        return string.getBytes(StandardCharsets.UTF_8);
    }

    @Deprecated
    public static ZonedDateTime readDate(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return DateTimeUtils.parseDateTime(matcher.group(1));
        }
        return null;
    }

    @Deprecated
    public static int readInt(String str, Pattern pattern, int i) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Integer.parseInt(matcher.group(1)) : i;
    }

    @Deprecated
    public static Integer readInteger(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Integer.valueOf(Integer.parseInt(matcher.group(1)));
        }
        return null;
    }

    @Deprecated
    public static Long readLong(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return safeParseLong(matcher.group(1));
        }
        return null;
    }

    @Deprecated
    public static Duration readNanos(String str, Pattern pattern) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return Duration.ofNanos(Long.parseLong(matcher.group(1)));
        }
        return null;
    }

    @Deprecated
    public static String readString(String str, Pattern pattern) {
        return readString(str, pattern, null);
    }

    @Deprecated
    public static String readStringMayHaveQuotes(String str, String str2, String str3) {
        String strN = zn.n("\"", str2, "\"");
        int iIndexOf = str.indexOf(strN);
        if (iIndexOf == -1) {
            return str3;
        }
        int iIndexOf2 = str.indexOf(34, strN.length() + iIndexOf);
        StringBuilder sb = new StringBuilder();
        while (true) {
            int i = iIndexOf2 + 1;
            char cCharAt = str.charAt(i);
            if (cCharAt == '\\') {
                iIndexOf2 += 2;
                char cCharAt2 = str.charAt(iIndexOf2);
                if (cCharAt2 == '\"') {
                    sb.append('\"');
                } else {
                    sb.append(cCharAt);
                    sb.append(cCharAt2);
                }
            } else {
                if (cCharAt == '\"') {
                    return Encoding.jsonDecode(sb.toString());
                }
                sb.append(cCharAt);
                iIndexOf2 = i;
            }
        }
    }

    @Deprecated
    public static String removeObject(String str, String str2) {
        int[] bracketIndexes = getBracketIndexes(str2, str, '{', '}', 0);
        if (bracketIndexes == null) {
            return str;
        }
        return str.substring(0, bracketIndexes[0]) + "\"rmvd" + str2.hashCode() + "\":\"\"" + str.substring(bracketIndexes[1] + 1);
    }

    public static Long safeParseLong(String str) {
        try {
            try {
                return Long.valueOf(Long.parseLong(str));
            } catch (Exception unused) {
                return Long.valueOf(zn.f(str));
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    @Deprecated
    public static byte[] simpleMessageBody(String str, Number number) {
        return (OPENQ + str + QCOLON + number + CLOSE).getBytes();
    }

    @Deprecated
    public static Pattern string_array_pattern(String str) {
        return buildPattern(str, STRING_ARRAY_RE);
    }

    @Deprecated
    public static Pattern string_pattern(String str) {
        return buildPattern(str, STRING_RE);
    }

    public static String toKey(Class<?> cls) {
        return "\"" + cls.getSimpleName() + QCOLON;
    }

    private static List<String> toList(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : str.split(COMMA)) {
            String strReplace = str2.trim().replace("\"", "");
            if (strReplace.length() > 0) {
                arrayList.add(Encoding.jsonDecode(strReplace));
            }
        }
        return arrayList;
    }

    public static void addJsons(StringBuilder sb, String str, List<? extends JsonSerializable> list, boolean z) {
        if (list != null && !list.isEmpty()) {
            _addList(sb, str, list, new no(4));
        } else if (z) {
            _addEmptyList(sb, str);
        }
    }

    @Deprecated
    public static Pattern buildPattern(String str, String str2) {
        return Pattern.compile("\"" + str + AFTER_FIELD_RE + str2, 2);
    }

    @Deprecated
    public static String getJsonObject(String str, String str2, String str3) {
        int[] bracketIndexes = getBracketIndexes(str, str2, '{', '}', 0);
        return bracketIndexes == null ? str3 : str2.substring(bracketIndexes[0], bracketIndexes[1] + 1);
    }

    @Deprecated
    public static String readString(String str, Pattern pattern, String str2) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Encoding.jsonDecode(matcher.group(1)) : str2;
    }

    @Deprecated
    public static byte[] simpleMessageBody(String str, String str2) {
        return (OPENQ + str + QCOLONQ + str2 + "\"}").getBytes();
    }

    public static void addStrings(StringBuilder sb, String str, List<String> list) {
        if (list == null || list.size() <= 0) {
            return;
        }
        _addStrings(sb, str, list);
    }

    @Deprecated
    public static Boolean readBoolean(String str, Pattern pattern, Boolean bool) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Boolean.valueOf(Boolean.parseBoolean(matcher.group(1))) : bool;
    }

    @Deprecated
    public static void readInt(String str, Pattern pattern, IntConsumer intConsumer) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            intConsumer.accept(Integer.parseInt(matcher.group(1)));
        }
    }

    @Deprecated
    public static long readLong(String str, Pattern pattern, long j) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? safeParseLong(matcher.group(1), j) : j;
    }

    @Deprecated
    public static Duration readNanos(String str, Pattern pattern, Duration duration) {
        Matcher matcher = pattern.matcher(str);
        return matcher.find() ? Duration.ofNanos(Long.parseLong(matcher.group(1))) : duration;
    }

    public static long safeParseLong(String str, long j) {
        Long lSafeParseLong = safeParseLong(str);
        return lSafeParseLong == null ? j : lSafeParseLong.longValue();
    }

    public static void addFieldWhenGtZero(StringBuilder sb, String str, Long l) {
        if (l == null || l.longValue() <= 0) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(l);
        sb.append(COMMA);
    }

    @Deprecated
    public static void readLong(String str, Pattern pattern, LongConsumer longConsumer) {
        Long lSafeParseLong;
        Matcher matcher = pattern.matcher(str);
        if (!matcher.find() || (lSafeParseLong = safeParseLong(matcher.group(1))) == null) {
            return;
        }
        longConsumer.accept(lSafeParseLong.longValue());
    }

    @Deprecated
    public static void readNanos(String str, Pattern pattern, Consumer<Duration> consumer) {
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            consumer.accept(Duration.ofNanos(Long.parseLong(matcher.group(1))));
        }
    }

    public static void addField(StringBuilder sb, String str, Boolean bool) {
        if (bool != null) {
            sb.append("\"");
            Encoding.jsonEncode(sb, str);
            sb.append(QCOLON);
            sb.append(bool.booleanValue() ? "true" : "false");
            sb.append(COMMA);
        }
    }

    public static void addField(StringBuilder sb, String str, Integer num) {
        if (num == null || num.intValue() < 0) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(num);
        sb.append(COMMA);
    }

    public static void addField(StringBuilder sb, String str, Long l) {
        if (l == null || l.longValue() < 0) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLON);
        sb.append(l);
        sb.append(COMMA);
    }

    public static void addField(StringBuilder sb, String str, JsonSerializable jsonSerializable) {
        if (jsonSerializable != null) {
            sb.append("\"");
            Encoding.jsonEncode(sb, str);
            sb.append(QCOLON);
            sb.append(jsonSerializable.toJson());
            sb.append(COMMA);
        }
    }

    public static void addField(StringBuilder sb, String str, Map<String, String> map) {
        if (map == null || map.size() <= 0) {
            return;
        }
        addField(sb, str, JsonValueUtils.instance(map));
    }

    public static void addField(StringBuilder sb, String str, ZonedDateTime zonedDateTime) {
        if (zonedDateTime == null || DateTimeUtils.DEFAULT_TIME.equals(zonedDateTime)) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append(QCOLONQ);
        sb.append(DateTimeUtils.toRfc3339(zonedDateTime));
        sb.append(QCOMMA);
    }

    public static void addField(StringBuilder sb, String str, Headers headers) {
        if (headers == null || headers.size() <= 0) {
            return;
        }
        sb.append("\"");
        Encoding.jsonEncode(sb, str);
        sb.append("\":{");
        for (Map.Entry<String, List<String>> entry : headers.entrySet()) {
            addStrings(sb, entry.getKey(), entry.getValue());
        }
        endJson(sb);
        sb.append(COMMA);
    }
}
