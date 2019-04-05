package com.dtb.admin.dao;

import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/5-11:05
 */
@Repository("dataAnalysisAdminMapper")
public interface DataAnalysisMapper {

    /**
     * 总体统计概况
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author lmx
     * @date 2019/4/5 11:19
     */
    Map<String, Object> overallStatistics(@Param("groupType") String groupType);

    /**
     * 分组查询已解决的问题数量
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param groupType 分组条件
     * @param param     多条件
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author lmx
     * @date 2019/4/5 15:57
     */
    List<Map<String, Object>> selectQuestionByStateCount(@Param("startDate") Date startDate,
                                                         @Param("endDate") Date endDate,
                                                         @Param("groupType") String groupType,
                                                         @Param("param") QuestionsWithBLOBs param);

    /**
     * 分组查询已解决的问题数量
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @param groupType 分组条件
     * @param param     多条件
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author lmx
     * @date 2019/4/5 15:57
     */
    List<Map<String, Object>> selectAnswerByStateCount(@Param("startDate") Date startDate,
                                                       @Param("endDate") Date endDate,
                                                       @Param("groupType") String groupType,
                                                       @Param("param") AnswersWithBLOBs param);

    /**
     * 统计用户数据
     *
     * @param user 查询参数
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author lmx
     * @date 2019/4/5 22:25
     */
    List<Map<String, Object>> selectUserCount(@Param("user") User user);
}
