package spring.controller.content;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.ModelAndView;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import mybatis.CommentDAO;
import mybatis.ContentDAO;
import net.sf.json.JSONObject;
import spring.model.CommentCommand;
import spring.model.ContentCommand;

@Controller
public class ContentModify {
	
	@Autowired
	private ContentDAO contentdao;

	@Autowired
	private CommentDAO commentdao;
	
	@ModelAttribute
	public CommentCommand commentdto(){
		return new CommentCommand();
	}
	
	@ModelAttribute
	public ContentCommand contentdto(){
		return new ContentCommand();
	}
	

	
	@RequestMapping("/ContentUpdate.hash")
	public void UpdateForm(HttpServletRequest request,
			HttpServletResponse resp,
			@RequestParam("connum") int connum
			) throws IOException{
		
		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
		
		ContentCommand contentdto =  contentdao.getContent(connum);
		String conhash = contentdto.getConhash();
		conhash = conhash.replaceAll(",", "");
		contentdto.setConhash(conhash);
		
		ArrayList<CommentCommand> array = (ArrayList) commentdao.getComments(connum);
		
		int count = commentdao.commentcount(connum);
		
		request.setAttribute("count", count);
		
		resp.setContentType("application/json;charset=utf-8");
		
		JSONObject jso = new JSONObject();
		
		jso.put("data", array);
		
		
		
		PrintWriter out = resp.getWriter();
		
		out.print(jso.toString());
		
	}
/*	@RequestMapping("/ContentUpdate.hash")
	public ModelAndView UpdateForm(
			ContentCommand contentdto,
			HttpServletRequest request){
		ModelAndView mav = new ModelAndView("content/UpdateContent");
		
		int connum = Integer.parseInt(request.getParameter("connum"));
		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
		
		contentdto = contentdao.getContent(connum);
		String conhash = contentdto.getConhash();
		conhash = conhash.replaceAll(",", "");
		contentdto.setConhash(conhash);
		
		ArrayList<CommentCommand> array = (ArrayList) commentdao.getComments(connum);
		
		int count = commentdao.commentcount(connum);
		
		mav.addObject("content", contentdto);
		mav.addObject("sdf", sdf);
		mav.addObject("comment", array);
		mav.addObject("conhash", conhash);
		mav.addObject("count", count);
		
		return mav;
	}*/
	
	@RequestMapping("/UpdateTagCheck.hash")
	private String tag_check(@RequestParam("check") String check,Model model){
		model.addAttribute("check","y");
		return "userpage/TagCheckUpdate";
	}
	
	
	@RequestMapping("/ContentUpdatePro.hash")
	public void updatePro(
			HttpServletResponse resp,
			@RequestParam("connum") int connum,
			@RequestParam("content") String content,
			@RequestParam("conhash") String conhash
			) throws IOException{

		ContentCommand contentdto = new ContentCommand();

		contentdto.setContent(content);
		contentdto.setConnum(connum);
		contentdto.setConhash(conhash);
		
		int result = contentdao.modifyContent(contentdto);
		JSONObject jso = new JSONObject();
		
		PrintWriter out = resp.getWriter();
		
		/*jso.put("result", result);*/
		
		out.print(jso.toString());

	}
/*	
	@RequestMapping("/ContentUpdatePro.hash")
	public ModelAndView UpdatePro(
			ContentCommand contentdto,
			HttpServletRequest request
			){
		ModelAndView mav = new ModelAndView("content/ContentView");
		
		int connum = Integer.parseInt(request.getParameter("connum"));
		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
		String updatetag = request.getParameter("updatetag");
		
		contentdto.setConhash(updatetag);
		//글 수정을 위한 메서드
		contentdao.modifyContent(contentdto);
		
		contentdto = contentdao.getContent(connum);
		String conhash = contentdto.getConhash();
		conhash = conhash.replaceAll(",", "");
		contentdto.setConhash(conhash);
		
		ArrayList<CommentCommand> array = (ArrayList) commentdao.getComments(connum);
		
		int count = commentdao.commentcount(connum);
			
		mav.addObject("content", contentdto);
		mav.addObject("sdf", sdf);
		mav.addObject("comment", array);
		mav.addObject("conhash", conhash);
		mav.addObject("count", count);
		return mav;
	}*/


	
	
	
}
