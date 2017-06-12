package id.secure.passbank.login.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Pattern;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Utility {

	private Context context;

	public Utility(Context context) {
		super();
		this.context = context;
	}
	
	public static View view(int res, View view) {
		return (View) view.findViewById(res);
	}
	
	public static TextView tv(int res, View view) {
		return (TextView) view.findViewById(res);
	}
	
	public static void setText(int res, View view, String value) {
		tv(res, view).setText(value);
	}
	
	public static void setText(int res, View view, int value) {
		tv(res, view).setText(value);
	}
	
	public static String getText(int res, View view) {
		return tv(res, view).getText().toString();
	}
	
	public static EditText edit(int res, View view) {
		return (EditText) view.findViewById(res);
	}
	
	public static void setEditText(int res, View view, String value) {
		edit(res, view).setText(value);
	}
	
	public static String getEditText(int res, View view) {
		return getEditText(res, view, false);
	}

	public static String getEditText(int res, View view, boolean sanitizeName) {
		String result = edit(res, view).getText().toString();

		return sanitizeName ? sanitizeName(result) : result;
	}
	
	public static Button button(int res, View view) {
		return (Button) view.findViewById(res);
	}
	
	public static ListView list(int res, View view) {
		return (ListView) view.findViewById(res);
	}
	
	public static String sanitizeName(String name) {
		return name.replaceAll("[^a-zA-Z0-9]+", "");
	}
	
	public static boolean isEmail(String email) {
		return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}
	
	public static boolean isPhone(String phone) {
		Pattern pattern = Pattern.compile("6[0-9][^0][0-9]+");
		Pattern pattern1 = Pattern.compile("0[^0][0-9]+");

		return (pattern.matcher(phone).matches() || pattern1.matcher(phone).matches()) && (phone.length() >= 10);
	}
	
	public static boolean isNumber(String id) {
		try {
			Double.parseDouble(id);
		} catch(NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static String MD5(String raw) throws NoSuchAlgorithmException {

		MessageDigest mDigest = MessageDigest.getInstance("MD5");
		mDigest.update(raw.getBytes());
		byte messageDigest[] = mDigest.digest();

		StringBuffer MD5Hash = new StringBuffer();
		for (int i = 0; i < messageDigest.length; i++) {
			String h = Integer.toHexString(0xFF & messageDigest[i]);
			while (h.length() < 2)
				h = "0" + h;
			MD5Hash.append(h);
		}

		return MD5Hash.toString();

	}

	public static String SHA1(String input) throws NoSuchAlgorithmException {

		MessageDigest mDigest = MessageDigest.getInstance("SHA1");
		byte[] result = mDigest.digest(input.getBytes());

		StringBuffer SHAStr = new StringBuffer();
		for (int i = 0; i < result.length; i++) {
			SHAStr.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
		}

		return SHAStr.toString();
	}
	
	public static boolean checkEmpty(View view, int... resList) {
		boolean hasEmpty = false;
		for (int res : resList) {
			hasEmpty = getEditText(res, view).equals("");
			if (hasEmpty)
				break;
		}
		return hasEmpty;
	}

	public static boolean stringMatch(View view, int res1, int res2) {
		String str1 = getEditText(res1, view);
		String str2 = getEditText(res2, view);

		return str1.equals(str2);
	}
	
	public boolean isFillUp(String inputText){
		if (!inputText.equals("")) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void toast(String msg,Context context){
		Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
	}
	
	public static String toPrice(String price) {
		if (price.equals("")) {
			return "";
		} else {
			double dbl = Double.parseDouble(price);
			return new DecimalFormat("#,###,###").format(dbl);
		}
	}
	
	public static String Date(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
		String currentDateandTime = sdf.format(new Date());
		return currentDateandTime;
		
	}
	
}
