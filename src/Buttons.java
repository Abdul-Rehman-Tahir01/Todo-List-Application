import java.awt.Button;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.util.ArrayList;
import java.awt.Scrollbar;

public class Buttons extends Button{
    // Creating color for buttons using RGB values
    Color color_addItem_button = new Color(137, 230, 220);
    static int i = 0;
    
    // Creating array list of panels to store each one of them when they are created
    static ArrayList<Panels> panel_list = new ArrayList<Panels>();
    
    // Creating array list of text fields and text areas to store the references of each when the respective panel is created
    static ArrayList<Text_Field> textField_list = new ArrayList<Text_Field>();
    static ArrayList<Text_Area> textArea_list = new ArrayList<Text_Area>();
    
    // Creating a scrollbar object for scrolling
    Scrollbar scrollbar = new Scrollbar(Scrollbar.VERTICAL);
    int previousScrollValue = 0;
    
    // Creating next button for menu frame 
    void create_next_button(MenuFrame menuFrame){
        setLabel("Next");
        setBounds(180, 300, 90, 30);
        
        // Adding functionality to dispose the current menu frame and create the todo list frame (1).
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuFrame.dispose();

                // First time todolist frame generation
                Frames todoList_frame = new Frames(1, null, 0, null, null);
                todoList_frame.load_backgroundImage();
                todoList_frame.create_todoList_frame();
            }
        });
    }
    
    // Creating add item button for the todoList frame (1)
    void create_addItem_button(Frames todoList_frame){
        setLabel("ADD ITEM");
        setBounds(50, 115, 80, 40);
        setBackground(color_addItem_button);
        
        // Disposing the current todoList frame (1) and creating the add item frame (2)
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(panel_list.size()<15){    // Maximum of 15 panels can be created
                    todoList_frame.setVisible(false);
                    
                    Frames addItem_frame = new Frames(2, todoList_frame, 0, null, null);
                    addItem_frame.load_backgroundImage();
                    addItem_frame.create_addItem_frame();
                }
                else{   // Else create a dialog box showing error message
                    System.out.println("Maximum of 15 panels are created\nNo more panels can be generated");
                    Dialogs dialog = new Dialogs();
                    dialog.create_error_box(todoList_frame);
                }
            }


        });
    }
    
    // Creating the cancel button for add item frame (2)
    void create_cancel_button(Frames addItem_frame, Frames todoList_frame){
        setLabel("Cancel");
        setBounds(250, 255, 55, 30);
        setBackground(Color.WHITE);
        
        // Disposing the current add item frame (2) and recreating the todoList frame (1)
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("The reference received cancel button is: " + todoList_frame);
                addItem_frame.dispose();
                todoList_frame.setVisible(true);
            }
        });
    }

    // Creating the done button for add item frame (2)
    void create_done_button(Frames addItem_frame, Frames todoList_frame, Text_Field addItem_textField, Text_Area addItem_textArea){
        setLabel("Done");
        setBounds(310, 255, 50, 30);
        setBackground(Color.WHITE);
        
        // Disposing the current add item frame (2) and creating the todolist frame (1) after button "Done" is pressed.
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("The reference received by done button is: " + todoList_frame);
                addItem_frame.dispose();
                todoList_frame.setVisible(true);
                
                // Adding panels to the todolist frame (1) and to the panel_list array list.
                Panels todoListPanel = new Panels();
                todoListPanel.create_panel(panel_list, addItem_textField, addItem_textArea);
                todoList_frame.add(todoListPanel);
                panel_list.add(todoListPanel);
                ++i;
                System.out.println("\nPanel" + i + " is generated");
                System.out.println("Size of panel list: " + panel_list.size());
                
                // Adding reference to the text field and text area array list according to the panels being generated
                textField_list.add(addItem_textField);
                System.out.println("Reference " + i + " of text field is saved");
                System.out.println("Size of panel list: " + textField_list.size());
                
                textArea_list.add(addItem_textArea);
                System.out.println("Reference " + i + " of text area is saved");
                System.out.println("Size of panel list: " + textArea_list.size());

                // Setting the scroll bar and its functionality
                scrollbar.setBounds(579, 30, 15, 415);
                scrollbar.setBackground(Color.BLACK);
                scrollbar.setValues(0, 3, 0, 150);
                
                if (panel_list.size() > 3) {
                    todoList_frame.add(scrollbar);
                    
                    scrollbar.addAdjustmentListener(new AdjustmentListener() {
                        @Override
                        public void adjustmentValueChanged(AdjustmentEvent e){
                            int currentScrollValue = e.getValue();
                            System.out.println(e.getValue());

                            if(currentScrollValue > previousScrollValue){
                                for(Panels panel : panel_list)
                                    panel.setLocation(25, panel.getY() - 5);
                            }
                            
                            else{
                                for(Panels panel : panel_list)
                                    panel.setLocation(25, panel.getY() + 5);
                            }

                                previousScrollValue = currentScrollValue;
                        }
                    });
                }
            }
        });
    }
    
    // Creating the view button for the panel on the todoList frame (1) after button "Done" is pressed
    void create_view_button(Panels panel){
        setLabel("View");
        setBounds(430,15, 50, 30);
        setBackground(Color.WHITE);
        
        // Creating the view task frame (3) without disposing the todoList frame (1)
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                int index_of_panel = panel_list.indexOf(panel);

                Frames viewTask_frame = new Frames(3, null, index_of_panel, textField_list, textArea_list);
                viewTask_frame.load_backgroundImage();
                viewTask_frame.create_viewTask_frame();
            }
        });
    }

    // Creating the delete button for the panel on the todoList frame (1) after button "Done" is pressed
    void create_delete_button(Panels panel, Text_Field addItem_textField, Text_Area addItem_textArea){
        setLabel("Delete");
        setBounds(490, 15, 50, 30);
        setBackground(Color.WHITE);
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("\nDeleting the panel");
                int indexOfDeletedPanel = panel_list.indexOf(panel);
                System.out.println("Index of panel being deleted: " + indexOfDeletedPanel);
                panel.delete_panel(panel);

                panel_list.remove(panel);
                System.out.println("Size of panel list: " + panel_list.size());
                
                textField_list.remove(addItem_textField);
                System.out.println("Size of text field list: " + textField_list.size());
                
                textArea_list.remove(addItem_textArea);
                System.out.println("Size of text area list: " + textArea_list.size());

                System.out.println("Rebounding other panels\n");
                
                for(int i=indexOfDeletedPanel; i<panel_list.size(); i++){
                    Panels resized_panel = panel_list.get(i);
                    
                    if(resized_panel.getY()-65>=200)
                    resized_panel.setBounds(25, resized_panel.getY()-65, 550, 60);
                }
                
            }
        });
    }

    // Creating the ok button for the error box
    void create_ok_button(Dialog dialog){
        setLabel("OK");
        setBounds(195, 100, 80, 30);
        setBackground(Color.WHITE);

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                dialog.dispose();
            }
        });
    }

    // Creating save button for view task frame (3)
    void create_save_button(Frames viewTask_frame){
        setLabel("Save");
        setBounds(310, 255, 50, 30);
        setBackground(Color.WHITE);
        
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                viewTask_frame.dispose();
            }
        });


            
        
    }

}


    

