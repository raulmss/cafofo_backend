package com.baseProject.cafofo.entity.DTO;

import lombok.Data;

@Data
public class AdminChangesPasswordDTO {
    long userId;
    String newPassword;
}
