package br.com.taprecisando.ePlantsUtils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class MaskUtils {
	public static String unmask(String caracter) {
		return caracter.replaceAll("[.]", "").replaceAll("[-]", "")
					   .replaceAll("[/]", "").replaceAll("[(]", "")
					   .replaceAll("[)]", "");
	}

	public static TextWatcher insert(final String mask, final EditText ediTxt) {
		return new TextWatcher() {
			boolean isUpdating;
			String old = "";

			public void onTextChanged(CharSequence sequencia, int start, int before,
					int count) {
				String str = MaskUtils.unmask(sequencia.toString());
				String mascara = "";
				if (isUpdating) {
					old = str;
					isUpdating = false;
					return;
				}
				int i = 0;
				for (char m : mask.toCharArray()) {
					if (m != '#' && str.length() > old.length()) {
						mascara += m;
						continue;
					}
					try {
						mascara += str.charAt(i);
					} catch (Exception e) {
						break;
					}
					i++;
				}
				isUpdating = true;
				ediTxt.setText(mascara);
				ediTxt.setSelection(mascara.length());
			}

			public void beforeTextChanged(CharSequence sequencia, int start, int count,
					int after) {
			}

			public void afterTextChanged(Editable s) {
			}
		};
	}
}