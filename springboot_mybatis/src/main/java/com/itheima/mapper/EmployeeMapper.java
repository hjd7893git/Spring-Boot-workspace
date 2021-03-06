package com.itheima.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.domain.Employee;
import org.springframework.stereotype.Repository;

/**
 * @program: spring-boot
 * @description:
 * @author: hjd
 * @create: 2020-07-30 13:41
 **/

@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {
}
