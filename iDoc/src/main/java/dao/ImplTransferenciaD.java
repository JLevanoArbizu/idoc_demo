package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.TransferenciaM;

public class ImplTransferenciaD extends Conexion implements IGenerica<TransferenciaM> {

    @Override
    public void registrar(TransferenciaM trans) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO TraDoc.TRANSFERENCIA (FECSALTRAN,FECRECTRAN,OBSTRAN,IDDOC,IDARE_EMI,IDARE_REC) VALUES(CONVERT(DATE,?,103),CONVERT(DATE,?,103),?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, trans.getFECSALTRAN());
            ps.setString(2, trans.getFECRECTRAN());
            ps.setString(3, trans.getOBSTRAN());
            ps.setString(4, trans.getIDDOC());
            ps.setString(5, trans.getIDARE_EMI());
            ps.setString(6, trans.getIDARE_REC());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            System.out.println();
            this.desconectar();
        }
    }

    @Override
    public void editar(TransferenciaM trans) throws Exception {

        try {
            this.conectar();
            String sql = "UPDATE TraDoc.TRANSFERENCIA SET  FECSALTRA=?, FECRECTRA=?, OBSTRA=?, ESTTRA=?, IDDOC=?, IDARE_EMI=?, IDARE_REC=? WHERE IDTRAN LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, trans.getFECSALTRAN());
            ps.setString(2, trans.getFECRECTRAN());
            ps.setString(3, trans.getOBSTRAN());
            ps.setString(4, trans.getESTTRA());
            ps.setString(5, trans.getIDDOC());
            ps.setString(6, trans.getIDARE_EMI());
            ps.setString(7, trans.getIDARE_REC());
            ps.setString(8, trans.getIDTRAN());
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
            String sql = "UPDATE TraDoc.TRANSFERENCIA SET ESTTRA='I' WHERE IDTRAN LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, trans.getIDTRAN());
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
        try {
            String sql = "SELECT * FROM TRADOC.TRANSFERENCIA WHERE TRADOC.TRANSFERENCIA.ESTTRA = 'A'";
            
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            listaTransferencia = new ArrayList();
            TransferenciaM trans;
            while (rs.next()) {
                trans = new TransferenciaM();
                trans.setIDTRAN(String.valueOf(rs.getInt("IDTRAN")));
                trans.setFECRECTRAN(rs.getString("FECRECTRAN"));
                trans.setFECSALTRAN(rs.getString("FECSALTRAN"));
                trans.setOBSTRAN(rs.getString("OBSTRAN"));
                trans.setESTTRA(rs.getString("ESTTRA"));
                trans.setIDDOC(rs.getString("IDDOC"));
                trans.setIDARE_EMI(rs.getString("IDARE_EMI"));
                trans.setIDARE_REC(rs.getString("IDARE_REC"));
                listaTransferencia.add(trans);
            }
            return listaTransferencia;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return null;

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
    public void generarReporte(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
