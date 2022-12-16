# Blog评论过滤扩展

[TOC]

## 基础介绍

后端的评论过滤包，基于DFA算法实现，包含敏感词库

<br />

<br />

<br />

## 后端项目使用

```
SensitiveWordBs: 评论过滤类
	replace: 敏感词过滤方法

/utils:
	HTMLUtils: 过滤工具类
		filter: 过滤方法

/service/impl:
	CommentServiceImpl: 评论服务实现类
		saveComment: 保存评论
	MessageServiceImpl: 留言服务实现类
		saveMessage: 保存留言
	WebSocketServiceImpl: websocket聊天服务实现类
		onMessage: 发送聊天消息
```

<br />

<br />

<br />

## 目录结构

```yaml
/com/nwpu/extension:
  /api:
    ICharFormat: Char单词格式化接口（忽略大小写、忽略全角半角、忽略停顿词、忽略停顿词）
    ISensitiveWordReplace: 敏感词替换策略接口
    ISensitiveWordReplaceContext: 敏感词替换策略上下文接口
    IWordAllow: 运行单词列表接口
    IWordContext: 单词上下文接口
    IWordData: 数据词接口
    IWordDeny: 拒绝词接口
    IWordMap: 敏感词Map接口
    IWordResult: 敏感词结果封装接口
    IWordResultHandler<R>: 敏感词结果处理接口
  /bs: 引导包
    SensitiveWordBs: 主引导类（敏感词Map、单词上下文、拒绝词、允许词）
      -------------------方法列表
      1.初始化敏感词Map
      2.获取禁止列表中真正的禁止词汇
      3.数据格式化处理
      4.新建验证实例
      5.初始化
      6.设置禁止的实现
      7.设置允许的实现
      8.设置是否启动数字检测
      9.设置是否启动 email 检测
      10.设置是否启动 url 检测
      11.是否忽略大小写
      12.是否忽略半角全角
      13.是否忽略数字格式
      14.是否忽略中文样式
      15.是否忽略英文样式
      16.是否忽略重复
      17.构建默认的上下文
      -------------------功能列表
      18.是否包含敏感词
      19.返回所有的敏感词
      20.返回第一个敏感词
      21.替换所有内容
      22.状态校验
    SensitiveWordContenxt: 主引导单词上下文类（忽略大小写、忽略半角全角、忽略数字格式、敏感数字检测、忽略中文繁简体、忽略英文的写法、忽略重复词、邮箱测试、url测试、）
  /constant: 常量
    /enums: 枚举
      ValidModeEnum: 校验模式枚举类（快速失败、全部遍历）
    AppConst: 敏感词应用常量类（结束标识、字典Map大小、英语词典的大小、拒绝的词语、用户允许的词语）
  /core: 核心包
    SensitiveWordHelper: 敏感词工具类（SensitiveWordBs实例）
  /exception:
    SensitiveWordException: 敏感词异常类
  /support: 功能支持实现包
    /allow: 允许词
      WordAllowInit: 允许词初始化抽象类（责任链Pipeline实现）
      WordAllows： 允许词链
      WordAllowSystem: 允许词接口默认实现类（加载本地允许词文件）
    /check: 敏感词检测
      /impl:
        SensitiveCheckChain: 敏感词检测责任链
        SensitiveCheckEmail: 邮箱正则表达式检测实现类
        SensitiveCheckNum: 数字检测实现类
        SensitiveCheckUrl: Url正则表达式检测实现类
        SensitiveCheckWord: 敏感词检测默认实现类（DFA实现）
      ISensitiveCheck: 敏感词检测接口
      SensitiveCheckResult: 敏感词检测接口类（下标、检测类）
    /data: 
      SensitiveWordData: 敏感词数据类（/dict.txt、/dict_en.txt、/sensitive_word_deny.txt、/sensitive_word_allow.txt）
    /deny: 拒绝词
      WordDenyInit: 拒绝词初始化抽象类（责任链Pipeline实现）
      WordDenys: 拒绝词词链
      WordDenySystem: 拒绝词接口默认实现类（加载本地拒绝词文件）
    /format: 格式化
      CharFormatChain: 字符格式化责任链
      IgnoreCaseCharFormat: 字符忽略大小写格式化实现类
      IgnoreChineseStyleFormat: 字符忽略中文简繁样式格式化实现类
      IgnoreEnglishStyleFormat: 字符忽略英文样式格式化实现类
      IgnoreNumStyleFormat: 字符忽略数字样式格式化实现类
      IgnoreWidthCharFormat: 字符忽略半角格式化实现类
    /map: Map
      SensitiveWordMap: 敏感词Map接口实现类（DFA构建）
        -------------------方法列表
        1.初始化敏感词Map
        2.是否包含敏感词
        3.返回所有对应的敏感词
        4.返回第一个敏感词
        5.敏感词替换
        6.敏感词检测
    /replace: 替换
      SensitiveWordReplaceChar: 敏感词替换策略接口实现类
      SensitiveWordRepalceContext: 敏感词替换策略上下文接口实现类
    /result: 结果
      WordResult: 敏感词结果封装接口实现类
      WordResultHandlerRaw: 敏感词结果处理接口原始实现（不做处理）
      WordResultHandlers: 敏感词处理封装
      WordResultHandlerWord: 敏感词结果处理接口单词处理实现（只保留单词）
  /utils: 工具
    CharUtils: 字符处理工具类
    InnerFormatUtils: 内部格式化工具类
    
```



