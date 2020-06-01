import javax.persistence.*;

@Entity
@Table(name = "p_connections")
public class Connection
{
    @EmbeddedId
    private PurchasePK id;

    public PurchasePK getId() {
        return id;
    }

    public void setId(PurchasePK id) {
        this.id = id;
    }
}
