package in.co.loan.granting.system.repository;

import in.co.loan.granting.system.dto.UserDTO;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserDTO,Long> {
}
