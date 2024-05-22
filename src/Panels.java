import java.awt.Panel;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;

public class Panels extends Panel{
    Color todolist_panel_Color = new Color(105, 180, 255);
    static int i = -1;

    // Creating the todoList panel on todolist frame (1) on which title and button are added
    void create_todolist_panel(Frames todoList_frame){

        // Setting add item button on panel
        Buttons addItem_button = new Buttons();

        // Setting two labels on panel
        Labels todoListLabel1 = new Labels();
        Labels todoListLabel2 = new Labels();

        setLayout(null);
        setBackground(todolist_panel_Color);
        setBounds(0, 0, 575, 180);

        addItem_button.create_addItem_button(todoList_frame);
        add(addItem_button);
        
        todoListLabel1.create_todoList_label1();
        add(todoListLabel1);

        todoListLabel2.create_todoList_label2();
        add(todoListLabel2);
    }

    // Creating panel for the todoList frame (1) after button "Done" is pressed.
    void create_panel(ArrayList<Panels> panel_list, Text_Field addItem_textField, Text_Area addItem_textArea){
        // Setting two buttons on panel
        Buttons view_button = new Buttons();
        Buttons delete_button = new Buttons();

        // Setting a label on panel
        Labels task_label = new Labels();

        setLayout(null);
        setBackground(Color.LIGHT_GRAY);
        if(i==-1)
            setBounds(25, 200, 550, 60);
        else
            setBounds(25, panel_list.get(i).getY() + panel_list.get(i).getHeight() + 5, 550, 60);

        i++;
        
        view_button.create_view_button(this);
        add(view_button);
        
        delete_button.create_delete_button(this, addItem_textField, addItem_textArea);
        add(delete_button);

        task_label.create_task_label(addItem_textField);
        add(task_label);
    }

    // Deleting the task panels from todo list frame (1)
    void delete_panel(Panel panel){
        Container parent = panel.getParent();
        i--;
        
        if(parent!=null){
            parent.remove(panel);
            parent.revalidate();
            parent.repaint();
        }
    }

}
