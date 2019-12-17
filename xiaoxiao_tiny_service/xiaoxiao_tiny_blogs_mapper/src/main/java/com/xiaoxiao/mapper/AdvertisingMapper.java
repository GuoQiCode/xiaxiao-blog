package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoAdvertising;
import com.xiaoxiao.provider.AdvertisingProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
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
 * @date:2019/12/16:18:53
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface AdvertisingMapper
{

    /**
     * 查询全部广告信息
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_advertising")
    List<XiaoxiaoAdvertising> findAllAdvertising();


    /**
     * 删除
     * @param advertisingId
     * @return
     */
    @Delete("DELETE from xiaoxiao_advertising where advertising_id = #{advertisingId}")
    int deleteAdvertising(@Param("advertisingId")String advertisingId);


    /**
     * 插入
     * @param advertising
     * @return
     */
    @InsertProvider(type = AdvertisingProvider.class,method = "insert")
    int insert(@Param("advertising") XiaoxiaoAdvertising advertising);


    /**
     *根据DI查询
     * @param advertisingId
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_advertising WHERE advertising_id = #{advertisingId}")
    XiaoxiaoAdvertising findAdvertisingById(@Param("advertisingId")String advertisingId);


    /**
     * 修改
     * @param advertising
     * @return
     */
    @UpdateProvider(type =AdvertisingProvider.class ,method = "update")
    int update(@Param("advertising") XiaoxiaoAdvertising advertising);


    /**
     * 修改等级
     * @param advertisingId
     */
    @Update("update xiaoxiao_advertising set advertising_level = 1 where  advertising_id = #{advertisingId}")
    int onto(@Param("advertisingId") String advertisingId);

}
