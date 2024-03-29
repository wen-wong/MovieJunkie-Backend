package ca.mcgill.ecse428.moviejunkie.repository;
import ca.mcgill.ecse428.moviejunkie.model.Account;
import org.springframework.data.repository.CrudRepository;
public interface AccountRepository extends CrudRepository<Account, Integer>{
    //Account findAccountByID(int id);
    Account findAccountByUsername(String username);
    Account findAccountByEmail(String email);
}
