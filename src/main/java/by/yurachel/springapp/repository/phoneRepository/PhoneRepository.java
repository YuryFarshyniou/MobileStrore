package by.yurachel.springapp.repository.phoneRepository;

import by.yurachel.springapp.model.phone.impl.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
    Page<Phone> findAll(Pageable pageable);
}
