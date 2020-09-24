package other_classes;

import javax.swing.JButton;

public class JTestButton extends JButton{
    private Integer test_id;

    public JTestButton(Integer test_id) {
        super();
        this.test_id = test_id;
    }

    public JTestButton(String text, Integer test_id) {
        super(text);
        this.test_id = test_id;
    }

    public Integer getTest_id() {
        return test_id;
    }

    public void setTest_id(Integer test_id) {
        this.test_id = test_id;
    }
}
