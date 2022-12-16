package com.nwpu.extensions.support.replace;

import com.nwpu.extensions.api.ISensitiveWordReplaceContext;

/**
 * 敏感词替换上下文
 */
public class SensitiveWordReplaceContext implements ISensitiveWordReplaceContext {

    public static SensitiveWordReplaceContext newInstance() {
        return new SensitiveWordReplaceContext();
    }

    /**
     * 敏感词
     */
    private String sensitiveWord;

    /**
     * 单词长度
     */
    private int wordLength;

    @Override
    public String sensitiveWord() {
        return sensitiveWord;
    }

    public SensitiveWordReplaceContext sensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
        return this;
    }

    @Override
    public int wordLength() {
        return wordLength;
    }

    public SensitiveWordReplaceContext wordLength(int wordLength) {
        this.wordLength = wordLength;
        return this;
    }

    @Override
    public String toString() {
        return "SensitiveWordReplaceContext{" +
                "sensitiveWord='" + sensitiveWord + '\'' +
                ", wordLength=" + wordLength +
                '}';
    }

}
