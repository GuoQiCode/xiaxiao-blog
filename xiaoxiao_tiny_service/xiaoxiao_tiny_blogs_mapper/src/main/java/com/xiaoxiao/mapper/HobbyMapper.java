package com.xiaoxiao.mapper;

import com.sun.xml.internal.ws.client.ClientSchemaValidationTube;
import com.xiaoxiao.pojo.XiaoxiaoHobby;
import com.xiaoxiao.pojo.vo.XiaoxiaoHobbyVo;
import com.xiaoxiao.provider.HobbyProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import javax.annotation.security.RolesAllowed;
import java.util.List;

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
 * @date:2019/12/4:12:05
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface HobbyMapper
{

    @Select("SELECT * FROM xiaoxiao_hobby")
    List<XiaoxiaoHobby> findAllHobby();

    @Delete("DELETE FROM xiaoxiao_hobby WHERE hobby_id = #{hobbyId}")
    int deleteHobbyById(@Param(("hobbyId")) String hobbyId);

    @InsertProvider(type = HobbyProvider.class,method = "insert")
    int insert(@Param("hobby") XiaoxiaoHobby hobby);

    @Select("SELECT b.user_id,c.hobby_id,c.bobby_name FROM xiaoxiao_users a,xiaoxiao_user_hobby b,xiaoxiao_hobby c WHERE a.user_id = b.user_id AND b.hobby_id= c.hobby_id AND a.user_id = #{userId}")
    List<XiaoxiaoHobbyVo> findUserHobby(@Param("userId") Long userId);


}
