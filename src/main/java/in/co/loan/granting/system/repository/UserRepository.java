package in.co.loan.granting.system.repository;

import in.co.loan.granting.system.dto.UserDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserDTO,Long> {
    Optional<UserDTO> findByUserId(String userId);
    Optional<UserDTO> findByEmail(String email);
    List<UserDTO> findAll();
    Page<UserDTO> findAll(Pageable pageable);
    List<UserDTO> findByIdAndRoleIdAndFirstNameAndEmail(Long id, Long roleId, String firstName, String email);
    Page<UserDTO> findByIdAndRoleIdAndFirstNameAndEmail(Long id, Long roleId, String firstName, String email,Pageable pageable);
    Optional<UserDTO> findByUserIdAndPassword(String userId,String password);

}
