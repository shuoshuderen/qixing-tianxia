package com.qixing.photosc;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
import org.eclipse.jdt.internal.compiler.ast.ArrayAllocationExpression;

import com.qixing.luxian.Lway;
import com.qixing.tiezi.LtieZi;
import com.qixing.user.Luser;
import com.qixing.util.GJnowtime;

@WebServlet("/MorePhotosc")
public class MorePhotosc extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public MorePhotosc() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("进入图片上传");
List<String> list=new ArrayList<String>();
		LtieZi ltieZi = new LtieZi();

		DiskFileUpload fUpload = new DiskFileUpload();
		List fList = null;
		try {
			fList = fUpload.parseRequest(request);
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator iterator = fList.iterator();
		String userid, postname, postnr,lei,time;
		
		while (iterator.hasNext()) {
			FileItem item = (FileItem) iterator.next();
			if (item.isFormField()) {
				request.setAttribute(item.getFieldName(), item.getString());

				System.out.println("item.getFieldName()-->"
						+ item.getFieldName() + "=" + item.getString());

			} else {
				try {
					
					Date date = new Date();
					long f1 = date.getTime();
					// 图片名
					String s = f1 + ".jpg";
					list.add(s);
					// 存放位置
					String s1 = "F:\\photo\\" + s;
					
					item.write(new File(s1));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		postnr = (String) request.getAttribute("postnr");
		userid = (String) request.getAttribute("userid");
		postname = (String) request.getAttribute("postname");
		lei=(String) request.getAttribute("lei");
		time=GJnowtime.gettime();
		ltieZi.Inewtz(userid, postname, postnr, time, lei);
		
		 int tzid1=ltieZi.SpostidBytime(time);
		 for (int i = 0; i < list.size(); i++) {
			 ltieZi.insertphoto(tzid1, list.get(i));
		}
		
		 
		System.out.println("完成图片上传");
	}

}
