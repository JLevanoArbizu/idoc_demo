package dao;

import modelo.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransparenciaImpl extends Conexion {

    public List<Transparencia> listarTransferencia(Transparencia modelo) throws Exception {
        List<Transparencia> lista = null;
        try {
            String sql = "SELECT persona.APEPATPER, persona.APEMATPER, persona.NOMPER, persona.DNIPER, "
                    + "       doc.FECDOC as fecDoc, doc.ASUDOC, doc.OBSDOC, doc.KEYDOC, "
                    + "       emisor.NOMARE, receptor.NOMARE, "
                    + "       empresa.RAZSOCEMP, empresa.RUCEMP, "
                    + "     trans.FECRECTRAN as fechaRec, "
                    + "     trans.FECSALTRAN as fechaSal,"
                    + "trans.ESTTRA, trans.OBSTRAN "
                    + "       FROM TraDoc.DOCUMENTO doc "
                    + "    INNER JOIN TraDoc.EMPRESA empresa "
                    + "        ON doc.IDEMP = empresa.IDEMP "
                    + "    INNER JOIN General.PERSONA persona "
                    + "        ON doc.IDPER = persona.IDPER "
                    + "    INNER JOIN TraDoc.TRANSFERENCIA trans "
                    + "        ON doc.IDDOC = trans.IDDOC "
                    + "    INNER JOIN General.AREA emisor "
                    + "        ON trans.IDARE_EMI = emisor.IDARE "
                    + "    INNER JOIN General.AREA receptor "
                    + "        ON trans.IDARE_REC = receptor.IDARE "
                    + "WHERE doc.KEYDOC = ? AND doc.CODDOC = ?";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, modelo.getKey());
            ps.setString(2, modelo.getCoddoc());
            ResultSet rs = ps.executeQuery();
            Transparencia transparencia;
            Documento documento;
            Transferencia transferencia;
            Empresa empresa;
            Persona persona;
            Area emisor, receptor;
            lista = new ArrayList<>();
            while (rs.next()) {
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

                documento.setFECDOC(rs.getDate(5));
                documento.setASUDOC(rs.getString(6));
                documento.setOBSDOC(rs.getString(7));
                documento.setKEYDOC(rs.getString(8));

                emisor.setNOMARE(rs.getString(9));
                receptor.setNOMARE(rs.getString(10));

                empresa.setRAZSOCEMP(rs.getString(11));
                empresa.setRUCEMP(rs.getString(12));

                transferencia.setFECRECTRAN(rs.getDate(13));
                transferencia.setFECSALTRAN(rs.getDate(14));

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    public List<Tupa> listarTupa() throws Exception {
        TupaImpl daoTupa = new TupaImpl();
        List<Tupa> lista = new ArrayList<>();
        lista = daoTupa.listar();
        return lista;
    }

    public List<Tupa> buscarTupa(String campo, List<Tupa> listaTupa) throws Exception {
        List<Tupa> lista = new ArrayList<>();
        try {
            for (Tupa tupa : listaTupa) {
                if (tupa.getNOMTUP().toUpperCase().contains(campo)
                        || tupa.getArea().getNOMARE().toUpperCase().contains(campo)) {
                    lista.add(tupa);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lista;
    }
}
