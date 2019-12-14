package com.xiaoxiao.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @project_name:xiaoxiao_powers_of_ten
 * @date:2019/11/8:22:34
 * @author:shinelon
 * @Describe:返回结果实体类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result
{
    /**
     * 状态信息
     */
    private Integer code;

    /**
     * 标记成功
     */
    private Boolean flag;

    /**
     * 消息
     */
    private String message;


    /**
     * 携带的数据
     */
    private Object data;


    public static final String  MARKED_WORDS_FAULT = "操作失败";

    public static final String MARKED_WORDS_SUCCESS = "操作成功";


    /**
     * 返回成功的消息
     *
     * @param code    消息代码
     * @param flag    是否成功
     * @param message 消息
     * @param data    数据
     * @return
     */
    public static Result ok(Integer code, boolean flag, String message, Object data)
    {
        return new Result(code, flag, message, data);
    }


    public static Result ok(Integer code, boolean flag, Object data){
        return new Result(code, flag, data);
    }

    public Result(Integer code, boolean flag, Object data)
    {
        this.code = code;
        this.flag = flag;
        this.data = data;
    }


    /**
     *
     * @param code
     * @param flag
     * @param message
     * @return
     */
    public static Result ok(Integer code, Boolean flag, String message){
        return new Result(code,flag,message);
    }

    public Result(Integer code, Boolean flag, String message)
    {
        this.code = code;
        this.flag = flag;
        this.message = message;
    }

    /**
     * 成功消息
     *
     * @param code    状态码
     * @param message 消息
     * @return
     */
    public static Result ok(Integer code, String message)
    {
        return new Result(code, message);
    }

    public Result(Integer code, String message)
    {
        this.code = code;
        this.message = message;
    }

    /**
     *
     * @return
     */
    public static Result ok(Integer code, String message, Object data)
    {
        return new Result(code, message, data);
    }

    /**
     *
     * @param code
     * @param data
     * @return
     */
    public static Result ok (Integer code, Object data){
        return  new Result(code, data);
    }

    public Result(Integer code, Object data)
    {
        this.code = code;
        this.data = data;
    }

    public Result(Integer code, String message, Object data)
    {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 失败请求
     * @param code
     * @param message
     * @return
     */
    public static Result error(int code, String message)
    {
        return  new Result(code, message);
    }


    public static Result error(int error, boolean b, String markedWordsFault, Object o)
    {
        return new Result(error,b,markedWordsFault,o);
    }
}
