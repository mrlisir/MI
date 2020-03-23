package model;

import java.util.List;

import dao.ProductDAO;

public class Page {
	
	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}



	int pageCount=5;//每页显示的记录数
	
	int pageNum;//当前的页数 1,2,3
	
	int pageSum;//总页数
	
	public int getPageSum() {
		return pageSum;
	}

	public void setPageSum(int pageSum) {
		this.pageSum = pageSum;
	}

	/**
	 * 得到某一页对应的集合
	 * @return
	 */
	public List<Product> getPageList()
	{
		int beginIndex;		
		ProductDAO dao=new ProductDAO();		
		pageSum=(dao.getSum()+pageCount-1)/pageCount;		
		if(pageNum>pageSum)
		{
			pageNum=pageSum;
		}
		if(pageNum<1)
			pageNum=1;		
		beginIndex=(pageNum-1)*pageCount;
		if(pageNum<1)
			beginIndex=0;		
		//13条记录  5条记录每页
		//(第三页)beginIndex=10；endIndex=13				
		return dao.getPageList(beginIndex, pageCount);
	}

}
