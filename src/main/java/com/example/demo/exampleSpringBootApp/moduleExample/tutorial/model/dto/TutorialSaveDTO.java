package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto;

import com.example.demo.exampleSpringBootApp.commons.validation.GroupValidationCRUD;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class TutorialSaveDTO extends TutorialDTO {

    @NotEmpty(
            message = "Al menos un autor debe ser informado",
            groups = {
                    Default.class,
                    GroupValidationCRUD.Create.class,
                    GroupValidationCRUD.Update.class
            }
    )
    private Set<Long> autors;

    public TutorialSaveDTO() {
        super();
        this.autors = new HashSet<>();
    }

}
