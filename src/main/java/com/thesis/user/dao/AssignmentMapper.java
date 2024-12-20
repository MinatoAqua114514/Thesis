package com.thesis.user.dao;

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
    void assignStudentToAdvisor(@Param("studentId") int studentId, @Param("advisorId") int advisorId);

    void removeStudentFromAdvisor(@Param("studentId") int studentId);

    void updateStudentAdvisor(@Param("studentId") int studentId, @Param("newAdvisorId") int advisorId);

    List<AdvisorStudentsVo> selectAdvisorStudents(@Param("advisorId") int advisorId);



    /* 2. 答辩小组 */
    void insertDefenseGroup(DefenseGroup defenseGroup);

    void deleteDefenseGroup(@Param("groupId") int groupId);

    void updateDefenseGroup(DefenseGroup defenseGroup);

    DefenseLeaderVo selectDefenseGroup(@Param("groupId") int groupId);



    /* 3. 答辩小组成员分配 */
    void insertDefenseGroupMember(DefenseGroupMember defenseGroupMember);

    void deleteDefenseGroupMember(@Param("groupId") int groupId, @Param("memberId") int memberId);

    void updateDefenseGroupMember(DefenseGroupMember defenseGroupMember);

    DefenseGroupMembersVo selectDefenseGroupMembers(@Param("groupId") int groupId);
}
