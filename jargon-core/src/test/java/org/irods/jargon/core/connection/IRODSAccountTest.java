package org.irods.jargon.core.connection;

import org.junit.Test;

public final class IRODSAccountTest {

	@Test
	public final void testToURIWithoutProxy() throws Exception {
		final IRODSAccount proxy = IRODSAccount.instance("localhost", 1247,
				"client", "password", "/zone/home/client", "zone", "");
		proxy.toURI(true);
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testToURIWithProxySameZone() throws Exception {
		final IRODSAccount proxy = IRODSAccount.instanceWithProxy("localhost",
				1247, "client", "proxyPassword", "/zone/home/client", "zone",
				"", "proxy", "zone");
		proxy.toURI(true);
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testToURIWithProxySameNameDifferentZone()
			throws Exception {
		final IRODSAccount proxy = IRODSAccount.instanceWithProxy("localhost",
				1247, "user", "proxyPassword",
				"/proxyZone/home/user#clientZone", "clientZone", "", "user",
				"proxyZone");
		proxy.toURI(true);
	}

	/**
	 * Tests bug https://github.com/DICE-UNC/jargon/issues/48 irods account with
	 * home path may cause URI formatting errors #48
	 *
	 * @throws Exception
	 */
	@Test
	public final void testToUriWithHomePathBug48() throws Exception {
		final IRODSAccount account = IRODSAccount.instance("localhost", 1247,
				"client", "password", "/zone/home/client", "zone", "");
		account.toURI(true);
	}

}
