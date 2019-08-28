
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "subscriptions")
public class Subscription
{
    @EmbeddedId
    private SubsPK id;
    @Column(name = "subscription_date")
    private LocalDateTime subscriptionDate;

    SubsPK getId() {
        return id;
    }

    public void setId(SubsPK id) {
        this.id = id;
    }

    LocalDateTime getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(LocalDateTime subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

}
