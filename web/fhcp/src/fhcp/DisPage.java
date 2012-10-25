/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  ��ҳ��ʾ����
 */

package fhcp;

public class DisPage {

	private int l_start; // ��ʼ��¼
	private int l_end; // �����¼
	private int l_curpage; // ��ǰҳ��
	private int l_totalnum;// �ܼ�¼��
	private int int_num = 20; // ÿҳ20��
	private int l_totalpage; // �ܵ�ҳ��
	
	public void setIntNum(int num)
	{
		int_num=num;
	}

	public void Init(int currentpage, int totalnum) {
		l_curpage = currentpage;
		l_totalnum = totalnum;

		if (currentpage >= 0) {
			if (currentpage >= (int) Math.ceil(l_totalnum / (float)int_num))
				l_curpage = (int) Math.floor(l_totalnum / (float)int_num);
			else
				l_curpage = currentpage;
		} else {
			l_curpage = 0;
		}

		l_start = l_curpage * int_num;
		l_end = l_start + int_num;

		if (l_end > l_totalnum)
			l_end = l_totalnum;

		l_totalpage = (int) Math.ceil(l_totalnum / (float)int_num);
		System.out.println(l_totalnum+" "+int_num+" "+l_totalpage);
	}

	public int getCurpage() {
		return l_curpage;
	}

	public int getPrepage() {
		if (l_curpage - 1 >= 0) {
			return l_curpage - 1;
		} else {
			return 0;
		}
	}

	public int getNextpage() {
		if (l_curpage + 1 < l_totalpage) {
			return l_curpage + 1;
		} else {
			return l_totalpage-1;
		}
	}

	public int getTotalnum() {
		return l_totalnum;
	}

	public int getTotalpage() {
		return l_totalpage;
	}

	public int getStart() {
		return l_start;
	}

	public int getEnd() {
		return l_end;
	}

	// ����
	public DisPage() {
	}
}
