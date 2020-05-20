package spring.sts.townow;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.model.category.CategoryDAO;
import spring.model.category.CategoryDTO;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryDAO dao;
	
	@RequestMapping(value="/category/list")
	public String list(HttpServletRequest request) {
		List<String> ca1_nameList = dao.getCa1_names();
		List<List<CategoryDTO>> category = new ArrayList<List<CategoryDTO>>();
		for(String str : ca1_nameList) {
			category.add(dao.categoryList(str));
		}
		
		request.setAttribute("category", category);
		
		return "/category/list";		
	}
	
	@ResponseBody
	@GetMapping(value="/category/create")
	public String create(CategoryDTO dto) throws Exception {
		if(dto.getCa2_name() == null)
			dto.setCa2_name("");
		return dao.create(dto) ? "0" : "1";		
	}
	
	@ResponseBody
	@GetMapping(value="/category/deleteCa1")
	public String deleteCa1(int ca_id) throws Exception {
		CategoryDTO dto = (CategoryDTO) dao.read(ca_id);
		String ca1_name = dto.getCa1_name();
		if(dao.hasCategory2(ca1_name) > 0)
			return "2";
		else 
			return dao.delete(ca_id) ? "0" : "1";		
	}
	
	@ResponseBody
	@GetMapping(value="/category/deleteCa2")
	public String deleteCa2(int ca_id) throws Exception {
		if(dao.hasArticle(ca_id) > 0)
			return "2";
		else 
			return dao.delete(ca_id) ? "0" : "1";		
	}
}
