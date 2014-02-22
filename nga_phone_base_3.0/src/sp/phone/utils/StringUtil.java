package sp.phone.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import sp.phone.adapter.ExtensionEmotionAdapter;
import sp.phone.bean.StringFindResult;
import android.R.integer;
import android.util.Log;


public class StringUtil {
	public final static String key = "asdfasdf";
	private final static String HOST = "http://bbs.ngacn.cc/";
	private static final String lesserNukeStyle = "<div style='border:1px solid #B63F32;margin:10px 10px 10px 10px;padding:10px' > <span style='color:#EE8A9E'>�û����������ʱ���ԣ���Ч�������ۼ�</span><br/>";
	private static final String styleAlignRight = "<div style='text-align:right' >";
	private static final String styleAlignLeft = "<div style='text-align:left' >";
	private static final String styleAlignCenter = "<div style='text-align:center' >";
	private static final String styleColor = "<span style='color:$1' >";
	private static final String collapseStart= "<div style='border:1px solid #888' >";
	private static final String ignoreCaseTag = "(?i)";
	private static final String endDiv = "</div>";

	/** ��֤�Ƿ������� */
	public static boolean isEmail(String email) {
		String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern pattern = Pattern.compile(pattern1);
		Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			return false;
		} else {
			return true;
		}
	}

	/** �ж��Ƿ��� "" ���� null */
	public static boolean isEmpty(String str) {
		if (str != null && !"".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isEmpty(StringBuffer str) {
		if (str != null && !"".equals(str)) {
			return false;
		} else {
			return true;
		}
	}

	/** yy-M-dd hh:mm */
	public static Long sDateToLong(String sDate) {
		DateFormat df = new SimpleDateFormat("yy-M-dd hh:mm");
		Date date = null;
		try {
			date = df.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	public static boolean isNumer(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static Long parseLong(String str) {
		if (str == null) {
			return null;
		} else {
			if (str.equals("")) {
				return 0l;
			} else {
				return Long.parseLong(str);
			}
		}
	}

	public static Long sDateToLong(String sDate, String dateFormat) {
		DateFormat df = new SimpleDateFormat(dateFormat);
		Date date = null;
		try {
			date = df.parse(sDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date.getTime();
	}

	public static String encodeUrl(final  String s, final String charset ){

        /*try {
            return java.net.URLEncoder.encode(s,charset);
            // this not work in android 4.4 if a english char is followed
            //by a Chinese character

        } catch (UnsupportedEncodingException e) {

            return "";
        }*/
        String ret = UriEncoderWithCharset.encode(s,null,charset);
        //Log.i("111111", s+"----->"+ret);
        return ret;
    }

	public static String parseHTML(String s) {
		// ת������
		if (s.indexOf("[quote]") != -1) {
			s = s.replace("[quote]", "");
			s = s
					.replace("[/quote]",
							"</font><font color='#1d2a63' size='10'>");

			s = s.replace("[b]", "<font color='red' size='1'>");
			s = s.replace("[/b]", "</font>");
			s = s.replace("<br/><br/>", "<br/>");
			s = s.replace("<br/><br/>", "<br/>");

			s = s.replace("[/pid]", "<font color='blue' size='2'>");
			s = s + "</font>";
		} else {
			s = "<font color='#1d2a63' size='10'>" + s;
			s = s + "</font>";
		}
		// ת�� ����

		s = s.replaceAll("(\\[s:\\d\\])", "<img src='$1'>");
		return s;
	}

	public static String decodeForumTag(String s,boolean showImage, int imageQuality, HashSet<String> imageURLSet) {
		if(StringUtil.isEmpty(s))
			return "";
		//s = StringUtil.unEscapeHtml(s);
		String quoteStyle = "<div style='background:#E8E8E8;border:1px solid #888' >";
		if(ThemeManager.getInstance().getMode() == ThemeManager.MODE_NIGHT)
			quoteStyle = "<div style='background:#000000;border:1px solid #888' >";
		
		final String styleLeft = "<div style='float:left' >";
		final String styleRight = "<div style='float:right' >";
		final String acniangofubbcode[]={
				"blink","goodjob","��","��ǹ","͵Ц",
				"��","����","����","��","��",
				"��","��","Ŷ","��","��1",
				"��Ц","��","��","��","��Ц",
				"��Ц1","��","ί��","��","����",
				"ŭ","��","��","��","ץ��",
				"�ٱ�","����","����","��","��",
				"Ϲ","��","��Ľ","����","��",
				"˥","�ƻ�ͨ","��ͬ","����","��ǹ"};//(0-44)
		final String acniangappadd[]={
				"-47218_5052bca81a77f.png","-47218_5052bd3b4b3bd.png","-1324875_50e597f5ce78d.png","-47218_5052bcba15fcf.png","-47218_5052bcb6e96d1.png",
				"-47218_5052bd2a0d49a.png","-47218_5052c10aa0303.png","-47218_5052bcaaacb45.png","-1324875_50e597c090c58.png","-47218_5052c104b8e27.png",
				"-47218_5052bc587c6f9.png","-47218_5052c1076f119.png","-47218_5052bd2497822.png","-47218_5052bd2fa0790.png","-47218_5052c0f6da079.png",
				"-47218_5052bc4cc6331.png","-47218_5052bcf37c4c9.png","-1324875_513394fbc54e1.gif","-47218_5052bc4f51be7.png","-47218_5052c1101747c.png",
				"-47218_5052c10d1f08c.png","-47218_5052bcdd279bc.png","-47218_5052bce27ab4d.png","-47218_5052bd35aec58.png","-47218_5052bcdfd9c69.png",
				"-47218_5052bc835856c.png","-47218_5052bce4f2963.png","-47218_5052bd330dfad.png","-47218_5052bc7d91913.png","-47218_5052c112b3b1b.png",
				"-47218_5052bcf0ba2db.png","-47218_5052bc8638067.png","-47218_5052bca55cb6e.png","-47218_5052bc521c04b.png","-47218_5052bca2a2f43.png",
				"-47218_5052bcad49530.png","-47218_5052bceb823da.png","-47218_5052bc80140e3.png","-47218_5052bcb3b8944.png","-1324875_50d841a63a673.png",
				"-47218_5052bcf68ddc2.png","-1324875_50e597e9d6319.png","-47218_5052bd27520ef.png","-47218_5052bcbe35760.png","-1324875_50e597f190a11.png"//0-44
		};
		
		s = s.replaceAll(ignoreCaseTag +"\\[l\\]", styleLeft);
		s = s.replaceAll(ignoreCaseTag +"\\[/l\\]", endDiv);
		//s = s.replaceAll("\\[L\\]", styleLeft);
		//s = s.replaceAll("\\[/L\\]", endDiv);
		
		s = s.replaceAll(ignoreCaseTag +"\\[r\\]", styleRight);
		s = s.replaceAll(ignoreCaseTag +"\\[/r\\]", endDiv);
		//s = s.replaceAll("\\[R\\]", styleRight);
		//s = s.replaceAll("\\[/R\\]", endDiv);
		
		
		
		s = s.replaceAll(ignoreCaseTag +"\\[align=right\\]", styleAlignRight);
		s = s.replaceAll(ignoreCaseTag +"\\[align=left\\]", styleAlignLeft);
		s = s.replaceAll(ignoreCaseTag +"\\[align=center\\]", styleAlignCenter);
		s = s.replaceAll(ignoreCaseTag +"\\[/align\\]", endDiv);
		
		s = s.replaceAll(ignoreCaseTag +"\\[quote\\]",quoteStyle);
		s = s.replaceAll(ignoreCaseTag +"\\[/quote\\]", endDiv);
		//reply
		s = s.replaceAll(
				ignoreCaseTag +"\\[pid=\\d+\\]Reply\\[/pid\\]", "Reply");
        s = s.replaceAll(
                ignoreCaseTag +"\\[pid=\\d+,\\d+,\\d\\]Reply\\[/pid\\]", "Reply");
		
		//topic
		s = s.replaceAll(
				ignoreCaseTag +"\\[tid=\\d+\\]Topic\\[/pid\\]", "Topic");
		//reply
		//s = s.replaceAll("\\[b\\]Reply to \\[pid=\\d+\\]Reply\\[/pid\\] (Post by .+ \\(\\d{4,4}-\\d\\d-\\d\\d \\d\\d:\\d\\d\\))\\[/b\\]"
		//		, "Reply to Reply <b>$1</b>");
		// ת�� tag
		//[b]
		s = s.replaceAll(ignoreCaseTag +"\\[b\\]", "<b>");
		s = s.replaceAll(ignoreCaseTag +"\\[/b\\]","</b>"/* "</font>"*/);
		
		//item
		s = s.replaceAll(ignoreCaseTag +"\\[item\\]", "<b>");
		s = s.replaceAll(ignoreCaseTag +"\\[/item\\]","</b>");
		
		s = s.replaceAll(ignoreCaseTag +"\\[u\\]", "<u>");
		s = s.replaceAll(ignoreCaseTag +"\\[/u\\]","</u>");
		
		s = s.replaceAll(ignoreCaseTag +"\\[s:(\\d+)\\]", "<img src='file:///android_asset/a$1.gif'>");
		for(int i=0;i<45;i++){
			s = s.replaceAll(ignoreCaseTag +"\\[s:ac:"+acniangofubbcode[i]+"\\]", "<img src='file:///android_asset/acniang/"+acniangappadd[i]+"'>");
		}
		s = s.replace(ignoreCaseTag +"<br/><br/>", "<br/>");
		//[url][/url]
		s = s.replaceAll(ignoreCaseTag +"\\[url\\](http[^\\[|\\]]+)\\[/url\\]",
				"<a href=\"$1\">$1</a>");
		s = s.replaceAll(ignoreCaseTag +"\\[url=(http[^\\[|\\]]+)\\]\\s*(.+?)\\s*\\[/url\\]"
				,"<a href=\"$1\">$2</a>");
		//flash
		s = s.replaceAll(ignoreCaseTag +"\\[flash\\](http[^\\[|\\]]+)\\[/flash\\]",
				"<a href=\"$1\"><img src='file:///android_asset/flash.png' style= 'max-width:100%;' ></a>");
		//color
		
		//s = s.replaceAll("\\[color=([^\\[|\\]]+)\\]\\s*(.+?)\\s*\\[/color\\]"
		//		,"<b style=\"color:$1\">$2</b>");
		s=s.replaceAll(ignoreCaseTag +"\\[color=([^\\[|\\]]+)\\]",styleColor);
		s = s.replaceAll(ignoreCaseTag +"\\[/color\\]", "</span>");
		
		
		//lessernuke
		s = s.replaceAll("\\[lessernuke\\]", lesserNukeStyle);
		s = s.replaceAll("\\[/lessernuke\\]", endDiv);
		
		s = s.replaceAll("\\[table\\]","<table border='1px' cellspacing='0px' style='border-collapse:collapse;color:blue'><tbody>");
		s = s.replaceAll("\\[/table\\]","</tbody></table>");
		s = s.replaceAll("\\[tr\\]","<tr>");
		s = s.replaceAll("\\[/tr\\]","<tr>");
		s = s.replaceAll("\\[td\\]", "<td>");
		s = s.replaceAll("\\[/td\\]","<td>");
		//[i][/i]
		s = s.replaceAll(ignoreCaseTag +"\\[i\\]", "<i style=\"font-style:italic\">");
		s = s.replaceAll(ignoreCaseTag +"\\[/i\\]", "</i>");
		//[del][/del]
		s = s.replaceAll(ignoreCaseTag +"\\[del\\]", "<del class=\"gray\">");
		s = s.replaceAll(ignoreCaseTag +"\\[/del\\]","</del>");
		
		s = s.replaceAll(ignoreCaseTag +"\\[font=([^\\[|\\]]+)\\]","<span style=\"font-family:$1\">");
		s = s.replaceAll(ignoreCaseTag +"\\[/font\\]","</span>");
		
		//collapse
		s = s.replaceAll(ignoreCaseTag +"\\[collapse([^\\[|\\]])*\\](([\\d|\\D])+?)\\[/collapse\\]",
				collapseStart + "$2" + endDiv);

		//size
		s = s.replaceAll(ignoreCaseTag +"\\[size=(\\d+)%\\]","<span style=\"font-size:$1%;line-height:$1%\">");
		s = s.replaceAll(ignoreCaseTag +"\\[/size\\]","</span>");
		
		//[img]./ddd.jpg[/img]
	//	if(showImage){
			s = s.replaceAll(ignoreCaseTag +"\\[img\\]\\s*\\.(/[^\\[|\\]]+)\\s*\\[/img\\]", 
				"<a href='http://" + HttpUtil.NGA_ATTACHMENT_HOST +"/attachments$1'><img src='http://" + HttpUtil.NGA_ATTACHMENT_HOST +"/attachments$1' style= 'max-width:100%' ></a>");
			s = s.replaceAll(ignoreCaseTag +"\\[img\\]\\s*(http[^\\[|\\]]+)\\s*\\[/img\\]", 
				"<a href='$1'><img src='$1' style= 'max-width:100%' ></a>");
			
			//s = s.replaceAll(ignoreCaseTag +"\\[IMG\\]\\s*\\.(/[^\\[|\\]]+)\\s*\\[/IMG\\]", 
			//		"<a href='http://img.ngacn.cc/attachments$1'><img src='http://img.ngacn.cc/attachments$1' style= 'max-width:100%' ></a>");
			//s = s.replaceAll(ignoreCaseTag +"\\[IMG\\]\\s*(http[^\\[|\\]]+)\\s*\\[/IMG\\]", 
			//		"<a href='$1'><img src='$1' style= 'max-width:100%' ></a>");
		/*}else{
			s = s.replaceAll("\\[img\\]\\s*\\.(/[^\\[|\\]]+)\\s*\\[/img\\]", 
					"<a href='http://img.ngacn.cc/attachments$1'><img src='file:///android_asset/ic_offline_image.png' style= 'max-width:100%;' ></a>");
			s = s.replaceAll("\\[img\\]\\s*(http[^\\[|\\]]+)\\s*\\[/img\\]", 
					"<a href='$1'><img src='file:///android_asset/ic_offline_image.png' style= 'max-width:100%;' ></a>");
			s = s.replaceAll("\\[IMG\\]\\s*\\.(/[^\\[|\\]]+)\\s*\\[/IMG\\]", 
					"<a href='http://img.ngacn.cc/attachments$1'><img src='file:///android_asset/ic_offline_image.png' style= 'max-width:100%;' ></a>");
			s = s.replaceAll("\\[IMG\\]\\s*(http[^\\[|\\]]+)\\s*\\[/IMG\\]", 
					"<a href='$1'><img src='file:///android_asset/ic_offline_image.png' style= 'max-width:100%;' ></a>");
			
		}*/

		  Pattern p = Pattern.compile("<img src='(http\\S+)' style= 'max-width:100%' >"); 
          Matcher m = p.matcher(s); 
          try{
          while (m.find()) { 
              String s0 = m.group(); 
              String s1 = m.group(1);
              String path = ExtensionEmotionAdapter.getPathByURI(s1);
              if(path != null)
              {
            	  
            	  String newImgBlock = "<img src='"+
            			  "file:///android_asset/"
            			  + path
            			  +"' style= 'max-width:100%' >";
            	  		  s = s.replace(s0, newImgBlock);
              }else if(!showImage){
            	  path = "ic_offline_image.png";
            	  String newImgBlock = "<img src='"+
            			  "file:///android_asset/"
            			  + path
            			  +"' style= 'max-width:100%' >";
            	  		  s = s.replace(s0, newImgBlock);
              }
              else
              {

            	  String newImgBlock = "<img src='"+
            			  buildOptimizedImageURL(s1, imageQuality)
            			  +"' style= 'max-width:100%' >";
            	  		  s = s.replace(s0, newImgBlock);
            	  		  int t = s1.indexOf(HttpUtil.NGA_ATTACHMENT_HOST);
            	  		  if (t != -1 && imageURLSet != null)
            	  		  {
            	  			  imageURLSet.add(s1.substring(t + HttpUtil.NGA_ATTACHMENT_HOST.length() + 13)); //this is the length from HOST/attachments/^
            	  		  }
              }
          }
          }catch(Exception e){
        	  
          }
		
		
		
		return s;
	}
	
	public static String buildOptimizedImageURL(String url, int imageQuality)
	{
		String encodedURL = null;
		try {
			encodedURL = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			return url;
		}
		String r = url;
		switch (imageQuality) {
		case 1:
			r = "http://ngac.sinaapp.com/imgapi/getimg.php?url=" + encodedURL + "&size=small";
			break;
		case 2:
			r = "http://ngac.sinaapp.com/imgapi/getimg.php?url=" + encodedURL + "&size=medium";
			break;
		case 3:
			r = "http://ngac.sinaapp.com/imgapi/getimg.php?url=" + encodedURL + "&size=large";
			break;
		}
		return r;
	}

	public static String removeBrTag(String s){
		s =s.replaceAll("<br/><br/>", "\n");
		s =s.replaceAll("<br/>", "\n");
		return s;
	}
	/**
	 * ����URL
	 * 
	 * @param url
	 * @return
	 */
	public static String doURL(String url) {
		if (!url.startsWith(HOST)) {
			return HOST + url;
		} else {
			return url;
		}
	}

	public static String getSaying() {
		Random random = new Random();
		int num = random.nextInt(SAYING.length);
		return SAYING[num];
	}

	public static String unEscapeHtml(String s){
		String ret = "";
		ret = StringHelper.unescapeHTML(s);
		return ret;
	}
	

	
	public static String buildThreadURLByTid(String tid){
		return "/read.php?tid="+tid;
	}
	private static final String[] SAYING = {
			"ս�����죬����������;��˹����˹",
			"���ӵ����ߡ� ",
			"���಻����ս����ս���ͻ�������ࡣ;Լ����F�������",
			"ս���޷�����˭����ȷ�ģ�ֻ�ܾ���˭�Ǵ���һ����;���� ",
			"û�к���½ս�ӵľ�������û�п��ӵ��·��� ;���������Ͻ� ������D������",
			"��ý��������Ҫ��˼��������;��³����",
			"������ϲ��ϲ������ʷ��վ��������ߣ������ǽ����������ᣡ;��³����",
			"�����ڵ�����ڣ��˴˱˴ˡ�;�����ռ� ",
			"öս��Ѳ����������ۣ�90����Ԫ�� ",
			"һ��F-22����ս��������ۣ�1.35����Ԫ",
			"һ��F-117A��ҹӥ������ս��������ۣ�1.22����Ԫ ",
			"һ��B-2��ը������ۣ�22����Ԫ",
			"ֻҪ��������ڣ��ͻ���ս�� ��;����˹̹ ",
			"���ŵ�����׼��;���� ����������ϵ�ʹ��˵�� ",
			"�κγ�ʵ�ľ���ָ�ӹٶ���������ڶ��þ���ʱ���¹�����;�޲��ء����������",
			"������ñ������Ȩ���������������ձ��;Ҷ���� ",
			"������������������½ս�Ӻ�����ǹ��;�������� Լ�� J. ����",
			"վ������һ�ߵ��˲��ܳ�Ϊ�ֲ����ӡ�;��������",
			"û�бȰ���ǹ�ӻ���Ȼ������ˬ���ˡ� ;�𼪶�",
			"����û������ս��������˵��ս����Ȼ���������ġ�;����˹�� ",
			"�Ѿ��˺��������Ѻá�",
			"����ʿ���ǽ���ս��һ�����⽻�������ǿ�ʼս���Ĺؼ���",
			"�Ƽ��ڱ�Ӧ��֮ǰ�����ǵ��������ġ���֮�����ƣ���֮�����;����������ɭ",
			"�ϼһ�����ս����ǰ�ߵ�ȴ��С�����ǡ�;�ղ��ء����� ����ʮһλ������ͳ",
			"ս���ϵ�ָ�ӹ����ǶԵģ�ƨ�ɺ����˾����Ǵ��ģ����Ǿٳ�������;������ ����ǰ������",
			"���ɲ�������ѵģ�������������½ս�ӻ�������ͷ��",
			"�Ҳ�֪�������������ս�������ʲô�����������Ĵ������ս���ǹ�����ʯͷ�� ;����˹̹",
			"������֪������ʲô�����ѵ���ȥ����",
			"֪��֪�ˣ���ս������;����",
			"���������˶��ܴ������澳�У������������֪��һ���˵���ʵһ�棬�����������;�ֿ�",
			"������ǲ��������֤�����ǵ�������м�ֵ������������¼������ǵ�����,�޲��ء����������",
			"����֮�������ñ����Ͱ����ߵ���Ѫһ����һ���ϴˢ;����˹�����ѷ (�������μ�, ��������ͳ, indpdc���Ե������)",
			"��������ɵñȻ������죬�ǰ˳��Ǽ�ֱ�����������������ⲻ��ȫ��",
			"5�������ֻ��3��;�����ռ�",
			"�������̫��˳������ô��ϲ���ϵ��ˡ�;ʿ���ռ� ",
			"û���κ���ս�ƻ����������������Ч��;������",
			"������˿���ε������������Ͳ��������ǵ������ˡ�;����½��ѵ����ʾ",
			"������������������������־���档;�����",
			"ö��ǹ��̹�˵�������ۣ�8����Ԫ ",
			"�Ե������������Ա�ǿ������",
			"�����ǲ�ס��66ʽ(XD)���ǳ��������� ",
			"ֻ���������˽�½ս�ӣ�½ս�Ӻ����ĵ��ˣ������˶��ڳ����ֵ���",
			"�����ߵ�½ս��ԱԽ�࣬��ԽHappy ��;����½�������˽��� ",
			"�����ˣ�����������ɳ�����͵ľ���������ġ� ",
			"��סҪ͸�����󿴱��ʡ���Ҫ��Ϊ��������İ����������",
			"�٣������㣬����Ҳ��û�ӵ��ˡ�;ʿ���ռ�",
			"������粻�����ר����֧�䡣;������Ƿ� ",
			"�������ܻ���Щ΢����������£����ǻ�֧�ַ��ɣ�Ȼ��ݻ�����;����̩ ",
			"Ӣ�۲����ñȱ��˸��¸ң������Ƕ�����5���ӡ�;���",
			"��󣬺����ˣ�����������ˡ��ӽ���ս�����������ˡ�;�޲��ء����������",
			"��Щ�˻���һ���ӣ�һֱϣ����Щʲô���£���½ս��Ա��û���Ǹ����⡣ ;���",
			"һ��������ֱ�ӽ����ڸոձ���ը���������ǲ����ǵġ� ;�����վ��н�",
			"����֮�����ܹ��ڴ���˯���Ⱦ�������Ϊ���������Ϊ����վ�ڡ�;���Ρ���Τ��",
			"�����һ��ʼû�㶨���Ͽ���п���֧Ԯ�� ",
			"ҷ�ⵯ�����Ĳ����ǵ��ˡ�;����½������",
			"�Ŷ�Э������Ҫ�������ñ������㰤ǹ�ӡ�",
			"ֻ�к�ƽ�Ż������յ�ʤ���� ;��Ĭ��",
			"��һ���ֲ��������ӵ�пƼ��������������ǲ���ȡ�ж�������ʮ�ֺ�ڡ�;������������˹ (������66�ι�����) ",
			"���ߣ����Ҳ��;����",
			"����Ŀɿ��Ժͺ���������Բ��ȶ�����ϻ����������ҡ�;�޲��ء����������",
			"��ս���У���Ӯ������ֻ��һ��֮� ;������˹����˰�ɪ����",
			"�㲻��˵����û�н�������������ÿ��ս���У����Ƕ������·������ɵ��㡣",
			"�������׺�������ô��֮ǰ����������ܻ�������ˡ�;�޲��ء����������",
			"ָ�ӵļһﲻ�䵱Ӣ�ۣ�������Ӣ����ս���е�����",
			"�κκϸ��ʿ����Ӧ�÷���ս����ͬʱ��Ҳ����ֵ��Ϊ֮ս���Ķ�����",
			"�����ʤ�̾ͱ�ȥ������ ",
			"��֪��������������;����",
			"������μ�ս�����ˣ��϶�û�����������;���� ��˹",
			"˵����ǿ�ڽ������˿϶�û�����Զ�������;������˹����˰�ɪ����",
			"а��ĵó�������������Ϊ ;�����ɡ�����",
			"If a man has done his best, what else is there?;���� S. �Ͷٽ���",
			"���׵ı�ը�뾶���Ǳ������Ծ�����һ�㡣",
			"������һ�߿�ҫ����ΰ���һ���ڲк����ա�",
			"ÿ���������������ɡ������Լ������ɡ�;�������ء�������(��������)",
			"�����ս��������˵�����Ǹ�ϣ�����е�ս������û�б�����;�������֡�D����˹��",
			"�ɹ������յ㣬ʧ��Ҳ�����սᣬֻ�������������㡣;��˹�١��𼪶� ",
			"û�б�ʤ�ľ��ģ�ս���ذ����ɡ�;������˹����˰�ɪ ",
			"���е�ս��������ս����Ϊ���е����඼��ͬ����;�������ߡ�����¡ ",
			"��ս���У��ڶ�����û�н��͵ġ�;���ꡤ������������ ",
			"�ö��벻�����ǽ����ĵ�һ����Ʒ��",
			"time is money",
			"���۶�ôʦ��������Ҳ�������������Ϊս��������ġ�;����˹��������",
			"Ϊ�������˰������޴��ģ����Ƿ���Ӧ�ø�л�ϵ�����������������;���Σ��Ͷٽ���",
			"ս����Ŀ�Ĳ���Ҫ��Ϊ������������Ҫ�ø����ĵ���Ϊ���Ĺ���������;���Σ��Ͷٽ���",
			"һ������� �� û���ˣ��Ͳ�����ս����;Լɪ��˹����",
			"һ���˵����������Ĳ��ң����������˵�������ֻ�Ǽ򵥵�ͳ�����֡�;Լɪ��˹����",
			"�Һ�ս������˵س�񣬷������ǿ��»ᰮ������;�޲��أ���",
			"���Լ����̲�ס��ʼ����ֱ������ʶ���Լ���ɣ��������ϯ�� ;Peter Reid,�ڲ����ն�ɣ�����ı����������",
			"�Ҹ����Ҷ���Josh �������¡�������ɭϣ���ְ�ΪӢ�����ӱ���������������¸������ҵ�Ů��Olivia��Ȼ�����ǵ����ж�������ˮ���ҡ����ǲ���˵���㲻��Ϊ��ɭ�������ˣ�;Lee Dixon ",

			"սʿ��Ը��Ϊ��һС��ѫ�¶���ս���ס�;�����أ����ð���",
			"����ս�ܵ���һ����ս�ܡ�;�����أ����ð���",
			"��������ͬһ���˶���̫�ã���������ѧ�������е�ս����;�����أ����ð���",
			"��������������û��û�Σ����ػ�����������ͷ���ԡ�;�վ�",
			"...��......��ʾ�ĺ����ǲ�ͬ�ġ�",
			"���죡������������������",
			"ĳ�У���˵�����������û���е�������������",
			"1024",
			"�㶮�ģ�",
			"YSLM",
			"5¥:people don't want face,sky down no enemy.",
			"1¥:no ����,who's your ����.",
			"Your brain has two parts:the left&the right .Your left brain has nothing right,and your right brain has nothing left.",
			"������һ����ɵ��������Ư��,�����ֺ���Ǯ�ֿϵ�����Ů����ô��ô��?", "8¥:������������Ӣ�ﶼ��ô�ã��ѹ�������ôǿ�� ",
			"�Ǽ�����2:Ŀ��֮��", "�Ǽ�����2:��������ʹ ", "���ŭ��:�Ǹ���,��Ҫһֱ�����ҿ�����",
			"��������Ц��:DK̹û�ö�,������",
			"¥������æ���Ҽ��������ĺ���ȡ�����������֡��������²��ڡ�¥�������,�����������������ճ¡�",
			"������ջ�ǹŴ�����������ջ��", "�����޶�����ҩ����������������",
			"ƽʱ��Ϧ�ദ���ˣ�ֻҪ����ҹ���£����ɸ���ɴ���Է��Ͳ���ʶ�ˡ�",
			"û�õ�С��ɫ�õ��书�����к�ǿ����ѧ�ԺͶ����ԣ����ǲ�����á�", "���ų����׷�+���ӵľ����ǿ������ˣ�����Ҫ���ù�ϵ��",
			"Ӣ����һ�Ѻñ������õ��Ӳ���ȥ����������",
			"���Ҽ��У�Ӣ��Ҫ�ǲ��������;�����������һ���˼�����Ҳ����Ϊһ���д����Ю�������˵���Ӣ�۷��ġ�",
			"һ��Ҫ�����ԵĴ��£��ų����У������ſ�ˮ��У�ȥ���ɣ���",
			"ʹ����ɱ��Ҫ���ܻ��ڵĶ�������Ҫ����һ�����ӣ������˾�����˻�͵Ϯ���������Ǹ��û��ᡭ��",
			"���ֶ��������������������ҷ��ҷɵ÷ɿ졣����Ҫ�Ǹ�Զ·��ȴ��������",
			"�����ײͣ�2����ţ��+�ϵ�Ů���졣(������ջ���ڹ�Ӧ......)",
			"���˴Ӳ��¶������˴Ӳ����¶��������˴Ӳ��¶�ȴ�ϱ������¶������˴Ӳ����¶�ȴû�˻�������",
			"��������ʾ�Լ�����Ϊ�����������һ����֦����֪��ߵغ��С��ɫ��ܣ�����������ջ��ʼ��Ӧ��֦����",
			"��һ����ֱ�Ľֵ�����׷ɱ�������кܶ���Ҫ������Ū�����Ե�С̯������Ҫ�ģ�",
			"�����ð�������ʽ���ƣ���Ŷ��գ�һ�����У������ð����Ǳ����޳ܣ���������������˶��Ӳ��С���",
			"����ǧ����������ˣ����ᱻ�������ž�ʹ�ŵ��������ſ�ˮ��У����ɣ�", "���о������˾����а�����Ӣ�ۣ��ղ���Ҳ���顭��",
			"��ʱ����ΰ����ã��������Σ����е����", "���г�ɱ���ľ�����һ���ӣ���������",
			"����������鲼һ��һ���������������Ҳ���鲼һ��һ���������",
			"����һ����������һ����˳��һ�������Ļῲ��������һ����˳�ĴӲ���Ʊ����",
			"�����˶�����Ǯ��ͭ����ٳ��֣�һ��һ�ŵ���Ʊ�Ȳ�ֽ�����ˡ�", " (������ջ��)��С��֪ʶԨ��������(+Ǯ)�ش�",
			"�����¾�1������(�Ϻ�������8��)��1��ͽ���������������ܲˡ�",
			"����ʱ�� �� ���� �� һ��֧�Ų�ס�ˣ��ͻẰ�˰�æ�� �� �Ը�����ħͷ�����ú�����ʲô�������壬���һ���ϣ� ��",
			"����ͼ��ݾ���ʧ�ԡ���", "һ���˺����ƾ�һ�����±��ꡣ", "����������У��Ĵ�?#���Ĵ�%�����Ĵ�*(����",
			"�ν�ʱ����ʱ���н�������ʱ��β���������", "��͢�Ĵ󽫾�����ʺ���������Ǹ��֡�",
			"��Ժ��������Ժ(�һ������������ŵ��ӹ�˾����)��", "��Ů�������ǣ����������Ƶġ��� ",
			"��Ҫ��Ϊ�����������ͬ�͵�ٱ�",
			"���к��������,�ѽ�����wap�ĳ�net!!!",
			"178,׼ʱ����,�����ӵ�!",
			"178,׼ʱ�ӵ�,��������!",
			"�ʺ����ۼ������½���210�������,����!",
			"Ѱ��NGA�ͻ��˿�Դ���������Google Play�̵��ᰲ��������װ",

	};

	public static int getNowPageNum(String link) {
		// link: http://bbs.ngacn.cc/thread.php?fid=7&page=1&rss=1&OOXX=
		int ret = 1;
		if (link.indexOf("\n") != -1) {
			link = link.substring(0, link.length() - 1);
		}
		if (link.indexOf("&") == -1) {
			return ret;
		} else {
			try{
			ret = Integer.parseInt(link.substring(link.indexOf("page=") + 5,
					link.length()));
			}catch(Exception E){
				
			}
		}
		return ret;
	}
	final static String tips = 	
					"����bug�����ȸ��µ����°汾,�����������[@��Ԋ����]/[@cfan8]/[@force0119]\n"
					+"�ʺ緢��ģʽ�����ۼ������½���210�������,ר����Ҫ��ɱ���û�!\n"
					+"��׿4.0�������ڿ������ý�������ť���������뷨�Ϸ���,�����ڲ˵���ť\n"
					+"�����п�ѡ��2G/3GʱͼƬ��תѹ��,ʹ������SAE����\n"
					+"NGA�ͻ��˿�Դ�濪���Ŷ�([@��Ԋ����]/[@cfan8]/[@force0119]/[@�Ǹ��ͽ���])�ٿ���,����Ƭ��\n"
					+"Ѱ�Ҹ�������Google Play�̵��ᰲ����NGA�ͻ��˿�Դ�氲װ\n"
					+"178,׼ʱ�ӵ�,��������";
	public static String getTips(){
		
		return tips;
		
	}


	public static StringFindResult getStringBetween(String data,int begPosition,
			String startStr, String endStr)
	{
		StringFindResult ret = new StringFindResult();
		do{
			if(isEmpty(data) || begPosition < 0 || data.length() <= begPosition
					|| isEmpty(startStr) || isEmpty(startStr))
				break;
			
			int start = data.indexOf( startStr,begPosition);
			if(start == -1)
				break;
			
			start +=startStr.length();
			int end = data.indexOf(endStr, start);
			if(end == -1)
				end = data.length();
			ret.result = data.substring(start,end);
			ret.position = end + endStr.length(); 
			
		}while(false);
		
		return ret;
	}

	public static int getUrlParameter(String url, String paraName){
		if(StringUtil.isEmpty(url))
		{
			return 0;
		}
		final String pattern = paraName+"=" ;
		int start = url.indexOf(pattern);
		if(start == -1)
			return 0;
		start +=pattern.length();
		int end = url.indexOf("&",start);
		if(end == -1)
			end = url.length();
		String value = url.substring(start,end);
		int ret = 0;
		try{
			ret = Integer.parseInt(value);
		}catch(Exception e){
			Log.e("getUrlParameter", "invalid url:" + url);
		}
		
		return ret;
	}


}

