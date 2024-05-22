import java.awt.Dialog;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Dialogs{
    // Creating error dialog box when maximum limit of 42 panels is reached 
    void create_error_box(Frames todoList_frame){
        Dialog error_dialog = new Dialog(todoList_frame, "Error", true);
        
        Labels error_label = new Labels();
        error_label.create_errorBox_label();
        error_dialog.add(error_label);

        Buttons ok_button = new Buttons();
        ok_button.create_ok_button(error_dialog);
        error_dialog.add(ok_button);
        
        error_dialog.setSize(500, 150);
        error_dialog.setLayout(null);
        error_dialog.setLocationRelativeTo(null);
        
        error_dialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e){
                error_dialog.dispose();
            }
        });
        
        error_dialog.setVisible(true);
    }

}
