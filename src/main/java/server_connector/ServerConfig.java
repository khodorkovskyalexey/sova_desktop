package server_connector;

public class ServerConfig {

    public static String host = "http://localhost";

    public static String port = ":8080";

    public static String getReq = "/get";

    public static String postReq = "/post";

    public static String getPersonByIdUrl(Integer id) {
        return host + port + getReq + "/person_by_id?id=" + id;
    }

    public static String getPersonByLoginAndPasswordUrl(String log, String pass) {
        return host + port + getReq + "/auth_person?login=" + log + "&password=" + pass;
    }

    public static String addNewPersonUrl(String name, String login, String password) {
        return host + port + postReq + "/add_new_person?login=" + login + "&password=" + password
                + "&name=" + name;
    }

    public static String addNewStudentUrl(String name, String login, String password, String group) {
        return host + port + postReq + "/add_new_student?login=" + login + "&password=" + password
            + "&name=" + name + "&group=" + group;
    }

    public static String addNewTeacherUrl(String name, String login, String password){
        return host + port + postReq + "/add_new_teacher?login=" + login + "&password=" + password
                + "&name=" + name;
    }

    public static String getAllTestsUrl() {
        return host + port + getReq + "/all_from_tests";
    }

    public static String addNewTestUrl(String name, Integer teacher_id) {
        return host + port + postReq + "/add_new_test?name_test=" + name + "&teacher_id=" + teacher_id;
    }

    public static String addNewQuestionUrl(String text, Integer test_id) {
        return host + port + postReq + "/add_new_question?question_text=" + text + "&test_id=" + test_id;
    }

    public static String addNewAnswerUrl(String answercol, Integer question_id, boolean isItCorrect) {
        return host + port + postReq + "/add_new_answer?answerscol=" + answercol
                + "&question_id=" + question_id + "&isItCorrect=" + isItCorrect;
    }

    public static String checkStatusUrl(Integer id) {
        return host + port + getReq + "/person_status?person_id=" + id;
    }

    public static String getTestResultUrl(Integer test_id) {
        return host + port + getReq + "/test_result?test_id=" + test_id;
    }

    public static String getTeacherIdUrl(Integer person_id) {
        return host + port + getReq + "/teacher_id?id=" + person_id;
    }

    public static String getTestByTeacherIdUrl(Integer teacher_id) {
        return host + port + getReq + "/test_by_teacher_id?teacher_id=" + teacher_id;
    }

    public static String getTestById(Integer test_id) {
        return host + port + getReq + "/test_by_id?test_id=" + test_id;
    }

    public static String getQuestionsForTest(Integer test_id) {
        return host + port + getReq + "/questions_for_test_id?test_id=" + test_id;
    }

    public static String getAnswersForQuestion(Integer question_id) {
        return host + port + getReq + "/answers_for_questions_id?question_id=" + question_id;
    }
}
