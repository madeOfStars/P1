package p1.enums;

/**
 *
 * @author Ertjon
 */
public enum ActiveView {

    HOME(null),
    PROJECT_VIEW(ActiveView.HOME),
    RELEASE_VIEW(ActiveView.PROJECT_VIEW),
    REVISION_VIEW(ActiveView.RELEASE_VIEW);

    private ActiveView previousView;

    ActiveView(ActiveView previousView) {
        this.previousView = previousView;
    }

    public ActiveView getPreviousView() {
        return previousView;
    }

    public void setPreviousView(ActiveView previousView) {
        this.previousView = previousView;
    }

}
