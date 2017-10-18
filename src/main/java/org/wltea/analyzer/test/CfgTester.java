/**
 * 
 */
package org.wltea.analyzer.test;

import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;

import junit.framework.TestCase;

/**
 * @author Administrator
 *
 */
public class CfgTester extends TestCase{
	
	public void testCfgLoading(){
		System.out.println(DefaultConfig.getInstance().getExtDictionarys().size());
		System.out.println(DefaultConfig.getInstance().getExtStopWordDictionarys().size());
	}

}
