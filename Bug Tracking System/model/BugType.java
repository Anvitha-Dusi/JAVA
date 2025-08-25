package model;

public class BugType {
    private int bugCode;
    private String bugCategory;
    private String bugSeverity;

    public BugType() {}

    public BugType(int bugCode, String bugCategory, String bugSeverity) {
        this.bugCode = bugCode;
        this.bugCategory = bugCategory;
        this.bugSeverity = bugSeverity;
    }

    public int getBugCode() { return bugCode; }
    public void setBugCode(int bugCode) { this.bugCode = bugCode; }

    public String getBugCategory() { return bugCategory; }
    public void setBugCategory(String bugCategory) { this.bugCategory = bugCategory; }

    public String getBugSeverity() { return bugSeverity; }
    public void setBugSeverity(String bugSeverity) { this.bugSeverity = bugSeverity; }
}
