package ex01;
import java.io.File;

public class RunMenu {

    public RunMenu() {
        SimilarityCalculation similarityCalculation = new SimilarityCalculation();
    }


    public void start(String fileOne, String fileTwo) {
        if(fileIsExists(fileOne) && fileIsExists(fileTwo)){
            SimilarityCalculation similarityCalculation = new SimilarityCalculation();
            System.out.println(similarityCalculation.allCalculatios( fileOne,fileTwo));
        }
        else System.out.println("One of the files was not found");
    }

    private Boolean fileIsExists(String filePath) {
        File file = new File(filePath);
        return file.exists();
    }
}
