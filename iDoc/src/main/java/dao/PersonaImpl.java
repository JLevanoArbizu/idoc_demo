package dao;

import com.google.gson.Gson;
import modelo.Persona;

import java.io.OutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;

public class PersonaImpl extends Conexion implements IGenerica<Persona> {

    @Override
    public void registrar(Persona modelo) throws Exception {
        try {
            String sql = "INSERT INTO GENERAL.PERSONA (APEPATPER, APEMATPER, NOMPER, DNIPER, CODUBI, DIRPER, NACPER, GENPER, ESTPER) "
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getAPEPATPER().toUpperCase());
            ps.setString(2, modelo.getAPEMATPER().toUpperCase());
            ps.setString(3, WordUtils.capitalize(modelo.getNOMPER()));
            ps.setString(4, modelo.getDNIPER());
            ps.setString(5, modelo.getCODUBI());
            ps.setString(6, WordUtils.capitalize(modelo.getDIRPER()));
            ps.setString(7, "P");
            ps.setString(8, String.valueOf(modelo.getGENPER().charAt(0)));
            ps.setString(9, "A");
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
    public void editar(Persona modelo) throws Exception {
        try {
            eliminar(modelo);
            registrar(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Persona modelo) throws Exception {
        try {
            String sql = "UPDATE GENERAL.PERSONA SET ESTPER=? WHERE IDPER=?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDPER()));
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
    public List<Persona> listar() throws Exception {
        List<Persona> lista = null;
        try {
            String sql = "SELECT * FROM General.PERSONA ORDER BY IDPER DESC";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);

            lista = new ArrayList<>();
            Persona persona;

            while (rs.next()) {
                persona = new Persona();
                persona.setIDPER(String.valueOf(rs.getInt("IDPER")));
                persona.setAPEPATPER(rs.getString("APEPATPER"));
                persona.setAPEMATPER(rs.getString("APEMATPER"));
                persona.setNOMPER(rs.getString("NOMPER"));
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setCODUBI(rs.getString("CODUBI"));
                persona.setDIRPER(rs.getString("DIRPER"));
                persona.setNACPER(rs.getString("NACPER").equals("P") ? "Peruano" : "Extranjero");
                persona.setGENPER(rs.getString("GENPER").equals("M") ? "Masculino" : "Femenino");
                persona.setESTPER(rs.getString("ESTPER"));
                persona.setCOMPLETO(
                        persona.getAPEPATPER() + " "
                        + persona.getAPEMATPER() + ", "
                        + persona.getNOMPER()
                );
                lista.add(persona);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<String> buscar(String campo, List<Persona> listaModelo) throws Exception {
        List<String> lista = new ArrayList<>();
        campo = campo.toUpperCase();
        for (Persona persona1 : listaModelo) {
            if (persona1.getCOMPLETO().startsWith(campo) && persona1.getESTPER().equals("A")) {
                lista.add(persona1.getCOMPLETO());
            }
        }
        return lista;
    }

    @Override
    public Persona obtenerCodigo(List<Persona> listaModelo, Persona modelo) throws Exception {
        for (Persona persona1 : listaModelo) {
            if (modelo.getCOMPLETO().equals(persona1.getCOMPLETO())) {
                modelo.setIDPER(persona1.getIDPER());
                return modelo;
            }
        }
        return null;
    }

    @Override
    public boolean existe(List<Persona> listaModelo, Persona modelo) throws Exception {
        if (modelo.getDNIPER() != null) {
            for (Persona next : listaModelo) {
                if (next.getDNIPER().equals(modelo.getDNIPER())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void generarReporte(Persona modelo) throws Exception{
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
            respuestaPF.setHeader("Content-disposition", "attachment; filename=Persona.pdf");
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
