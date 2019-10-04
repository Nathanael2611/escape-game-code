package fr.nathanael2611.openclassrooms.escapegame.code.core.util;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class AppHelper
{

    /**
     * Just read a file content to a String object
     */
    public static String readFileToString(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            String ls = System.getProperty("line.separator");
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            reader.close();
            return stringBuilder.toString();
        }catch(Exception e){
            e.printStackTrace();
        }
        return "ERROR";
    }

    public static int[] parseStringToCode(String codeString)
    {
        try {
            String[] entireCode = codeString.split("");
            int[] code = new int[entireCode.length];
            for (int i = 0; i < entireCode.length; i++)
                code[i] = Integer.parseInt(entireCode[i]);
            return code;
        } catch (NumberFormatException ex)
        {
            throw new NumberFormatException();
        }
    }

}
