package com.xiaoxiao.service.backend.impl;

import com.xiaoxiao.feign.BlogsFeignServiceClient;
import com.xiaoxiao.service.backend.AdvertisingService;
import com.xiaoxiao.pojo.XiaoxiaoAdvertising;
import com.xiaoxiao.utils.BackendUploadResult;
import com.xiaoxiao.utils.Result;
import com.xiaoxiao.utils.UploadOSSUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
 * @date:2019/12/16:18:36
 * @author:shinelon
 * @Describe:
 */
@Service
public class AdvertisingServiceImpl implements AdvertisingService
{

    @Value("${ENDPOINT}")
    private String ENDPOINT;

    @Value("${ACCESSKEYSECRET}")
    private String ACCESSKEYSECRET;

    @Value("${ACCESSKEYID}")
    private String ACCESSKEYID;

    @Value("${BUCKETNAME}")
    private String BUCKETNAME;

    @Value("${ADVERTISING_FILE_PATH}")
    private String ADVERTISING_FILE_PATH;

    @Value("${REQUEST_HEAD}")
    private String REQUEST_HEAD;

    @Autowired
    private BlogsFeignServiceClient client;

    @Override
    public Result findAllAdvertising(Integer page, Integer rows)
    {
        return this.client.findAllAdvertising(page,rows);
    }

    @Override
    public Result deleteAdvertising(String advertisingId)
    {
        return this.client.deleteAdvertising(advertisingId);
    }

    @Override
    public Result findAdvertisingById(String advertisingId)
    {
        return this.client.findAdvertisingById(advertisingId);
    }

    @Override
    public Result insert(XiaoxiaoAdvertising advertising)
    {
        return this.client.insert(advertising);
    }

    @Override
    public Result update(XiaoxiaoAdvertising advertising)
    {
        return this.client.update(advertising);
    }


    @Override
    public BackendUploadResult upload(MultipartFile file)
    {
        String  fileName = null;
        try
        {
            fileName = UploadOSSUtils.upload(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET, BUCKETNAME,ADVERTISING_FILE_PATH, file.getInputStream());
            /**
             * 插入数据库文件
             */
            return BackendUploadResult.ok(1, this.REQUEST_HEAD+ADVERTISING_FILE_PATH+fileName);
        }catch (Exception e){

        }
        return BackendUploadResult.error(0,"上传失败");
    }

    @Override
    public Result onto(String advertisingId)
    {
        return this.client.onto(advertisingId);
    }
}
