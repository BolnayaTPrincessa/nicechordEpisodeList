package com.nicechord.wiki;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class EpisodeList implements Serializable {
	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<String> videoCode = new ArrayList<String>();
	
	/**
	 * Gets the list of titles.
	 * @return List of titles, arranged in chronological order.
	 */
	public ArrayList<String> getTitle() {
		return title;
	}
	/**
	 * Gets the title of the specific episode, counting from 0.
	 * @param i episode number.
	 * @return the title of the specific episode, counting from 0.
	 */
	public String getTitle(int i) {
		return title.get(i);
	}
	/**
	 * Gets the list of video codes.
	 * @return List of video codes, arranged in chronological order.
	 */
	public ArrayList<String> getVideoCode() {
		return videoCode;
	}
	/**
	 * Gets the video code of the specific episode, counting from 0.
	 * @param i episode number.
	 * @return the video code of the specific episode, counting from 0.
	 */
	public String getVideoCode(int i) {
		return videoCode.get(i);
	}
	/**
	 * Convert the EpisodeList object back into plain text raw data. 
	 */
	@Override
	public String toString() {
		String a = "";
		for (int i = 0; i < title.size(); i++) {
			a = a.concat((i + 1) + "\t" + title.get(i) + "\t" + videoCode.get(i) + "\n");
		}
		return a;
	}
	/**
	 * Same as toString(), except without video codes.
	 * @return Episode number, together with title.
	 */
	public String outputTitle() {
		String a = "";
		for (int i = 0; i < title.size(); i++) {
			a = a.concat((i + 1) + "\t" + title.get(i) + "\n");
		}
		return a;
	}
	/**
	 * Same as toString(), except without titles.
	 * @return Episode number, together with video code.
	 */
	public String outputVideoCode() {
		String a = "";
		for (int i = 0; i < title.size(); i++) {
			a = a.concat((i + 1) + "\t" + videoCode.get(i) + "\n");
		}
		return a;
	}
	/**
	 * Read a serialized object of the episode list.
	 * @return
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static EpisodeList load() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream is = new ObjectInputStream(new FileInputStream("nicechord-episode-list-java"));
		EpisodeList list = (EpisodeList) is.readObject();
		is.close();
		return list;
	}
	/**
	 * Read a text file and convert it into an EpisodeList object.
	 * @return
	 * @throws IOException
	 */
	public static EpisodeList parse() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("nicechord-episode-list.txt"), "UTF-8"));
		EpisodeList list = new EpisodeList();
		String line = "";
		while (line != null) {
			line = br.readLine();
			if (line == null) break; // EOF
			if (line.isEmpty()) continue; // Ignore empty lines
			if (line.startsWith(";")) continue; // Ignore lines starting with ";"
			String[] a = line.split("\t");
			list.title.add(a[1]);
			list.videoCode.add(a[2]);
		}
		br.close();
		return list;
	}
	/**
	 * Read a title list from a text file and add it to an existing list.
	 * @throws IOException
	 */
	public void parseTitle() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("title-only.txt"), "UTF-8"));
		videoCode = new ArrayList<String>(); // Clear existing title list
		String line = "";
		while (line != null) {
			line = br.readLine();
			if (line == null) break; // EOF
			if (line.isEmpty()) continue; // Ignore empty lines
			if (line.startsWith(";")) continue; // Ignore lines starting with ";"
			String[] a = line.split("\t");
			title.add(a[1]);
		}
		br.close();
	}
	/**
	 * Read a video code list from a text file and add it to an existing list.
	 * @throws IOException
	 */
	public void parseVideoCode() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("video-code-only.txt"), "UTF-8"));
		title = new ArrayList<String>(); // Clear existing video code list
		String line = "";
		while (line != null) {
			line = br.readLine();
			if (line == null) break; // EOF
			if (line.isEmpty()) continue; // Ignore empty lines
			if (line.startsWith(";")) continue; // Ignore lines starting with ";"
			String[] a = line.split("\t");
			videoCode.add(a[1]);
		}
		br.close();
	}
	/**
	 * Export the episode list as a serialized object.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void save() throws FileNotFoundException, IOException {
		ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("nicechord-episode-list-java"));
		os.writeObject(this);
		os.close();
	}
}
