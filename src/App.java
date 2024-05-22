// App class from where the application initiates
public class App {

    public static void main(String[] args) {
        // Creating object of menuframe and calling respective functions of it to create it.
        MenuFrame menu_frame = new MenuFrame();
        menu_frame.load_menuImage();
        menu_frame.create_menu_frame();

        // Creating object to add labels to menu frame
        Labels menuLabel1 = new Labels();
        Labels menuLabel2 = new Labels();

        // Creating object to add buttons to menu frame
        Buttons nextButton = new Buttons();
        
        // Adding labels to menu frame
        menuLabel1.create_menu_label1();
        menu_frame.add(menuLabel1);

        menuLabel2.create_menu_label2();
        menu_frame.add(menuLabel2);

        // Adding next button to menu frame
        nextButton.create_next_button(menu_frame);
        menu_frame.add(nextButton);
        
    }
}
