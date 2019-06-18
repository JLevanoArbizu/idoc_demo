package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.DocumentoM;

public class ImplDocumentoD extends Conexion implements IGenerica<DocumentoM> {

    @Override
    public void registrar(DocumentoM documento) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO DOCUMENTO(NUMFOLDOC,TIPDOC,FECDOC,ASUDOC,CODTUP,CODTRAB,CODPER) VALUES(?,?,CONVERT(DATE,?,103),?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, documento.getNUMFOLDOC());
            ps.setString(2, documento.getTIPDOC());
            ps.setString(3, documento.getFECDOC());
            ps.setString(4, documento.getASUDOC());
            ps.setString(5, documento.getCODTUP());
            ps.setString(6, documento.getCODTRAB());
            ps.setString(7, documento.getCODPER());
//            ps.setString(6, documento.getCODEMP());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public void editar(DocumentoM documento) throws Exception {

        try {
            this.conectar();
            String sql = "UPDATE DOCUMENTO SET  NUMFLODOC=? ,TIPDOC=?, FECDOC=?, ASUDOC=?, ESTDOC=?, CODTUP=? , CODTRAB=?, CODPER=? WHERE CODDOCLIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, documento.getNUMFOLDOC());
            ps.setString(2, documento.getTIPDOC());
            ps.setString(3, documento.getFECDOC());
            ps.setString(4, documento.getASUDOC());
            ps.setString(5, documento.getESTDOC());
            ps.setString(6, documento.getCODTUP());
            ps.setString(7, documento.getCODTRAB());
            ps.setString(8, documento.getCODPER());

            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(DocumentoM documento) throws Exception {

        try {
            this.conectar();
            String sql = "UPDATE DOCUMENTO SET ESTDOC='I' WHERE CODDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, documento.getCODDOC());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<DocumentoM> listar() throws Exception {
        List<DocumentoM> listaDocumento;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_DOCUMENTO";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listaDocumento = new ArrayList();
            DocumentoM documento;
            while (rs.next()) {
                documento = new DocumentoM();
                documento.setCODDOC(rs.getString("CODDOC"));
                documento.setNUMFOLDOC(rs.getString("NUMFOLDOC"));
                documento.setTIPDOC(rs.getString("TIPDOC"));
                documento.setFECDOC(rs.getString("FECDOC"));
                documento.setASUDOC(rs.getString("ASUDOC"));
                documento.setESTDOC(rs.getString("ESTDOC"));
                documento.setCODTUP(rs.getString("CODTUP"));
                documento.setCODTRAB(rs.getString("CODTRAB"));
                documento.setCODPER(rs.getString("CODPER"));
                listaDocumento.add(documento);
            }
            return listaDocumento;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<String> buscar(String campo, List<DocumentoM> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DocumentoM obtenerCodigo(List<DocumentoM> listaModelo, DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<DocumentoM> listaModelo, DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(DocumentoM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
