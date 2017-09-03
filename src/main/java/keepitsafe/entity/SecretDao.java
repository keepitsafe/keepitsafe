package keepitsafe.entity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecretDao extends CrudRepository<Secret, Long> {

}
