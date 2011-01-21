package org.dita.dost.exception;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.xml.sax.Locator;
import org.xml.sax.SAXParseException;

public class SAXExceptionWrapperTest {

	private final Locator l = new Locator() {
		@Override
		public int getColumnNumber() {
			return 1;
		}
		@Override
		public int getLineNumber() {
			return 3;
		}
		@Override
		public String getPublicId() {
			return "publicId";
		}
		@Override
		public String getSystemId() {
			return "systemId";
		}
	};

	@Test
	public void testSAXExceptionWrapperStringLocator() {
		new SAXExceptionWrapper("message", l);
	}

	@Test
	public void testSAXExceptionWrapperStringLocatorException() {
		new SAXExceptionWrapper("message", l, new RuntimeException("msg"));
	}

	@Test
	public void testSAXExceptionWrapperStringStringStringIntInt() {
		new SAXExceptionWrapper("message", "publicId", "systemId", 3, 1);
	}

	@Test
	public void testSAXExceptionWrapperStringStringStringIntIntException() {
		new SAXExceptionWrapper("message", "publicId", "systemId", 3, 1, new RuntimeException("msg"));
	}

	@Test
	public void testSAXExceptionWrapperStringSAXParseException() {
		new SAXExceptionWrapper("message", new SAXParseException("msg", l));
	}

	@Test
	public void testGetMessage() {
		final SAXExceptionWrapper e = new SAXExceptionWrapper("message", new SAXParseException("msg", l));
		final String act = e.getMessage();
		final String exp = new StringBuilder()
			.append("message")
			.append(" Line ")
			.append(l.getLineNumber())
			.append(":")
			.append("msg")
			.append(System.getProperty("line.separator")).toString();
		assertEquals(exp, act);
	}

}