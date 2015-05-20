package com.example.guessmusic.modal;

public class Song {

	private String mSongName;
	private int mNameLength;
	private String mSongFileName;

	public char[] getNameCharacters(){
		return mSongName.toCharArray();
	}
	public String getSongName() {
		return mSongName;
	}

	public void setSongName(String songName) {
		mSongName = songName;
		this.mNameLength = mSongName.length();
	}

	public int getNameLength() {
		return mNameLength;
	}

	public void setNameLength(int nameLength) {
		mNameLength = nameLength;
	}

	public String getSongFileName() {
		return mSongFileName;
	}

	public void setSongFileName(String songFileName) {
		mSongFileName = songFileName;
	}

}
