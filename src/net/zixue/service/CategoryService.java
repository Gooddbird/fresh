package net.zixue.service;

import net.zixue.bean.Category;

import net.zixue.bean.Page;
import net.zixue.dao.CategoryDao;

import java.sql.SQLException;
import java.util.List;


public class CategoryService {

    public boolean addCategory(Category category) throws SQLException {

        CategoryDao dao = new CategoryDao();
        boolean addCategory = dao.addCategory(category);
        return addCategory;
    }


    public List<Category> findCategory() throws SQLException {
        CategoryDao dao = new CategoryDao();
        List<Category> categories = dao.queryCategoryList();
        return categories;
    }

    public Page findPageCategory(int currentPage,int currentCount) throws SQLException {

        Page page=new Page();
        // 1 查询出生鲜数据的总数
        CategoryDao dao = new CategoryDao();
        int totalCount = dao.queryCount();

        // 2 根据总数和当前页显示数 计算出总页数
        int totalPage= (int) Math.ceil(1.0*totalCount/currentCount);
        //3 将分页相关信息封装到page类中
        page.setCurrentCount(currentCount);
        page.setCurrentPage(currentPage);
        page.setTotalCount(totalCount);
        page.setTotalPage(totalPage);

        // 计算查询的起始位置

        //计算出起始位置
         int startPosition=(currentPage-1)*currentCount;
         // 分页查询数据
         List<Category> categories = dao.queryPageCategoryList(startPosition,currentCount);
        // 将集合数据封装到page类中
        page.setList(categories);

         return page;
    }


    public boolean updateCategory(Category category) throws SQLException {
        CategoryDao dao=new CategoryDao();
        boolean updateCategory = dao.updateCategory(category);
        return updateCategory;
    }
    public boolean deleteCategory(Category category) throws SQLException {
        CategoryDao dao=new CategoryDao();
        boolean updateCategory = dao.deleteCategory(category);
        return updateCategory;
    }
}
