package com.nwpu.extensions.support.allow;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.nwpu.extensions.api.IWordAllow;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化类
 */
@ThreadSafe
public abstract class WordAllowInit implements IWordAllow {

    /**
     * 初始化列表
     *
     * @param pipeline 当前列表泳道
     */
    protected abstract void init(final Pipeline<IWordAllow> pipeline);

    @Override
    public List<String> allow() {
        Pipeline<IWordAllow> pipeline = new DefaultPipeline<>();
        this.init(pipeline);

        List<String> results = new ArrayList<>();
        List<IWordAllow> wordAllows = pipeline.list();
        for (IWordAllow wordAllow : wordAllows) {
            List<String> allowList = wordAllow.allow();
            results.addAll(allowList);
        }

        return results;
    }

}
