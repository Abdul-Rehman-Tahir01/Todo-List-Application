import java.awt.TextArea;
import java.awt.Color;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Text_Area extends TextArea{
    // Creating array list of text area to store each one's reference
    static ArrayList<Text_Area> textArea_list = new ArrayList<Text_Area>();

    // Creating the text area for add item frame (2)
    void create_addItem_textArea(){
        setText("Enter Task");
        setBounds(40, 85, 320, 160);
        setBackground(Color.WHITE);
        setForeground(Color.GRAY);
        setFocusable(false);
        
        // Setting functionality to sense the mouse click. When the click is sensed, focus is gained.
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setFocusable(true);
            }
        });

        // Setting functionality that if the focus is gained, set the "Enter text" to null and if the focus is lost again and the text area is empty, then set the text to "Enter text"
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e){
                if (getText().equals("Enter Task")) {
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

    // Creating view task text area for view task frame (3) to show the task
    void create_viewTask_textArea(Text_Area viewTask_textArea, boolean save_text){
        setBounds(40, 85, 320, 160);
        setBackground(Color.WHITE);
        setFocusable(false);
        if (save_text)
            setText(viewTask_textArea.getText());

        // Adding mouse listener to set focus to true when clicked inside
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setFocusable(true);
            }
        });
    }
}