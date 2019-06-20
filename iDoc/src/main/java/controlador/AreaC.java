package controlador;

import dao.AreaImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Area;

@Named(value = "areaC")
@SessionScoped
public class AreaC implements Serializable {

    MunicipalidadC muni;
    Area area;
    List<Area> listaArea;
    AreaImpl daoArea;

    public AreaC() {
        try {
            area = new Area();
            listaArea = new ArrayList<>();

            daoArea = new AreaImpl();
            muni = new MunicipalidadC();
        } catch (Exception e) {
        }

    }

    @PostConstruct
    public void initA() {
        try {
            muni.listarMunicipalidad();
            listar();

        } catch (Exception e) {
        }

    }

    public void generarReporte(Area are)throws Exception{
        try {
            daoArea.generarReporte(are);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void listar() throws Exception {
        try {
            listaArea = daoArea.listar();

            muni.municipalidad.clear();
            area.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registrarArea() throws Exception {
        try {
            setearCodigos();
            daoArea.registrar(area);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Agregado Correctamente", null));
            listar();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editarArea() throws Exception {
        try {
            setearCodigos();
            daoArea.editar(area);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado Correctamente", null));
            listar();
        } catch (Exception e) {
        }
    }

    public void eliminarArea(Area ar) throws Exception {
        try {
            daoArea.eliminar(ar);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminación Exitosa", null));
            listar();
        } catch (Exception e) {
        }
    }

    public void setearCodigos() throws Exception {
        try {
            area.setIDARE_PADR(daoArea.obtenerCodigo(listaArea, area).getIDARE_PADR());
            area.setIDMUN(daoArea.obtenerCodigo(listaArea, area).getIDMUN());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> buscarArea(String nombreArea) throws Exception {
        return daoArea.buscar(nombreArea, listaArea);
    }

    public MunicipalidadC getMuni() {
        return muni;
    }

    public void setMuni(MunicipalidadC muni) {
        this.muni = muni;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public List<Area> getListaArea() {
        return listaArea;
    }

    public void setListaArea(List<Area> listaArea) {
        this.listaArea = listaArea;
    }
}
