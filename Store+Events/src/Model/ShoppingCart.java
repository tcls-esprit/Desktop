package Model;

public class ShoppingCart {
    private int id;
    private int id_p;
    private int id_u;
    private String nom;
    private int quantity;
    private Double price;
    private Double total;

    public ShoppingCart(String nom, int quantity, Double price, Double total) {
        this.nom = nom;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public ShoppingCart(int id, String nom, int quantity, Double price, Double total) {
        this.id = id;
        this.nom = nom;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    public ShoppingCart(int id_u, int id_p, String nom, int quantity, Double price) {
        this.id_u = id_u;
        this.id_p = id_p;
        this.nom = nom;
        this.quantity = quantity;
        this.price = price;
        this.total = price * quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getId_u() {
        return id_u;
    }

    public void setId_u(int id_u) {
        this.id_u = id_u;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "nom='" + nom + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                " TD, total=" + total +
                " TD}";
    }

}
