package Model;

public class History {
    private int id;
    private String idt,dateh;
    private Double amounth;

    public History(){}

    public History(String trxh, Double amounth, String dateh) {
        this.idt = trxh;
        this.amounth = amounth;
        this.dateh = dateh;
    }

    public History(int id, String trxh, Double amounth, String dateh) {
        this.id = id;
        this.idt = trxh;
        this.amounth = amounth;
        this.dateh = dateh;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdt() {
        return idt;
    }

    public void setIdt(String idt) {
        this.idt = idt;
    }

    public String getDateh() {
        return dateh;
    }

    public void setDateh(String dateh) {
        this.dateh = dateh;
    }

    public Double getAmounth() {
        return amounth;
    }

    public void setAmounth(Double amounth) {
        this.amounth = amounth;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", trx='" + idt + '\'' +
                ", date='" + dateh + '\'' +
                ", amount=" + amounth +
                '}';
    }
}
