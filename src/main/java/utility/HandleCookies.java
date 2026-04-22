package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;

public class HandleCookies {

	public boolean loadCookies(WebDriver driver, String filePath) {
		try {
			File file = new File(filePath);
			if (file.exists() && file.length() > 0) {
				System.out.println("👉 Loading cookies...");
				ObjectInputStream in = new ObjectInputStream(new FileInputStream(file));
				Set<Cookie> cookies = (Set<Cookie>) in.readObject();
				in.close();
				driver.manage().deleteAllCookies();
				for (Cookie cookie : cookies) {
					try {
						driver.manage().addCookie(cookie);
					} catch (Exception e) {
						System.out.println("Skipping cookie: " + cookie.getName());
					}
				}
				driver.navigate().refresh();
				System.out.println("✅ Logged in using cookies");
				return true; 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false; 
	}

	public void saveCookies(WebDriver driver, String filePath) {
		try {
			File file = new File(filePath);
			Set<Cookie> cookies = driver.manage().getCookies();
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(cookies);
			out.close();
			System.out.println("✅ Cookies saved!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}