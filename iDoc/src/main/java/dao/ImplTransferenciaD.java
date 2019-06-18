package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.TransferenciaM;

public class ImplTransferenciaD extends Conexion implements IGenerica<TransferenciaM> {

    @Override
    public void registrar(TransferenciaM trans) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO TRANSFERENCIA VALUES(?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, trans.getFECENTTRAN());
            ps.setString(2, trans.getFECSALTRAN());
            ps.setString(3, trans.getFECRECTRAN());
            ps.setString(4, trans.getOBSTRAN());
            ps.setString(5, trans.getCODDOC());
            ps.setString(6, trans.getCODAREORI());
            ps.setString(7, trans.getCODAREDES());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(TransferenciaM trans) throws Exception {

        try {
            this.conectar();
            String sql = "UPDATE TRANSFERENCIA SET FECENTTRA=?, FECSALTRA=?, FECRECTRA=?, OBSTRA=?, CODDOC=?, CODAREORI=?, CODAREDES=? WHERE CODTRA LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, trans.getFECENTTRAN());
            ps.setString(2, trans.getFECSALTRAN());
            ps.setString(3, trans.getFECRECTRAN());
            ps.setString(4, trans.getOBSTRAN());
            ps.setString(5, trans.getCODDOC());
            ps.setString(6, trans.getCODAREORI());
            ps.setString(7, trans.getCODAREDES());
            ps.setString(8, trans.getCODTRAN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(TransferenciaM trans) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE TRANSFERENCIA SET ESTTRAN='I' WHERE CODTRAN LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, trans.getCODTRAN());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<TransferenciaM> listar() throws Exception {
        List<TransferenciaM> listaTransferencia;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM TRANSFERENCIA WHERE ESTPER LIKE 'A'";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listaTransferencia = new ArrayList();
            TransferenciaM trans;
            while (rs.next()) {
                trans = new TransferenciaM();
                trans.setCODTRAN(rs.getString("CODTRAN"));
                trans.setFECENTTRAN(rs.getString("FECENTTRAN"));
                trans.setFECSALTRAN(rs.getString("FECSALTRAN"));
                trans.setFECRECTRAN(rs.getString("FECRECTRAN"));
                trans.setOBSTRAN(rs.getString("OBSTRAN"));
                trans.setCODDOC(rs.getString("CODDOC"));
                trans.setCODAREORI(rs.getString("CODAREORI"));
                trans.setCODAREDES(rs.getString("CODAREDES"));
                listaTransferencia.add(trans);
            }
            return listaTransferencia;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }

    }

    @Override
    public List<String> buscar(String campo, List<TransferenciaM> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TransferenciaM obtenerCodigo(List<TransferenciaM> listaModelo, TransferenciaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<TransferenciaM> listaModelo, TransferenciaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(TransferenciaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
