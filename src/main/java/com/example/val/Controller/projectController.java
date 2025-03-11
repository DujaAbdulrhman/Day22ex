package com.example.val.Controller;
import com.example.val.Api.ApiResponse;
import com.example.val.Model.projectModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/project")
public class projectController {
    ArrayList<projectModel> projects = new ArrayList<>();

    @PostMapping("/add")
    public ApiResponse addProject(@Valid @RequestBody projectModel project) {
        projects.add(project);
        return new ApiResponse("Successfully added project");
    }

    @GetMapping("/get")
    public ArrayList<projectModel> getProject() {
        return projects;
    }

    @PostMapping("/update/{index}")
    public ApiResponse updateProject(@PathVariable int index, @Valid @RequestBody projectModel project) {
        projects.set(index, project);
        return ResponseEntity.status(200).body(new ApiResponse("Successfully updated project")).getBody();
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteProject(@PathVariable int index) {
        projects.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("deleted Successfully")).getBody();

    }

    @PutMapping("/updates")
    public ResponseEntity setstatus(@RequestBody String status) {
        if (status.equalsIgnoreCase("done")) {
            return ResponseEntity.status(200).body("Successfully updated project");
        } else if (status.equalsIgnoreCase("not done")) {
            return ResponseEntity.status(200).body("Successfully updated project");
        }
        return null;
    }

    @GetMapping("/searchtitle")
    public ResponseEntity searchProjectByTitle(@RequestParam String title) {
        ArrayList<projectModel> foundProjects = new ArrayList<>();
        for (projectModel proj : projects) {
            if (proj.getTitle().equalsIgnoreCase(title)) {
               // foundProjects.add(proj);
                return ResponseEntity.status(200).body(foundProjects.add(proj));
            }else return ResponseEntity.status(404).body("Not Found");
        }
        return null;
    }

    @GetMapping("/getByCompany")
    public ResponseEntity<ApiResponse> getProjectsByCompany(@RequestParam String companyName) {
        ArrayList<projectModel> projByCompany = new ArrayList<>();
        for (projectModel proj : projects) {
            if (proj.getCompanyName().equalsIgnoreCase(companyName)) {
                projByCompany.add(proj);
                return ResponseEntity.status(200).body(new ApiResponse("project by company found"));

            }else return ResponseEntity.status(404).body(new ApiResponse("project by company not found"));
        }
        return null;
    }
}
