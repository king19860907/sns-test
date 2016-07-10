package com.majun.sns.dto;

import java.io.Serializable;

/**
 * Created by majun on 2016/7/10.
 */
public class InfoDto implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 146637012769885247L;


    private String result;

    private String message;

    private Object dataResult;

    public static InfoDto success(){
        InfoDto info = new InfoDto();
        info.setResult("1");
        info.setMessage("成功");
        return info;
    }
    public static InfoDto fail(String flag, String msg){
        InfoDto info = new InfoDto();
        info.setResult(flag);
        info.setMessage(msg);
        return info;
    }


    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public Object getDataResult() {
        return dataResult;
    }
    public void setDataResult(Object dataResult) {
        this.dataResult = dataResult;
    }


}
