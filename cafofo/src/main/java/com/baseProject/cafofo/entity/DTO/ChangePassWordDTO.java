package com.baseProject.cafofo.entity.DTO;

import lombok.Data;

@Data
public class ChangePassWordDTO {
    private String email;
    private String secretAnswer;
    private String password;
}
