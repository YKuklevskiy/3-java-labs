package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.CourseInfo;
import lab2.model.CourseInstance;

import java.io.File;
import java.io.IOException;

public class CourseDataReader {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CourseDataReader() {
        objectMapper.findAndRegisterModules();
    }

    public CourseInfo[] readCourseInfoData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/courseInfos.json"), CourseInfo[].class);
    }

    public CourseInstance[] readCourseInstanceData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/courseInstances.json"), CourseInstance[].class);
    }
}
