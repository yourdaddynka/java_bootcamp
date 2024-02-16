package ex01;


import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class SimilarityCalculation {
    private Map<String, Integer> words;
    private Vector<Integer> firstWordMap;
    private Vector<Integer> secondWordMap;


    public double allCalculatios(String fileNameOne, String fileNameTwo) {
        createWordMap(fileNameOne, fileNameTwo);
        createVectors(fileNameOne, fileNameTwo);
        double res = calcVector();
        return res;
    }

    private double calcVector(){
        int Numerator = 0;
        double DenominatorA = 0;
        double DenominatorB = 0;
        for(int i = 0; i < words.size();i++){
            Numerator += firstWordMap.get(i) * secondWordMap.get(i);
        }
        for(int i = 0; i < firstWordMap.size();i++){
            DenominatorA += firstWordMap.get(i) * firstWordMap.get(i);
        }
        for(int i = 0; i < secondWordMap.size();i++){
            DenominatorB += secondWordMap.get(i) * secondWordMap.get(i);
        }
        double Denominator = Math.sqrt(DenominatorA)*Math.sqrt(DenominatorB);
        return (double)(Numerator/Denominator);
    }

    private void createVectors(String fileNameOne, String fileNameTwo) {
        firstWordMap = openFileVector(fileNameOne);
        secondWordMap = openFileVector(fileNameTwo);
    }

    private Vector<Integer> openFileVector(String fileName) {
        Vector<Integer> wordCount = new Vector<>(words.size());
        List<String> keys = new ArrayList<>(words.keySet());
        for (int i = 0; i < words.size(); i++) {
            wordCount.add(0);
        }
        try (FileInputStream signatureFile = new FileInputStream(fileName)) {
            Scanner in = new Scanner(signatureFile);
            while (in.hasNextLine()) {
                String[] lineWords = in.nextLine().split("\\s+");
                for(int i = 0; i < lineWords.length;i++){
                    if(words.containsKey(lineWords[i])){
                        int index = keys.indexOf(lineWords[i]);
                        wordCount.set(index,wordCount.get(index)+1);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.err.println("File :" + fileName + "  is exist");
            System.exit(-1);
        }
        return wordCount;
    }

    private void createWordMap(String fileNameOne, String fileNameTwo) {
        words = new HashMap<>();
        openFile(fileNameOne,words);
        openFile(fileNameTwo,words);
    }

    private void openFile(String fileName, Map<String, Integer> wordMap) {
        try (FileInputStream signatureFile = new FileInputStream(fileName)) {
            Scanner in = new Scanner(signatureFile);
            while (in.hasNextLine()) {
                String[] words = in.nextLine().split("\\s+");
                for (String word : words) {
                    if (wordMap.containsKey(words)) {
                        wordMap.put(word, wordMap.get(word) + 1);
                    } else {
                        wordMap.put(word, 1);
                    }
                }
            }
            in.close();
        } catch (IOException e) {
            System.err.println("File :" + fileName + "  is exist");
            System.exit(-1);
        }
    }
}
