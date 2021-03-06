package Model;


import Model.ConnectionDB;
import com.stripe.Stripe;
import com.stripe.exception.*;
import com.stripe.model.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PaymentServices {
    private static final String TEST_STRIPE_SECRET_KEY = "sk_test_CoMKvJ1jnLcAVUgAHqbRKdci";
    private Connection cnx;

    public PaymentServices() {
        Stripe.apiKey = TEST_STRIPE_SECRET_KEY;
        cnx= ConnectionDB.getInstance().getConnection();
    }


    /*public String createCustomer(int id) throws SQLException {
        String req="select nom, prenom, email from user where id="+id;
        Statement st=cnx.createStatement();
        ResultSet rst=st.executeQuery(req);
        String ids = null;
        while(rst.next()){
            User user = new User(rst.getString("email"),rst.getString("nom")+" "+rst.getString("prenom"));
            Map<String, Object> customerParams = new HashMap<String, Object>();
            customerParams.put("description", user.getFullName());
            customerParams.put("email", user.getEmail());

        try {
            Customer stripeCustomer = Customer.create(customerParams);
            ids = stripeCustomer.getId();
            //System.out.println(stripeCustomer);
        } catch (CardException e) {
            // Transaction failure
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed (wrong API key?)
        }  catch (StripeException e) {
            // Generic error
        } catch (Exception e) {
            // Something else happened unrelated to Stripe
        }
     User user1 = new User(user.getEmail(), user.getFullName(),ids);
            PreparedStatement ps = cnx.prepareStatement("insert into userStripe (ids, fname, email) values (?, ?, ?)");
            ps.setString(1,user1.getIds());
            ps.setString(2,user1.getFullName());
            ps.setString(3,user1.getEmail());
            ps.executeUpdate();
    }
        return ids;
    }*/


    public Charge chargeCreditCard(Double OrderBill,String tok) {
// Stripe requires the charge amount to be in cents
        Charge x = null;
        int Bill = (int) (100 * OrderBill);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", Bill);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Monthly Charges");
        chargeParams.put("source",tok);

        try {
            // Submit charge to credit card
            /*Charge charge =*/
            x =Charge.create(chargeParams);
            //System.out.println(charge);
        } catch (CardException e) {
            // Transaction was declined
            System.out.println("Status is: " + e.getCode());
            System.out.println("Message is: " + e.getMessage());
        } catch (RateLimitException e) {
            // Too many requests made to the API too quickly
        } catch (InvalidRequestException e) {
            // Invalid parameters were supplied to Stripe's API
        } catch (AuthenticationException e) {
            // Authentication with Stripe's API failed (wrong API key?)
        }  catch (StripeException e) {
            // Generic error
        } catch (Exception e) {
            // Something else happened unrelated to Stripe
        }
        System.out.println("Yay! your payment accepted, enjoy the item!");
        return x;
    }
    public String createToken(String Number,String m,String y,String cvc) throws StripeException {
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", Number);
        cardParams.put("exp_month", m);
        cardParams.put("exp_year", y);
        cardParams.put("cvc", cvc);
        tokenParams.put("card", cardParams);
        Token x = Token.create(tokenParams);
        //System.out.println(x);
        return x.getId();
    }
}
