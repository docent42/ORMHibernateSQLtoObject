import javax.persistence.*;

@Entity
@Table(name = "p_connections")
public class Connection
{
    @EmbeddedId
    private SubsPK id;

    public SubsPK getId() {
        return id;
    }

    public void setId(SubsPK id) {
        this.id = id;
    }
}
