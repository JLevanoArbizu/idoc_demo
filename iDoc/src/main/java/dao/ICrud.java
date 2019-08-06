package dao;

import java.util.HashSet;

public interface ICrud<T> {

    void registrar(T modelo) throws Exception;

    void editar(T modelo) throws Exception;

    void eliminar(T modelo) throws Exception;

    HashSet<T> listar() throws Exception;

    HashSet<T> listar(T modelo) throws Exception;

    T obtenerModelo(T modelo) throws Exception;

}
