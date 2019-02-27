package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HistoryService {
    private Connection cnx;

    public HistoryService(){cnx= ConnectionDB.getInstance().getConnection();}

    public List<History> showHistory(int id_u) throws SQLException {
        List<History> products= new ArrayList<>();
        String req="SELECT * FROM historystripe where id_u="+id_u;
        Statement stm=cnx.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while (rst.next()){
            History h=new History(rst.getInt("id"),rst.getString("transaction"),rst.getDouble("amount"),rst.getString("date"));
            products.add(h);
        }
        return products;
        }

    }


