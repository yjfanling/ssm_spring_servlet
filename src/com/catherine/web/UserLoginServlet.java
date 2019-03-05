package com.catherine.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.catherine.bean.User;
import com.catherine.service.UserService;
import com.catherine.service.UserServiceImpl;

//ʹ��servlet���������û���¼���ܣ�
//����Ŀ�м���Spring���
//��service��dao��dateSource����Spring����
//��web.xml������Spring�������Ͷ�ȡ�����ļ�
/**
 * Servlet implementation class UserLoginServlet
 */
@WebServlet("/userLogin")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
		private UserService us;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//���ܱ�����
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		//��װ��user����
		User u=new User();
		u.setU_username(username);
		u.setU_password(password);
		
		//ͨ��������ȡuserService
//		ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
//		us=(UserService) ac.getBean("userService");
				
		
		//��web��Ŀ������ֻ��Ҫһ��spring����
		
		//application��
		
		//ServletContext()������������web��Ŀ����������������web��Ŀ�رն�����
		
		//ServletContext����ͨ�����ü������ﵽ������web��Ŀ������ʱ�򴴽�spring����������ʱ�ر�spring����
		WebApplicationContext wac=WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		us=(UserService) wac.getBean("userService");
		
		//����service������֤�û�
		//UserService us=new UserServiceImpl();
		User loginUser=us.getUserByInfo(u);
		
		//�����û���֤������в���
		if(loginUser==null)
		{
			//��֤ʧ�ܣ�ת����login_page.jsp
			request.setAttribute("errorMsg", "�û����������");
			request.getRequestDispatcher("/login_page.jsp").forward(request, response);
		}
		else
		{
			//��֤�ɹ����ض���index.jsp
			HttpSession session=request.getSession();
			session.setAttribute("user", loginUser);
			response.sendRedirect(request.getContextPath()+"/index.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
