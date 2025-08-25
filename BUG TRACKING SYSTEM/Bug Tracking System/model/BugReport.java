package model;

public class BugReport {
    private int bugNo;
    private int bugCode;
    private int projectID;
    private int testerCode;
    private int employeeCode;
    private String status;
    private String bugDescription;

    public BugReport() {}

    public BugReport(int bugNo, int bugCode, int projectID, int testerCode, int employeeCode, String status, String bugDescription) {
        this.bugNo = bugNo;
        this.bugCode = bugCode;
        this.projectID = projectID;
        this.testerCode = testerCode;
        this.employeeCode = employeeCode;
        this.status = status;
        this.bugDescription = bugDescription;
    }

    public int getBugNo() { return bugNo; }
    public void setBugNo(int bugNo) { this.bugNo = bugNo; }

    public int getBugCode() { return bugCode; }
    public void setBugCode(int bugCode) { this.bugCode = bugCode; }

    public int getProjectID() { return projectID; }
    public void setProjectID(int projectID) { this.projectID = projectID; }

    public int getTesterCode() { return testerCode; }
    public void setTesterCode(int testerCode) { this.testerCode = testerCode; }

    public int getEmployeeCode() { return employeeCode; }
    public void setEmployeeCode(int employeeCode) { this.employeeCode = employeeCode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBugDescription() { return bugDescription; }
    public void setBugDescription(String bugDescription) { this.bugDescription = bugDescription; }
}