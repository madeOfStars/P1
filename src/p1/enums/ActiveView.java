package p1.enums;

/**
 *
 * @author Ertjon
 */
public enum ActiveView {

    PROJECT_VIEW(null),
    RELEASE_VIEW(ActiveView.PROJECT_VIEW);

    private ActiveView previousViw;

    ActiveView(ActiveView previousView) {
        this.previousViw = previousView;
    }

    public ActiveView getPreviousViw() {
        return previousViw;
    }

    public void setPreviousViw(ActiveView previousViw) {
        this.previousViw = previousViw;
    }

}
