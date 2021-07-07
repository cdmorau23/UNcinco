package com.UN5.market.domain.repository;

import com.UN5.market.domain.Rest;

import java.util.List;
import java.util.Optional;

public interface RestRepository {
    Rest getRest(int restId);
    Rest save(Rest rest);
    void delete(int restId);
    List<Rest> adminRests(int adminId);
    void updateRest(String name, String slogan, int tableTotal, int restId);

    /*metodos necesarios:
    - dado cierto id de administrador obtener la lista de todos los restaurantes asociados a este
    - dado cierto nombre obtener la lista de restaurantes de la lista anterior con ese nombre
    - insertar un nuevo restaurante dando los datos correspondientes, uso en el menu de agregar restaurante (debe asociarse al administrador que lo crea)
    - modificar restaurante dados los datos correspondientes, uso en el menu de detalles restaurante funcionalidad actualizar restaurante
    - eliminar restaurante, uso en el menu de detalles restaurante funcionalidad eliminar restaurante
     */
}
