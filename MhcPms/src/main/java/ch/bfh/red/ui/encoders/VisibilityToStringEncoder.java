package ch.bfh.red.ui.encoders;


import ch.bfh.red.backend.models.SessionType;
import ch.bfh.red.backend.models.Visibility;
import com.vaadin.flow.templatemodel.ModelEncoder;

public class VisibilityToStringEncoder implements ModelEncoder<Visibility, String>{

    @Override
    public Visibility decode(String presentationValue){

        try{
            Visibility visibility = Visibility.valueOf(presentationValue.toUpperCase());
        }catch (IllegalArgumentException e) {}
        return null;



    }

    @Override
    public String encode(Visibility modelValue)
    {
        return modelValue == null ? null : modelValue.getCode();
    }
}

