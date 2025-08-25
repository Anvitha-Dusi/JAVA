package model;

public class Employee {
    private int empCode;
    private String empName;
    private String empEmail;
    private String empPassword;
    private String gender;
    private String dob;
    private long mobileNo;
    private String role;

    // Constructor, getters and setters
    public Employee() {}

    public Employee(int empCode, String empName, String empEmail, String empPassword, String gender, String dob, long mobileNo, String role) {
        this.empCode = empCode;
        this.empName = empName;
        this.empEmail = empEmail;
        this.empPassword = empPassword;
        this.gender = gender;
        this.dob = dob;
        this.mobileNo = mobileNo;
        this.role = role;
    }

    public int getEmpCode() { return empCode; }
    public void setEmpCode(int empCode) { this.empCode = empCode; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }

    public String getEmpPassword() { return empPassword; }
    public void setEmpPassword(String empPassword) { this.empPassword = empPassword; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public long getMobileNo() { return mobileNo; }
    public void setMobileNo(long mobileNo) { this.mobileNo = mobileNo; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}