package spring.springauthority.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import spring.springauthority.entity.UmsAdmin;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jobob
 * @since 2020-08-28
 */
public interface IUmsAdminService extends IService<UmsAdmin> {

    String login(String username, String password);

    public UserDetails loadUserByUsername(String username);
}
