package io.nats.client.impl;

import defpackage.z20;
import defpackage.zn;
import defpackage.zt;
import io.nats.client.support.ByteArrayBuilder;
import io.nats.client.support.NatsConstants;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

/* JADX INFO: loaded from: classes.dex */
public class Headers {
    private static final int HVCRLF_BYTES;
    private static final String KEY_CANNOT_BE_EMPTY_OR_NULL = "Header key cannot be null.";
    private static final String KEY_INVALID_CHARACTER = "Header key has invalid character: ";
    private static final int NON_DATA_BYTES;
    private static final String VALUE_INVALID_CHARACTERS = "Header value has invalid character: ";
    private int dataLength;
    private final Map<String, Integer> lengthMap;
    private final boolean readOnly;
    private byte[] serialized;
    private final Map<String, List<String>> valuesMap;

    public class Checker {
        int len;
        List<String> list = new ArrayList();

        public Checker(String str, Collection<String> collection) {
            this.len = 0;
            Headers.this.checkKey(str);
            if (collection.isEmpty()) {
                return;
            }
            for (String str2 : collection) {
                if (str2 != null) {
                    if (str2.isEmpty()) {
                        this.list.add(str2);
                        this.len = str.length() + 3 + this.len;
                    } else {
                        Headers.this.checkValue(str2);
                        this.list.add(str2);
                        this.len = str2.length() + str.length() + 3 + this.len;
                    }
                }
            }
        }

        public boolean hasValues() {
            return !this.list.isEmpty();
        }
    }

    static {
        int length = NatsConstants.HEADER_VERSION_BYTES_PLUS_CRLF.length;
        HVCRLF_BYTES = length;
        NON_DATA_BYTES = length + 2;
    }

    public Headers() {
        this(null, false, null);
    }

    private Headers _add(String str, Collection<String> collection) {
        if (collection != null) {
            Checker checker = new Checker(str, collection);
            if (checker.hasValues()) {
                this.valuesMap.computeIfAbsent(str, new zt(1)).addAll(checker.list);
                this.dataLength += checker.len;
                this.lengthMap.put(str, Integer.valueOf(this.lengthMap.getOrDefault(str, 0).intValue() + checker.len));
                this.serialized = null;
            }
        }
        return this;
    }

