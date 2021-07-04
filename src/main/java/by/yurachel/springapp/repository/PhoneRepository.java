package by.yurachel.springapp.repository;

import by.yurachel.springapp.model.phone.impl.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone,Long> {

}
