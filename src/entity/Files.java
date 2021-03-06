package entity;
// Generated Apr 2, 2017 9:11:23 AM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Files generated by hbm2java
 */
public class Files  implements java.io.Serializable {


     private int id;
     private String name;
     private String filePath;
     private Date dateAdded;
     private Set revisionFiles = new HashSet(0);

    public Files() {
    }

	
    public Files(int id, String name, String filePath) {
        this.id = id;
        this.name = name;
        this.filePath = filePath;
    }
    public Files(int id, String name, String filePath, Date dateAdded, Set revisionFiles) {
       this.id = id;
       this.name = name;
       this.filePath = filePath;
       this.dateAdded = dateAdded;
       this.revisionFiles = revisionFiles;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getFilePath() {
        return this.filePath;
    }
    
    public void setFilePath(String filePath) {
        this.filePath = filePath;
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




}


