package com.zj.gongyi.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.GeneralTechService;
import com.zj.vo.GeneralVO;

public class GoneYiAction extends ActionSupport {
	
	private String tid;

	private static final long serialVersionUID = 6374835508204898215L;
	// 示范工程的图片
	private File[] image;
	// 封装上传文件类型的属性
	private String[] imageContentType;
	// 封装上传文件名的属性
	private String[] imageFileName;

	// 工艺原理的视频
	private File tcflash;
	private String tcflashContentType;
	private String tcflashFileName;

	// 技术路线视频展示
	private File pathflash;
	private String pathflashContentType;
	private String pathflashFileName;

	// 接受依赖注入的属性
	private String savePath;
	private String videoPath;
	
	
	private String zhtype;//类别
	private String techtype;//工艺体系
	private String techname;//工艺名称
	private String pulAttr;//污染物特征
	private String imagedescribtion1;//示范工程图片描述1
	private String imagedescribtion2;
	private String imagedescribtion3;
	private String imagedescribtion4;
	private String shortmessage;//工艺简介
	private String usescope;//适用范围
	private String advancedesc;//先进性
	private String appdesc;//应用情况介绍
	private String detailmessage;//工艺详情
	
	private ArrayList<String> imagenamelist=new ArrayList<String>();//图片命名
	private ArrayList<String> imagedescrlist=new ArrayList<String>();//图片描述
	private ArrayList<String> tezhenglist=new ArrayList<String>();//污染物特征
	private String tevname="00";//文件名
	private String pvname="00";//文件名
	private String ztype="00";
	private String ttype="00";
	
	
	
	public String getTid() {
		return tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	private JSONObject jsonobject;

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}
	
	
	

	/**
	 * 专门用来上传的方法
	 * */
	public String uploadFirst() {
		
		
		boolean pic=SavePic();
		boolean tf=Savetcflash();
		boolean pt=Savepathflash();
		jsonobject = new JSONObject();
		JSONObject flag=new JSONObject();
		if(pic==false){
			flag.put("pic","工程示范图像上传失败");
		}
		if(tf==false){
			flag.put("tflash", "工艺原理视频上传失败");
		}
		if(pt==false){
			flag.put("pflash", "技术路线视频上传失败");
		}
		//构造数据
		preparefor();
		GeneralVO vo=construceVO();
		//添加到数据库
	    GeneralTechService service=new GeneralTechService();
	    service.addGeneralVO(vo);
		
		
		//下面喀什实例化到数据库中
		jsonobject.put("data",flag);
		return SUCCESS;
	}
	
	public String updateTech(){
		GeneralTechService service=new GeneralTechService();
		GeneralVO vo=service.getById(tid);
		GeneralVO update=construceVO();
		vo.setAdvancedesc(update.getAdvancedesc());
		vo.setAppdesc(update.getAppdesc());
		vo.setDetailmessage(update.getDetailmessage());
		vo.setShortmessage(update.getShortmessage());
		vo.setUsescope(update.getUsescope());
		vo.setTechname(update.getTechname());
		vo.setTechtype(update.getTechtype());
		vo.setZhtype(update.getZhtype());
		service.updateGeneralVO(vo);
		return SUCCESS;
	}
	public String delete() {
		System.out.println("deleteAction");
		GeneralTechService service=new GeneralTechService();
		return service.delete(techname) ? SUCCESS : INPUT;
	}

	/**
	 * 保存实例工厂的图片
	 * */
	public boolean SavePic() {
		System.out.println("========开始保存图片===============");
		System.out.println(savePath);
		FileOutputStream fos = null; // 文件输出流
		FileInputStream fis = null; // 文件输入流
		try {
			System.out.println(getSavePath() + "  =========");
			// 建立文件输出流
			for (int i = 0; i < image.length; i++) {
				
				// 建立文件上传流
				fis = new FileInputStream(image[i]);
				String newName=makenames(imageFileName[i]);
				fos = new FileOutputStream(getSavePath() + File.separator + newName);
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				imagenamelist.add(newName);
				
			}
			return true;

		} catch (Exception e) {
			System.out.println("文件图像上传失败");
			e.printStackTrace();
			close(fos, fis);
			return false;
		}
	}

