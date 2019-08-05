package dao;

import modelo.Ubigeo;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.StreamedContent;

public class UbigeoImpl extends Conexion implements ICrud<Ubigeo> {

    @Override
    public void registrar(Ubigeo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editar(Ubigeo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar(Ubigeo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ubigeo> listar() throws Exception {
        List<Ubigeo> listaTemporal = null;
        try {
            String sql = "SELECT * FROM GENERAL.UBIGEO WHERE GENERAL.UBIGEO.PROVUBI = 'CAÑETE'";
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);
            listaTemporal = new ArrayList<>();
            Ubigeo ubigeo;
            while (rs.next()) {
                ubigeo = new Ubigeo();
                ubigeo.setCODUBI(rs.getString("CODUBI"));
                ubigeo.setDEPUBI(rs.getString("DEPUBI"));
                ubigeo.setPROVUBI(rs.getString("PROVUBI"));
                ubigeo.setDISTUBI(rs.getString("DISTUBI"));
                listaTemporal.add(ubigeo);
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return listaTemporal;
    }

    @Override
    public Ubigeo obtenerModelo(List<Ubigeo> listaModelo, Ubigeo modelo) throws Exception {
        //Evita hacer una colsulta para obtener el código
        for (Ubigeo ubigeo1 : listaModelo) {
            if (ubigeo1.getDISTUBI().equals(modelo.getDISTUBI())) {
                modelo.setCODUBI(ubigeo1.getCODUBI());
                return modelo;
            }
        }
        return null;
    }

    @Override
    public boolean existe(List<Ubigeo> listaModelo, Ubigeo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Ubigeo> listar(Ubigeo modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
