package com.zent.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.zent.dao.IPostDAO;
import com.zent.dao.PostDAO;
import com.zent.entity.Category;
import com.zent.entity.Post;
import com.zent.entity.User;
import com.zent.util.Constant;
import com.zent.util.Format;

@Controller
public class PostController {
	@Autowired
	private IPostDAO postDAO;

	public IPostDAO getPostDAO() {
		return postDAO;
	}

	public void setPostDAO(IPostDAO postDAO) {
		this.postDAO = postDAO;
	}

	@RequestMapping(value = "/deletePost", method = RequestMethod.GET)
	public String deletePost(@RequestParam("id") Integer id) {
		postDAO.deletePost(id);
		return "redirect:post";
	}

	@RequestMapping(value = "/addPost", method = RequestMethod.GET)
	public String addPost(Model model, HttpSession session) {
		if (session.getAttribute("userLogin") == null) {
			return "redirect:login";
		}
		
		ArrayList<Category> categoryList = (ArrayList<Category>) postDAO.getCategory();
		model.addAttribute("categoryList", categoryList);
		return "addPost";
	}

	@PostMapping("/addPost")
	public String multipleFileUpload(@RequestParam("image") MultipartFile[] files, @RequestParam("code") String code,
			@RequestParam("categoryId") Integer categoryId, @RequestParam("title") String title,
			@RequestParam("content") String content, HttpSession session,
			Model model) throws IOException {

		// Save file on system
		User user = (User) session.getAttribute("userLogin");
		for (MultipartFile file : files) {
			if (!file.getOriginalFilename().isEmpty()) {
				BufferedOutputStream outputStream = new BufferedOutputStream(
						new FileOutputStream(new File(Constant.image, file.getOriginalFilename())));
				String image = "images/" + file.getOriginalFilename();
				String categoryName = postDAO.getCategoryNameById(categoryId);
				Post post = new Post(categoryId, categoryName, code, title, content, 
						user.getId(), user.getUsername(), Format.formatDateDatabase(new Date()), image);
				postDAO.add(post);
				outputStream.write(file.getBytes());
				outputStream.flush();
				outputStream.close();
			} else {
				
			}
		}
		return "redirect:post";
	}
	
	@RequestMapping(value = "/updatePost", method = RequestMethod.GET)
	public String updatePost(@RequestParam("id") Integer id, Model model, HttpSession session) {
		if (session.getAttribute("userLogin") == null) {
			return "redirect:login";
		}
		
		Post post = postDAO.getPostById(id);
		ArrayList<Category> categoryList = (ArrayList<Category>) postDAO.getCategory();
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("post", post);
		model.addAttribute("id", id);
		session.setAttribute("image", post.getImage());
		return "editPost";
	}

	@PostMapping("/updatePost")
	public String multipleFileUploadUpdate(@RequestParam("image") MultipartFile file, @RequestParam("code") String code,
			@RequestParam("categoryId") Integer categoryId, @RequestParam("title") String title,
			@RequestParam("content") String content, @RequestParam("id") Integer id, 
			HttpSession session, Model model) throws IOException {
		
		User user = (User) session.getAttribute("userLogin");
		if (!file.getOriginalFilename().isEmpty()) {	// new image
			BufferedOutputStream outputStream = new BufferedOutputStream(
					new FileOutputStream(new File(Constant.image, file.getOriginalFilename())));
			String image = "images/" + file.getOriginalFilename();
			String categoryName = postDAO.getCategoryNameById(categoryId);
			Post post = new Post(categoryId, categoryName, code, title, content, 
					user.getId(), user.getUsername(), Format.formatDateDatabase(new Date()), image);
			postDAO.updatePost(post, id);
			outputStream.write(file.getBytes());
			outputStream.flush();
			outputStream.close();
		} else {	// old image
			String image = (String) session.getAttribute("image");
			String categoryName = postDAO.getCategoryNameById(categoryId);
			Post post = new Post(categoryId, categoryName, code, title, content, 
					user.getId(), user.getUsername(), Format.formatDateDatabase(new Date()), image);
			postDAO.updatePost(post, id);
		}
		session.removeAttribute("image");
		return "redirect:post";
	}
	
