package cities;

import java.io.*;

public class Cities {

    public static void addFromConsole() {
        String city;
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter writer = new BufferedWriter(new FileWriter(
                    "C:/Users/Dell/Documents/GitHub/IAY0361/src/main/java/cities/input.txt"));

            while ((city = input.readLine()) != null && city.length() != 0) {
                writer.write(city + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
