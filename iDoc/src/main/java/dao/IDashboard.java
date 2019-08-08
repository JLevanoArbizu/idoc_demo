package dao;

import java.util.List;
import org.primefaces.model.chart.BarChartModel;

public interface IDashboard<T> {

    public BarChartModel generarBar(T modelo) throws Exception;

    public BarChartModel generarBar(List<T> modelo) throws Exception;

    public List<T> listarCantidades() throws Exception;

    public List<T> listarCantidades(T modelo) throws Exception;
}
