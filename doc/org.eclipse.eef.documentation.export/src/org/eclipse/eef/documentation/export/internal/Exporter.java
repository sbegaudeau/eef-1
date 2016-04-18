/*******************************************************************************
 * Copyright (c) 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.eef.documentation.export.internal;

import static org.junit.Assert.fail;

import com.google.common.base.Charsets;
import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.Closeables;
import com.google.common.io.Files;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.junit.Test;
import org.osgi.framework.Bundle;

/**
 * The documentation exporter.
 *
 * @author sbegaudeau
 */
public class Exporter {
	/**
	 * The path of the documentation pages in the bundle org.eclipse.eef.documentation.
	 */
	public static final String DOCUMENTATION_PAGES_ROOT_PATH = "/pages"; //$NON-NLS-1$

	/**
	 * The start of the root element containing the whole body.
	 */
	public static final String ROOT_ELEMENT_START = "<md-content layout=\"column\" layout-align=\"center stretch\" class=\"content\">"; //$NON-NLS-1$

	/**
	 * The end of the root element containing the whole body.
	 */
	public static final String ROOT_ELEMENT_END = "</md-content>"; //$NON-NLS-1$

	/**
	 * The extension of HTML files.
	 */
	public static final String HTML_EXTENSION = ".html"; //$NON-NLS-1$

	/**
	 * The start of an "a" element.
	 */
	public static final String A_ELEMENT_START = "<a href=\""; //$NON-NLS-1$

	/**
	 * The end of the href attribute of an "a" element.
	 */
	public static final String A_HREF_ATTRIBUTE_END = "\""; //$NON-NLS-1$

	/**
	 * The extension of the textile files.
	 */
	public static final String TEXTILE_EXTENSION = ".textile"; //$NON-NLS-1$

	/**
	 * The extension of the CSS files.
	 */
	public static final String CSS_EXTENSION = ".css"; //$NON-NLS-1$

	/**
	 * The name of the file containing the Eclipse Help table of content.
	 */
	public static final String TOC_XML_FILENAME = "toc.xml"; //$NON-NLS-1$

	/**
	 * The URL of the root of the documentation on the website.
	 */
	public static final String ROOT_DOCUMENTATION_URL = "#/documentation"; //$NON-NLS-1$

	/**
	 * Exports the documentation for the website.
	 */
	@Test
	public void exportDocumentation() {
		Bundle bundle = Platform.getBundle("org.eclipse.eef.documentation"); //$NON-NLS-1$
		if (bundle != null) {
			String version = this.getVersion(bundle);

			// Find all the documentation pages recursively in the documentation bundle
			Enumeration<URL> entries = bundle.findEntries(DOCUMENTATION_PAGES_ROOT_PATH, "*.*", true); //$NON-NLS-1$
			while (entries.hasMoreElements()) {
				URL entry = entries.nextElement();
				String path = entry.getPath().substring((DOCUMENTATION_PAGES_ROOT_PATH + "/").length()); //$NON-NLS-1$

				if (entry.getFile().endsWith(HTML_EXTENSION)) {
					// Read, process and write the processed HTML files
					List<String> lines = this.readLines(entry);
					List<String> linesToKeep = this.getBody(lines, path, version);
					this.writeLines(linesToKeep, path, version);
				} else if (this.shouldCopy(path)) {
					// Let's copy other resources directly at its new location (images, video, etc)
					this.copyResource(entry, path, version);
				}
			}
		}
	}

	/**
	 * Indicates if we should copy the resource with the given path.
	 *
	 * @param path
	 *            The path of the resource
	 * @return <code>true</code> if we should copy it, <code>false</code> otherwise
	 */
	public boolean shouldCopy(String path) {
		boolean shouldCopy = true;

		// We will not copy css files, textile files nor the file toc.xml.

		shouldCopy = shouldCopy && !path.endsWith(TEXTILE_EXTENSION);
		shouldCopy = shouldCopy && !path.endsWith(CSS_EXTENSION);
		shouldCopy = shouldCopy && !path.endsWith(TOC_XML_FILENAME);
		return shouldCopy;
	}

