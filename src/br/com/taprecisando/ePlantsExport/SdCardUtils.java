package br.com.taprecisando.ePlantsExport;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("SdCardPath")
public class SdCardUtils {

	private static final String TAG = SdCardUtils.class.getName();

	public static File getSdCardFile(String novaPasta, String arquivo) {

		//File sdcard = Environment.getExternalStorageDirectory();
		//File diretorio = new File(sdcard, novaPasta);
		
		File diretorio = new File("/mnt/sdcard", novaPasta);

		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
		File file = new File(diretorio, arquivo);
		return file;	
	}

	public static File writeToSdcard(File f, byte[] bytes) {
		try {
			if (f != null) {
				FileOutputStream out = new FileOutputStream(f);
				out.write(bytes);
				out.close();
			}

		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return f;
	}

}
