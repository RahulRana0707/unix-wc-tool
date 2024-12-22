package com.example;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.example.commands.WCMain;
import com.example.commands.Wc;

public class App {

    static final String CCWC = "ccwc";

    public static void main(String[] args) {
        Wc wcCommand = new Wc();
        WCMain wcmain = new WCMain();

        JCommander jc = JCommander.newBuilder()
                .addObject(wcmain)
                .addCommand(CCWC, wcCommand)
                .build();

        try {
            jc.parse(args);
            String parsedCmdStr = jc.getParsedCommand();
            System.out.println("Parsed command: " + parsedCmdStr);

            if (CCWC.equals(parsedCmdStr)) {
                wcCommand.execute();
                return;
            }
            System.err.println("Invalid command: " + parsedCmdStr);

        } catch (ParameterException e) {
            System.err.println(e.getMessage());
            jc.usage();
        }
    }
}
