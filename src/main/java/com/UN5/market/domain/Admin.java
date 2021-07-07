package com.UN5.market.domain;

import java.util.List;

public class Admin {
    private int adminId;
    private String adminname;
    private String admincorreo;
    private String admincontrasenia;
    private String adminconfcontrasenia;
    private String adminrole;

    public Admin(int adminId, String adminname, String admincorreo, String encode, String adminconfcontrasenia, String adminrole) {
    }

    public Admin(String admincorreo) {
        this.admincorreo = admincorreo;
    }

    public Admin() {

    }

    //private List<AdminRest> admin;

    /*public List<AdminRest> getAdmin() {
        return admin;
    }

    public void setAdmin(List<AdminRest> admin) {
        this.admin = admin;
    }*/

    public String getAdminconfcontrasenia() {
        return adminconfcontrasenia;
    }

    public void setAdminconfcontrasenia(String adminconfcontrasenia) {
        this.adminconfcontrasenia = adminconfcontrasenia;
    }

    public String getAdminrole() {
        return adminrole;
    }

    public void setAdminrole(String adminrole) {
        this.adminrole = adminrole;
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
    }

    public String getAdmincorreo() {
        return admincorreo;
    }

    public void setAdmincorreo(String admincorreo) {
        this.admincorreo = admincorreo;
    }

    public String getAdmincontrasenia() {
        return admincontrasenia;
    }

    public void setAdmincontrasenia(String admincontrasenia) {
        this.admincontrasenia = admincontrasenia;
    }

}
