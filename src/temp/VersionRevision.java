package temp;
// Generated Apr 2, 2017 9:29:14 AM by Hibernate Tools 4.3.1



/**
 * VersionRevision generated by hbm2java
 */
public class VersionRevision  implements java.io.Serializable {


     private int id;
     private Revision revision;
     private Version version;

    public VersionRevision() {
    }

	
    public VersionRevision(int id) {
        this.id = id;
    }
    public VersionRevision(int id, Revision revision, Version version) {
       this.id = id;
       this.revision = revision;
       this.version = version;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Revision getRevision() {
        return this.revision;
    }
    
    public void setRevision(Revision revision) {
        this.revision = revision;
    }
    public Version getVersion() {
        return this.version;
    }
    
    public void setVersion(Version version) {
        this.version = version;
    }




}


