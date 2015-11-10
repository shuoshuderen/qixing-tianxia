package com.qixing.photosc;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import com.qixing.user.Luser;

@WebServlet("/Touxiangphoto")
public class Touxiangphoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Touxiangphoto() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入图片上传");
		Luser luser=new Luser();
		Date date = new Date();
		long f1 = date.getTime();
		System.out.println(f1);
		String s = f1 + ".jpg";
		System.out.println(s);
		String s1="F:\\photo\\" + s;
		System.out.println(s1);
		DiskFileUpload fUpload = new DiskFileUpload();
		List fList = null;
		try {
			fList = fUpload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 依次处理每个上传的文件
		Iterator iterator = fList.iterator();
		while (iterator.hasNext()) {
			FileItem item = (FileItem) iterator.next();
			if (item.isFormField()) {
				
				
				request.setAttribute(item.getFieldName(), item.getString());
				System.out.println("item.getFieldName()-->"
						+ item.getFieldName() + "=" + item.getString());
				String userid=item.getString();
				boolean boo=luser.Uimage(userid, s);
			} else {
				try {
					item.write(new File(s1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println("完成图片上传");
	}

}
