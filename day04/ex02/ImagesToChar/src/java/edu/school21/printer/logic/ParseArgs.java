package edu.school21.printer.logic;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class ParseArgs {

    @Parameter(names = "--white", description = "color white")
    private String colorWrite;

    @Parameter(names = "--black", description = "color black")
    private String colorBlack;

    public String getColorWrite() {
        return colorWrite;
    }

    public String getColorBlack() {
        return colorBlack;
    }
}
