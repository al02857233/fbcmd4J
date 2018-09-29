package org.fbcmd4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.fbcmd4j.utils.Utils;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.Post;
import facebook4j.ResponseList;

public class Main {
	static final Logger logger = LogManager.getLogger(Main.class);
	private static final String CONFIG_DIR = "config";
	private static final String CONFIG_FILE = "fbcmd4j.properties";

	public static void main(String[] args) {
		logger.info("Iniciando Facebook Command Line Client");
		Facebook fb =  null;
		Properties props = null;

		try {
			props = Utils.loadConfigFile(CONFIG_DIR, CONFIG_FILE);
		} catch (IOException ex) {
			logger.error(ex);
		}
		
		int option = 1;
		try {
			Scanner scan = new Scanner(System.in);
			while(true) {
				fb = Utils.configFacebook(props);
				System.out.println("Facebook Command Line Client \n\n"
								+  "Opciones: \n"
								+  "(0) Configurar Cliente \n"
								+  "(1) Obtener NewsFeed \n"
								+  "(2) Obtener Wall \n"
								+  "(3) Publicar Estado \n"
								+  "(4) Publicar Link \n"
								+  "(5) Salir \n"
								+  "\nSeleccione una opcion:");
				try {
					option = scan.nextInt();
					scan.nextLine();
					switch (option) {
					case 0:
						Utils.configTokens(CONFIG_DIR, CONFIG_FILE, props, scan);
						props = Utils.loadConfigFile(CONFIG_DIR, CONFIG_FILE);
						break;
					case 1:
						System.out.println("Obteniendo NewsFeed...");
						break;
					case 2:
						System.out.println("Obteniendo Wall...");
						break;
					case 3:
						System.out.println("Introduzca el estado que quiere publicar: ");
						break;
					case 4:
						System.out.println("Introduzca el link que quiere publicar: ");
						break;
					case 5:
						System.out.println("Saliendo de Facebook Command Line Client");
						System.exit(0);
						break;
					default:
						break;
					}
				} catch (InputMismatchException ex) {
					System.out.println("ERROR, REVISE EL LOG");
					logger.error("OPCION NO VALIDA \n", ex.getClass());
				}  catch (Exception ex) {
					System.out.println("ERROR, REVISE EL LOG");
					logger.error(ex);
				}
				System.out.println();
			}
		} catch (Exception e) {
			logger.error(e);
		}
	}
	
	
}