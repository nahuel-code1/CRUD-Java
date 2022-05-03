package com.example.application.data.repository;

import com.example.application.data.entity.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    // 1- Utiliza la anotación @Query para definir una consulta personalizada. En este caso, comprueba si la cadena coincide con el nombre o el apellido,
    // e ignora el caso. La consulta utiliza Java Persistence Query Language (JPQL), que es un lenguaje similar a SQL para consultar bases de datos 
    // administradas por JPA.

    // 2- Agreguamos el método search() al repositorio de contactos para proporcionar a la clase de servicio el método necesario para filtrar contactos.

    @Query("select c from Contact c " +
        "where lower(c.firstName) like lower(concat('%', :searchTerm, '%')) " +
        "or lower(c.lastName) like lower(concat('%', :searchTerm, '%'))") // 1
    List<Contact> search(@Param("searchTerm") String searchTerm);// 2
}
