package com.example.val.Model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class projectModel {
    @NotNull
    @Size(min =2)
    private String id;
    @NotNull
    @Size(min =8)
    private String title;
    @NotNull
    @Size(min = 15)
    private String description;
    @NotNull
    @Size(min=7)
    private String companyName;
    @NotNull
    private String status;
}