// Primer der "Pipe" zur Primzahlenaussiebung
public class FastPipe extends Thread {
	private FastPipe next; // der nächste Primer in der "Pipe"

	FastPipe(int prime) { // Konstuktor
		super("Number-" + prime); // Name eintragen
		this.start(); // Thread sofort starten
	}

	// ... weitere Variablen und Methoden ...
	public static void main(String args[]) {
		FastPipe first = new FastPipe(1); // ersten Primer : 2
		for (int i = 2; i <= 1000; i++) {
			first.send(i);
		}
		; // weitere

		first.send(0); // Abbruchmitteilung

		System.out.println(currentThread() + " main beendet");
	}

	@Override
	public void run() { // Die Arbeitsmethode des Primers
		// ... ist nicht synchronisiert
		Thread thread = Thread.currentThread();
		System.out.println(thread + "create");
		while (true) { // Endlos-Schleife
			int n = receive(); // Lese-Versuch
			if (n == 0) {// wenn n=0: Ende
				if (next != null) {
					next.send(n);
				} // auch von next
				System.out.println(thread + "close");
				break; // Ende whileloop
			}
			if (n != 0) { // vielleicht prim
				if (next != null)
					next.send(n);// weiter testen
				else
					next = new FastPipe(n); // Primzahl!
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