package com;

import com.models.TapeWrapper;
import com.parsers.FileParser;

public class Main {

    public static void main(String[] args) {

        FileParser fileParser = new FileParser();
        TapeWrapper updatedTapeFile = fileParser.updateFile(args[0],args[1]);
        fileParser.writeToFile(updatedTapeFile,args[2]);
    }
}
