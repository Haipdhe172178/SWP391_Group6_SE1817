/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

/**
 *
 * @author huyca
 */
public class Account {
    private int accountId;
    private String fullName;
    private String userName;
    private String passWord;
    private String gender;
    private String email;
    private String phoneNumber;
    private String address;
    private int roleId;
    private String imgAccount;
    private int status;

    public Account() {
    }

    public Account(int accountId, String fullName, String userName, String passWord, String gender, String email, String phoneNumber, String address, int roleId, String imgAccount, int status) {
        this.accountId = accountId;
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.roleId = roleId;
        this.imgAccount = imgAccount;
        this.status = status;
    }

    

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getImgAccount() {
        return imgAccount;
    }

    public void setImgAccount(String imgAccount) {
        this.imgAccount = imgAccount;
    }

    @Override
    public String toString() {
        return "Account{" + "accountId=" + accountId + ", fullName=" + fullName + ", userName=" + userName + ", passWord=" + passWord + ", gender=" + gender + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address + ", roleId=" + roleId + ", imgAccount=" + imgAccount + ", status=" + status + '}';
    }
    
}
