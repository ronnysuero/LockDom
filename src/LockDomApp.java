/**
 * LockDomApp.java
 *
 * @author Ronny Z. Suero
 */

import viewClass.extrasUI.Splash;

public class LockDomApp {
	// test-driver
	public static void main(final String[] args) {
		new Thread(new Splash().IniciarApp()).start();
	}
}
