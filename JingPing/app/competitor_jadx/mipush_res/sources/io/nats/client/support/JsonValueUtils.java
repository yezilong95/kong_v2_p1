package io.nats.client.support;

import defpackage.c60;
import defpackage.fb;
import defpackage.no;
import defpackage.zt;
import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/* JADX INFO: loaded from: classes.dex */
public abstract class JsonValueUtils {

    public static class ArrayBuilder implements JsonSerializable {
        public JsonValue jv = new JsonValue(new ArrayList());

        public ArrayBuilder add(Object obj) {
            if (obj != null && JsonValueUtils.toJsonValue(obj).type != JsonValue.Type.NULL) {
                this.jv.array.add(JsonValueUtils.toJsonValue(obj));
            }
            return this;
        }

        @Deprecated
        public JsonValue getJsonValue() {
            return this.jv;
        }

        @Override // io.nats.client.support.JsonSerializable
        public String toJson() {
            return this.jv.toJson();
        }

        @Override // io.nats.client.support.JsonSerializable
        public JsonValue toJsonValue() {
            return this.jv;
        }
    }

    public interface JsonValueSupplier<T> {
        T get(JsonValue jsonValue);
    }

    private JsonValueUtils() {
    }

    public static ArrayBuilder arrayBuilder() {
        return new ArrayBuilder();
    }

    public static Integer getInteger(JsonValue jsonValue) {
        Integer num = jsonValue.i;
        if (num != null) {
            return num;
        }
        Long l = jsonValue.l;
        if (l == null || l.longValue() > 2147483647L || jsonValue.l.longValue() < -2147483648L) {
            return null;
        }
        return Integer.valueOf(jsonValue.l.intValue());
    }

    public static Long getLong(JsonValue jsonValue) {
        Long l = jsonValue.l;
        if (l != null) {
            return l;
        }
        if (jsonValue.i != null) {
            return Long.valueOf(r2.intValue());
        }
        return null;
    }

