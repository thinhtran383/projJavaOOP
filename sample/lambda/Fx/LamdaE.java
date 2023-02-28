package lambda.Fx;

import java.awt.Color;
import java.awt.Font;
import java.util.WeakHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LamdaE extends JFrame {
    private final int width;
    private final int height;

    public LamdaE(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.setTitle(title);
        this.setSize(this.width, this.height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
        addComponents();
        setVisible(true);
    }

    private void addComponents() {
        JLabel label = new JLabel("Input name: ", JLabel.CENTER);
        label.setBounds(this.width / 2 - 100, this.height / 5 - 20, 200, 40);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        this.add(label);
        //
        int width = 120;
        int height = 40;
        JButton btnClickMe = new JButton("Submit");
        btnClickMe.setBounds(this.width / 2 - width / 2, 3 * this.height - height / 2, width, height);
        btnClickMe.setBackground(Color.RED);
        btnClickMe.setForeground(Color.WHITE);
        this.add(btnClickMe);
        //
        JTextField txtMessage = new JTextField();
        txtMessage.setFont(new Font("Arial", Font.PLAIN, 14));
        txtMessage.setBounds(this.width / 2 - 90, 2 * this.height / 5 - 10, 100, 40);
        this.add(txtMessage);
    }

    public static void main(String[] args) {
        var title = "JFrame";
        var width = 400;
        var height = 250;
        new LamdaE(title, width, height);
    }
}
