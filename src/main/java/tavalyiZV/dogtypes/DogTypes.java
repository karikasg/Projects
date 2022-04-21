package tavalyiZV.dogtypes;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class DogTypes {

    private JdbcTemplate jdbcTemplate;


    public DogTypes(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<String> getDogsByCountry(String country) {

        return jdbcTemplate.query("select name from dog_types where country = ? ORDER BY name",
                (rs, i) -> rs.getString("name").toLowerCase(),
                country);
    }
}
