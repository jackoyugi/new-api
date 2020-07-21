import com.google.gson.Gson;
import dao.Sql2oDepartmentDao;
import dao.Sql2oEmployeesDao;
import dao.Sql2oNewsDao;
import exceptions.ApiException;
import models.Department;
import models.Employees;
import models.News;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

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

        //post: Add news
        post("/news/new","application/json",(request, response) -> {
            News news = gson.fromJson(request.body(), News.class);
            newsDao.add(news);
            response.type("application/json");
            response.status(201);
            return gson.toJson(news);
        });

        //get:View All news
        get("/news","application/json",(request, response) -> {
            int departmentId = Integer.parseInt(request.params("departmentId"));
            return gson.toJson(newsDao.getAllNews());
        });
        //post:Add department
        post("/department/new","application/json",(request, response) -> {
            Department department = gson.fromJson(request.body(), Department.class);
            departmentDao.add(department);

            response.type("application/json");
            response.status(201);
            return gson.toJson(departmentDao.getAll());
        });

        //get:view all departments
        get("/department","application/json",(request, response) -> {
            return gson.toJson(departmentDao.getAll());
        });
        //post: Add news to department
        post("/department/:id/news/new","application/json",(request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            News news = gson.fromJson(request.body(),News.class);
            news.setId(id);
            newsDao.add(news);
            response.type("application/json");
            response.status(201);
            return gson.toJson(news);
        });

        //get:View all news for department
        get("/departments/:id/depNews", "application/json", (request, response) -> {
            int id = Integer.parseInt(request.params("id"));
            return gson.toJson(newsDao.getAllNews());
        });

        //post:add a user(employees)
        post("/employees/new", "application/json", (request, response) -> {
            Employees employees = gson.fromJson(request.body(), Employees.class);
            employeesDao.add(employees);
            response.type("application/json");
            response.status(201);
            return gson.toJson(employees);
        });

        //Get: View all users
        get("/users", "application/json", (request, response) -> {
            return gson.toJson(employeesDao.getAllEmployees());
        });

        //post:Add a department to a user
        post("employees/employeeId/department/:departmentId","application/json",(request, response) -> {
            int emplyeeId = Integer.parseInt(request.params("employeeId"));
            int departmentId = Integer.parseInt(request.params("departmentId"));
            Employees employeesFound = employeesDao.findById(emplyeeId);
            Department departmentfound = departmentDao.findById(departmentId);

            if (departmentfound != null && employeesFound != null){
                departmentDao.addDepartmentToEmployees(departmentfound,employeesFound);
                response.type("application/json");
                response.status(201);
                return gson.toJson("Employees and Department have been associated");
            }
            else {
                throw new ApiException(404, "Employee or Department does not exist");
            }
        });

        //get:View all departments a user belongs to

        get("/employees/:employeeId/department","application/json",(request, response) -> {
            int employeeId = Integer.parseInt(request.params("employeeId"));
            Employees employees = employeesDao.findById(employeeId);

            if (employees == null){
                throw new Exception("No Employee with that id");
            }else if(employeesDao.getAllDepartmentsForEmployee(employeeId).size() == 0){
                return "{\"message\":\"Employee not associated with any department\"}";
            }else {
                return gson.toJson(employeesDao.getAllDepartmentsForEmployee(employeeId));
            }
        });

        exception(ApiException.class, (exception, request, response) -> {
            ApiException err = exception;
            Map<String, Object> jsonMap = new HashMap<>();
            jsonMap.put("status", err.getStatusCode());
            jsonMap.put("errorMessage", err.getMessage());
            response.type("application/json");
            response.status(err.getStatusCode());
            response.body(gson.toJson(jsonMap));
        });


        after((request, response) ->{
            response.type("application/json");
        });


        //get: get all
        get("/", (request, response) -> {
            Map< String, Object > model = new HashMap<>();
            return new ModelAndView(model, "news.hbs");
        }, new HandlebarsTemplateEngine());

        //get:department new
        get("/new/department", (request, response) -> {
            Map< String, Object > model = new HashMap<>();
            return new ModelAndView(model, "department-form.hbs");
        }, new HandlebarsTemplateEngine());

    }
}
