/**
 *  Title  fhcp customer dataplat
 *  @author: tobilon
 *  Company: marvel2
 *  Copyright: Copyright (c) 2012
 *  @version 1.0
 *  分页显示计算
 */

package fhcp;

public class DisPage {

	private int l_start; // 开始纪录
	private int l_end; // 结束纪录
	private int l_curpage; // 当前页数
	private int l_totalnum;// 总记录数
	private int int_num = 20; // 每页20条
	private int l_totalpage; // 总的页数

	public void Init(int currentpage, int totalnum) {
		l_curpage = currentpage;
		l_totalnum = totalnum;

		if (currentpage >= 0) {
			if (currentpage >= (int) Math.ceil(l_totalnum / int_num))
				l_curpage = (int) Math.floor(l_totalnum / int_num);
			else
				l_curpage = currentpage;
		} else {
			l_curpage = 0;
		}

		l_start = l_curpage * int_num;
		l_end = l_start + int_num;

		if (l_end > l_totalnum)
			l_end = l_totalnum;

		l_totalpage = (int) Math.ceil(l_totalnum / int_num);

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
		if (l_curpage + 1 <= l_totalpage) {
			return l_curpage + 1;
		} else {
			return l_totalpage;
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

	// 结束
	public DisPage() {
	}
}
