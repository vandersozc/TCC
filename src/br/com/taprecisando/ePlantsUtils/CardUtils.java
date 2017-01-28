package br.com.taprecisando.ePlantsUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.util.Log;

@SuppressLint("SdCardPath")
public class CardUtils {

	private static final String TAG = CardUtils.class.getName();

	public static File getSdCardFile(String novaPasta, String arquivo) {
		File diretorio = new File("/mnt/sdcard", novaPasta);

		if (!diretorio.exists()) {
			diretorio.mkdir();
		}
		File file = new File(diretorio, arquivo);
		return file;	
	}

	public static File writeToSdcard(File arquivo, byte[] bytes) {
		try {
			if (arquivo != null) {
				FileOutputStream out = new FileOutputStream(arquivo);
				out.write(bytes);
				out.close();
			}

		} catch (FileNotFoundException e) {
			Log.e(TAG, e.getMessage(), e);
		} catch (IOException e) {
			Log.e(TAG, e.getMessage(), e);
		}
		return arquivo;
	}

}
