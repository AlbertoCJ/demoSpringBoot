package com.example.demo.exampleSpringBootApp.moduleExample.tutorial.model.dto;

import com.example.demo.exampleSpringBootApp.commons.validation.GroupValidationCRUD;
import com.example.demo.exampleSpringBootApp.commons.validation.customValidators.enums.ValueOfEnum;
import com.example.demo.exampleSpringBootApp.domain.enums.TutorialType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.groups.Default;

@Getter
@Setter
public class TutorialDTO {

    protected Long id;

    @NotBlank(
            message = "Título debe ser informada",
            groups = {
                    Default.class,
                    GroupValidationCRUD.Create.class,
                    GroupValidationCRUD.Update.class
            }
    )
    protected String title;

    protected String desc;

    @NotNull(
            message = "Publicado debe ser informado",
            groups = {
                    GroupValidationCRUD.Update.class
            }
    )
    @Null(
            message = "Publicado no debe ser informado",
            groups = {
                    GroupValidationCRUD.Create.class
            }
    )
    protected Boolean published;

    @NotBlank(
            message = "Tipo tutorial debe ser informado",
            groups = {
                    Default.class,
                    GroupValidationCRUD.Create.class,
                    GroupValidationCRUD.Update.class
            }
    )
    @ValueOfEnum(
            enumClass = TutorialType.class,
            message = "Tipo tutorial no válido",
            groups = {
                    Default.class,
                    GroupValidationCRUD.Create.class,
                    GroupValidationCRUD.Update.class
            }
    )
    protected String tutorialType;

    public TutorialDTO() {
        super();
    }

}
