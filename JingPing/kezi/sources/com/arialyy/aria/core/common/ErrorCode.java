package com.arialyy.aria.core.common;

/* JADX INFO: loaded from: classes.dex */
public enum ErrorCode {
    ERROR_CODE_NORMAL("ERROR_CODE_NORMAL", "正常"),
    ERROR_CODE_TASK_ID_NULL("ERROR_CODE_TASK_ID_NULL", "任务id为空的错误码"),
    ERROR_CODE_URL_NULL("ERROR_CODE_URL_NULL", "url 为空"),
    ERROR_CODE_URL_INVALID("ERROR_CODE_URL_INVALID", "url 无效"),
    ERROR_CODE_PAGE_NUM("ERROR_CODE_PAGE_NUM", "page和num不能小于1"),
    ERROR_CODE_GROUP_URL_NULL("ERROR_CODE_GROUP_URL_NULL", "组合任务url列表为空"),
    ERROR_CODE_UPLOAD_FILE_NULL("ERROR_CODE_UPLOAD_FILE_NULL", "上传文件不存在"),
    ERROR_CODE_MEMBER_WARNING("ERROR_CODE_MEMBER_WARNING", "为了防止内存泄漏，请使用静态的成员类(public static class xxx)或文件类(A.java)"),
    ERROR_CODE_TASK_NOT_EXIST("ERROR_CODE_TASK_NOT_EXIST", "任务信息不存在");

    public int code;
    public String msg;

    ErrorCode(String str, String str2) {
        this.code = i;
        this.msg = str2;
    }
}
