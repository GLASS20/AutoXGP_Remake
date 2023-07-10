package me.liycxc.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This file is part of AutoXGP Remake project.
 * Copyright 2023 Liycxc
 * All Rights Reserved.
 *
 * @author Liycxc
 * @date: 2023-07-09
 * @time: 16:08
 */
public class FileUtils {
    private static final File FILE_DIR = new File("XGP");
    private static File FILE = new File(FILE_DIR, "Saved-0.txt");

    public static void fileInit() {
        try {
            FILE_DIR.mkdirs();
            for (int index = 0 ; true ; index++) {
                if (FILE.exists()) {
                    if(FILE.length() == 0) {
                        break;
                    }
                    FILE = new File(FILE_DIR,"Saved-" + index + ".txt");
                } else {
                    FILE.createNewFile();
                    break;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
    public static void fileSave(String str) {
        System.out.println(FILE.getName());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE, true))) {
            writer.write(str);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
