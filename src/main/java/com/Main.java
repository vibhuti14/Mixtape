package com;

import com.models.TapeWrapper;
import com.parsers.FileParser;

public class Main {

    public static void main(String[] args) {

        FileParser fileParser = new FileParser();
        TapeWrapper originalFile = fileParser.updateFile(args[0],args[1]);
        fileParser.writeToFile(originalFile,args[2]);
    }
}
