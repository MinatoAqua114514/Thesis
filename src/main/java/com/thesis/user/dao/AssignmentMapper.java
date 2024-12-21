package com.thesis.user.dao;

import com.thesis.user.dto.DefenseDTO;
import com.thesis.user.entity.DefenseGroup;
import com.thesis.user.entity.DefenseGroupMember;
import com.thesis.user.vo.AdvisorStudentsVo;
import com.thesis.user.vo.DefenseGroupMembersVo;
import com.thesis.user.vo.DefenseLeaderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AssignmentMapper {

    /* 1. 指导学生的学生分配*/
    void assignStudentToAdvisor(@Param("studentId") Integer studentId, @Param("advisorId") Integer advisorId);

    void removeStudentFromAdvisor(@Param("studentId") Integer studentId);

    void updateStudentAdvisor(@Param("studentId") Integer studentId, @Param("newAdvisorId") Integer advisorId);

    List<AdvisorStudentsVo> selectAdvisorStudents(@Param("advisorId") Integer advisorId);

    boolean existsAdvisor(@Param("studentId") Integer studentId);

    List<AdvisorStudentsVo> selectAllAdvisorStudents();



    /* 2. 答辩小组 */
    void insertDefenseGroup(DefenseGroup defenseGroup);

    void deleteDefenseGroup(@Param("groupId") Integer groupId);

    void updateDefenseGroup(DefenseGroup defenseGroup);

    DefenseLeaderVo selectDefenseGroup(@Param("groupId") Integer groupId);

    List<DefenseDTO> selectAllDefenseGroups();





    /* 3. 答辩小组成员分配 */
    void insertDefenseGroupMember(DefenseGroupMember defenseGroupMember);

    void deleteDefenseGroupMember(@Param("groupId") Integer groupId, @Param("memberId") Integer memberId);

    void updateDefenseGroupMember(DefenseGroupMember defenseGroupMember);

    List<DefenseGroupMembersVo> selectDefenseGroupMembers(@Param("groupId") Integer groupId);
}
