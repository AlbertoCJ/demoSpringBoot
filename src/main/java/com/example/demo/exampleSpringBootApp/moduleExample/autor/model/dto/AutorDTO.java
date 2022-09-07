package com.example.demo.exampleSpringBootApp.moduleExample.autor.model.dto;

import com.example.demo.exampleSpringBootApp.commons.validation.GroupValidationCRUD;
import com.example.demo.exampleSpringBootApp.commons.validation.customValidators.enums.ValueOfEnum;
import com.example.demo.exampleSpringBootApp.domain.enums.AutorType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.groups.Default;

@Getter
@Setter
public class AutorDTO {

    private Long id;

    @NotBlank(
            message = "Nombre debe ser informada",
            groups = {
                    Default.class,
                    GroupValidationCRUD.Create.class,
                    GroupValidationCRUD.Update.class
            }
    )
    private String name;

    private String surname1;

    private String surname2;

    @NotBlank(
            message = "Tipo autor debe ser informado",
            groups = {
                    Default.class,
                    GroupValidationCRUD.Create.class,
                    GroupValidationCRUD.Update.class
            }
    )
    @ValueOfEnum(
            enumClass = AutorType.class,
            message = "Tipo autor no válido",
            groups = {
                    Default.class,
                    GroupValidationCRUD.Create.class,
                    GroupValidationCRUD.Update.class
            }
    )
    private String autorType;

    private boolean deleted;

    public AutorDTO() {
        super();
    }

    public AutorDTO(Long id, String name, String surname1, String surname2, AutorType autorType) {
        this.id = id;
        this.name = name;
        this.surname1 = surname1;
        this.surname2 = surname2;
        this.autorType = autorType.name();
    }
}
