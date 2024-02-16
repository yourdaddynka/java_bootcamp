package ex00;

import javafx.util.Pair;

import java.io.File;
import java.util.List;
import java.util.Scanner;

public class RunMenu {
    SignaturesRead signaturesRead;
    Scanner in;

    public RunMenu() {
        signaturesRead = new SignaturesRead();
        in = new Scanner(System.in);
    }

    private void closedScan(String flag) {
        if (flag.equals("42")) {
            in.close();
            System.exit(0);
        }
    }

    public void start() {
        List<Pair<String, String>> temp = signaturesRead.getListSignature();
        while (true) {
            String numAndg = in.nextLine().trim();
            closedScan(numAndg);
            if (fileIsExists(numAndg)) {
                signaturesRead.stringToBytes(numAndg);
            } else System.err.println("Incorrect filename");
        }
    }

    private Boolean fileIsExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
