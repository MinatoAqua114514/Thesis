package com.thesis.user.vo;

import jakarta.persistence.*;

@Entity
@Table(name = "defense_group_members")
public class DefenseGroupMembersVo {
    @Id
    @Column(name = "group_id")
    private Integer groupId;

    @Column(name = "leader_name")
    private String leaderName;

    @Column(name = "member_id")
    private Integer memberId;

    @Column(name = "member_name")
    private String memberName;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }
}

