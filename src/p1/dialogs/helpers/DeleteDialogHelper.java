package p1.dialogs.helpers;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import p1.components.EnhancedJCheckBox;
import p1.enums.FontEnum;
import p1.interfaces.Identifiable;

/**
 *
 * @author Ertjon
 * @param <E>
 */
public class DeleteDialogHelper<E extends Identifiable> extends DialogHelper{

    private List<E> data;
    
    public DeleteDialogHelper(JFrame frame, String title){
        super(frame, title);
    }
    
    public DeleteDialogHelper(JFrame frame, String title, List<E> data){
        this(frame, title);
        this.data=data;
    }
    
    protected List<EnhancedJCheckBox> populateList(){
        List<EnhancedJCheckBox> lista=new ArrayList<>();
        for (E el:data) {
            EnhancedJCheckBox tmp=new EnhancedJCheckBox();
            tmp.setFont(FontEnum.HEADER.getFont());
            tmp.setText(el.toString());
            tmp.setId(el.getIdentifier());
            lista.add(tmp);
        }
        return lista;
    }

    public List<E> getData() {
        return data;
    }

    public void setData(List<E> data) {
        this.data = data;
    }
}
