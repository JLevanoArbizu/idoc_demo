package controlador;

import dao.PersonaImpl;
import modelo.Persona;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Ubigeo;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

@Named(value = "personaC")
@SessionScoped
public class PersonaC extends UbigeoC implements Serializable {

    Persona persona;

    List<Persona> listaPersona;
    List<Persona> listaPersonaFiltrado;
    PersonaImpl daoPersona;

    private PieChartModel pie;

    int contadorM = 0, contadorF = 0;

    public PersonaC() throws Exception {
        daoPersona = new PersonaImpl();
        persona = new Persona();
        listaPersona = new ArrayList<>();
        listarPersonas();
    }

    public void editarPersona() throws Exception {
        try {
            seterCodigoUbigeo();
            daoPersona.editar(persona);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Editado Correctamente", null));
            listarPersonas();
            persona.clear();
        } catch (Exception e) {
        }
    }

    //No usado
    public void eliminarPersona(Persona person) throws Exception {
        try {
            daoPersona.eliminar(person);
            listarPersonas();
            persona.clear();
        } catch (Exception e) {
        }
    }

    public void registrarPersona() throws Exception {
        try {
            if (daoPersona.existe(listaPersona, persona) == false && !persona.getDNIPER().equals("00000000")) {
                seterCodigoUbigeo();
                daoPersona.registrar(persona);
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro Exitoso.", null));
                listarPersonas();
                persona.clear();
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "El DNI ingresado ya existe o es inválido.", null));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Registro Fallido.", null));
            e.printStackTrace();
        }
    }

    public void generarReporte(String IDPER) throws Exception {
        PersonaImpl reportPer = new PersonaImpl();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put(null, IDPER); //Insertamos un parametro
            reportPer.generarReporte(parameters); //Pido exportar Reporte con los parametros
//            report.exportarPDF2(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void generarReporteIndividual(String IDPER) throws Exception {
        PersonaImpl reportePerIndi = new PersonaImpl();
        try {
            Map<String, Object> parameters = new HashMap(); // Libro de parametros
            parameters.put(null, IDPER); //Insertamos un parametro
            reportePerIndi.generarReporte(parameters); //Pido exportar Reporte con los parametros
//            report.exportarPDF2(parameters);
        } catch (Exception e) {
            throw e;
        }
    }

    public void listarPersonas() throws Exception {
        try {
            listaPersona = daoPersona.listar();
            List<Persona> listaTemp = new ArrayList<>();
            for (Persona nextPersona : listaPersona) {
                if (nextPersona.getESTPER().equals("A")) {
                    System.out.println(nextPersona.toString());
                    if (nextPersona.getGENPER().equals("Masculino")) {
                        contadorM++;
                    } else {
                        contadorF++;
                    }
                }
                for (Ubigeo nextUbigeo : listaUbigeo) {
                    if (nextPersona.getCODUBI().equals(nextUbigeo.getCODUBI())) {
                        nextPersona.setCODUBI(nextUbigeo.getDISTUBI());
                        listaTemp.add(nextPersona);
                    }
                }
            }
            listaPersona = listaTemp;
            persona.clear();
            ubigeo.clear();
            createPie();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPie() {
        pie = new PieChartModel();

        ChartData data = new ChartData();

        PieChartDataSet dataSet = new PieChartDataSet();
        List<Number> values = new ArrayList<>();
        values.add(contadorM);
        values.add(contadorF);
        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 99, 132)");
        dataSet.setBackgroundColor(bgColors);

        data.addChartDataSet(dataSet);
        List<String> labels = new ArrayList<>();
        labels.add("Masculino");
        labels.add("Femenino");
        data.setLabels(labels);
        pie.setData(data);
    }

    public void seterCodigoUbigeo() throws Exception {
        ubigeo.setDISTUBI(persona.getCODUBI());
        persona.setCODUBI(obtenerCodigoUbigeo().getCODUBI());
    }

    public Persona obtenerCodigo() throws Exception {
        return daoPersona.obtenerCodigo(listaPersona, persona);
    }

    public List<String> buscarPersona(String query) throws Exception {
        return daoPersona.buscar(query, listaPersona);
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(List<Persona> listaPersona) {
        this.listaPersona = listaPersona;
    }

    public List<Persona> getListaPersonaFiltrado() {
        return listaPersonaFiltrado;
    }

    public void setListaPersonaFiltrado(List<Persona> listaPersonaFiltrado) {
        this.listaPersonaFiltrado = listaPersonaFiltrado;
    }

    public List<Ubigeo> getListaUbigeo() {
        return listaUbigeo;
    }

    public void setListaUbigeo(List<Ubigeo> listaUbigeo) {
        this.listaUbigeo = listaUbigeo;
    }

    public Ubigeo getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(Ubigeo ubigeo) {
        this.ubigeo = ubigeo;
    }

    public PieChartModel getPie() {
        return pie;
    }

    public void setPie(PieChartModel pie) {
        this.pie = pie;
    }
}
