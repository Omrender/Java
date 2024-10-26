package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

public class SuffixingApp {

    private static final Logger LOGGER = Logger.getLogger(SuffixingApp.class.getName());

    public static void main(String[] args) {
        if (args.length < 1) {
            LOGGER.severe("No config file provided.");
            return;
        }

        String configFilePath = args[0];
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);

            String mode = properties.getProperty("mode");
            String suffix = properties.getProperty("suffix");
            String filesList = properties.getProperty("files");

            // Validate mode
            if (mode == null || !(mode.equalsIgnoreCase("copy") || mode.equalsIgnoreCase("move"))) {
                LOGGER.severe("Mode is not recognized: " + mode);
                return;
            }

            // Validate suffix
            if (suffix == null || suffix.isEmpty()) {
                LOGGER.severe("No suffix is configured.");
                return;
            }

            // Validate files
            if (filesList == null || filesList.isEmpty()) {
                LOGGER.warning("No files are configured to be copied/moved.");
                return;
            }

            String[] files = filesList.split(":");
            processFiles(mode, suffix, files);

        } catch (IOException e) {
            LOGGER.severe("Error reading config file: " + e.getMessage());
        }
    }

    private static void processFiles(String mode, String suffix, String[] files) {
        for (String filePath : files) {
            Path sourcePath = Paths.get(filePath);
            if (!Files.exists(sourcePath)) {
                LOGGER.severe("No such file: " + sourcePath.toString().replace("\\", "/"));
                continue;
            }

            String fileName = sourcePath.getFileName().toString();
            int dotIndex = fileName.lastIndexOf(".");
            String newFileName = (dotIndex == -1) ? fileName + suffix : fileName.substring(0, dotIndex) + suffix + fileName.substring(dotIndex);
            Path destinationPath = sourcePath.resolveSibling(newFileName);

            try {
                if (mode.equalsIgnoreCase("copy")) {
                    Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    LOGGER.info(sourcePath.toString().replace("\\", "/") + " -> " + destinationPath.toString().replace("\\", "/"));
                } else if (mode.equalsIgnoreCase("move")) {
                    Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                    LOGGER.info(sourcePath.toString().replace("\\", "/") + " => " + destinationPath.toString().replace("\\", "/"));
                }
            } catch (IOException e) {
                LOGGER.severe("Error processing file: " + sourcePath.toString().replace("\\", "/") + " - " + e.getMessage());
            }
        }
    }
}
