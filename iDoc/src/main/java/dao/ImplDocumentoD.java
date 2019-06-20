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
            String sql = "INSERT INTO TraDoc.DOCUMENTO (CODDOC,NUMLIBDOC,NUMFOLDOC,TIPDOC,FECDOC,ASUDOC,OBSDOC,IDTUP,IDLOG,IDEMP,IDPER,KEYDOC) VALUES(?,?,?,?,CONVERT(DATE,?,103),?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, documento.getCODDOC());
            ps.setString(2, documento.getNUMLIBDOC());
            ps.setString(3, documento.getNUMFOLDOC());
            ps.setString(4, documento.getTIPDOC());
            ps.setString(5, documento.getFECDOC());
            ps.setString(6, documento.getASUDOC());
            ps.setString(7, documento.getOBSDOC());
            ps.setString(8, documento.getIDTUP());
            ps.setString(9, documento.getIDLOG());
            ps.setString(10, documento.getIDEMP() == null ? "1":documento.getIDEMP());
            ps.setString(11, documento.getIDPER());
            ps.setString(12, documento.getKEYDOC());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            System.out.println();
            this.desconectar();
        }

    }

    @Override
    public void editar(DocumentoM documento) throws Exception {

        try {
            this.conectar();
            String sql = "UPDATE TraDoc.DOCUMENTO SET CODDOC=?, NUMLIBDOC = ?, NUMFOLDOC=? , TIPDOC=? , FECDOC=? , ASUDOC = ? , OBSDOC = ? , ESTDOC = ? , IDTUP = ? , IDLOG = ? , IDEMP = ? , IDPER = ? , KEYDOC = ? WHERE IDDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setString(1, documento.getCODDOC());
            ps.setString(2, documento.getNUMLIBDOC());
            ps.setString(3, documento.getNUMFOLDOC());
            ps.setString(4, documento.getTIPDOC());
            ps.setString(5, documento.getFECDOC());
            ps.setString(6, documento.getASUDOC());
            ps.setString(7, documento.getOBSDOC());
            ps.setString(8, documento.getESTDOC());
            ps.setString(9, documento.getIDTUP());
            ps.setString(10, documento.getIDLOG());
            ps.setString(11, documento.getIDEMP());
            ps.setString(12, documento.getIDPER());
            ps.setString(13, documento.getKEYDOC());
            ps.setString(14, documento.getIDDOC());


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
            String sql = "UPDATE TraDoc.DOCUMENTO SET ESTDOC='I' WHERE IDDOC LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, documento.getIDDOC());
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
            String sql = "SELECT * FROM TraDoc.DOCUMENTO";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listaDocumento = new ArrayList();
            DocumentoM documento;
            while (rs.next()) {
                documento = new DocumentoM();
                documento.setIDDOC(rs.getString("IDDOC"));
                documento.setCODDOC(rs.getString("CODDOC"));
                documento.setNUMLIBDOC(rs.getString("NUMLIBDOC"));
                documento.setNUMFOLDOC(rs.getString("NUMFOLDOC"));
                documento.setTIPDOC(rs.getString("TIPDOC"));
                documento.setFECDOC(rs.getString("FECDOC"));
                documento.setASUDOC(rs.getString("ASUDOC"));
                documento.setOBSDOC(rs.getString("OBSDOC"));
                documento.setESTDOC(rs.getString("ESTDOC"));
                documento.setIDTUP(rs.getString("IDTUP"));
                documento.setIDLOG(rs.getString("IDLOG"));
                documento.setIDEMP(rs.getString("IDEMP"));
                documento.setIDPER(rs.getString("IDPER"));
                documento.setKEYDOC(rs.getString("KEYDOC"));
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
