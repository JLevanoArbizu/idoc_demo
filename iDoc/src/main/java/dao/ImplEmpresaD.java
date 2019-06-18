package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.EmpresaM;

public class ImplEmpresaD extends Conexion implements IGenerica<EmpresaM> {

    @Override
    public void registrar(EmpresaM empresa) throws Exception {
        try {
            this.conectar();
            String sql = "INSERT INTO EMPRESA(RAZSOCEMP,RUCEMP,DIREMP,CODUBI) VALUES(?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, empresa.getRAZSOCEMP());
            ps.setString(2, empresa.getRUCEMP());
            ps.setString(3, empresa.getDIREMP());
            ps.setString(4, empresa.getCODUBI());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(EmpresaM empresa) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE EMPRESA SET RAZSOCEMP=?, RUCEMP=?, DIREMP=?, CODUBI=? WHERE CODEMP LIKE ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);

            ps.setString(1, empresa.getRAZSOCEMP());
            ps.setString(2, empresa.getRUCEMP());
            ps.setString(3, empresa.getDIREMP());
            ps.setString(4, empresa.getCODUBI());
            ps.setString(5, empresa.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void eliminar(EmpresaM empresa) throws Exception {
        try {
            this.conectar();
            String sql = "UPDATE EMPRESA SET ESTEMP = 'I' WHERE CODEMP LIKE ?";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            ps.setString(1, empresa.getCODEMP());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<EmpresaM > listar() throws Exception {
        List<EmpresaM> listadoEmpresa;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM VW_EMPRESA WHERE ESTEMP LIKE 'A'";
            PreparedStatement ps = this.conectar().prepareCall(sql);
            rs = ps.executeQuery();
            listadoEmpresa = new ArrayList();
            EmpresaM empresa;
            while (rs.next()) {
                empresa = new EmpresaM();
                empresa.setCODEMP(rs.getString("CODEMP"));
                empresa.setRAZSOCEMP(rs.getString("RAZSOCEMP"));
                empresa.setRUCEMP(rs.getString("RUCEMP"));
                empresa.setDIREMP(rs.getString("DIREMP"));
                empresa.setESTEMP(rs.getString("ESTEMP"));
                empresa.setUBIGEO(rs.getString("UBIGEO"));
                empresa.setCODUBI(rs.getString("CODUBI"));
                listadoEmpresa.add(empresa);
            }
            return listadoEmpresa;
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<String> buscar(String campo, List<EmpresaM> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EmpresaM obtenerCodigo(List<EmpresaM> listaModelo, EmpresaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean existe(List<EmpresaM> listaModelo, EmpresaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporte(EmpresaM modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
