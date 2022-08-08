package bg.softuni.timeforschool.web;

import bg.softuni.timeforschool.model.entity.CityEntity;
import bg.softuni.timeforschool.model.entity.DistrictEntity;
import bg.softuni.timeforschool.model.entity.SchoolEntity;
import bg.softuni.timeforschool.util.TestDataModels;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class RestSchoolControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TestDataModels testDataModels;

    private SchoolEntity testSchool;
    private CityEntity testCity;
    private DistrictEntity testDistrict;

    @BeforeEach
    void setUp() {
        testCity = testDataModels.createTestCity();
        testDistrict = testDataModels.createTestDistrict(testCity);
        testSchool = testDataModels.createTestSchool(testCity, testDistrict);
    }

    @AfterEach
    void tearDown() {
        testDataModels.cleanUpDatabase();
    }

    @Test
    void testAllSchoolsRestAPI() throws Exception {
        mockMvc.perform(get("/api/schools/")).
                andExpect(status().isOk());
    }

    @Test
    void testSearchWithOutErrorsRestAPI() throws Exception {
        mockMvc.perform(get("/api/schools/{id}", testSchool.getId())).
                andExpect(status().isOk());
    }

    @Test
    void testSearchWithErrorsRestAPI() throws Exception {
        mockMvc.perform(get("/schools/{id}", 30L)).
                andExpect(status().is4xxClientError());
    }
}
