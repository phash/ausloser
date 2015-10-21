package de.germanwarfare.stats;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

import de.germanwarfare.stats.entity.GameOverReason;
import de.germanwarfare.stats.entity.Level;
import de.germanwarfare.stats.entity.Match;
import de.germanwarfare.stats.entity.Player;
import de.germanwarfare.stats.entity.PlayerMatch;
import de.germanwarfare.stats.entity.Tier;

public class DataPublisher {

	LevelService levelService = new LevelService();
	GameOverReasonService gameOverReasonService = new GameOverReasonService();
	VehicleService vehicleService = new VehicleService();

	File file;
	int pvp = 0, pve = 0, win = 0, draw = 0, loss = 0;
	Label pvpMatches = new Label();
	Label pveMatches = new Label();
	Label siege = new Label();
	Label winrate = new Label();

	VerticalLayout layout = new VerticalLayout();

	public Panel getDataForUpload(File pFile) throws ParserConfigurationException, SAXException, IOException {
		file = pFile;
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setMargin(true);
		hlayout.addComponent(pvpMatches);
		hlayout.addComponent(pveMatches);
		hlayout.addComponent(siege);
		hlayout.addComponent(winrate);

		layout.addComponent(hlayout);
		Panel panel = new Panel();
		panel.setContent(layout);
		// boolean first = true;
		boolean spielerZeilen = false;
		ArrayList<Match> matchlist = new ArrayList<>();
		Match match = null;
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {

				if (line.contains("matchtype=")) {
					match = createMatchSetup(line);
					countThings(line);
					continue;
				}
				// Die Spieler

				if (line.contains("<All>")) {
					spielerZeilen = true;
				}
				if (line.contains("</All>")) {
					spielerZeilen = false;
					matchlist.add(match);
				}
				if (spielerZeilen) {
					if (line.trim().startsWith("<_")) {
						HorizontalLayout playerLayout = new HorizontalLayout();
						layout.addComponent(playerLayout);
						Player einSpieler = new Player();
						PlayerMatch playerMatch = new PlayerMatch(einSpieler);
						String[] contents = line.trim().split(" ");
						for (String string : contents) {
							if (string.startsWith("<_")) {
								String removed = string.replaceAll("<_", "");

								einSpieler.setGivenid(Integer.valueOf(removed));
								Label l = new Label("" + einSpieler.getGivenid());
								l.setCaption("ID");
								playerLayout.addComponent(l);
							}
							if (string.startsWith("playername")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										einSpieler.setName("unbekannter");
									} else {
										einSpieler.setName(zelle[1]);
									}
									Label l = new Label(einSpieler.getName());
									l.setCaption("Name:");
									playerLayout.addComponent(l);
								}
							}

							if (string.startsWith("damagedealt")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setDamagedealt(0);
									} else {
										playerMatch.setDamagedealt(Integer.valueOf(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getDamagedealt());
									l.setCaption("Damage:");
									playerLayout.addComponent(l);
								}
							}
							if (string.startsWith("killsenemies")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setKillsenemies(0);
									} else {
										playerMatch.setKillsenemies(Integer.valueOf(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getDamagedealt());
									l.setCaption("Kills:");
									playerLayout.addComponent(l);
								}
							}
							if (string.startsWith("spottedenemies")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setSpottedenemies(0);
									} else {
										playerMatch.setDamagedealt(Integer.valueOf(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getSpottedenemies());
									l.setCaption("Spotted Enemies:");
									playerLayout.addComponent(l);
								}
							}
							if (string.startsWith("spottingdamage")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setSpottingdamage(0);
									} else {
										playerMatch.setSpottingdamage(Integer.valueOf(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getSpottingdamage());
									l.setCaption("Spttin:");
									playerLayout.addComponent(l);
								}
							}
							if (string.startsWith("teamid")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setTeamid(0);
									} else {
										playerMatch.setTeamid(Integer.valueOf(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getTeamid());
									l.setCaption("Team:");
									playerLayout.addComponent(l);
								}
							}
							if (string.startsWith("assists")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setAssists(0);
									} else {
										playerMatch.setAssists(Integer.valueOf(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getAssists());
									l.setCaption("Assists:");
									playerLayout.addComponent(l);
								}
							}
							if (string.startsWith("xpearned")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setXpearned(0);
									} else {
										playerMatch.setXpearned(Integer.valueOf(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getXpearned());
									l.setCaption("XP:");
									playerLayout.addComponent(l);
								}
							}
							if (string.startsWith("vehiclename")) {

								String removed = string.replaceAll("\"", "");
								String[] zelle = removed.split("=");
								if (zelle.length > 1) {
									if (zelle[1].isEmpty() || zelle[1] == null || zelle[1].equals("")
											|| zelle[1].equals(" ")) {
										playerMatch.setVehicle(vehicleService.getVehicleByName("unbekanntes Fahrzeug"));

									} else {
										playerMatch.setVehicle(vehicleService.getVehicleByName(zelle[1]));
									}
									Label l = new Label("" + playerMatch.getAssists());
									l.setCaption("Vehicle:");
									playerLayout.addComponent(l);
								}
							}

						}
					}

				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		updateCounters();
		return panel;

	}

