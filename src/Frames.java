import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;


// Class for creating frames
public class Frames extends Frame{
    private BufferedImage background_image;
    private Frames todoList_frame_reference;
    boolean save_text = false;

    // To use for view task frame (3)
    Text_Field viewTask_textField = new Text_Field();
    Text_Area viewTask_textArea = new Text_Area();

    // Constructor 
    public Frames(int number, Frames todoList_frame_reference, int index_of_panel, ArrayList<Text_Field> textField_list, ArrayList<Text_Area> textArea_List){
        // Number 1 for todo list frame
        if(number==1){
            Panels todoList_panel1 = new Panels();
            todoList_panel1.create_todolist_panel(this);
            add(todoList_panel1);
        }

        // Number 2 for add item frame
        if(number==2){
            this.todoList_frame_reference = todoList_frame_reference;
            System.out.println("\nThe reference set is: " + this.todoList_frame_reference);

            Text_Field addItem_textField = new Text_Field();
            addItem_textField.create_setName_field();
            add(addItem_textField);
            
            Text_Area addItem_textArea = new Text_Area();
            addItem_textArea.create_addItem_textArea();
            add(addItem_textArea);

            Buttons cancel_button = new Buttons();
            System.out.println("The reference to be passed to cancel button: " + todoList_frame_reference);
            cancel_button.create_cancel_button(this, todoList_frame_reference);
            add(cancel_button);
            
            Buttons done_button = new Buttons();
            System.out.println("The reference to be passed to done button: " + todoList_frame_reference + "\n");
            done_button.create_done_button(this, todoList_frame_reference, addItem_textField, addItem_textArea);
            add(done_button);
            
            // Checking if the click occurred outside the TextArea or TextField in Add item frame (2)
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!addItem_textArea.getBounds().contains(e.getPoint())) {
                        addItem_textArea.setFocusable(false); // Disable focus for the TextArea
                        
                        if(addItem_textArea.getText().isEmpty()){
                            addItem_textArea.setText("Enter Task"); // Reset placeholder text
                            addItem_textArea.setForeground(Color.GRAY); // Reset text color
                        }
                    }
                    
                    if (!addItem_textField.getBounds().contains(e.getPoint())) {
                        addItem_textField.setFocusable(false); // Disable focus for the TextField
                        
                        if(addItem_textField.getText().isEmpty()){
                            addItem_textField.setText("Enter Name of Task"); // Reset placeholder text
                            addItem_textField.setForeground(Color.GRAY); // Reset text color
                        }
                    }
                }
            });

        }
        
        // Number 3 for view task frame
        if(number==3){
            // View task text field
            viewTask_textField = textField_list.get(index_of_panel);
            viewTask_textField.create_getName_field(viewTask_textField);
            add(viewTask_textField);
            
            // View task text area
            viewTask_textArea = textArea_List.get(index_of_panel);
            viewTask_textArea.create_viewTask_textArea(viewTask_textArea, save_text);
            add(viewTask_textArea);

            Buttons save_button = new Buttons();
            save_button.create_save_button(this);
            add(save_button);

            // Disabling the focus if clicked outside the text area in view task frame (3)
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (!viewTask_textArea.getBounds().contains(e.getPoint())) 
                        viewTask_textArea.setFocusable(false); // Disable focus for the TextArea
                }
            });

        }
    }
    
    // Loading background image for frame
    void load_backgroundImage(){
        // Load background image from file
        try {
            background_image = ImageIO.read(new File("C:\\Users\\Abdul Rehman Tahir\\OneDrive\\Desktop\\ToDo List Application\\lib\\Background.png")); 
            System.out.println("Background image loaded successfully");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load background image");
        }
    }

    // Draw background image on the frame
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (background_image != null){ 
            g.drawImage(background_image, 0, 0, getWidth(), getHeight(), this);
        }
    }

    // Creating the todo list frame (1)
    void create_todoList_frame(){
        setTitle("ToDo List");
        setSize(600, 450);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });

    }
    
    // Creating add item frame (2)
    void create_addItem_frame(){
        setTitle("ADD ITEM");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }

    // Create view task frame (3)
    void create_viewTask_frame(){
        setTitle("Viewing Task");
        setSize(400, 300);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                dispose();
            }
        });
    }
}

// Class to create menu frame and to draw separate image on it
class MenuFrame extends Frame{
    // To load image
    private BufferedImage menu_image;

    // Method to load menu image from file
    void load_menuImage(){
        // Load menu image from file
        try {
            menu_image = ImageIO.read(new File("C:\\Users\\Abdul Rehman Tahir\\OneDrive\\Desktop\\ToDo List Application\\lib\\Menu image.png")); 
            System.out.println("Menu image loaded successfully");
        } catch (IOException e) {
            System.out.println("Failed to load menu image");
            e.printStackTrace();
        }
    }
    
    // Draw menu image on the frame
    @Override
    public void paint(Graphics g){
        super.paint(g);
        if (menu_image != null){ 
            g.drawImage(menu_image, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    // Creating the menu frame
    void create_menu_frame(){
        setTitle("ToDo List");
        setSize(480, 350);
        setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                System.exit(0);
            }
        });
    }
}