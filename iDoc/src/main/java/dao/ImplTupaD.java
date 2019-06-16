package dao;

//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.TupaM;

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
            String sql = "UPDATE TraDoc.TUPA SET  NUMTUP=?, NOMTUP=?, PRETUP=?, PLATUP=?, ARETUP=? WHERE CODTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, tupa.getNUMTUP());
            ps.setString(2, tupa.getNOMTUP());
            ps.setDouble(3, tupa.getPRETUP());
            ps.setString(4, tupa.getPLATUP());
            ps.setString(5, tupa.getARETUP());
            ps.setString(6, tupa.getCODTUP());

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
            String sql = "UPDATE TraDoc.TUPA SET ESTTUP='I' WHERE CODTUP LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, tupa.getCODTUP());
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
//          String sql = "SELECT CODTUP,NOMTUP,PRETUP,FORMAT(FECTUP,'dd/MM/yyyy','en-gb') AS FECTUP,ARETUP FROM TUPA ";
            String sql = "SELECT *  FROM TraDoc.TUPA ";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listaTupa = new ArrayList();
            TupaM tupa;
            while (rs.next()) {
                tupa = new TupaM();
                tupa.setCODTUP(rs.getString("CODTUP"));
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
    
    

}
