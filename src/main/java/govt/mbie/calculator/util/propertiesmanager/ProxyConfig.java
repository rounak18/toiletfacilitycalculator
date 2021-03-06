package govt.mbie.calculator.util.propertiesmanager;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import govt.mbie.calculator.util.loghelper.LoggerHelper;




public class ProxyConfig {

	private static Logger log = LoggerHelper.getLogger(ProxyConfig.class);

	private boolean proxyIsRequired;
	private String proxyType;
	private String proxyHost;
	private int proxyPort = 0;
	private String proxyUsername = "";
	private String proxyPassword = "";
	private String nonProxyHosts = "";

	ProxyConfig(PropertyLoader propertyLoader) {
		proxyIsRequired = propertyLoader.getPropertyAsBoolean("proxy.required", null);

		setProxyFromConfigFile(propertyLoader);
		setProxyFromSystemProperties();
		setProxyFromEnvironmentVariables();

		if (proxyIsRequired && proxyHost.isEmpty()) {
			throw new IllegalArgumentException("proxy.host has not been set when proxy.required is set to true");
		}
	}

	/**
	 * Whether a proxy should be configured for accessing the test application or
	 * not, regardless of means of accessing the test application, e.g. web browser
	 * or api request.
	 *
	 * @return true or false
	 */
	public boolean isProxyRequired() {
		return proxyIsRequired;
	}

	/**
	 * @return The type of Proxy to use for a browser:
	 *         <ul>
	 *         <li>DIRECT: Direct connection, no proxy (default on Windows)</li>
	 *         <li>MANUAL: Manual proxy settings (e.g. for httpProxy). Will default
	 *         to this if proxy.host is set</li>
	 *         <li>PAC: Proxy auto-configuration from URL</li>
	 *         <li>AUTODETECT: Proxy auto-detection (presumably with WPAD)</li>
	 *         <li>SYSTEM: Use system settings (default on Linux)</li>
	 *         </ul>
	 */
	public String getProxyType() {
		return proxyType;
	}

	/**
	 * @return The hostname, or address, of the proxy server.
	 */
	public String getProxyHost() {
		return proxyHost;
	}

	/**
	 * @return The port number of the proxy server.
	 */
	public int getProxyPort() {
		return proxyPort;
	}

	/**
	 * @return The hostname and port of the proxy server in the format host:port.
	 */
	public String getProxyAddress() {
		if (proxyHost.isEmpty()) {
			return "";
		}

		if (proxyPort == 0 || proxyPort == 80) {
			return proxyHost;
		}

		return proxyHost + ":" + String.valueOf(proxyPort);
	}

	/**
	 * @return Username to authenticate connections through the proxy server.
	 */
	public String getProxyUsername() {
		return proxyUsername;
	}

	/**
	 * @return Password to authenticate connections through the proxy server.
	 */
	public String getProxyPassword() {
		return proxyPassword;
	}

	/**
	 * Indicates the hosts that should be accessed without going through the proxy.
	 * Typically this defines internal hosts. The value of this property is a list
	 * of hosts, separated by the '|' character. In addition the wildcard character
	 * '*' can be used for pattern matching.
	 *
	 * <p>
	 * For example: proxy.nonProxyHosts=*.foo.com,localhost will indicate that every
	 * hosts in the foo.com domain and the localhost should be accessed directly
	 * even if a proxy server is specified.
	 * </p>
	 *
	 * @return The hosts that should be accessed without going through the proxy
	 */
	public String getNonProxyHosts() {
		return nonProxyHosts;
	}

	private void setProxyFromConfigFile(PropertyLoader propertyLoader) {
		proxyType = propertyLoader.getProperty("proxy.type", "");
		proxyHost = propertyLoader.getProperty("proxy.host", "");

		if (proxyHost.isEmpty()) {
			return;
		}

		log.debug("Loading Proxy settings from configuration file(s)");

		proxyPort = propertyLoader.getPropertyAsInteger("proxy.port", "80");
		proxyUsername = propertyLoader.getProperty("proxy.username", "");
		proxyPassword = propertyLoader.getProperty("proxy.password", "");
		nonProxyHosts = propertyLoader.getProperty("proxy.nonProxyHosts", "");
	}

	private void setProxyFromSystemProperties() {
		if (!proxyHost.isEmpty()) {
			return;
		}

		proxyHost = System.getProperty("http.proxyHost", "");

		if (proxyHost.isEmpty()) {
			return;
		}

		log.debug("Loading Proxy settings from http.proxy... system properties");

		proxyPort = Integer.valueOf(System.getProperty("http.proxyPort", "80"));
		proxyUsername = System.getProperty("http.proxyUser", "");
		proxyPassword = System.getProperty("http.proxyPassword", "");
		nonProxyHosts = System.getProperty("http.nonProxyHosts", "").replaceAll("\\|", ",");
	}

	private void setProxyFromEnvironmentVariables() {
		if (!proxyHost.isEmpty()) {
			return;
		}

		URL proxyUrl = getProxyUrl();

		if (proxyUrl == null) {
			proxyHost = "";
		} else {
			log.debug("Loading Proxy settings from HTTP_PROXY environment variable(s)");

			proxyHost = proxyUrl.getHost();
			proxyPort = proxyUrl.getPort();

			String userInfo = proxyUrl.getUserInfo();

			if (userInfo != null) {
				StringTokenizer st = new StringTokenizer(userInfo, ":");

				try {
					proxyUsername = st.hasMoreTokens()
							? URLDecoder.decode(st.nextToken(), StandardCharsets.UTF_8.name())
							: null;
					proxyPassword = st.hasMoreTokens()
							? URLDecoder.decode(st.nextToken(), StandardCharsets.UTF_8.name())
							: null;
				} catch (UnsupportedEncodingException e) {
					// TODO log this
					// do nothing
				}
			}

			if (proxyUsername == null || proxyUsername.isEmpty()) {
				proxyUsername = System.getenv("HTTP_PROXY_USER");
			}

			if (proxyPassword == null || proxyPassword.isEmpty()) {
				proxyPassword = System.getenv("HTTP_PROXY_PASS");
			}

			nonProxyHosts = System.getenv("NO_PROXY");
		}
	}

	/**
	 * Environment variable HTTP_PROXY should be set like:
	 * http://[username]:[password]@[proxyHost]:[proxyPort]
	 * 
	 * e.g. http://myusername:mypassword@corporateproxy.com:8080
	 * 
	 * @return represents a Uniform Resource Locator
	 */
	private URL getProxyUrl() {
		String proxyInput = System.getenv("HTTP_PROXY");

		try {
			if (proxyInput != null) {
				return new URL(proxyInput.matches("^http[s]?://.*$") ? proxyInput : "http://" + proxyInput);
			}
		} catch (MalformedURLException e) {
			log.warn("Invalid proxy url {} in HTTP_PROXY environment variable");
		}

		return null;
	}

}
