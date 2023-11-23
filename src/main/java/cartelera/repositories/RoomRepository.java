package cartelera.repositories;

import cartelera.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("select r from Room r where r.cinema.id = ?1")
    List<Room> findAllByCinema_Id(Long id);

    @Query("select r from Room r where r.film.id = ?1")
    List<Room> findAllByFilm_Id(Long id);
}