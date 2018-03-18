package com.ramtin;

import java.util.HashMap;
import java.util.Map;

import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;

public class Audio {

	/** FIELDS **/

	public static Map<String, Sound> soundMap = new HashMap<String, Sound>();
	public static Map<String, Music> musicMap = new HashMap<String, Music>();

	/** LOAD **/
	public static void load() {
		// STATIC -> BECAUSE ONLY INITIALISED ONCE
		// LOAD IN SOUNDS

		try {
			soundMap.put("menu_sound", new Sound("Resources/Click2-Sebastian-759472264.wav"));
			musicMap.put("music1", new Music("Resources/Psychedelic-trip-electronic-music-beat-126-bpm.wav"));
			musicMap.put("music2", new Music("Resources/Quirky-Rhythm-6.wav"));
			musicMap.put("music3", new Music("Resources/Terra-incognita-instrumental-background-music.wav"));
			musicMap.put("music4", new Music("Resources/Trap-music-instrumental.wav"));
			musicMap.put("music5", new Music("Resources/Exclusion-Unity.wav"));
		} catch (SlickException e) {
			e.printStackTrace();
		}

	}

	/** GETTER **/
	public static Music getMusic(String key) {
		return musicMap.get(key);
	}

	public static Sound getSound(String key) {
		return soundMap.get(key);
	}

}
