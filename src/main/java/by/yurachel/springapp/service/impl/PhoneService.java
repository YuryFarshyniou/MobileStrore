package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.phone.Phone;
import by.yurachel.springapp.repository.phoneRepository.PhoneRepository;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("phoneService")
@Transactional
public class PhoneService implements IService<Phone> {
    private final PhoneRepository phoneRepository;
    private final Logger logger = LoggerFactory.getLogger(PhoneService.class);

    public PhoneService(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Phone findById(long id) {
        Optional<Phone> byId = phoneRepository.findById(id);
        if (byId.isPresent()) {
            Phone phone = byId.get();
            logger.info("Phone {} was successfully found", phone.getId());
            return phone;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Phone> findAllWithPagination(Pageable pageable) {
        Page<Phone> phones = phoneRepository.findAll(pageable);
        logger.info("All phones in db was successfully found");
        return phones;
    }




    @Override
    public Phone save(Phone phone) {
        Phone phoneToDb = phoneRepository.save(phone);
        logger.info("Phone {} was successfully added to db", phoneToDb.getId());
        return phoneToDb;
    }

    @Override
    public void deleteById(long id) {
        phoneRepository.deleteById(id);
        logger.info("Phone with id {} was successfully deleted form db", id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Phone> findAll() {
        List<Phone> phones = phoneRepository.findAll();
        logger.info("All phones was successfully found in db");
        return phones;
    }
}
