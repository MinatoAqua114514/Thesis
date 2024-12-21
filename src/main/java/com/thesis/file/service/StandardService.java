package com.thesis.file.service;

import com.thesis.file.dao.StandardMapper;
import com.thesis.file.entity.ThesisWritingStandard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StandardService {

    @Autowired
    private StandardMapper standardMapper;

    // 提交论文撰写规范名称和文件ID FOR 管理员
    public void addThesisWritingStandard(ThesisWritingStandard Standard) {
        standardMapper.insertThesisWritingStandard(Standard);
    }

    // 删除论文撰写规范 FOR 管理员
    public void deleteThesisWritingStandard(int standardId) {
        if (standardId == 1) {
            throw new RuntimeException("删除失败，ID = 1的规范不可删除");
        }
        if (!standardMapper.existsById(standardId)) {
            throw new RuntimeException("删除失败，ID = " + standardId + "的规范不存在");
        }
        standardMapper.deleteThesisWritingStandard(standardId);
    }

    // 更新论文撰写规范 FOR 管理员
    public void updateThesisWritingStandard(ThesisWritingStandard Standard) {
        if (!standardMapper.existsById(Standard.getStandardId())) {
            throw new RuntimeException("更新失败，ID = " + Standard.getStandardId() + "的规范不存在");
        }
        standardMapper.updateThesisWritingStandard(Standard);
    }

    // 查询论文撰写规范 默认获取ID = 1的规范 FOR 管理员、学生
    public ThesisWritingStandard findThesisWritingStandard() {
        return standardMapper.selectThesisWritingStandard();
    }
}
