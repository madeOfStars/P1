package entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Ertjon
 */
@Entity
@Table(name = "RELEASE_REVISION")
public class ReleaseRevision {
    private int id;
    private Release release;
    private Revision revision;
    
    public ReleaseRevision(){
    }
    
    public ReleaseRevision(int id){
        this.id=id;
    }
    
    public ReleaseRevision(int id, Release release, Revision revision){
        this.id=id;
        this.release=release;
        this.revision=revision;
    }

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JoinColumn(name = "RELEASE_ID")
    public Release getRelease() {
        return release;
    }

    public void setRelease(Release release) {
        this.release = release;
    }

    @JoinColumn(name = "REVISION_ID")
    public Revision getRevision() {
        return revision;
    }

    public void setRevision(Revision revision) {
        this.revision = revision;
    }
    
    
}
