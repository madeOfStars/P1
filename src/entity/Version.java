package entity;
// Generated Apr 2, 2017 9:11:23 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Version generated by hbm2java
 */
public class Version  implements java.io.Serializable {


     private int id;
     private int versionNumber;
     private Date dateAdded;
     private Set releaseVersions = new HashSet(0);
     private Set versionRevisions = new HashSet(0);

    public Version() {
    }

	
    public Version(int id, int versionNumber) {
        this.id = id;
        this.versionNumber = versionNumber;
    }
    public Version(int id, int versionNumber, Date dateAdded, Set releaseVersions, Set versionRevisions) {
       this.id = id;
       this.versionNumber = versionNumber;
       this.dateAdded = dateAdded;
       this.releaseVersions = releaseVersions;
       this.versionRevisions = versionRevisions;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getVersionNumber() {
        return this.versionNumber;
    }
    
    public void setVersionNumber(int versionNumber) {
        this.versionNumber = versionNumber;
    }
    public Date getDateAdded() {
        return this.dateAdded;
    }
    
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    public Set getReleaseVersions() {
        return this.releaseVersions;
    }
    
    public void setReleaseVersions(Set releaseVersions) {
        this.releaseVersions = releaseVersions;
    }
    public Set getVersionRevisions() {
        return this.versionRevisions;
    }
    
    public void setVersionRevisions(Set versionRevisions) {
        this.versionRevisions = versionRevisions;
    }




}


