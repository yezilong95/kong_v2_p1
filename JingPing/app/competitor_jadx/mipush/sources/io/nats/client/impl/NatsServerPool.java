package io.nats.client.impl;

import io.nats.client.Options;
import io.nats.client.ServerPool;
import io.nats.client.support.NatsUri;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: classes.dex */
public class NatsServerPool implements ServerPool {
    protected String defaultScheme;
    protected List<ServerPoolEntry> entryList;
    protected boolean hasSecureServer;
    protected NatsUri lastConnected;
    protected final ReentrantLock listLock = new ReentrantLock();
    protected int maxConnectAttempts;
    protected Options options;

    @Override // io.nats.client.ServerPool
    public boolean acceptDiscoveredUrls(List<String> list) {
        boolean z = false;
        if (this.options.isIgnoreDiscoveredServers()) {
            return false;
        }
        this.listLock.lock();
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                try {
                    arrayList.add(new NatsUri(it.next(), this.defaultScheme));
                } catch (URISyntaxException unused) {
                }
            }
            ArrayList arrayList2 = new ArrayList();
            for (ServerPoolEntry serverPoolEntry : this.entryList) {
                int iFindEquivalent = findEquivalent(arrayList, serverPoolEntry.nuri);
                if (iFindEquivalent != -1 || serverPoolEntry.nuri.equals(this.lastConnected) || !serverPoolEntry.isGossiped) {
                    arrayList2.add(serverPoolEntry);
                    if (iFindEquivalent != -1) {
                        arrayList.remove(iFindEquivalent);
                    }
                }
            }
            if (!arrayList.isEmpty()) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    arrayList2.add(new ServerPoolEntry((NatsUri) it2.next(), true));
                }
                z = true;
            }
            this.entryList = arrayList2;
            afterListChanged();
            return z;
        } finally {
            this.listLock.unlock();
        }
    }

    public void afterListChanged() {
        if (this.entryList.size() > 1 && !this.options.isNoRandomize()) {
            Collections.shuffle(this.entryList, ThreadLocalRandom.current());
        }
        this.hasSecureServer = false;
        int i = -1;
        for (int i2 = 0; i2 < this.entryList.size(); i2++) {
            NatsUri natsUri = this.entryList.get(i2).nuri;
            this.hasSecureServer |= natsUri.isSecure();
            if (natsUri.equals(this.lastConnected)) {
                i = i2;
            }
        }
        if (i != -1) {
            List<ServerPoolEntry> list = this.entryList;
            list.add(list.remove(i));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
    
        r4 = r1.failedAttempts + 1;
        r1.failedAttempts = r4;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0027, code lost:
    
        if (r4 < r3.maxConnectAttempts) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0029, code lost:
    
        r3.entryList.remove(r0);
     */
    @Override // io.nats.client.ServerPool
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void connectFailed(io.nats.client.support.NatsUri r4) {
        /*
            r3 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r3.listLock
            r0.lock()
            java.util.List<io.nats.client.impl.ServerPoolEntry> r0 = r3.entryList     // Catch: java.lang.Throwable -> L2f
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L2f
            int r0 = r0 + (-1)
        Ld:
            if (r0 < 0) goto L31
            java.util.List<io.nats.client.impl.ServerPoolEntry> r1 = r3.entryList     // Catch: java.lang.Throwable -> L2f
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L2f
            io.nats.client.impl.ServerPoolEntry r1 = (io.nats.client.impl.ServerPoolEntry) r1     // Catch: java.lang.Throwable -> L2f
            io.nats.client.support.NatsUri r2 = r1.nuri     // Catch: java.lang.Throwable -> L2f
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> L2f
            if (r2 == 0) goto L37
            int r4 = r1.failedAttempts     // Catch: java.lang.Throwable -> L2f
            int r4 = r4 + 1
            r1.failedAttempts = r4     // Catch: java.lang.Throwable -> L2f
            int r1 = r3.maxConnectAttempts     // Catch: java.lang.Throwable -> L2f
            if (r4 < r1) goto L31
            java.util.List<io.nats.client.impl.ServerPoolEntry> r4 = r3.entryList     // Catch: java.lang.Throwable -> L2f
            r4.remove(r0)     // Catch: java.lang.Throwable -> L2f
            goto L31
        L2f:
            r4 = move-exception
            goto L3a
        L31:
            java.util.concurrent.locks.ReentrantLock r4 = r3.listLock
            r4.unlock()
            return
        L37:
            int r0 = r0 + (-1)
            goto Ld
        L3a:
            java.util.concurrent.locks.ReentrantLock r0 = r3.listLock
            r0.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsServerPool.connectFailed(io.nats.client.support.NatsUri):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x001f, code lost:
    
        r3.lastConnected = r4;
        r1.failedAttempts = 0;
     */
    @Override // io.nats.client.ServerPool
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void connectSucceeded(io.nats.client.support.NatsUri r4) {
        /*
            r3 = this;
            java.util.concurrent.locks.ReentrantLock r0 = r3.listLock
            r0.lock()
            java.util.List<io.nats.client.impl.ServerPoolEntry> r0 = r3.entryList     // Catch: java.lang.Throwable -> L2a
            int r0 = r0.size()     // Catch: java.lang.Throwable -> L2a
            int r0 = r0 + (-1)
        Ld:
            if (r0 < 0) goto L24
            java.util.List<io.nats.client.impl.ServerPoolEntry> r1 = r3.entryList     // Catch: java.lang.Throwable -> L2a
            java.lang.Object r1 = r1.get(r0)     // Catch: java.lang.Throwable -> L2a
            io.nats.client.impl.ServerPoolEntry r1 = (io.nats.client.impl.ServerPoolEntry) r1     // Catch: java.lang.Throwable -> L2a
            io.nats.client.support.NatsUri r2 = r1.nuri     // Catch: java.lang.Throwable -> L2a
            boolean r2 = r2.equals(r4)     // Catch: java.lang.Throwable -> L2a
            if (r2 == 0) goto L2c
            r3.lastConnected = r4     // Catch: java.lang.Throwable -> L2a
            r4 = 0
            r1.failedAttempts = r4     // Catch: java.lang.Throwable -> L2a
        L24:
            java.util.concurrent.locks.ReentrantLock r4 = r3.listLock
            r4.unlock()
            return
        L2a:
            r4 = move-exception
            goto L2f
        L2c:
            int r0 = r0 + (-1)
            goto Ld
        L2f:
            java.util.concurrent.locks.ReentrantLock r0 = r3.listLock
            r0.unlock()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.impl.NatsServerPool.connectSucceeded(io.nats.client.support.NatsUri):void");
    }

    public int findEquivalent(List<NatsUri> list, NatsUri natsUri) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equivalent(natsUri)) {
                return i;
            }
        }
        return -1;
    }

    @Override // io.nats.client.ServerPool
    public List<String> getServerList() {
        this.listLock.lock();
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<ServerPoolEntry> it = this.entryList.iterator();
            while (it.hasNext()) {
                arrayList.add(it.next().nuri.toString());
            }
            return arrayList;
        } finally {
            this.listLock.unlock();
        }
    }

    @Override // io.nats.client.ServerPool
    public boolean hasSecureServer() {
        return this.hasSecureServer;
    }

    @Override // io.nats.client.ServerPool
    public void initialize(Options options) {
        this.options = options;
        this.maxConnectAttempts = options.getMaxReconnect() < 0 ? Integer.MAX_VALUE : this.options.getMaxReconnect() + 1;
        this.listLock.lock();
        try {
            this.entryList = new ArrayList();
            for (NatsUri natsUri : this.options.getNatsServerUris()) {
                Iterator<ServerPoolEntry> it = this.entryList.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (natsUri.equivalent(it.next().nuri)) {
                            break;
                        }
                    } else {
                        if (this.defaultScheme == null && !natsUri.getScheme().equals("nats")) {
                            this.defaultScheme = natsUri.getScheme();
                        }
                        this.entryList.add(new ServerPoolEntry(natsUri, false));
                    }
                }
            }
            afterListChanged();
            this.listLock.unlock();
        } catch (Throwable th) {
            this.listLock.unlock();
            throw th;
        }
    }

    @Override // io.nats.client.ServerPool
    public NatsUri nextServer() {
        this.listLock.lock();
        try {
            if (this.entryList.isEmpty()) {
                this.listLock.unlock();
                return null;
            }
            ServerPoolEntry serverPoolEntryRemove = this.entryList.remove(0);
            serverPoolEntryRemove.lastAttempt = System.currentTimeMillis();
            this.entryList.add(serverPoolEntryRemove);
            return serverPoolEntryRemove.nuri;
        } finally {
            this.listLock.unlock();
        }
    }

    @Override // io.nats.client.ServerPool
    public NatsUri peekNextServer() {
        this.listLock.lock();
        try {
            return this.entryList.isEmpty() ? null : this.entryList.get(0).nuri;
        } finally {
            this.listLock.unlock();
        }
    }

    @Override // io.nats.client.ServerPool
    public List<String> resolveHostToIps(String str) {
        if (this.options.isNoResolveHostnames()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            for (InetAddress inetAddress : InetAddress.getAllByName(str)) {
                arrayList.add(inetAddress.getHostAddress());
            }
        } catch (UnknownHostException unused) {
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        if (arrayList.size() > 1 && !this.options.isNoRandomize()) {
            Collections.shuffle(arrayList, ThreadLocalRandom.current());
        }
        return arrayList;
    }
}
