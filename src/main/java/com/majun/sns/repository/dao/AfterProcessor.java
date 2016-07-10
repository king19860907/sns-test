package com.majun.sns.repository.dao;

import com.majun.sns.dto.ProcessParam;

/**
 * Created by majun on 2016/7/10.
 */
public interface AfterProcessor {

    void execute(ProcessParam param);

}
