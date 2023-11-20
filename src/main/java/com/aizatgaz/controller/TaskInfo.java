package com.aizatgaz.controller;

import com.aizatgaz.domain.entity.enums.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class TaskInfo {
    @NotBlank
    private String description;
    @NotNull
    private Status status;
}
