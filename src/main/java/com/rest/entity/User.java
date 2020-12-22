package com.rest.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    private int id;
    private String fullName;
    private String address;
    private String phone;
    private Region regionSupport;

    public User() {
    }

    public User(int id, String fullName, String address, String phone, Region regionSupport) {
        this.id = id;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.regionSupport = regionSupport;
    }

    public User(String fullName, String address, String phone, Region regionSupport) {
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.regionSupport = regionSupport;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "full_name", nullable = true, length = 100)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 45)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "region_support", nullable = true)
    public Region getRegionSupport() {
        return regionSupport;
    }

    public void setRegionSupport(Region regionSupport) {
        this.regionSupport = regionSupport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (fullName != null ? !fullName.equals(user.fullName) : user.fullName != null) return false;
        if (address != null ? !address.equals(user.address) : user.address != null) return false;
        if (phone != null ? !phone.equals(user.phone) : user.phone != null) return false;
        return regionSupport == user.regionSupport;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (regionSupport != null ? regionSupport.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return
                id +
                        "." + fullName + '\'' +
                        "," + address + '\'' +
                        "," + phone + '\'' +
                        "," + regionSupport + "\n";
    }
}
