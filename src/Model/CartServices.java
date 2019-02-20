package Model;

import Model.ProduitStock;
import Model.ShoppingCart;
import Model.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartServices{

    private Connection cnx;

    public CartServices() {
        cnx= ConnectionDB.getInstance().getConnection();
    }

    //if item exists it will update qty else it will add the item
    public void fillCart(int id_u,int id_p, int quantity) throws SQLException {

        String req1="select * from Product where id="+id_p;
        Statement st1=cnx.createStatement();
        ResultSet rst=st1.executeQuery(req1);

        while (rst.next()) {
            ShoppingCart c = new ShoppingCart(id_u, id_p, rst.getString("name"), quantity, rst.getDouble("price"));
            String req3 = String.format("select count(*) as gg from cart where name in (select name from cart where id_u = any (select id_u from cart where id_u=%d) and id_p = any (select id_p from cart where id_p=%d))", id_u, id_p);
            Statement st2=cnx.createStatement();
            ResultSet rst1=st2.executeQuery(req3);
            while(rst1.next()) {
                //System.out.println(rst1.getInt("gg"));
                if (rst1.getInt("gg") == 0) {
                    String req2 = "INSERT INTO Cart (name, quantity, price, total, id_p, id_u) values( ?, ?, ?, ?, ?, ?)";
                    PreparedStatement ps = cnx.prepareStatement(req2);
                    ps.setString(1, c.getNom());
                    ps.setInt(2, c.getQuantity());
                    ps.setDouble(3, c.getPrice());
                    ps.setDouble(4, c.getTotal());
                    ps.setInt(5, c.getId_p());
                    ps.setInt(6, c.getId_u());
                    ps.executeUpdate();
                } else {
                    String req5 = "select * from cart where id_u = any (select id_u from cart where id_u=" + id_u + ") and id_p = any (select id_p from cart where id_p=" + id_p + ")";
                    Statement st3 = cnx.createStatement();
                    ResultSet rst2 = st3.executeQuery(req5);
                    while (rst2.next()){
                    String req4 = "UPDATE cart SET quantity=? WHERE id=" + rst2.getInt("id");
                    PreparedStatement ps1 = cnx.prepareStatement(req4);
                    ps1.setInt(1, c.getQuantity() + rst2.getInt("Quantity"));
                    ps1.executeUpdate();}
                }
            }
        }
    }

     //remove whole item
    public void removeCart(int id) throws SQLException {
        String req="Delete from cart where id="+id;
        PreparedStatement ps=cnx.prepareStatement(req);
        ps.executeUpdate();
        String req1="set @autoid :=0";
        String req2="update cart set id = @autoid :=(@autoid+1)";
        String req3= "alter table cart auto_increment = 1";
        Statement s=cnx.createStatement();
        s.executeUpdate(req1);
        s.executeUpdate(req2);
        s.executeUpdate(req3);
    }

     //update by removing by quantity
    public void removeQCart(int id, int quantity) throws SQLException {
        String req="select * from cart where id="+id;
        Statement st=cnx.createStatement();
        ResultSet rst=st.executeQuery(req);
        while(rst.next()){
            String req1 = "update cart set quantity=?, total=? where id="+id;
            PreparedStatement ps = cnx.prepareStatement(req1);
            ps.setInt(1, rst.getInt("quantity")-quantity);
            ps.setDouble(2,rst.getDouble("price")*(rst.getInt("quantity")-quantity));
            ps.executeUpdate();

            System.out.println("Votre Produit a ete modifie avec succees");}
    }

     // direct update
    public void update(int id, int quantity) throws SQLException {
        String req1="select * from cart where id="+id;
        Statement st1=cnx.createStatement();
        ResultSet rst=st1.executeQuery(req1);
        while(rst.next()){
        String req = "update cart set quantity=?, total=? where id="+id;
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, quantity);
        ps.setDouble(2,rst.getDouble("price")*quantity);
        ps.executeUpdate();

        System.out.println("Votre Produit a ete modifie avec succees");}
    }


    public void updateAdd(int id, int qty) throws SQLException {
        String req="select * from cart where id="+id;
        Statement stm=cnx.createStatement();
        ResultSet rst=stm.executeQuery(req);
        while(rst.next()) {
            String reqs = "update cart set quantity=?, total=? where id=" + id;
            PreparedStatement ps = cnx.prepareStatement(reqs);
            ps.setInt(1, rst.getInt("quantity")+qty);
            ps.setDouble(2, rst.getDouble("price") * (rst.getInt("quantity") + qty));
            ps.executeUpdate();

            System.out.println("Votre Produit a ete modifie avec succees");
        }
    }


    public List<ShoppingCart> showCart(int id_u) throws SQLException {
        List <ShoppingCart> products= new ArrayList<>();
        String req="SELECT * FROM Cart where id_u="+id_u;
        Statement stm=cnx.createStatement();
        ResultSet rst=stm.executeQuery(req);

        while (rst.next()){
            ShoppingCart p=new ShoppingCart(rst.getString("name"),rst.getInt("quantity"),rst.getDouble("price"),rst.getDouble("total"));
            products.add(p);
        }
        return products;

    }

}
