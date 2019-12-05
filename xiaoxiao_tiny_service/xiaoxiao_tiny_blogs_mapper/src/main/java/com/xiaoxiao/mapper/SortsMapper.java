package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoSorts;
import com.xiaoxiao.pojo.XiaoxiaoUsers;
import com.xiaoxiao.pojo.vo.XiaoxiaoSortsVo;
import com.xiaoxiao.provider.SortsProvider;
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
 * @project_name:xiaoxiao_blogs
 * @date:2019/11/26:17:03
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface SortsMapper
{
    @Select("SELECT a.sort_name,b.sum FROM xiaoxiao_sorts a,(SELECT  COUNT(*) as sum,article_bk_sorts_id FROM xiaoxiao_articles  a GROUP BY a.article_bk_sorts_id) b WHERE a.sort_id = b.article_bk_sorts_id")
    List<XiaoxiaoSortsVo> findAllSorts();


    @Select("SELECT * FROM `xiaoxiao_borker`.`xiaoxiao_sorts`")
    List<XiaoxiaoSorts> findAll();


    @Delete("DELETE FROM xiaoxiao_sorts WHERE sort_id = #{sortsId}")
    int delete(@Param("sortsId") Long sortsId);


    @Select("SELECT * FROM `xiaoxiao_borker`.`xiaoxiao_sorts` WHERE sort_id = #{sortId}")
    XiaoxiaoSorts findSortById(@Param("sortId") Long sortId);


    @UpdateProvider(type = SortsProvider.class,method = "update")
    int update(@Param("sorts") XiaoxiaoSorts sorts);


    /**
     *
     * @param sorts
     * @return
     */
    @InsertProvider(type = SortsProvider.class,method = "insert")
    int insert(@Param("sorts") XiaoxiaoSorts sorts);
}
