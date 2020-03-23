package servlet;

import dao.UserDAO;
import model.User;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RegisterActionServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		

		boolean flag = true; // ����У���־λ
		
		String uname = null;
		String upwd = null;
		String confirmPassword = null;
		String code = null;
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			upload.setHeaderEncoding("UTF-8");
			// 3���ж��ύ�����������Ƿ����ϴ���������
			if (!ServletFileUpload.isMultipartContent(request)) {

				uname = request.getParameter("uname");
				upwd = request.getParameter("upwd");
				confirmPassword = request.getParameter("upwds");
				code = request.getParameter("code");

			} else {
				// 4��ʹ��ServletFileUpload�����������ϴ����ݣ�
				// ����������ص���һ��List<FileItem>���ϣ�
				// ÿһ��FileItem��Ӧһ��Form����������
				List<FileItem> list;
				try {
					list = upload.parseRequest(request);
					for (FileItem item : list) {
						if (item.isFormField()) {
							String name = item.getFieldName();
							String value = item.getString("UTF-8");
							if ("uname".equals(name)) {
								uname = value;
							} else if ("upwd".equals(name)) {
								upwd = value;
							} else if ("upwds".equals(name)) {
								confirmPassword = value;
							} else if("code".equals(name)){
								code = value;
							}
						} 
					}
				} catch (FileUploadException e) {
					e.printStackTrace();
				}
			}
		
			HttpSession session = request.getSession();
			String code2 = (String)session.getAttribute("code");
		// ����У��
		if (StringUtil.isEmpty(uname)) {
			request.setAttribute("uname", "��¼������Ϊ��");
			flag = false;
		}
		if (StringUtil.isEmpty(upwd)) {
			request.setAttribute("upwd", "���벻��Ϊ��");
			flag = false;
		}
		
		if (!confirmPassword.equals(upwd)) {
			request.setAttribute("upwds", "������������벻һ��");
			flag = false;
		}
		else if(StringUtil.isEmpty(code)){
			request.setAttribute("kong", "��֤��Ϊ��");
			flag = false;
		}
		else if(!code2.equals(code)){
			request.setAttribute("kong", "��֤������");
			flag = false;
		}

		// ����ҵ��
		boolean flag2 = true;
		boolean flag3 = false;
	
		if (flag) {
			UserDAO dao = new UserDAO();
			User u = new User();
			u.setUname(uname);
	
			List<User> users = dao.select(u);
			
			
			if (users.size() > 0) {
				request.setAttribute("uname", "��¼���Ѵ���");
				flag2 = false;
			} 
			
			
			else{
				// ���
				u.setUname(uname);
				u.setUpwd(upwd);
				
				flag3 = dao.insert(u);
				if (flag3) {
					u.setUpwd(null);
					u = dao.select(u).get(0);
					HttpSession session1 = request.getSession(true);
					session1.setAttribute("user", u);					
				}
			}
		}

		// ������Ӧ
		if (!flag) { // ����֤ʧ��
			// register.jsp
			request.getRequestDispatcher("/register.jsp").forward(
					request, response);
		} else if (flag2 == false) { // ����
			// register.jsp
			request.getRequestDispatcher("/register.jsp").forward(
					request, response);
		} else if (flag3 == false) { // ע��ʧ��
			// register.jsp
			request.getRequestDispatcher("/register.jsp").forward(
					request, response);
		} else { // ע��ɹ�
					// login.jsp
			response.sendRedirect("/MI/z.protect/register_success.jsp");
		}
	}

}
