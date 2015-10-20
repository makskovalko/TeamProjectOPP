package com.rhcloud.msdm.conference.utils.exeptions;


import java.io.IOException;

public class BufferDirIsNotDirectoryException extends IOException {

    public BufferDirIsNotDirectoryException(String msg){super(msg);}

    public BufferDirIsNotDirectoryException(){}
}
