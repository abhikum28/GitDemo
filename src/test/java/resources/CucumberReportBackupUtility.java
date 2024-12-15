package resources;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CucumberReportBackupUtility {

	private static final String REPORT_DIRECTORY = "target/cucumber-html-reports"; 
    private static final String BACKUP_DIRECTORY = "target/cucumber-report-backups";

    public static void backupReports() throws IOException {
        // Get the current timestamp for naming backup files
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")); 
    	
        String backupDirectoryChild = BACKUP_DIRECTORY+"/Results_"+timestamp;
        // Create backup directory if it doesn't exist
    	Path backupPath = Paths.get(backupDirectoryChild);
        if (!Files.exists(backupPath)) {
            Files.createDirectories(backupPath);
        }
        
        // Iterate through files in the report directory
        File reportDir = new File(REPORT_DIRECTORY);
        File[] reportFiles = reportDir.listFiles();
        if (reportFiles != null) {
            for (File reportFile : reportFiles) {
                // Create the backup file path with timestamp
                String backupFileName = reportFile.getName();
                Path source = Paths.get(reportFile.getAbsolutePath());
                Path destination = Paths.get(backupDirectoryChild, backupFileName);

                // Copy the report file to the backup directory
                Files.copy(source, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }
	
}
