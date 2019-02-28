package Controller;

import Model.CartServices;
import Model.ConnectionDB;
import Model.CurrentUser;
import Model.PaymentServices;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONException;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CardController {
    @FXML
    private JFXTextField cardNumber;
    @FXML
    private JFXTextField expDate;
    @FXML
    private JFXTextField cardHolder;
    @FXML
    private JFXTextField securityCode;
    private Connection cnx;
    @FXML
    private Label verification;
    @FXML
    private JFXButton payButton;

    public CardController() {
        cnx = ConnectionDB.getInstance().getConnection();
    }

    @FXML
    private void CheckOut(ActionEvent actionEvent) throws StripeException, SQLException, JSONException {
        PaymentServices pay = new PaymentServices();
        String ex = expDate.getText(); //"02/20"
        if(cardHolder.getText().isEmpty()) {
            verification.setText("Put Card Details please!");
            verification.setStyle("-fx-text-fill: #FF3D00");
        }else if(cardNumber.getText().isEmpty()) {
            verification.setText("Put Card Details please!");
            verification.setStyle("-fx-text-fill: #FF3D00");
        }else if (expDate.getText().isEmpty()) {
            verification.setText("Put Card Details please!");
            verification.setStyle("-fx-text-fill: #FF3D00");
        }else if(securityCode.getText().isEmpty()) {
            verification.setText("Put Card Details please!");
            verification.setStyle("-fx-text-fill: #FF3D00");
        }else {

            String m = ex.substring(0, 2);
            String yy = ex.substring(3, 5);
            //System.out.println(m);
            //System.out.println(yy);
            String x = pay.createToken(cardHolder.getText(),cardNumber.getText(), m, "20" + yy, securityCode.getText());
            CartServices c = new CartServices();
            Double tot = c.showCart(CurrentUser.id).stream().mapToDouble(e -> e.getTotal()).sum();
            if (!tot.equals(0.0)) {
                Charge y = pay.chargeCreditCard(tot, x);
                //result.setText("Payment Accepted! =D, Total of = "+tot +" USD");
                addToHistroy(y);

            c.showCart(CurrentUser.id).stream().forEach(e ->
            {
                int id = e.getId();
                String req1 = "delete from cart where id=" + id;
                try {
                    Statement st = cnx.createStatement();
                    st.executeUpdate(req1);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            });
            Image live = new Image("file:C:\\Users\\PlusUltra\\Documents\\GitHub\\Desktop\\src\\View\\img\\easy.png");
            payButton.setGraphic(new ImageView(live));
            payButton.setText("Enjoy!");
            }else{
                verification.setText("Nothing to pay!");
                payButton.setText("Payer");
                payButton.setGraphic(null);
            }

        }
    }
    private void addToHistroy(Charge X) throws SQLException {
        //System.out.println(X);
        String id = X.getBalanceTransaction();
        Double price =X.getAmount().doubleValue()/100;
        Long x = X.getCreated();
        Date timeStampDate = new Date((long) (x * 1000));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String formattedDate = dateFormat.format(timeStampDate);

        String req = "insert into historystripe (transaction,amount,date,id_u) values (?,?,?,?)";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1,id);
        ps.setDouble(2,price);
        ps.setString(3,formattedDate);
        ps.setInt(4,CurrentUser.id);
        ps.executeUpdate();

    }
}
