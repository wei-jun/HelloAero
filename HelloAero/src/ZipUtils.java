import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 2018-06-02 */
 
public class ZipUtils {
	
	// zip one file
	public static void zipOneFile(String srcFile, String outputFile) throws IOException {
        String sourceFile = srcFile;
        FileOutputStream fos = new FileOutputStream(outputFile);
        ZipOutputStream zipOut = new ZipOutputStream(fos);
        File fileToZip = new File(sourceFile);
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
        zipOut.putNextEntry(zipEntry);
        final byte[] bytes = new byte[4096];
        int length;
        while((length = fis.read(bytes)) >= 0) {
            zipOut.write(bytes, 0, length);
        }
        zipOut.close();
        fis.close();
        fos.close();
    }
	
	// zip files in a directory	
	public static void zipFiles(String srcDir, String destDir) throws ZipException, IOException {
		System.out.println("zip " + srcDir + " starts..."); 
		String sourceFile = srcDir;
		File filesToZip = new File(sourceFile);
		//SimpleDateFormate sf = new SimpleDateFormat(yyyy-mm-dd);
		String outFile = destDir + "/" + filesToZip.getName() + ".zip";
        FileOutputStream fos = new FileOutputStream(outFile);
        ZipOutputStream zos = new ZipOutputStream(fos);
        
        File[] files = filesToZip.listFiles();
        for (File file : files) {
        	zipFile(file, file.getName(), zos);
        }
        zos.close();
        fos.close();
        System.out.println("zip " + srcDir + " ends"); 
	}
	
	private static void zipFile(File fileToZip, String fileName, ZipOutputStream zos) throws ZipException, IOException {
//        if (fileToZip.isHidden()) {
//            return;
//        }
        if (fileToZip.isDirectory()) {
        	System.out.println("zip dir " + fileToZip.toString() + "/");        	
            ZipEntry zipEntry = new ZipEntry(fileName + "/");
            zos.putNextEntry(zipEntry);
            File[] children = fileToZip.listFiles();
            for (File childFile : children) {            	
                zipFile(childFile, fileName + "/" + childFile.getName(), zos);
            }
            return;
        }
        System.out.println("zip file " + fileToZip.getPath());
        FileInputStream fis = new FileInputStream(fileToZip);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);
        byte[] bytes = new byte[4096];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }
        fis.close();
    }
	
	public static void unzipFile(String filePath, String destDir) {
		System.out.println("unzip " + filePath + " starts...");
        File fileZip = new File(filePath);
        String dir = destDir;
        byte[] buffer = new byte[4096];
        try {
        	ZipInputStream zis = new ZipInputStream(new FileInputStream(fileZip));
            ZipEntry zipEntry = zis.getNextEntry();
            while(zipEntry != null){
            	String fileName = zipEntry.getName();
                File newFile = new File(dir, fileName);
                if (zipEntry.isDirectory()) {
                	// create directory structure if needed
                	System.out.println("unzip dir " + newFile.getPath() + "/");
                	newFile.mkdirs();
                } else {
                	System.out.println("unzip file " + newFile.getPath());
                	FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    while ((len = zis.read(buffer)) > 0) {
                        fos.write(buffer, 0, len);
                    }
                    fos.close();
                }
                zipEntry = zis.getNextEntry();
            }
            zis.closeEntry();
            zis.close();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println("unzip ends");
	}
}
