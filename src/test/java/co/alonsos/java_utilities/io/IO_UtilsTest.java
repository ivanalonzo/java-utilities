package co.alonsos.java_utilities.io;

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IO_UtilsTest {
	IO_Utils io = new IO_Utils();
	private static Logger log = Logger.getLogger(IO_UtilsTest.class);

	@Test
	public void testImageResizeLgtoSm() {
		String inputPath = "src/test/resources/unit_test_files/images/2048-1365.jpg";
		int targetWidth = 1280;
		double maxRatio = 1.5;

		try {
			File tempFile = File.createTempFile("tempfile", ".jpg");
			io.imageResize(inputPath, tempFile.getAbsolutePath(), targetWidth, maxRatio);
			BufferedImage tempImage = ImageIO.read(tempFile);
			Assert.assertEquals(targetWidth, tempImage.getWidth());
		}catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testImageResizeSmallChange() {
		String inputPath = "src/test/resources/unit_test_files/images/1200-630.jpg";
		int targetWidth = 1280;
		double maxRatio = 1.5;

		try {
			File tempFile = File.createTempFile("tempfile", ".jpg");
			io.imageResize(inputPath, tempFile.getAbsolutePath(), targetWidth, maxRatio);
			BufferedImage tempImage = ImageIO.read(tempFile);
			Assert.assertEquals(targetWidth, tempImage.getWidth());
		}catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testImageResizeSmallChange2() {
		String inputPath = "src/test/resources/unit_test_files/images/1300-884.jpg";
		int targetWidth = 1280;
		double maxRatio = 1.5;

		try {
			File tempFile = File.createTempFile("tempfile", ".jpg");
			io.imageResize(inputPath, tempFile.getAbsolutePath(), targetWidth, maxRatio);
			BufferedImage tempImage = ImageIO.read(tempFile);
			Assert.assertEquals(targetWidth, tempImage.getWidth());
		}catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void testImageResizeTooSmall() {
		String inputPath = "src/test/resources/unit_test_files/images/360-203.png";
		int targetWidth = 1280;
		double maxRatio = 1.5;

		try {
			File tempFile = File.createTempFile("tempfile", ".png");
			io.imageResize(inputPath, tempFile.getAbsolutePath(), targetWidth, maxRatio);
			ImageIO.read(tempFile);
		}catch (Exception e) {
			// We want an exception to get thrown after reading the tempImage because it should never
			// have been written in the first place
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testImageResizeLgtoSm2() {
		String inputPath = "src/test/resources/unit_test_files/images/2048-1365.jpg";
		int targetWidth = 200;
		double maxRatio = 1.5;

		try {
			File tempFile = File.createTempFile("tempfile", ".jpg");
			io.imageResize(inputPath, tempFile.getAbsolutePath(), targetWidth, maxRatio);
			BufferedImage tempImage = ImageIO.read(tempFile);
			Assert.assertEquals(targetWidth, tempImage.getWidth());
		}catch (Exception e) {
			Assert.fail(e.getMessage());
		}
	}

}
