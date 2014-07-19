package com.client.action;

import java.io.File;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.service.GeneralTechService;
import com.zj.util.CommonTools;
import com.zj.vo.GeneralVO;

public class LoadTechAction extends ActionSupport {

	private static final long serialVersionUID = 7471085848737492619L;
	private String type;// 类型
	private String number;// 编号

	private JSONArray array;
	private JSONObject jsonobject;

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}

	@Override
	public String execute() throws Exception {
		StringBuilder bb = new StringBuilder();
		bb.append("<h3 class=\"widget-title\"><span>工艺</span>展示</h3><ul class=\"recent-list-cars clearfix\">");
		bb.append(construceDefaultString());
		bb.append("</ul>");
		jsonobject = new JSONObject();
		jsonobject.put("data", bb.toString());
		jsonobject.put("array", array);
		System.out.println(jsonobject.toString());
		return SUCCESS;
	}

	public String LoadByNumber(){
		StringBuilder bb = new StringBuilder();
		bb.append("<h3 class=\"widget-title\"><span>工艺</span>展示</h3><ul class=\"recent-list-cars clearfix\">");
		bb.append(consByNumber(type,number));
		bb.append("</ul>");
		jsonobject = new JSONObject();
		jsonobject.put("data", bb.toString());
		jsonobject.put("array", array);
		System.out.println(jsonobject.toString());
		return SUCCESS;
	}
	/**
	 * 按号码来的
	 * */
	public String consByNumber(String type,String number){
		GeneralTechService service = new GeneralTechService();
		List<GeneralVO> list = service.getByTypeAndNumber(type, number);
		array=new JSONArray();
		if(list!=null){
			for(GeneralVO vo:list){
				JSONObject json = new JSONObject();
				json.put("appdesc", vo.getAppdesc());
				json.put("detail", vo.getDetailmessage());
				json.put("shortmessage", vo.getShortmessage());
				array.add(json);
				if (!"00".equals(vo.getProjectimages())) {
					String newpath = ChangeString(vo.getProjectimages());
					vo.setProjectimages(newpath);
				} else {
					vo.setProjectimages(getDefaultImage());
				}
				if (!"00".equals(vo.getImagesdescribe())) {

					String newvo = ChangeString1(vo.getImagesdescribe());
					vo.setImagesdescribe(newvo);
				}
				if (!vo.getTcflash().equals("00")) {
					vo.setTcflash("../upload/uploadflash/" + vo.getTcflash());
				} else {
					vo.setTcflash("../upload/uploadflash/def1.swf");
				}
				if (!"00".equals(vo.getPathflash())) {
					vo.setPathflash(getVideoPath() + File.separator
							+ vo.getPathflash());
				} else {
					vo.setPathflash(getDefaultFlash());
				}
			}
		}
		StringBuilder bu=new StringBuilder();
		for(int i=0;i<list.size();i++){
			bu.append(constructString(list.get(i), i));;
		}
		return bu.toString();
	}
	public String construceDefaultString() {
		List<GeneralVO> list = getdefaultVOs(5);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			builder.append(constructString(list.get(i), i));
		}
		return builder.toString();
	}

	/**
	 * 返回默认的 max条
	 * */
	public List<GeneralVO> getdefaultVOs(int max) {
		GeneralTechService service = new GeneralTechService();
		List<GeneralVO> list = service.getVOs();
		int i=0;
		array=new JSONArray();
		for (; i < max&&i<list.size(); i++) {
			GeneralVO vo = list.get(i);
			JSONObject json = new JSONObject();
			json.put("appdesc", vo.getAppdesc());
			json.put("detail", vo.getDetailmessage());
			json.put("shortmessage", vo.getShortmessage());
			array.add(json);
			if (!"00".equals(vo.getProjectimages())) {
				String newpath = ChangeString(vo.getProjectimages());
				vo.setProjectimages(newpath);
			} else {
				vo.setProjectimages(getDefaultImage());
			}
			if (!"00".equals(vo.getImagesdescribe())) {

				String newvo = ChangeString1(vo.getImagesdescribe());
				vo.setImagesdescribe(newvo);
			}
			if (!vo.getTcflash().equals("00")) {
				vo.setTcflash("../upload/uploadflash/" + vo.getTcflash());
			} else {
				vo.setTcflash("../upload/uploadflash/def1.swf");
			}
			if (!"00".equals(vo.getPathflash())) {
				vo.setPathflash(getVideoPath() + File.separator
						+ vo.getPathflash());
			} else {
				vo.setPathflash(getDefaultFlash());
			}
		}
		return list.subList(0, Math.min(max, i));
	}

	public String constructString(GeneralVO vo, int num) {
		StringBuilder sb = new StringBuilder();
		sb.append("<li><a href=\"javascript:;\" class=\"single-image picture video\">");
		sb.append(" <img src=\"");
		sb.append(vo.getProjectimages().split("##")[0] + "\"");
		sb.append("alt=\"\"/>");
		sb.append("<span class=\"video-icon\" onClick=\"showVideo('"
				+ vo.getTcflash() + "')\">&nbsp;</span>");
		sb.append("<span class=\"picture-icon\" onClick=\"$.fancybox.open(");
		String ima = vo.getProjectimages();
		String ps[] = ima.split("##");
		sb.append("[");
		for (int i = 0; i < ps.length; i++) {
			sb.append("{ ");
			sb.append(" href : '" + ps[i] + "',");
			sb.append("title : '" + vo.getImagesdescribe().split("##")[i] + "'");
			sb.append("},");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		sb.append(",");

		sb.append(" {helpers : { thumbs : { width: 75,height: 50}}} );\">&nbsp;</span> <span class=\"curtain\">  </a>");
		sb.append("<a  href=\"#showmessage\" class=\"list-meta\"  onClick=\"fillMessageDiv('1','"
				+ num + "')\">");
		sb.append("<h6 class=\"title-list-item\">" + vo.getTechname() + "</h6>");
		sb.append("</a>");
		sb.append("<a href=\"#showmessage\" class=\"button orange\" onClick=\"fillMessageDiv('2','"
				+ num + "')\" >简介</a>");
		sb.append("<a href=\"#showmessage\" class=\"see\" onClick=\"fillMessageDiv('3','"
				+ num + "')\">&nbsp;&nbsp;详述</a>");
		sb.append("</li>");
		return sb.toString();
	}

	public String getPath() {
		return ServletActionContext.getServletContext().getRealPath(
				"/upload/uploadimge");
	}

	public String getVideoPath() {
		return ServletActionContext.getServletContext().getRealPath(
				"/upload/uploadflash");
	}

	public String ChangeString(String org) {
		String path = "";
		String[] newpath = CommonTools.SplitString(org);
		for (String s : newpath) {
			String ss = "../upload/uploadimge/" + s + "##";
			path = path + ss;
		}
		path = path.substring(0, path.length() - 2);
		return path.trim();
	}

	public String ChangeString1(String org) {
		String path = "";
		String[] newpath = CommonTools.SplitString(org);
		for (String s : newpath) {
			path = path + s + "##";
		}
		path = path.substring(0, path.length() - 2);
		return path.trim();
	}

	public String getDefaultImage() {
		return ServletActionContext.getServletContext().getRealPath(
				"/upload/uploadimge")
				+ File.separator + "defimage.png";
	}

	public String getDefaultFlash() {
		return ServletActionContext.getServletContext().getRealPath(
				"/upload/uploadflash")
				+ File.separator + "def1.swf";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	

}
