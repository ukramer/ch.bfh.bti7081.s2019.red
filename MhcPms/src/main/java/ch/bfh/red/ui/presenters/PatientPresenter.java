package ch.bfh.red.ui.presenters;

import ch.bfh.red.ui.views.EditPatientView;

public class PatientPresenter implements EditPatientView.EditPatientViewListener {
    EditPatientView editPatientView;


    PatientPresenter(EditPatientView editPatientView){
        this.editPatientView = editPatientView;
        editPatientView.addListener(this);
    }

}
