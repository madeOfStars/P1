package entity;
// Generated Apr 2, 2017 9:11:23 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Revision generated by hbm2java
 */
public class Revision  implements java.io.Serializable {


     private int id;
     private int revisionNumber;
     private Date dateAdded;
     private Set revisionFiles = new HashSet(0);
     private Set versionRevisions = new HashSet(0);

    public Revision() {
    }

	
    public Revision(int id, int revisionNumber) {
        this.id = id;
        this.revisionNumber = revisionNumber;
    }
    public Revision(int id, int revisionNumber, Date dateAdded, Set revisionFiles, Set versionRevisions) {
       this.id = id;
       this.revisionNumber = revisionNumber;
       this.dateAdded = dateAdded;
       this.revisionFiles = revisionFiles;
       this.versionRevisions = versionRevisions;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public int getRevisionNumber() {
        return this.revisionNumber;
    }
    
    public void setRevisionNumber(int revisionNumber) {
        this.revisionNumber = revisionNumber;
    }
    public Date getDateAdded() {
        return this.dateAdded;
    }
    
    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
    public Set getRevisionFiles() {
        return this.revisionFiles;
    }
    
    public void setRevisionFiles(Set revisionFiles) {
        this.revisionFiles = revisionFiles;
    }
    public Set getVersionRevisions() {
        return this.versionRevisions;
    }
    
    public void setVersionRevisions(Set versionRevisions) {
        this.versionRevisions = versionRevisions;
    }




}


