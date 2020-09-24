import database_classes.Answer;
import database_classes.Person;
import database_classes.Test;
import forms.AuthorizationWindow;
import forms.MenuWindow;
import forms.TeacherResult;
import forms.create_test.CreateAnswers;
import forms.create_test.CreateTest;
import server_connector.ServerConnector;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        new AuthorizationWindow();

        /*try {
            System.out.println(ServerConnector.getTeacherId(36));
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //ServerConnector.addTest(new Test("test"));

//        new TeacherResult();
//        System.out.println(ServerConnector.replaceSpace("a10"));

        //new CreateTest();

//        CreateAnswers ans = new CreateAnswers();
//        Answer[] a = ans.getAnswers();
//        for (int i = 0; i < a.length; i++) {
//            System.out.println(a[i].getAnswerText());
//        }

        //System.out.println(ans.getAnswers());
        //new CreateTest();
        //new MenuWindow(null);

        /*try {
            Person user = ServerConnector.getPersonById(1);
            System.out.println(user);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }
}
