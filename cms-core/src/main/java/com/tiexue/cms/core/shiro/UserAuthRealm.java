package com.tiexue.cms.core.shiro;


import java.util.HashSet;

import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Service;
import com.tiexue.cms.core.dto.CmsShiroSubject;
import com.tiexue.cms.core.entity.CmsAdmin;
import com.tiexue.cms.core.service.ICmsAdminService;

/**
 * Function:shiro权限控制. <br/>
 *
 * @author qiao
 * @date:nnn
 * @version:
 * @since:JDK 1.7
 */
@Service("userAuthRealm")
public class UserAuthRealm extends AuthorizingRealm {
	private static final Logger logger = Logger.getLogger(UserAuthRealm.class);

	// 管理员用户
	@Resource
	private ICmsAdminService CmsAdminSer;

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用. 角色查询设置
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

		CmsAdmin userAdmin = CmsAdminSer.getByName(username);
		if (userAdmin != null) {
			// 角色控制权限
			authorizationInfo.setRoles(CmsAdminSer.findRoles(username));
		} else {
			Set<String> roleCp = new HashSet<String>();
			roleCp.add("CP");
			authorizationInfo.setRoles(roleCp);
		}

		CmsShiroSubject subject = new CmsShiroSubject();
		subject.setCmsAdmin(userAdmin);

		return authorizationInfo;
	}

	/**
	 * 认证回调函数,登录时调用. 登录判断逻辑
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String) token.getPrincipal();
		CmsAdmin userAdmin = CmsAdminSer.getByName(username);
		String password = "";
		if (((UsernamePasswordToken) token).getPassword() != null) {
			password = new String(((UsernamePasswordToken) token).getPassword());
		}

		CmsShiroSubject subject = new CmsShiroSubject();
		subject.setCmsAdmin(userAdmin);
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute("user", subject);

		logger.debug("this is testing");

		// 用户密码验证,交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配，如果觉得人家的不好可以自定义实现
		SimpleAuthenticationInfo authInfo = new SimpleAuthenticationInfo(subject.getUserName(), subject.getPassword(),
				ByteSource.Util.bytes(""), subject.getUserName());
		return authInfo;
	}



	@Override
	public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
		super.clearCachedAuthorizationInfo(principals);
	}

	@Override
	public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
		super.clearCachedAuthenticationInfo(principals);
	}

	@Override
	public void clearCache(PrincipalCollection principals) {
		super.clearCache(principals);
	}

	public void clearAllCachedAuthorizationInfo() {
		getAuthorizationCache().clear();
	}

	public void clearAllCachedAuthenticationInfo() {
		getAuthenticationCache().clear();
	}

	public void clearAllCache() {
		this.clearAllCachedAuthenticationInfo();
		this.clearAllCachedAuthorizationInfo();
	}

}
