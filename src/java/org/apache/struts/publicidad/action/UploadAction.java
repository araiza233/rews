package org.apache.struts.publicidad.action;
import java.io.File;
import com.opensymphony.xwork2.ActionSupport;
import javax.servlet.http.HttpServletRequest;
//import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
public class UploadAction extends ActionSupport implements ServletRequestAware{
      private File file;
      private String contentType;
      private String filename;
      private String codigo;
      public HttpServletRequest request;
      public void setUpload(File file) {
         this.file = file;
      }

      public void setUploadContentType(String contentType) {
         this.contentType = contentType;
      }

      public void setUploadFileName(String filename) {
         this.filename = filename;
      }

    @Override
    public String execute() {
        try{
            String filePath = request.getRealPath("/") +"/images/imagesProducts/";
            System.out.println("Server path:" + filePath); 
            File fileToCreate = new File(filePath, codigo+".jpg"); 
            FileUtils.copyFile(file, fileToCreate); 
            filePath = fileToCreate.getAbsolutePath();
            /*Thumbnails.of(new File(filePath))
			.size(160, 160)
			.toFile(new File(filePath.replaceAll(".jpg", "-2.jpg")));
            request.getSession().setAttribute("mostrarGuardar", "mostrar");
            request.getSession().setAttribute("path", filePath);
             
             */
        }catch(Exception e){
            request.getSession().setAttribute("error", e.getMessage());
            e.printStackTrace();
            return "error";
        }
        return SUCCESS;
    }
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;  
    }
    public String getCodigo() {
        return codigo;
    }
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}