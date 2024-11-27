package backup;

/**
 * Написать функцию, создающую резервную копию всех файлов в директории (без
 * поддиректорий) во вновь созданную папку ./backup
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


public class BackupUtility {

    public static void createBackup(String sourceDirPath) {
        Path sourceDir = Paths.get(sourceDirPath);
        // Замените на путь к директории для резервных копий
        Path backupDir = Paths.get(".\\src\\backup\\BackupPath");

              if (!Files.exists(backupDir)) {
            try {
                Files.createDirectory(backupDir);
            } catch (IOException e) {
                System.err.println("Не удалось создать директорию ./BackupPath: " + e.getMessage());
                return;
            }
        }

         try {
            Files.list(sourceDir)
                    .filter(path -> path.toFile().isFile())
                    .forEach(path -> {
                        Path destinationPath = backupDir.resolve(path.getFileName());
                        try {
                            Files.copy(path, destinationPath, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Скопирован файл: " + path.getFileName());
                        } catch (IOException e) {
                            System.err.println("Не удалось скопировать файл " + path.getFileName() + ": " + e.getMessage());
                        }
                    });
        } catch (IOException e) {
            System.err.println("Ошибка при получении списка файлов: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Заменить "sourceDirPath" на путь к вашей директории для резервного копирования (backup)
        createBackup(".\\src\\backup\\FolderForBackup");
    }
}
