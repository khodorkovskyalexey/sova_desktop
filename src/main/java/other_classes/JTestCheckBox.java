package other_classes;

import javax.swing.*;

public class JTestCheckBox extends JCheckBox{
    private int answer_id;

    public JTestCheckBox() {
    }

    public JTestCheckBox(String text, int answer_id) {
        super(text);
        this.answer_id = answer_id;
    }

    public int getAnswer_id() {
        return answer_id;
    }

    public void setAnswer_id(int answer_id) {
        this.answer_id = answer_id;
    }
}
