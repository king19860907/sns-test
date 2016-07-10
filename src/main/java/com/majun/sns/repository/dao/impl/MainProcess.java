package com.majun.sns.repository.dao.impl;

import com.majun.sns.dto.ProcessParam;
import com.majun.sns.repository.dao.AfterProcessor;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by majun on 2016/7/10.
 */
public class MainProcess implements AfterProcessor {

    private List<AfterProcessor> processors;

    public void execute(ProcessParam param) {
       if(!CollectionUtils.isEmpty(processors)){
           for(AfterProcessor processor : processors){
               processor.execute(param);
           }
       }
    }

    public void setProcessors(List<AfterProcessor> processors) {
        this.processors = processors;
    }

}
