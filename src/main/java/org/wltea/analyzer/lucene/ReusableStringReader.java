package org.wltea.analyzer.lucene;

import java.io.Reader;

public class ReusableStringReader
extends Reader {
	  private int pos = 0, size = 0;
	  private String s = null;
	  
	  public void setValue(String s) {
	    this.s = s;
	    this.size = s.length();
	    this.pos = 0;
	  }
	  
	  @Override
	  public int read() {
	    if (pos < size) {
	      return s.charAt(pos++);
	    } else {
	      s = null;
	      return -1;
	    }
	  }
	  
	  @Override
	  public int read(char[] c, int off, int len) {
	    if (pos < size) {
	      len = Math.min(len, size-pos);
	      s.getChars(pos, pos+len, c, off);
	      pos += len;
	      return len;
	    } else {
	      s = null;
	      return -1;
	    }
	  }
	  
	  @Override
	  public void close() {
	    pos = size; // this prevents NPE when reading after close!
	    s = null;
	  }
	}
