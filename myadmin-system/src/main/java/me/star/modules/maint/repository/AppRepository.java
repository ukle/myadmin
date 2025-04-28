package me.star.modules.maint.repository;

import me.star.modules.maint.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
* @author zhanghouying
* @date 2019-08-24
*/
public interface AppRepository extends JpaRepository<App, Long>, JpaSpecificationExecutor<App> {
}
