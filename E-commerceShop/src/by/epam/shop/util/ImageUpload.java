package by.epam.shop.util;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.Part;

public class ImageUpload {
    public static void uploadFile(Collection<Part> parts, String savePath, String fileName) throws UtilException {
	File fileSaveDir  = new File(savePath);
	if(!fileSaveDir.exists()) {
	    fileSaveDir.mkdir();
	}
	
	for(Part part : parts) {
	    fileName = new File(fileName).getName();
	    try {
		part.write(savePath + File.separator + fileName);
	    } catch (IOException e) {
		throw new UtilException(e);
	    }
	}
    }
}
