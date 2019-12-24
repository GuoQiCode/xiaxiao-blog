package com.xiaoxiao.provider;

import com.xiaoxiao.pojo.XiaoxiaoNotice;
import org.apache.ibatis.jdbc.SQL;

/**
 * _ooOoo_ o8888888o 88" . "88 (| -_- |) O\ = /O ____/`---'\____ .' \\| |// `. / \\||| : |||// \ / _||||| -:- |||||- \ |
 * | \\\ - /// | | | \_| ''\---/'' | | \ .-\__ `-` ___/-. / ___`. .' /--.--\ `. . __ ."" '< `.___\_<|>_/___.' >'"". | |
 * : `- \`.;`\ _ /`;.`/ - ` : | | \ \ `-. \_ __\ /__ _/ .-` / / ======`-.____`-.___\_____/___.-`____.-'====== `=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 佛祖保佑 永无BUG 佛曰: 写字楼里写字间，写字间里程序员； 程序人员写程序，又拿程序换酒钱。 酒醒只在网上坐，酒醉还来网下眠；
 * 酒醉酒醒日复日，网上网下年复年。 但愿老死电脑间，不愿鞠躬老板前； 奔驰宝马贵者趣，公交自行程序员。 别人笑我忒疯癫，我笑自己命太贱； 不见满街漂亮妹，哪个归得程序员？
 *
 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/24:16:28
 * @author:shinelon
 * @Describe:
 */
public class NoticeProvider {

    public String update(final XiaoxiaoNotice notice) {
        return new SQL() {
            {
                UPDATE("xiaoxiao_notice");

                if (notice.getTitle() != null && notice.getTitle() != "") {
                    SET("title = #{notice.title}");
                }
                if (notice.getContent() != null && notice.getContent() != "") {
                    SET("content = #{notice.content}");
                }
                if (notice.getDate() != null) {
                    SET("date = #{notice.date}");
                }
                if (notice.getType() != null && notice.getType() != "") {
                    SET("type = #{notice.type}");
                }
                if (notice.getAuthor() != null && notice.getAuthor() != "") {
                    SET("author = #{notice.author}");
                }
                WHERE("id = #{notice.id}");
            }
        }.toString();
    }

    public String insert(final XiaoxiaoNotice notice) {
        return new SQL() {
            {
                INSERT_INTO("xiaoxiao_notice");
                if (notice.getId() != null && notice.getId() != "") {
                    VALUES("id", "#{notice.id}");
                }
                if (notice.getTitle() != null && notice.getTitle() != "") {
                    VALUES("title", "#{notice.title}");
                }
                if (notice.getContent() != null && notice.getContent() != "") {
                    VALUES("content", "#{notice.content}");
                }
                if (notice.getDate() != null) {
                    VALUES("date", "#{notice.date}");
                }
                if (notice.getType() != null && notice.getType() != "") {
                    VALUES("type", "#{notice.type}");
                }
                if (notice.getAuthor() != null && notice.getAuthor() != "") {
                    VALUES("author", "#{notice.author}");
                }
            }
        }.toString();
    }
}
