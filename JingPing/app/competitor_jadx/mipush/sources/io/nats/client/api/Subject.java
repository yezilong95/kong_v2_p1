package io.nats.client.api;

import io.nats.client.support.JsonValue;
import io.nats.client.support.JsonValueUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class Subject implements Comparable<Subject> {
    private final long count;
    private final String name;

    public Subject(String str, long j) {
        this.name = str;
        this.count = j;
    }

    public static List<Subject> listOf(JsonValue jsonValue) {
        Map<String, JsonValue> map;
        ArrayList arrayList = new ArrayList();
        if (jsonValue != null && (map = jsonValue.map) != null) {
            for (String str : map.keySet()) {
                Long l = JsonValueUtils.getLong(jsonValue.map.get(str));
                if (l != null) {
                    arrayList.add(new Subject(str, l.longValue()));
                }
            }
        }
        return arrayList;
    }

    public long getCount() {
        return this.count;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return "Subject{name='" + this.name + "', count=" + this.count + '}';
    }

    @Override // java.lang.Comparable
    public int compareTo(Subject subject) {
        return this.name.compareTo(subject.name);
    }
}
