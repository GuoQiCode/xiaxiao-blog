package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoHeadPhoto;
import com.xiaoxiao.provider.XiaoxiaoHeadPhotoProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
 * @date:2019/12/22:21:28
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface HeadPhotoMapper
{

    /**
     * 查询全部
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_head_photo ORDER BY  head_date")
    List<XiaoxiaoHeadPhoto> findAll();


    /**
     * 查找一个
     * @param xiaoxiaoHeadPhoto
     * @return
     */
    @Select("SELECT * FROM xiaoxiao_head_photo WHERE head_id = #{xiaoxiaoHeadPhoto.headId}")
    XiaoxiaoHeadPhoto findOne(@Param("xiaoxiaoHeadPhoto") XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto);


    /**
     * 删除
     * @param xiaoxiaoHeadPhoto
     * @return
     */
    @Delete("DELETE FROM xiaoxiao_head_photo WHERE head_id = #{xiaoxiaoHeadPhoto.headId}")
    int delete(@Param("xiaoxiaoHeadPhoto") XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto);


    /**
     *插入
     * @param xiaoxiaoHeadPhoto
     * @return
     */
    @InsertProvider(type = XiaoxiaoHeadPhotoProvider.class,method = "insert")
    int insert(@Param("xiaoxiaoHeadPhoto") XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto);


    /**
     * 修改
      * @param xiaoxiaoHeadPhoto
     * @return
     */
    @UpdateProvider(type =XiaoxiaoHeadPhotoProvider.class,method = "update")
    int update(@Param("xiaoxiaoHeadPhoto") XiaoxiaoHeadPhoto xiaoxiaoHeadPhoto);
}
