package com.xiaoxiao.search.controller;

import com.xiaoxiao.search.service.ArticleSolrService;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/13:13:56
 * @author:shinelon
 * @Describe:
 */
@RestController
@RequestMapping(value = "/search_service")
public class SolrController {

    @Autowired
    private ArticleSolrService articleSolrService;

    /**
     * 导入的数据到Solr
     * 
     * @return
     */
    @PostMapping(value = "/importArticleToSolr")
    public Result importArticleToSolr() {
        return this.articleSolrService.importArticleToSolr();
    }


    /**
     * 搜索服务
     * @return
     */
    @PostMapping(value = "/search_article")
    public Result searchArticle(@RequestParam(name = "query") String query,
                                @RequestParam(name = "page",defaultValue = "1")Long page,
                                @RequestParam(name = "rows",defaultValue = "10")Integer rows ){
        if(query != null && query != ""){
            return this.articleSolrService.searchArticle(query,page,rows);
        }
        return Result.error(StatusCode.ERROR,true,Result.MARKED_WORDS_FAULT,null);
    }
}
