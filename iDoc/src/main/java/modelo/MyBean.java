package modelo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped

public class MyBean {

    private boolean showDo = true;

    public void doAction() {
        showUndo = false;
    }

    public void undoAction() {
        showDo = false;
    }

    public boolean isShowDo() {
        return showDo;
    }

    public void setShowDo(boolean showDo) {
        this.showDo = showDo;
    }

    public boolean isShowUndo() {
        return showUndo;
    }

    public void setShowUndo(boolean showUndo) {
        this.showUndo = showUndo;
    }
    private boolean showUndo = true;
}
