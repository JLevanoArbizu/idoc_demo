package servicio;

import com.google.gson.Gson;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import modelo.Documento;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

public class ReporteS {

    public void generarActa(Documento documento) throws UnsupportedEncodingException {
        try {
            Gson gson = new Gson();
            HttpClient httpClient = HttpClientBuilder.create().build();
            String url = "https://codelog.herokuapp.com/api/RegistroCivil/reportes/actas";
            HttpPost post = new HttpPost(url);
            StringEntity postEntity = new StringEntity(gson.toJson(documento),ContentType.APPLICATION_JSON);
            post.setEntity(postEntity);
            HttpResponse respuestaApi = httpClient.execute(post);
            System.out.println(respuestaApi.getStatusLine().getStatusCode());

            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse respuestaPF = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            respuestaPF.reset();
            respuestaPF.setContentType("application/pdf");
            respuestaPF.setHeader("Content-disposition", "attachment; filename=Acta.pdf");
            OutputStream output = respuestaPF.getOutputStream();
            respuestaApi.getEntity().writeTo(output);
            output.flush();
            FacesContext.getCurrentInstance().responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
