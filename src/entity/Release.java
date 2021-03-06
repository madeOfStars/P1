package entity;
// Generated Apr 2, 2017 9:11:23 AM by Hibernate Tools 4.3.1

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import p1.interfaces.Identifiable;
import p1.interfaces.Pathable;

/**
 * Release generated by hbm2java
 */
@Entity
@Table(name = "RELEASE")
public class Release implements Serializable, Identifiable, Pathable {

    private int id;
    private int code;
    private Date dateAdded;
    private Integer closed;
    private Date dateClosed;
    private String releaseFolder;
    
    private Project project;
    
    private List<Revision> releaseVersion=new ArrayList<>();

    public Release() {
    }

    public Release(int id, int code) {
        this.id = id;
        this.code = code;
    }

    public Release(int id, int code, Date dateAdded, Integer closed, Date dateClosed) {
        this.id = id;
        this.code = code;
        this.dateAdded = dateAdded;
        this.closed = closed;
        this.dateClosed = dateClosed;
    }

    public Release(int code, Date dateAdded, int closed, Date dateClosed) {
        this.code = code;
        this.dateAdded = dateAdded;
        this.closed = closed;
        this.dateClosed = dateClosed;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "CODE")
    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_ADDED")
    public Date getDateAdded() {
        return this.dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Column(name = "CLOSED")
    public Integer getClosed() {
        return this.closed;
    }

    public void setClosed(Integer closed) {
        this.closed = closed;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE_CLOSED")
    public Date getDateClosed() {
        return this.dateClosed;
    }

    public void setDateClosed(Date dateClosed) {
        this.dateClosed = dateClosed;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "PROJECT_RELEASE", joinColumns = {
        @JoinColumn(name = "RELEASE_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
        @JoinColumn(name = "PROJECT_ID", referencedColumnName = "ID")})
    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    @Fetch(FetchMode.SELECT)
    public List<Revision> getReleaseVersion() {
        return releaseVersion;
    }

    public void setReleaseVersion(List<Revision> releaseVersion) {
        this.releaseVersion = releaseVersion;
    }

    @Column(name = "RELEASE_FOLDER")
    public String getReleaseFolder() {
        return releaseFolder;
    }

    public void setReleaseFolder(String releaseFolder) {
        this.releaseFolder = releaseFolder;
    }

    @Transient
    @Override
    public int getIdentifier() {
        return this.getId();
    }
    
    @Override
    public String toString(){
        return this.getCode()+"";
    }

    @Override
    public String folderPath(int code) {
        return project.folderPath(this.getCode())+"/"+code;
    }

    @Override
    public void renameFolderPath(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
