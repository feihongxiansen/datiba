package com.dtb.admin.dao;

import com.dtb.entity.Subject;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author lmx
 * @version 1.0.0
 * @create 2019/3/31-20:46
 */
@Repository("subjectAdminMapper")
public interface SubjectMapper {

    /**
     * 根据id查找
     *
     * @param id 主键id
     * @return com.dtb.entity.Subject
     * @author lmx
     * @date 2019/3/31 20:47
     */
    Subject selectById(@Param("id") Integer id);
}
