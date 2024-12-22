package com.example.commands;

import com.beust.jcommander.Parameter;

public class Wc {
    @Parameter(names = {"-c","--count"},description = "Takes the file and prints the number of bytes in the file",required = true)
    private String countFile;


    public void execute(){
        System.out.println("Displaying " + 1 + " lines from file: " + countFile);
    }

}
