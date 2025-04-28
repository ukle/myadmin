package me.star.service.mapstruct;

import me.star.base.BaseMapper;
import me.star.domain.SysLog;
import me.star.service.dto.SysLogErrorDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * @author Zheng Jie
 * @date 2019-5-22
 */
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface LogErrorMapper extends BaseMapper<SysLogErrorDto, SysLog> {

}
