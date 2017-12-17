package getresults;

import exceptions.WrongCityNameException;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.nio.file.Paths;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class testWritingAndReadingFiles {


    @Test
    public void testIfFileIsCreated() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(
                Paths.get("").toAbsolutePath() +"/src/test/java/getresults/test.txt"));
        Data data = new Data();
        data.writeToFile(writer, "a test result");
        File createdFile = new File(Paths.get("").toAbsolutePath() +"/src/test/java/getresults/test.txt");
        assertTrue(createdFile.exists());
        createdFile.delete();
    }


    @Test
    @Ignore
    public void testIfFileIsCreatedMock() {

    }

    @Test
    public void testWritingToFile() throws IOException {
        Data data = new Data();
        BufferedWriter writer = mock(BufferedWriter.class);
        data.writeToFile(writer, "weather report");
        verify(writer).write("weather report");
    }

}
