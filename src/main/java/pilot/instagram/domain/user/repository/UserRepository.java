package pilot.instagram.domain.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pilot.instagram.domain.user.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
