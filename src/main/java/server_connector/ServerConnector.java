package server_connector;

import database_classes.*;
import other_classes.PersonStatus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class ServerConnector {

    private static String replaceSpecialSimvol(String string) {
        string = string.replace(" ", "--_--");
        string = string.replace("&", "--ampersentznak--");
        string = string.replace("?", "--questionznak--");
        return string;
    }

    public static Person getPersonByLoginAndPassword(String login, String pass) {
        String query = ServerConfig.getPersonByLoginAndPasswordUrl(login, pass);
        Person user = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                user = JsonTranslater.getUser(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return user;
    }

    public static Person getPersonById(Integer id) throws IOException {
        String query = ServerConfig.getPersonByIdUrl(id);
        Person user = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                user = JsonTranslater.getUser(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return user;
    }

    public static Integer getTeacherId(Integer id) throws IOException {
        String query = ServerConfig.getTeacherIdUrl(id);
        Integer teacher_id = null;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                teacher_id = JsonTranslater.getTeacherId(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return teacher_id;
    }

    public static Test[] getTestsForTeacher(Integer teacher_id) throws IOException {
        String query = ServerConfig.getTestByTeacherIdUrl(teacher_id);
        HttpURLConnection connection = null;
        Test[] tests = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                String inputLine;
                StringBuilder sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                tests = JsonTranslater.getTests(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return tests;
    }

    public static Test[] getAllTests() throws IOException {
        String query = ServerConfig.getAllTestsUrl();
        HttpURLConnection connection = null;
        Test[] tests = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                String inputLine;
                StringBuilder sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                tests = JsonTranslater.getTests(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return tests;
    }

    public static PersonStatus getPersonStatus(Integer id) throws IOException {
        String query = ServerConfig.checkStatusUrl(id);
        HttpURLConnection connection = null;
        StringBuilder sb = new StringBuilder();
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        switch (sb.toString()) {
            case "Student" : return PersonStatus.Student;
            case "Teacher" : return PersonStatus.Teacher;
            case "Admin" : return PersonStatus.Admin;
            default: return null;
        }
    }

    public static int addPerson(String name, String login, String pass) {
        int responceCode = HttpURLConnection.HTTP_BAD_REQUEST;
        HttpURLConnection connection = null;
        try {
            String url = ServerConfig.addNewPersonUrl(replaceSpecialSimvol(name), login, pass);
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "*/*");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                System.out.println(sb.toString());
                responceCode = connection.getResponseCode();
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
                responceCode = connection.getResponseCode();
            }
        } catch (Throwable cause) {
            System.err.println("couldn't make POST request: " + cause.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return responceCode;
    }

    public static int addStudent(String name, String login, String pass, String group) {
        name = replaceSpecialSimvol(name);
        int responceCode = HttpURLConnection.HTTP_BAD_REQUEST;
        HttpURLConnection connection = null;
        try {
            String url = ServerConfig.addNewStudentUrl(name, login, pass, group);
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "*/*");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                System.out.println(sb.toString());
                responceCode = connection.getResponseCode();
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
                responceCode = connection.getResponseCode();
            }
        } catch (Throwable cause) {
            System.err.println("couldn't make POST request: " + cause.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return responceCode;
    }

    public static int addTeacher(String name, String login, String pass) {
        name = replaceSpecialSimvol(name);
        int responceCode = HttpURLConnection.HTTP_BAD_REQUEST;
        HttpURLConnection connection = null;
        try {
            String url = ServerConfig.addNewTeacherUrl(name, login, pass);
            System.out.println(url);
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            //connection.setRequestProperty("Accept", "*/*");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                System.out.println(sb.toString());
                responceCode = connection.getResponseCode();
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
                responceCode = connection.getResponseCode();
            }
        } catch (Throwable cause) {
            System.err.println("couldn't make POST request: " + cause.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return responceCode;
    }

    public static void addTestAndQuestionsAndAnswers(Test test, ArrayList<Question> questions, ArrayList<Answer> answers) {
        Integer test_id = addTest(test);
        if(test_id >= 0) {
            ArrayList<Integer> questions_id = addQuestions(questions, test_id);
            for (int i = 0; i < questions_id.size(); i++) {
                if(questions_id.get(i) >= 0) {
                    int j = 0;
                    while (j < answers.size()) {
                        if(answers.get(j).getQuestionLocalId() == i) {
                            answers.get(j).setQuestionId(questions_id.get(i));
                            addAnswer(answers.get(j));
                            answers.remove(j);
                        } else {
                            j++;
                        }
                    }
                } else {
                    System.err.println("Ошибка при добавлении вопроса, questions_id[" + i + "] = " + questions_id.get(i));
                    int j = 0;
                    while (j < answers.size()) {
                        if(answers.get(j).getQuestionLocalId() == i) {
                            answers.remove(j);
                        } else {
                            j++;
                        }
                    }
                }
            }
        } else {
            System.err.println("Ошибка при добавлении теста, test_id = " + test_id);
        }
    }

    public static Integer addTest(Test test) {
        Integer id = -1;
        HttpURLConnection connection = null;
        try {
            /*
            String str = replaceSpecialSimvol(test.getName());
            System.out.println(str);
            String url = ServerConfig.addNewTestUrl(str);*/
            String url = ServerConfig.addNewTestUrl(replaceSpecialSimvol(test.getName()), test.getTeacherId());
            System.out.println(url);
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "*/*");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                System.out.println(sb.toString());
                id = JsonTranslater.getTestId(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            System.err.println("couldn't make POST request: " + cause.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return id;
    }

    public static ArrayList<Integer> addQuestions(ArrayList<Question> question, Integer test_id) {
        ArrayList<Integer> questionIdList = new ArrayList<>();
        for (int i = 0; i < question.size(); i++) {
            question.get(i).setTestId(test_id);
            questionIdList.add(addQuestion(question.get(i)));
        }
        return questionIdList;
    }

    public static Integer addQuestion(Question question) {
        Integer id = -1;
        HttpURLConnection connection = null;
        try {
            //String url = ServerConfig.addNewQuestionUrl(replaceSpecialSimvol(question.getText()), question.getTestId());
            String url = ServerConfig.addNewQuestionUrl(replaceSpecialSimvol(question.getText()), question.getTestId());
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "*/*");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                System.out.println(sb.toString());
                id = JsonTranslater.getQuestionId(sb.toString());
                //id = Integer.parseInt(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            System.err.println("couldn't make POST request: " + cause.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return id;
    }

    public static void addAnswer(Answer answer) {
        HttpURLConnection connection = null;
        try {
//            String url = ServerConfig.addNewAnswerUrl(replaceSpecialSimvol(answer.getAnswerText()),
//                    answer.getQuestionId(), answer.isItCorrect());
            String url = ServerConfig.addNewAnswerUrl(replaceSpecialSimvol(answer.getAnswerText()),
                    answer.getQuestionId(), answer.isItCorrect());
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Accept", "*/*");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                StringBuilder sb = new StringBuilder();
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                System.out.println(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            System.err.println("couldn't make POST request: " + cause.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
    }

    public static Result[] getResultsForTest(Integer test_id) throws IOException {
        String query = ServerConfig.getTestResultUrl(test_id);
        HttpURLConnection connection = null;
        Result[] results = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                String inputLine;
                StringBuilder sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                results = JsonTranslater.getResults(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return results;
    }

    public static Test getTest(Integer test_id) {
        String query = ServerConfig.getTestById(test_id);
        HttpURLConnection connection = null;
        Test test = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                String inputLine;
                StringBuilder sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                test = JsonTranslater.getTest(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return test;
    }

    public static Question[] getQuestions(Integer test_id) {
        String query = ServerConfig.getQuestionsForTest(test_id);
        HttpURLConnection connection = null;
        Question[] questions = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                String inputLine;
                StringBuilder sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                questions = JsonTranslater.getQuestions(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return questions;
    }

    public static Answer[] getAnswers(Integer question_id) {
        String query = ServerConfig.getAnswersForQuestion(question_id);
        HttpURLConnection connection = null;
        Answer[] answers = null;
        try {
            connection = (HttpURLConnection) new URL(query).openConnection();
            connection.setRequestMethod("GET");

            connection.connect();

            if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "cp1251"));

                String inputLine;
                StringBuilder sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    sb.append(inputLine);
                }
                in.close();
                answers = JsonTranslater.getAnswers(sb.toString());
            } else {
                System.out.println("fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage());
            }
        } catch (Throwable cause) {
            cause.printStackTrace();
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }
        return answers;
    }
}
