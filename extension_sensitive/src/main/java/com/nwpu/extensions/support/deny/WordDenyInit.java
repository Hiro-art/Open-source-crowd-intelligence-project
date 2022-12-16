package com.nwpu.extensions.support.deny;

import com.github.houbb.heaven.annotation.ThreadSafe;
import com.github.houbb.heaven.support.pipeline.Pipeline;
import com.github.houbb.heaven.support.pipeline.impl.DefaultPipeline;
import com.github.houbb.heaven.util.io.StreamUtil;
import com.nwpu.extensions.api.IWordDeny;

import java.util.ArrayList;
import java.util.List;

/**
 * 初始化类
 */
@ThreadSafe
public abstract class WordDenyInit implements IWordDeny {

    /**
     * 初始化列表
     */
    protected abstract void init(final Pipeline<IWordDeny> pipeline);

    @Override
    public List<String> deny() {
        Pipeline<IWordDeny> pipeline = new DefaultPipeline<>();
        this.init(pipeline);

        List<String> results = new ArrayList<>();
        List<IWordDeny> wordDenies = pipeline.list();
        for (IWordDeny wordDeny : wordDenies) {
            List<String> denyList = wordDeny.deny();
            results.addAll(denyList);
        }

        return results;
    }

}
