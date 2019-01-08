package co.alonsos.java_utilities.io;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 * The class provides basic IO utilities like:
 * ** Returning a list of strings given a path
 * ** Returning the contents of a filepath or stream as a string
 * ** Confirming a file exists, is empty or executable
 * ** Creating a file given a string input and a file path
 * ** Unzipping a file
 * ** Saving the output of a stream to a file
 * ** Resizing an image
 * 
 * @author ivanalonso
 *
 */
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
	 * @param pathToFile: the absolute path to the file
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

	public boolean saveInputToFile(InputStream stream, String filePath) {
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

	/**
	 * Given the input path, it will resize the image to the target width as long as the target width
	 * does not exceed the max ratio. The ratio is calculated by dividing the target width by the
	 * current
	 * width. If the calculated ratio is higher than the target width an error is returned
	 * 
	 * @param inputImagePath
	 * @param outputImagePath
	 * @param targetWidth
	 * @param maxRatio
	 * @throws Exception
	 */
	public void imageResize(String inputImagePath, String outputImagePath, int targetWidth, double maxRatio)
	        throws Exception {
		File inputFile = new File(inputImagePath);
		BufferedImage inputImage = ImageIO.read(inputFile);

		double percent = targetWidth / (double) inputImage.getWidth();

		if (percent > maxRatio) {
			log.debug("The scaling ratio is more than " + maxRatio + " (" + percent + ") for image: " + inputImagePath
			        + ". Will skip");
		}else {
			// Find the scale ratio
			int scaledWidth = (int) (inputImage.getWidth() * percent);
			int scaledHeight = (int) (inputImage.getHeight() * percent);

			// creates output image
			BufferedImage outputImage = new BufferedImage(scaledWidth, scaledHeight, inputImage.getType());

			// scales the input image to the output image
			Graphics2D g2d = outputImage.createGraphics();
			g2d.drawImage(inputImage, 0, 0, scaledWidth, scaledHeight, null);
			g2d.dispose();

			// extracts extension of output file
			String formatName = outputImagePath.substring(outputImagePath.lastIndexOf(".") + 1);

			// writes to output file
			ImageIO.write(outputImage, formatName, new File(outputImagePath));
		}
	}
}
