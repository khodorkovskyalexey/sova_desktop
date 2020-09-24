package server_connector;

import com.google.gson.Gson;
import database_classes.*;

public class JsonTranslater {
    public static Person[] getUsers(String json) {
        //String json = "{\"id\":1,\"name\":\"Vova\",\"login\":\"vovan\",\"password\":\"pass\",\"status\":\"\",\"student_group\":null}";
        //System.out.println(json);
        Gson gson = new Gson();
        Person[] some_users = gson.fromJson(json, Person[].class);
        return some_users;
    }

    public static Person getUser(String json) {
        Person[] users = getUsers(json);
        if(users.length == 1) return users[0];
        else if(users.length > 1) {
            System.err.println("JsonTranslater.java 16:Users > 1");
            return users[0];
        } else {
            System.err.println("JsonTranslater.java 16:Users < 1");
            return null;
        }
    }

    public static Test[] getTests(String json) {
        Gson gson = new Gson();
        Test[] tests = gson.fromJson(json, Test[].class);
        return tests;
    }

    public static Test getTest(String json) {
        Test[] tests = getTests(json);
        if(tests.length == 1) return tests[0];
        else if(tests.length > 1) {
            System.err.println("JsonTranslater.java 34:Test > 1");
            return tests[0];
        } else {
            System.err.println("JsonTranslater.java 34:Test < 1");
            return null;
        }
    }

    public static Result[] getResults(String json) {
        Gson gson = new Gson();
        Result[] results = gson.fromJson(json, Result[].class);
        return results;
    }

    public static Result getResult(String json) {
        Result[] results = getResults(json);
        if(results.length == 1) return results[0];
        else if(results.length > 1) {
            System.err.println("JsonTranslater.java 34:Result > 1");
            return results[0];
        } else {
            System.err.println("JsonTranslater.java 34:Result < 1");
            return null;
        }
    }

    public static Integer getTeacherId(String json) {
        Gson gson = new Gson();
        Teacher[] teacher = gson.fromJson(json, Teacher[].class);
        if(teacher.length == 1) return teacher[0].getId();
        else if(teacher.length > 1) {
            System.err.println("JsonTranslater.java 34:Teacher > 1");
            return teacher[0].getId();
        } else {
            System.err.println("JsonTranslater.java 34:Teacher < 1");
            return null;
        }
    }

    public static Integer getTestId(String json) {
        Gson gson = new Gson();
        Test test = gson.fromJson(json, Test.class);
        if(test == null) {
            System.err.println("JsonTranslater.java 34:Test < 1");
            return null;
        }
        return test.getId();
    }

    public static Integer getQuestionId(String json) {
        Gson gson = new Gson();
        Question test = gson.fromJson(json, Question.class);
        if(test == null) {
            System.err.println("JsonTranslater.java 34:Question < 1");
            return null;
        }
        return test.getId();
    }

    public static Question[] getQuestions(String json) {
        Gson gson = new Gson();
        Question[] questions = gson.fromJson(json, Question[].class);
        return questions;
    }

    public static Answer[] getAnswers(String json) {
        Gson gson = new Gson();
        Answer[] answers = gson.fromJson(json, Answer[].class);
        return answers;
    }
}
