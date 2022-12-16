package com.nwpu.extensions.bs;

import com.nwpu.extensions.api.IWordContext;

import java.util.Map;

/**
 * 上下文
 */
public class SensitiveWordContext implements IWordContext {

    /**
     * 忽略大小写
     */
    private boolean ignoreCase;

    /**
     * 忽略半角全角
     */
    private boolean ignoreWidth;

    /**
     * 是否忽略数字格式
     */
    private boolean ignoreNumStyle;

    /**
     * 敏感词信息
     */
    private Map sensitiveWordMap;

    /**
     * 是否进行敏感数字检测
     */
    private boolean sensitiveCheckNum;

    /**
     * 是否忽略中文繁简体
     */
    private boolean ignoreChineseStyle;

    /**
     * 是否忽略英文的写法
     */
    private boolean ignoreEnglishStyle;

    /**
     * 忽略重复词
     */
    private boolean ignoreRepeat;

    /**
     * 是否进行邮箱测试
     */
    private boolean sensitiveCheckEmail;

    /**
     * 是否进行 url 测试
     */
    private boolean sensitiveCheckUrl;

    /**
     * 私有化构造器
     */
    private SensitiveWordContext() {
    }

    /**
     * 新建一个对象实例
     * @return 对象实例
     */
    public static SensitiveWordContext newInstance() {
        return new SensitiveWordContext();
    }

    @Override
    public boolean ignoreCase() {
        return ignoreCase;
    }

    @Override
    public SensitiveWordContext ignoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
        return this;
    }

    @Override
    public boolean ignoreWidth() {
        return ignoreWidth;
    }

    @Override
    public SensitiveWordContext ignoreWidth(boolean ignoreWidth) {
        this.ignoreWidth = ignoreWidth;
        return this;
    }

    @Override
    public boolean ignoreNumStyle() {
        return ignoreNumStyle;
    }

    @Override
    public SensitiveWordContext ignoreNumStyle(boolean ignoreNumStyle) {
        this.ignoreNumStyle = ignoreNumStyle;
        return this;
    }

    @Override
    public Map sensitiveWordMap() {
        return sensitiveWordMap;
    }

    @Override
    public SensitiveWordContext sensitiveWordMap(Map sensitiveWordMap) {
        this.sensitiveWordMap = sensitiveWordMap;
        return this;
    }

    @Override
    public boolean sensitiveCheckNum() {
        return sensitiveCheckNum;
    }

    @Override
    public SensitiveWordContext sensitiveCheckNum(boolean sensitiveCheckNum) {
        this.sensitiveCheckNum = sensitiveCheckNum;
        return this;
    }

    @Override
    public boolean ignoreChineseStyle() {
        return ignoreChineseStyle;
    }

    @Override
    public SensitiveWordContext ignoreChineseStyle(boolean ignoreChineseStyle) {
        this.ignoreChineseStyle = ignoreChineseStyle;
        return this;
    }

    @Override
    public boolean ignoreEnglishStyle() {
        return ignoreEnglishStyle;
    }

    @Override
    public SensitiveWordContext ignoreEnglishStyle(boolean ignoreEnglishStyle) {
        this.ignoreEnglishStyle = ignoreEnglishStyle;
        return this;
    }

    @Override
    public boolean ignoreRepeat() {
        return ignoreRepeat;
    }

    @Override
    public SensitiveWordContext ignoreRepeat(boolean ignoreRepeat) {
        this.ignoreRepeat = ignoreRepeat;
        return this;
    }

    @Override
    public boolean sensitiveCheckEmail() {
        return sensitiveCheckEmail;
    }

    @Override
    public SensitiveWordContext sensitiveCheckEmail(boolean sensitiveCheckEmail) {
        this.sensitiveCheckEmail = sensitiveCheckEmail;
        return this;
    }

    @Override
    public boolean sensitiveCheckUrl() {
        return sensitiveCheckUrl;
    }

    @Override
    public SensitiveWordContext sensitiveCheckUrl(boolean sensitiveCheckUrl) {
        this.sensitiveCheckUrl = sensitiveCheckUrl;
        return this;
    }
}
