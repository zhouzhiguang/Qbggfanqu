/**
 * $Id: PreferenceUitl.java,v 1.1 2011/05/20 07:28:07 jian.hu Exp $
 */
package com.fanqu.framework.main.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 首选项工具类
 * 
 * @author dust@downjoy.com
 */
public class PreferenceUitl {

	// 首选项
	public static final String PREFERENCE_NAME = "qbgg"; // 首选项名称

	private static PreferenceUitl preferenceUitl;

	private SharedPreferences sp;

	private Editor ed;


	private PreferenceUitl(Context context) {
		init(context);
		
	}

	public void init(Context context) {
		if (sp == null || ed == null) {
			try {
				sp = context.getSharedPreferences(PREFERENCE_NAME, 0);
				ed = sp.edit();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static PreferenceUitl getInstance(Context context) {
		if (preferenceUitl == null) {
			preferenceUitl = new PreferenceUitl(context);
		}
		preferenceUitl.init(context);
	
		return preferenceUitl;
	}

	public void saveLong(String key, long l) {
		ed.putLong(key, l);
		ed.commit();
	}

	public long getLong(String key, long defaultlong) {
		return sp.getLong(key, defaultlong);
	}

	public void saveBoolean(String key, boolean value) {
		ed.putBoolean(key, value);
		ed.commit();
	}

	public boolean getBoolean(String key, boolean defaultboolean) {
		return sp.getBoolean(key, defaultboolean);
	}

	public void saveInt(String key, int value) {
		if (ed != null) {
			ed.putInt(key, value);
			ed.commit();
		}
	}

	public int getInt(String key, int defaultInt) {
		return sp.getInt(key, defaultInt);
	}

	public String getString(String key, String defaultInt) {
		return sp.getString(key, defaultInt);
	}

	public String getString(Context context, String key, String defaultValue) {
		if (sp == null || ed == null) {
			sp = context.getSharedPreferences(PREFERENCE_NAME, 0);
			ed = sp.edit();
		}
		if (sp != null) {
			String result = sp.getString(key, defaultValue);
			
			return result;
		}
		return defaultValue;
	}

	public void saveString(String key, String value) {
		
		ed.putString(key, value);
		ed.commit();
	}

	public void remove(String key) {
		ed.remove(key);
		ed.commit();
	}

	public void destroy() {
		sp = null;
		ed = null;
		preferenceUitl = null;
	}
}
