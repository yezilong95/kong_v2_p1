package io.nats.client.impl;

import io.nats.client.support.JsonValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
abstract class StringListReader extends AbstractListReader {
    List<String> strings;

    public StringListReader(String str) {
        this(str, null);
    }

    public List<String> getStrings() {
        return this.strings;
    }

    @Override // io.nats.client.impl.AbstractListReader
    public void processItems(List<JsonValue> list) {
        Iterator<JsonValue> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next().string;
            if (str != null) {
                this.strings.add(str);
            }
        }
    }

    public StringListReader(String str, String str2) {
        super(str, str2);
        this.strings = new ArrayList();
    }
}
