package lab2.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import lab2.model.Instructor;

import java.io.File;
import java.io.IOException;

public class InstructorDataReader {
    private ObjectMapper objectMapper = new ObjectMapper();

    public Instructor[] readInstructorData() throws IOException {
        return objectMapper.readValue(new File("src/main/resources/instructors.json"), Instructor[].class);
    }
}
