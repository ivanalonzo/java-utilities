package co.alonsos.java_utilities.io;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.AgeFileFilter;
import org.apache.commons.io.IOUtils;

import org.apache.log4j.Logger;

public class IO_Utils {

	private static Logger log = Logger.getLogger(IO_Utils.class);

	private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
		byte[] bytesIn = new byte[4096];
		int read = 0;
		while ((read = zipIn.read(bytesIn)) != -1) {
			bos.write(bytesIn, 0, read);
		}
		bos.close();
	}

	/**
	 * Returns the value of a small file
	 * 
	 * @param pathToFile
	 * @return
	 */
	public List<String> readSmallTextFile(String pathToFile) {
		try {
			Path path = Paths.get(pathToFile);
			return Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			log.error(e);
			return null;
		}
	}

	/**
	 * Return the string output of a given file.
	 * 
	 * @param filePath
	 *            the file path
	 * @return the CSV string
	 * @throws IOException:
	 *             Signals that an I/O exception has occurred.
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public String fileToString(String filePath) throws IOException {

		Path p = Paths.get(filePath);
		byte[] raw = Files.readAllBytes(p);
		String data = new String(raw);

		return data;
	}

	/**
	 * Helper function. Converts stream to String. SEE:
	 * http://stackoverflow.com/questions/309424/read-convert-an-inputstream-to-a-string
	 * 
	 * @throws IOException
	 */
	public String StreamToString(InputStream jsonStream) throws IOException {
		StringWriter sw = new StringWriter();
		IOUtils.copy(jsonStream, sw);
		return sw.toString();
	}

	/**
	 * Confirms if a file exists
	 * 
	 * @param pathToFile
	 *            : complete path
	 * @return
	 */
	public boolean fileExists(String pathToFile) {
		try {
			return new File(pathToFile).isFile();
		} catch (Exception e) {
			log.error("File not found: " + e.toString());
			return false;
		}
	}

	/**
	 * Check if a file is empty
	 * 
	 * @param pathToFile
	 *            - the absolute path to the file
	 * @return
	 */
	public boolean isFileEmpty(String pathToFile) {
		boolean isEmpty = false;
		BufferedReader br = null;

		if (!fileExists(pathToFile)) {
			return true;
		}

		try {

			br = new BufferedReader(new FileReader(pathToFile));
			isEmpty = (br.readLine() == null);

		} catch (Exception e) {

			isEmpty = true;

		} finally {

			try {
				if (br != null) {
					br.close();
				}
			} catch (Exception e) {
			}

		}

		return isEmpty;
	}

	/**
	 * Will write a file to disk as an executable
	 * 
	 * @param fileLoc:
	 *            complete path
	 * @param values
	 * @return true if success
	 */
	public boolean executableWriter(String fileLoc, String values) {

		Writer writer = null;
		boolean success = false;

		try {
			File file = new File(fileLoc);
			// Delete the file if it exists
			if (file.delete()) {
				log.debug("This file (" + fileLoc
						+ ") was already in place, deleting it to make sure we have a fresh file");
			}
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileLoc), "utf-8"));
			writer.write(values);
			// Set it as executable
			file.setExecutable(true);
			success = true;
		} catch (IOException e) {
			log.error("Error writing file: " + fileLoc + ". Error: " + e.toString());
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				log.error("Error closing writer: " + e.toString());
			}
		}
		return success;
	}

	/**
	 * Will write a file to disk
	 * 
	 * @param fileLoc:
	 *            folder path </path/to/folder>
	 * @param fileName:
	 *            file name with extension <file.ext>
	 * @param content:
	 *            the content of the file
	 * @return
	 */
	public boolean saveStringToDisk(String fileLoc, String fileName, String content) {

		Writer writer = null;
		boolean success = false;
		String fileToWrite = fileLoc + fileName;

		try {
			File file = new File(fileToWrite);
			// Delete the file if it exists
			if (file.delete()) {
				log.debug("This file (" + fileToWrite
						+ ") was already in place, deleting it to make sure we have a fresh file");
			}

			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileToWrite), "utf-8"));
			writer.write(content);

			success = true;
		} catch (IOException e) {
			log.error("Error writing file: " + fileToWrite + ". Error: " + e.toString());
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
				log.error("Error closing writer: " + e.toString());
			}
		}
		return success;
	}

	/**
	 * Given a zip path, it will extract a specific entry to a destination
	 * 
	 * @param zipFilePath
	 * @param destDirectory
	 * @param entryToExtract
	 * @throws IOException
	 */
	public void unzip(String zipFilePath, String destDirectory, String entryToExtract) throws Exception {
		File destDir = new File(destDirectory);
		if (!destDir.exists()) {
			FileUtils.forceMkdir(destDir);
			destDir.setWritable(true);
			log.info("Created: " + destDir.getAbsolutePath());
		}
		ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
		ZipEntry entry = zipIn.getNextEntry();
		// iterates over entries in the zip file
		while (entry != null) {
			if (entryToExtract.equals(entry.getName())) {
				String filePath = destDirectory + File.separator + entry.getName();
				if (!entry.isDirectory()) {
					// if the entry is a file, extracts it
					extractFile(zipIn, filePath);
					log.debug("Sucessfully extracted: " + entry.getName());
				} else {
					// if the entry is a directory, make the directory
					File dir = new File(filePath);
					FileUtils.forceMkdir(dir);
					dir.setWritable(true);
				}
			}
			zipIn.closeEntry();
			entry = zipIn.getNextEntry();
		}
		zipIn.close();
	}

	/**
	 * Given the age in hours, it will delete all files and folders older than the
	 * given ageInHours
	 * 
	 * @param directoryName
	 * @param ageInHours
	 */
	public void deleteOldFilesAndFolders(String directoryName, int ageInHours, String excludeThisFileOrFolder) {
		long cutoff = System.currentTimeMillis() - (ageInHours * 60 * 60 * 1000);
		String fileToExclude = excludeThisFileOrFolder.trim();

		try {
			FilenameFilter filter = new AgeFileFilter(cutoff);
			File directory = new File(directoryName);
			if (directory.exists()) {
				File[] fList = directory.listFiles(filter);
				if (fList.length == 0) {
					log.debug("No files/folders older than: " + new Date(cutoff));
				}
				for (File file : fList) {
					log.debug(file.getName());
					if (fileToExclude.isEmpty()) {
						log.debug("Attempting to delete: " + file.getAbsolutePath());
						FileUtils.forceDelete(file);
						continue;
					}
					if (!file.getName().contains(fileToExclude)) {
						log.debug("Attempting to delete: " + file.getAbsolutePath());
						FileUtils.forceDelete(file);
						continue;
					}
					if (file.getName().contains(fileToExclude)) {
						log.debug("Nothing to delete since we're excluding: " + fileToExclude);
						continue;
					}
				}
			} else {
				log.debug("File/Directory does not exist: " + directory.getAbsolutePath());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	/**
	 * Will delete files which are older than a specified time-frame and have a
	 * specific extension
	 * 
	 * @param directoryName
	 * @param ageInHours
	 * @param fileExtension
	 *            should be in the form of .ext (.txt, .png, etc).
	 * @throws Exception
	 *             if fileExtension is empty or null
	 */
	public void deleteOldFiles(String directoryName, int ageInHours, String fileExtension) throws Exception {
		long cutoff = System.currentTimeMillis() - (ageInHours * 60 * 60 * 1000);
		if (fileExtension == null) {
			fileExtension = "";
		}
		String ext = fileExtension.trim();

		try {
			if (ext.isEmpty()) {
				throw new Exception("You must include a non-emtpy value for extension");
			}
			log.debug("Files in this folder (" + directoryName + ") with extension (" + ext + ") and older than "
					+ ageInHours + " hours will be deleted");
			FilenameFilter filter = new AgeFileFilter(cutoff);
			File directory = new File(directoryName);
			if (directory.exists()) {
				File[] fList = directory.listFiles(filter);
				if (fList.length == 0) {
					log.debug("No files/folders older than: " + new Date(cutoff));
				}
				for (File file : fList) {
					if (file.getName().endsWith(ext)) {
						log.debug("Attempting to delete: " + file.getAbsolutePath());
						FileUtils.forceDelete(file);
						continue;
					}
				}
			} else {
				log.debug("File/Directory does not exist: " + directory.getAbsolutePath());
			}
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new Exception(e);
		}
	}

	/**
	 * Using DNS lookup, it will validate myHost against an array of cNames and
	 * return true/false if myHost is address to which one of the cNames points
	 * 
	 * @param myHost:
	 *            myhost.corp.yahoo.com
	 * @param prodCNames:
	 *            array of cName like holodeck.load.yahoo.com,
	 *            holodeck.stress.yahoo.com, etc
	 * @return true if ANY of the cNames in the array list point to myHost
	 */
	public boolean isPrimary(String myHost, String[] prodCNames) {
		boolean primary = false;
		prodCheck: for (int i = 0; i < prodCNames.length; i++) {
			InetAddress[] inetAddressArray;
			try {
				inetAddressArray = InetAddress.getAllByName(prodCNames[i]);
				for (int x = 0; x < inetAddressArray.length; x++) {
					if (myHost.equals(inetAddressArray[x].getCanonicalHostName())) {
						primary = true;
						break prodCheck;
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}
		return primary;
	}

	/**
	 * Using DNS lookup, it will validate myHost against an array of cNames and
	 * return prod CName if myHost is address to which one of the cNames points
	 * 
	 * @param myHost:
	 *            myhost.corp.yahoo.com
	 * @param prodCNames:
	 *            array of cName like holodeck.load.yahoo.com,
	 *            holodeck.stress.yahoo.com, etc
	 * @return prod CName if there is a match, otherwise return myHost
	 */
	public String getProdCName(String myHost, String[] prodCNames) {
		String prodCName = null;

		prodCheck: for (int i = 0; i < prodCNames.length; i++) {
			InetAddress[] inetAddressArray;
			try {
				inetAddressArray = InetAddress.getAllByName(prodCNames[i]);
				for (int x = 0; x < inetAddressArray.length; x++) {
					if (myHost.equals(inetAddressArray[x].getCanonicalHostName())) {
						prodCName = prodCNames[i];
						break prodCheck;
					}
				}
			} catch (Exception e) {
				log.error(e.getMessage(), e);
			}
		}

		// if we cannot find prodCName, just return my host name
		if (prodCName == null) {
			prodCName = myHost;
		}

		return prodCName;
	}

	/**
	 * Given the filePath, it will sue Apache.FileUtils to forceDelete the file If
	 * you pass it a folder, it will delete the files under the folder and the
	 * folder itself
	 * 
	 * If you only want to delete the contents of a folder, use cleanFolder method
	 * instead
	 * 
	 * @param filePath
	 *            complete path to file to be deleted
	 * @return true if successful
	 */
	public boolean deleteFile(String filePath) {
		boolean deleted = false;

		File fileToDelete = new File(filePath);
		try {
			FileUtils.forceDelete(fileToDelete);
			deleted = true;
		} catch (FileNotFoundException fe) {
			log.info("File (" + filePath + ") does not exist. Nothing to delete.");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return deleted;
	}

	/**
	 * Given a folder path, it will delete all of its contents. Unlike deleteFile,
	 * it will NOT delete the folder itself
	 * 
	 * @param folderPath
	 * @return true if succesful.
	 */
	public boolean cleanFolder(String folderPath) {
		boolean deleted = false;

		File folderToDelete = new File(folderPath);
		try {
			FileUtils.cleanDirectory(folderToDelete);
			deleted = true;
		} catch (FileNotFoundException fe) {
			log.info("Folder (" + folderPath + ") does not exist. Nothing to delete.");
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return deleted;
	}

	public boolean saveInputToFileFile(InputStream stream, String filePath) {
		if (stream == null || filePath == null) {
			return false;
		}

		try {
			File output = new File(filePath);
			output.delete();
			output.createNewFile();
			OutputStream out = new FileOutputStream(output);

			int read = 0;
			byte[] bytes = new byte[1024];
			while ((read = stream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

}
