package ex00;

import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignaturesRead {
    private List<Pair<String, String>> listSignature;
    private static final String signPath = "signatures.txt";
    private static final String resPath = "result.txt";

    public SignaturesRead() {
        listSignature = new ArrayList<>();
    }

    private void parseSignature() {
        try {
            FileInputStream signatureFile = new FileInputStream(signPath);
            Scanner in = new Scanner(signatureFile);
            while (in.hasNextLine()) {
                String[] args = in.nextLine().split(",");
                if (args.length == 2) {
                    listSignature.add(new Pair<>(args[0].trim(), args[1].trim()));
                }
            }
            in.close();
        } catch (IOException e) {
            System.err.println("signatures file not found");
            System.exit(-1);
        }
    }

    public List<Pair<String, String>> getListSignature() {
        parseSignature();
        return listSignature;
    }


    public void stringToBytes(String fileName) {
        try (FileInputStream signatureFile = new FileInputStream(fileName)) {
            byte[] nameByte = new byte[8];
            signatureFile.read(nameByte);
            String result = ByteToHex(nameByte);
            printToFile(listSignature, result);
        } catch (IOException e) {
            System.err.println("signatures file not found");
        }
    }

    private String ByteToHex(byte[] arr) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            hex.append(String.format("%02X", arr[i])).append(" ");
        }
        return hex.toString();
    }

    private void printToFile(List<Pair<String, String>> signList, String bytes) {
        try (FileOutputStream resultFile = new FileOutputStream(resPath, true)) {
            for (int i = 0; i < signList.size(); i++) {
                if (bytes.contains(signList.get(i).getValue())) {
                    resultFile.write(signList.get(i).getKey().getBytes());
                    resultFile.write('\n');
                    System.out.println("PROCESSED");
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Results file not found");
            System.exit(-1);
        }
    }
}

