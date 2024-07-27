import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HotelReservationSystemController implements ActionListener {

    private HotelReservationSystemView view;
    private HotelReservationSystemModel model;

    public HotelReservationSystemController(HotelReservationSystemView view, HotelReservationSystemModel model) {
        this.view = view;
        this.model = model;

        view.setMainMenuActionLister(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Create Hotel":
                System.out.println("Create Hotel");
                break;
            case "View Hotel":
                System.out.println("View Hotel");
                break;
            case "Manage Hotel":
                System.out.println("Manage Hotel");
                break;
            case "Book Hotel":
                System.out.println("Book Hotel");
                break;
            case "Back to Main":
                System.out.println("Back to Main");
                break;
        }
    }


}
