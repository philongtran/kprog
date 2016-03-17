// Primer der "Pipe" zur Primzahlenaussiebung
public class FuenfSpaltenPrimzahlen extends Thread {
	private int p; // die Primzahl dieses Primers
	private FuenfSpaltenPrimzahlen next; // der nächste Primer in der "Pipe"
	private int id;

	FuenfSpaltenPrimzahlen(int prime, int id) { // Konstuktor
		super("Primer-" + prime); // Name eintragen
		p = prime; // Primzahl eintragen
		this.id = id;
		this.start(); // Thread sofort starten
	}

	// ... weitere Variablen und Methoden ...
	public static void main(String args[]) {
		FuenfSpaltenPrimzahlen first = new FuenfSpaltenPrimzahlen(2, 0); // ersten
																			// Primer
																			// :
																			// 2
		FuenfSpaltenPrimzahlen second = new FuenfSpaltenPrimzahlen(2, 1); // ersten
																			// Primer
																			// :
																			// 2
		FuenfSpaltenPrimzahlen third = new FuenfSpaltenPrimzahlen(2, 2); // ersten
																			// Primer
																			// :
																			// 2
		FuenfSpaltenPrimzahlen fourth = new FuenfSpaltenPrimzahlen(2, 3); // ersten
																			// Primer
																			// :
																			// 2
		FuenfSpaltenPrimzahlen five = new FuenfSpaltenPrimzahlen(2, 4); // ersten
																		// Primer
																		// : 2
		for (int i = 3; i <= 5000; i++) {
			first.send(i);
			second.send(i);
			third.send(i);
			fourth.send(i);
			five.send(i);
		}
		; // weitere

		first.send(0); // Abbruchmitteilung
		second.send(0);
		third.send(0);
		fourth.send(0);
		five.send(0);
		System.out.println(currentThread() + " main beendet");
	}

	@Override
	public void run() { // Die Arbeitsmethode des Primers
		// ... ist nicht synchronisiert
		int i = id;
		String result = "";
		while (i > 0) {
			result += "      ";
			i--;
		}

		System.out.println(result += p);
		while (true) { // Endlos-Schleife
			int n = receive(); // Lese-Versuch
			if (n == 0) {// wenn n=0: Ende
				if (next != null)
					next.send(n);// auch von next
				break; // Ende whileloop
			}
			if (n % p != 0) { // vielleicht prim
				if (next != null)
					next.send(n);// weiter testen
				else
					next = new FuenfSpaltenPrimzahlen(n, id); // Primzahl!
			} // sonst: n nicht prim
		}
	}

	private int buffer = -1; // Puffer zum Senden & Empfangen
	// wenn < 0: leer

	synchronized void send(int i) { // Sperre erlangen
		try {
			while (buffer >= 0)
				wait();// warten bis Puffer frei
			buffer = i; // Puffer füllen
			notify(); // Empfänger benachrichtigen
		} catch (InterruptedException e) {
		}
	}

	synchronized int receive() { // Sperre erlangen
		int result = 0;
		try {
			while ((result = buffer) < 0)
				wait();// warten bis Puffer
			// voll
			buffer = -1; // Puffer leeren
			notify(); // Sender
			// benachrichtigen
		} catch (InterruptedException e) {
		}
		return result;
	}
}