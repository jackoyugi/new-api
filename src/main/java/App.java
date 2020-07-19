import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oEmployeesDao;
import dao.Sql2oNewsDao;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static spark.Spark.port;
import static spark.Spark.staticFileLocation;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");
        ProcessBuilder processBuilder = new ProcessBuilder();
        Integer port;
        if (processBuilder.environment().get("PORT") != null) {
            port = Integer.parseInt(processBuilder.environment().get("PORT"));
        } else {
            port = 4567;
        }
        port(port);
        Sql2oDepartmentDao departmentDao;
        Sql2oNewsDao newsDao;
        Sql2oEmployeesDao employeesDao;
        Connection conn;
        Gson gson = new Gson();

        String connectingString = "jdbc:postgresql://localhost:5432/newsapi_test";
        Sql2o sql2o = new Sql2o(connectingString, "jackoyugi", "00100");
        departmentDao = new Sql2oDepartmentDao(sql2o);
        newsDao = new Sql2oNewsDao(sql2o);
        employeesDao = new Sql2oEmployeesDao(sql2o);
        conn = sql2o.open();

    }
}