	/**
	 * Copy the resource from the given entry at a new location computed from its path and the version of the
	 * documentation.
	 *
	 * @param entry
	 *            The entry
	 * @param path
	 *            The path
	 * @param documentationVersion
	 *            The version of the documentation
	 */
	private void copyResource(URL entry, String path, String documentationVersion) {
		InputStream inputStream = null;
		try {
			inputStream = entry.openStream();
			Path outputPath = this.getOutputPath(documentationVersion, path);
			outputPath.toFile().getParentFile().mkdirs();
			outputPath.toFile().createNewFile();
			ByteStreams.copy(inputStream, Files.newOutputStreamSupplier(outputPath.toFile()));
		} catch (IOException e) {
			fail(e.getMessage());
		} finally {
			try {
				Closeables.close(inputStream, false);
			} catch (IOException e) {
				fail(e.getMessage());
			}
		}
	}

	/**
	 * Returns the version of the given bundle as a string with the major, minor and micro numbers only.
	 *
	 * @param bundle
	 *            The bundle
	 * @return The version of the given bundle
	 */
	private String getVersion(Bundle bundle) {
		return "" + bundle.getVersion().getMajor() + '.' + bundle.getVersion().getMinor() + '.' + bundle.getVersion().getMicro(); //$NON-NLS-1$
	}

	/**
	 * Returns the content of the body of the HTML file processed.
	 *
	 * @param lines
	 *            The lines of the HTML file
	 * @param path
	 *            The path of the file
	 * @param documentationVersion
	 *            The version of the documentation
	 * @return The content of the file to save
	 */
	public List<String> getBody(List<String> lines, String path, String documentationVersion) {
		List<String> linesToKeep = new ArrayList<>();
		linesToKeep.add(ROOT_ELEMENT_START);

		boolean hasFoundStartBody = false;
		boolean hasFoundStopBody = false;
		for (String line : lines) {
			hasFoundStopBody = hasFoundStopBody || line.contains("</body>"); //$NON-NLS-1$
			if (hasFoundStartBody && !hasFoundStopBody) {
				linesToKeep.add(this.fixLinks(line, path, documentationVersion));
			}
			hasFoundStartBody = hasFoundStartBody || line.contains("<body>"); //$NON-NLS-1$
		}

		linesToKeep.add(ROOT_ELEMENT_END);
		return linesToKeep;
	}

	/**
	 * Fixes the links that can be found in the line.
	 *
	 * @param line
	 *            A line of the body of the HTML file
	 * @param path
	 *            The path of the file
	 * @param documentationVersion
	 *            The version of the documentation
	 * @return The line with its links fixed
	 */
	public String fixLinks(String line, String path, String documentationVersion) {
		StringBuilder builder = new StringBuilder();

		int startIndex = 0;
		int endIndex = 0;

		while (startIndex != -1 && endIndex != -1) {
			startIndex = this.getNextLinkStart(line, endIndex);
			if (startIndex != -1) {
				// Append the content before this link
				builder.append(line.substring(endIndex, startIndex));

				endIndex = this.getNextLinkEnd(line, startIndex + A_ELEMENT_START.length());
				if (endIndex != -1) {
					// We have found at least one link in the line
					String link = line.substring(startIndex + A_ELEMENT_START.length(), endIndex);

					// Let's convert it
					String convertedLink = this.convertLink(link, path, documentationVersion);

					// Now append it to the builder
					builder.append(A_ELEMENT_START);
					builder.append(convertedLink);
				} else {
					// Should not occur, the link is invalid
				}
			} else {
				// No more link on the line, append the rest
				builder.append(line.substring(endIndex));
			}
		}

		return builder.toString();
	}

	/**
	 * Returns the index of the start of the next link.
	 *
	 * @param line
	 *            The line
	 * @param fromIndex
	 *            The index from which the search should start
	 * @return The index of the start of the next link or -1 if none could be find
	 */
	private int getNextLinkStart(String line, int fromIndex) {
		return line.indexOf(A_ELEMENT_START, fromIndex);
	}

	/**
	 * Returns the index of the end of the next link.
	 *
	 * @param line
	 *            The line
	 * @param fromIndex
	 *            The index from which the search should start
	 * @return The index of the end of the next link or -1 if none could be find
	 */
	private int getNextLinkEnd(String line, int fromIndex) {
		return line.indexOf(A_HREF_ATTRIBUTE_END, fromIndex);
	}

	/**
	 * Trims the HTML extension of the path.
	 *
	 * @param path
	 *            The path
	 * @return The trimmed extension
	 */
	public String trimExtension(String path) {
		if (path.endsWith(HTML_EXTENSION)) {
			return path.substring(0, path.length() - HTML_EXTENSION.length());
		}
		return path;
	}

