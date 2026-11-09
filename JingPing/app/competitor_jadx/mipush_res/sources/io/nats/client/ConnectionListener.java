package io.nats.client;

import defpackage.zn;

/* JADX INFO: loaded from: classes.dex */
public interface ConnectionListener {

    public enum Events {
        CONNECTED(true, "opened"),
        CLOSED(true, "closed"),
        DISCONNECTED(true, "disconnected"),
        RECONNECTED(true, "reconnected"),
        RESUBSCRIBED(false, "subscriptions re-established"),
        DISCOVERED_SERVERS(false, "discovered servers"),
        LAME_DUCK(false, "lame duck mode");

        private final boolean connectionEvent;
        private final String event;
        private final String natsEvent;

        Events(boolean z, String str) {
            this.connectionEvent = z;
            this.event = str;
            if (z) {
                this.natsEvent = zn.v("nats: connection ", str);
            } else {
                this.natsEvent = zn.v("nats: ", str);
            }
        }

        public String getEvent() {
            return this.event;
        }

        public String getNatsEvent() {
            return this.natsEvent;
        }

        public boolean isConnectionEvent() {
            return this.connectionEvent;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.natsEvent;
        }
    }

    void connectionEvent(Connection connection, Events events);
}