	@RequestMapping(value = "/post", method = RequestMethod.GET)
	public String post(HttpSession session, Model model,
			@RequestParam(required = false, value = "curPage") Integer curPage,
			@RequestParam(required = false, value = "section") Integer curSection,
			@RequestParam(required = false, value = "status") String curStatus) {
		
//		if (session.getAttribute("userLogin") == null) {
//			return "redirect:login";
//		}
		
		model.addAttribute("postSearch", new Post());
		model.addAttribute("post", new Post());
		Integer page = 1;
		Integer section = 1;
		Integer nextSection = 1;
		Integer prevSection = 1;
		model.addAttribute("category", new Category());
		if (curPage == null)
			page = 1;
		else
			page = curPage.intValue();

		if (curSection != null) {
			section = curSection.intValue();

		} else
			section = 1;
		if ("next".equals(curStatus)) {
			section++;
			nextSection = section;
			page = (section - 1) * 3 + 1;
		} else if ("prev".equals(curStatus)) {
			prevSection = section;
			if (section <= 0)
				section = 1;
			page = (section - 1) * 3 + 1;
		} else if (curStatus == null && curSection != null) {
			section = curSection;
			prevSection = curSection - 1;
			nextSection = curSection + 1;
		}
		Integer count = postDAO.countAll(new Post());// tổng số sản phẩm
		Integer numberOfPage = (int) Math.ceil((double) count / Constant.PAGE_SIZE_POST);// tổng page
		Integer numberOfSection = (int) Math.ceil((double) numberOfPage / 3);// tổng section
		model.addAttribute("startPage", (section - 1) * 3 + 1);// trang bắt đầu
		if (section == numberOfSection) {
			model.addAttribute("endPage", numberOfSection); // trang kết thúc section

		} else {
			model.addAttribute("endPage", section * 3);// trang kết thúc section

		}
		model.addAttribute("curSection", section);
		ArrayList<Post> list = (ArrayList<Post>) postDAO.getAll(new Post(), page);
		model.addAttribute("postList", list);
		model.addAttribute("activePage", page);// page hiện tại
		model.addAttribute("nextSection", nextSection);
		model.addAttribute("prevSection", prevSection);
		model.addAttribute("numberOfSection", numberOfSection);
		return "managePost";
	}

	@RequestMapping(value = "/searchPost", method = RequestMethod.GET)
	public String search(HttpSession session, Model model, @ModelAttribute("post") Post post,
			@RequestParam(required = false, value = "curPage") Integer curPage,
			@RequestParam(required = false, value = "section") Integer curSection,
			@RequestParam(required = false, value = "status") String curStatus) {
		
//		if (session.getAttribute("userLogin") == null) {
//			return "redirect:login";
//		}
		
		Integer page = 1;
		Integer section = 1;
		Integer nextSection = 1;
		Integer prevSection = 1;
		if (curPage == null)
			page = 1;
		else
			page = curPage.intValue();

		if (curSection != null) {
			section = curSection.intValue();

		} else
			section = 1;
		if ("next".equals(curStatus)) {
			section++;
			nextSection = section;
			page = (section - 1) * 3 + 1;
		} else if ("prev".equals(curStatus)) {
			prevSection = section;
			if (section <= 0)
				section = 1;
			page = (section - 1) * 3 + 1;
		} else if (curStatus == null && curSection != null) {
			section = curSection;
			prevSection = curSection - 1;
			nextSection = curSection + 1;
		}
		Integer count = postDAO.countAll(post);// tổng số sản phẩm
		Integer numberPage = (int) Math.ceil((double) count / Constant.PAGE_SIZE_POST);// tổng page
		Integer numberOfSection = (int) Math.ceil((double) numberPage / 3);// tổng section
		model.addAttribute("startPage", (section - 1) * 3 + 1);// trang bắt đầu
		if (section == numberOfSection) {
			model.addAttribute("endPage", numberPage); // trang kết thúc section

		} else {
			model.addAttribute("endPage", section * 3);// trang kết thúc section

		}
		model.addAttribute("curSection", section);
		ArrayList<Post> list = (ArrayList<Post>) postDAO.getAll(post, page);
		model.addAttribute("postList", list);
		model.addAttribute("activePage", page);// page hiện tại
		model.addAttribute("nextSection", nextSection);
		model.addAttribute("prevSection", prevSection);
		model.addAttribute("numberOfSection", numberOfSection);
		return "managePost";
	}
}
