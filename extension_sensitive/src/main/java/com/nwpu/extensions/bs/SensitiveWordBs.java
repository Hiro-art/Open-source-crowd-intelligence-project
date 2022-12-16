package com.nwpu.extensions.bs;

import com.github.houbb.heaven.constant.CharConst;
import com.github.houbb.heaven.support.handler.IHandler;
import com.github.houbb.heaven.util.common.ArgUtil;
import com.github.houbb.heaven.util.util.CollectionUtil;
import com.nwpu.extensions.api.*;
import com.nwpu.extensions.support.allow.WordAllows;
import com.nwpu.extensions.support.deny.WordDenys;
import com.nwpu.extensions.support.map.SensitiveWordMap;
import com.nwpu.extensions.support.replace.SensitiveWordReplaceChar;
import com.nwpu.extensions.support.result.WordResultHandlers;
import com.nwpu.extensions.utils.InnerFormatUtils;

import java.util.*;

/**
 * 敏感词引导类
 *
 */
public class SensitiveWordBs {

    /**
     * 私有化构造器
     */
    private SensitiveWordBs() {
    }

    /**
     * 敏感词 map
     */
    private IWordMap sensitiveWordMap;

    /**
     * 默认的执行上下文
     */
    private final IWordContext context = buildDefaultContext();

    /**
     * 禁止的单词
     */
    private IWordDeny wordDeny = WordDenys.system();

    /**
     * 允许的单词
     */
    private IWordAllow wordAllow = WordAllows.system();

    /**
     * DCL 初始化 wordMap 信息
     */
    private synchronized void initWordMap() {
        // 加载配置信息
        List<String> denyList = wordDeny.deny();
        List<String> allowList = wordAllow.allow();
        List<String> results = getActualDenyList(denyList, allowList);

        // 初始化 DFA 信息
        if(sensitiveWordMap == null) {
            sensitiveWordMap = new SensitiveWordMap();
        }
        // 便于可以多次初始化
        sensitiveWordMap.initWordMap(results);
    }

    /**
     * 获取禁止列表中真正的禁止词汇
     * @param denyList 禁止
     * @param allowList 允许
     * @return 结果
     */
    List<String> getActualDenyList(List<String> denyList,
                                   List<String> allowList) {
        if(CollectionUtil.isEmpty(denyList)) {
            return Collections.emptyList();
        }
        if(CollectionUtil.isEmpty(allowList)) {
            return denyList;
        }

        List<String> formatDenyList = this.formatWordList(denyList);
        List<String> formatAllowList = this.formatWordList(allowList);

        List<String> resultList = new ArrayList<>();
        // O(1)
        Set<String> allowSet = new HashSet<>(formatAllowList);

        for(String deny : formatDenyList) {
            if(allowSet.contains(deny)) {
                continue;
            }

            resultList.add(deny);
        }
        return resultList;
    }

    /**
     * 数据格式化处理
     * @param list 列表
     * @return 结果
     */
    private List<String> formatWordList(List<String> list) {
        if(CollectionUtil.isEmpty(list)) {
            return list;
        }

        List<String> resultList = new ArrayList<>(list.size());
        for(String word : list) {
            String formatWord = InnerFormatUtils.format(word, this.context);
            resultList.add(formatWord);
        }

        return resultList;
    }

    /**
     * 新建验证实例
     * double-lock
     *
     * @return this
     */
    public static SensitiveWordBs newInstance() {
        return new SensitiveWordBs();
    }

    /**
     * 初始化
     *
     * 1. 根据配置，初始化对应的 map。比较消耗性能。
     * @return this
     */
    public SensitiveWordBs init() {
        this.initWordMap();

        return this;
    }

    /**
     * 设置禁止的实现
     * @param wordDeny 禁止的实现
     * @return this
     */
    public SensitiveWordBs wordDeny(IWordDeny wordDeny) {
        ArgUtil.notNull(wordDeny, "wordDeny");
        this.wordDeny = wordDeny;
        return this;
    }

    /**
     * 设置允许的实现
     * @param wordAllow 允许的实现
     * @return this
     */
    public SensitiveWordBs wordAllow(IWordAllow wordAllow) {
        ArgUtil.notNull(wordAllow, "wordAllow");
        this.wordAllow = wordAllow;
        return this;
    }

    /**
     * 设置是否启动数字检测
     *
     * @param enableNumCheck 数字检测
     */
    public SensitiveWordBs enableNumCheck(boolean enableNumCheck) {
        this.context.sensitiveCheckNum(enableNumCheck);
        return this;
    }

    /**
     * 设置是否启动 email 检测
     *
     * @param enableEmailCheck email 检测
     * @return this
     */
    public SensitiveWordBs enableEmailCheck(boolean enableEmailCheck) {
        this.context.sensitiveCheckEmail(enableEmailCheck);
        return this;
    }

    /**
     * 设置是否启动 url 检测
     *
     * @param enableUrlCheck url 检测
     * @return this
     */
    public SensitiveWordBs enableUrlCheck(boolean enableUrlCheck) {
        this.context.sensitiveCheckUrl(enableUrlCheck);
        return this;
    }

