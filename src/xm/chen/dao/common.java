package xm.chen.dao;

import java.io.File;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class common {
	/**
	 * Timestamp转化为String:
	 * 
	 * @return
	 */
	public static String TimetoString(Timestamp now) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 定义格式，不显示毫秒 
		// 获取系统当前时间 
		String str = df.format(now);
		return str;

	}

	/**
	 * String转化为Timestamp:
	 */
	public static Timestamp StringtoTime() {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time = df.format(new Date());
		Timestamp ts = Timestamp.valueOf(time);
		return ts;
	}

	/**
	 * 删除文件
	 * 
	 * @param fileName
	 *            文件名
	 * @return 是否删除
	 */
	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.exists() && file.isFile()) {
			if (file.delete()) {
				// System.out.println("删除单个文件" + fileName + "成功！");
				return true;
			} else {
				// System.out.println("删除单个文件" + fileName + "失败！");
				return false;
			}
		} else {
			// System.out.println("删除单个文件失败：" + fileName + "不存在！");
			return false;
		}

	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID();
		String str = uuid.toString();
		// 去掉"-"符号
		return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str.substring(19, 23)
				+ str.substring(24);

	}

	// 获得指定数量的UUID
	public static String[] getUUID(int number) {
		if (number < 1) {
			return null;
		}
		String[] ss = new String[number];
		for (int i = 0; i < number; i++) {
			ss[i] = getUUID();
		}
		return ss;
	}
}
