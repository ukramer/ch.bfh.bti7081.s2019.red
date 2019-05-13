package ch.bfh.red.ui.presenters;

import ch.bfh.red.ui.views.EditPatientView;
import ch.bfh.red.ui.views.ListPatientView;

public class PatientPresenter implements EditPatientView.EditPatientViewListener, ListPatientView.ListPatientViewListener {
    EditPatientView editPatientView;
    ListPatientView listPatientView;


    public PatientPresenter(EditPatientView editPatientView){
        this.editPatientView = editPatientView;
        editPatientView.addListener(this);
    }

    public PatientPresenter(ListPatientView listPatientView){
        this.listPatientView = listPatientView;
        listPatientView.addListener(this);
    }

}
