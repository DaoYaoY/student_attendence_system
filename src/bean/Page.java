package bean;

import java.util.List;

public class Page {
    private String page_name;
    private int nowPage = 1;//��ǰҳ��
    private int totalPage;//��ҳ��
    private int pageNum = 10;//ÿҳ��ʾ��������
    private int allNum;//����������
    private List list;//��ǰҳ����ʾ����

    public Page(int totalPage, int allNum, List list) {
        this.totalPage = totalPage;
        this.allNum = allNum;
        this.list = list;
    }

    public Page() {

    }


    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getAllNum() {
        return allNum;
    }

    public void setAllNum(int allNum) {
        this.allNum = allNum;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }
}
