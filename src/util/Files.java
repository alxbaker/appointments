package util;

import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Instant;

public class Files {
    private static String filename = "src/files/authentication_log.txt";
    public static void updateLog(User user) throws IOException {
        FileWriter fwriter = new FileWriter(filename, true);
        PrintWriter outputFile = new PrintWriter(fwriter);

        outputFile.println("UserId: " + user.getUserId() + " Username: " + user.getUserName() + " logged in at " + Instant.now());
        outputFile.close();
    }

}
