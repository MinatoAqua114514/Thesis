package com.thesis.file.dao;

import com.thesis.file.entity.ThesisWritingStandard;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StandardMapper {

    // 提交论文撰写规范名称和文件ID
    void insertThesisWritingStandard(ThesisWritingStandard thesisWritingStandard);

    // 删除论文撰写规范
    void deleteThesisWritingStandard(@Param("standardId") int standardId);

    // 更新论文撰写规范
    void updateThesisWritingStandard(ThesisWritingStandard thesisWritingStandard);

    // 查询论文撰写规范 默认获取ID = 1的规范
    ThesisWritingStandard selectThesisWritingStandard();

    // 根据ID查询论文撰写规范
    boolean existsById(@Param("standardId") int standardId);
}
