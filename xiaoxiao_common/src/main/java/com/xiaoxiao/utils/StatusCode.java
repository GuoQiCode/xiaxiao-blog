package com.xiaoxiao.utils;

/**
 * @project_name:xiaoxiao_powers_of_ten
 * @date:2019/11/8:22:41
 * @author:shinelon
 * @Describe:装态码
 */
public class StatusCode
{
    /**
     * 成功
     */
    public static final int OK=20000;

    /**
     * 失败
     */
    public static final int ERROR=20001;

    /**
     * 用户名或密码错误
     */
    public static final int LOGINERROR=20002;

    /**
     * 权限不足
     */
    public static final int ACCESSERROR=20003;

    /**
     * 远程调用失败
     */
    public static final int REMOTEERROR=20004;

    /**
     * 重复操作
     */
    public static final int REPERROR=20005;

    /**
     * 没有数据
     */
    public static final int NO_DATA_EXIST = 20006;


    /**
     * 上传图片成功
     */
    public static final int UPLOADSUCCESS = 1;


    /**
     * 上传图片失败
     */
    public static final int UPLOADERROR = 0;
}
