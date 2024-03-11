package repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Model.Tache;
@Repository
public interface TacheRepository extends JpaRepository<Tache, Integer>{

}





