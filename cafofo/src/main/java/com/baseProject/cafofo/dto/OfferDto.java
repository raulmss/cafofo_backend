package com.baseProject.cafofo.dto;

import com.baseProject.cafofo.entity.*;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OfferDto {
    private Long id;
    private OfferStatus offerStatus;
    private LocalDateTime offerDate;





}
