package com.thesis.user.dto;

import com.thesis.user.vo.DefenseGroupMembersVo;

import java.util.List;

public class DefenseDTO {

    private int groupId;

    private int leaderId;

    private String leaderName;

    private List<DefenseGroupMembersVo> members;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public int getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public List<DefenseGroupMembersVo> getMembers() {
        return members;
    }

    public void setMembers(List<DefenseGroupMembersVo> members) {
        this.members = members;
    }
}
