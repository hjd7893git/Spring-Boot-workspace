package spring.springauthority.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;
import spring.springauthority.entity.UmsAdminRoleRelation;
import spring.springauthority.entity.UmsResource;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jobob
 * @since 2020-08-28
 */

@Repository
public interface UmsAdminRoleRelationMapper extends BaseMapper<UmsAdminRoleRelation> {
    List<UmsResource> getResourceList(Long id);
}