    public static JsonValue instance(Duration duration) {
        return new JsonValue(duration.toNanos());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$null$14(JsonValue jsonValue) {
        String str = jsonValue.string;
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.isEmpty()) {
            return null;
        }
        return strTrim;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Duration lambda$null$19(JsonValue jsonValue) {
        Long l = getLong(jsonValue);
        if (l == null) {
            return null;
        }
        return Duration.ofNanos(l.longValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$readArray$2(JsonValue jsonValue) {
        return jsonValue == null ? JsonValue.EMPTY_ARRAY.array : jsonValue.array;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Boolean lambda$readBoolean$11(Boolean bool, JsonValue jsonValue) {
        Boolean bool2;
        return (jsonValue == null || (bool2 = jsonValue.bool) == null) ? bool : bool2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ZonedDateTime lambda$readDate$6(JsonValue jsonValue) {
        String str;
        if (jsonValue == null || (str = jsonValue.string) == null) {
            return null;
        }
        return DateTimeUtils.parseDateTimeThrowParseError(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$readInteger$7(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return getInteger(jsonValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Integer lambda$readInteger$8(int i, JsonValue jsonValue) {
        Integer integer;
        return (jsonValue == null || (integer = getInteger(jsonValue)) == null) ? Integer.valueOf(i) : integer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Long lambda$readLong$10(long j, JsonValue jsonValue) {
        Long l;
        return (jsonValue == null || (l = getLong(jsonValue)) == null) ? Long.valueOf(j) : l;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Long lambda$readLong$9(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return getLong(jsonValue);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$readLongList$18(JsonValue jsonValue) {
        return listOf(jsonValue, new zt(5));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$readNanosList$20(JsonValue jsonValue) {
        return listOf(jsonValue, new zt(3));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ JsonValue lambda$readObject$1(JsonValue jsonValue) {
        return jsonValue == null ? JsonValue.EMPTY_MAP : jsonValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$readOptionalStringList$17(JsonValue jsonValue) {
        return optionalListOf(jsonValue, new zt(4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$readString$3(JsonValue jsonValue) {
        if (jsonValue == null) {
            return null;
        }
        return jsonValue.string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$readString$5(String str, JsonValue jsonValue) {
        return jsonValue == null ? str : jsonValue.string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ String lambda$readStringEmptyAsNull$4(JsonValue jsonValue) {
        if (jsonValue == null || jsonValue.string.isEmpty()) {
            return null;
        }
        return jsonValue.string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$readStringList$13(JsonValue jsonValue) {
        return listOf(jsonValue, new zt(7));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$readStringListIgnoreEmpty$15(JsonValue jsonValue) {
        return listOf(jsonValue, new zt(6));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ JsonValue lambda$readValue$0(JsonValue jsonValue) {
        return jsonValue;
    }

    public static <T> List<T> listOf(JsonValue jsonValue, Function<JsonValue, T> function) {
        List<JsonValue> list;
        ArrayList arrayList = new ArrayList();
        if (jsonValue != null && (list = jsonValue.array) != null) {
            Iterator<JsonValue> it = list.iterator();
            while (it.hasNext()) {
                T tApply = function.apply(it.next());
                if (tApply != null) {
                    arrayList.add(tApply);
                }
            }
        }
        return arrayList;
    }

    public static MapBuilder mapBuilder() {
        return new MapBuilder();
    }

    public static <T> List<T> optionalListOf(JsonValue jsonValue, Function<JsonValue, T> function) {
        List<T> listListOf = listOf(jsonValue, function);
        if (listListOf.isEmpty()) {
            return null;
        }
        return listListOf;
    }

    public static <T> T read(JsonValue jsonValue, String str, JsonValueSupplier<T> jsonValueSupplier) {
        Map<String, JsonValue> map;
        return jsonValueSupplier.get((jsonValue == null || (map = jsonValue.map) == null) ? null : map.get(str));
    }

    public static List<JsonValue> readArray(JsonValue jsonValue, String str) {
        return (List) read(jsonValue, str, new no(15));
    }

    public static byte[] readBase64(JsonValue jsonValue, String str) {
        String string = readString(jsonValue, str);
        if (string == null) {
            return null;
        }
        return Encoding.base64BasicDecode(string);
    }

    public static boolean readBoolean(JsonValue jsonValue, String str) {
        return readBoolean(jsonValue, str, Boolean.FALSE).booleanValue();
    }

    public static byte[] readBytes(JsonValue jsonValue, String str) {
        String string = readString(jsonValue, str);
        if (string == null) {
            return null;
        }
        return string.getBytes(StandardCharsets.UTF_8);
    }

    public static ZonedDateTime readDate(JsonValue jsonValue, String str) {
        return c60.k(read(jsonValue, str, new no(5)));
    }

    public static Integer readInteger(JsonValue jsonValue, String str) {
        return (Integer) read(jsonValue, str, new no(16));
    }

    public static Long readLong(JsonValue jsonValue, String str) {
        return (Long) read(jsonValue, str, new no(12));
    }

    public static List<Long> readLongList(JsonValue jsonValue, String str) {
        return (List) read(jsonValue, str, new no(10));
    }

    public static Duration readNanos(JsonValue jsonValue, String str) {
        Long l = readLong(jsonValue, str);
        if (l == null) {
            return null;
        }
        return Duration.ofNanos(l.longValue());
    }

    public static List<Duration> readNanosList(JsonValue jsonValue, String str) {
        return readNanosList(jsonValue, str, false);
    }

    public static JsonValue readObject(JsonValue jsonValue, String str) {
        return (JsonValue) read(jsonValue, str, new no(17));
    }

    public static List<String> readOptionalStringList(JsonValue jsonValue, String str) {
        return (List) read(jsonValue, str, new no(6));
    }

    public static String readString(JsonValue jsonValue, String str) {
        return (String) read(jsonValue, str, new no(7));
    }

    public static String readStringEmptyAsNull(JsonValue jsonValue, String str) {
        return (String) read(jsonValue, str, new no(14));
    }

    public static List<String> readStringList(JsonValue jsonValue, String str) {
        return (List) read(jsonValue, str, new no(11));
    }

    public static List<String> readStringListIgnoreEmpty(JsonValue jsonValue, String str) {
        return (List) read(jsonValue, str, new no(13));
    }

    public static Map<String, String> readStringStringMap(JsonValue jsonValue, String str) {
        JsonValue object = readObject(jsonValue, str);
        if (object.type != JsonValue.Type.MAP || object.map.size() <= 0) {
            return null;
        }
        HashMap map = new HashMap();
        for (String str2 : object.map.keySet()) {
            String string = readString(object, str2);
            if (string != null) {
                map.put(str2, string);
            }
        }
        if (map.isEmpty()) {
            return null;
        }
        return map;
    }

    public static JsonValue readValue(JsonValue jsonValue, String str) {
        return (JsonValue) read(jsonValue, str, new no(8));
    }

    public static JsonValue toJsonValue(Object obj) {
        if (obj == null) {
            return JsonValue.NULL;
        }
        if (obj instanceof JsonValue) {
            return (JsonValue) obj;
        }
        if (obj instanceof JsonSerializable) {
            return ((JsonSerializable) obj).toJsonValue();
        }
        if (obj instanceof Map) {
            return new JsonValue((Map<String, JsonValue>) obj);
        }
        if (obj instanceof List) {
            return new JsonValue((List<JsonValue>) obj);
        }
        if (obj instanceof Set) {
            return new JsonValue(new ArrayList((Set) obj));
        }
        if (!(obj instanceof String)) {
            return obj instanceof Boolean ? new JsonValue((Boolean) obj) : obj instanceof Integer ? new JsonValue(((Integer) obj).intValue()) : obj instanceof Long ? new JsonValue(((Long) obj).longValue()) : obj instanceof Double ? new JsonValue(((Double) obj).doubleValue()) : obj instanceof Float ? new JsonValue(((Float) obj).floatValue()) : obj instanceof BigDecimal ? new JsonValue((BigDecimal) obj) : obj instanceof BigInteger ? new JsonValue((BigInteger) obj) : new JsonValue(obj.toString());
        }
        String strTrim = ((String) obj).trim();
        return strTrim.length() == 0 ? new JsonValue() : new JsonValue(strTrim);
    }

    public static class MapBuilder implements JsonSerializable {
        public JsonValue jv;

        public MapBuilder() {
            this.jv = new JsonValue(new HashMap());
        }

        @Deprecated
        public JsonValue getJsonValue() {
            return this.jv;
        }

        public MapBuilder put(String str, Object obj) {
            if (obj != null) {
                JsonValue jsonValue = JsonValueUtils.toJsonValue(obj);
                if (jsonValue.type != JsonValue.Type.NULL) {
                    this.jv.map.put(str, jsonValue);
                    this.jv.mapOrder.add(str);
                }
            }
            return this;
        }

        @Override // io.nats.client.support.JsonSerializable
        public String toJson() {
            return this.jv.toJson();
        }

        @Override // io.nats.client.support.JsonSerializable
        public JsonValue toJsonValue() {
            return this.jv;
        }

        public MapBuilder(JsonValue jsonValue) {
            this.jv = jsonValue;
        }

        public MapBuilder put(String str, Map<String, String> map) {
            if (map != null) {
                MapBuilder mapBuilder = new MapBuilder();
                for (String str2 : map.keySet()) {
                    mapBuilder.put(str2, map.get(str2));
                }
                this.jv.map.put(str, mapBuilder.jv);
                this.jv.mapOrder.add(str);
            }
            return this;
        }
    }

    public static long getLong(JsonValue jsonValue, long j) {
        Long l = jsonValue.l;
        if (l != null) {
            return l.longValue();
        }
        return jsonValue.i != null ? r1.intValue() : j;
    }

    public static JsonValue instance(Collection collection) {
        JsonValue jsonValue = new JsonValue(new ArrayList());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            jsonValue.array.add(toJsonValue(it.next()));
        }
        return jsonValue;
    }

    public static Boolean readBoolean(JsonValue jsonValue, String str, Boolean bool) {
        return (Boolean) read(jsonValue, str, new fb(4, bool));
    }

    public static int readInteger(JsonValue jsonValue, String str, final int i) {
        return ((Integer) read(jsonValue, str, new JsonValueSupplier() { // from class: d60
            @Override // io.nats.client.support.JsonValueUtils.JsonValueSupplier
            public final Object get(JsonValue jsonValue2) {
                return JsonValueUtils.lambda$readInteger$8(i, jsonValue2);
            }
        })).intValue();
    }

    public static long readLong(JsonValue jsonValue, String str, final long j) {
        return ((Long) read(jsonValue, str, new JsonValueSupplier() { // from class: e60
            @Override // io.nats.client.support.JsonValueUtils.JsonValueSupplier
            public final Object get(JsonValue jsonValue2) {
                return JsonValueUtils.lambda$readLong$10(j, jsonValue2);
            }
        })).longValue();
    }

    public static List<Duration> readNanosList(JsonValue jsonValue, String str, boolean z) {
        List<Duration> list = (List) read(jsonValue, str, new no(9));
        if (list.isEmpty() && z) {
            return null;
        }
        return list;
    }

    public static String readString(JsonValue jsonValue, String str, String str2) {
        return (String) read(jsonValue, str, new fb(5, str2));
    }

    public static Duration readNanos(JsonValue jsonValue, String str, Duration duration) {
        Long l = readLong(jsonValue, str);
        return l == null ? duration : Duration.ofNanos(l.longValue());
    }

    public static JsonValue instance(Map map) {
        JsonValue jsonValue = new JsonValue(new HashMap());
        for (Object obj : map.keySet()) {
            jsonValue.map.put(obj.toString(), toJsonValue(map.get(obj)));
        }
        return jsonValue;
    }
}
