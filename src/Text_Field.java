import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

public class Text_Field extends TextField{

    // Creating the text field for add item frame (2) to set name of task
    void create_setName_field(){
        setText("Enter Name of Task");
        setForeground(Color.GRAY);
        setBounds(40, 40, 320, 30);
        setFocusable(false);
        
        // Setting functionality to sense mouse click. When the click is sensed, the focus is gained.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                setFocusable(true);
            }
        });

         // Setting functionality that if the focus is gained, set the "Enter Name of Task" to null and if the focus is lost again and the text field is empty, then set the text to "Enter Name of Task"
         addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if (getText().equals("Enter Name of Task")) {
                    setText(null);
                    setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e){
                if(getText().isEmpty()){
                    setText("Enter Task");
                    setForeground(Color.GRAY);
                }
            }
        });

    }

    // Creating the text field for view task frame (3) to get name of task
    void create_getName_field(Text_Field viewTask_textField){

        if(viewTask_textField.getText().equals("Enter Name of Task"))
            setText("Task");
        else
            setText(viewTask_textField.getText());

        setForeground(Color.BLACK);
        setBounds(40, 40, 320, 30);
        setFocusable(false);
        setEnabled(false);
    }
}
