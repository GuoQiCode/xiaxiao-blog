package com.xiaoxiao.mapper;

import com.xiaoxiao.pojo.XiaoxiaoHobby;
import com.xiaoxiao.pojo.vo.XiaoxiaoHobbyVo;
import com.xiaoxiao.provider.HobbyProvider;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import java.util.List;

/**

 * @project_name:xiaoxiao_final_blogs
 * @date:2019/12/4:12:05
 * @author:shinelon
 * @Describe:
 */
@Repository
public interface HobbyMapper {

    @Select("SELECT * FROM xiaoxiao_hobby")
    List<XiaoxiaoHobby> findAllHobby();

    @Delete("DELETE FROM xiaoxiao_hobby WHERE hobby_id = #{hobbyId}")
    int deleteHobbyById(@Param(("hobbyId")) String hobbyId);

    @InsertProvider(type = HobbyProvider.class, method = "insert")
    int insert(@Param("hobby") XiaoxiaoHobby hobby);

    @Select("SELECT b.user_id,c.hobby_id,c.bobby_name FROM xiaoxiao_users a,xiaoxiao_user_hobby b,xiaoxiao_hobby c WHERE a.user_id = b.user_id AND b.hobby_id= c.hobby_id AND a.user_id = #{userId}")
    List<XiaoxiaoHobbyVo> findUserHobby(@Param("userId") Long userId);

}
