package entity;
// Generated Apr 1, 2017 2:04:21 PM by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Project generated by hbm2java
 */
@Entity
@Table(name = "PROJECT")
public class Project implements java.io.Serializable {

    private int id;
    private String projectName;
    private String path;
    private Date dateAdded;

    public Project() {
    }

    public Project(int id, String projectName, String path) {
        this.id = id;
        this.projectName = projectName;
        this.path = path;
    }

    public Project(int id, String projectName, String path, Date dateAdded) {
        this.id = id;
        this.projectName = projectName;
        this.path = path;
        this.dateAdded = dateAdded;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "PROJECT_NAME")
    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    @Column(name = "PATH")
    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_ADDED")
    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
