package ru.rit.personsearch.repository;

import org.slf4j.Logger;
import ru.rit.personsearch.model.Car;
import ru.rit.personsearch.model.City;
import ru.rit.personsearch.to.PersonTo;
import ru.rit.personsearch.util.SqlHelper;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.slf4j.LoggerFactory.getLogger;

public class JdbcPersonRepository implements PersonRepository {
    private static final Logger log = getLogger(JdbcPersonRepository.class);
    private final SqlHelper sqlHelper;

    private final String commonQuery = "select p.id, p.first_name, p.last_name, p.patronymic, ca.model, ca.license, ci.city_name " +
            "from persons p " +
            "left outer join cars ca on p.id = ca.person_id " +
            "join cities ci on p.city_id = ci.id";

    public JdbcPersonRepository(String dbUrl, String dbUser, String dbPassword) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.debug("No postgres driver found.");
            throw new IllegalStateException(e);
        }
        sqlHelper = new SqlHelper(() -> DriverManager.getConnection(dbUrl, dbUser, dbPassword));
    }

    @Override
    public List<PersonTo> get(Map<String, String> requestParams) {
        StringBuilder stringBuilder = new StringBuilder(commonQuery + " where ");
        Iterator<String> iterator = requestParams.keySet().iterator();
        while (iterator.hasNext()) {
            stringBuilder.append("upper(");
            stringBuilder.append(iterator.next());
            stringBuilder.append(") like ?");
            if (iterator.hasNext()) {
                stringBuilder.append(" and ");
            }
        }
        return sqlHelper.queryExecute(stringBuilder.toString(),
                preparedStatement -> {
                    int i = 1;
                    for (String value : requestParams.values()) {
                        preparedStatement.setString(i++, value.toUpperCase() + "%");
                    }
                    log.debug("Query string: " + preparedStatement);
                    return getPersons(preparedStatement);
                });
    }

    @Override
    public List<PersonTo> getAll() {
        return sqlHelper.queryExecute(commonQuery, this::getPersons);
    }

    private ArrayList<PersonTo> getPersons(PreparedStatement preparedStatement) throws SQLException {
        ResultSet resultSet = preparedStatement.executeQuery();
        Map<String, PersonTo> personMap = new ConcurrentHashMap<>();

        if (!resultSet.next()) return new ArrayList<>();

        do {
            PersonTo personTo = new PersonTo(
                    Integer.parseInt(resultSet.getString("id")),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name"),
                    resultSet.getString("patronymic"));
            personTo.setCity(new City(resultSet.getString("city_name")));
            personMap.putIfAbsent(resultSet.getString("id"), personTo);
            personMap
                    .get(resultSet.getString("id"))
                    .addCar(new Car(resultSet.getString("model"), resultSet.getString("license")));
        } while (resultSet.next());

        log.debug("Found " + personMap.values().size() + " values in database.");

        return new ArrayList<>(personMap.values());
    }

}