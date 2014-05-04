// Help.java
package viewClass.extrasUI;

import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import viewClass.MenuPrincipal;

public class Help {
	private File fichero;
	private URL hsURL;
	private HelpSet helpset;
	private HelpBroker hb;

	public Help(final JMenuItem jmi, final MenuPrincipal mp) {
		try {
			fichero = new File("./help/help_set.hs");
			hsURL = fichero.toURI().toURL();

			helpset = new HelpSet(getClass().getClassLoader(), hsURL);

			hb = helpset.createHelpBroker();
			hb.enableHelpOnButton(jmi, "aplicacion", helpset);
			hb.enableHelpKey(mp.getRootPane(), "aplicacion", helpset);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}

	public void agregarHelp(final JInternalFrame jif, final String titulo) {
		try {
			hb.enableHelpKey(jif.getRootPane(), titulo, helpset);
		} catch (final Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}
	}
}