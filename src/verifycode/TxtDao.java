package verifycode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class TxtDao {
	private List<User> userList = new ArrayList<User>();
	private String Path = "d:/user.txt";

	// 从txt中读入数据
	public void TxtToList() throws IOException {
		File file = new File(Path);
		if (!file.exists()) {
			file.createNewFile();
		}
		InputStreamReader read = new InputStreamReader(
				new FileInputStream(file), "UTF-8");
		BufferedReader br = new BufferedReader(read);// 构造一个BufferedReader类来读取文件
		String s = null;
		// 使用readLine方法，一次读一行
		while ((s = br.readLine()) != null) {
			User user = new User();
			user.setUsername(s.split(",")[0]);
			user.setPassword(s.split(",")[1]);
			userList.add(user);
		}
		br.close();
	}

	// 判断用户名是否被注册
	public boolean IsRegist(String username) {
		for (int i = 0; i < userList.size(); i++) {
			if (userList.get(i).getUsername().equals(username.trim()))
				return true;
		}
		return false;
	}

	// 判断用户是否存在
	public boolean IsExist(User user) {
		User m_user = null;
		for (int i = 0; i < userList.size(); i++) {
			m_user = userList.get(i);
			if (m_user.getUsername().equals(user.getUsername())
					&& m_user.getPassword().equals(user.getPassword()))
				return true;
		}
		return false;
	}

	// 注册成功将用户存入txt
	public void UserToTxt(User user) throws IOException {
		File file = new File(Path);

		if (!file.exists()) {
			file.createNewFile();
		}

		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(
				file, true), "UTF-8");
		BufferedWriter bufferWritter = new BufferedWriter(write);
		bufferWritter.write(user.getUsername().trim() + ","
				+ user.getPassword().trim() + '\n');
		bufferWritter.close();
	}

	public void ClearList() {
		userList.clear();
	}

	/*
	 * public static void main(String[] args) throws IOException { TxtDao tx=new
	 * TxtDao(); tx.TxtToList(); }
	 */
}
