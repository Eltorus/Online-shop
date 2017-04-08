package by.epam.shop.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.imageio.ImageIO;
import javax.servlet.http.Part;

public final class ImageUpload {
    public static void uploadFile(Part part, String savePath, String fileName) throws UtilException {
	InputStream is = null;
	try{
	    is = part.getInputStream();
	    if(ImageIO.read(is) == null) {
		throw new UtilException("File is not img");
	    }
	    
	    is = part.getInputStream();
	    Files.copy(is, new File(savePath + fileName).toPath(), StandardCopyOption.REPLACE_EXISTING);
	} catch (IOException e) {
	    throw new UtilException("Error during upload");
	} finally {
	    try {
		is.close();
	    } catch (IOException e) {
		throw new UtilException("Error during upload");
	    }
	}
    }
}
