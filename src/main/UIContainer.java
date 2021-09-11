package main;

import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class UIContainer extends JFrame {
    private final String BUTTON_NAME = "Button";
    private JProgressBar progressBar;
    private JTextField textField;
    private static JButton button;

    public UIContainer(int width, int height){
        super("nHentai Parser");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(Color.BLACK);
        setLayout(null);

        // --- TEXT FIELD
        textField = new JTextField(1);
        textField.setBounds(0, 0, 0, 30);
        textField.setHorizontalAlignment(SwingConstants.CENTER);
        textField.setToolTipText("Enter manga number");

        textField.setBounds(0,402,100,30);
        add(textField);

        // --- DOWNLOAD BUTTON
        Action action = new SimpleAction();
        button = new JButton(action);
        button.setName(BUTTON_NAME);
        button.setText("Download");
        button.setMnemonic('D');
        button.setBorder(null);

        button.setBounds(0,432,100,30);
        add(button);

        // --- PROGRESS BAR
        progressBar = new JProgressBar(0,0,10);
        progressBar.setPreferredSize(new Dimension(500,25));
        progressBar.setBounds(100,432,540,30);
        add(progressBar);

        ImageIcon imageIcon = new ImageIcon("src/main/nLogo.png");
        JLabel background = new JLabel("",imageIcon,SwingConstants.CENTER);
        background.setBounds(-7,0,width,height);
        getContentPane().add(background);

        setSize(width, height);
        setVisible(true);
    }
    class SimpleAction extends AbstractAction{
        private static final long serialVersionUID = 1L;

        SimpleAction(){
            putValue(SHORT_DESCRIPTION, "HORNY BUTTON");
        }
        public void actionPerformed(ActionEvent event){
            JButton btn = (JButton) event.getSource();
            System.out.println("Нажатие на кнопку <" + btn.getName() + ">");

            try {
                Main.startDownload();
            } catch (InterruptedException e) {
                System.out.println("Interrupted");
            }

            if(btn.getName().equalsIgnoreCase(BUTTON_NAME)){
                btn.setEnabled(false);
                btn.setText("Disabled");
            }
        }
    }

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public JTextField getTextField() {
        return textField;
    }

    public static JButton getButton() {
        return button;
    }
}
