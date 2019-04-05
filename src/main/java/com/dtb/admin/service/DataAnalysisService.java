package com.dtb.admin.service;

import com.dtb.entity.AnswersWithBLOBs;
import com.dtb.entity.QuestionsWithBLOBs;
import com.dtb.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/4/5-11:06
 */
public interface DataAnalysisService {

    /**
     * 总体统计概况
     *
     * @return java.util.Map<java.lang.String, java.lang.Object>
     * @author lmx
     * @date 2019/4/5 11:19
     */
    Map<String, Object> overallStatistics(String groupType);

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
    List<Map<String, Object>> selectQuestionByStateCount(Date startDate,
                                                         Date endDate,
                                                         String groupType,
                                                         QuestionsWithBLOBs param);

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
    List<Map<String, Object>> selectAnswerByStateCount(Date startDate,
                                                       Date endDate,
                                                       String groupType,
                                                       AnswersWithBLOBs param);

    /**
     * 统计用户数据
     *
     * @param user 查询参数
     * @return java.util.List<java.util.Map < java.lang.String, java.lang.Object>>
     * @author lmx
     * @date 2019/4/5 22:25
     */
    List<Map<String, Object>> selectUserCount(User user);
}
