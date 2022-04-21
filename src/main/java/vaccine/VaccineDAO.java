package vaccine;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.util.Optional;

public class VaccineDAO {

    private JdbcTemplate jdbcTemplate;

    public VaccineDAO(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Optional<String> getCityByZip(String zip) {
        String result;
        try {
            result = jdbcTemplate.queryForObject("select city from cities where zip=?", (rs, i) -> rs.getString("city"), zip);
        }
        catch (EmptyResultDataAccessException erdae) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public boolean saveCitizen(Citizen citizen) {

        if (getCitizenByTaj(citizen.getTajNr()).isEmpty()) {
            jdbcTemplate.update("insert into citizens " +
                            "(citizen_name, zip, age, email, taj, number_of_vaccination, last_vaccination)" +
                            " values (?,?,?,?,?,?,?)",
                    citizen.getName(),
                    citizen.getZip(),
                    citizen.getAge(),
                    citizen.getEmail(),
                    citizen.getTajNr(),
                    citizen.getNumberOfVaccination(),
                    citizen.getLastVaccination());
            return true;
        }
        System.out.println("Már létezik ezzel a TAJ számmal regisztráció!");
        return false;
    }

    public Optional<Citizen> getCitizenByTaj(String taj) {
        Citizen result;
        try {
            result = jdbcTemplate.queryForObject("select * from citizens where taj = ?", (rs, i) ->
            {
                String name = rs.getString("citizen_name");
                String zip = rs.getString("zip");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                String tajNr = rs.getString("taj");
                int numberOfVaccination = rs.getInt("number_of_vaccination");
                LocalDate lastVaccination = rs.getDate("last_vaccination").toLocalDate();
                return new Citizen(name, zip, age, email, tajNr, numberOfVaccination, lastVaccination);
            }, taj);
        }
        catch (EmptyResultDataAccessException erdae) {
            return Optional.empty();
        }
        return Optional.of(result);
    }

    public void vaccinate(String taj) {
//        jdbcTemplate.update("update (")
    }
}
