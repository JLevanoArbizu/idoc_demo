package dao;

import java.util.List;

public interface IGenerica<T> {

    //Añade un registro en una tabla en la DB
    void registrar(T modelo) throws Exception;

    //Editar campos de u registro en una tabla
    void editar(T modelo) throws Exception;

    //Inactiva un campo
    void eliminar(T modelo) throws Exception;

    //Lista la tabla
    List<T> listar() throws Exception;

    //Sirve para Autocomplete
    List<String> buscar(String campo, List<T> listaModelo) throws Exception;

    //No hacer query a la DB para obtener el codigo de un campo
    T obtenerCodigo(List<T> listaModelo, T modelo) throws Exception;

    boolean existe(List<T> listaModelo, T modelo) throws Exception;
}
