package com.ki5s.service.adminMenu;

import com.ki5s.entity.adminMenu.AdminMenu;

import java.util.List;

public interface AdminMenuService {
	/**
	 * 查询所有
	 * @return
	 */
	List<AdminMenu> listAdminMenuAll();
	/**
	 * 通过ID查询
	 * @param id
	 * @return
	 */
	List<AdminMenu> listAdminMenuById(String id);
	/**
	 * 通过父级Id查询
	 * @param parentId
	 * @return
	 */
	List<AdminMenu> listAdminMenuByParentId(String parentId);
	/**
	 * 新增
	 * @param item
	 */
	void add(AdminMenu item);
	/**
	 * 修改
	 * @param item
	 */
	void update(AdminMenu item);
}
