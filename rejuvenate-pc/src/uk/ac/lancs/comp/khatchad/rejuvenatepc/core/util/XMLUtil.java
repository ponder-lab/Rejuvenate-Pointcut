/**
 * 
 */
package uk.ac.lancs.comp.khatchad.rejuvenatepc.core.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.eclipse.ajdt.core.javaelements.AdviceElement;
import org.eclipse.jdt.core.IJavaElement;
import org.jdom.Attribute;
import org.jdom.Element;

/**
 * @author raffi
 *
 */
public class XMLUtil {
	private XMLUtil() {}

	/**
	 * @param advElem
	 * @return
	 */
	@SuppressWarnings("restriction")
	public static File getSavedXMLFile(AdviceElement advElem) {
		String relativeFileName = XMLUtil.getRelativeXMLFileName(advElem);
		File aFile = new File(FileUtil.WORKSPACE_LOC, relativeFileName);
		if (!aFile.exists())
			throw new IllegalArgumentException("No XML file found for advice "
					+ advElem.getElementName());
		return aFile;
	}

	/**
	 * @param advElem
	 * @return
	 */
	public static String getRelativeXMLFileName(AdviceElement advElem) {
		StringBuilder fileNameBuilder = new StringBuilder(advElem.getPath()
				.toOSString());
		fileNameBuilder.append("#" + advElem.toDebugString());
		fileNameBuilder.append(".rejuv-pc.xml");
		return fileNameBuilder.toString();
	}

	@SuppressWarnings("restriction")
	public static PrintWriter getXMLFileWriter(AdviceElement advElem)
			throws IOException {
		String fileName = getRelativeXMLFileName(advElem);
		final File aFile = new File(FileUtil.WORKSPACE_LOC, fileName);
		return FileUtil.getPrintWriter(aFile, false);
	}

	/**
	 * @param elem
	 * @return
	 */
	public static Element getXML(IJavaElement elem) {
		Element ret = new Element(elem.getClass().getSimpleName());
		ret.setAttribute(new Attribute("id", elem.getHandleIdentifier()));
		ret.setAttribute(new Attribute("name", elem.getElementName()));
		ret.setAttribute(new Attribute("type", String.valueOf(elem
				.getElementType())));
		return ret;
	}
}