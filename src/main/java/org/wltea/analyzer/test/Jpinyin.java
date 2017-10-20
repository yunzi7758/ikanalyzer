package org.wltea.analyzer.test;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

public class Jpinyin
{

	public static void main(String[] args) throws PinyinException
	{
		String str = "你好世界";
	    PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK); // nǐ,hǎo,shì,jiè
	    PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER); // ni3,hao3,shi4,jie4
	    PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE); // ni,hao,shi,jie
	    PinyinHelper.getShortPinyin(str); // nhsj
	    
	    
	    
	    System.out.println(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK)); // nǐ,hǎo,shì,jiè
	    System.out.println(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER)); // ni3,hao3,shi4,jie4
	    System.out.println(PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE)); // ni,hao,shi,jie
	    System.out.println(PinyinHelper.getShortPinyin(str)); // nhsj
//	    PinyinHelper.addPinyinDict("user.dict");  // 添加用户自定义字典
	}
}
