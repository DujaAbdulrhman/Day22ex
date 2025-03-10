package com.example.val1.Model;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class event {
    @NotEmpty(message = "id cannot be null!")
    @Size(min = 3, message = "id length must be more than 2")
    private String id;

    @NotEmpty(message = "Description can't be null")
    @Size(min = 16, message = "Description length must be more than 15")
    private String description;

    @Min(value = 26, message = "Capacity must be more than 25")
    private int capacity;

    private LocalDate startDate;
    private LocalDate endDate;
}
