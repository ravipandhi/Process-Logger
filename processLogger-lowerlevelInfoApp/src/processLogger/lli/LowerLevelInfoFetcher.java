package processLogger.lli;

import java.io.*;
import java.util.*;

class LowerLevelInfoFetcher {

    //JNI (Java Native Interface to run c code in java)
	
	public native double nativePrint();

    static {
    	System.load("/home/ravipandhi/workspace/processLogger-lowerlevelInfoApp/resources/libjniClockTickImpl.so");
    }
    

    static String[] main(String args) {
        
        String send[] = new String[4];
        try {

            String pid = args;			//PID (Static)
            int PID = Integer.parseInt(pid);

            //System.out.println(pid);
            double clocktcs = new LowerLevelInfoFetcher().nativePrint();
            System.out.println(clocktcs);
            String command = "cat /proc/" + PID + "/stat";		//All info in stat file of 'PID' process

            String command2 = "cat /proc/uptime";				//Uptime of the system and amount of time spent in idle process in seconds

            String command3 = "cat /proc/meminfo";				//Memory info from meminfo file

            Runtime r = Runtime.getRuntime();

            Process p = r.exec(command);
            Process p2 = r.exec(command2);
            Process p3 = r.exec(command3);

            InputStream in = p.getInputStream();
            InputStream in2 = p2.getInputStream();
            InputStream in3 = p3.getInputStream();

            // Get the input stream and read from it
            String result = getStringFromInputStream(in);
            String result2 = getStringFromInputStream(in2);
            String result3 = getStringFromInputStream(in3);

            String results[] = new String[9];
            String uptime = null;
            String totmem = null;

            System.out.println("Stat file results: \n" + result + "\n");
//            System.out.println("Uptime file results: \n" + result2 + "\n");
//            System.out.println("Meminfo file results: \n" + result3 + "\n");
            
            StringTokenizer rs = new StringTokenizer(result);
            StringTokenizer rs2 = new StringTokenizer(result2);
            StringTokenizer rs3 = new StringTokenizer(result3);

            //Loop for selecting and storing required fields from stat file
            for (int i = 0, j = 0; rs.hasMoreElements(); i++) {

                //i = 0 - PID, 1 - process name, 13 - utime, 14 - stime, 16 - cutime, 17 - cstime, 19 - num of threads, 21 - start time of process, 23 - no. of pages of a process.
                //for more detail refer txt file i've attached in mail
                if (i == 0 || i == 1 || i == 13 || i == 14 || i == 16 || i == 17 || i == 19 || i == 21 || i == 23) {
                    results[j] = (String) (rs.nextElement());
                    j++;
                } else {
                    rs.nextElement();
                }
            }

            System.out.println("No of Threads is " + results[6]);
            //Loop for selecting and storing 'uptime of the system'
            for (int i = 0; rs2.hasMoreElements(); i++) {
                if (i == 0) {
                    uptime = (String) (rs2.nextElement());
                } else {
                    rs2.nextElement();
                }
            }

            //Loop for selecting and storing only Total memory field
            for (int i = 0; rs3.hasMoreElements(); i++) {
                if (i == 1) {
                    totmem = (String) rs3.nextElement();
                } else {
                    rs3.nextElement();
                }
            }

            /*for (int i = 0; i < results.length; i++) {
             System.out.println("result " + (i+1) + " = " + results[i]);
             }*/
            
//            System.out.println("\nUptime of the system = " + uptime);
//            System.out.println("\nTotal memory = " + totmem);
            
            double pages = Double.parseDouble(results[8]);			//conversion from string to double
            double memtotal = Double.parseDouble(totmem);			//conversion fron string to double
            double memusage = pages * (4) * 100 / memtotal;			//Equation for calculating mem usage in percentage
            
//            System.out.println("\nMemory Usage in percentage = " + memusage);	//we can round of and truncate it's value if needed

            //CPU Utilization
            double utime = Double.parseDouble(uptime);
//            System.out.println("utime = " + utime);
            double total_time = Integer.parseInt(results[2]) + Integer.parseInt(results[3]);
//            System.out.println("\nTotal time without child = " + total_time);
            total_time = total_time + Integer.parseInt(results[4]) + Integer.parseInt(results[5]);
//            System.out.println("\nTotal time = " + total_time);

//            System.out.println(clocktcs);
//            System.out.println("start time = " + results[6]);
            double seconds = utime - (Integer.parseInt(results[6]) / clocktcs);
//            System.out.println("seconds = " + seconds);
            double cpu_usage = 100 * ((total_time / clocktcs) / seconds);

            System.out.println("CPU Usage in percentage = " + cpu_usage);
            send[0] = String.valueOf(PID);
            send[1] = results[6];
            send[2] = String.valueOf(cpu_usage);
            send[3] = String.valueOf(memusage);

            in.close();
            in2.close();
            in3.close();
        } catch (Exception e) {
            send[0] = "invalid";
            send[1] = "pid";
            System.out.println("Some Problem" + e);
            return send;
        }
        //System.out.println("out of try catch");
        return send;
    }

    private static String getStringFromInputStream(InputStream in) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(in));
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }

        return sb.toString();
    }
}
