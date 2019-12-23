package com.xiaoxiao.component;

import com.xiaoxiao.feign.RedisCacheFeignClient;
import com.xiaoxiao.pojo.XiaoxiaoAdminMessage;
import com.xiaoxiao.pojo.XiaoxiaoMenu;
import com.xiaoxiao.pojo.XiaoxiaoUsers;
import org.springframework.stereotype.Component;

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
 * @date:2019/12/17:10:47
 * @author:shinelon
 * @Describe:服务降级
 */
@Component
public class BackendTinyBlogsFallback implements RedisCacheFeignClient
{
    @Override
    public void insertMenuToRedis(List<XiaoxiaoMenu> menus)
    {

    }

    @Override
    public List<XiaoxiaoMenu> getMenuToRedis()
    {
        return null;
    }

    @Override
    public void insertAdminManagerToRedis(List<XiaoxiaoAdminMessage> messages)
    {

    }

    @Override
    public List<XiaoxiaoAdminMessage> getAdminManagerToRedis()
    {
        return null;
    }

    @Override
    public void deleteArticleSumToRedis()
    {

    }

    @Override
    public void deleteIndexArticleLabel()
    {

    }

    @Override
    public void deleteIndexSortsAllToRedis()
    {

    }

    @Override
    public void deleteAdminManager()
    {

    }

    @Override
    public void updateUserToRedis(XiaoxiaoUsers users)
    {

    }

    @Override
    public void deleteIndexArticle()
    {

    }

    @Override
    public void deleteShowMe()
    {

    }

    @Override
    public void deleteLabelCount()
    {

    }

    @Override
    public void deleteBlogsBySortsToRedis(Long sortId)
    {

    }

    @Override
    public void deleteArticleSortSum(Long sortId)
    {

    }

    @Override
    public void deleteArticleByLabelId(Long labelId)
    {

    }

    @Override
    public void deleteArticleNewRecommend()
    {

    }

    @Override
    public void deleteArticleLabelSum(Long labelId)
    {

    }

    @Override
    public void deleteUserToRedis(String token)
    {

    }

    @Override
    public void deleteArticleArchive()
    {

    }

    @Override
    public void deleteAdvertisingToRedis()
    {

    }

    @Override
    public void deleteHeadPhoto()
    {

    }
}
