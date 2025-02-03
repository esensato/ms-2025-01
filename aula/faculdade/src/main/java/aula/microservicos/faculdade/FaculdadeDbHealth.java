package aula.microservicos.faculdade;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("dbFaculdade")
public class FaculdadeDbHealth implements HealthIndicator {

    // Pacote javax.sql
    @Autowired
    DataSource ds;

    @Override
    public Health health() {

        try (Connection conn = ds.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.execute("select COUNT(*) from TAB_ALUNO");
        } catch (SQLException ex) {
            return Health.outOfService().withException(ex).build();
        }
        return Health.up().build();
    }

}
