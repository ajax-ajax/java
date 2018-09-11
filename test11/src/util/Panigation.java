package util;

public class Panigation {
private int curPage;
private int maxPage;
private int start;
private int end;
private int m;
public Panigation(int curPage,int count,int numInPage,int numOfPage) {
	this.curPage=curPage;
	if(this.curPage<=1) {
		this.curPage=1;
	}
	
	if(count%numInPage==0){
		   maxPage=count/numInPage;
	    }else{
	    	maxPage=count/numInPage+1;
	    }

	   if(this.curPage>=maxPage) {
		   this.curPage=maxPage;
	   }
	   
	    start=this.curPage-numOfPage/2;
	   if(start<=1) {
		   start=1;
	   }
	    end=start+numOfPage-1;
	   if(end>=maxPage) {
		   end=maxPage;
		   start=end-numOfPage+1;
	   }
	   if(start<=1) {
		   start=1;
	   }

   m = numInPage * (this.curPage - 1);
}
public int getM() {
	return m;
}
public int getCurPage() {
	return curPage;
}

public int getMaxPage() {
	return maxPage;
}
public int getStart() {
	return start;
}
public int getEnd() {
	return end;
}

}
