package ch.bfh.red.ui.presenters;

import ch.bfh.red.backend.models.Therapist;
import ch.bfh.red.ui.views.DashboardView;

public class DashboardPresenter implements DashboardView.DashboardViewListener {
    private Therapist therapist;
    private DashboardView view;

    public DashboardPresenter(DashboardView view) {
        this.view  = view;

        view.setListener(this);
    }
}