	/**
	 * 保存工艺原理视频
	 * */
	public boolean Savetcflash() {
		System.out.println("========开始保存工艺原理视频===============");
		System.out.println(videoPath);
		FileOutputStream fos = null; // 文件输出流
		FileInputStream fis = null; // 文件输入流
		try {
			System.out.println(getVideoPath() + "  =========");
			
			// 建立文件上传流
			fis = new FileInputStream(tcflash);
			
			String fname=makenames(tcflashFileName);
			// 建立文件输出流
			fos = new FileOutputStream(getVideoPath() + File.separator + fname);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			tevname=fname;
			return true;

		} catch (Exception e) {
			System.out.println("工艺原理视频上传失败");
			e.printStackTrace();
			close(fos, fis);
			return false;
		}
	}

	/**
	 * 保存技术路线视频
	 * */
	public boolean Savepathflash() {
		System.out.println("========开始保存工艺原理视频===============");
		System.out.println(videoPath);
		FileOutputStream fos = null; // 文件输出流
		FileInputStream fis = null; // 文件输入流
		try {
			System.out.println(getVideoPath() + "  =========");
			// 建立文件输出流
			// 建立文件上传流
			fis = new FileInputStream(pathflash);
			String npath=makenames(pathflashFileName);
			fos = new FileOutputStream(getVideoPath() + File.separator + npath);
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			pvname=npath;
			return true;

		} catch (Exception e) {
			System.out.println("技术路线视频上传失败");
			e.printStackTrace();
			close(fos, fis);
			return false;
		}
	}

