package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoImg;
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
 * @date:2019/12/1:17:16
 * @author:shinelon
 * @Describe:
 */
public class ImgProvider
{


    public String update(final XiaoxiaoImg img){
        return new SQL(){
            {
                UPDATE("xiaoxiao_img");
                if(img.getImgAddr() != null && img.getImgAddr() != "")SET("img_addr = #{img.imgAddr}");
                if(img.getImgDesc() != null && img.getImgDesc()!="")SET("img_desc = #{img.imgDesc}");
                WHERE("img_id = #{img.imgId}");
            }
        }.toString();
    }



    public String insert(final XiaoxiaoImg img){
        return new SQL(){
            {
                INSERT_INTO("xiaoxiao_img");
                if(img.getImgId() != null && img.getImgId() != "")VALUES("img_id","#{img.imgId}");
                if(img.getImgAddr() != null && img.getImgAddr() != "") VALUES("img_addr", "#{img.imgAddr}");
                if(img.getImgUploadTime() !=null)VALUES("img_upload_time", "#{img.imgUploadTime}");
                if(img.getImgDesc() != null && img.getImgDesc() != "")VALUES("img_desc", "#{img.imgDesc}");
            }
        }.toString();
    }
}
