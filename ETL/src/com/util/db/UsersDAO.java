package com.util.db;

import java.util.List;

import com.util.export.Users;

public interface UsersDAO {
	public boolean insert(Users user);	//��
	public boolean delete(String id);   //����ɾ��
	public boolean delete(String[] userIds);  //����ɾ��
	public boolean update(Users user);  //�޸�
	public List<Users> query();     //ȫ����ѯ
	public Users query(String id);  //����¼��ѯ

}