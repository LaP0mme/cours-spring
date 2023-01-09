package monprojet.dao;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import monprojet.entity.City;
import monprojet.entity.Country;
import monprojet.dto.PaysPop;

// This will be AUTO IMPLEMENTED by Spring

public interface CountryRepository extends JpaRepository<Country, Integer> {
    @Query(value = "SELECT SUM(population) " +
            "FROM City " +
            "WHERE country_id = :id",
            nativeQuery= true)
    public Integer getPopPays(Integer id);

    @Query(value = "SELECT Country.name as nom, SUM(population) as pop " +
            "FROM City INNER JOIN Country On Country.id = City.country_id " +
            "GROUP BY City.country_id",
            nativeQuery = true)
    public List<PaysPop> getListPopulationPays();
}