    /**
     * 是否忽略大小写
     * @param ignoreCase 大小写
     * @return this
     */
    public SensitiveWordBs ignoreCase(boolean ignoreCase) {
        this.context.ignoreCase(ignoreCase);
        return this;
    }

    /**
     * 是否忽略半角全角
     * @param ignoreWidth 半角全角
     * @return this
     */
    public SensitiveWordBs ignoreWidth(boolean ignoreWidth) {
        this.context.ignoreWidth(ignoreWidth);
        return this;
    }

    /**
     * 是否忽略数字格式
     * @param ignoreNumStyle 数字格式
     * @return this
     */
    public SensitiveWordBs ignoreNumStyle(boolean ignoreNumStyle) {
        this.context.ignoreNumStyle(ignoreNumStyle);
        return this;
    }

    /**
     * 是否忽略中文样式
     * @param ignoreChineseStyle 中文样式
     * @return this
     */
    public SensitiveWordBs ignoreChineseStyle(boolean ignoreChineseStyle) {
        this.context.ignoreChineseStyle(ignoreChineseStyle);
        return this;
    }

    /**
     * 是否忽略英文样式
     * @param ignoreEnglishStyle 英文样式
     * @return this
     */
    public SensitiveWordBs ignoreEnglishStyle(boolean ignoreEnglishStyle) {
        this.context.ignoreEnglishStyle(ignoreEnglishStyle);
        return this;
    }

    /**
     * 是否忽略重复
     * @param ignoreRepeat 忽略重复
     * @return this
     */
    public SensitiveWordBs ignoreRepeat(boolean ignoreRepeat) {
        this.context.ignoreRepeat(ignoreRepeat);
        return this;
    }

    /**
     * 构建默认的上下文
     *
     * @return 结果
     */
    private IWordContext buildDefaultContext() {
        IWordContext wordContext = SensitiveWordContext.newInstance();
        // 格式统一化
        wordContext.ignoreCase(true);
        wordContext.ignoreWidth(true);
        wordContext.ignoreNumStyle(true);
        wordContext.ignoreChineseStyle(true);
        wordContext.ignoreEnglishStyle(true);
        wordContext.ignoreRepeat(false);

        // 开启校验
        wordContext.sensitiveCheckNum(true);
        wordContext.sensitiveCheckEmail(true);
        wordContext.sensitiveCheckUrl(true);

        return wordContext;
    }

    /**
     * 是否包含敏感词
     *
     * @param target 目标字符串
     * @return 是否
     */
    public boolean contains(final String target) {
        statusCheck();

        return sensitiveWordMap.contains(target, context);
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     *
     * @param target 目标字符串
     * @return 敏感词列表
     */
    public List<String> findAll(final String target) {
        return findAll(target, WordResultHandlers.word());
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target 目标字符串
     * @return 敏感词
     */
    public String findFirst(final String target) {
        return findFirst(target, WordResultHandlers.word());
    }

    /**
     * 返回所有的敏感词
     * 1. 这里是默认去重的，且是有序的。
     * 2. 如果不存在，返回空列表
     *
     * @param target 目标字符串
     * @param <R> 泛型
     * @param handler 处理类
     * @return 敏感词列表
     */
    public <R> List<R> findAll(final String target, final IWordResultHandler<R> handler) {
        ArgUtil.notNull(handler, "handler");
        statusCheck();

        List<IWordResult> wordResults = sensitiveWordMap.findAll(target, context);
        return CollectionUtil.toList(wordResults, new IHandler<IWordResult, R>() {
            @Override
            public R handle(IWordResult wordResult) {
                return handler.handle(wordResult);
            }
        });
    }

    /**
     * 返回第一个敏感词
     * （1）如果不存在，则返回 {@code null}
     *
     * @param target 目标字符串
     * @param handler 处理类
     * @param <R> 泛型
     * @return 敏感词
     */
    public <R> R findFirst(final String target, final IWordResultHandler<R> handler) {
        ArgUtil.notNull(handler, "handler");
        statusCheck();

        IWordResult wordResult = sensitiveWordMap.findFirst(target, context);
        return handler.handle(wordResult);
    }


    /**
     * 替换所有内容
     *
     * @param target      目标字符串
     * @param replaceChar 替换为的 char
     * @return 替换后结果
     */
    public String replace(final String target, final char replaceChar) {
        ISensitiveWordReplace replace = new SensitiveWordReplaceChar(replaceChar);

        return replace(target, replace);
    }

    /**
     * 替换所有内容
     *
     * @param target      目标字符串
     * @param replace 替换策略
     * @return 替换后结果
     */
    public String replace(final String target, final ISensitiveWordReplace replace) {
        statusCheck();

        return sensitiveWordMap.replace(target, replace, context);
    }

    /**
     * 替换所有内容
     * 1. 默认使用空格替换，避免星号改变 md 的格式。
     *
     * @param target 目标字符串
     * @return 替换后结果
     */
    public String replace(final String target) {
        return this.replace(target, CharConst.STAR);
    }


    /**
     * 状态校验
     */
    private void statusCheck(){
        //DLC
        if(sensitiveWordMap == null) {
            synchronized (this) {
                if(sensitiveWordMap == null) {
                    this.init();
                }
            }
        }
    }

}
