/**
 * IK 中文分词  版本 5.0.1
 * IK Analyzer release 5.0.1
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 */
package org.wltea.analyzer.lucene;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;

/**
 * IK分词器，Lucene Analyzer接口实现
 * 兼容Lucene 4.0版本
 */
public final class IKAnalyzer extends Analyzer{
	
	private boolean useSmart;
	private String keys;
	  private String decode;
	public boolean useSmart() {
		return useSmart;
	}
	//老子根本不知道在哪里执行的
	public void setUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
	}

	/**
	 * IK分词器Lucene  Analyzer接口实现类
	 * 
	 * 默认细粒度切分算法
	 */
	public IKAnalyzer(){
		this(false);
	}
	
	/**
	 * IK分词器Lucene Analyzer接口实现类
	 * 
	 * @param useSmart 当为true时，分词器进行智能切分
	 */
	public IKAnalyzer(boolean useSmart){
		super();
		this.useSmart = useSmart;
		SetCache cache = SetCache.GetInstance();
		keys = (String) cache.getSetchache("keys");
		decode = (String) cache.getSetchache("decode");
	}
	/**
	 * 重载Analyzer接口，构造分词组件
	 */
	@Override
	protected TokenStreamComponents createComponents(String fieldName) {
		Tokenizer _IKTokenizer = new IKTokenizer(this.useSmart());
		return new TokenStreamComponents(_IKTokenizer);
	}
	@Override
	protected Reader initReader(String fieldName, Reader reader) {
		/*System.out.println(fieldName);
		keys="description";
		if (keys!=null&&!"".equals(keys)&&keys.contains(fieldName))
		{
			char[] abyte0 = new char[4096];
	        StringBuffer stringbuffer = new StringBuffer();
	        for(int i = 0; i != -1;)
	        {
	            try
				{
					i = reader.read(abyte0, 0, 4096);
					if(i != -1)
		            {
		                String s2 = new String(abyte0, 0, i);//new String(abyte0, 0, i, "utf-8");
		                stringbuffer.append(s2);
		            }
				} catch (IOException e)
				{
					e.printStackTrace();
				}
	        }
	        String result = stringbuffer.toString();
	        try
			{
	        	result = URLDecoder.decode(result, "utf-8");
			} catch (UnsupportedEncodingException e)
			{
				e.printStackTrace();
			}
	        reader = new ReusableStringReader();
	        ((ReusableStringReader) reader).setValue(result);
//	        reader = new StringReader(result);
//	        
		}*/
		
	    return reader;
	  }
}
