package bg.softuni.timeforschool.web;

import bg.softuni.timeforschool.model.dto.SchoolDetailDTO;
import bg.softuni.timeforschool.service.SchoolService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/schools")
public class RestSchoolController {

    private final SchoolService schoolService;

    public RestSchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping
    public ResponseEntity<List<SchoolDetailDTO>> getAllBooks() {
        return ResponseEntity.
                ok(schoolService.getAllSchoolsRest());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SchoolDetailDTO> getSchoolById(@PathVariable("id") Long schoolId) {
        Optional<SchoolDetailDTO> schoolOpt = schoolService.findSchoolByID(schoolId);
        if (schoolOpt.isEmpty()) {
            return ResponseEntity.
                    notFound().
                    build();
        } else {
            return ResponseEntity.
                    ok(schoolOpt.get());
        }
    }
}
