package dao;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import modelo.Login;
import modelo.TupaM;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class ImplTupaD extends Conexion implements IGenerica<TupaM> {

    @Override
    public void registrar(TupaM tupa) throws Exception {
        try {
//            this.conectar();
            String sql = "INSERT INTO TraDoc.TUPA(NUMTUP,NOMTUP,PRETUP,PLATUP,ARETUP) VALUES(?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.setString(2, tupa.getNOMTUP());
            ps.setDouble(3, tupa.getPRETUP());
            ps.setString(4, tupa.getPLATUP());
            ps.setString(5, tupa.getARETUP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(TupaM tupa) throws Exception {
        try {
//            this.conectar();
            String sql = "UPDATE TraDoc.TUPA SET  NUMTUP=?, NOMTUP=?, PRETUP=?, PLATUP=?, ARETUP=? WHERE IDTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.setString(2, tupa.getNOMTUP());
            ps.setDouble(3, tupa.getPRETUP());
            ps.setString(4, tupa.getPLATUP());
            ps.setString(5, tupa.getARETUP());
            ps.setString(6, tupa.getIDTUP());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(TupaM tupa) throws Exception {

        try {
            this.conectar();
            String sql = "UPDATE TraDoc.TUPA SET ESTTUP='I' WHERE IDTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, tupa.getIDTUP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<TupaM> listar() throws Exception {
    List<TupaM> listaTupa;
        ResultSet rs;
        try {
            this.conectar();
//          String sql = "SELECT IDTUP,NOMTUP,PRETUP,FORMAT(FECTUP,'dd/MM/yyyy','en-gb') AS FECTUP,ARETUP FROM TUPA ";
            String sql = "SELECT *  FROM TraDoc.TUPA ";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listaTupa = new ArrayList();
            TupaM tupa;
            while (rs.next()) {
                tupa = new TupaM();
                tupa.setIDTUP(rs.getString("IDTUP"));
                tupa.setNUMTUP(rs.getString("NUMTUP"));
                tupa.setNOMTUP(rs.getString("NOMTUP"));
                tupa.setPRETUP(rs.getDouble("PRETUP"));
                tupa.setPLATUP(rs.getString("PLATUP"));
                tupa.setARETUP(rs.getString("ARETUP"));
                listaTupa.add(tupa);
            }
            return listaTupa;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }    

    @Override
    public List<String> buscar(String campo, List<TupaM> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TupaM obtenerCodigo(List<TupaM> listaModelo, TupaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<TupaM> listaModelo, TupaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(TupaM modelo) throws Exception{
        try {
            Gson gson = new Gson();
            HttpClient httpClient = HttpClientBuilder.create().build();
            String url = "http://192.168.1.35:5000/iDoc/reportes/maestros";
            HttpPost post = new HttpPost(url);
            StringEntity postEntity = new StringEntity(gson.toJson(modelo), ContentType.APPLICATION_JSON);
            post.setEntity(postEntity);
            HttpResponse respuestaApi = httpClient.execute(post);
            System.out.println(respuestaApi.getStatusLine().getStatusCode());

            FacesContext facesContext = FacesContext.getCurrentInstance();

            HttpServletResponse respuestaPF = (HttpServletResponse) facesContext.getExternalContext().getResponse();
            respuestaPF.reset();
            respuestaPF.setContentType("application/pdf");
            respuestaPF.setHeader("Content-disposition", "attachment; filename=Tupa.pdf");
            OutputStream output = respuestaPF.getOutputStream();
            respuestaApi.getEntity().writeTo(output);
            output.flush();
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Terminó");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrar(Login Login) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
