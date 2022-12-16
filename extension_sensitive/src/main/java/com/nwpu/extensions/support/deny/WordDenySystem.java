package com.nwpu.extensions.support.deny;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.util.guava.Guavas;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.nwpu.extensions.api.IWordDeny;
import com.nwpu.extensions.constant.AppConst;

import java.util.List;

/**
 * 系统默认的信息
 * @author binbin.hou
 */
@ThreadSafe
public class WordDenySystem implements IWordDeny {

    @Override
    public List<String> deny() {
        List<String> results = StreamUtil.readAllLines("/dict.txt");
        results.addAll(StreamUtil.readAllLines("/dict_en.txt"));
        results.addAll(StreamUtil.readAllLines("/sensitive_word_deny.txt"));
        return results;
    }

}
