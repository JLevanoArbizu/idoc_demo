package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DocumentoTipo;
import org.primefaces.model.StreamedContent;

public class DocumentoTipoImpl extends Conexion implements ICrud<DocumentoTipo>, IReporte<DocumentoTipo> {

    @Override
    public void registrar(DocumentoTipo documentotipo) throws Exception {
        try {
            String sql = "INSERT INTO TIPO_DOCUMENTO (TIPDOC, NOMTIPDOC) VALUES(?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setString(1, documentotipo.getTIPDOC());
            ps.setString(2, documentotipo.getNOMTIPDOC());
            
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void editar(DocumentoTipo documentotipo) throws Exception {
        try {
            String sql = "UPDATE TIPO_DOCUMENTO SET  TIPDOC=?, NOMTIPDOC=?, ESTTIPDOC=? WHERE IDTIPDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setString(1, documentotipo.getTIPDOC());
            ps.setString(2, documentotipo.getNOMTIPDOC());
            ps.setString(3, documentotipo.getESTTIPDOC());
            ps.setInt(4, documentotipo.getIDTIPDOC());            
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(DocumentoTipo documentotipo) throws Exception {
        try {
            String sql = "UPDATE TIPO_DOCUMENTO SET ESTTIPDOC='I' WHERE IDTIPDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setInt(1, documentotipo.getIDTIPDOC());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<DocumentoTipo> listar() throws Exception {
        List<DocumentoTipo> listaDocumentotipo = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM TIPO_DOCUMENTO where  ESTTIPDOC != 'I' ";
            ps = this.conectar().prepareStatement(sql);
            rs = ps.executeQuery();
            DocumentoTipo documento;
            while (rs.next()) {
                documento = new DocumentoTipo();
                documento.setIDTIPDOC(rs.getInt(1));
                documento.setTIPDOC(rs.getString(2));
                documento.setNOMTIPDOC(rs.getString(3));
                documento.setESTTIPDOC(rs.getString(4));
                listaDocumentotipo.add(documento);
            }
            ps.closeOnCompletion();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (ps.isClosed()) {
                ps.clearParameters();
                rs.close();
                this.desconectar();
            }
        }
        return listaDocumentotipo;

    }

    @Override
    public List<DocumentoTipo> listar(DocumentoTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DocumentoTipo obtenerModelo(DocumentoTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteIndividual(DocumentoTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteGeneral(DocumentoTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteIndividualPrev(DocumentoTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public StreamedContent generarReporteGeneralPrev(DocumentoTipo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
