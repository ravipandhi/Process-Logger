package processLogger.lli;

import java.io.*;
import java.net.*;
import java.util.StringTokenizer;



public class AppController implements Serializable {

    private static DatagramSocket serversocket;
    private static DatagramPacket dp;
    private static DatagramPacket dp1;

    private static InetAddress ia1, ia2;

    private static byte buf[] = new byte[1024];
    private static byte buf1[] = new byte[1024];
    private static byte buf2[] = new byte[1024];
    private static byte buf3[] = new byte[1024];
    private static byte buf4[] = new byte[1024];

    private static final int cport = 6003, sport = 6033;
    private static final int sportg = 6002;// cportg = 6003;

    private static String DATA = new String();

    public static void main(String[] a) throws IOException {

    	
    	//commented by ravi
    	
        try {
            serversocket = new DatagramSocket(cport);
        } catch (SocketException e) {
            //e.printStackTrace();
            System.out.println(e);
        }

        dp = new DatagramPacket(buf, buf.length);
        dp1 = new DatagramPacket(buf1, buf1.length);

        
        //commented by ravi 
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter PC Name of Database Machine: ");
        String db = read.readLine();
        System.out.println("Enter PC Name of GUI Machine: ");
        String gui = read.readLine();

        
        
        
        
        //byte IP2[] = new byte[]{(byte) 25, (byte)31, (byte) 158, (byte) 103};    //enter ip address of graph machine
        //byte IP[] = new byte[]{(byte) 25, (byte) 96, (byte) 114, (byte) 4};     //ip of database
        //ia1 = InetAddress.getByAddress(IP);   //for ip address of ravi(db)
        
        
        
        
        
        //commented by ravi 
        
        ia1 = InetAddress.getByName(db);       //for pc name of ravi (db)
        ia2 = InetAddress.getByName(gui);    //for pc name (gui)
         
        
        
        
        
        
        //System.out.println("Server is Running...");
        int f1 = 0, f2;
        boolean flag = true;
        while (true) {

            try {
                f2 = 0;
                String cstr;//, inter, size;
                String str[] = new String[3];
                int i = 0;

                if (flag == false) {
                    System.out.println("Process is killed or Does not Exist");
                    buf4 = "stop".getBytes();
                    serversocket.send(new DatagramPacket(buf4, "stop".length(), ia1, sport)); // sending stop to ravi
                    flag = true;
                }

                if (f1 == 0) {
                    System.out.println("Waiting for PID....");
                    f1++;
                }

                
                
                //COMMENTED BY RAVI
                
                serversocket.receive(dp);               // recieving from maharshi
                cstr = new String(dp.getData(), 0, dp.getLength());

                StringTokenizer strt = new StringTokenizer(cstr, "=;");
				
                while (strt.hasMoreTokens()) {
                    str[i] = strt.nextToken();
                    //System.out.println("Data is " + str[i]);
                    i++;
                }
				
                
                
                if (str[0].equals("STOP")) {
                    buf4 = str[0].getBytes();
                    serversocket.send(new DatagramPacket(buf4, str[0].length(), ia1, sport)); // sending STOP to ravi

                    System.out.println("Software Closed....");
                    serversocket.close();
                    System.exit(0);
                }
                
                
                
                int intervel = Integer.parseInt(str[1]);

                serversocket.setSoTimeout(intervel);
                
				
                
                //sending
              
                
                buf4 = str[2].getBytes();
                serversocket.send(new DatagramPacket(buf4, str[2].length(), ia1, sport)); // sending file size to ravi
				
                
                
                
                while (true) {

                    String str2 = null;

                    try {
                    	
                    	//COMMENTED BY RAVI
                        
                    	//System.out.println("in 2nd while waiting for stop");
                        if (f2 == 0) {
                            System.out.println("Press Stop Logging button to stop logging " + str[0] + " Process");
                            f2++;
                        }

                        flag = false;
                        //System.out.println("waiting for stop");
                        serversocket.receive(dp1); //for 2nd pc

                        System.out.println("Stop Logging Pressed");
                        str2 = new String(dp1.getData(), 0, dp1.getLength()); //for 2nd pc
                        f1 = 0;
                        flag = true;
                    	
                    	

                    } catch (Exception e) {
                        System.out.println("catch in 2nd while stop not recieved" + e);
                        e.printStackTrace();
                        str2 = "wait";
                    } finally {

                        //System.out.println("After try and catch");
                        String str1[];
                        //String str1[] = {"1st data", "2nd data", "3rd data", "4th data"};

                        try {
                        	
                        	
                        	
                            System.out.println("calling main");
                            str1 = LowerLevelInfoFetcher.main(str[0]);
                            
                            
                            
//                            str1 = Main.main("2862"); //added by ravi
                            if ("invalid".equals(str1[0])) {
                                buf3 = str1[0].getBytes();
                                serversocket.send(new DatagramPacket(buf3, str1[0].length(), ia2, sportg));
                                //System.out.println("invalid sent " + str1[0]);
                                f1 = 0;
                                break;
                            }

                            //System.out.println("data fetched");

                            /*for (int j = 0; j < 4; j++) {
                             System.out.println(j + 1 + "th data " + str1[j]);
                             }*/
                            DATA = str1[0] + "=;" + str1[1] + "=;" + str1[2] + "=;" + str1[3] + "=;" + str2;

                            System.out.println("combined data is " + DATA);
                            
                            
                            //COMMENTED BY RAVI
                            
                            buf2 = DATA.getBytes();

                            serversocket.send(new DatagramPacket(buf2, DATA.length(), ia1, sport));
                            //System.out.println("1st send");
                            
                            
                            if ("stop".equals(str2)) {
                                break;
                            }

                            //COMMENTED BY RAVI
                            //sending to maharshi
                            
                            String strmh = str1[2] + "=;" + str1[3];
                            buf3 = strmh.getBytes();
                            serversocket.send(new DatagramPacket(buf3, strmh.length(), ia2, sportg));
                            
                            
                        } catch (Exception e) {
                            System.out.println("error in 2nd while " + e);
                            //e.printStackTrace();
                        }
                    }
                }
            } catch (Exception e) {
            	
                System.out.println("error in 1st while " );
                 e.printStackTrace();
            }
        }
    }
}
