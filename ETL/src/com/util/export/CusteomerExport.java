package com.util.export;

import java.util.ArrayList;

import com.util.db.CustomerProfile;
import com.util.db.DbCustomerOperator;
import com.util.gui.FileBrower;
import com.util.tools.IdcardUtils;
import com.util.tools.Log;
import com.util.tools.StringUtil;

public class CusteomerExport {

	public static int count = 0;

	/*
	 * dirname: ������ filename���ļ� �� headList: ��ͷ infoList���м�¼ isFirstRow���Ƿ� �ǵ�һ��
	 * ��TRUE Ϊ�� ��ACCESS�������Ϊ�٣�ACCESS ��һ��Ϊ�棬����Ϊ��
	 */
	public static void anasys(String dirname, String filename,
			ArrayList<String> headList, ArrayList<String> infoList,
			boolean isFirstRow) {

		// ȥ���ո�
		StringUtil.deleteBlankList(infoList);
		StringUtil.deleteBlankList(headList);

		FileBrower.customerReport.setUsernum(FileBrower.customerReport
				.getUsernum() + 1);

		// ����
		String info = null;
		CustomerProfile data = new CustomerProfile();

		for (int loop = 0; loop < FileBrower.typeList.size(); loop++) {
			if (dirname.equals(FileBrower.typeList.get(loop).getDisc())) {
				data.usertype = FileBrower.typeList.get(loop).getTag();
				break;
			}
		}

		data.dataSource = FileBrower.id;

		for (int loop = 0; loop < infoList.size(); loop++) {
			
			info = infoList.get(loop);
			// System.out.println(info);
			if (info.length() < 1) {
				continue;
			}
			// ��ȷ��ѯ
			if (StringUtil.isNum(info.charAt(0))) {
				AccurateAnasys.accurate(data, info);
			}
			// ģ����ѯ
			else if (StringUtil.isChinese(info)) {
				FuzzyAnasys.fuzzy(data, info, headList.get(loop), isFirstRow);
			}
			// ֻ��Ҫ����������
			else {
				if (StringUtil.isMail(info)) {
					data.mail = info;
					continue;
				}
			}
		}

		// ɾ���ظ��ĵ绰����
		if (data.mobilePhonelist.size() > 1) {
			StringUtil.deleteRepeat(data.mobilePhonelist);
		}
		printlog(data);

		// ���ݽ���oracle
		if (checkImport(filename, data)) {
			DbCustomerOperator db = new DbCustomerOperator();
			db.importOracle(data);

		}
	}

	// check customer profile can import oracle
	static boolean checkImport(String filename, CustomerProfile data) {
		// mobilephone and mail all null ,then can not import

		if (data.mobilePhonelist.size() == 0 && data.getMail().length() == 0) {
			Log.error(filename + " " + FileBrower.customerReport.getUsernum()
					+ " : mobilephone && mail is null");
			return false;
		}
		return true;
	}

	static void printlog(CustomerProfile data) {
		String phone = "";
		for (int loop = 0; loop < data.mobilePhonelist.size(); loop++) {
			phone += data.mobilePhonelist.get(loop);
		}
		count++;
		String content = "--- num: " + count + " src: " + data.dataSource
				+ " name: " + data.name + "addr: " + data.address + " born: "
				+ data.born + " com: " + data.company + " fix: "
				+ data.getFixPhone1() + " id: " + data.idCard + " mail: "
				+ data.mail + " post: " + data.post + " phone: " + phone
				+ " sex: " + data.sex + "  -------";

		Log.error(content);
		// System.out.println(content);
		content = null;

	}

}
