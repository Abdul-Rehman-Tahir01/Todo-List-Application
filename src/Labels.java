import java.awt.Color;
import java.awt.Font;
import java.awt.Label;

public class Labels extends Label{
    // Describing the font of labels
    Font font = new Font(null, 1, 40);

    // Creating custom colors by RGB values
    Color color_menu_labels = new Color(163, 226, 230); 
    Color color_todoList_labels = new Color(105, 180, 255);

    static int i = 1;

    // Creating label1 for menu frame
    void create_menu_label1(){
        setText("ToDo List");
        setBounds(20, 90, 230, 70);
        setFont(font);
        setBackground(color_menu_labels);
    }
    
    // Creating label2 for menu frame
    void create_menu_label2(){
        setText("Application");
        setBounds(40, 170, 230, 70);
        setFont(font);
        setBackground(color_menu_labels);
    }

    // Creating label1 for todoList frame (1)
    void create_todoList_label1(){
        setText("TODO LIST");
        setBounds(170, 30, 230, 70);
        setFont(font);
        setBackground(color_todoList_labels);
    }

    // Creating label2 for todolist frame (1)
    void create_todoList_label2(){
        setBounds(40, 175, 510, 3);
        setBackground(Color.DARK_GRAY);
    }

    // Creating task label on panel for todoList frame (1) after button "Done" is pressed.
    void create_task_label(Text_Field textField){
        // Giving it a separate font
        Font font_task = new Font("Arial", Font.PLAIN, 17);

        setFont(font_task);
        setBounds(20, 15, 350, 30);

        if(textField.getText().equals("Enter Name of Task"))
            setText("Task");
        else
            setText(textField.getText());

        setBackground(Color.LIGHT_GRAY);
        ++i;
    }
 
    // Creating label for label box
    void create_errorBox_label(){
        setText("       Maximum Limit of creating tasks is achieved. Delete previous tasks to create more.");
        setBounds(15, 20, 460, 70);
    }
}
