package io.nats.client.support;

/* JADX INFO: loaded from: classes.dex */
public class ServerVersion implements Comparable<ServerVersion> {
    final String extra;
    final Integer major;
    final Integer minor;
    final Integer patch;

    /* JADX WARN: Removed duplicated region for block: B:25:0x007c  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0091  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ServerVersion(java.lang.String r13) {
        /*
            r12 = this;
            r12.<init>()
            r0 = -1
            r1 = 0
            java.lang.String r2 = "v"
            boolean r2 = r13.startsWith(r2)     // Catch: java.lang.NumberFormatException -> L21
            java.lang.String r3 = "\\Q.\\E"
            java.lang.String r4 = "."
            java.lang.String r5 = "-"
            r6 = 1
            if (r2 == 0) goto L25
            java.lang.String r13 = r13.substring(r6)     // Catch: java.lang.NumberFormatException -> L21
            java.lang.String r13 = r13.replaceAll(r5, r4)     // Catch: java.lang.NumberFormatException -> L21
            java.lang.String[] r13 = r13.split(r3)     // Catch: java.lang.NumberFormatException -> L21
            goto L2d
        L21:
            r3 = r0
            r6 = r3
        L23:
            r9 = r1
            goto L79
        L25:
            java.lang.String r13 = r13.replaceAll(r5, r4)     // Catch: java.lang.NumberFormatException -> L21
            java.lang.String[] r13 = r13.split(r3)     // Catch: java.lang.NumberFormatException -> L21
        L2d:
            r2 = 0
            r2 = r13[r2]     // Catch: java.lang.NumberFormatException -> L21
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L21
            r3 = r13[r6]     // Catch: java.lang.NumberFormatException -> L21
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L21
            int r6 = r13.length     // Catch: java.lang.NumberFormatException -> L77
            r7 = 3
            if (r6 >= r7) goto L40
            r6 = r0
            goto L47
        L40:
            r6 = 2
            r6 = r13[r6]     // Catch: java.lang.NumberFormatException -> L77
            int r6 = java.lang.Integer.parseInt(r6)     // Catch: java.lang.NumberFormatException -> L77
        L47:
            r9 = r1
            r8 = r7
        L49:
            int r10 = r13.length     // Catch: java.lang.NumberFormatException -> L79
            if (r8 >= r10) goto L7a
            if (r8 != r7) goto L60
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> L79
            r10.<init>()     // Catch: java.lang.NumberFormatException -> L79
            r10.append(r5)     // Catch: java.lang.NumberFormatException -> L79
            r11 = r13[r8]     // Catch: java.lang.NumberFormatException -> L79
            r10.append(r11)     // Catch: java.lang.NumberFormatException -> L79
            java.lang.String r9 = r10.toString()     // Catch: java.lang.NumberFormatException -> L79
            goto L74
        L60:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch: java.lang.NumberFormatException -> L79
            r10.<init>()     // Catch: java.lang.NumberFormatException -> L79
            r10.append(r9)     // Catch: java.lang.NumberFormatException -> L79
            r10.append(r4)     // Catch: java.lang.NumberFormatException -> L79
            r11 = r13[r8]     // Catch: java.lang.NumberFormatException -> L79
            r10.append(r11)     // Catch: java.lang.NumberFormatException -> L79
            java.lang.String r9 = r10.toString()     // Catch: java.lang.NumberFormatException -> L79
        L74:
            int r8 = r8 + 1
            goto L49
        L77:
            r6 = r0
            goto L23
        L79:
            r2 = r0
        L7a:
            if (r2 != r0) goto L91
            java.lang.Integer r13 = java.lang.Integer.valueOf(r0)
            r12.major = r13
            java.lang.Integer r13 = java.lang.Integer.valueOf(r0)
            r12.minor = r13
            java.lang.Integer r13 = java.lang.Integer.valueOf(r0)
            r12.patch = r13
            r12.extra = r1
            goto La5
        L91:
            java.lang.Integer r13 = java.lang.Integer.valueOf(r2)
            r12.major = r13
            java.lang.Integer r13 = java.lang.Integer.valueOf(r3)
            r12.minor = r13
            java.lang.Integer r13 = java.lang.Integer.valueOf(r6)
            r12.patch = r13
            r12.extra = r9
        La5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.nats.client.support.ServerVersion.<init>(java.lang.String):void");
    }

    public static boolean isNewer(String str, String str2) {
        return new ServerVersion(str).compareTo(new ServerVersion(str2)) > 0;
    }

    public static boolean isOlder(String str, String str2) {
        return new ServerVersion(str).compareTo(new ServerVersion(str2)) < 0;
    }

    public static boolean isSame(String str, String str2) {
        return new ServerVersion(str).compareTo(new ServerVersion(str2)) == 0;
    }

    public static boolean isSameOrNewer(String str, String str2) {
        return new ServerVersion(str).compareTo(new ServerVersion(str2)) >= 0;
    }

    public static boolean isSameOrOlder(String str, String str2) {
        return new ServerVersion(str).compareTo(new ServerVersion(str2)) <= 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.major);
        sb.append(NatsConstants.DOT);
        sb.append(this.minor);
        sb.append(NatsConstants.DOT);
        sb.append(this.patch);
        String str = this.extra;
        if (str == null) {
            str = "";
        }
        sb.append(str);
        return sb.toString();
    }

    @Override // java.lang.Comparable
    public int compareTo(ServerVersion serverVersion) {
        int iCompareTo = this.major.compareTo(serverVersion.major);
        if (iCompareTo != 0 || (iCompareTo = this.minor.compareTo(serverVersion.minor)) != 0 || (iCompareTo = this.patch.compareTo(serverVersion.patch)) != 0) {
            return iCompareTo;
        }
        String str = this.extra;
        if (str == null) {
            return serverVersion.extra == null ? 0 : 1;
        }
        String str2 = serverVersion.extra;
        if (str2 == null) {
            return -1;
        }
        return str.compareTo(str2);
    }
}
