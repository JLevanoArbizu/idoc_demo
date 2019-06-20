package dao;

import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import modelo.Incidencia;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class IncidenciaImpl extends Conexion implements IGenerica<Incidencia> {

    @Override
    public void registrar(Incidencia modelo) throws Exception {
        try {
            String sql = "INSERT INTO REGCIV.INCIDENCIA (IDACTA, IDINCTIP, FECINC, MOTINC, ESTINC) VALUES (?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getIDDOC());
            ps.setString(2, modelo.getIDINCTIP());
            ps.setDate(3, modelo.getFECINC());
            ps.setString(4, modelo.getMOTINC());
            ps.setString(5, "A");
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Incidencia modelo) throws Exception {
        try {
            String sql = "UPDATE REGCIV.INCIDENCIA SET IDACTA =?, IDINCTIP =?, FECINC=?, MOTINC=?, ESTINC=? WHERE IDINC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getIDDOC());
            ps.setString(2, modelo.getIDINCTIP());
            ps.setDate(3, modelo.getFECINC());
            ps.setString(4, modelo.getMOTINC());
            ps.setString(5, modelo.getESTINC());
            ps.setInt(6, Integer.valueOf(modelo.getIDINC()));
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(Incidencia modelo) throws Exception {
        try {
            String sql = "UPDATE REGCIV.INCIDENCIA SET ESTINC=? WHERE IDINC=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDINC()));
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Incidencia> listar() throws Exception {
        List<Incidencia> lista = null;
        try {
            String sql = "SELECT IDINC,IDACTA,IDINCTIP,MOTINC, FECINC, ESTINC FROM REGCIV.INCIDENCIA ORDER BY IDINC DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            Incidencia incidencia;
            lista = new ArrayList<>();
            while (rs.next()) {
                incidencia = new Incidencia();
                incidencia.setIDINC(String.valueOf(rs.getInt(1)));
                incidencia.setIDDOC(String.valueOf(rs.getInt(2)));
                incidencia.setIDINCTIP(String.valueOf(rs.getInt(3)));
                incidencia.setMOTINC(rs.getString(4));
                incidencia.setFechaTemporal(rs.getDate(5));
                incidencia.setESTINC(rs.getString(6));
                lista.add(incidencia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<String> buscar(String campo, List<Incidencia> listaModelo) throws Exception {
        List<String> lista = new ArrayList<>();
        campo = campo.toUpperCase();
        for (Incidencia next : listaModelo) {
            if (next.getIDDOC().startsWith(campo)) {
                lista.add(next.getIDDOC());
            }
        }
        return lista;
    }

    @Override
    public Incidencia obtenerCodigo(List<Incidencia> listaModelo, Incidencia modelo) throws Exception {
        for (Incidencia next : listaModelo) {
            if (next.getIDDOC().equals(modelo.getIDDOC())) {
                modelo.setIDINC(next.getIDINC());
                return modelo;

            }

        }
        return null;
    }

    @Override
    public boolean existe(List<Incidencia> listaModelo, Incidencia modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(Incidencia modelo) throws Exception{
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
            respuestaPF.setHeader("Content-disposition", "attachment; filename=Incidencia.pdf");
            OutputStream output = respuestaPF.getOutputStream();
            respuestaApi.getEntity().writeTo(output);
            output.flush();
            FacesContext.getCurrentInstance().responseComplete();
            System.out.println("Terminó");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
