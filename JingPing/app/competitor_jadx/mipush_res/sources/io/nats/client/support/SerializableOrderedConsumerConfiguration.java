package io.nats.client.support;

import io.nats.client.api.OrderedConsumerConfiguration;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class SerializableOrderedConsumerConfiguration implements Serializable {
    private static final long serialVersionUID = 1;
    private transient OrderedConsumerConfiguration occ;

    public SerializableOrderedConsumerConfiguration() {
        setOrderedConsumerConfiguration(new OrderedConsumerConfiguration());
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        this.occ = new OrderedConsumerConfiguration(objectInputStream.readUTF());
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeUTF(this.occ.toJson());
    }

    public OrderedConsumerConfiguration getOrderedConsumerConfiguration() {
        return this.occ;
    }

    public void setOrderedConsumerConfiguration(OrderedConsumerConfiguration orderedConsumerConfiguration) {
        this.occ = orderedConsumerConfiguration;
    }

    public SerializableOrderedConsumerConfiguration(OrderedConsumerConfiguration orderedConsumerConfiguration) {
        setOrderedConsumerConfiguration(orderedConsumerConfiguration);
    }
}
