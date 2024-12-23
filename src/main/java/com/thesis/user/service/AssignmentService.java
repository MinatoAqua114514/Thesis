package com.thesis.user.service;

import com.thesis.user.dao.AssignmentMapper;
import com.thesis.user.dto.DefenseDTO;
import com.thesis.user.entity.DefenseGroup;
import com.thesis.user.entity.DefenseGroupMember;
import com.thesis.user.vo.AdvisorStudentsVo;
import com.thesis.user.vo.DefenseGroupMembersVo;
import com.thesis.user.vo.DefenseLeaderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AssignmentService {

    @Autowired
    private AssignmentMapper assignmentMapper;

    /* 1. 指导学生的学生分配*/
    // 为指导老师添加分配学生 FOR 管理员
    public void assignStudentToAdvisor(int studentId, int advisorId) {
        if (assignmentMapper.selectAdvisorStudents(advisorId).size() >= 5) {
            throw new RuntimeException("当前指导老师已经达到最大指导学生数目");
        }
        // 学生已被分配
        if (assignmentMapper.existsAdvisor(studentId)) {
            throw new RuntimeException("当前学生已经被分配给指导老师");
        }
        assignmentMapper.assignStudentToAdvisor(studentId, advisorId);
    }

    // 为指导老师删除分配学生 FOR 管理员
    public void removeStudentFromAdvisor(int studentId) {
        if (!assignmentMapper.existsAdvisor(studentId)) {
            throw new RuntimeException("当前学生未被分配给指导老师");
        }
        assignmentMapper.removeStudentFromAdvisor(studentId);
    }

    // 更新学生的指导老师 FOR 管理员
    public void updateStudentAdvisor(int studentId, int newAdvisorId) {
        if (assignmentMapper.selectAdvisorStudents(newAdvisorId).size() >= 5) {
            throw new RuntimeException("当前指导老师已经达到最大指导学生数目");
        }
        assignmentMapper.updateStudentAdvisor(studentId, newAdvisorId);
    }

    // 指导老师获取指导学生的详细信息 FOR 指导老师
    public List<AdvisorStudentsVo> findAdvisorStudents(int advisorId) {
        return assignmentMapper.selectAdvisorStudents(advisorId);
    }

    // 获取所有指导老师的学生信息 FOR 管理员
    public List<AdvisorStudentsVo> findAllAdvisorStudents() {
        return assignmentMapper.selectAllAdvisorStudents();
    }


    /* 2. 答辩小组 */
    // 添加答辩小组 FOR 管理员
    public void addDefenseGroup(DefenseGroup defenseGroup) {
        // 答辩小组已存在
        if (assignmentMapper.selectDefenseGroup(defenseGroup.getGroupId()) != null) {
            throw new RuntimeException("当前答辩小组已存在");
        }
        // 组长已存在
        if (assignmentMapper.selectDefenseGroup(defenseGroup.getLeaderId()) != null) {
            throw new RuntimeException("当前组长已存在");
        }
        assignmentMapper.insertDefenseGroup(defenseGroup);
    }

    // 删除答辩小组 FOR 管理员
    public void deleteDefenseGroup(int groupId) {
        if (assignmentMapper.selectDefenseGroup(groupId) == null) {
            throw new RuntimeException("当前答辩小组不存在");
        }
        assignmentMapper.deleteDefenseGroup(groupId);
    }

    // 更新答辩小组的组长 FOR 管理员
    public void updateDefenseGroup(DefenseGroup defenseGroup) {
        if (assignmentMapper.selectDefenseGroup(defenseGroup.getGroupId()) == null) {
            throw new RuntimeException("当前答辩小组不存在");
        }
        if (assignmentMapper.selectDefenseGroup(defenseGroup.getLeaderId()) != null) {
            throw new RuntimeException("当前组长已存在");
        }
        assignmentMapper.updateDefenseGroup(defenseGroup);
    }

    // ID获取答辩小组+组长的信息+组员的信息 FOR 管理员、组长
    public DefenseDTO findDefenseGroupWithDetails(int groupId) {
        // 查询答辩小组的组长信息
        DefenseLeaderVo leader = assignmentMapper.selectDefenseGroup(groupId);
        if (leader == null) {
            throw new NoSuchElementException("答辩小组ID为" + groupId + "的记录不存在。");
        }

        // 查询该答辩小组的所有组员信息
        List<DefenseGroupMembersVo> members = assignmentMapper.selectDefenseGroupMembers(groupId);

        // 创建并填充DefenseDTO对象
        DefenseDTO defenseDTO = new DefenseDTO();
        defenseDTO.setGroupId(groupId);
        defenseDTO.setLeaderId(leader.getLeaderId());
        defenseDTO.setLeaderName(leader.getLeaderName()); // 假设DefenseLeaderVo有getLeaderName()方法提供组长姓名
        defenseDTO.setMembers(members); // 设置组员列表

        return defenseDTO;
    }

    // 获取所有答辩小组的信息 FOR 管理员
    public List<DefenseDTO> findAllDefenseGroups() {
        List<DefenseDTO> defenseGroups = assignmentMapper.selectAllDefenseGroups();
        for (DefenseDTO defenseGroup : defenseGroups) {
            List<DefenseGroupMembersVo> members = assignmentMapper.selectDefenseGroupMembers(defenseGroup.getGroupId());
            defenseGroup.setMembers(members);
        }
        return defenseGroups;
    }


    /* 3. 答辩小组成员分配 */
    // 添加答辩小组的成员 FOR 管理员
    public void addDefenseGroupMember(DefenseGroupMember defenseGroupMember) {
        if (assignmentMapper.selectDefenseGroup(defenseGroupMember.getGroupId()) == null) {
            throw new RuntimeException("当前答辩小组不存在");
        }
        if (assignmentMapper.selectDefenseGroup(defenseGroupMember.getMemberId()) != null) {
            throw new RuntimeException("当前成员已存在");
        }
        assignmentMapper.insertDefenseGroupMember(defenseGroupMember);
    }

    // 删除答辩小组的成员 FOR 管理员
    public void deleteDefenseGroupMember(int groupId, int memberId) {
        if (assignmentMapper.selectDefenseGroup(groupId) == null) {
            throw new RuntimeException("当前答辩小组不存在");
        }
        if (!assignmentMapper.existsDefenseGroupMember(groupId, memberId)) {
            throw new RuntimeException("当前成员不存在");
        }
        if (assignmentMapper.selectDefenseGroup(groupId).getLeaderId() == memberId) {
            throw new RuntimeException("当前成员为组长，不能删除");
        }
        assignmentMapper.deleteDefenseGroupMember(groupId, memberId);
    }

    // 为小组成员重新分配小组 FOR 管理员
    public void updateDefenseGroupMember(Integer newGroupId, Integer memberId) {
        if (assignmentMapper.selectDefenseGroup(newGroupId) == null) {
            throw new RuntimeException("当前答辩小组不存在");
        }
        assignmentMapper.updateDefenseGroupMember(newGroupId, memberId);
    }

    // 查看多个答辩小组的成员信息 FOR 组长、管理员
    public List<DefenseGroupMembersVo> findDefenseGroupMembers(int groupId) {
        return assignmentMapper.selectDefenseGroupMembers(groupId);
    }
}
