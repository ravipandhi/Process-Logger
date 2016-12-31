package processLogger.dal;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

class DataAccessController {

	public static DatagramSocket clientsocket;
	public static DatagramPacket dp, dp2;

	public static InetAddress ia;
	public static byte buf[] = new byte[1024];
	public static byte buf1[] = new byte[1024];

	public static int cport = 6033;
	public static String TIMESTAMP = null;

	public static void main(String[] a) throws IOException {
		clientsocket = new DatagramSocket(cport);
		dp = new DatagramPacket(buf, buf.length);

		dp2 = new DatagramPacket(buf1, buf1.length);

		String ID = null;
		DataAccessObject handle;
		double size;

		while (true) {

			try {
				System.out.println("Waiting for dp packets");
				clientsocket.receive(dp);

				String striin = new String(dp.getData(), 0, dp.getLength());

				if (striin.equals("STOP")) {

					clientsocket.close();
					System.out.println("Database Server will be closed");
					System.exit(0);

				}

				size = Double.parseDouble(striin);

				handle = new DataAccessObject(size);

				Inner: while (true) {
					try {
						String stri[] = new String[4];
						String flag = "abd";
						int i = 0;
						System.out.println("Waiting for dp2 packets");
						clientsocket.receive(dp2);

						String recv = new String(dp2.getData(), 0, dp2.getLength());

						stri[1] = stri[0] = stri[2] = stri[3] = "abc";
						StringTokenizer st = new StringTokenizer(recv, "=;");

						while (st.hasMoreTokens()) {

							if (i == 4) {
								flag = st.nextToken();
								ID = stri[0];
							} else {

								stri[i] = st.nextToken();

							}

							i++;

						}

						if (flag.equals("stop") || stri[0].equals("stop")) {

							System.out.println("Stop logging command received, Log file will be created");
							handle.BackupDb(TIMESTAMP, ID);
							break;

						}

						if (stri[1].contentEquals("abc") || stri[1] == "abc") {
							System.out.println(
									"Some packet must have been lost, the logging process will continue neverthless");
							if (stri[0].equals("STOP")) {

								handle.BackupDb(TIMESTAMP, ID);
								System.out.println("Database Server will be closed");
								System.exit(0);
							} else {
								handle.BackupDb(TIMESTAMP, ID);
								size = Double.parseDouble(stri[0]);
								handle = new DataAccessObject(size);
								continue Inner;
							}
						}

						TIMESTAMP = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
						handle.InsertIntoLog(stri);

					} catch (Exception e) {
						System.out.println("In inner catch");
						e.printStackTrace();
					}
				}
			}

			catch (Exception e) {

				e.printStackTrace();
			}
		}

	}
}
