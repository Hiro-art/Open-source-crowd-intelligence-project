package com.nwpu.extensions.constant;

/**
 * 敏感词应用常量类
 */
public final class AppConst {

    private AppConst(){}

    /**
     * 是否为结束标识
     */
    public static final String IS_END = "ED";

    /**
     * 字典的大小
     */
    public static final int DICT_SIZE = 65275;

    /**
     * 英语词典的大小
     */
    public static final int DICT_EN_SIZE = 12;

    /**
     * 拒绝的词语
     */
    public static final String SENSITIVE_WORD_DENY_PATH = "/sensitive_word_deny.txt";

    /**
     * 用户允许的词语
     */
    public static final String SENSITIVE_WORD_ALLOW_PATH = "/sensitive_word_allow.txt";

}
