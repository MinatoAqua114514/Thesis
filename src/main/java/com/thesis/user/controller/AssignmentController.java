package com.thesis.user.controller;

import com.thesis.common.response.ApiResponse;
import com.thesis.user.dto.DefenseDTO;
import com.thesis.user.entity.DefenseGroup;
import com.thesis.user.entity.DefenseGroupMember;
import com.thesis.user.service.AssignmentService;
import com.thesis.user.vo.AdvisorStudentsVo;
import com.thesis.user.vo.DefenseGroupMembersVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignment")
public class AssignmentController {

    @Autowired
    private AssignmentService assignmentService;

    /**
     * 为指导老师添加分配学生
     *
     * @param studentId 学生ID
     * @param advisorId 指导老师ID
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping("/assignStudent")
    public ApiResponse<Void> assignStudentToAdvisor(@RequestParam int studentId, @RequestParam int advisorId) {
        try {
            assignmentService.assignStudentToAdvisor(studentId, advisorId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 为指导老师删除分配学生
     *
     * @param studentId 学生ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/removeStudent")
    public ApiResponse<Void> removeStudentFromAdvisor(int studentId) {
        try {
            assignmentService.removeStudentFromAdvisor(studentId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新学生的指导老师
     *
     * @param studentId 学生ID
     * @param newAdvisorId 新指导老师ID
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/updateAdvisor")
    public ApiResponse<Void> updateStudentAdvisor(int studentId, int newAdvisorId) {
        try {
            assignmentService.updateStudentAdvisor(studentId, newAdvisorId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 指导老师获取指导学生的详细信息
     *
     * @param advisorId 指导老师ID
     * @return ApiResponse<List<AdvisorStudentsVo>> 查询操作的响应结果。成功时，返回状态码200及AdvisorStudentsVo列表；失败时，返回状态码404及错误信息
     */
    @GetMapping("/advisorStudents")
    public ApiResponse<List<AdvisorStudentsVo>> findAdvisorStudents(int advisorId) {
        try {
            return ApiResponse.success(assignmentService.findAdvisorStudents(advisorId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 获取所有指导老师的学生信息
     *
     * @return ApiResponse<List<AdvisorStudentsVo>> 查询操作的响应结果。成功时，返回状态码200及AdvisorStudentsVo列表；失败时，返回状态码404及错误信息
     */
    @GetMapping("/allAdvisorStudents")
    public ApiResponse<List<AdvisorStudentsVo>> findAllAdvisorStudents() {
        try {
            return ApiResponse.success(assignmentService.findAllAdvisorStudents());
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }








    /**
     * 添加答辩小组
     *
     * @param defenseGroup 答辩小组实体对象，包含答辩小组的详细信息
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping("/addDefenseGroup")
    public ApiResponse<Void> addDefenseGroup(@RequestBody DefenseGroup defenseGroup) {
        try {
            assignmentService.addDefenseGroup(defenseGroup);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除答辩小组
     *
     * @param groupId 答辩小组ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/deleteDefenseGroup")
    public ApiResponse<Void> deleteDefenseGroup(int groupId) {
        try {
            assignmentService.deleteDefenseGroup(groupId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 更新答辩小组的组长
     *
     * @param defenseGroup 答辩小组实体对象，包含答辩小组的详细信息
     * @return ApiResponse<Void> 更新操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PutMapping("/updateDefenseGroup")
    public ApiResponse<Void> updateDefenseGroup(DefenseGroup defenseGroup) {
        try {
            assignmentService.updateDefenseGroup(defenseGroup);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * ID获取答辩小组+组长的信息+组员的信息
     *
     * @param groupId 答辩小组ID
     * @return ApiResponse<DefenseDTO> 查询操作的响应结果。成功时，返回状态码200及DefenseGroup对象；失败时，返回状态码404及错误信息
     */
    @GetMapping("/defenseGroupDetails")
    public ApiResponse<DefenseDTO> findDefenseGroupDetails(int groupId) {
        try {
            return ApiResponse.success(assignmentService.findDefenseGroupWithDetails(groupId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }

    /**
     * 获取所有答辩小组的详细信息
     *
     * @return ApiResponse<List<DefenseDTO>> 查询操作的响应结果。成功时，返回状态码200及DefenseGroup列表；失败时，返回状态码404及错误信息
     */
    @GetMapping("/allDefenseGroups")
    public ApiResponse<List<DefenseDTO>> findAllDefenseGroups() {
        try {
            return ApiResponse.success(assignmentService.findAllDefenseGroups());
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
    }







    /**
     * 添加答辩小组成员
     *
     * @param defenseGroupMember 答辩小组成员实体对象，包含答辩小组成员的详细信息
     * @return ApiResponse<Void> 添加操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @PostMapping("/addMember")
    public ApiResponse<Void> addDefenseGroupMember(@RequestBody DefenseGroupMember defenseGroupMember) {
        try {
            assignmentService.addDefenseGroupMember(defenseGroupMember);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 删除答辩小组成员
     *
     * @param memberId 答辩小组成员ID
     * @return ApiResponse<Void> 删除操作的响应结果。成功时，返回状态码200；失败时，返回状态码404及错误信息
     */
    @DeleteMapping("/deleteMember")
    public ApiResponse<Void> deleteDefenseGroupMember(int groupId, int memberId) {
        try {
            assignmentService.deleteDefenseGroupMember(groupId, memberId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }


    /**
     * 更新答辩小组成员信息。
     *
     * @param newGroupId 新的答辩小组ID。
     * @param memberId 需要更新的成员ID。
     * @return 如果操作成功，返回一个表示成功的ApiResponse对象；如果操作失败，返回包含错误信息的ApiResponse对象。
     */
    @PutMapping("/updateMember")
    public ApiResponse<Void> updateDefenseGroupMember(@RequestParam int newGroupId, @RequestParam int memberId) {
        try {
            assignmentService.updateDefenseGroupMember(newGroupId, memberId);
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }
        return ApiResponse.success(null);
    }

    /**
     * 获取答辩小组的所有成员信息
     *
     * @param groupId 答辩小组ID
     * @return ApiResponse<List<DefenseGroupMembersVo>> 查询操作的响应结果。成功时，返回状态码200及DefenseGroupMembersVo列表；失败时，返回状态码404及错误信息
     */
    @GetMapping("/allMembers")
    public ApiResponse<List<DefenseGroupMembersVo>> findDefenseGroupMembers(int groupId) {
        try {
            return ApiResponse.success(assignmentService.findDefenseGroupMembers(groupId));
        } catch (Exception e) {
            return ApiResponse.error(404, e.getMessage(), null);
        }

    }
}