	/**
	 * Converts the link.
	 *
	 * @param link
	 *            The link
	 * @param path
	 *            The path of the HTML file containing the link
	 * @param documentationVersion
	 *            The version of the documentation
	 * @return The converted link
	 */
	public String convertLink(String link, String path, String documentationVersion) {
		String convertedLink = link;

		if (this.isRelative(link)) {
			// We have to resolve the link regarding to the current path, for that we will build an URL with the path
			// and resolve the link against it
			try {
				// Let's consider that path=/folder/subfolder/resource.html

				String prefix = "http://host.tld/"; //$NON-NLS-1$
				URL prefixedURL = new URL(new URL("http", "host.tld", "/" + this.trimExtension(path)), this.trimExtension(link)); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				String prefixedResolvedLink = prefixedURL.toString();

				// Now prefix=http://host.tld, prefixedResolvedLink=http://host.tld/folder/subfolder/resource
				String resolvedPath = prefixedResolvedLink.substring(prefix.length());
				convertedLink = ROOT_DOCUMENTATION_URL + '/' + documentationVersion + '/' + resolvedPath;
			} catch (MalformedURLException e) {
				fail(e.getMessage());
			}
		} else if (this.isAnchor(link)) {
			// The link must now be absolute
			convertedLink = ROOT_DOCUMENTATION_URL + '/' + documentationVersion + '/' + this.trimExtension(path) + link;
		}

		return convertedLink;
	}

	/**
	 * Indicates if a link is relative.
	 *
	 * @param link
	 *            The link
	 * @return <code>true</code> if the link is relative, <code>false</code> otherwise
	 */
	public boolean isRelative(String link) {
		return !link.contains(":/"); //$NON-NLS-1$
	}

	/**
	 * Indicates if a link is an anchor.
	 *
	 * @param link
	 *            The link
	 * @return <code>true</code> if the link is an anchor, <code>false</code> otherwise
	 */
	public boolean isAnchor(String link) {
		return link.startsWith("#"); //$NON-NLS-1$
	}

	/**
	 * Reads the lines of the file at the given URL.
	 *
	 * @param entry
	 *            The URL of the file
	 * @return The lines of the file
	 */
	private List<String> readLines(URL entry) {
		List<String> lines = new ArrayList<>();

		InputStreamReader inputStreamReader = null;
		try {
			InputStream openStream = entry.openStream();
			inputStreamReader = new InputStreamReader(openStream, Charsets.UTF_8);
			lines.addAll(CharStreams.readLines(inputStreamReader));
		} catch (IOException e) {
			fail(e.getMessage());
		} finally {
			try {
				Closeables.close(inputStreamReader, false);
			} catch (IOException e) {
				fail(e.getMessage());
			}
		}
		return lines;
	}

	/**
	 * Writes the lines of the file at the given path.
	 *
	 * @param lines
	 *            The lines to write
	 * @param path
	 *            The path of the file
	 * @param documentationVersion
	 *            The version of the documentation (used to compute the full path where the file will be written)
	 */
	private void writeLines(List<String> lines, String path, String documentationVersion) {
		Path file = this.getOutputPath(documentationVersion, path);
		StringBuilder builder = new StringBuilder();
		lines.forEach(line -> builder.append(line + System.lineSeparator()));
		try {
			file.toFile().getParentFile().mkdirs();
			Files.write(builder.toString(), file.toFile(), Charset.forName("UTF-8")); //$NON-NLS-1$
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	/**
	 * Computes the output path for the given input path. The path of the ouput will be located in the folder "target"
	 * of this plugin under a folder named at the given documentation version. If the path of the input is
	 * "/folder/subfolder/file.html" then the path of the ouput will be
	 * "LOCATION_OF_THIS_PLUGIN/target/DOCUMENTATION_VERSION/folder/subfolder/file.html".
	 *
	 * @param documentationVersion
	 *            The version of the documentation
	 * @param inputPath
	 *            The path of the input
	 * @return The path of the output
	 */
	private Path getOutputPath(String documentationVersion, String inputPath) {
		String userDir = System.getProperty("user.dir"); //$NON-NLS-1$
		Path userDirPath = Paths.get(userDir);
		return userDirPath.resolve("target").resolve(documentationVersion).resolve(inputPath); //$NON-NLS-1$
	}
}
