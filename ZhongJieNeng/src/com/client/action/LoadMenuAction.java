package com.client.action;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.zj.util.CommonTools;
import com.zj.vo.MenuItem;
import com.zj.vo.ShortGeneralVO;
import com.zj.vo.TechTypeVO;
import com.zj.vo.TypeVO;

public class LoadMenuAction extends ActionSupport{

	private static final long serialVersionUID = -1510018825923970804L;
	private JSONObject jsonobject;

	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}
	
	/**
	 * 程序入口函数，主页面。
	 */
	@Override
	public String execute() throws Exception {
		jsonobject = new JSONObject();
		ArrayList<MenuItem> list=CommonTools.construceMenu();
		StringBuilder result=new StringBuilder();
		String[] cla=new String[]{"icon-home","icon-cogs","icon-briefcase","icon-user","icon-comments-alt"};
		result.append("<ul class=\"vertical-nav dark red\"><li class=\"showhide\" style=\"display: none;\"><span class=\"title\">MENU</span><span class=\"icon\"><em></em><em></em><em></em><em></em></span></li>");
		for(int i=0;i<list.size();i++){
			result.append(consFirstmenustring(i==0,list.get(i),cla,i%list.size()));
		}
		result.append("</ul>");
		System.out.println("========="+result.toString());
		jsonobject.put("data", result.toString());
		System.out.println(jsonobject.toString());
		return SUCCESS;
	}
	/**
	 * 
	 * */
	private String consFirstmenustring(boolean flag,MenuItem me,String[] css,int num){
		StringBuilder sb=new StringBuilder();
		if(flag){
			sb.append("<li class=");
			sb.append("\"");
			sb.append("active\" ");
		}else{
		    sb.append(" <li ");
		}
		sb.append("style=\"\">");
		sb.append("<a href=\"javascript:showmessage('");
		sb.append(me.getType()+"','");
		sb.append(me.getCode()+"');\"><i class=\"");
		sb.append(css[num]+"\"");
		sb.append("></i>");
		sb.append(me.getName());
		if(me.getChild().size()>0){
			sb.append("<span class=\"submenu-icon\"></span></a>");
			sb.append("<ul style=\"display: none;\">");
			for(MenuItem mm:me.getChild()){
				sb.append(conSub(mm));
			}
			sb.append("</ul></a></li>");
		}else{
			sb.append("</a></li> ");
		}
		return sb.toString();
	}
	
	/**
	 * 地柜构造菜单
	 * */
	private String conSub(MenuItem me){
		StringBuilder ss=new StringBuilder();
		ss.append("<li><a href=\"javascript:showmessage('");
		ss.append(me.getType()+"','");
		ss.append(me.getCode()+"');\">");
		ss.append(me.getName());
		if(me.getChild().size()>0){
			ss.append("<span class=\"submenu-icon\"></span></a>");
			ss.append("<ul style=\"\">");
			for(MenuItem mm:me.getChild()){
				ss.append(conSub(mm));
			}
			ss.append("</ul></li>");
		}else{
			ss.append("</a></li>");
		}
	    return ss.toString();	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<TechTypeVO> telist=CommonTools.getTechVOs();//公益类别
		List<ShortGeneralVO> gvos=CommonTools.getGeneVOs();
		List<TypeVO> tvos=CommonTools.getTypeVOs();
		System.out.println(telist.size());
		System.out.println(gvos.size());
		System.out.println(tvos.size());

	}

}