	/**
	 * Function :Close all the Input and Output Stream Enter parameter:
	 * InputStream,OutPutStream
	 * */
	private void close(FileOutputStream fos, FileInputStream fis) {
		if (fis != null) {
			try {
				fis.close();
			} catch (IOException e) {
				System.out.println("FileInputStream关闭失败");
				e.printStackTrace();
			}
		}
		if (fos != null) {
			try {
				fos.close();
			} catch (IOException e) {
				System.out.println("FileOutputStream关闭失败");
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重命名，以当前时间
	 * */
	private String makenames(String orname){
		if(orname==null||orname==""){
			return "0";
		}
		String name=""+System.currentTimeMillis();
		String type=orname.substring(orname.lastIndexOf(".")+1,orname.length());
		return name+"."+type;
	}
	
	/**
	 * 构造VO
	 * */
	private GeneralVO construceVO(){
		GeneralVO value=new GeneralVO();
		value.setTechname(techname);
		value.setZhtype(ztype);
		value.setTechtype(ttype);
		//污染物特征
		String tezheng="";
		if(tezhenglist.size()<1){
			tezheng="00";
		}else{
			for(String s:tezhenglist){
				tezheng+="$$"+s;
			}
		}
		value.setPulAttr(tezheng);
		
		//工程图片
		String gt="";
		if(imagenamelist.size()<1){
			gt="00";
		}else{
			for(String s:imagenamelist){
				gt+="$$"+s;
			}
		}
		value.setProjectimages(gt);
		//图片描述
		String  gtm="";
		if(imagedescrlist.size()<1){
			gtm="00";
		}else{
			for(String s:imagedescrlist){
				gtm+="$$"+s;
			}
		}
		value.setImagesdescribe(gtm);
		//视频
		value.setTcflash(tevname);
		value.setPathflash(pvname);
		//描述富文本
		value.setShortmessage(shortmessage);
		value.setUsescope(usescope);
		value.setAdvancedesc(advancedesc);
		value.setAppdesc(appdesc);
		value.setDetailmessage(detailmessage);
		return value;
	}
	/**
	 * 构造数据
	 * */
	public boolean preparefor(){
		//图片描述
		if(imagedescribtion1!=null&&imagedescribtion1!=""&&imagedescribtion1!="图片1简要说明"){
			imagedescrlist.add(imagedescribtion1);
		}
		if(imagedescribtion2!=null&&imagedescribtion2!=""&&imagedescribtion2!="图片2简要说明"){
			imagedescrlist.add(imagedescribtion2);
		}
		if(imagedescribtion3!=null&&imagedescribtion3!=""&&imagedescribtion3!="图片3简要说明"){
			imagedescrlist.add(imagedescribtion3);
		}
		if(imagedescribtion4!=null&&imagedescribtion4!=""&&imagedescribtion4!="图片4简要说明"){
			imagedescrlist.add(imagedescribtion4);
		}
		if(pulAttr!=null&&pulAttr!=""&&!("多个特征请以&&分隔".equals(pulAttr))){
			String ats[]=pulAttr.split("&&");
			for(String ss:ats){
				tezhenglist.add(ss);
			}
		}
		if(zhtype!=null&&zhtype!=""){
			ztype=zhtype;
		}
		if(techtype!=null&&techtype!=""){
			ttype=techtype;
		}
		if(shortmessage==null||shortmessage==""){
			shortmessage="00";
		}
		if(usescope==null||usescope==""){
			usescope="00";
		}
		if(advancedesc==null||advancedesc==""){
			advancedesc="00";
		}
		if(appdesc==null||appdesc==""){
			appdesc="00";
		}
		if(detailmessage==null||detailmessage==""){
			detailmessage="00";
		}
		return true;
	}
	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	public File getTcflash() {
		return tcflash;
	}

	public void setTcflash(File tcflash) {
		this.tcflash = tcflash;
	}

	public String getTcflashContentType() {
		return tcflashContentType;
	}

	public void setTcflashContentType(String tcflashContentType) {
		this.tcflashContentType = tcflashContentType;
	}

	public String getTcflashFileName() {
		return tcflashFileName;
	}

	public void setTcflashFileName(String tcflashFileName) {
		this.tcflashFileName = tcflashFileName;
	}

	public File getPathflash() {
		return pathflash;
	}

	public void setPathflash(File pathflash) {
		this.pathflash = pathflash;
	}

	public String getPathflashContentType() {
		return pathflashContentType;
	}

	public void setPathflashContentType(String pathflashContentType) {
		this.pathflashContentType = pathflashContentType;
	}

	public String getPathflashFileName() {
		return pathflashFileName;
	}

	public void setPathflashFileName(String pathflashFileName) {
		this.pathflashFileName = pathflashFileName;
	}

	// 图像的路径
	public String getSavePath() {
		return ServletActionContext.getServletContext().getRealPath(savePath);
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	// 视频的路径
	public String getVideoPath() {
		return ServletActionContext.getServletContext().getRealPath(videoPath);
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}

	public String getZhtype() {
		return zhtype;
	}

	public void setZhtype(String zhtype) {
		this.zhtype = zhtype;
	}

	public String getTechtype() {
		return techtype;
	}

	public void setTechtype(String techtype) {
		this.techtype = techtype;
	}

	public String getTechname() {
		return techname;
	}

	public void setTechname(String techname) {
		this.techname = techname;
	}

	public String getPulAttr() {
		return pulAttr;
	}

	public void setPulAttr(String pulAttr) {
		this.pulAttr = pulAttr;
	}

	public String getImagedescribtion1() {
		return imagedescribtion1;
	}

	public void setImagedescribtion1(String imagedescribtion1) {
		this.imagedescribtion1 = imagedescribtion1;
	}

	public String getImagedescribtion2() {
		return imagedescribtion2;
	}

	public void setImagedescribtion2(String imagedescribtion2) {
		this.imagedescribtion2 = imagedescribtion2;
	}

	public String getImagedescribtion3() {
		return imagedescribtion3;
	}

	public void setImagedescribtion3(String imagedescribtion3) {
		this.imagedescribtion3 = imagedescribtion3;
	}

	public String getImagedescribtion4() {
		return imagedescribtion4;
	}

	public void setImagedescribtion4(String imagedescribtion4) {
		this.imagedescribtion4 = imagedescribtion4;
	}

	public String getShortmessage() {
		return shortmessage;
	}

	public void setShortmessage(String shortmessage) {
		this.shortmessage = shortmessage;
	}

	public String getUsescope() {
		return usescope;
	}

	public void setUsescope(String usescope) {
		this.usescope = usescope;
	}

	public String getAdvancedesc() {
		return advancedesc;
	}

	public void setAdvancedesc(String advancedesc) {
		this.advancedesc = advancedesc;
	}

	public String getAppdesc() {
		return appdesc;
	}

	public void setAppdesc(String appdesc) {
		this.appdesc = appdesc;
	}

	public String getDetailmessage() {
		return detailmessage;
	}

	public void setDetailmessage(String detailmessage) {
		this.detailmessage = detailmessage;
	}

	
}
