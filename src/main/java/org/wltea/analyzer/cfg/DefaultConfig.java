/**
 * IK 中文分词  版本 5.0
 * IK Analyzer release 5.0
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
 * 
 */
package org.wltea.analyzer.cfg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InvalidPropertiesFormatException;
import java.util.List;
import java.util.Properties;

import org.wltea.analyzer.dic.Dictionary;

/**
 * Configuration 默认实现 2012-5-8
 *
 */
public class DefaultConfig implements Configuration
{

	/*
	 * 分词器默认字典路径
	 */
	private static final String PATH_DIC_MAIN = "org/wltea/analyzer/dic/main2012.dic";
	private static final String PATH_DIC_QUANTIFIER = "org/wltea/analyzer/dic/quantifier.dic";

	/*
	 * 分词器配置文件路径
	 */
	private static final String FILE_NAME = "IKAnalyzer.cfg.xml";
	// 配置属性——扩展字典
	private static final String EXT_DICT = "ext_dict";
	// 配置属性——扩展停止词典
	private static final String EXT_STOP = "ext_stopwords";

	private Properties props;
	/*
	 * 是否使用smart方式分词
	 */
	private boolean useSmart;

	/**
	 * 返回单例
	 * 
	 * @return Configuration单例
	 */
	public static Configuration getInstance()
	{
		return new DefaultConfig();
	}

	/*
	 * 初始化配置文件
	 */
	private DefaultConfig()
	{
		props = new Properties();

		InputStream input = this.getClass().getClassLoader().getResourceAsStream(FILE_NAME);
		if (input != null)
		{
			try
			{
				props.loadFromXML(input);
			} catch (InvalidPropertiesFormatException e)
			{
				e.printStackTrace();
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		} else
		{
			System.out.println("have not IKAnalyzer.cfg.xml");
		}
	}

	/**
	 * 返回useSmart标志位 useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
	 * 
	 * @return useSmart
	 */
	public boolean useSmart()
	{
		return useSmart;
	}

	/**
	 * 设置useSmart标志位 useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
	 * 
	 * @param useSmart
	 */
	public void setUseSmart(boolean useSmart)
	{
		this.useSmart = useSmart;
	}

	/**
	 * 获取主词典路径
	 * 
	 * @return String 主词典路径
	 */
	public String getMainDictionary()
	{
		return PATH_DIC_MAIN;
	}

	/**
	 * 获取量词词典路径
	 * 
	 * @return String 量词词典路径
	 */
	public String getQuantifierDicionary()
	{
		return PATH_DIC_QUANTIFIER;
	}

	/**
	 * 获取扩展字典配置路径
	 * 
	 * @return List<String> 相对类加载器的路径
	 */
	public List<String> getExtDictionarys()
	{
		List<String> extDictFiles = new ArrayList<String>(2);
		String extDictCfg = props.getProperty(EXT_DICT);
		if (extDictCfg != null)
		{
			// 使用;分割多个扩展字典配置
			String[] filePaths = extDictCfg.split(";");
			if (filePaths != null)
			{
				for (String filePath : filePaths)
				{
					if (filePath != null && !"".equals(filePath.trim()))
					{
						extDictFiles.add(filePath.trim());
					}
				}
			}
		}
		return extDictFiles;
	}

	/**
	 * 获取扩展停止词典配置路径
	 * 
	 * @return List<String> 相对类加载器的路径
	 */
	public List<String> getExtStopWordDictionarys()
	{
		List<String> extStopWordDictFiles = new ArrayList<String>(2);
		String extStopWordDictCfg = props.getProperty(EXT_STOP);
		if (extStopWordDictCfg != null)
		{
			// 使用;分割多个扩展字典配置
			String[] filePaths = extStopWordDictCfg.split(";");
			if (filePaths != null)
			{
				for (String filePath : filePaths)
				{
					if (filePath != null && !"".equals(filePath.trim()))
					{
						extStopWordDictFiles.add(filePath.trim());
					}
				}
			}
		}
		return extStopWordDictFiles;
	}

	public static void main(String[] args)
	{
		System.out.println(new DefaultConfig().getExtDictionarys().size());
		System.out.println(new DefaultConfig().getExtStopWordDictionarys().size());
		int count = 0;
		// 加载扩展词典配置
		InputStream is = null;
		List<String> extDictFiles = new DefaultConfig().getExtDictionarys();
		if (extDictFiles != null)
		{
			for (String extDictName : extDictFiles)
			{
				// 读取扩展词典文件
				// is = Dictionary.class.getResourceAsStream(extDictName);
				is = DefaultConfig.class.getClassLoader().getResourceAsStream(extDictName);
				// 如果找不到扩展的字典，则忽略
				if (is == null)
				{
					System.out.println("aaaaaaaaaa");
					continue;
				}
				try
				{
					BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
					String theWord = null;
					do
					{
						theWord = br.readLine();
						if (theWord != null && !"".equals(theWord.trim()))
						{
							// 加载扩展词典数据到主内存词典中
							System.out.println(theWord);
//							 _MainDict.fillSegment(theWord.trim().toCharArray());
						}
					} while (theWord != null);

				} catch (IOException ioe)
				{
					System.err.println("Extension Dictionary loading exception.");
					ioe.printStackTrace();

				} finally
				{
					try
					{
						if (is != null)
						{
							is.close();
							is = null;
						}
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			}
		}
	}
}
