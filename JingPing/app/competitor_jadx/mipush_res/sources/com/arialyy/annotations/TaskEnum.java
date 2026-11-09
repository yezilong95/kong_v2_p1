package com.arialyy.annotations;

/* JADX INFO: loaded from: classes.dex */
public enum TaskEnum {
    DOWNLOAD("DownloadTask", "$$DownloadListenerProxy", "AptNormalTaskListener"),
    DOWNLOAD_GROUP("DownloadGroupTask", "$$DownloadGroupListenerProxy", "AptNormalTaskListener"),
    DOWNLOAD_GROUP_SUB("DownloadGroupTask", "$$DGSubListenerProxy", "AptSubTaskListener"),
    UPLOAD("UploadTask", "$$UploadListenerProxy", "AptNormalTaskListener"),
    M3U8_PEER("DownloadTask", "$$M3U8PeerListenerProxy", "AptM3U8PeerTaskListener");

    public String className;
    public String pkg = "com.arialyy.aria.core.task";
    public String proxySuffix;
    public String proxySuperClass;

    TaskEnum(String str, String str2, String str3) {
        this.className = str;
        this.proxySuffix = str2;
        this.proxySuperClass = str3;
    }
}
