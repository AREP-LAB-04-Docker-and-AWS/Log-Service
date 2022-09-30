package edu.eci.arep.logService;

import edu.eci.arep.logService.service.MongoService;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class SparkWebServer {

    private static final MongoService mongoService;

    static {
        mongoService = new MongoService();
    }

    public static void main( String[] args ) {
        port(getPort());
        get("/get/hello", SparkWebServer::getGreeting);
        get("/get/strings", SparkWebServer::uploadAStringAndGetLastStrings);
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }

    private static String getGreeting(Request request, Response response) {
        response.type("text/html");
        return "Hello, Spark!";
    }

    private static String uploadAStringAndGetLastStrings(Request request, Response response) {
        response.type("application/json");
        String newString = request.queryParams("newString");
        return mongoService.uploadAStringAndGetLastStrings(newString);
    }
}