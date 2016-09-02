package com.ki5s.dao.adminMenu;

import com.ki5s.entity.adminMenu.AdminMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMenuDao {
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
	List<AdminMenu> listAdminMenuById(@Param("id") String id);
	/**
	 * 通过父级Id查询
	 * @param parentId
	 * @return
	 */
	List<AdminMenu> listAdminMenuByParentId(@Param("parentId") String parentId);
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
