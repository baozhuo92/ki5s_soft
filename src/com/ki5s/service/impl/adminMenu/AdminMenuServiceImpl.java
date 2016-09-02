package com.ki5s.service.impl.adminMenu;

import com.ki5s.dao.adminMenu.AdminMenuDao;
import com.ki5s.entity.adminMenu.AdminMenu;
import com.ki5s.service.adminMenu.AdminMenuService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Repository
@Transactional(value = "transactionManager")
public class AdminMenuServiceImpl implements AdminMenuService{
	private Log log = LogFactory.getLog(AdminMenuServiceImpl.class);
	
	@Resource
	AdminMenuDao adminMenuDao;
	
	/**
	 * 查询所有
	 * @return
	 */
	public List<AdminMenu> listAdminMenuAll(){
		log.info("查询所有后台菜单");
		return adminMenuDao.listAdminMenuAll();
	}
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	public List<AdminMenu> listAdminMenuById(String id){
		log.info("通过ID查询后台菜单");
		return adminMenuDao.listAdminMenuById(id);
	}
	/**
	 * 通过父级Id查询
	 * @param parentId
	 * @return
	 */
	public List<AdminMenu> listAdminMenuByParentId(String parentId){
		return adminMenuDao.listAdminMenuByParentId(parentId);
	}
	/**
	 * 新增
	 * @param item
	 */
	public void add(AdminMenu item){
		adminMenuDao.add(item);
	}
	/**
	 * 修改
	 * @param item
	 */
	public void update(AdminMenu item){
		adminMenuDao.update(item);
	}
	
	
}
