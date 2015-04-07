import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Doreen on 2015-04-03.
 */
public class MyGUIProgram extends Frame implements ActionListener {
    JPanel mypanel;
    JButton mybutton;
    JLabel mylabel;
    JFileChooser myfile;
    static TextArea mytext;

    public MyGUIProgram() {
    mypanel = new JPanel();
    mybutton = new JButton("submit");
    mybutton.addActionListener(this);
    mylabel = new JLabel();
    mytext= new TextArea(20,20);
    mytext.setEditable(true);

        mypanel.add(mybutton);
        mypanel.add(mylabel);
        mypanel.add(mytext);
        this.add(mypanel);







}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(mybutton == e.getSource()){
            String txt = mytext.getText();
            System.out.print(txt);

        }

    }

    }
