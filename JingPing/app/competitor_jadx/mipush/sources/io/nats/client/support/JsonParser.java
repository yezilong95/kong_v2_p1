package io.nats.client.support;

import defpackage.zn;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class JsonParser {
    private char current;
    private int idx;
    private final char[] json;
    private final boolean keepNulls;
    private final int len;
    private char next;
    private int nextIdx;
    private char previous;

    public enum Option {
        KEEP_NULLS
    }

    public JsonParser(char[] cArr) {
        this(cArr, 0, new Option[0]);
    }

    private JsonValue asNumber(String str) throws JsonParseException {
        char cCharAt = str.charAt(0);
        if ((cCharAt < '0' || cCharAt > '9') && cCharAt != '-') {
            throw new JsonParseException(zn.n("val [", str, "] is not a valid number."));
        }
        if (isDecimalNotation(str)) {
            try {
                try {
                    BigDecimal bigDecimal = new BigDecimal(str);
                    return (cCharAt == '-' && BigDecimal.ZERO.compareTo(bigDecimal) == 0) ? new JsonValue(-0.0d) : new JsonValue(bigDecimal);
                } catch (NumberFormatException unused) {
                    double d = Double.parseDouble(str);
                    if (!Double.isNaN(d) && !Double.isInfinite(d)) {
                        return new JsonValue(d);
                    }
                    throw new JsonParseException("val [" + str + "] is not a valid number.");
                }
            } catch (NumberFormatException unused2) {
                throw new JsonParseException(zn.n("val [", str, "] is not a valid number."));
            }
        }
        if (cCharAt == '0' && str.length() > 1) {
            char cCharAt2 = str.charAt(1);
            if (cCharAt2 >= '0' && cCharAt2 <= '9') {
                throw new JsonParseException(zn.n("val [", str, "] is not a valid number."));
            }
        } else if (cCharAt == '-' && str.length() > 2) {
            char cCharAt3 = str.charAt(1);
            char cCharAt4 = str.charAt(2);
            if (cCharAt3 == '0' && cCharAt4 >= '0' && cCharAt4 <= '9') {
                throw new JsonParseException(zn.n("val [", str, "] is not a valid number."));
            }
        }
        BigInteger bigInteger = new BigInteger(str);
        return bigInteger.bitLength() <= 31 ? new JsonValue(bigInteger.intValue()) : bigInteger.bitLength() <= 63 ? new JsonValue(bigInteger.longValue()) : new JsonValue(bigInteger);
    }

    private boolean isDecimalNotation(String str) {
        return str.indexOf(46) > -1 || str.indexOf(101) > -1 || str.indexOf(69) > -1 || "-0".equals(str);
    }

    private List<JsonValue> nextArray() throws JsonParseException {
        ArrayList arrayList = new ArrayList();
        char cPeekToken = peekToken();
        while (cPeekToken != ']') {
            if (cPeekToken == ',') {
                nextToken();
            } else {
                arrayList.add(nextValue());
            }
            cPeekToken = peekToken();
        }
        nextToken();
        return arrayList;
    }

    private char nextChar() {
        this.previous = this.current;
        int i = this.idx;
        if (i == this.len) {
            this.current = (char) 0;
        } else {
            char[] cArr = this.json;
            this.idx = i + 1;
            this.current = cArr[i];
        }
        this.next = (char) 0;
        this.nextIdx = -1;
        return this.current;
    }

    private Map<String, JsonValue> nextObject() throws JsonParseException {
        HashMap map = new HashMap();
        while (true) {
            char cNextToken = nextToken();
            if (cNextToken == 0) {
                throw new JsonParseException("Text must end with '}'");
            }
            if (cNextToken != '[' && cNextToken != '{') {
                if (cNextToken == '}') {
                    break;
                }
            } else if (this.previous == '{') {
                throw new JsonParseException("Cannot directly nest another Object or Array.");
            }
            String strNextString = nextString();
            if (nextToken() != ':') {
                throw new JsonParseException("Expected a ':' after a key.");
            }
            JsonValue jsonValueNextValue = nextValue();
            if (jsonValueNextValue != JsonValue.NULL || this.keepNulls) {
                map.put(strNextString, jsonValueNextValue);
            }
            char cNextToken2 = nextToken();
            if (cNextToken2 != ',') {
                if (cNextToken2 != '}') {
                    throw new JsonParseException("Expected a ',' or '}'.");
                }
            } else if (peekToken() == '}') {
                break;
            }
        }
        return map;
    }

    private JsonValue nextPrimitiveValue() throws JsonParseException {
        StringBuilder sb = new StringBuilder();
        char cPeekToken = peekToken();
        while (cPeekToken >= ' ' && ",:]}/\\\"[{;=#".indexOf(cPeekToken) < 0) {
            sb.append(nextToken());
            cPeekToken = peekToken();
        }
        String string = sb.toString();
        if ("true".equalsIgnoreCase(string)) {
            return new JsonValue(Boolean.TRUE);
        }
        if ("false".equalsIgnoreCase(string)) {
            return new JsonValue(Boolean.FALSE);
        }
        if ("null".equalsIgnoreCase(string)) {
            return JsonValue.NULL;
        }
        try {
            return asNumber(string);
        } catch (Exception unused) {
            throw new JsonParseException("Invalid value.");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:44:0x0081, code lost:
    
        throw new io.nats.client.support.JsonParseException("Unterminated string.");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String nextString() throws io.nats.client.support.JsonParseException {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
        L5:
            char r1 = r6.nextChar()
            if (r1 == 0) goto L7a
            r2 = 10
            if (r1 == r2) goto L7a
            r3 = 13
            if (r1 == r3) goto L7a
            r4 = 34
            r5 = 92
            if (r1 == r5) goto L24
            if (r1 != r4) goto L20
            java.lang.String r0 = r0.toString()
            return r0
        L20:
            r0.append(r1)
            goto L5
        L24:
            char r1 = r6.nextChar()
            if (r1 == r4) goto L76
            r4 = 39
            if (r1 == r4) goto L76
            r4 = 47
            if (r1 == r4) goto L76
            if (r1 == r5) goto L76
            r4 = 98
            if (r1 == r4) goto L70
            r4 = 102(0x66, float:1.43E-43)
            if (r1 == r4) goto L6a
            r4 = 110(0x6e, float:1.54E-43)
            if (r1 == r4) goto L66
            r2 = 114(0x72, float:1.6E-43)
            if (r1 == r2) goto L62
            r2 = 116(0x74, float:1.63E-43)
            if (r1 == r2) goto L5c
            r2 = 117(0x75, float:1.64E-43)
            if (r1 != r2) goto L54
            char[] r1 = r6.parseU()
            r0.append(r1)
            goto L5
        L54:
            io.nats.client.support.JsonParseException r0 = new io.nats.client.support.JsonParseException
            java.lang.String r1 = "Illegal escape."
            r0.<init>(r1)
            throw r0
        L5c:
            r1 = 9
            r0.append(r1)
            goto L5
        L62:
            r0.append(r3)
            goto L5
        L66:
            r0.append(r2)
            goto L5
        L6a:
            r1 = 12
            r0.append(r1)
            goto L5
        L70:
            r1 = 8
            r0.append(r1)
            goto L5
        L76:
            r0.append(r1)
            goto L5
        L7a:
            io.nats.client.support.JsonParseException r0 = new io.nats.client.support.JsonParseException
            java.lang.String r1 = "Unterminated string."
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.support.JsonParser.nextString():java.lang.String");
    }

    private char nextToken() {
        peekToken();
        this.idx = this.nextIdx;
        this.nextIdx = -1;
        this.previous = this.current;
        char c = this.next;
        this.current = c;
        this.next = (char) 0;
        return c;
    }

    private JsonValue nextValue() throws JsonParseException {
        char cPeekToken = peekToken();
        if (cPeekToken == 0) {
            throw new JsonParseException("Unexpected end of data.");
        }
        if (cPeekToken == '\"') {
            nextToken();
            return new JsonValue(nextString());
        }
        if (cPeekToken == '{') {
            nextToken();
            return new JsonValue(nextObject());
        }
        if (cPeekToken != '[') {
            return nextPrimitiveValue();
        }
        nextToken();
        return new JsonValue(nextArray());
    }

    public static JsonValue parse(char[] cArr) throws JsonParseException {
        return new JsonParser(cArr, 0, new Option[0]).parse();
    }

    private char[] parseU() throws JsonParseException {
        char[] cArr = new char[4];
        for (int i = 0; i < 4; i++) {
            char cNextToken = nextToken();
            cArr[i] = cNextToken;
            if (cNextToken == 0) {
                throw new JsonParseException("Illegal escape.");
            }
        }
        try {
            return Character.toChars(Integer.parseInt("" + cArr[0] + cArr[1] + cArr[2] + cArr[3], 16));
        } catch (RuntimeException e) {
            throw new JsonParseException("Illegal escape.", e);
        }
    }

    public static JsonValue parseUnchecked(char[] cArr) {
        try {
            return parse(cArr);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    private char peekToken() {
        if (this.nextIdx == -1) {
            this.nextIdx = this.idx;
            this.next = (char) 0;
            while (true) {
                int i = this.nextIdx;
                if (i >= this.len) {
                    break;
                }
                char[] cArr = this.json;
                this.nextIdx = i + 1;
                char c = cArr[i];
                if (c != '\t' && c != '\n' && c != '\r' && c != ' ') {
                    this.next = c;
                    return c;
                }
            }
        }
        return this.next;
    }

    public JsonParser(char[] cArr, Option... optionArr) {
        this(cArr, 0, optionArr);
    }

    public static JsonValue parse(char[] cArr, int i) throws JsonParseException {
        return new JsonParser(cArr, i, new Option[0]).parse();
    }

    public JsonParser(char[] cArr, int i, Option... optionArr) {
        boolean z;
        this.json = cArr;
        int length = optionArr.length;
        int i2 = 0;
        while (true) {
            if (i2 >= length) {
                z = false;
                break;
            } else {
                if (optionArr[i2] == Option.KEEP_NULLS) {
                    z = true;
                    break;
                }
                i2++;
            }
        }
        this.keepNulls = z;
        this.len = cArr == null ? 0 : cArr.length;
        this.idx = i;
        if (i >= 0) {
            this.nextIdx = -1;
            this.previous = (char) 0;
            this.current = (char) 0;
            this.next = (char) 0;
            return;
        }
        throw new IllegalArgumentException("Invalid start index.");
    }

    public static JsonValue parse(char[] cArr, Option... optionArr) throws JsonParseException {
        return new JsonParser(cArr, 0, optionArr).parse();
    }

    public static JsonValue parseUnchecked(char[] cArr, int i) {
        try {
            return parse(cArr, i);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonValue parse(char[] cArr, int i, Option... optionArr) throws JsonParseException {
        return new JsonParser(cArr, i, optionArr).parse();
    }

    public static JsonValue parse(String str) throws JsonParseException {
        return new JsonParser(str.toCharArray(), 0, new Option[0]).parse();
    }

    public static JsonValue parseUnchecked(char[] cArr, Option... optionArr) {
        try {
            return parse(cArr, optionArr);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonValue parse(String str, int i) throws JsonParseException {
        return new JsonParser(str.toCharArray(), i, new Option[0]).parse();
    }

    public static JsonValue parse(String str, Option... optionArr) throws JsonParseException {
        return new JsonParser(str.toCharArray(), 0, optionArr).parse();
    }

    public static JsonValue parseUnchecked(char[] cArr, int i, Option... optionArr) {
        try {
            return parse(cArr, i, optionArr);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonValue parse(String str, int i, Option... optionArr) throws JsonParseException {
        return new JsonParser(str.toCharArray(), i, optionArr).parse();
    }

    public static JsonValue parse(byte[] bArr) throws JsonParseException {
        return new JsonParser(new String(bArr, StandardCharsets.UTF_8).toCharArray(), 0, new Option[0]).parse();
    }

    public static JsonValue parseUnchecked(String str) {
        try {
            return parse(str);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonValue parse(byte[] bArr, Option... optionArr) throws JsonParseException {
        return new JsonParser(new String(bArr, StandardCharsets.UTF_8).toCharArray(), 0, optionArr).parse();
    }

    public static JsonValue parseUnchecked(String str, int i) {
        try {
            return parse(str, i);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public JsonValue parse() throws JsonParseException {
        if (peekToken() == 0) {
            return JsonValue.NULL;
        }
        return nextValue();
    }

    public static JsonValue parseUnchecked(String str, Option... optionArr) {
        try {
            return parse(str, optionArr);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonValue parseUnchecked(String str, int i, Option... optionArr) {
        try {
            return parse(str, i, optionArr);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonValue parseUnchecked(byte[] bArr) {
        try {
            return parse(bArr);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }

    public static JsonValue parseUnchecked(byte[] bArr, Option... optionArr) {
        try {
            return parse(bArr, optionArr);
        } catch (JsonParseException e) {
            throw new RuntimeException(e);
        }
    }
}
