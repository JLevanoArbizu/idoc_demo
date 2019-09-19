package dao;

import org.primefaces.model.StreamedContent;

public interface IReporte<T> {

    public void generarReporteIndividual(T modelo) throws Exception;

    public void generarReporteGeneral(T modelo) throws Exception;

    public StreamedContent generarReporteIndividualPrev(T modelo) throws Exception;

    public StreamedContent generarReporteGeneralPrev(T modelo) throws Exception;

}
