package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.repository.PhoneRepository;
import by.yurachel.springapp.service.IService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("phoneService")
public class PhoneService implements IService<Phone> {
    private final PhoneRepository phoneRepository;

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    public Phone findById(long id) {
        return phoneRepository.findById(id).orElse(null);
    }

    public List<Phone> findAll() {
        return phoneRepository.findAll();
    }

    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    public void deleteById(long id) {
        phoneRepository.deleteById(id);
    }
}
