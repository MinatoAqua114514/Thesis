package com.thesis.user.entity;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "defense_group_member")
@IdClass(DefenseGroupMemberId.class)
public class DefenseGroupMember implements Serializable {

    @Id
    @Column(name = "group_id")
    private Integer groupId;

    @Id
    @Column(name = "member_id")
    private Integer memberId;

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}