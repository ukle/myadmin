package me.star.repository;

import me.star.domain.LocalStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author Zheng Jie
* @date 2019-09-05
*/
public interface LocalStorageRepository extends JpaRepository<LocalStorage, Long>, JpaSpecificationExecutor<LocalStorage> {
}
