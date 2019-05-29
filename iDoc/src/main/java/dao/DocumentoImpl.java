package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import modelo.Documento;
import org.apache.commons.lang3.text.WordUtils;

public class DocumentoImpl extends Conexion implements IGenerica<Documento> {

    @Override
    public void registrar(Documento modelo) throws Exception {
        try {
            String sql = "INSERT INTO REGCIV.DOCUMENTO "
                    + "(IDMUN, IDLOG, IDPER, NUMLIBDOC, NUMFOLDOC, FECREGDOC, OBSDOC, CODUBI, DIRACT, FECACT, TIPDOC, ESTDOC) "
                    + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setInt(1, 6);
            ps.setInt(2, 1);
            ps.setInt(3, Integer.valueOf(modelo.getIDPER()));
            ps.setString(4, modelo.getNUMLIBDOC());
            ps.setString(5, modelo.getNUMFOLDOC());
            ps.setObject(6, modelo.getFECREGDOC(), java.sql.Types.DATE);
            ps.setString(7, WordUtils.capitalize(modelo.getOBSDOC()));
            ps.setString(8, modelo.getCODUBI());
            ps.setString(9, WordUtils.capitalize(modelo.getDIRACT()));
            ps.setObject(10, modelo.getFECACT(), java.sql.Types.DATE);
            ps.setString(11, modelo.getTIPDOC());
            ps.setString(12, "A");
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void editar(Documento modelo) throws Exception {
        try {
            eliminar(modelo);
            registrar(modelo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(Documento modelo) throws Exception {
        try {
            String sql = "UPDATE REGCIV.DOCUMENTO SET "
                    + "ESTDOC=? "
                    + "WHERE IDDOC=? ";
            PreparedStatement ps = this.conectar().prepareStatement(sql);
            ps.setString(1, "I");
            ps.setInt(2, Integer.valueOf(modelo.getIDDOC()));
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
    }

    @Override
    public List<Documento> listar() throws Exception {
        List<Documento> lista = null;
        try {
            String sql = "SELECT "
                    + "D.IDDOC, "
                    + "D.IDMUN, "
                    + "D.IDPER, "
                    + "CONCAT(personaDocumento.APEPATPER, ' ',personaDocumento.APEMATPER, ', ',personaDocumento.NOMPER ) AS datosTitular, "
                    + "D.NUMLIBDOC, "
                    + "D.NUMFOLDOC, "
                    + "D.FECREGDOC, "
                    + "D.OBSDOC, "
                    + "D.CODUBI, "
                    + "D.DIRACT, "
                    + "D.FECACT, "
                    + "D.TIPDOC, "
                    + "D.ESTDOC, "
                    + "A.IDACT, "
                    + "A.IDDOC,	"
                    + "A.IDPER,	"
                    + "CONCAT(personaActor.APEPATPER, ' ',personaActor.APEMATPER, ', ',personaActor.NOMPER ) AS datosActor, "
                    + "A.TIPACT "
                    + "FROM RegCiv.ACTOR AS A "
                    + "INNER JOIN General.PERSONA AS personaActor "
                    + "ON A.IDPER = personaActor.IDPER "
                    + "FULL OUTER JOIN RegCiv.DOCUMENTO AS D "
                    + "ON A.IDDOC = D.IDDOC "
                    + "INNER JOIN General.PERSONA AS personaDocumento "
                    + "ON D.IDPER = personaDocumento.IDPER ";
            Documento documento;
            lista = new ArrayList<>();
            ResultSet rs = this.conectar().createStatement().executeQuery(sql);

            while (rs.next()) {
                documento = new Documento();

                documento.setIDDOC(String.valueOf(rs.getInt(1)));
                documento.setIDMUN(String.valueOf(rs.getInt(2)));
                documento.setIDPER(String.valueOf(rs.getInt(3)));
                documento.setTitular(rs.getString(4));
                documento.setNUMLIBDOC(rs.getString(5));
                documento.setNUMFOLDOC(rs.getString(6));
                documento.setFECREGDOC((rs.getDate(7)));
                documento.setOBSDOC(rs.getString(8));
                documento.setCODUBI(rs.getString(9));
                documento.setDIRACT(rs.getString(10));
                documento.setFECACT((rs.getDate(11)));
                documento.setTIPDOC(rs.getString(12));
                documento.setESTDOC(rs.getString(13));
                String tipoActor = rs.getString(18);
                if (tipoActor != null) {
                    switch (tipoActor) {
                        case "1":
                            documento.setEsposa(rs.getString(17));
                            break;
                        case "2":
                            documento.setDeclarante(rs.getString(17));
                            break;
                        case "3":
                            documento.setPapa(rs.getString(17));
                            break;
                        case "4":
                            documento.setMama(rs.getString(17));
                            break;
                        case "5":
                            documento.setCelebrante(rs.getString(17));
                            break;
                        case "6":
                            documento.setMedico(rs.getString(17));
                            break;
                        default:
                            break;
                    }
                }
                lista.add(documento);
            }
            rs.clearWarnings();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.desconectar();
        }
        return lista;
    }

    @Override
    public List<String> buscar(String campo, List<Documento> listaModelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Documento obtenerCodigo(List<Documento> listaModelo, Documento modelo) throws Exception {
        modelo.setFECREGDOC(modelo.getFECREGDOC());
        for (Documento documento1 : listaModelo) {
            if (modelo.getFECREGDOC().equals(documento1.getFECREGDOC())
                    && modelo.getIDPER().equals(documento1.getIDPER())
                    && modelo.getTIPDOC().equals(documento1.getTIPDOC())) {
                modelo.setIDDOC(documento1.getIDDOC());
            }
        }
        return modelo;

    }

    @Override
    public boolean existe(List<Documento> listaModelo, Documento modelo) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
