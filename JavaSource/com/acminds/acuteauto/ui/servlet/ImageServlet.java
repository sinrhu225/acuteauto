package com.acminds.acuteauto.ui.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.acminds.acuteauto.persistence.BaseDAO;
import com.acminds.acuteauto.persistence.dto.Image;
import com.acminds.acuteauto.utils.Constants;
import com.acminds.acuteauto.utils.Utils;

/**
 * 
 * @author MANSUR
 *
 */
public class ImageServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final String DEFAULT_IMG = "/images/thumbnails/coming-soon.jpg";
	/*
	 * mime types:
	 * image/bmp
	 * image/gif
	 * image/jpeg
	 * image/png
	 * 
	 * image/tiff
	 * image/svg
	 */
	private Log logger = LogFactory.getLog(ImageServlet.class);
	
	
	public void init() throws ServletException {
		super.init();
		logger.info("Image rendering servlet initialized");
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		try {
			Image image = null;
			String imageId = request.getParameter("imgId");
			String isUploaded = request.getParameter("isUploaded");
			logger.info("Image Id: "+imageId);
			if(!Utils.isEmpty(isUploaded)) {
				List<Image> list = getUploadedImages(request);
				image = list.get(Integer.valueOf(imageId.trim()));
				if(image == null)
					return;
				OutputStream out = response.getOutputStream();
				response.setContentType(image.getMimeType());		
				if(!image.isPersistent())
					out.write(image.getImageData());
				else {
					InputStream str = new FileInputStream(new File(image.getRealLocation()));
					out.write(IOUtils.toByteArray(str));
				}
			} else if(Utils.isEmpty(imageId)) {
				String isImgHolder = request.getParameter(Constants.IMG_HOLDER);
				if(Utils.isEmpty(isImgHolder))
					return;
				image = (Image)request.getSession().getAttribute(Constants.IMG_HOLDER);
				if(image == null)
					return;
				response.setContentType(image.getMimeType());		
				OutputStream out = response.getOutputStream();
				out.write(image.getImageData());
			} else {
				image = BaseDAO.getInstance().get(Image.class, Integer.valueOf(imageId.trim()));
				if(image == null)
				{
					writeDefaultImage(request, response);
					return;
				}
				InputStream str = new FileInputStream(new File(image.getRealLocation()));
				response.setContentType(image.getMimeType());		
				OutputStream out = response.getOutputStream();
				out.write(IOUtils.toByteArray(str));
			}
			
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e);
		} catch (FileNotFoundException e) {
			try {
				writeDefaultImage(request, response);
			} catch (URISyntaxException e1) {
				logger.error(e.getMessage(), e);
				throw new ServletException(e);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e);
		} catch (URISyntaxException e) {
			logger.error(e.getMessage(), e);
			throw new ServletException(e);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse arg1) 
		throws ServletException, IOException 
	{
		String fileName = null;
        List<FileItem> multipartItems = null;
        try {
            // Parse the multipart request items.
            multipartItems = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request: " + e.getMessage());
        }
        // Loop through multipart request items.
        List<Image> uploadedImages = getUploadedImages(request);
        for (FileItem fi : multipartItems) {
        	if(!Utils.isEmpty(fi.getFieldName()) && fi.getFieldName().equals("fileName"))
        		fileName = fi.getString();
        	if (!fi.isFormField()) {
        		Image img = new Image();
	        	img.setName(fileName);
	        	img.setMimeType(fi.getContentType());
	        	img.setImageSize((int)fi.getSize());
	        	img.setImageData(fi.get());
	        	img.setImageLocation("temp");
	            uploadedImages.add(img);
	            logger.info("Image uploaded: "+img.getName()+"@@@@"+img.getImageSize());
        	}
        }			
	}
	
	@SuppressWarnings("unchecked")
	private List<Image> getUploadedImages(HttpServletRequest request) {
		Object obj = request.getSession(false).getAttribute(Constants.UPLOADED_IMAGES);
		if(obj == null)
			request.getSession(false).setAttribute(Constants.UPLOADED_IMAGES, new ArrayList<Image>());
		return (List<Image>) request.getSession(false).getAttribute(Constants.UPLOADED_IMAGES);
	}
	
	private void writeDefaultImage(HttpServletRequest request, HttpServletResponse response) throws IOException, URISyntaxException{
		String path = getServletContext().getRealPath(Constants.DEFAULT_IMG_LOC);
		InputStream str = new FileInputStream(new File(path));
		response.setContentType("image/jpeg");
		OutputStream out = response.getOutputStream();
		out.write(IOUtils.toByteArray(str));
	}
}
