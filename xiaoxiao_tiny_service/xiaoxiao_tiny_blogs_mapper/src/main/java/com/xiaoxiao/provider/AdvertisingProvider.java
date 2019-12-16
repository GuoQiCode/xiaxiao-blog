package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoAdvertising;
import org.apache.ibatis.jdbc.SQL;

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
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/16:18:58
 * @author:shinelon
 * @Describe:
 */
public class AdvertisingProvider
{

    public String update(final XiaoxiaoAdvertising advertising){
        return new SQL(){
            {
                UPDATE("xiaoxiao_advertising");

                if(advertising.getAdvertisingImg() != null && advertising.getAdvertisingImg() != ""){
                    SET("advertising_img = #{advertising.advertisingImg}");
                }
                if(advertising.getAdvertisingAlt() != null && advertising.getAdvertisingAlt() != ""){
                    SET("advertising_alt = #{advertising.advertisingAlt}");
                }
                if(advertising.getAdvertisingRemark() != null && advertising.getAdvertisingRemark() != ""){
                    SET("advertising_remark = #{advertising.advertisingRemark}");
                }
                if(advertising.getAdvertisingLevel() != null && advertising.getAdvertisingLevel() != ""){
                    SET("advertising_level = #{advertising.advertisingLevel}");
                }
                if(advertising.getAdvertisingUrl() != null && advertising.getAdvertisingUrl() != ""){
                    SET("advertising_url = #{advertising.advertisingUrl}");
                }
                WHERE("advertising_id = #{advertising.advertisingId}");
            }
        }.toString();
    }

    public String insert(final XiaoxiaoAdvertising advertising){
        return new SQL(){
            {
                INSERT_INTO("xiaoxiao_advertising");
                if(advertising.getAdvertisingId() != null && advertising.getAdvertisingId() != ""){
                    VALUES("advertising_id", "#{advertising.advertisingId}");
                }
                if(advertising.getAdvertisingImg() != null && advertising.getAdvertisingImg() != ""){
                    VALUES("advertising_img", "#{advertising.advertisingImg}");
                }
                if(advertising.getAdvertisingAlt() != null && advertising.getAdvertisingAlt() != ""){
                    VALUES("advertising_alt", "#{advertising.advertisingAlt}");
                }
                if(advertising.getAdvertisingRemark() != null && advertising.getAdvertisingRemark() != ""){
                    VALUES("advertising_remark", "#{advertising.advertisingRemark}");
                }
                if(advertising.getAdvertisingLevel() != null && advertising.getAdvertisingLevel() != ""){
                    VALUES("advertising_level", "#{advertising.advertisingLevel}");
                }
                if(advertising.getAdvertisingUrl() != null && advertising.getAdvertisingUrl() != ""){
                    VALUES("advertising_url", "#{advertising.advertisingUrl}");
                }
            }
        }.toString();
    }
}
