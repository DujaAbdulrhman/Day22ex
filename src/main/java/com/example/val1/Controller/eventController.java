package com.example.val1.Controller;


import com.example.val1.Model.event;
import com.example.val1.ApiRespone.ApiResponse;
import com.example.val1.Model.event;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/event2")
@Validated
public class eventController {
    private final ArrayList<event> events = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addEvent(@Valid @RequestBody event event) {
        events.add(event);
        return new ApiResponse("Event added successfully");
    }

    @GetMapping("/get")
    public ArrayList<event> getEvent() {
        return events;
    }

    @PutMapping("/update/{id}")
    public ApiResponse updateEvent(@PathVariable String id, @Valid @RequestBody event updatedEvent) {
        Optional<event> eventOptional = events.stream().filter(event -> event.getId().equals(id)).findFirst();
        if (eventOptional.isPresent()) {
            event event = eventOptional.get();
            event.setDescription(updatedEvent.getDescription());
            event.setCapacity(updatedEvent.getCapacity());
            event.setStartDate(updatedEvent.getStartDate());
            event.setEndDate(updatedEvent.getEndDate());
            return new ApiResponse("Event updated successfully");
        } else {
            return new ApiResponse("Event not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ApiResponse deleteEvent(@PathVariable String id) {
        events.removeIf(event -> event.getId().equals(id));
        return new ApiResponse("Event deleted successfully");
    }

    @PutMapping("/changecap/{id}")
    public ApiResponse changeCapacity(@PathVariable String id, @RequestParam @Min(26) int capacity) {
        Optional<event> eventOptional = events.stream().filter(event -> event.getId().equals(id)).findFirst();
        if (eventOptional.isPresent()) {
            eventOptional.get().setCapacity(capacity);
            return new ApiResponse("Event capacity updated successfully");
        } else {
            return new ApiResponse("Event not found");
        }
    }

    @GetMapping("/search/{id}")
    public event searchEventById(@PathVariable String id) {
        return events.stream().filter(event -> event.getId().equals(id)).findFirst().orElse(null);
    }
}