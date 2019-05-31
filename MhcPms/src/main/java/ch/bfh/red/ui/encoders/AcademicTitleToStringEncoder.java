package ch.bfh.red.ui.encoders;

import ch.bfh.red.backend.models.AcademicTitle;
import com.vaadin.flow.templatemodel.ModelEncoder;


public class AcademicTitleToStringEncoder implements ModelEncoder<AcademicTitle, String> {

    @Override
    public AcademicTitle decode(String presentationValue){
        try {
            AcademicTitle title = AcademicTitle.valueOf(presentationValue.toUpperCase());
        } catch (IllegalArgumentException e) {}
        return null;

    }
    @Override
    public String encode(AcademicTitle modelValue)
    {
        return modelValue == null ? null : modelValue.getCode();
    }
}



