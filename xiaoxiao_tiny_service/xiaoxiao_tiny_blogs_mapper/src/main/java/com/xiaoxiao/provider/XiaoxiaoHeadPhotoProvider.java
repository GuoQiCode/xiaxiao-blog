package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoHeadPhoto;
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
 * @date:2019/12/22:21:42
 * @author:shinelon
 * @Describe:
 */
public class XiaoxiaoHeadPhotoProvider
{


    public String update(final XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto){
        return new SQL(){
            {
                UPDATE("xiaoxiao_head_photo");
                if(xiaoxiaoHeadPhoto.getHeadUrl() != null && xiaoxiaoHeadPhoto.getHeadUrl() !=""){
                    SET("head_url = #{xiaoxiaoHeadPhoto.headUrl}");
                }
                if(xiaoxiaoHeadPhoto.getHeadType() != null && xiaoxiaoHeadPhoto.getHeadType() !=""){
                    SET("head_type = #{xiaoxiaoHeadPhoto.headType}");
                }
                if(xiaoxiaoHeadPhoto.getHeadDate() != null){
                    SET("head_date = #{xiaoxiaoHeadPhoto.headDate}");
                }
                WHERE("head_id = #{xiaoxiaoHeadPhoto.headId}");
            }
        }.toString();
    }


    public String insert(final XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto){
        return new SQL(){
            {
                INSERT_INTO("xiaoxiao_head_photo");
                if(xiaoxiaoHeadPhoto.getHeadId() != null && xiaoxiaoHeadPhoto.getHeadId() !=""){
                    VALUES("head_id","#{xiaoxiaoHeadPhoto.headId}");
                }
                if(xiaoxiaoHeadPhoto.getHeadUrl() != null && xiaoxiaoHeadPhoto.getHeadUrl() !=""){
                    VALUES("head_url","#{xiaoxiaoHeadPhoto.headUrl}");
                }
                if(xiaoxiaoHeadPhoto.getHeadType() != null && xiaoxiaoHeadPhoto.getHeadType() !=""){
                    VALUES("head_type","#{xiaoxiaoHeadPhoto.headType}");
                }
                if(xiaoxiaoHeadPhoto.getHeadDate() != null){
                    VALUES("head_date","#{xiaoxiaoHeadPhoto.headDate}");
                }
            }
        }.toString();
    }
}
