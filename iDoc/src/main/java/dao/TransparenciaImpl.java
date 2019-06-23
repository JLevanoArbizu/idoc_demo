package dao;

import modelo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TransparenciaImpl extends Conexion implements IGenerica<Transparencia> {
    @Override
    public void registrar(Transparencia modelo) throws Exception {

    }

    @Override
    public void editar(Transparencia modelo) throws Exception {

    }

    @Override
    public void eliminar(Transparencia modelo) throws Exception {

    }

    @Override
    public List<Transparencia> listar() throws Exception {
        return null;
    }

    public List<Transparencia> listar(Transparencia modelo) throws Exception {
        List<Transparencia> lista = null;
        try {
            String sql = "SELECT persona.APEPATPER, persona.APEMATPER, persona.NOMPER, persona.DNIPER, " +
                    "       doc.FECDOC, doc.ASUDOC, doc.OBSDOC, doc.KEYDOC, " +
                    "       emisor.NOMARE, receptor.NOMARE, " +
                    "       empresa.RAZSOCEMP, empresa.RUCEMP, " +
                    "       trans.FECRECTRAN, trans.FECSALTRAN, trans.ESTTRA, trans.OBSTRAN " +
                    "       FROM TraDoc.DOCUMENTO doc " +
                    "    INNER JOIN TraDoc.EMPRESA empresa " +
                    "        ON doc.IDEMP = empresa.IDEMP " +
                    "    INNER JOIN General.PERSONA persona " +
                    "        ON doc.IDPER = persona.IDPER " +
                    "    INNER JOIN TraDoc.TRANSFERENCIA trans " +
                    "        ON doc.IDDOC = trans.IDDOC " +
                    "    INNER JOIN General.AREA emisor " +
                    "        ON trans.IDARE_EMI = emisor.IDARE " +
                    "    INNER JOIN General.AREA receptor " +
                    "        ON trans.IDARE_REC = receptor.IDARE " +
                    "WHERE persona.DNIPER = ? AND doc.KEYDOC = ? AND doc.IDDOC = ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getDni());
            ps.setString(2, modelo.getKey());
            ps.setString(3, modelo.getIdtra());
            ResultSet rs = ps.executeQuery();
            Transparencia transparencia;
            Documento documento;
            Transferencia transferencia;
            Empresa empresa;
            Persona persona;
            Area emisor, receptor;
            lista = new ArrayList<>();
            while (rs.next()){
                transparencia = new Transparencia();
                documento = new Documento();
                transferencia = new Transferencia();
                empresa = new Empresa();
                persona = new Persona();
                emisor = new Area();
                receptor = new Area();

                persona.setAPEPATPER(rs.getString(1));
                persona.setAPEMATPER(rs.getString(2));
                persona.setNOMPER(rs.getString(3));
                persona.setDNIPER(rs.getString(4));
                persona.setCOMPLETO(persona.getAPEPATPER()+" "+persona.getAPEMATPER()+", "+persona.getNOMPER());

                documento.setFECDOC(String.valueOf(rs.getDate(5)));
                documento.setASUDOC(rs.getString(6));
                documento.setOBSDOC(rs.getString(7));
                documento.setKEYDOC(rs.getString(8));

                emisor.setNOMARE(rs.getString(9));
                receptor.setNOMARE(rs.getString(10));

                empresa.setRAZSOCEMP(rs.getString(11));
                empresa.setRUCEMP(rs.getString(12));

                transferencia.setFECRECTRAN(String.valueOf(rs.getDate(13)));
                transferencia.setFECSALTRAN(String.valueOf(rs.getDate(14)));

                transferencia.setOBSTRAN(rs.getString(16));

                transparencia.setDocumento(documento);
                transparencia.setTransferencia(transferencia);
                transparencia.setEmpresa(empresa);
                transparencia.setPersona(persona);
                transparencia.setEmisor(emisor);
                transparencia.setReceptor(receptor);
                lista.add(transparencia);
            }
            rs.close();
            ps.clearParameters();
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.desconectar();
        }
        return lista;
    }
    @Override
    public List<String> buscar(String campo, List<Transparencia> listaModelo) throws Exception {
        return null;
    }

    @Override
    public Transparencia obtenerCodigo(List<Transparencia> listaModelo, Transparencia modelo) throws Exception {
        return null;
    }

    @Override
    public boolean existe(List<Transparencia> listaModelo, Transparencia modelo) throws Exception {
        return false;
    }

    @Override
    public void generarReporte(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void generarReporteIndividual(Map parameters) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
