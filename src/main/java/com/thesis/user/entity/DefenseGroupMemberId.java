package com.thesis.user.entity;

import java.io.Serializable;

public class DefenseGroupMemberId implements Serializable {

    private Integer groupId;

    private Integer memberId;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getMemberId() {
        return memberId;
    }

    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
}
