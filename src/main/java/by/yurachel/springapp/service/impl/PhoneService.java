package by.yurachel.springapp.service.impl;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.repository.phoneRepository.PhoneRepository;
import by.yurachel.springapp.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Cacheable(cacheNames = "phoneId", key = "#id")
    public Phone findById(long id) {
        Phone phone = phoneRepository.findById(id).orElse(null);
        logger.info("Phone {} was successfully found", phone.getId());
        return phone;
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "phonesP")
    public Page<Phone> findAllWithPagination(Pageable pageable) {
        Page<Phone> phones = phoneRepository.findAll(pageable);
        logger.info("All phones in db was successfully found");
        return phones;
    }


    @Override
    @CachePut(cacheNames = "phoneId", key = "#phone.id")
    public Phone save(Phone phone) {
        Phone phoneToDb = phoneRepository.save(phone);
        logger.info("Phone {} was successfully added to db", phoneToDb.getId());
        return phoneToDb;
    }

    @Override
    @CacheEvict(cacheNames = "phoneId", key = "#id")
    public void deleteById(long id) {
        phoneRepository.deleteById(id);
        logger.info("Phone with id {} was successfully deleted form db", id);
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(cacheNames = "phoneF")
    public List<Phone> findAll() {
        List<Phone>phones = phoneRepository.findAll();
        logger.info("All phones was successfully found in db");
        return phones;
    }
}
