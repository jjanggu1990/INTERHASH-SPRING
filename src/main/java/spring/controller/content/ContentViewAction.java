package spring.controller.content;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import mybatis.CommentDAO;
import mybatis.ContentDAO;
import spring.model.CommentCommand;
import spring.model.ContentCommand;
import spring.model.MemberCommand;

@Controller
public class ContentViewAction {

	@RequestMapping("/Main.hash")
	private String mainview() {
		/*Properties prop = System.getProperties();
		Set set = prop.keySet();
		Iterator iter = set.iterator();
		while(iter.hasNext()){
			String key=(String)iter.next();
			System.out.println(key + " :: "+prop.getProperty(key));
			
		}*/
		return "main";
	}

	@Autowired
	private ContentDAO contentdao;

	@Autowired
	private CommentDAO commentdao;
	
	public void setContentdao(ContentDAO contentdao) {
		this.contentdao = contentdao;
	}

	public void setCommentdao(CommentDAO commentdao) {
		this.commentdao = commentdao;
	}

	@ModelAttribute("contentdto")
	public ContentCommand content() {
		return new ContentCommand();
	}

	@ModelAttribute("commentdto")
	public CommentCommand comment() {
		return new CommentCommand();
	}

	@RequestMapping("/ContentView.hash")
	public ModelAndView contentView(@ModelAttribute("contentdao") ContentCommand content,
			@ModelAttribute("commentdto") CommentCommand comment, HttpServletRequest request,@RequestParam("connum")int connum) throws Exception {
		ModelAndView mav = new ModelAndView("content/ContentView");
		/*int connum = Integer.parseInt(request.getParameter("connum"));*/
	

		//int connum = 91;
		//int connum = 88;
		int connum1 = 93;
		SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd HH:mm");
		content = contentdao.getContent(connum1);
		String conhash = content.getConhash();
		conhash = conhash.replaceAll(",", "");
		content.setConhash(conhash);
		ArrayList<CommentCommand> array = (ArrayList) commentdao.getComments(connum1);
		
		int count = commentdao.commentcount(connum1);
		
		mav.addObject("content", content);
		mav.addObject("sdf", sdf);
		mav.addObject("comment", array);
		mav.addObject("conhash", conhash);
		mav.addObject("count", count);

		return mav;
	}
	


}