    private Headers _put(String str, Collection<String> collection) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException("Key cannot be null or empty.");
        }
        if (collection != null) {
            Checker checker = new Checker(str, collection);
            if (checker.hasValues()) {
                this.dataLength = (this.dataLength - this.lengthMap.getOrDefault(str, 0).intValue()) + checker.len;
                this.valuesMap.put(str, checker.list);
                this.lengthMap.put(str, Integer.valueOf(checker.len));
                this.serialized = null;
            }
        }
        return this;
    }

    private void _remove(String str) {
        if (this.valuesMap.remove(str) != null) {
            this.dataLength -= this.lengthMap.remove(str).intValue();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkKey(String str) {
        if (str == null || str.isEmpty()) {
            throw new IllegalArgumentException(KEY_CANNOT_BE_EMPTY_OR_NULL);
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt < '!' || cCharAt > '~' || cCharAt == ':') {
                throw new IllegalArgumentException("Header key has invalid character: '" + cCharAt + "'");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkValue(String str) {
        str.chars().forEach(new z20());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ List lambda$_add$0(String str) {
        return new ArrayList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$checkValue$1(int i) {
        if (i > 127 || i == 10 || i == 13) {
            throw new IllegalArgumentException(zn.j(i, VALUE_INVALID_CHARACTERS));
        }
    }

    public Headers add(String str, String... strArr) {
        if (this.readOnly) {
            throw new UnsupportedOperationException();
        }
        return (strArr == null || strArr.length == 0) ? this : _add(str, Arrays.asList(strArr));
    }

    @Deprecated
    public ByteArrayBuilder appendSerialized(ByteArrayBuilder byteArrayBuilder) {
        byteArrayBuilder.append(NatsConstants.HEADER_VERSION_BYTES_PLUS_CRLF);
        for (String str : this.valuesMap.keySet()) {
            for (String str2 : this.valuesMap.get(str)) {
                byteArrayBuilder.append(str);
                byteArrayBuilder.append(NatsConstants.COLON_BYTES);
                byteArrayBuilder.append(str2);
                byteArrayBuilder.append(NatsConstants.CRLF_BYTES);
            }
        }
        byteArrayBuilder.append(NatsConstants.CRLF_BYTES);
        return byteArrayBuilder;
    }

    public void clear() {
        if (this.readOnly) {
            throw new UnsupportedOperationException();
        }
        this.valuesMap.clear();
        this.lengthMap.clear();
        this.dataLength = 0;
        this.serialized = null;
    }

    public boolean containsKey(String str) {
        return this.valuesMap.containsKey(str);
    }

    public boolean containsKeyIgnoreCase(String str) {
        Iterator<String> it = this.valuesMap.keySet().iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public Set<Map.Entry<String, List<String>>> entrySet() {
        return Collections.unmodifiableSet(this.valuesMap.entrySet());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.valuesMap, ((Headers) obj).valuesMap);
    }

    public void forEach(BiConsumer<String, List<String>> biConsumer) {
        Collections.unmodifiableMap(this.valuesMap).forEach(biConsumer);
    }

    public List<String> get(String str) {
        List<String> list = this.valuesMap.get(str);
        if (list == null) {
            return null;
        }
        return Collections.unmodifiableList(list);
    }

    public String getFirst(String str) {
        List<String> list = this.valuesMap.get(str);
        if (list == null) {
            return null;
        }
        return list.get(0);
    }

    public List<String> getIgnoreCase(String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : this.valuesMap.keySet()) {
            if (str2.equalsIgnoreCase(str)) {
                arrayList.addAll(this.valuesMap.get(str2));
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String getLast(String str) {
        List<String> list = this.valuesMap.get(str);
        if (list == null) {
            return null;
        }
        return list.get(list.size() - 1);
    }

    public byte[] getSerialized() {
        if (this.serialized == null) {
            byte[] bArr = new byte[serializedLength()];
            this.serialized = bArr;
            serializeToArray(0, bArr);
        }
        return this.serialized;
    }

    public int hashCode() {
        return Objects.hash(this.valuesMap);
    }

    public boolean isDirty() {
        return this.serialized == null;
    }

    public boolean isEmpty() {
        return this.valuesMap.isEmpty();
    }

    public boolean isReadOnly() {
        return this.readOnly;
    }

    public Set<String> keySet() {
        return Collections.unmodifiableSet(this.valuesMap.keySet());
    }

    public Set<String> keySetIgnoreCase() {
        HashSet hashSet = new HashSet();
        Iterator<String> it = this.valuesMap.keySet().iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().toLowerCase());
        }
        return Collections.unmodifiableSet(hashSet);
    }

    public Headers put(String str, String... strArr) {
        if (this.readOnly) {
            throw new UnsupportedOperationException();
        }
        return (strArr == null || strArr.length == 0) ? this : _put(str, Arrays.asList(strArr));
    }

    public void remove(String... strArr) {
        if (this.readOnly) {
            throw new UnsupportedOperationException();
        }
        for (String str : strArr) {
            _remove(str);
        }
        this.serialized = null;
    }

    public int serializeToArray(int i, byte[] bArr) {
        byte[] bArr2 = NatsConstants.HEADER_VERSION_BYTES_PLUS_CRLF;
        int i2 = HVCRLF_BYTES;
        System.arraycopy(bArr2, 0, bArr, i, i2);
        int i3 = i + i2;
        for (Map.Entry<String, List<String>> entry : this.valuesMap.entrySet()) {
            for (String str : entry.getValue()) {
                String key = entry.getKey();
                Charset charset = StandardCharsets.US_ASCII;
                byte[] bytes = key.getBytes(charset);
                System.arraycopy(bytes, 0, bArr, i3, bytes.length);
                int length = i3 + bytes.length;
                int i4 = length + 1;
                bArr[length] = NatsConstants.COLON;
                byte[] bytes2 = str.getBytes(charset);
                System.arraycopy(bytes2, 0, bArr, i4, bytes2.length);
                int length2 = i4 + bytes2.length;
                bArr[length2] = NatsConstants.CR;
                bArr[length2 + 1] = 10;
                i3 = length2 + 2;
            }
        }
        bArr[i3] = NatsConstants.CR;
        bArr[i3 + 1] = 10;
        return serializedLength();
    }

    public int serializedLength() {
        return this.dataLength + NON_DATA_BYTES;
    }

    public int size() {
        return this.valuesMap.size();
    }

    public Headers(Headers headers) {
        this(headers, false, null);
    }

    public Headers(Headers headers, boolean z) {
        this(headers, z, null);
    }

    public Headers(Headers headers, boolean z, String[] strArr) {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        if (headers != null) {
            map.putAll(headers.valuesMap);
            map2.putAll(headers.lengthMap);
            this.dataLength = headers.dataLength;
            if (strArr != null) {
                for (String str : strArr) {
                    if (str != null && map.remove(str) != null) {
                        this.dataLength -= ((Integer) map2.remove(str)).intValue();
                    }
                }
            }
        }
        this.readOnly = z;
        if (z) {
            this.valuesMap = Collections.unmodifiableMap(map);
            this.lengthMap = Collections.unmodifiableMap(map2);
        } else {
            this.valuesMap = map;
            this.lengthMap = map2;
        }
    }

    public Headers add(String str, Collection<String> collection) {
        if (this.readOnly) {
            throw new UnsupportedOperationException();
        }
        return (collection == null || collection.isEmpty()) ? this : _add(str, collection);
    }

    public Headers put(String str, Collection<String> collection) {
        if (this.readOnly) {
            throw new UnsupportedOperationException();
        }
        return (collection == null || collection.isEmpty()) ? this : _put(str, collection);
    }

    public void remove(Collection<String> collection) {
        if (!this.readOnly) {
            Iterator<String> it = collection.iterator();
            while (it.hasNext()) {
                _remove(it.next());
            }
            this.serialized = null;
            return;
        }
        throw new UnsupportedOperationException();
    }

    public Headers put(Map<String, List<String>> map) {
        if (!this.readOnly) {
            if (map != null && !map.isEmpty()) {
                for (String str : map.keySet()) {
                    _put(str, map.get(str));
                }
            }
            return this;
        }
        throw new UnsupportedOperationException();
    }
}
