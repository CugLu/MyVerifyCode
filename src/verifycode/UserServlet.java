package verifycode;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.commons.beanutils.BeanUtils;

public class UserServlet extends HttpServlet {
	private TxtDao txtDao = new TxtDao();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8");

		String method = req.getParameter("method");

		if (method.equals("verifyCode"))
			verifyCode(req, resp);
		else if (method.equals("login"))
			login(req, resp);
		else
			regist(req, resp);
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 得到参数
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 对表单数据进行初步判断
		Map<String, String> errors = new HashMap<String, String>();
		if (user.getUsername().equals(""))
			errors.put("username", "用户名不能为空！");
		if (user.getPassword().equals(""))
			errors.put("password", "密码不能为空！");
		String code = (String) request.getSession().getAttribute("vCode");
		if (!user.getVerifyCode().equalsIgnoreCase(code))
			errors.put("verifyCode", "验证码不对！");

		if (errors.size() > 0) {
			request.setAttribute("form", user);
			request.setAttribute("errors", errors);
			request.setAttribute("msg", "出错了~~~");
			request.getRequestDispatcher("/login.jsp").forward(request,
					response);
		} else {
			txtDao.TxtToList();
			boolean b = txtDao.IsExist(user);
			txtDao.ClearList();
			if (b) {
				request.getSession().setAttribute("session_user", "admin");
				request.getRequestDispatcher("/index.jsp").forward(request,
						response);
			} else {
				request.setAttribute("form", user);
				request.setAttribute("msg", "用户名或密码不正确~~~");
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		}
	}

	/**
	 * 注册
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
		} catch (Exception e) {
			System.out.println("表单处理出错:" + e);
		}
		// 先对user进行判断，username不为空，密码不为空，2次密码一致
		Map<String, String> errors = new HashMap<String, String>();
		if (user.getUsername().equals(""))
			errors.put("username", "用户名不能为空！");
		if (user.getPassword().equals(""))
			errors.put("password", "密码不能为空！");
		if (!user.getRepassword().equals(user.getPassword()))
			errors.put("repassword", "2次密码不一致！");

		if (errors.size() > 0) {
			request.setAttribute("errors", errors);
			request.setAttribute("form", user);
			request.getRequestDispatcher("/regist.jsp").forward(request,
					response);
		} else {
			// 在txt中进行判断，用户名不能重复,不重复就注册成功，转发到登录页面，否则返回注册页面，提示错误
			txtDao.TxtToList();
			boolean b = txtDao.IsRegist(user.getUsername());
			txtDao.ClearList();
			if (b) {
				errors.put("username", "用户名已经被注册！");
				request.setAttribute("errors", errors);
				request.setAttribute("form", user);
				request.getRequestDispatcher("/regist.jsp").forward(request,
						response);
			} else {
				txtDao.UserToTxt(user);
				request.setAttribute("msg", "注册成功，请登陆！");
				request.getRequestDispatcher("/login.jsp").forward(request,
						response);
			}
		}
	}

	/**
	 * 验证码
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void verifyCode(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();// 获取一次性验证码图片

		// 该方法必须在getImage()方法之后来调用
		VerifyCode.output(image, response.getOutputStream());// 把图片写到指定流中

		// 把文本保存到session中，为LoginServlet验证做准备
		request.getSession().setAttribute("vCode", vc.getText());
	}

}
