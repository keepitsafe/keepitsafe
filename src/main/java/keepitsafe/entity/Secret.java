package keepitsafe.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Secret {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String content;
        
    Secret(String content) {
        this.content = content;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((content == null) ? 0 : content.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Secret other = (Secret) obj;
        if (content == null) {
            if (other.content != null)
                return false;
        } else if (!content.equals(other.content))
            return false;
        return true;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }    
}