	private Match createMatchSetup(String line) {
		HorizontalLayout hlayout = new HorizontalLayout();
		hlayout.setMargin(true);
		String[] contents = line.trim().split(" ");
		String id = contents[0].replace("<", "");
		Match match = new Match(id);
		hlayout.addComponent(new Label(match.getMatchId()));
		for (String string : contents) {
			if (string.startsWith("levelname")) {
				Level playedLevel = levelService
						.getLevelByName(string.substring(11, string.length() - 1).toLowerCase());
				hlayout.addComponent(new Label(playedLevel.getName()));
				match.setLevel(playedLevel);
			}
			if (string.startsWith("gameoverreason")) {
				GameOverReason playedGameover = gameOverReasonService
						.getReasonByName(string.substring(16, string.length() - 1).toLowerCase());
				hlayout.addComponent(new Label(playedGameover.getReason()));
				match.setGameOverReason(playedGameover);
			}
			if (string.startsWith("matchtier")) {
				Tier playedtier = Tier.getTierForInt(string.substring(11, string.length() - 1).toLowerCase());
				hlayout.addComponent(new Label(playedtier.name()));
				match.setMatchTier(playedtier);
			}
			// TODO - FIXME zeit is noch falsch
			if (string.startsWith("timestamp")) {
				Date timestamp = new Date(
						(Integer.valueOf(string.substring(11, string.length() - 1).toLowerCase())) * 1000);
				hlayout.addComponent(new Label(timestamp.toLocaleString()));
				match.setTimestamp(timestamp);
			}
			if (string.startsWith("duration")) {
				// das ende mit > ist hier mit dran -könnte man
				// abschneiden...
				int duration = Integer.valueOf(string.substring(10, string.length() - 2).toLowerCase());
				hlayout.addComponent(new Label("" + duration));
				match.setDuration(duration);
			}
			layout.addComponent(hlayout);
		}
		return match;
	}

	private void updateCounters() {
		pvpMatches.setCaption("Spiele PvP: ");
		pvpMatches.setValue(Integer.toString(pvp));
		pveMatches.setCaption("Spiele PvE: ");
		pveMatches.setValue(Integer.toString(pve));
		siege.setCaption("Siege PvP: ");
		siege.setValue(Integer.toString(win));
		winrate.setCaption("winrate PvP: ");
		Double erg = (double) win / (double) pvp * 100;
		DecimalFormat f = new DecimalFormat("##.000");
		winrate.setValue(f.format(erg));
	}

	private void countThings(String line) {
		if (line.contains("matchtype=\"pvp\"")) {
			pvp++;
		} else {
			pve++;
		}
		if (line.contains("wincondition=\"0\"")) {
			win++;
		} else if (line.contains("wincondition=\"2\"")) {
			loss++;
		}
		if (line.contains("wincondition=\"1\"")) {
			draw++;
		}
	}
}
