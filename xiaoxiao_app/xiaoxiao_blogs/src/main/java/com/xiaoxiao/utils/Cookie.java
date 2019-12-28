package com.xiaoxiao.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * _ooOoo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * O\  =  /O
 * ____/`---'\____
 * .'  \\|     |//  `.
 * /  \\|||  :  |||//  \
 * /  _||||| -:- |||||-  \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |   |
 * \  .-\__  `-`  ___/-. /
 * ___`. .'  /--.--\  `. . __
 * ."" '<  `.___\_<|>_/___.'  >'"".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 写字楼里写字间，写字间里程序员；
 * 程序人员写程序，又拿程序换酒钱。
 * 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。
 * 但愿老死电脑间，不愿鞠躬老板前；
 * 奔驰宝马贵者趣，公交自行程序员。
 * 别人笑我忒疯癫，我笑自己命太贱；
 * 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:ideaProject
 * @date:2019/12/27:17:42
 * @author:shinelon
 * @Describe:
 */
public class Cookie
{



    /**
     *
     * @param request
     * @param response
     * @param cookieName
     * @param value
     * @param maxAge
     */
    public static void setCookie(HttpServletRequest request, HttpServletResponse response, String cookieName, String value, Integer maxAge, boolean isEncode){
        javax.servlet.http.Cookie cookie = null;
        try{
            if(value == null){
                value = "";
            } else if(isEncode){
                value = URLEncoder.encode(value, "utf-8");
            }
            cookie = new javax.servlet.http.Cookie(cookieName, value);
            cookie.setMaxAge(maxAge);
            cookie.setPath("/");
            response.addCookie(cookie);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 获取
     * @param request
     * @param cookieName
     * @return
     */
    public static String getCookie(HttpServletRequest request, String cookieName)
    {
        String retValue = null;
        javax.servlet.http.Cookie[] cookies = request.getCookies();
        if(cookies.length <= 0 || cookies == null){
            return retValue;
        }
        try
        {
            for (javax.servlet.http.Cookie x: cookies
                 )
            {
                if(x.getName().equals(cookieName)){
                    retValue = URLEncoder.encode(x.getValue(), "utf-8");
                    break;
                }
            }
        } catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
        return retValue;
    }
}
