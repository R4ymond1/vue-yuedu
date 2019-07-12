package com.gedc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * テーブル: vehicle_client
 */
public class VehicleClient {
    /**
     * recid
     */
    private String recid;

    /**
     * iccid
     */
    private String iccid;

    /**
     * use_authority
     */
    private int useAuthority;

    /**
     * create_user
     */
    @JsonIgnore
    private String createUser;

    /**
     * update_user
     */
    @JsonIgnore
    private String updateUser;

    public String getRecid() {
        return recid;
    }

    public void setRecid(String recid) {
        this.recid = recid;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public int getUseAuthority() {
        return useAuthority;
    }

    public void setUseAuthority(int useAuthority) {
        this.useAuthority = useAuthority;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }
}