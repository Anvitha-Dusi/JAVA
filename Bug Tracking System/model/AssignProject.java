package model;

public class AssignProject {
    private int assignID;
    private int projectID;
    private int empCode;
    
    public AssignProject() {}
    
    public AssignProject(int assignID, int projectID, int empCode) {
        this.assignID = assignID;
        this.projectID = projectID;
        this.empCode = empCode;
    }
    
    public int getAssignID() { return assignID; }
    public void setAssignID(int assignID) { this.assignID = assignID; }
    
    public int getProjectID() { return projectID; }
    public void setProjectID(int projectID) { this.projectID = projectID; }
    
    public int getEmpCode() { return empCode; }
    public void setEmpCode(int empCode) { this.empCode = empCode; }
}