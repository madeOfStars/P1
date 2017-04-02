package p1.utils;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Ertjon
 */
public class SessionPackage {
    private Transaction tx;
    private Session session=HibernateUtil.getSessionFactory().openSession();;
    
    public SessionPackage(){}

    public Transaction getTx() {
        tx=session.beginTransaction();
        return tx;
    }

    public void setTx(Transaction tx) {
        this.tx = tx;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }
    
    
}
