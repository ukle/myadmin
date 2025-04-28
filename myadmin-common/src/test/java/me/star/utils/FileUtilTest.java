
    @Test
    public void testGetExtensionName() {
        assertEquals("foo", getExtensionName("foo"));
        assertEquals("exe", getExtensionName("bar.exe"));
    }

    @Test
    public void testGetFileNameNoEx() {
        assertEquals("foo", getFileNameNoEx("foo"));
        assertEquals("bar", getFileNameNoEx("bar.txt"));
    }

    @Test
    public void testGetSize() {
        assertEquals("1000B   ", getSize(1000));
        assertEquals("1.00KB   ", getSize(1024));
        assertEquals("1.00MB   ", getSize(1048576));
        assertEquals("1.00GB   ", getSize(1073741824));
    }
}
