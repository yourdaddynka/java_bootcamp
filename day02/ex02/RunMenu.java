package ex01;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;

public class RunMenu {
    private String path;


    public void start(String[] args) {
        path = checkValid(args);
        Scanner in = new Scanner(System.in);
        while (true) {
            parseCommand(in.nextLine());
        }
    }

    private void parseCommand(String inp) {
        String[] lineArgs = inp.split("\\s+");
        if (lineArgs[0].equals("mv") && lineArgs.length == 3) {
            commandMv(lineArgs[1], lineArgs[2]);
        } else if (lineArgs[0].equals("ls")) {
            commandLs();
        } else if (lineArgs[0].equals("cd") && lineArgs.length == 2) {
            commandCd(lineArgs[1]);
        } else if (lineArgs[0].equals("exit")) {
            System.exit(0);
        } else {
            System.out.println("command not found: " + lineArgs[0]);
        }
    }

    private void commandLs() {
        File file = new File(path);
        File folder[] = file.listFiles();
        if (folder != null) {
            for (File f : folder) {
                System.out.println(f.getName() + " " + f.length() / 1024 + "KB");
            }
        }
    }

    private void commandCd(String newFolder) {
        Path newPath = Paths.get(path, newFolder);
        File file = new File(newPath.toString());
        if (file.exists() && file.isDirectory()) {
            path = newPath.toString();
        } else {
            System.out.println("Invalid folder name: " + newFolder);
        }
    }

    private void commandMv(String what, String where) {
        Path source = null;
        Path destination = null;

        try (DirectoryStream<Path> files = Files.newDirectoryStream(Paths.get(path))) {
            for (Path temp : files) {
                if (temp.getFileName().toString().equals(what) && Files.isRegularFile(temp)) {
                    source = temp;
                    break;
                }
            }
            if (source == null) {
                System.out.println("mv: " + what + " no such file");
                return;
            }
            if (isDirectory(where)) {
                destination = Paths.get(path, where, what);
            } else {
                destination = Paths.get(path, where);
            }

            if (Files.exists(destination)) {
                System.out.println("mv: " + where + " already exists");
                return;
            }
            Files.move(source, destination, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private boolean isDirectory(String strPath) {
        Path nPath = Paths.get(strPath);
        nPath = Paths.get(path).resolve(nPath).normalize();

        return Files.isDirectory(nPath);
    }

    private String checkValid(String[] args) {
        if (args.length != 1 && args[0].startsWith("--current-folder=")) {
            System.err.println("invalid command line argument. Use to --current-folder=/Users/YourUsername/MAIN");
            System.exit(-1);
        }
        Path testFile = Paths.get(args[0].substring("--current-folder=".length()));
        System.out.println(testFile);
        return testFile.toString();
    }
}
