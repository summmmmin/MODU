package com.modu.app.common;

public class FileUtill {

	// 파일 확장자 추출 메소드 (동일한 코드를 FileController와 PostController에서 사용)
	public static String getFileExtension(String filename) {
		if (filename == null)
			return null;
		int extensionIndex = filename.lastIndexOf(".");
		return (extensionIndex == -1) ? null : filename.substring(extensionIndex + 1).toLowerCase();
	}
}
